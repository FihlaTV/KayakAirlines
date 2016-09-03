package com.oguzbabaoglu.kayakairlines.domain;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Airline implements Comparable<Airline>, Parcelable {

  public abstract String displayName();
  public abstract String code();
  public abstract String logoUrl();
  @Nullable public abstract String phone();
  @Nullable public abstract String website();
  public abstract boolean isStarred();

  public static Builder builder() {
    return new AutoValue_Airline.Builder();
  }

  @Override public int compareTo(@NonNull Airline airline) {
    return displayName().compareToIgnoreCase(airline.displayName());
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder displayName(String displayName);
    public abstract Builder code(String code);
    public abstract Builder logoUrl(String logoUrl);
    public abstract Builder phone(String phone);
    public abstract Builder website(String website);
    public abstract Builder isStarred(boolean starred);
    public abstract Airline build();
  }
}
