package com.oguzbabaoglu.kayakairlines.features.airlines.detail;

import android.net.Uri;

public interface AirlineDetailView {

  void openDialer(String phone);

  void openBrowser(Uri website);
}
