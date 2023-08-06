package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class ColorKeyframeAnimation extends KeyframeAnimation<Integer> {
    public ColorKeyframeAnimation(List<Keyframe<Integer>> keyframes) {
        super(keyframes);
    }

    /* access modifiers changed from: package-private */
    public Integer getValue(Keyframe<Integer> keyframe, float keyframeProgress) {
        return Integer.valueOf(getIntValue(keyframe, keyframeProgress));
    }

    public int getIntValue(Keyframe<Integer> keyframe, float keyframeProgress) {
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        if (this.valueCallback != null) {
            Integer value = (Integer) this.valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), (Integer) keyframe.startValue, (Integer) keyframe.endValue, keyframeProgress, getLinearCurrentKeyframeProgress(), getProgress());
            if (value != null) {
                return value.intValue();
            }
        }
        return GammaEvaluator.evaluate(MiscUtils.clamp(keyframeProgress, 0.0f, 1.0f), ((Integer) keyframe.startValue).intValue(), ((Integer) keyframe.endValue).intValue());
    }

    public int getIntValue() {
        return getIntValue(getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
    }
}
