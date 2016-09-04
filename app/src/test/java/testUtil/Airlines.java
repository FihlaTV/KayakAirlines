package testutil;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.model.AirlineResponse;

public final class Airlines {

  private Airlines() {
  }

  public static Airline airline(String name) {
    return Airline.builder()
        .name(name)
        .code("test")
        .isStarred(false)
        .build();
  }

  public static AirlineResponse airlineResponse(String name) {
    return AirlineResponse.builder()
        .clazz("test")
        .defaultName(name)
        .name(name)
        .code("test")
        .usName(name)
        .logoUrl("test")
        .build();
  }
}
