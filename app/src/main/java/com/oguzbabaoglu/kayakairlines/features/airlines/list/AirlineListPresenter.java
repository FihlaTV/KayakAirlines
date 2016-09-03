package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import com.oguzbabaoglu.kayakairlines.domain.Airline;

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
    view.showSearchClearButton(!filter.isEmpty());
  }

  public void onSearchClearClick() {
    view.clearFilter();
  }

  public void onAirlineClick(Airline airline) {
    view.displayAirlineDetailView(airline);
  }
}
