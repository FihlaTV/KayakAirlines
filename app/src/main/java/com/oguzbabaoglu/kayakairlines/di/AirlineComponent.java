package com.oguzbabaoglu.kayakairlines.di;

import com.google.gson.Gson;
import com.oguzbabaoglu.kayakairlines.network.KayakApi;
import com.oguzbabaoglu.kayakairlines.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
    NetworkModule.class
})
public interface AirlineComponent {

  Gson gson();

  KayakApi kayakApi();
}
