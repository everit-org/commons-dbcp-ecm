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

/**
 * Constants of Data Source priority.
 */
public final class DataSourceAttributePriority {

  public static final int P00_SERVICE_DESCRIPTION = 0;

  public static final int P01_DATASOURCE_TARGET = 1;

  public static final int P02_DEFAULT_AUTO_COMMIT = 2;

  public static final int P03_ENABLE_AUTO_COMMIT_ON_RETURN = 3;

  public static final int P04_ROLLBACK_ON_RETURN = 4;

  public static final int P05_DEFAULT_READ_ONLY = 5;

  public static final int P06_DEFAULT_TRANSACTION_ISOLATION = 6;

  public static final int P07_DEFAULT_CATALOG = 7;

  public static final int P08_DEFAULT_QUERY_TIMEOUT = 8;

  public static final int P09_MAX_TOTAL = 9;

  public static final int P10_MAX_IDLE = 10;

  public static final int P11_MIN_IDLE = 11;

  public static final int P12_INITIAL_SIZE = 12;

  public static final int P13_CACHE_STATE = 13;

  public static final int P14_LIFO = 14;

  public static final int P15_MAX_CONN_LIFETIME_MILLIS = 15;

  public static final int P16_MAX_WAIT_MILLIS = 16;

  public static final int P17_POOL_PREPARED_STATEMENTS = 17;

  public static final int P18_MAX_OPEN_PREPARED_STATEMENTS = 18;

  public static final int P19_TEST_ON_CREATE = 19;

  public static final int P20_TEST_ON_BORROW = 20;

  public static final int P21_TEST_ON_RETURN = 21;

  public static final int P22_TIME_BETWEEN_EVICTION_RUNS_MILLIS = 22;

  public static final int P23_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS = 23;

  public static final int P24_NUM_TESTS_PER_EVICTION_RUN = 24;

  public static final int P25_MIN_EVICTABLE_IDLE_TIME_MILLIS = 25;

  public static final int P26_TEST_WHILE_IDLE = 26;

  public static final int P27_VALIDATION_QUERY = 27;

  public static final int P28_VALIDATION_QUERY_TIMEOUT = 28;

  public static final int P29_CONNECTION_INIT_SQLS = 29;

  public static final int P30_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED = 30;

  public static final int P31_REMOVE_ABANDONED_ON_MAINTENANCE = 31;

  public static final int P32_REMOVE_ABANDONED_ON_BORROW = 32;

  public static final int P33_REMOVE_ABANDONED_TIMEOUT = 33;

  public static final int P34_LOG_ABANDONED = 34;

  public static final int P35_ABANDONED_USAGE_TRACKING = 35;

  public static final int P36_JMX_NAME = 36;

  public static final int P37_TRANSACTION_MANAGER_TARGET = 37;

  private DataSourceAttributePriority() {
  }
}
