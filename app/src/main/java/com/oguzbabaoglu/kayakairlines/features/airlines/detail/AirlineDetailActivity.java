package com.oguzbabaoglu.kayakairlines.features.airlines.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzbabaoglu.kayakairlines.R;
import com.oguzbabaoglu.kayakairlines.domain.Airline;
import com.oguzbabaoglu.kayakairlines.util.Dagger;
import com.oguzbabaoglu.kayakairlines.util.RemoveWhiteTransformation;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirlineDetailActivity extends AppCompatActivity implements AirlineDetailView {

  private static final String KEY_AIRLINE = "KEY_AIRLINE";

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.airline_detail_logo) ImageView logoImageView;
  @BindView(R.id.airline_detail_name) TextView nameTextView;
  @BindView(R.id.airline_detail_star) ImageView starImageView;
  @BindView(R.id.airline_detail_phone) TextView phoneTextView;
  @BindView(R.id.airline_detail_website) TextView websiteTextView;

  @Inject AirlineDetailPresenter presenter;

  public static Intent newIntent(Context context, Airline airline) {
    return new Intent(context, AirlineDetailActivity.class)
        .putExtra(KEY_AIRLINE, airline);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_airline_detail);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    Dagger.INJECTOR.airlineComponent().inject(this);

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    toolbar.setNavigationOnClickListener(click -> onBackPressed());

    Airline airline = getIntent().getParcelableExtra(KEY_AIRLINE);
    init(airline);
    presenter.setView(this);
    presenter.init(airline);
  }

  private void init(Airline airline) {
    getSupportActionBar().setTitle(airline.name());

    int logoWidth = getResources().getDimensionPixelSize(R.dimen.airline_logo_width);
    int logoHeight = getResources().getDimensionPixelSize(R.dimen.airline_logo_height);
    Picasso.with(this)
        .load(airline.logoUrl())
        .placeholder(R.drawable.airline_logo_placeholder)
        .resize(logoWidth, logoHeight)
        .transform(new RemoveWhiteTransformation())
        .into(logoImageView);

    nameTextView.setText(airline.name());
    phoneTextView.setPaintFlags(phoneTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    phoneTextView.setText(airline.phone());
    websiteTextView.setPaintFlags(websiteTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    websiteTextView.setText(airline.websiteHumanString());

    starImageView.setOnClickListener(click -> presenter.onStarClick());
    phoneTextView.setOnClickListener(click -> presenter.onPhoneClick());
    websiteTextView.setOnClickListener(click -> presenter.onWebsiteClick());
  }

  @Override public void setStarred(boolean full) {
    starImageView.setImageResource(full ? R.drawable.ic_star : R.drawable.ic_star_border);
  }

  @Override public void openDialer(String phone) {
    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
    startActivity(intent);
  }

  @Override public void openBrowser(Uri website) {
    Intent intent = new Intent(Intent.ACTION_VIEW, website);
    intent.putExtra(Browser.EXTRA_APPLICATION_ID, getPackageName());
    startActivity(intent);
  }
}
