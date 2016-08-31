package com.oguzbabaoglu.kayakairlines.util;

import com.oguzbabaoglu.kayakairlines.features.airlines.AirlineComponent;
import com.oguzbabaoglu.kayakairlines.features.airlines.DaggerAirlineComponent;

public enum Dagger {
  INJECTOR;

  private AirlineComponent airlineComponent;

  private void init() {
    airlineComponent = DaggerAirlineComponent.create();
  }

  public AirlineComponent airlineComponent() {
    if (airlineComponent == null) {
      init();
    }
    return airlineComponent;
  }
}
