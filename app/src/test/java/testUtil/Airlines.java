package testutil;

import android.net.Uri;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.model.AirlineResponse;

import static org.mockito.Mockito.mock;

public final class Airlines {

  private Airlines() {
  }

  public static Airline airline(String name, String code) {
    return Airline.builder()
        .name(name)
        .code(code)
        .logoUrl(mock(Uri.class))
        .isStarred(false)
        .build();
  }

  public static AirlineResponse airlineResponse(String code) {
    return airlineResponseBuilder()
        .code(code)
        .build();
  }

  public static AirlineResponse.Builder airlineResponseBuilder() {
    return AirlineResponse.builder()
        .clazz("clazz")
        .defaultName("defaultName")
        .name("name")
        .code("code")
        .usName("usName")
        .logoUrl("logoUrl")
        .site("site");
  }
}
