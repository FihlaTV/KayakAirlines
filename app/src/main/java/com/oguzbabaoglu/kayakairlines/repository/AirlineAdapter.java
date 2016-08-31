package com.oguzbabaoglu.kayakairlines.repository;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.response.AirlineModel;

/**
 * Converts airline api responses to their domain counterparts.
 */
final class AirlineAdapter {

  private AirlineAdapter() {
  }

  static Airline fromAirlineModel(AirlineModel model) {
    return Airline.builder()
        .displayName(model.name())
        .code(model.code())
        .phone(model.phone())
        .website(model.site())
        .isStarred(false) //TODO
        .build();
  }
}
