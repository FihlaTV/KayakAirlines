package com.oguzbabaoglu.kayakairlines.persistence;

import java.util.Set;

/**
 * Implementations should persist key value pairs across app launches.
 */
public interface Persister {

  /**
   * Changing key names might cause data-loss in implementations.
   */
  enum Key {
    STARRED_AIRLINES
  }

  /**
   * Order is not respected.
   */
  Set<String> loadStringSet(Key key);

  /**
   * Order is not respected.
   */
  void saveStringSet(Key key, Set<String> value);
}
