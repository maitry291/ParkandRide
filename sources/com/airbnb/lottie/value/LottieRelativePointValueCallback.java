package com.airbnb.lottie.value;

import android.graphics.PointF;
import com.airbnb.lottie.utils.MiscUtils;

public class LottieRelativePointValueCallback extends LottieValueCallback<PointF> {
    private final PointF point = new PointF();

    public LottieRelativePointValueCallback() {
    }

    public LottieRelativePointValueCallback(PointF staticValue) {
        super(staticValue);
    }

    public final PointF getValue(LottieFrameInfo<PointF> frameInfo) {
        this.point.set(MiscUtils.lerp(frameInfo.getStartValue().x, frameInfo.getEndValue().x, frameInfo.getInterpolatedKeyframeProgress()), MiscUtils.lerp(frameInfo.getStartValue().y, frameInfo.getEndValue().y, frameInfo.getInterpolatedKeyframeProgress()));
        PointF offset = getOffset(frameInfo);
        this.point.offset(offset.x, offset.y);
        return this.point;
    }

    public PointF getOffset(LottieFrameInfo<PointF> lottieFrameInfo) {
        if (this.value != null) {
            return (PointF) this.value;
        }
        throw new IllegalArgumentException("You must provide a static value in the constructor , call setValue, or override getValue.");
    }
}
