package com.oguzbabaoglu.kayakairlines.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Set;

class SharedPrefPersister implements Persister {

  /**
   * IMPORTANT: Changing this value requires a data migration.
   */
  private static final String PREF_NAME = "SharedPrefPersister";

  private final SharedPreferences sharedPreferences;

  public SharedPrefPersister(Context context) {
    sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
  }

  @Override public Set<String> getStringSet(Key key) {
    return sharedPreferences.getStringSet(key.name(), Collections.emptySet());
  }

  @Override public void putStringSet(Key key, Set<String> value) {
    sharedPreferences.edit().putStringSet(key.name(), value).apply();
  }

  @Override public void delete(Key key) {
    sharedPreferences.edit().remove(key.name()).apply();
  }
}
