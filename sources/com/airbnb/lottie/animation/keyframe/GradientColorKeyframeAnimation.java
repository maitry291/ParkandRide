package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class GradientColorKeyframeAnimation extends KeyframeAnimation<GradientColor> {
    private final GradientColor gradientColor;

    public GradientColorKeyframeAnimation(List<Keyframe<GradientColor>> keyframes) {
        super(keyframes);
        int size = 0;
        GradientColor startValue = (GradientColor) keyframes.get(0).startValue;
        size = startValue != null ? startValue.getSize() : size;
        this.gradientColor = new GradientColor(new float[size], new int[size]);
    }

    /* access modifiers changed from: package-private */
    public GradientColor getValue(Keyframe<GradientColor> keyframe, float keyframeProgress) {
        this.gradientColor.lerp((GradientColor) keyframe.startValue, (GradientColor) keyframe.endValue, keyframeProgress);
        return this.gradientColor;
    }
}
