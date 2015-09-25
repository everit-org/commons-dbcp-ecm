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

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DataSourceConnectionFactory;

/**
 * Simple {@link BasicDataSource} extended implementation.
 */
public class BasicSimpleDataSource extends BasicDataSource {

  private DataSource nonPoolingDataSource;

  /**
   * Creating a connection factory based on the provided DataSource.
   */
  @Override
  protected ConnectionFactory createConnectionFactory() throws SQLException {
    return new DataSourceConnectionFactory(nonPoolingDataSource);
  }

  public void setNonPoolingDataSource(final DataSource nonPoolDataSource) {
    nonPoolingDataSource = nonPoolDataSource;
  }
}
