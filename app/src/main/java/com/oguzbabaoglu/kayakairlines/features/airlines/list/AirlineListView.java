package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import com.oguzbabaoglu.kayakairlines.domain.Airline;

import java.util.List;

public interface AirlineListView {
  void updateContent(List<Airline> airlines);
}
