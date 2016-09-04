package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.features.airlines.AirlineRepository;
import com.oguzbabaoglu.kayakairlines.features.airlines.starred.StarredAirlineHelper;
import com.oguzbabaoglu.kayakairlines.util.ListUtil;

import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;

public class AirlineListPresenter {

  private final AirlineRepository repository;
  private final StarredAirlineHelper starredAirlineHelper;

  private AirlineListView view;
  private int type;

  @Inject public AirlineListPresenter(AirlineRepository repository, StarredAirlineHelper starredAirlineHelper) {
    this.repository = repository;
    this.starredAirlineHelper = starredAirlineHelper;
  }

  public void setView(AirlineListView view, int type) {
    this.view = view;
    this.type = type;
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

  public void init() {
    repository.getAllAirlines()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleSubscriber<List<Airline>>() {
          @Override public void onSuccess(List<Airline> airlines) {
            if (type == AirlineListView.STARRED) {
              airlines = ListUtil.filter(airlines,
                  airline -> starredAirlineHelper.isStarred(airline.code()));
            }
            view.updateContent(airlines);
          }

          @Override public void onError(Throwable error) {

          }
        });
  }
}
