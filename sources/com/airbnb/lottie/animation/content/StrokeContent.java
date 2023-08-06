package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.LottieValueCallback;

public class StrokeContent extends BaseStrokeContent {
    private final BaseKeyframeAnimation<Integer, Integer> colorAnimation;
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private final boolean hidden;
    private final BaseLayer layer;
    private final String name;

    public StrokeContent(LottieDrawable lottieDrawable, BaseLayer layer2, ShapeStroke stroke) {
        super(lottieDrawable, layer2, stroke.getCapType().toPaintCap(), stroke.getJoinType().toPaintJoin(), stroke.getMiterLimit(), stroke.getOpacity(), stroke.getWidth(), stroke.getLineDashPattern(), stroke.getDashOffset());
        this.layer = layer2;
        this.name = stroke.getName();
        this.hidden = stroke.isHidden();
        BaseKeyframeAnimation<Integer, Integer> createAnimation = stroke.getColor().createAnimation();
        this.colorAnimation = createAnimation;
        createAnimation.addUpdateListener(this);
        layer2.addAnimation(createAnimation);
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        if (!this.hidden) {
            this.paint.setColor(((ColorKeyframeAnimation) this.colorAnimation).getIntValue());
            if (this.colorFilterAnimation != null) {
                this.paint.setColorFilter(this.colorFilterAnimation.getValue());
            }
            super.draw(canvas, parentMatrix, parentAlpha);
        }
    }

    public String getName() {
        return this.name;
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        super.addValueCallback(property, callback);
        if (property == LottieProperty.STROKE_COLOR) {
            this.colorAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.COLOR_FILTER) {
            BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.colorFilterAnimation;
            if (baseKeyframeAnimation != null) {
                this.layer.removeAnimation(baseKeyframeAnimation);
            }
            if (callback == null) {
                this.colorFilterAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.colorFilterAnimation = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            this.layer.addAnimation(this.colorAnimation);
        }
    }
}
