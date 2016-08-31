package com.oguzbabaoglu.kayakairlines.util;

import java.util.concurrent.Callable;

/**
 * Retrolambda converts lambdas that return values into {@link Callable} objects.
 * This is just a Callable interface that does not throw, for convenient usage.
 */
public interface Lambda<V> extends Callable<V> {

  @Override V call();
}
