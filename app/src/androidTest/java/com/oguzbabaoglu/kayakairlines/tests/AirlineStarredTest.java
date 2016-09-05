package com.oguzbabaoglu.kayakairlines.tests;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.oguzbabaoglu.kayakairlines.features.airlines.AirlineInfoActivity;
import com.oguzbabaoglu.kayakairlines.robots.AirlineInfoRobot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AirlineStarredTest {

  @Rule public final ActivityTestRule<AirlineInfoActivity> rule = new ActivityTestRule<>(AirlineInfoActivity.class);

  @Before public void setUp() {
    rule.getActivity().getSharedPreferences("SharedPrefPersister", Context.MODE_PRIVATE).edit().clear().commit();
  }

  @Test public void starringUpdatesBothTabs() {
    AirlineInfoRobot infoRobot = new AirlineInfoRobot();

    infoRobot.clickAllTab()
        .assertAirlineStarred("AccesRail (9B)", false)
        .clickOnAirline("AccesRail (9B)")
        .toggleStar()
        .goBack()
        .assertAirlineStarred("AccesRail (9B)", true);

    infoRobot.clickStarredTab()
        .assertAirlineDisplayed("AccesRail (9B)", true)
        .assertAirlineStarred("AccesRail (9B)", true)
        .clickOnAirline("AccesRail (9B)")
        .toggleStar()
        .goBack()
        .assertAirlineDisplayed("AccesRail (9B)", false);

    infoRobot.clickAllTab()
        .assertAirlineStarred("AccesRail (9B)", false);
  }
}
