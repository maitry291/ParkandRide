package com.airbnb.lottie.animation.keyframe;

import android.graphics.Color;
import android.graphics.Paint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.value.LottieValueCallback;

public class DropShadowKeyframeAnimation implements BaseKeyframeAnimation.AnimationListener {
    private static final double DEG_TO_RAD = 0.017453292519943295d;
    private final BaseKeyframeAnimation<Integer, Integer> color;
    private final BaseKeyframeAnimation<Float, Float> direction;
    private final BaseKeyframeAnimation<Float, Float> distance;
    private boolean isDirty = true;
    private final BaseKeyframeAnimation.AnimationListener listener;
    private final BaseKeyframeAnimation<Float, Float> opacity;
    private final BaseKeyframeAnimation<Float, Float> radius;

    public DropShadowKeyframeAnimation(BaseKeyframeAnimation.AnimationListener listener2, BaseLayer layer, DropShadowEffect dropShadowEffect) {
        this.listener = listener2;
        BaseKeyframeAnimation<Integer, Integer> createAnimation = dropShadowEffect.getColor().createAnimation();
        this.color = createAnimation;
        createAnimation.addUpdateListener(this);
        layer.addAnimation(createAnimation);
        BaseKeyframeAnimation<Float, Float> createAnimation2 = dropShadowEffect.getOpacity().createAnimation();
        this.opacity = createAnimation2;
        createAnimation2.addUpdateListener(this);
        layer.addAnimation(createAnimation2);
        BaseKeyframeAnimation<Float, Float> createAnimation3 = dropShadowEffect.getDirection().createAnimation();
        this.direction = createAnimation3;
        createAnimation3.addUpdateListener(this);
        layer.addAnimation(createAnimation3);
        BaseKeyframeAnimation<Float, Float> createAnimation4 = dropShadowEffect.getDistance().createAnimation();
        this.distance = createAnimation4;
        createAnimation4.addUpdateListener(this);
        layer.addAnimation(createAnimation4);
        BaseKeyframeAnimation<Float, Float> createAnimation5 = dropShadowEffect.getRadius().createAnimation();
        this.radius = createAnimation5;
        createAnimation5.addUpdateListener(this);
        layer.addAnimation(createAnimation5);
    }

    public void onValueChanged() {
        this.isDirty = true;
        this.listener.onValueChanged();
    }

    public void applyTo(Paint paint) {
        if (this.isDirty) {
            this.isDirty = false;
            double directionRad = ((double) this.direction.getValue().floatValue()) * DEG_TO_RAD;
            float distance2 = this.distance.getValue().floatValue();
            int baseColor = this.color.getValue().intValue();
            int color2 = Color.argb(Math.round(this.opacity.getValue().floatValue()), Color.red(baseColor), Color.green(baseColor), Color.blue(baseColor));
            paint.setShadowLayer(this.radius.getValue().floatValue(), ((float) Math.sin(directionRad)) * distance2, ((float) Math.cos(3.141592653589793d + directionRad)) * distance2, color2);
        }
    }

    public void setColorCallback(LottieValueCallback<Integer> callback) {
        this.color.setValueCallback(callback);
    }

    public void setOpacityCallback(final LottieValueCallback<Float> callback) {
        if (callback == null) {
            this.opacity.setValueCallback((LottieValueCallback) null);
        } else {
            this.opacity.setValueCallback(new LottieValueCallback<Float>() {
                /* JADX WARNING: type inference failed for: r4v0, types: [com.airbnb.lottie.value.LottieFrameInfo, com.airbnb.lottie.value.LottieFrameInfo<java.lang.Float>] */
                /* JADX WARNING: Unknown variable types count: 1 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Float getValue(com.airbnb.lottie.value.LottieFrameInfo<java.lang.Float> r4) {
                    /*
                        r3 = this;
                        com.airbnb.lottie.value.LottieValueCallback r0 = r3
                        java.lang.Object r0 = r0.getValue(r4)
                        java.lang.Float r0 = (java.lang.Float) r0
                        if (r0 != 0) goto L_0x000c
                        r1 = 0
                        return r1
                    L_0x000c:
                        float r1 = r0.floatValue()
                        r2 = 1076048691(0x40233333, float:2.55)
                        float r1 = r1 * r2
                        java.lang.Float r1 = java.lang.Float.valueOf(r1)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation.AnonymousClass1.getValue(com.airbnb.lottie.value.LottieFrameInfo):java.lang.Float");
                }
            });
        }
    }

    public void setDirectionCallback(LottieValueCallback<Float> callback) {
        this.direction.setValueCallback(callback);
    }

    public void setDistanceCallback(LottieValueCallback<Float> callback) {
        this.distance.setValueCallback(callback);
    }

    public void setRadiusCallback(LottieValueCallback<Float> callback) {
        this.radius.setValueCallback(callback);
    }
}
