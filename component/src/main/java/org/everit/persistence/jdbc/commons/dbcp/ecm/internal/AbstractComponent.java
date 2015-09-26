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

import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.everit.osgi.ecm.annotation.attribute.BooleanAttribute;
import org.everit.osgi.ecm.annotation.attribute.BooleanAttributes;
import org.everit.osgi.ecm.annotation.attribute.IntegerAttribute;
import org.everit.osgi.ecm.annotation.attribute.IntegerAttributes;
import org.everit.osgi.ecm.annotation.attribute.LongAttribute;
import org.everit.osgi.ecm.annotation.attribute.LongAttributes;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributeOption;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.persistence.jdbc.commons.dbcp.ecm.DSFConstants;
import org.everit.persistence.jdbc.commons.dbcp.ecm.PriorityConstants;
import org.osgi.framework.Constants;

/**
 * Abstract component information.
 */
@StringAttributes({
    @StringAttribute(attributeId = DSFConstants.ATTR_DEFAULT_AUTO_COMMIT,
        priority = PriorityConstants.PRIORITY_02, label = "Default auto commit mode",
        description = "The default auto-commit state of connections created by this pool.",
        defaultValue = DSFConstants.BOOLEAN_OPT_DEFAULT, options = {
            @StringAttributeOption(value = DSFConstants.BOOLEAN_OPT_DEFAULT, label = "Default"),
            @StringAttributeOption(value = DSFConstants.BOOLEAN_OPT_TRUE, label = "True"),
            @StringAttributeOption(value = DSFConstants.BOOLEAN_OPT_FALSE, label = "False")
        }),
    @StringAttribute(attributeId = DSFConstants.ATTR_DEFAULT_READ_ONLY,
        priority = PriorityConstants.PRIORITY_05, label = "Default read-only state",
        description = "The default read-only state of connections created by this pool.",
        defaultValue = DSFConstants.BOOLEAN_OPT_DEFAULT, options = {
            @StringAttributeOption(value = DSFConstants.BOOLEAN_OPT_DEFAULT, label = "Default"),
            @StringAttributeOption(value = DSFConstants.BOOLEAN_OPT_TRUE, label = "True"),
            @StringAttributeOption(value = DSFConstants.BOOLEAN_OPT_FALSE, label = "False")
        }),
    @StringAttribute(attributeId = DSFConstants.ATTR_DEFAULT_TRANSACTION_ISOLATION,
        priority = PriorityConstants.PRIORITY_06, label = "Default transaction isolation",
        description = "The default TransactionIsolation state of connections created by this pool.",
        defaultValue = DSFConstants.VALUE_READ_COMMITED, options = {
            @StringAttributeOption(value = DSFConstants.VALUE_TRANSACTION_NONE,
                label = "None"),
            @StringAttributeOption(value = DSFConstants.VALUE_READ_UNCOMMITTED,
                label = "Read uncommited"),
            @StringAttributeOption(value = DSFConstants.VALUE_READ_COMMITED,
                label = "Read commited"),
            @StringAttributeOption(value = DSFConstants.VALUE_REPEATABLE_READ,
                label = "Reapeatable read"),
            @StringAttributeOption(value = DSFConstants.VALUE_SERIALIZABLE,
                label = "Serializable")
        }),
    @StringAttribute(attributeId = DSFConstants.ATTR_DEFAULT_CATALOG,
        priority = PriorityConstants.PRIORITY_07, defaultValue = "", label = "Default catalog",
        description = "The default \"catalog\" of connections created by this pool."),
    @StringAttribute(attributeId = DSFConstants.ATTR_VALIDATION_QUERY,
        priority = PriorityConstants.PRIORITY_27, defaultValue = "", label = "Validation query",
        description = "The SQL query that will be used to validate connections from this pool "
            + "before returning them to the caller.  If specified, this query MUST be an "
            + "SQL SELECT statement that returns at least one row."),
    @StringAttribute(attributeId = DSFConstants.ATTR_JMX_NAME,
        priority = PriorityConstants.PRIORITY_36, defaultValue = "", label = "JMX name",
        description = "The JMX name that has been requested for this DataSource. If the requested "
            + "name is not valid, an alternative may be chosen. This DataSource will attempt to "
            + "register itself using this name. If another component registers this DataSource "
            + "with JMX and this name is valid this name will be used in preference to any "
            + "specified by the other component."),
    @StringAttribute(attributeId = Constants.SERVICE_DESCRIPTION, defaultValue = "",
        label = "Service Description",
        description = "The description of this component configuration. It is used to easily "
            + "identify the service registered by this component.") })
