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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.everit.persistence.jdbc.commons.dbcp.ecm.DSFConstants;

/**
 * Utility to process properties.
 */
public final class Util {

  /**
   * Add reference IDs to service property.
   */
  public static void addReferenceIdsToServiceProperties(final String prefix,
      final Map<String, Object> referenceProps,
      final Dictionary<String, Object> serviceProps) {

    Object serviceId = referenceProps.get("service.id");
    if (serviceId != null) {
      serviceProps.put(prefix + ".service.id", serviceId);
    }

    Object servicedPid = referenceProps.get("service.pid");
    if (servicedPid != null) {
      serviceProps.put(prefix + ".service.pid", servicedPid);
    }
  }

  private static void applyConnectionInitSqlsOnBasicDataSource(
      final BasicDataSource basicDataSource, final Object connectionInitSqlsObject) {
    if (connectionInitSqlsObject != null) {
      if (!(connectionInitSqlsObject instanceof String[])) {
        throw new RuntimeException("Expected type of " + DSFConstants.ATTR_CONNECTION_INIT_SQLS
            + "' is not String[].");
      }

      Collection<String> connectionInitSqls = Arrays.asList((String[]) connectionInitSqlsObject);
      basicDataSource.setConnectionInitSqls(connectionInitSqls);
    }
  }

  /**
   * Apply component properties in {@link BasicDataSource}.
   */
  public static void applyPropertiesOnBasicDataSource(final BasicDataSource basicDataSource,
      final Map<String, Object> componentProperties) {

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED, boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_DEFAULT_AUTO_COMMIT, Boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_DEFAULT_CATALOG, String.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_DEFAULT_READ_ONLY, Boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_DEFAULT_QUERY_TIMEOUT,
        Integer.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.ATTR_INITIAL_SIZE,
        int.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.ATTR_LOG_ABANDONED,
        boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.ATTR_MAX_TOTAL,
        int.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.ATTR_MAX_IDLE,
        int.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_MAX_OPEN_PREPARED_STATEMENTS,
        int.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_MAX_WAIT_MILLIS, long.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_MIN_EVICTABLE_IDLE_TIME_MILLIS,
        long.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.ATTR_MIN_IDLE,
        int.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_NUM_TESTS_PER_EVICTION_RUN,
        int.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_POOL_PREPARED_STATEMENTS,
        boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_REMOVE_ABANDONED_ON_BORROW,
        boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_REMOVE_ABANDONED_ON_MAINTENANCE,
        boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_REMOVE_ABANDONED_TIMEOUT,
        int.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_TEST_ON_BORROW, boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_TEST_ON_RETURN, boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_TEST_WHILE_IDLE, boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_TIME_BETWEEN_EVICTION_RUNS_MILLIS,
        long.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_VALIDATION_QUERY, String.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_VALIDATION_QUERY_TIMEOUT,
        int.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.ATTR_LIFO,
        boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.ATTR_CACHE_STATE,
        boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_TEST_ON_CREATE, boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS, long.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_MAX_CONN_LIFETIME_MILLIS,
        long.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties, DSFConstants.ATTR_JMX_NAME,
        String.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_ENABLE_AUTO_COMMIT_ON_RETURN,
        boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_ROLLBACK_ON_RETURN, boolean.class);

    Util.setPropertyIfNotNull(basicDataSource, componentProperties,
        DSFConstants.ATTR_ABANDONED_USAGE_TRACKING,
        boolean.class);

    Object connectionInitSqlsObject =
        componentProperties.get(DSFConstants.ATTR_CONNECTION_INIT_SQLS);
    Util.applyConnectionInitSqlsOnBasicDataSource(basicDataSource, connectionInitSqlsObject);

