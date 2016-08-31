package com.oguzbabaoglu.kayakairlines.features.airlinelist;

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
import com.oguzbabaoglu.kayakairlines.util.Lambda;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirlineListActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private static final Lambda[] TABS = new Lambda[]{AirlineListFragment::newInstance, AirlineListFragment::newInstance};
  private static final int[] TITLES = new int[]{ R.string.airline_tab_all, R.string.airline_tab_starred};

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.airline_list_viewpager) ViewPager airlineViewPager;
  @BindView(R.id.airline_list_tablayout) TabLayout airlineTabLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_airline_list);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);

    airlineViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
      @Override public Fragment getItem(int position) {
        return (Fragment) TABS[position].call();
      }

      @Override public int getCount() {
        return TABS.length;
      }

      @Override public CharSequence getPageTitle(int position) {
        return getString(TITLES[position]);
      }
    });

    airlineTabLayout.setupWithViewPager(airlineViewPager);
    
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
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
}
