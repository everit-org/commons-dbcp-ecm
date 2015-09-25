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
package org.everit.persistence.jdbc.commons.dbcp.ecm;

/**
 * Constants that make it possible to configure the DSF component programmatically.
 */
public final class DSFConstants {

  public static final String ATTR_ABANDONED_USAGE_TRACKING = "abandonedUsageTracking";

  public static final String ATTR_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED =
      "accessToUnderlyingConnectionAllowed";

  public static final String ATTR_CACHE_STATE = "cacheState";

  public static final String ATTR_CONNECTION_INIT_SQLS = "connectionInitSqls";

  public static final String ATTR_DATASOURCE_TARGET = "dataSource.target";

  public static final String ATTR_DEFAULT_AUTO_COMMIT = "defaultAutoCommit";

  public static final String ATTR_DEFAULT_CATALOG = "defaultCatalog";

  public static final String ATTR_DEFAULT_QUERY_TIMEOUT = "defaultQueryTimeout";

  public static final String ATTR_DEFAULT_READ_ONLY = "defaultReadOnly";

  public static final String ATTR_DEFAULT_TRANSACTION_ISOLATION = "defaultTransactionIsolation";

  public static final String ATTR_ENABLE_AUTO_COMMIT_ON_RETURN = "enableAutoCommitOnReturn";

  public static final String ATTR_INITIAL_SIZE = "initialSize";

  public static final String ATTR_JMX_NAME = "jmxName";

  public static final String ATTR_LIFO = "lifo";

  public static final String ATTR_LOG_ABANDONED = "logAbandoned";

  public static final String ATTR_MAX_CONN_LIFETIME_MILLIS = "maxConnLifetimeMillis";

  public static final String ATTR_MAX_IDLE = "maxIdle";

  public static final String ATTR_MAX_OPEN_PREPARED_STATEMENTS = "maxOpenPreparedStatements";

  public static final String ATTR_MAX_TOTAL = "maxTotal";

  public static final String ATTR_MAX_WAIT_MILLIS = "maxWaitMillis";

  public static final String ATTR_MIN_EVICTABLE_IDLE_TIME_MILLIS = "minEvictableIdleTimeMillis";

  public static final String ATTR_MIN_IDLE = "minIdle";

  public static final String ATTR_NUM_TESTS_PER_EVICTION_RUN = "numTestsPerEvictionRun";

  public static final String ATTR_POOL_PREPARED_STATEMENTS = "poolPreparedStatements";

  public static final String ATTR_REMOVE_ABANDONED_ON_BORROW = "removeAbandonedOnBorrow";

  public static final String ATTR_REMOVE_ABANDONED_ON_MAINTENANCE = "removeAbandonedOnMaintenance";

  public static final String ATTR_REMOVE_ABANDONED_TIMEOUT = "removeAbandonedTimeout";

  public static final String ATTR_ROLLBACK_ON_RETURN = "rollbackOnReturn";

  public static final String ATTR_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS =
      "softMinEvictableIdleTimeMillis";

  public static final String ATTR_TEST_ON_BORROW = "testOnBorrow";

  public static final String ATTR_TEST_ON_CREATE = "testOnCreate";

  public static final String ATTR_TEST_ON_RETURN = "testOnReturn";

  public static final String ATTR_TEST_WHILE_IDLE = "testWhileIdle";

  public static final String ATTR_TIME_BETWEEN_EVICTION_RUNS_MILLIS =
      "timeBetweenEvictionRunsMillis";

  public static final String ATTR_TRANSACTION_MANAGER_TARGET = "transactionManager.target";

  public static final String ATTR_VALIDATION_QUERY = "validationQuery";

  public static final String ATTR_VALIDATION_QUERY_TIMEOUT = "validationQueryTimeout";

  public static final String ATTR_XA_DATASOURCE_TARGET = "xaDataSource.target";

  public static final String BOOLEAN_OPT_DEFAULT = "default";

  public static final String BOOLEAN_OPT_FALSE = "false";

  public static final String BOOLEAN_OPT_TRUE = "true";

  public static final int DEFAULT_REMOVE_ABANDONED_TIMEOUT = 300;

  public static final String SERVICE_FACTORY_PID_DATASOURCE =
      "org.everit.persistence.jdbc.commons.dbcp.ecm.DataSource";

  public static final String SERVICE_FACTORY_PID_MANAGED_DATASOURCE =
      "org.everit.persistence.jdbc.commons.dbcp.ecm.ManagedDataSource";

  public static final String VALUE_READ_COMMITED = "readCommited";

  public static final String VALUE_READ_UNCOMMITTED = "readUncommited";

  public static final String VALUE_REPEATABLE_READ = "repeatableRead";

  public static final String VALUE_SERIALIZABLE = "serializable";

  public static final String VALUE_TRANSACTION_NONE = "none";

  private DSFConstants() {
  }
}
