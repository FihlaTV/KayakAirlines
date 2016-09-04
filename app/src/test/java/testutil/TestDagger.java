package testutil;

import android.content.Context;

import com.oguzbabaoglu.kayakairlines.features.airlines.AirlineComponent;
import com.oguzbabaoglu.kayakairlines.features.airlines.DaggerAirlineComponent;
import com.oguzbabaoglu.kayakairlines.util.ContextModule;

import org.mockito.Mockito;

/**
 * Test version of the Dagger injection graph.
 * Uses mocks for Android dependant modules.
 */
public enum  TestDagger {
  INJECTOR;

  private final AirlineComponent airlineComponent;

  TestDagger() {
    this.airlineComponent = DaggerAirlineComponent.builder()
        .contextModule(new ContextModule(Mockito.mock(Context.class)))
        .build();
  }

  public AirlineComponent airlineComponent() {
    return airlineComponent;
  }
}
