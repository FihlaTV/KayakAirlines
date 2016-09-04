package com.oguzbabaoglu.kayakairlines.features.airlines.starred;

import com.oguzbabaoglu.kayakairlines.persistence.Persister;

import java.util.HashSet;
import java.util.Set;

/**
 * Manages storing and retrieving starred airlines.
 */
public class StarredAirlineHelper {

  private final Persister persister;
  private final Set<String> starredAirlines;

  public StarredAirlineHelper(Persister persister) {
    this.persister = persister;
    starredAirlines = new HashSet<>(persister.loadStringSet(Persister.Key.STARRED_AIRLINES));
  }

  public boolean isStarred(String code) {
    return starredAirlines.contains(code);
  }

  public void setStarred(String code, boolean starred) {
    boolean changed = starred ? starredAirlines.add(code) : starredAirlines.remove(code);
    if (changed) {
      persister.saveStringSet(Persister.Key.STARRED_AIRLINES, starredAirlines);
    }
  }
}
