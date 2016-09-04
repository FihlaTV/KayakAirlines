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

  @Inject public AirlineAdapter() {
  }

  static Airline fromAirlineResponse(AirlineResponse response) {
    return Airline.builder()
        .name(response.name())
        .code(response.code())
        .logoUrl(buildLogoUri(response.logoUrl()))
        .phone(response.phone())
        .websiteUrl(buildWebsiteUri(response.site()))
        .build();
  }

  private static Uri buildLogoUri(String logoPath) {
    return Uri.parse(BuildConfig.BASE_URL).buildUpon().path(logoPath).build();
  }

  private static Uri buildWebsiteUri(String site) {
    if (site == null) return null;
    if (!site.startsWith("http")) {
      site = "http://" + site;
    }
    return Uri.parse(site);
  }
}
