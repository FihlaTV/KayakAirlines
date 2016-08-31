package com.oguzbabaoglu.kayakairlines.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.oguzbabaoglu.kayakairlines.R;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

  private final Drawable divider;

  public DividerItemDecoration(Context context) {
    divider = ContextCompat.getDrawable(context, R.drawable.divider);
  }

  @Override public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      View child = parent.getChildAt(i);
      RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

      int left = parent.getPaddingLeft();
      int top = child.getBottom() + params.bottomMargin - divider.getIntrinsicHeight() / 2;
      int right = parent.getWidth() - parent.getPaddingRight();
      int bottom = top + divider.getIntrinsicHeight();

      divider.setBounds(left, top, right, bottom);
      divider.draw(c);
    }
  }
}