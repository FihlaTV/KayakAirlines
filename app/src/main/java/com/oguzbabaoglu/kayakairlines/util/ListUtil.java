package com.oguzbabaoglu.kayakairlines.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.functions.Func1;

public final class ListUtil {

  private ListUtil() {
  }

  /**
   * Applies a transformation function on each element of the input list.
   */
  public static <T, R> List<R> transform(List<T> input, Func1<T, R> func) {
    if (input == null || input.isEmpty()) {
      return Collections.emptyList();
    }
    List<R> result = new ArrayList<>(input.size());
    for (T item : input) {
      result.add(func.call(item));
    }
    return result;
  }

  /**
   * Calls {@link Collections#sort(List)} and also returns the list to allow chaining.
   */
  public static <T extends Comparable<? super T>> List<T> sort(List<T> input) {
    Collections.sort(input);
    return input;
  }

  /**
   * Applies a filter function on each element of the input list.
   */
  public static <T> List<T> filter(List<T> input, Func1<T, Boolean> func) {
    if (input == null || input.isEmpty()) {
      return Collections.emptyList();
    }
    List<T> result = new ArrayList<>(input.size() / 2);
    for (T item : input) {
      if (func.call(item)) {
        result.add(item);
      }
    }
    return result;
  }

  /**
   * Transforms any List into an ArrayList.
   */
  public static <T> ArrayList<T> asArrayList(List<T> input) {
    if (input == null) {
      return null;
    }
    if (input instanceof ArrayList) {
      return (ArrayList<T>) input;
    }
    return new ArrayList<>(input);
  }
}
