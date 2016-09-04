package com.oguzbabaoglu.kayakairlines.features.airlines.detail;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.features.airlines.starred.StarredAirlineHelper;

import javax.inject.Inject;

public class AirlineDetailPresenter {

  private final StarredAirlineHelper starredAirlineHelper;

  private AirlineDetailView view;
  private Airline airline;

  @Inject public AirlineDetailPresenter(StarredAirlineHelper starredAirlineHelper) {
    this.starredAirlineHelper = starredAirlineHelper;
  }

  public void setView(AirlineDetailView view) {
    this.view = view;
  }

  public void init(Airline airline) {
    this.airline = airline;
    view.setStarred(starredAirlineHelper.isStarred(airline.code()));
  }

  public void onStarClick() {
    boolean starred = starredAirlineHelper.isStarred(airline.code());
    starredAirlineHelper.setStarred(airline.code(), !starred);
    view.setStarred(!starred);
  }

  public void onPhoneClick() {
    view.openDialer(airline.phone());
  }

  public void onWebsiteClick() {
    view.openBrowser(airline.websiteUrl());
  }
}
