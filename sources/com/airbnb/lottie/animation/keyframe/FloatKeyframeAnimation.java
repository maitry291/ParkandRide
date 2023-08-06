package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class FloatKeyframeAnimation extends KeyframeAnimation<Float> {
    public FloatKeyframeAnimation(List<Keyframe<Float>> keyframes) {
        super(keyframes);
    }

    /* access modifiers changed from: package-private */
    public Float getValue(Keyframe<Float> keyframe, float keyframeProgress) {
        return Float.valueOf(getFloatValue(keyframe, keyframeProgress));
    }

    /* access modifiers changed from: package-private */
    public float getFloatValue(Keyframe<Float> keyframe, float keyframeProgress) {
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        if (this.valueCallback != null) {
            Float value = (Float) this.valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), (Float) keyframe.startValue, (Float) keyframe.endValue, keyframeProgress, getLinearCurrentKeyframeProgress(), getProgress());
            if (value != null) {
                return value.floatValue();
            }
        }
        return MiscUtils.lerp(keyframe.getStartValueFloat(), keyframe.getEndValueFloat(), keyframeProgress);
    }

    public float getFloatValue() {
        return getFloatValue(getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
    }
}
