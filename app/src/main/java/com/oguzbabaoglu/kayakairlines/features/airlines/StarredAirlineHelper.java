package com.oguzbabaoglu.kayakairlines.features.airlines;

import com.oguzbabaoglu.kayakairlines.persistence.Persister;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

/**
 * Manages storing and retrieving starred airlines.
 */
public class StarredAirlineHelper {

  private final Persister persister;
  private final Set<String> starredAirlines;

  @Inject public StarredAirlineHelper(Persister persister) {
    this.persister = persister;
    starredAirlines = new HashSet<>(persister.loadStringSet(Persister.Key.STARRED_AIRLINES));
  }

  public boolean isStarred(String code) {
    return starredAirlines.contains(code);
  }

  public void setStarred(String code) {
    if (starredAirlines.add(code)) {
      persister.saveStringSet(Persister.Key.STARRED_AIRLINES, starredAirlines);
    }
  }

  public void removeStarred(String code) {
    if (starredAirlines.remove(code)) {
      persister.saveStringSet(Persister.Key.STARRED_AIRLINES, starredAirlines);
    }
  }
}
