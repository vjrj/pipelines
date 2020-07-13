package org.gbif.pipelines.factory;

import org.gbif.pipelines.core.parsers.config.model.PipelinesConfig;
import org.gbif.pipelines.core.parsers.ws.client.metadata.MetadataServiceClient;
import org.gbif.pipelines.transforms.SerializableSupplier;

import lombok.SneakyThrows;

public class MetadataServiceClientFactory {

  private final MetadataServiceClient client;
  private static volatile MetadataServiceClientFactory instance;
  private static final Object MUTEX = new Object();

  @SneakyThrows
  private MetadataServiceClientFactory(PipelinesConfig config) {
    this.client = MetadataServiceClient.create(config.getGbifApi(), config.getContent());
  }

  /* TODO Comment */
  public static MetadataServiceClient getInstance(PipelinesConfig config) {
    if (instance == null) {
      synchronized (MUTEX) {
        if (instance == null) {
          instance = new MetadataServiceClientFactory(config);
        }
      }
    }
    return instance.client;
  }

  /* TODO Comment */
  public static SerializableSupplier<MetadataServiceClient> createSupplier(PipelinesConfig config) {
    return () -> new MetadataServiceClientFactory(config).client;
  }

  /* TODO Comment */
  public static SerializableSupplier<MetadataServiceClient> getInstanceSupplier(PipelinesConfig config) {
    return () -> getInstance(config);
  }
}
