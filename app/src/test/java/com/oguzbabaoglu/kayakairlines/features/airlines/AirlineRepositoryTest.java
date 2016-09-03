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
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AirlineRepositoryTest {

  @Mock KayakApi kayakApi;

  AirlineRepository airlineRepository;

  @Before public void setUp() {
    initMocks(this);
    airlineRepository = new AirlineRepository(kayakApi);
  }

  static Airline airline(String name) {
    return Airline.builder()
        .displayName(name)
        .code("test")
        .isStarred(false)
        .build();
  }

  static AirlineResponse airlineResponse(String name) {
    return AirlineResponse.builder()
        .clazz("test")
        .defaultName(name)
        .name(name)
        .code("test")
        .usName(name)
        .logoURL("test")
        .build();
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