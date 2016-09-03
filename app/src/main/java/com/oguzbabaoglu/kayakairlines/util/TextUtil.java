package com.oguzbabaoglu.kayakairlines.util;

import android.text.Editable;
import android.text.TextWatcher;

import rx.functions.Action1;

public final class TextUtil {

  private TextUtil() {
  }

  public static TextWatcher doAfter(Action1<String> action) {
    return new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override public void afterTextChanged(Editable s) {
        action.call(s.toString());
      }
    };
  }
}
