package com.oguzbabaoglu.kayakairlines.robots;

import android.support.test.espresso.Espresso;

import com.oguzbabaoglu.kayakairlines.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class AirlineDetailRobot {

  public AirlineDetailRobot toggleStar() {
    onView(withId(R.id.airline_detail_star))
        .perform(click());
    return this;
  }

  public AirlineListRobot goBack() {
    Espresso.pressBack();
    return new AirlineListRobot();
  }
}
