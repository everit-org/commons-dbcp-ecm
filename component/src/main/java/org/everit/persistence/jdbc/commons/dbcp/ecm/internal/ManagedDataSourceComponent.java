/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.persistence.jdbc.commons.dbcp.ecm.internal;

import java.sql.SQLException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import javax.sql.DataSource;
import javax.sql.XADataSource;
import javax.transaction.TransactionManager;

import org.apache.commons.dbcp2.managed.BasicManagedDataSource;
import org.everit.osgi.ecm.annotation.Activate;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Deactivate;
import org.everit.osgi.ecm.annotation.ServiceRef;
import org.everit.osgi.ecm.component.ComponentContext;
import org.everit.osgi.ecm.component.ServiceHolder;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.everit.persistence.jdbc.commons.dbcp.ecm.AttributePriority;
import org.everit.persistence.jdbc.commons.dbcp.ecm.DSFConstants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import aQute.bnd.annotation.headers.ProvideCapability;

/**
 * Simple component that registers managed XADataSource as an OSGi service.
 */
@Component(componentId = DSFConstants.SERVICE_FACTORY_PID_MANAGED_DATASOURCE,
    configurationPolicy = ConfigurationPolicy.FACTORY,
    label = "Everit Commons DBCP ManagedDataSource",
    description = "A managed and pooled datasource component that retrieves connections from an "
        + "XADataSource based on the commons-dbcp implementation.")
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
public class ManagedDataSourceComponent extends AbstractComponent {

  private BasicManagedDataSource managedDataSource = null;

  private ServiceRegistration<DataSource> serviceRegistration;

  private Map<String, Object> tmServiceProperties = null;

  private TransactionManager transactionManager;

  private XADataSource xaDataSource;

  private Map<String, Object> xaDataSourceServiceProperties = null;

  /**
   * Component activator method.
   */
  @Activate
  public void activate(final ComponentContext<ManagedDataSourceComponent> componentContext) {
    managedDataSource = new BasicManagedDataSource();
    managedDataSource.setXaDataSourceInstance(xaDataSource);
    managedDataSource.setTransactionManager(transactionManager);

    Map<String, Object> componentProperties = componentContext.getProperties();

    Util.applyPropertiesOnBasicDataSource(managedDataSource, componentProperties);

    Dictionary<String, Object> serviceProperties =
        new Hashtable<String, Object>(componentProperties);
    Util.addReferenceIdsToServiceProperties("xaDataSource", xaDataSourceServiceProperties,
        serviceProperties);
    Util.addReferenceIdsToServiceProperties("transactionManager", tmServiceProperties,
        serviceProperties);

    serviceRegistration =
        componentContext.registerService(DataSource.class, managedDataSource, serviceProperties);
  }

  /**
   * Component deactivate method.
   */
  @Deactivate
  public void deactivate() {
    String servicePid = "";
    if (serviceRegistration != null) {
      ServiceReference<DataSource> serviceReference = serviceRegistration.getReference();
      servicePid = (String) serviceReference.getProperty("service.pid");
      serviceRegistration.unregister();
    }
    if (managedDataSource != null) {
      try {
        managedDataSource.close();
      } catch (SQLException e) {
        throw new RuntimeException("Error during closing data source at component " + servicePid);
      }
    }
  }

  @ServiceRef(attributeId = DSFConstants.ATTR_TRANSACTION_MANAGER_TARGET, defaultValue = "",
      attributePriority = AttributePriority.P37_TRANSACTION_MANAGER_TARGET,
      label = "TransactionManager service filter",
      description = "The OSGi filter expression to select the right transaction manager.")
  public void setTransactionManager(final ServiceHolder<TransactionManager> serviceHolder) {
    transactionManager = serviceHolder.getService();
    tmServiceProperties = serviceHolder.getAttributes();
  }

  @ServiceRef(attributeId = DSFConstants.ATTR_XA_DATASOURCE_TARGET, defaultValue = "",
      attributePriority = AttributePriority.P01_XA_DATASOURCE_TARGET,
      label = "XADataSource service filter",
      description = "The OSGi filter expression to select the right XADataSource.")
  public void setXaDataSource(final ServiceHolder<XADataSource> serviceHolder) {
    xaDataSource = serviceHolder.getService();
    xaDataSourceServiceProperties = serviceHolder.getAttributes();
  }
}
