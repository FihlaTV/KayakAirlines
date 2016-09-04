package com.oguzbabaoglu.kayakairlines.features.airlines.starred;

import com.oguzbabaoglu.kayakairlines.persistence.Persister;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Manages storing and retrieving starred airlines.
 */
public class StarredAirlineHelper {

  private final Persister persister;
  private final Set<String> starredAirlines;
  private final List<Subscriber> subscribers;

  public interface Subscriber {
    void stateChanged(String code, boolean starred);
  }

  public StarredAirlineHelper(Persister persister) {
    this.persister = persister;
    this.starredAirlines = new HashSet<>(persister.loadStringSet(Persister.Key.STARRED_AIRLINES));
    this.subscribers = new ArrayList<>();
  }

  public boolean isStarred(String code) {
    return starredAirlines.contains(code);
  }

  public void setStarred(String code, boolean starred) {
    boolean changed = starred ? starredAirlines.add(code) : starredAirlines.remove(code);
    if (changed) {
      persister.saveStringSet(Persister.Key.STARRED_AIRLINES, starredAirlines);
      for (Subscriber subscriber : subscribers) {
        subscriber.stateChanged(code, starred);
      }
    }
  }

  public void subscribe(Subscriber subscriber) {
    subscribers.add(subscriber);
  }

  public void unSubscribe(Subscriber subscriber) {
    subscribers.remove(subscriber);
  }
}
