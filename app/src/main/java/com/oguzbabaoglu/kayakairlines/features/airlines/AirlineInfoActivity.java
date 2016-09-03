package com.oguzbabaoglu.kayakairlines.features.airlines;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.oguzbabaoglu.kayakairlines.R;
import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.features.airlines.list.AirlineListFragment;
import com.oguzbabaoglu.kayakairlines.util.Dagger;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirlineInfoActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, AirlineInfoView {

  private static final int INDEX_ALL = 0;
  private static final int TAB_COUNT = 2;

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.airline_list_viewpager) ViewPager airlineViewPager;
  @BindView(R.id.airline_list_tablayout) TabLayout airlineTabLayout;

  @Inject AirlineInfoPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_airline_list);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    Dagger.INJECTOR.airlineComponent().inject(this);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    presenter.setView(this);
    presenter.init();
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override public void displayListTabs(List<Airline> all, List<Airline> starred) {
    airlineViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
      @Override public Fragment getItem(int position) {
        return AirlineListFragment.newInstance(position == INDEX_ALL ? all : starred);
      }

      @Override public int getCount() {
        return TAB_COUNT;
      }

      @Override public CharSequence getPageTitle(int position) {
        return getString(position == INDEX_ALL ? R.string.airline_tab_all : R.string.airline_tab_starred);
      }
    });

    airlineTabLayout.setupWithViewPager(airlineViewPager);
  }
}
