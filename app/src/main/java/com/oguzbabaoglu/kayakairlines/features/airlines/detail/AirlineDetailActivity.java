package com.oguzbabaoglu.kayakairlines.features.airlines.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.oguzbabaoglu.kayakairlines.R;

public class AirlineDetailActivity extends AppCompatActivity {

  public static Intent newIntent(Context context) {
    return new Intent(context, AirlineDetailActivity.class);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_airline_detail);
  }
}
