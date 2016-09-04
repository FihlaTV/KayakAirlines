package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.features.airlines.starred.StarredAirlineHelper;
import com.oguzbabaoglu.kayakairlines.util.ListUtil;

import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AirlineInfoPresenter {

  private final AirlineRepository repository;
  private final StarredAirlineHelper starredAirlineHelper;
  private AirlineInfoView view;

  @Inject public AirlineInfoPresenter(AirlineRepository repository, StarredAirlineHelper starredAirlineHelper) {
    this.repository = repository;
    this.starredAirlineHelper = starredAirlineHelper;
  }

  public void setView(AirlineInfoView view) {
    this.view = view;
  }

  public void init() {
    repository
        .getAllAirlines()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleSubscriber<List<Airline>>() {
          @Override public void onSuccess(List<Airline> all) {
            List<Airline> starred = ListUtil.filter(all,
                airline -> starredAirlineHelper.isStarred(airline.code()));
            view.displayListTabs(all, starred);
          }

          @Override public void onError(Throwable error) {
          }
        });

  }
}
