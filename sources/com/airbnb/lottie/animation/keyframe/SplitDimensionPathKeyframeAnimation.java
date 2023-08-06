package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;

public class SplitDimensionPathKeyframeAnimation extends BaseKeyframeAnimation<PointF, PointF> {
    private final PointF point = new PointF();
    private final PointF pointWithCallbackValues = new PointF();
    private final BaseKeyframeAnimation<Float, Float> xAnimation;
    protected LottieValueCallback<Float> xValueCallback;
    private final BaseKeyframeAnimation<Float, Float> yAnimation;
    protected LottieValueCallback<Float> yValueCallback;

    public SplitDimensionPathKeyframeAnimation(BaseKeyframeAnimation<Float, Float> xAnimation2, BaseKeyframeAnimation<Float, Float> yAnimation2) {
        super(Collections.emptyList());
        this.xAnimation = xAnimation2;
        this.yAnimation = yAnimation2;
        setProgress(getProgress());
    }

    public void setXValueCallback(LottieValueCallback<Float> xValueCallback2) {
        LottieValueCallback<Float> lottieValueCallback = this.xValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation((BaseKeyframeAnimation<?, ?>) null);
        }
        this.xValueCallback = xValueCallback2;
        if (xValueCallback2 != null) {
            xValueCallback2.setAnimation(this);
        }
    }

    public void setYValueCallback(LottieValueCallback<Float> yValueCallback2) {
        LottieValueCallback<Float> lottieValueCallback = this.yValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation((BaseKeyframeAnimation<?, ?>) null);
        }
        this.yValueCallback = yValueCallback2;
        if (yValueCallback2 != null) {
            yValueCallback2.setAnimation(this);
        }
    }

    public void setProgress(float progress) {
        this.xAnimation.setProgress(progress);
        this.yAnimation.setProgress(progress);
        this.point.set(this.xAnimation.getValue().floatValue(), this.yAnimation.getValue().floatValue());
        for (int i = 0; i < this.listeners.size(); i++) {
            ((BaseKeyframeAnimation.AnimationListener) this.listeners.get(i)).onValueChanged();
        }
    }

    public PointF getValue() {
        return getValue((Keyframe<PointF>) null, 0.0f);
    }

    /* access modifiers changed from: package-private */
    public PointF getValue(Keyframe<PointF> keyframe, float keyframeProgress) {
        Keyframe<Float> yKeyframe;
        Keyframe<Float> xKeyframe;
        Float xCallbackValue = null;
        Float yCallbackValue = null;
        if (!(this.xValueCallback == null || (xKeyframe = this.xAnimation.getCurrentKeyframe()) == null)) {
            float progress = this.xAnimation.getInterpolatedCurrentKeyframeProgress();
            Float endFrame = xKeyframe.endFrame;
            xCallbackValue = this.xValueCallback.getValueInternal(xKeyframe.startFrame, endFrame == null ? xKeyframe.startFrame : endFrame.floatValue(), (Float) xKeyframe.startValue, (Float) xKeyframe.endValue, keyframeProgress, keyframeProgress, progress);
        }
        if (!(this.yValueCallback == null || (yKeyframe = this.yAnimation.getCurrentKeyframe()) == null)) {
            float progress2 = this.yAnimation.getInterpolatedCurrentKeyframeProgress();
            Float endFrame2 = yKeyframe.endFrame;
            yCallbackValue = this.yValueCallback.getValueInternal(yKeyframe.startFrame, endFrame2 == null ? yKeyframe.startFrame : endFrame2.floatValue(), (Float) yKeyframe.startValue, (Float) yKeyframe.endValue, keyframeProgress, keyframeProgress, progress2);
        }
        if (xCallbackValue == null) {
            this.pointWithCallbackValues.set(this.point.x, 0.0f);
        } else {
            this.pointWithCallbackValues.set(xCallbackValue.floatValue(), 0.0f);
        }
        if (yCallbackValue == null) {
            PointF pointF = this.pointWithCallbackValues;
            pointF.set(pointF.x, this.point.y);
        } else {
            PointF pointF2 = this.pointWithCallbackValues;
            pointF2.set(pointF2.x, yCallbackValue.floatValue());
        }
        return this.pointWithCallbackValues;
    }
}
