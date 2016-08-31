package com.oguzbabaoglu.kayakairlines.di;

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
