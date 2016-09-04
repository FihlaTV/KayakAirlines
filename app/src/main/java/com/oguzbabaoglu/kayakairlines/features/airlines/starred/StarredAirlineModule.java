package com.oguzbabaoglu.kayakairlines.features.airlines.starred;

import com.oguzbabaoglu.kayakairlines.persistence.Persister;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StarredAirlineModule {

  @Singleton // scoped as singleton for convenience (this is a single-feature app)
  @Provides StarredAirlineHelper starredAirlineHelper(Persister persister) {
    return new StarredAirlineHelper(persister);
  }
}
