/*
 * Copyright © 2019 camunda services GmbH (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.zeebe.kafka.connect.util;

import io.camunda.zeebe.client.ClientProperties;
import java.time.Duration;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.apache.kafka.common.config.ConfigDef.Width;

public final class ZeebeClientConfigDef {

  public static final String GATEWAY_ADDRESS_CONFIG = ClientProperties.GATEWAY_ADDRESS;
  public static final String REQUEST_TIMEOUT_CONFIG = ClientProperties.DEFAULT_REQUEST_TIMEOUT;
  public static final String USE_PLAINTEXT_CONFIG = ClientProperties.USE_PLAINTEXT_CONNECTION;

  public static final String CAMUNDA_CLOUD_CLUSTER_ID_CONFIG = "zeebe.client.cloud.clusterId";
  public static final String CAMUNDA_CLOUD_CLIENT_ID_CONFIG = "zeebe.client.cloud.clientId";
  public static final String CAMUNDA_CLOUD_CLIENT_SECRET_CONFIG = "zeebe.client.cloud.clientSecret";

  private static final String CLIENT_CONFIG_GROUP = "Zeebe Client";
  private static final String GATEWAY_ADDRESS_DEFAULT = "localhost:26500";
  private static final String GATEWAY_ADDRESS_DOC =
      "Gateway address, e.g. ``localhost:26500``, for the Zeebe client";
  private static final long REQUEST_TIMEOUT_DEFAULT = Duration.ofSeconds(1).toMillis();
  private static final String REQUEST_TIMEOUT_DOC =
      "How long to wait before a request to the broker is timed out";
  private static final boolean USE_PLAINTEXT_DEFAULT = false;
  private static final String USE_PLAINTEXT_DOC =
      "Disable secure connection to gateway for the Zeebe client";

  private static final String CAMUNDA_CLOUD_CLUSTER_ID_DOC =
      "Camunda Cloud Cluster ID to connect to (on cloud.camunda.io). If set this is used instead of the gateway address.";
  private static final String CAMUNDA_CLOUD_CLIENT_ID_DOC = "Camunda Cloud Client ID";
  private static final String CAMUNDA_CLOUD_CLIENT_SECRET_DOC = "Camunda Cloud Client Secret";

  private ZeebeClientConfigDef() {}

  public static void defineClientGroup(final ConfigDef definitions) {
    int order = 0;

    definitions
        .define(
            GATEWAY_ADDRESS_CONFIG,
            Type.STRING,
            GATEWAY_ADDRESS_DEFAULT,
            Importance.HIGH,
            GATEWAY_ADDRESS_DOC,
            CLIENT_CONFIG_GROUP,
            ++order,
            Width.SHORT,
            "Broker contact point")
        .define(
            REQUEST_TIMEOUT_CONFIG,
            Type.LONG,
            REQUEST_TIMEOUT_DEFAULT,
            Importance.LOW,
            REQUEST_TIMEOUT_DOC,
            CLIENT_CONFIG_GROUP,
            ++order,
            Width.SHORT,
            "Request timeout")
        .define(
            USE_PLAINTEXT_CONFIG,
            Type.BOOLEAN,
            USE_PLAINTEXT_DEFAULT,
            Importance.LOW,
            USE_PLAINTEXT_DOC,
            CLIENT_CONFIG_GROUP,
            ++order,
            Width.SHORT,
            "Use plaintext connection")
        .define(
            CAMUNDA_CLOUD_CLUSTER_ID_CONFIG,
            Type.STRING,
            null,
            Importance.LOW,
            CAMUNDA_CLOUD_CLUSTER_ID_DOC,
            CLIENT_CONFIG_GROUP,
            ++order,
            Width.MEDIUM,
            "Zeebe Cluster Id")
        .define(
            CAMUNDA_CLOUD_CLIENT_ID_CONFIG,
            Type.STRING,
            null,
            Importance.LOW,
            CAMUNDA_CLOUD_CLIENT_ID_DOC,
            CLIENT_CONFIG_GROUP,
            ++order,
            Width.MEDIUM,
            "Zeebe Client Id")
        .define(
            CAMUNDA_CLOUD_CLIENT_SECRET_CONFIG,
            Type.STRING,
            null,
            Importance.LOW,
            CAMUNDA_CLOUD_CLIENT_SECRET_DOC,
            CLIENT_CONFIG_GROUP,
            ++order,
            Width.MEDIUM,
            "Zeebe Client Secret");
  }
}