@BooleanAttributes({
    @BooleanAttribute(attributeId = DSFConstants.ATTR_ENABLE_AUTO_COMMIT_ON_RETURN,
        defaultValue = true, priority = PriorityConstants.PRIORITY_03,
        label = "Enable auto commit on return",
        description = "Sets the value of the flag that controls whether or not connections being "
            + "returned to the pool will checked and configured with Connection.setAutoCommit(true)"
            + " if the auto commit setting is 'false' when the connection is returned. "
            + "It is 'true' by default."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_ROLLBACK_ON_RETURN,
        defaultValue = true, priority = PriorityConstants.PRIORITY_04, label = "Rollback on return",
        description = "Sets the flag that controls if a connection will be rolled back when it "
            + "is returned to the pool if auto commit is not enabled and the connection is not "
            + "read only."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_CACHE_STATE,
        defaultValue = true, priority = PriorityConstants.PRIORITY_13, label = "Cache state",
        description = "The property that controls if the pooled connections cache some state "
            + "rather than query the database for current state to improve performance."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_LIFO,
        defaultValue = BaseObjectPoolConfig.DEFAULT_LIFO, priority = PriorityConstants.PRIORITY_14,
        label = "LIFO",
        description = "True means that borrowObject returns the most recently used (\"last in\") "
            + "connection in the pool (if there are idle connections available). False means that "
            + "the pool behaves as a FIFO queue - connections are taken from the idle instance "
            + "pool in the order that they are returned to the pool."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_POOL_PREPARED_STATEMENTS,
        defaultValue = false, priority = PriorityConstants.PRIORITY_17,
        label = "Pool prepared statements",
        description = "Prepared statement pooling for this pool. When this property is set to "
            + "\"true\" both PreparedStatements and CallableStatements are pooled."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_TEST_ON_CREATE,
        defaultValue = false, priority = PriorityConstants.PRIORITY_19, label = "Test on create",
        description = "The indication of whether objects will be validated as soon as they have "
            + "been created by the pool. If the object fails to validate, the borrow operation "
            + "that triggered the creation will fail."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_TEST_ON_BORROW,
        defaultValue = true, priority = PriorityConstants.PRIORITY_20, label = "Test on borrow",
        description = "The indication of whether objects will be validated before being borrowed "
            + "from the pool. If the object fails to validate, it will be dropped from the pool, "
            + "and we will attempt to borrow another. For a <code>true</code> value to have any "
            + "effect, the \"Validation query\" property must be set to a non-null string."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_TEST_ON_RETURN,
        defaultValue = false, priority = PriorityConstants.PRIORITY_21, label = "Test on return",
        description = "The indication of whether objects will be validated before being returned "
            + "to the pool. For a \"true\" value to have any effect, the 'Validation query' "
            + "property must be set to a non-null string."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_TEST_WHILE_IDLE,
        defaultValue = false, priority = PriorityConstants.PRIORITY_26, label = "Test while idle",
        description = "This property determines whether or not the idle object evictor will "
            + "validate connections.  For a \"true\" value to have any effect, the "
            + "\"Validation query\" property must be set to a non-null string."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_ACCESS_TO_UNDERLYING_CONNECTION_ALLOWED,
        defaultValue = false, priority = PriorityConstants.PRIORITY_30,
        label = "Access to underlying connection allowed",
        description = "Controls access to the underlying connection."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_REMOVE_ABANDONED_ON_MAINTENANCE,
        defaultValue = false, priority = PriorityConstants.PRIORITY_31,
        label = "Remove abandoned on maintenance",
        description = "Flag to remove abandoned connections if they exceed the "
            + "removeAbandonedTimeout when borrowObject is invoked. If set to true a connection is "
            + "considered abandoned and eligible for removal if it has been idle longer than the "
            + "removeAbandonedTimeout. Setting this to true can recover db connections from poorly "
            + "written applications which fail to close a connection."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_REMOVE_ABANDONED_ON_BORROW,
        defaultValue = false, priority = PriorityConstants.PRIORITY_32,
        label = "Remove abandoned on borrow",
        description = "Flag to remove abandoned connections if they exceed the "
            + "removeAbandonedTimeout during pool maintenance. If set to true a connection is "
            + "considered abandoned and eligible for removal if it has been idle longer than the "
            + "removeAbandonedTimeout. Setting this to true can recover db connections from poorly "
            + "written applications which fail to close a connection."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_LOG_ABANDONED,
        defaultValue = false, priority = PriorityConstants.PRIORITY_34, label = "Log abandoned",
        description = "Flag to log stack traces for application code which abandoned a Statement "
            + "or Connection. Logging of abandoned Statements and Connections adds overhead for "
            + "every Connection open or new Statement because a stack trace has to be generated."),
    @BooleanAttribute(attributeId = DSFConstants.ATTR_ABANDONED_USAGE_TRACKING,
        defaultValue = false, priority = PriorityConstants.PRIORITY_35,
        label = "Abandoned usage tracking",
        description = "If the connection pool implements UsageTracking, configure whether the "
            + "connection pool should record a stack trace every time a method is called on a "
            + "pooled connection and retain the most recent stack trace to aid debugging of "
            + "abandoned connections.") })
