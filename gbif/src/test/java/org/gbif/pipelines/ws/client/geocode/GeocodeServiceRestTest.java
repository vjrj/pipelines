package org.gbif.pipelines.ws.client.geocode;

import org.gbif.pipelines.ws.MockServer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Collection;

import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Tests the {@link GeocodeServiceRestTest} and {@link GeocodeService}.
 */
public class GeocodeServiceRestTest extends MockServer {

  private static final Double LATITUDE_CANADA = 60.4;
  private static final Double LONGITUDE_CANADA = -131.3;
  private static final String CANADA_COUNTRY_NAME = "Canada";
  private static final String CANADA_COUNTRY_CODE = "CA";

  @BeforeClass
  public static void setUp() throws IOException {
    mockServerSetUp();
  }

  @AfterClass
  public static void tearDown() throws IOException {
    mockServerTearDown();
  }

  @Test
  public void simpleCallTest() throws IOException {
    // @formatter:off
    // Call mocked: https://api.gbif-uat.org/v1/geocode/reverse?lat=60.4&lng=-131.3
    // @formatter:on

    enqueueResponse(REVERSE_CANADA_RESPONSE);

    GeocodeService service = GeocodeServiceRest.getInstance().getService();

    Call<Collection<GeocodeResponse>> call = service.reverse(LATITUDE_CANADA, LONGITUDE_CANADA);

    try {
      Response<Collection<GeocodeResponse>> response = call.execute();

      Assert.assertNotNull(response);

      Collection<GeocodeResponse> geocodeResponseCol = response.body();

      GeocodeResponse geocodeResponse = Iterables.get(geocodeResponseCol, 0);

      Assert.assertEquals(CANADA_COUNTRY_NAME, geocodeResponse.getCountryName());
      Assert.assertEquals(CANADA_COUNTRY_CODE, geocodeResponse.getIsoCountryCode2Digit());

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      System.out.println(gson.toJson(geocodeResponse));

    } catch (IOException e) {
      Assert.fail(e.getMessage());
    }

  }

  @Test
  public void nullParamsCallTest() {
    GeocodeService service = GeocodeServiceRest.getInstance().getService();

    enqueueErrorResponse(HttpURLConnection.HTTP_BAD_REQUEST);

    Call<Collection<GeocodeResponse>> call = service.reverse(null, null);

    try {
      Response<Collection<GeocodeResponse>> response = call.execute();

      Assert.assertNull(response.body());
    } catch (IOException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void invalidParamsCallTest() {
    GeocodeService service = GeocodeServiceRest.getInstance().getService();

    enqueueErrorResponse(HttpURLConnection.HTTP_BAD_REQUEST);

    Call<Collection<GeocodeResponse>> call = service.reverse(300d, 300d);

    try {
      Response<Collection<GeocodeResponse>> response = call.execute();

      Assert.assertNull(response.body());
    } catch (IOException e) {
      Assert.fail(e.getMessage());
    }
  }

}