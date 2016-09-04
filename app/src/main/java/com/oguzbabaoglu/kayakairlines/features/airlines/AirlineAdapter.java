package com.oguzbabaoglu.kayakairlines.features.airlines;

import android.net.Uri;

import com.oguzbabaoglu.kayakairlines.BuildConfig;
import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.model.AirlineResponse;

import javax.inject.Inject;

/**
 * Converts airline api responses to their domain counterparts.
 */
final class AirlineAdapter {

  private final StarredAirlineHelper starredAirlineHelper;

  @Inject public AirlineAdapter(StarredAirlineHelper starredAirlineHelper) {
    this.starredAirlineHelper = starredAirlineHelper;
  }

  Airline fromAirlineResponse(AirlineResponse response) {
    String domain = response.site();
    Uri websiteUri = domain == null ? null
        : new Uri.Builder().scheme("http").authority(domain).build();

    String logoPath = response.logoUrl();
    Uri logoUri = Uri.parse(BuildConfig.BASE_URL).buildUpon().path(logoPath).build();

    return Airline.builder()
        .name(response.name())
        .code(response.code())
        .logoUrl(logoUri)
        .phone(response.phone())
        .websiteUrl(websiteUri)
        .isStarred(starredAirlineHelper.isStarred(response.code()))
        .build();
  }
}
