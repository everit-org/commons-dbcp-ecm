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

import org.everit.osgi.ecm.annotation.Activate;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Deactivate;
import org.everit.osgi.ecm.annotation.ServiceRef;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.component.ComponentContext;
import org.everit.osgi.ecm.component.ServiceHolder;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.everit.persistence.jdbc.commons.dbcp.ecm.DSFConstants;
import org.everit.persistence.jdbc.commons.dbcp.ecm.PriorityConstants;
import org.osgi.framework.ServiceRegistration;

import aQute.bnd.annotation.headers.ProvideCapability;

/**
 * Simple component that registers non pooling DataSource as an OSGi service.
 */
@Component(componentId = DSFConstants.SERVICE_FACTORY_PID_DATASOURCE,
    configurationPolicy = ConfigurationPolicy.FACTORY, label = "Everit Commons DBCP DataSource",
    description = "A pooled datasource component based on the commons-dbcp implementation.")
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@StringAttributes({
    @StringAttribute(attributeId = DSFConstants.ATTR_DEFAULT_QUERY_TIMEOUT,
        priority = PriorityConstants.PRIORITY_08, label = "Default query timeout",
        description = "Set the default query timeout that will be used for Statements created "
            + "from this connection. null  means that the driver default will be used.") })
public class DataSourceComponent extends AbstractComponent {

  private BasicSimpleDataSource basicDataSource = null;

  private DataSource dataSource;

  private Map<String, Object> dataSourceServiceProperties = null;

  private ServiceRegistration<DataSource> serviceRegistration;

  /**
   * Component activator method.
   */
  @Activate
  public void activate(final ComponentContext<DataSourceComponent> componentContext) {
    basicDataSource = new BasicSimpleDataSource();
    basicDataSource.setNonPoolingDataSource(dataSource);

    Map<String, Object> componentProperties = componentContext.getProperties();

    Util.applyPropertiesOnBasicDataSource(basicDataSource, componentProperties);

    Dictionary<String, Object> serviceProperties =
        new Hashtable<String, Object>(componentProperties);
    Util.addReferenceIdsToServiceProperties("dataSource", dataSourceServiceProperties,
        serviceProperties);

    serviceRegistration =
        componentContext.registerService(DataSource.class, basicDataSource, serviceProperties);
  }

  /**
   * Component deactivate method.
   */
  @Deactivate
  public void deactivate() {
    if (serviceRegistration != null) {
      serviceRegistration.unregister();
    }
    if (basicDataSource != null) {
      try {
        basicDataSource.close();
      } catch (SQLException e) {
        throw new RuntimeException("Error during closing data source at component ");
        // FIXME
        // + componentProperties.get("service.pid"));
      }
    }
  }

  @ServiceRef(attributeId = DSFConstants.ATTR_DATASOURCE_TARGET, defaultValue = "",
      attributePriority = PriorityConstants.PRIORITY_01, label = "DataSource service filter",
      description = "The OSGi filter expression to select the right non-pooled DataSource.")
  public void setDataSource(final ServiceHolder<DataSource> serviceHolder) {
    dataSource = serviceHolder.getService();
    dataSourceServiceProperties = serviceHolder.getAttributes();
  }
}
