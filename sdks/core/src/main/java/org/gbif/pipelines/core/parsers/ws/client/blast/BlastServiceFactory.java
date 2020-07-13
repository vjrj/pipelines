package org.gbif.pipelines.core.parsers.ws.client.blast;

import java.util.concurrent.TimeUnit;

import org.gbif.pipelines.core.parsers.config.model.WsConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class BlastServiceFactory {
  private final BlastService service;
  private static volatile BlastServiceFactory instance;
  private static final Object MUTEX = new Object();

  private BlastServiceFactory(WsConfig wsConfig) {

    // create client
    OkHttpClient client =
        new OkHttpClient.Builder()
            .connectTimeout(wsConfig.getTimeoutSec(), TimeUnit.SECONDS)
            .readTimeout(wsConfig.getTimeoutSec(), TimeUnit.SECONDS)
            .build();

    // create service
    Retrofit retrofit =
        new Retrofit.Builder()
            .client(client)
            .baseUrl(wsConfig.getWsUrl())
            .addConverterFactory(JacksonConverterFactory.create())
            .validateEagerly(true)
            .build();

    service = retrofit.create(BlastService.class);
  }

  public static BlastServiceFactory getInstance(WsConfig config) {
    if (instance == null) {
      synchronized (MUTEX) {
        if (instance == null) {
          instance = new BlastServiceFactory(config);
        }
      }
    }
    return instance;
  }

  public BlastService getService() {
    return service;
  }

}
