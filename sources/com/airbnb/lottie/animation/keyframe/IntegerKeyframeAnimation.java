package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class IntegerKeyframeAnimation extends KeyframeAnimation<Integer> {
    public IntegerKeyframeAnimation(List<Keyframe<Integer>> keyframes) {
        super(keyframes);
    }

    /* access modifiers changed from: package-private */
    public Integer getValue(Keyframe<Integer> keyframe, float keyframeProgress) {
        return Integer.valueOf(getIntValue(keyframe, keyframeProgress));
    }

    /* access modifiers changed from: package-private */
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
        return MiscUtils.lerp(keyframe.getStartValueInt(), keyframe.getEndValueInt(), keyframeProgress);
    }

    public int getIntValue() {
        return getIntValue(getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
    }
}
