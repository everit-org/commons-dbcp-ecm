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
import org.everit.osgi.ecm.annotation.ManualService;
import org.everit.osgi.ecm.annotation.ManualServices;
import org.everit.osgi.ecm.annotation.ServiceRef;
import org.everit.osgi.ecm.component.ComponentContext;
import org.everit.osgi.ecm.extender.ExtendComponent;
import org.everit.persistence.jdbc.commons.dbcp.ecm.DSFConstants;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * Simple component that registers non pooling DataSource as an OSGi service.
 */
@ExtendComponent
@Component(componentId = DSFConstants.SERVICE_FACTORY_PID_DATASOURCE,
    configurationPolicy = ConfigurationPolicy.FACTORY, label = "Everit Commons DBCP DataSource",
    description = "A pooled datasource component based on the commons-dbcp implementation.")
@ManualServices(@ManualService(DataSource.class))
public class DataSourceComponent extends AbstractComponent {

  private BasicSimpleDataSource basicDataSource = null;

  private DataSource dataSource;

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

    serviceRegistration =
        componentContext.registerService(DataSource.class, basicDataSource, serviceProperties);
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
    if (basicDataSource != null) {
      try {
        basicDataSource.close();
      } catch (SQLException e) {
        throw new RuntimeException("Error during closing data source at component " + servicePid);
      }
    }
  }

  @ServiceRef(attributeId = DSFConstants.ATTR_DATASOURCE_TARGET, defaultValue = "",
      attributePriority = DataSourceAttributePriority.P01_DATASOURCE_TARGET,
      label = "DataSource service filter",
      description = "The OSGi filter expression to select the right non-pooled DataSource.")
  public void setDataSource(final DataSource dataSource) {
    this.dataSource = dataSource;
  }
}
