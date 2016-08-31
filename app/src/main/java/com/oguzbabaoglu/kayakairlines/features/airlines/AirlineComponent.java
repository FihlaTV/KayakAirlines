package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.google.gson.Gson;
import com.oguzbabaoglu.kayakairlines.features.airlines.list.AirlineListFragment;
import com.oguzbabaoglu.kayakairlines.network.KayakApi;
import com.oguzbabaoglu.kayakairlines.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // scoped as singleton for convenience (this is a single-feature app)
@Component(modules = {
    NetworkModule.class
})
public interface AirlineComponent {

  Gson gson();

  KayakApi kayakApi();

  void inject(AirlineListFragment fragment);
}
