package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.oguzbabaoglu.kayakairlines.domain.Airline;

import java.util.List;

public interface AirlineInfoView {
  void displayListTabs(List<Airline> all, List<Airline> starred);
}
