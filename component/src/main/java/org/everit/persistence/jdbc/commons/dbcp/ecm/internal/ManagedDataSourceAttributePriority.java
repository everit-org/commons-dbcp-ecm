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
 * Constants of Managed DataSource priority.
 */
public final class ManagedDataSourceAttributePriority {

  public static final int P01_XA_DATASOURCE_TARGET = 1;

  public static final int P37_TRANSACTION_MANAGER_TARGET = 37;

  private ManagedDataSourceAttributePriority() {
  }
}
