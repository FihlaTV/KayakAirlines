package com.oguzbabaoglu.kayakairlines.network.response;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class AirlineModel {

  @SerializedName("__clazz") public abstract String clazz();
  @SerializedName("code") public abstract String code();
  @SerializedName("defaultName") public abstract String defaultName();
  @SerializedName("logoURL") public abstract String logoURL();
  @SerializedName("name") public abstract String name();
  @Nullable @SerializedName("phone") public abstract String phone();
  @Nullable @SerializedName("site") public abstract String site();
  @SerializedName("usName") public abstract String usName();

  public static TypeAdapter<AirlineModel> typeAdapter(Gson gson) {
    return new AutoValue_AirlineModel.GsonTypeAdapter(gson);
  }

  public static Builder builder() {
    return new AutoValue_AirlineModel.Builder();
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder clazz(String clazz);
    public abstract Builder code(String code);
    public abstract Builder defaultName(String defaultName);
    public abstract Builder logoURL(String logoURL);
    public abstract Builder name(String name);
    public abstract Builder phone(String phone);
    public abstract Builder site(String site);
    public abstract Builder usName(String usName);
    public abstract AirlineModel build();
  }
}
