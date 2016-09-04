package com.oguzbabaoglu.kayakairlines.persistence;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PersistenceModule {

  @Singleton // SharedPreferences object itself is a Singleton
  @Provides public Persister persister(Context context) {
    return new SharedPrefPersister(context);
  }
}
