package com.oguzbabaoglu.kayakairlines.tests;

import android.support.test.rule.ActivityTestRule;

import com.oguzbabaoglu.kayakairlines.features.airlines.AirlineInfoActivity;
import com.oguzbabaoglu.kayakairlines.robots.AirlineInfoRobot;

import org.junit.Rule;
import org.junit.Test;

public class AirlineFilterTest {

  @Rule public final ActivityTestRule<AirlineInfoActivity> rule = new ActivityTestRule<>(AirlineInfoActivity.class);

  @Test public void searchTextFiltersAirlineList() {
    AirlineInfoRobot infoRobot = new AirlineInfoRobot();

    infoRobot.clickAllTab()
        .typeSearchText("branson")
        .assertAirlineDisplayed("Branson Air Express (1X)", true)
        .clearSearchText()
        .assertAirlineDisplayed("Branson Air Express (1X)", false);
  }
}
