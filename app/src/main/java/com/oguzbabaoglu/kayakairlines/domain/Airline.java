package com.oguzbabaoglu.kayakairlines.domain;

import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Airline implements Comparable<Airline>, Parcelable {

  public abstract String name();
  public abstract String code();
  public abstract Uri logoUrl();
  @Nullable public abstract String phone();
  @Nullable public abstract Uri websiteUrl();
  public abstract boolean isStarred();

  public static Builder builder() {
    return new AutoValue_Airline.Builder();
  }

  /**
   * Comparison is done lexicographically on {@link #name()}
   */
  @Override public int compareTo(@NonNull Airline airline) {
    return name().compareToIgnoreCase(airline.name());
  }

  /**
   * @return Website string suitable for displaying in UI.
   */
  public String websiteHumanString() {
    Uri websiteUrl = websiteUrl();
    return websiteUrl == null ? "" : websiteUrl.getHost();
  }

  /**
   * @return true if matches either {@link #name()} or {@link #code()}. Ignores case.
   */
  public boolean matches(@NonNull CharSequence filter) {
    String match = filter.toString().toLowerCase();
    return name().toLowerCase().contains(match) || code().toLowerCase().contains(match);
  }

  /**
   * {@link #code()} is considered to be unique for each Airline.
   */
  @Override public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof Airline) {
      Airline that = (Airline) o;
      return this.code().equals(that.code());
    }
    return false;
  }

  /**
   * {@link #code()} is considered to be unique for each Airline.
   */
  @Override public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.code().hashCode();
    return h;
  }

  @AutoValue.Builder
  public abstract static class Builder {
    public abstract Builder name(String name);
    public abstract Builder code(String code);
    public abstract Builder logoUrl(Uri logoUrl);
    public abstract Builder phone(String phone);
    public abstract Builder websiteUrl(Uri websiteUrl);
    public abstract Builder isStarred(boolean starred);
    public abstract Airline build();
  }
}
