package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.features.airlines.AirlineRepository;

import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AirlineListPresenter {

  private final AirlineRepository repository;
  private AirlineListView view;

  @Inject public AirlineListPresenter(AirlineRepository repository) {
    this.repository = repository;
  }

  public void setView(AirlineListView view) {
    this.view = view;
  }

  public void init() {
    repository
        .getAllAirlines()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleSubscriber<List<Airline>>() {
          @Override public void onSuccess(List<Airline> value) {
            view.updateContent(value);
          }

          @Override public void onError(Throwable error) {
          }
        });

  }
}
