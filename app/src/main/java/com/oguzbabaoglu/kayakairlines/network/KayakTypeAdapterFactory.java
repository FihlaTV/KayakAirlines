package com.oguzbabaoglu.kayakairlines.network;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
abstract class KayakTypeAdapterFactory implements TypeAdapterFactory {

  public static KayakTypeAdapterFactory create() {
    return new AutoValueGson_KayakTypeAdapterFactory();
  }
}