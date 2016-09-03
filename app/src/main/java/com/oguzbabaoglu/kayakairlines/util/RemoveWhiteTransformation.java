package com.oguzbabaoglu.kayakairlines.util;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Replaces white pixels with transparent ones in source {@link Bitmap}.
 */
public class RemoveWhiteTransformation implements Transformation {

  private static final int WHITE = 0xFFFFFFFF;
  private static final int TRANSPARENT = 0x00000000;

  @Override public Bitmap transform(Bitmap source) {
    int width = source.getWidth();
    int height = source.getHeight();
    int[] pixels = new int[width * height];
    source.getPixels(pixels, 0, width, 0, 0, width, height);

    for(int i = 0; i < pixels.length; i++) {
      pixels[i] = (pixels[i] == WHITE) ? TRANSPARENT : pixels[i];
    }

    Bitmap result = Bitmap.createBitmap(width, height, source.getConfig());
    result.setPixels(pixels, 0, width, 0, 0, width, height);

    source.recycle();

    return result;
  }

  @Override public String key() {
    return RemoveWhiteTransformation.class.getCanonicalName();
  }
}
