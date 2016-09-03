package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.util.ListUtil;

import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AirlineInfoPresenter {

  private final AirlineRepository repository;
  private AirlineInfoView view;

  @Inject public AirlineInfoPresenter(AirlineRepository repository) {
    this.repository = repository;
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
            List<Airline> starred = ListUtil.filter(all, Airline::isStarred);
            view.displayListTabs(all, starred);
          }

          @Override public void onError(Throwable error) {
          }
        });

  }
}
