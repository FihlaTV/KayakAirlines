package util;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

import rx.Observable;
import rx.Subscriber;
import rx.plugins.RxJavaObservableExecutionHook;

/**
 * A custom {@link IdlingResource} that waits for Observables to complete.
 *
 * NOTE: This will wait for all Observables to finish, can't use endless Observables (event streams).
 */
public final class RxIdlingResource extends RxJavaObservableExecutionHook implements IdlingResource {

  private final AtomicInteger subscriptions = new AtomicInteger(0);

  private ResourceCallback resourceCallback;

  @Override public String getName() {
    return "RxIdlingResource";
  }

  @Override public boolean isIdleNow() {
    return subscriptions.get() == 0;
  }

  @Override public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
    this.resourceCallback = resourceCallback;
  }

  @Override public <T> Observable.OnSubscribe<T> onSubscribeStart(Observable<? extends T> observableInstance,
                                                                  final Observable.OnSubscribe<T> onSubscribe) {
    subscriptions.incrementAndGet();
    return subscriber -> onSubscribe.call(new Subscriber<T>() {
      @Override public void onCompleted() {
        subscriber.onCompleted();
        onObservableFinish();
      }

      @Override public void onError(Throwable e) {
        subscriber.onError(e);
        onObservableFinish();
      }

      @Override public void onNext(T t) {
        subscriber.onNext(t);
      }
    });
  }

  private void onObservableFinish() {
    if (subscriptions.decrementAndGet() == 0) {
      resourceCallback.onTransitionToIdle();
    }
  }
}
