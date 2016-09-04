package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.KayakApi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Single;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static testutil.Airlines.airline;
import static testutil.Airlines.airlineResponse;

@RunWith(RobolectricTestRunner.class) // Required for android.net.Uri implementation
public class AirlineRepositoryTest {

  @Mock KayakApi mockApi;

  AirlineRepository airlineRepository;

  @Before public void setUp() {
    initMocks(this);
    airlineRepository = new AirlineRepository(mockApi);
  }

  @Test public void testGetAllAirlinesReturnsSortedList() {
    when(mockApi.getAirlines()).thenReturn(Single.just(Arrays.asList(
        airlineResponse("Jet Blue", "B6"),
        airlineResponse("9 Air", "AQ"),
        airlineResponse("Air Canada", "AC")
    )));

    List<Airline> airlines = airlineRepository.getAllAirlines().toBlocking().value();

    assertThat(airlines).containsExactly(
        airline("9 Air", "AQ"),
        airline("Air Canada", "AC"),
        airline("Jet Blue", "B6")
    );
  }

  @Test public void testGetAllAirlinesCachedInMemory() {
    when(mockApi.getAirlines()).thenReturn(Single.just(Collections.singletonList(
        airlineResponse("Jet Blue", "B6")
    )));

    List<Airline> airlines = airlineRepository.getAllAirlines().toBlocking().value();
    assertThat(airlines).containsExactly(airline("Jet Blue", "B6"));

    airlines = airlineRepository.getAllAirlines().toBlocking().value();
    assertThat(airlines).containsExactly(airline("Jet Blue", "B6"));

    verify(mockApi, times(1)).getAirlines();
  }

  @Test public void testGetAllAirlinesSharesSameSingle() {
    when(mockApi.getAirlines()).thenReturn(Single.just(Collections.singletonList(
        airlineResponse("Jet Blue", "B6")
    )).delay(500, TimeUnit.MILLISECONDS));

    TestSubscriber<List<Airline>> first = TestSubscriber.create();
    TestSubscriber<List<Airline>> second = TestSubscriber.create();

    airlineRepository.getAllAirlines()
        .observeOn(Schedulers.trampoline())
        .subscribe(first);

    airlineRepository.getAllAirlines()
        .observeOn(Schedulers.trampoline())
        .subscribe(second);

    verify(mockApi, times(1)).getAirlines();
  }
}