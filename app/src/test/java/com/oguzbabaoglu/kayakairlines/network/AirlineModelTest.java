package com.oguzbabaoglu.kayakairlines.network;

import com.oguzbabaoglu.kayakairlines.network.response.AirlineModel;

import org.junit.Test;

import util.TestResourceLoader;

import static org.assertj.core.api.Assertions.assertThat;

public class AirlineModelTest {

  @Test public void testParse() {
    String json = TestResourceLoader.load("fixtures/airline_model.json");
    AirlineModel model = GsonProvider.get().fromJson(json, AirlineModel.class);

    assertThat(model.clazz()).isEqualTo("com.r9.harmony.httpd.mobileapis.AirlineMobile");
    assertThat(model.code()).isEqualTo("A2");
    assertThat(model.defaultName()).isEqualTo("Astra Airlines");
    assertThat(model.logoURL()).isEqualTo("/res/images/air/2x/A2.png?v=b1e3678060cf55e6816b12d265c3a651a4c7f991");
    assertThat(model.name()).isEqualTo("Astra Airlines");
    assertThat(model.phone()).isEqualTo("+30 2310 489 391");
    assertThat(model.site()).isEqualTo("www.astra-airlines.gr");
    assertThat(model.usName()).isEqualTo("Astra Airlines");
  }

  @Test public void phoneIsOptional() {
    AirlineModel model = AirlineModel.builder()
        .clazz("test")
        .code("test")
        .defaultName("test")
        .logoURL("test")
        .name("test")
        .phone(null)
        .site("test")
        .usName("test")
        .build();

    assertThat(model).isNotNull();
  }

  @Test public void siteIsOptional() {
    AirlineModel model = AirlineModel.builder()
        .clazz("test")
        .code("test")
        .defaultName("test")
        .logoURL("test")
        .name("test")
        .phone("test")
        .site(null)
        .usName("test")
        .build();

    assertThat(model).isNotNull();
  }
}