package com.oguzbabaoglu.kayakairlines.features.airlinelist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oguzbabaoglu.kayakairlines.R;

import butterknife.ButterKnife;

public class AirlineListFragment extends Fragment {

  public static AirlineListFragment newInstance() {
    Bundle args = new Bundle();
    AirlineListFragment fragment = new AirlineListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_airline_list, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
  }
}
