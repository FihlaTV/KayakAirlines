package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import com.oguzbabaoglu.kayakairlines.domain.Airline;

public interface AirlineListView {

  void filterAirlines(String constraint);

  void showSearchClearButton(boolean show);

  void clearFilter();

  void displayAirlineDetailView(Airline airline);
}
