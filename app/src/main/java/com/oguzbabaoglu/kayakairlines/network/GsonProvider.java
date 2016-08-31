package com.oguzbabaoglu.kayakairlines.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Initializes and provides a Gson instance for the network package.
 */
enum GsonProvider {
  INSTANCE;

  private Gson gson;

  private void init() {
    gson = new GsonBuilder()
        .registerTypeAdapterFactory(KayakTypeAdapterFactory.create())
        .create();
  }

  public static Gson get() {
    if (INSTANCE.gson == null) {
      INSTANCE.init();
    }
    return INSTANCE.gson;
  }
}
