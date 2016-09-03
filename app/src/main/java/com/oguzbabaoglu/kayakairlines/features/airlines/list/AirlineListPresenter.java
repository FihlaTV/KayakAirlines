package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import javax.inject.Inject;

public class AirlineListPresenter {

  private AirlineListView view;

  @Inject public AirlineListPresenter() {
  }

  public void setView(AirlineListView view) {
    this.view = view;
  }

  public void onFilterTextChanged(String filter) {
    view.filterAirlines(filter);
  }
}