@IntegerAttributes({
    @IntegerAttribute(attributeId = DSFConstants.ATTR_MAX_TOTAL,
        defaultValue = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL,
        priority = PriorityConstants.PRIORITY_09, label = "Max. total",
        description = "Sets the maximum total number of idle and borrows connections that can be "
            + "active at the same time. Use a negative value for no limit."),
    @IntegerAttribute(attributeId = DSFConstants.ATTR_MAX_IDLE,
        defaultValue = GenericObjectPoolConfig.DEFAULT_MAX_IDLE,
        priority = PriorityConstants.PRIORITY_10, label = "Max. idle",
        description = "The maximum number of connections that can remain idle in the pool. "
            + "Excess idle connections are destroyed on return to the pool."),
    @IntegerAttribute(attributeId = DSFConstants.ATTR_MIN_IDLE,
        defaultValue = GenericObjectPoolConfig.DEFAULT_MIN_IDLE,
        priority = PriorityConstants.PRIORITY_11, label = "Min. idle connections",
        description = "The minimum number of active connections that can remain idle in the pool, "
            + "without extra ones being created, or 0 to create none."),
    @IntegerAttribute(attributeId = DSFConstants.ATTR_INITIAL_SIZE,
        defaultValue = 0,
        priority = PriorityConstants.PRIORITY_12, label = "Initial size",
        description = "The initial number of connections that are created when the pool is "
            + "started."),
    @IntegerAttribute(attributeId = DSFConstants.ATTR_MAX_OPEN_PREPARED_STATEMENTS,
        defaultValue = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL,
        priority = PriorityConstants.PRIORITY_18, label = "Max. open prepared statements",
        description = "The maximum number of open statements that can be allocated from the "
            + "statement pool at the same time, or non-positive for no limit."),
    @IntegerAttribute(attributeId = DSFConstants.ATTR_NUM_TESTS_PER_EVICTION_RUN,
        defaultValue = GenericObjectPoolConfig.DEFAULT_NUM_TESTS_PER_EVICTION_RUN,
        priority = PriorityConstants.PRIORITY_24, label = "Test number per eviction run",
        description = "The number of objects to examine during each run of the idle object "
            + "evictor thread (if any)."),
    @IntegerAttribute(attributeId = DSFConstants.ATTR_VALIDATION_QUERY_TIMEOUT,
        defaultValue = -1,
        priority = PriorityConstants.PRIORITY_28, label = "Validation query timeout",
        description = "The validation query timeout, the amount of time, in seconds, that "
            + "connection validation will wait for a response from the database when executing a "
            + "validation query. Use a value less than or equal to 0 for no timeout."),
    @IntegerAttribute(attributeId = DSFConstants.ATTR_REMOVE_ABANDONED_TIMEOUT,
        defaultValue = DSFConstants.DEFAULT_REMOVE_ABANDONED_TIMEOUT,
        priority = PriorityConstants.PRIORITY_33, label = "Remove abandoned timeout",
        description = "Timeout in seconds before an abandoned connection can be removed.") })
@LongAttributes({
    @LongAttribute(attributeId = DSFConstants.ATTR_MAX_CONN_LIFETIME_MILLIS,
        defaultValue = -1,
        priority = PriorityConstants.PRIORITY_15, label = "Max. conn. lifetime millis",
        description = "Sets the maximum permitted lifetime of a connection in milliseconds. "
            + "A value of zero or less indicates an infinite lifetime."),
    @LongAttribute(attributeId = DSFConstants.ATTR_MAX_WAIT_MILLIS,
        defaultValue = GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS,
        priority = PriorityConstants.PRIORITY_16, label = "Max. wait",
        description = "The maximum number of milliseconds that the pool will wait for a connection"
            + " to be returned before throwing an exception. A value less than or equal to zero "
            + "means the pool is set to wait indefinitely."),
    @LongAttribute(attributeId = DSFConstants.ATTR_TIME_BETWEEN_EVICTION_RUNS_MILLIS,
        defaultValue = GenericObjectPoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS,
        priority = PriorityConstants.PRIORITY_22, label = "Time between eviction runs",
        description = "The number of milliseconds to sleep between runs of the idle object evictor"
            + " thread. When non-positive, no idle object evictor thread will be run."),
    @LongAttribute(attributeId = DSFConstants.ATTR_MIN_EVICTABLE_IDLE_TIME_MILLIS,
        defaultValue = GenericObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS,
        priority = PriorityConstants.PRIORITY_25, label = "Min. evictable idle time",
        description = "The minimum amount of time an object may sit idle in the pool before it is "
            + "eligable for eviction by the idle object evictor (if any)."),
    @LongAttribute(attributeId = DSFConstants.ATTR_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS,
        defaultValue = GenericObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS,
        priority = PriorityConstants.PRIORITY_23, label = "Soft min. evictable idle time in millis",
        description = "Sets the minimum amount of time a connection may sit idle in the pool "
            + "before it is eligible for eviction by the idle object evictor, with the extra "
            + "condition that at least \"minIdle\" connections remain in the pool.") })
public abstract class AbstractComponent {

  protected String[] connectionInitSqls;

  @StringAttribute(attributeId = DSFConstants.ATTR_CONNECTION_INIT_SQLS, defaultValue = "",
      priority = PriorityConstants.PRIORITY_29, label = "Connection init SQLs",
      description = "The list of SQL statements executed when a physical connection is "
          + "first created.")
  public void setConnectionInitSqls(final String[] connectionInitSqls) {
    this.connectionInitSqls = connectionInitSqls;
  }
}
