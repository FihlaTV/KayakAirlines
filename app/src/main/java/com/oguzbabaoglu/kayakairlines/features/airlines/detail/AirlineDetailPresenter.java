package com.oguzbabaoglu.kayakairlines.features.airlines.detail;

import com.oguzbabaoglu.kayakairlines.domain.Airline;

import javax.inject.Inject;

public class AirlineDetailPresenter {
  private AirlineDetailView view;
  private Airline airline;

  @Inject public AirlineDetailPresenter() {
  }

  public void setView(AirlineDetailView view) {
    this.view = view;
  }

  public void init(Airline airline) {
    this.airline = airline;
  }

  public void onStarClick() {

  }

  public void onPhoneClick() {

  }

  public void onWebsiteClick() {

  }
}
