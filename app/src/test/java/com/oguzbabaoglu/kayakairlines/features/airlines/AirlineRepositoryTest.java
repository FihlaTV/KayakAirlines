package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.KayakApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import rx.Single;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static testutil.Airlines.airline;
import static testutil.Airlines.airlineResponse;

public class AirlineRepositoryTest {

  @Mock KayakApi kayakApi;

  AirlineRepository airlineRepository;

  @Before public void setUp() {
    initMocks(this);
    airlineRepository = new AirlineRepository(kayakApi);
  }

  @Test public void testGetAllAirlinesReturnsSortedList() {
    when(kayakApi.getAirlines()).thenReturn(Single.just(Arrays.asList(
        airlineResponse("Jet Blue"),
        airlineResponse("9 Air"),
        airlineResponse("Air Canada")
    )));

    List<Airline> airlines = airlineRepository.getAllAirlines().toBlocking().value();

    assertThat(airlines).containsExactly(
        airline("9 Air"),
        airline("Air Canada"),
        airline("Jet Blue")
    );
  }
}