package com.airbnb.lottie.value;

import android.view.animation.Interpolator;
import com.airbnb.lottie.utils.MiscUtils;

public class LottieInterpolatedIntegerValue extends LottieInterpolatedValue<Integer> {
    public /* bridge */ /* synthetic */ Object getValue(LottieFrameInfo lottieFrameInfo) {
        return super.getValue(lottieFrameInfo);
    }

    public LottieInterpolatedIntegerValue(Integer startValue, Integer endValue) {
        super(startValue, endValue);
    }

    public LottieInterpolatedIntegerValue(Integer startValue, Integer endValue, Interpolator interpolator) {
        super(startValue, endValue, interpolator);
    }

    /* access modifiers changed from: package-private */
    public Integer interpolateValue(Integer startValue, Integer endValue, float progress) {
        return Integer.valueOf(MiscUtils.lerp(startValue.intValue(), endValue.intValue(), progress));
    }
}
