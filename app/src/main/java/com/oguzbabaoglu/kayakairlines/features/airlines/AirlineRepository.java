package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.network.KayakApi;
import com.oguzbabaoglu.kayakairlines.util.ListUtil;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;

/**
 * Fetches airline list from api and keeps it in memory.
 * In reality airline information does not change very frequently,
 * therefore a longer term caching strategy could be used.
 */
@Singleton // scoped as singleton for convenience (this is a single-feature app)
public class AirlineRepository {

  private final KayakApi kayakApi;

  private volatile List<Airline> allAirlines; // Can be set and read from separate threads

  @Inject public AirlineRepository(KayakApi kayakApi) {
    this.kayakApi = kayakApi;
  }

  public Single<List<Airline>> getAllAirlines() {
    if (allAirlines != null) {
      return Single.just(allAirlines);
    }
    return kayakApi.getAirlines()
        .map(models -> ListUtil.transform(models, AirlineAdapter::fromAirlineResponse))
        .map(ListUtil::sort)
        .doOnSuccess(airlines -> allAirlines = airlines);
  }
}
