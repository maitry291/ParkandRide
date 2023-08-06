package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class PointKeyframeAnimation extends KeyframeAnimation<PointF> {
    private final PointF point = new PointF();

    public PointKeyframeAnimation(List<Keyframe<PointF>> keyframes) {
        super(keyframes);
    }

    public PointF getValue(Keyframe<PointF> keyframe, float keyframeProgress) {
        return getValue(keyframe, keyframeProgress, keyframeProgress, keyframeProgress);
    }

    /* access modifiers changed from: protected */
    public PointF getValue(Keyframe<PointF> keyframe, float linearKeyframeProgress, float xKeyframeProgress, float yKeyframeProgress) {
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF startPoint = (PointF) keyframe.startValue;
        PointF endPoint = (PointF) keyframe.endValue;
        if (this.valueCallback != null) {
            PointF value = (PointF) this.valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), startPoint, endPoint, linearKeyframeProgress, getLinearCurrentKeyframeProgress(), getProgress());
            if (value != null) {
                return value;
            }
        }
        this.point.set(startPoint.x + ((endPoint.x - startPoint.x) * xKeyframeProgress), startPoint.y + ((endPoint.y - startPoint.y) * yKeyframeProgress));
        return this.point;
    }
}