    Object transactionIsolationObject =
        componentProperties.get(DSFConstants.ATTR_DEFAULT_TRANSACTION_ISOLATION);
    Util.applyTransactionIsolationPropertyOnBasicDataSource(basicDataSource,
        transactionIsolationObject);
  }

  private static void applyTransactionIsolationPropertyOnBasicDataSource(
      final BasicDataSource basicDataSource,
      final Object transactionIsolationObject) {
    if (transactionIsolationObject != null) {
      if (DSFConstants.VALUE_READ_COMMITED.equals(transactionIsolationObject)) {
        basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
      } else if (DSFConstants.VALUE_READ_UNCOMMITTED.equals(transactionIsolationObject)) {
        basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
      } else if (DSFConstants.VALUE_REPEATABLE_READ.equals(transactionIsolationObject)) {
        basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
      } else if (DSFConstants.VALUE_SERIALIZABLE.equals(transactionIsolationObject)) {
        basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
      } else if (DSFConstants.VALUE_TRANSACTION_NONE.equals(transactionIsolationObject)) {
        basicDataSource.setDefaultTransactionIsolation(Connection.TRANSACTION_NONE);
      } else {
        throw new RuntimeException(
            "Unknown value '" + transactionIsolationObject + "' for the property key '"
                + DSFConstants.ATTR_DEFAULT_TRANSACTION_ISOLATION
                + " during datasource configuration.");
      }
    }
  }

  private static Object convertConfigurationValueIfNecessary(final Class<?> paramType,
      final Object configValue) {
    if ((configValue == null)
        || ((configValue instanceof String) && "".equals(((String) configValue).trim()))) {
      return null;
    }
    if (paramType.isAssignableFrom(Integer.class)) {
      return Util.convertObjectToIntegerIfNecessary(configValue);
    } else if (paramType.isAssignableFrom(Long.class)) {
      return Util.convertObjectToLongIfNecessary(configValue);
    } else if (paramType.isAssignableFrom(Boolean.class)) {
      return Util.convertObjectToBooleanIfNecessary(configValue);
    }
    return configValue;
  }

  private static Object convertObjectToBooleanIfNecessary(final Object configValue) {
    if (configValue instanceof String) {
      if (configValue.equals(DSFConstants.BOOLEAN_OPT_DEFAULT)) {
        return null;
      }
      return Boolean.valueOf((String) configValue);
    }
    return configValue;
  }

  private static Object convertObjectToIntegerIfNecessary(final Object configValue) {
    if (configValue instanceof String) {
      return Integer.valueOf((String) configValue);
    }
    return configValue;
  }

  private static Object convertObjectToLongIfNecessary(final Object configValue) {
    if (configValue instanceof String) {
      return Long.valueOf((String) configValue);
    }
    return configValue;
  }

  private static String convertPropNameToMethodName(final String propName) {
    return "set" + Character.toUpperCase(propName.charAt(0)) + propName.substring(1);
  }

  private static Class<?> convertToConfigValueType(final Class<?> originalType) {
    if (boolean.class.isAssignableFrom(originalType)) {
      return Boolean.class;
    }
    if (int.class.isAssignableFrom(originalType)) {
      return Integer.class;
    }
    if (long.class.isAssignableFrom(originalType)) {
      return Long.class;
    }
    return originalType;
  }

  private static <T> void setPropertyIfNotNull(final BasicDataSource dataSource,
      final Map<String, Object> properties, final String propKey, final Class<T> paramType) {

    Object propValueObject = properties.get(propKey);
    propValueObject = Util.convertConfigurationValueIfNecessary(paramType, propValueObject);
    if (propValueObject == null) {
      return;
    }

    Class<?> propType = Util.convertToConfigValueType(paramType);
    if (!propType.isAssignableFrom(propValueObject.getClass())) {
      throw new RuntimeException("Expected type of property '" + propKey + "' is '" + propType
          + "' while the component property has the type '" + propValueObject.getClass() + "'");
    }

    String methodName = Util.convertPropNameToMethodName(propKey);
    try {
      Method method = dataSource.getClass().getMethod(methodName, paramType);
      method.invoke(dataSource, propValueObject);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException("Error during setting property '" + propKey + "' of datasource",
          e);
    } catch (SecurityException e) {
      throw new RuntimeException("Error during setting property '" + propKey + "' of datasource",
          e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Error during setting property '" + propKey + "' of datasource",
          e);
    } catch (IllegalArgumentException e) {
      throw new RuntimeException("Error during setting property '" + propKey + "' of datasource",
          e);
    } catch (InvocationTargetException e) {
      throw new RuntimeException("Error during setting property '" + propKey + "' of datasource",
          e);
    }
  }

  private Util() {
  }
}
