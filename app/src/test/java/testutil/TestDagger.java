package testutil;

import android.content.Context;

import com.oguzbabaoglu.kayakairlines.features.airlines.AirlineComponent;
import com.oguzbabaoglu.kayakairlines.features.airlines.DaggerAirlineComponent;
import com.oguzbabaoglu.kayakairlines.network.NetworkModule;
import com.oguzbabaoglu.kayakairlines.util.ContextModule;

import org.mockito.Mockito;

import okhttp3.mockwebserver.MockWebServer;

/**
 * Test version of the Dagger injection graph.
 * Uses mocks for Android dependant modules.
 */
public enum TestDagger {
  INJECTOR;

  private final MockWebServer mockWebServer;
  private final AirlineComponent airlineComponent;

  TestDagger() {
    this.mockWebServer = new MockWebServer();
    this.airlineComponent = DaggerAirlineComponent.builder()
        .contextModule(new ContextModule(Mockito.mock(Context.class)))
        .networkModule(new NetworkModule(mockWebServer.url("/").toString()))
        .build();
  }

  public AirlineComponent airlineComponent() {
    return airlineComponent;
  }

  public MockWebServer mockWebServer() {
    return mockWebServer;
  }
}
