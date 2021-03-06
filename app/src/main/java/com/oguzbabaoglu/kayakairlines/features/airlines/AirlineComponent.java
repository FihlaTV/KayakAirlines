package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.google.gson.Gson;
import com.oguzbabaoglu.kayakairlines.features.airlines.detail.AirlineDetailActivity;
import com.oguzbabaoglu.kayakairlines.features.airlines.list.AirlineListFragment;
import com.oguzbabaoglu.kayakairlines.features.airlines.starred.StarredAirlineModule;
import com.oguzbabaoglu.kayakairlines.network.KayakApi;
import com.oguzbabaoglu.kayakairlines.network.NetworkModule;
import com.oguzbabaoglu.kayakairlines.persistence.PersistenceModule;
import com.oguzbabaoglu.kayakairlines.util.ContextModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // scoped as singleton for convenience (this is a single-feature app)
@Component(modules = {
    NetworkModule.class,
    PersistenceModule.class,
    ContextModule.class,
    StarredAirlineModule.class
})
public interface AirlineComponent {

  Gson gson();

  KayakApi kayakApi();

  void inject(AirlineInfoActivity activity);
  void inject(AirlineListFragment fragment);
  void inject(AirlineDetailActivity activity);
}
