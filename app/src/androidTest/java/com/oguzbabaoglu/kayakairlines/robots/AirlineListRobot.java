package com.oguzbabaoglu.kayakairlines.robots;

import com.oguzbabaoglu.kayakairlines.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

public class AirlineListRobot {

  public AirlineDetailRobot clickOnAirline(String airlineName) {
    onView(allOf(withText(airlineName), isDisplayed()))
        .perform(click());
    return new AirlineDetailRobot();
  }

  public AirlineListRobot assertAirlineDisplayed(String airlineName, boolean displayed) {
    onView(allOf(withText(airlineName), isDisplayed()))
        .check(displayed ? matches(isDisplayed()) : doesNotExist());
    return this;
  }

  public AirlineListRobot assertAirlineStarred(String airlineName, boolean starred) {
    onView(allOf(withText(airlineName), isDisplayed()))
        .check(matches(hasSibling(allOf(withId(R.id.airline_item_star),
            starred ? isDisplayed() : not(isDisplayed())))));
    return this;
  }

  public AirlineListRobot typeSearchText(String text) {
    onView(allOf(withId(R.id.airline_list_search), isDisplayed()))
        .perform(typeText(text));
    return this;
  }

  public AirlineListRobot clearSearchText() {
    onView(allOf(withId(R.id.airline_list_search_clear), isDisplayed()))
        .perform(click());
    return this;
  }
}
