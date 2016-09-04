package com.oguzbabaoglu.kayakairlines.features.airlines.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.oguzbabaoglu.kayakairlines.R;
import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.features.airlines.detail.AirlineDetailActivity;
import com.oguzbabaoglu.kayakairlines.features.airlines.starred.StarredAirlineHelper;
import com.oguzbabaoglu.kayakairlines.util.Dagger;
import com.oguzbabaoglu.kayakairlines.util.DividerItemDecoration;
import com.oguzbabaoglu.kayakairlines.util.TextUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirlineListFragment extends Fragment implements AirlineListView {

  private static final String KEY_TYPE = "KEY_TYPE";

  @BindView(R.id.airline_list_search) EditText airlineSearchText;
  @BindView(R.id.airline_list_search_clear) ImageView airlineSearchClear;
  @BindView(R.id.airline_list_recyclerview) RecyclerView airlineRecyclerView;

  @Inject AirlineListPresenter presenter;
  @Inject StarredAirlineHelper starredAirlineHelper;

  private AirlineListAdapter listAdapter;

  public static AirlineListFragment newAllInstance() {
    return newInstance(ALL);
  }

  public static AirlineListFragment newStarredInstance() {
    return newInstance(STARRED);
  }

  private static AirlineListFragment newInstance(int type) {
    Bundle args = new Bundle();
    args.putInt(KEY_TYPE, type);
    AirlineListFragment fragment = new AirlineListFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Dagger.INJECTOR.airlineComponent().inject(this);
    presenter.setView(this, getArguments().getInt(KEY_TYPE));
  }

  @Nullable
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_airline_list, container, false);
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    airlineRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    airlineRecyclerView.addItemDecoration(new DividerItemDecoration(getContext()));

    airlineSearchClear.setOnClickListener(click -> presenter.onSearchClearClick());
    airlineSearchText.addTextChangedListener(TextUtil.doAfter(text -> presenter.onFilterTextChanged(text)));

    presenter.init();
  }

  @Override public void updateContent(List<Airline> airlines) {
    listAdapter = new AirlineListAdapter(airlines, starredAirlineHelper,
        airline -> presenter.onAirlineClick(airline));
    airlineRecyclerView.setAdapter(listAdapter);
  }

  @Override public void filterAirlines(String constraint) {
    listAdapter.getFilter().filter(constraint);
  }

  @Override public void showSearchClearButton(boolean show) {
    airlineSearchClear.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
  }

  @Override public void clearFilter() {
    airlineSearchText.setText("");
  }

  @Override public void displayAirlineDetailView(Airline airline) {
    startActivity(AirlineDetailActivity.newIntent(getContext(), airline));
  }

  @Override public void notifyItemChanged(String changedAirlineCode) {
    listAdapter.notifyAirlineItemChanged(changedAirlineCode);
  }

  @Override public void addAirline(Airline airline) {
    listAdapter.addAirline(airline, airlineSearchText.getText().toString());
  }

  @Override public void removeAirline(Airline airline) {
    listAdapter.removeAirline(airline);
  }

  @Override public void onStart() {
    super.onStart();
    presenter.onStart();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    presenter.onDestroy();
  }
}
