package com.oguzbabaoglu.kayakairlines.features.airlines.list;

public interface AirlineListView {
  void filterAirlines(String constraint);
  void showSearchClearButton(boolean show);
  void clearFilter();
}
