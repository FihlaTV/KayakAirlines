package com.oguzbabaoglu.kayakairlines.features.airlines;

import javax.inject.Inject;

public class AirlineInfoPresenter {

  private AirlineInfoView view;

  @Inject public AirlineInfoPresenter() {
  }

  public void setView(AirlineInfoView view) {
    this.view = view;
  }

  public boolean onNavigationItemClick() {
    view.showSnackBarMessage("Not implemented.");
    return true;
  }
}
