package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import com.oguzbabaoglu.kayakairlines.domain.Airline;

import java.util.List;

public interface AirlineListView {

  int ALL = 0;
  int STARRED = 1;

  void updateContent(List<Airline> airlines);
  void filterAirlines(String constraint);

  void showSearchClearButton(boolean show);

  void clearFilter();

  void displayAirlineDetailView(Airline airline);
}
