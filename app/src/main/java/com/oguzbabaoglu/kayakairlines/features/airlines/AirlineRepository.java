package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.KayakApi;
import com.oguzbabaoglu.kayakairlines.util.ListUtil;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;
import rx.schedulers.Schedulers;

/**
 * Fetches airline list from api and keeps it in memory.
 * In reality airline information does not change very frequently,
 * therefore a longer term caching strategy could be used.
 */
@Singleton // scoped as singleton for convenience (this is a single-feature app)
public class AirlineRepository {

  private final KayakApi kayakApi;

  // These will be set and read from separate threads
  private volatile List<Airline> allAirlines;
  private volatile Single<List<Airline>> activeCall;

  @Inject public AirlineRepository(KayakApi kayakApi) {
    this.kayakApi = kayakApi;
  }

  public Single<List<Airline>> getAllAirlines() {
    if (allAirlines != null) {
      return Single.just(allAirlines);
    }
    if (activeCall != null) {
      return activeCall;
    }
    activeCall = kayakApi.getAirlines()
        .map(models -> ListUtil.transform(models, AirlineAdapter::fromAirlineResponse))
        .map(ListUtil::sort)
        .map(Collections::unmodifiableList)
        .doOnSuccess(airlines -> {
          allAirlines = airlines;
          activeCall = null;
        })
        .subscribeOn(Schedulers.io())
        .toObservable().share().toSingle(); // there is no share() for Single
    return activeCall;
  }
}
