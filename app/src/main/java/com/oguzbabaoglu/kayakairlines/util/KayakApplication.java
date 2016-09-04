package com.oguzbabaoglu.kayakairlines.util;

import android.app.Application;

public class KayakApplication extends Application {

  static KayakApplication instance; // exposed for Dagger ContextModule

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
  }
}
