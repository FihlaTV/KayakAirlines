package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.oguzbabaoglu.kayakairlines.BuildConfig;
import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.model.AirlineResponse;

/**
 * Converts airline api responses to their domain counterparts.
 */
final class AirlineAdapter {

  private AirlineAdapter() {
  }

  static Airline fromAirlineResponse(AirlineResponse response) {
    return Airline.builder()
        .displayName(response.name())
        .code(response.code())
        .logoUrl(BuildConfig.BASE_URL +  response.logoUrl())
        .phone(response.phone())
        .website(response.site())
        .isStarred(false) //TODO
        .build();
  }
}
