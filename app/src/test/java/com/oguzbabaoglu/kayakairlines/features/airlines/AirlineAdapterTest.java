package com.oguzbabaoglu.kayakairlines.features.airlines;

import android.net.Uri;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.model.AirlineResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import testutil.Airlines;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static testutil.Airlines.airlineResponseBuilder;

@RunWith(RobolectricTestRunner.class) // Required for android.net.Uri implementation
public class AirlineAdapterTest {

  @Mock StarredAirlineHelper mockHelper;

  AirlineAdapter airlineAdapter;

  @Before public void setUp() {
    initMocks(this);
    airlineAdapter = new AirlineAdapter(mockHelper);
  }

  @Test public void testFromAirlineResponse() {
    AirlineResponse airlineResponse = Airlines.airlineResponse("test");
    when(mockHelper.isStarred(airlineResponse.code())).thenReturn(true);

    Airline result = airlineAdapter.fromAirlineResponse(airlineResponse);

    assertThat(result.name()).isEqualTo(airlineResponse.name());
    assertThat(result.code()).isEqualTo(airlineResponse.code());
    assertThat(result.logoUrl()).isEqualTo(Uri.parse("https://www.kayak.com/logoUrl"));
    assertThat(result.phone()).isEqualTo(airlineResponse.phone());
    assertThat(result.websiteUrl()).isEqualTo(Uri.parse("http://site"));
    assertThat(result.isStarred()).isTrue();
  }

  @Test public void testHandlesAllWebsiteFormats() {
    when(mockHelper.isStarred(anyString())).thenReturn(true);
    Airline result;
    AirlineResponse response;

    response = airlineResponseBuilder().site("http://jp.ch.com").build();
    result = airlineAdapter.fromAirlineResponse(response);
    assertThat(result.websiteUrl()).isEqualTo(Uri.parse("http://jp.ch.com"));

    response = airlineResponseBuilder().site("https://www.flynamair.com").build();
    result = airlineAdapter.fromAirlineResponse(response);
    assertThat(result.websiteUrl()).isEqualTo(Uri.parse("https://www.flynamair.com"));

    response = airlineResponseBuilder().site("indian-airlines.nic.in/").build();
    result = airlineAdapter.fromAirlineResponse(response);
    assertThat(result.websiteUrl()).isEqualTo(Uri.parse("http://indian-airlines.nic.in/"));

    response = airlineResponseBuilder().site("w2.volawindjet.it/default.aspx?lang=en").build();
    result = airlineAdapter.fromAirlineResponse(response);
    assertThat(result.websiteUrl()).isEqualTo(Uri.parse("http://w2.volawindjet.it/default.aspx?lang=en"));
  }
}