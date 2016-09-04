package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.KayakApi;
import com.oguzbabaoglu.kayakairlines.network.model.AirlineResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import rx.Single;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static testutil.Airlines.airline;
import static testutil.Airlines.airlineResponse;

public class AirlineRepositoryTest {

  @Mock KayakApi mockApi;
  @Mock AirlineAdapter mockAdapter;
  @Mock StarredAirlineHelper mockHelper;

  AirlineRepository airlineRepository;

  @Before public void setUp() {
    initMocks(this);
    when(mockHelper.isStarred(anyString())).thenReturn(false);
    airlineRepository = new AirlineRepository(mockApi, mockHelper, mockAdapter);
  }

  @Test public void testGetAllAirlinesReturnsSortedList() {
    when(mockApi.getAirlines()).thenReturn(Single.just(Arrays.asList(
        airlineResponse("Jet Blue"),
        airlineResponse("9 Air"),
        airlineResponse("Air Canada")
    )));

    when(mockAdapter.fromAirlineResponse(any(AirlineResponse.class))).thenReturn(
        airline("Jet Blue", "B6"),
        airline("9 Air", "AQ"),
        airline("Air Canada", "AC")
    );

    List<Airline> airlines = airlineRepository.getAllAirlines().toBlocking().value();

    assertThat(airlines).containsExactly(
        airline("9 Air", "AQ"),
        airline("Air Canada", "AC"),
        airline("Jet Blue", "B6")
    );
  }
}