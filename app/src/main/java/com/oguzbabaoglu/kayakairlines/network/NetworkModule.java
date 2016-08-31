package com.oguzbabaoglu.kayakairlines.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oguzbabaoglu.kayakairlines.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

  @Singleton
  @Provides public Gson gson() {
    return new GsonBuilder()
        .registerTypeAdapterFactory(KayakTypeAdapterFactory.create())
        .create();
  }

  @Singleton
  @Provides public OkHttpClient okHttpClient() {
    OkHttpClient.Builder builder = new OkHttpClient.Builder();

    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
      logging.setLevel(HttpLoggingInterceptor.Level.BODY);
      builder.addInterceptor(logging);
    }

    return builder.build();
  }

  @Singleton
  @Provides public Retrofit retrofit(OkHttpClient httpClient, Gson gson) {
    return new Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }

  @Singleton
  @Provides public KayakApi kayakApi(Retrofit retrofit) {
    return retrofit.create(KayakApi.class);
  }
}
