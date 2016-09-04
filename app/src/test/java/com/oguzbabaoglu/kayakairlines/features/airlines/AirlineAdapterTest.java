package com.oguzbabaoglu.kayakairlines.features.airlines;

import android.net.Uri;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.model.AirlineResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import testutil.Airlines;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import static testutil.Airlines.airlineResponseBuilder;

@RunWith(RobolectricTestRunner.class) // Required for android.net.Uri implementation
public class AirlineAdapterTest {

  @Before public void setUp() {
    initMocks(this);
  }

  @Test public void testFromAirlineResponse() {
    AirlineResponse airlineResponse = Airlines.airlineResponse("test", "test");

    Airline result = AirlineAdapter.fromAirlineResponse(airlineResponse);

    assertThat(result.name()).isEqualTo(airlineResponse.name());
    assertThat(result.code()).isEqualTo(airlineResponse.code());
    assertThat(result.logoUrl()).isEqualTo(Uri.parse("https://www.kayak.com/logoUrl"));
    assertThat(result.phone()).isEqualTo(airlineResponse.phone());
    assertThat(result.websiteUrl()).isEqualTo(Uri.parse("http://site"));
  }

  @Test public void testHandlesAllWebsiteFormats() {
    Airline result;
    AirlineResponse response;

    response = airlineResponseBuilder().site("http://jp.ch.com").build();
    result = AirlineAdapter.fromAirlineResponse(response);
    assertThat(result.websiteUrl()).isEqualTo(Uri.parse("http://jp.ch.com"));

    response = airlineResponseBuilder().site("https://www.flynamair.com").build();
    result = AirlineAdapter.fromAirlineResponse(response);
    assertThat(result.websiteUrl()).isEqualTo(Uri.parse("https://www.flynamair.com"));

    response = airlineResponseBuilder().site("indian-airlines.nic.in/").build();
    result = AirlineAdapter.fromAirlineResponse(response);
    assertThat(result.websiteUrl()).isEqualTo(Uri.parse("http://indian-airlines.nic.in/"));

    response = airlineResponseBuilder().site("w2.volawindjet.it/default.aspx?lang=en").build();
    result = AirlineAdapter.fromAirlineResponse(response);
    assertThat(result.websiteUrl()).isEqualTo(Uri.parse("http://w2.volawindjet.it/default.aspx?lang=en"));
  }
}