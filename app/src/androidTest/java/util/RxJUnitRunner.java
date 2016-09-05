package util;

import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.runner.AndroidJUnitRunner;

import com.linkedin.android.testbutler.TestButler;

import rx.plugins.RxJavaPlugins;

/**
 * Custom JUnit runner with rx idling and disabled animation settings.
 */
public class RxJUnitRunner extends AndroidJUnitRunner {

  @Override public void onCreate(Bundle arguments) {
    super.onCreate(arguments);
    registerRxHook();
    TestButler.setup(InstrumentationRegistry.getTargetContext());
  }

  @Override public void finish(int resultCode, Bundle results) {
    TestButler.teardown(InstrumentationRegistry.getTargetContext());
    super.finish(resultCode, results);
  }

  /**
   * Registers an {@link RxIdlingResource} that waits for Observables to complete.
   */
  private void registerRxHook() {
    RxIdlingResource rxIdlingResource = new RxIdlingResource();
    RxJavaPlugins.getInstance().registerObservableExecutionHook(rxIdlingResource);
    Espresso.registerIdlingResources(rxIdlingResource);
  }
}
