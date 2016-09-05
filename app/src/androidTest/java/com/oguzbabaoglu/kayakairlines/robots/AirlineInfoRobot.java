package com.oguzbabaoglu.kayakairlines.robots;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class AirlineInfoRobot {

  public AirlineListRobot clickAllTab() {
    onView(withText("ALL"))
        .perform(click());
    return new AirlineListRobot();
  }

  public AirlineListRobot clickStarredTab() {
    onView(withText("STARRED"))
        .perform(click());
    return new AirlineListRobot();
  }

}
