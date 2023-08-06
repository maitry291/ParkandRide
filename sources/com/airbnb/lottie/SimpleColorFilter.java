package com.airbnb.lottie;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

public class SimpleColorFilter extends PorterDuffColorFilter {
    public SimpleColorFilter(int color) {
        super(color, PorterDuff.Mode.SRC_ATOP);
    }
}
