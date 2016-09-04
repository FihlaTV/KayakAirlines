package com.oguzbabaoglu.kayakairlines.network;

import com.google.gson.Gson;
import com.oguzbabaoglu.kayakairlines.network.model.AirlineResponse;

import org.junit.Test;

import testutil.TestDagger;
import testutil.TestResourceLoader;

import static org.assertj.core.api.Assertions.assertThat;

public class AirlineResponseTest {

  @Test public void testParse() {
    Gson gson = TestDagger.INJECTOR.airlineComponent().gson();
    String json = TestResourceLoader.load("fixtures/airline_response.json");
    AirlineResponse response = gson.fromJson(json, AirlineResponse.class);

    assertThat(response.clazz()).isEqualTo("com.r9.harmony.httpd.mobileapis.AirlineMobile");
    assertThat(response.code()).isEqualTo("A2");
    assertThat(response.defaultName()).isEqualTo("Astra Airlines");
    assertThat(response.logoUrl()).isEqualTo("/res/images/air/2x/A2.png?v=b1e3678060cf55e6816b12d265c3a651a4c7f991");
    assertThat(response.name()).isEqualTo("Astra Airlines");
    assertThat(response.phone()).isEqualTo("+30 2310 489 391");
    assertThat(response.site()).isEqualTo("www.astra-airlines.gr");
    assertThat(response.usName()).isEqualTo("Astra Airlines");
  }

  @Test public void phoneIsOptional() {
    AirlineResponse response = AirlineResponse.builder()
        .clazz("test")
        .code("test")
        .defaultName("test")
        .logoUrl("test")
        .name("test")
        .phone(null)
        .site("test")
        .usName("test")
        .build();

    assertThat(response).isNotNull();
  }

  @Test public void siteIsOptional() {
    AirlineResponse response = AirlineResponse.builder()
        .clazz("test")
        .code("test")
        .defaultName("test")
        .logoUrl("test")
        .name("test")
        .phone("test")
        .site(null)
        .usName("test")
        .build();

    assertThat(response).isNotNull();
  }
}