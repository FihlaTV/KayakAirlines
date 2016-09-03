package com.oguzbabaoglu.kayakairlines.network;

import com.oguzbabaoglu.kayakairlines.features.airlines.DaggerAirlineComponent;
import com.oguzbabaoglu.kayakairlines.network.model.AirlineResponse;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import util.TestResourceLoader;

import static org.assertj.core.api.Assertions.assertThat;
import static util.AssertRecordedRequest.assertRecordedRequest;

public class KayakApiTest {

  @Rule public final MockWebServer server = new MockWebServer();

  KayakApi kayakApi;

  @Before public void setUp() {
    kayakApi = DaggerAirlineComponent.builder()
        .networkModule(new NetworkModule(server.url("/").toString()))
        .build()
        .kayakApi();
  }

  @Test public void testGetAirlines() throws InterruptedException {
    String response = TestResourceLoader.load("fixtures/get_airlines_response.json");
    server.enqueue(new MockResponse().setBody(response));

    List<AirlineResponse> list = kayakApi.getAirlines().toBlocking().value();

    assertThat(list).isNotNull().hasSize(3);
    assertThat(list.get(0).code()).isEqualTo("A2");

    assertRecordedRequest(server.takeRequest())
        .path("/h/mobileapis/directory/airlines")
        .method("GET");
  }
}