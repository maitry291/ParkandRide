package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.model.content.GradientStroke;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.LottieValueCallback;

public class GradientStrokeContent extends BaseStrokeContent {
    private static final int CACHE_STEPS_MS = 32;
    private final RectF boundsRect = new RectF();
    private final int cacheSteps;
    private final BaseKeyframeAnimation<GradientColor, GradientColor> colorAnimation;
    private ValueCallbackKeyframeAnimation colorCallbackAnimation;
    private final BaseKeyframeAnimation<PointF, PointF> endPointAnimation;
    private final boolean hidden;
    private final LongSparseArray<LinearGradient> linearGradientCache = new LongSparseArray<>();
    private final String name;
    private final LongSparseArray<RadialGradient> radialGradientCache = new LongSparseArray<>();
    private final BaseKeyframeAnimation<PointF, PointF> startPointAnimation;
    private final GradientType type;

    public GradientStrokeContent(LottieDrawable lottieDrawable, BaseLayer layer, GradientStroke stroke) {
        super(lottieDrawable, layer, stroke.getCapType().toPaintCap(), stroke.getJoinType().toPaintJoin(), stroke.getMiterLimit(), stroke.getOpacity(), stroke.getWidth(), stroke.getLineDashPattern(), stroke.getDashOffset());
        this.name = stroke.getName();
        this.type = stroke.getGradientType();
        this.hidden = stroke.isHidden();
        this.cacheSteps = (int) (lottieDrawable.getComposition().getDuration() / 32.0f);
        BaseKeyframeAnimation<GradientColor, GradientColor> createAnimation = stroke.getGradientColor().createAnimation();
        this.colorAnimation = createAnimation;
        createAnimation.addUpdateListener(this);
        layer.addAnimation(createAnimation);
        BaseKeyframeAnimation<PointF, PointF> createAnimation2 = stroke.getStartPoint().createAnimation();
        this.startPointAnimation = createAnimation2;
        createAnimation2.addUpdateListener(this);
        layer.addAnimation(createAnimation2);
        BaseKeyframeAnimation<PointF, PointF> createAnimation3 = stroke.getEndPoint().createAnimation();
        this.endPointAnimation = createAnimation3;
        createAnimation3.addUpdateListener(this);
        layer.addAnimation(createAnimation3);
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        Shader shader;
        if (!this.hidden) {
            getBounds(this.boundsRect, parentMatrix, false);
            if (this.type == GradientType.LINEAR) {
                shader = getLinearGradient();
            } else {
                shader = getRadialGradient();
            }
            shader.setLocalMatrix(parentMatrix);
            this.paint.setShader(shader);
            super.draw(canvas, parentMatrix, parentAlpha);
        }
    }

    public String getName() {
        return this.name;
    }

    private LinearGradient getLinearGradient() {
        int gradientHash = getGradientHash();
        LinearGradient gradient = this.linearGradientCache.get((long) gradientHash);
        if (gradient != null) {
            return gradient;
        }
        PointF startPoint = this.startPointAnimation.getValue();
        PointF endPoint = this.endPointAnimation.getValue();
        GradientColor gradientColor = this.colorAnimation.getValue();
        int[] colors = applyDynamicColorsIfNeeded(gradientColor.getColors());
        float[] positions = gradientColor.getPositions();
        float x0 = startPoint.x;
        float y0 = startPoint.y;
        float x1 = endPoint.x;
        float y1 = endPoint.y;
        float f = y1;
        float f2 = x1;
        float f3 = y0;
        float f4 = x0;
        LinearGradient gradient2 = new LinearGradient(x0, y0, x1, y1, colors, positions, Shader.TileMode.CLAMP);
        this.linearGradientCache.put((long) gradientHash, gradient2);
        return gradient2;
    }

    private RadialGradient getRadialGradient() {
        int gradientHash = getGradientHash();
        RadialGradient gradient = this.radialGradientCache.get((long) gradientHash);
        if (gradient != null) {
            return gradient;
        }
        PointF startPoint = this.startPointAnimation.getValue();
        PointF endPoint = this.endPointAnimation.getValue();
        GradientColor gradientColor = this.colorAnimation.getValue();
        int[] colors = applyDynamicColorsIfNeeded(gradientColor.getColors());
        float[] positions = gradientColor.getPositions();
        float x0 = startPoint.x;
        float y0 = startPoint.y;
        float x1 = endPoint.x;
        float y1 = endPoint.y;
        float r = (float) Math.hypot((double) (x1 - x0), (double) (y1 - y0));
        float f = r;
        float f2 = y1;
        float f3 = x1;
        float f4 = y0;
        RadialGradient gradient2 = new RadialGradient(x0, y0, r, colors, positions, Shader.TileMode.CLAMP);
        this.radialGradientCache.put((long) gradientHash, gradient2);
        return gradient2;
    }

    private int getGradientHash() {
        int startPointProgress = Math.round(this.startPointAnimation.getProgress() * ((float) this.cacheSteps));
        int endPointProgress = Math.round(this.endPointAnimation.getProgress() * ((float) this.cacheSteps));
        int colorProgress = Math.round(this.colorAnimation.getProgress() * ((float) this.cacheSteps));
        int hash = 17;
        if (startPointProgress != 0) {
            hash = 17 * 31 * startPointProgress;
        }
        if (endPointProgress != 0) {
            hash = hash * 31 * endPointProgress;
        }
        if (colorProgress != 0) {
            return hash * 31 * colorProgress;
        }
        return hash;
    }

    private int[] applyDynamicColorsIfNeeded(int[] colors) {
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = this.colorCallbackAnimation;
        if (valueCallbackKeyframeAnimation != null) {
            Integer[] dynamicColors = (Integer[]) valueCallbackKeyframeAnimation.getValue();
            if (colors.length == dynamicColors.length) {
                for (int i = 0; i < colors.length; i++) {
                    colors[i] = dynamicColors[i].intValue();
                }
            } else {
                colors = new int[dynamicColors.length];
                for (int i2 = 0; i2 < dynamicColors.length; i2++) {
                    colors[i2] = dynamicColors[i2].intValue();
                }
            }
        }
        return colors;
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        super.addValueCallback(property, callback);
        if (property == LottieProperty.GRADIENT_COLOR) {
            if (this.colorCallbackAnimation != null) {
                this.layer.removeAnimation(this.colorCallbackAnimation);
            }
            if (callback == null) {
                this.colorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.colorCallbackAnimation = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            this.layer.addAnimation(this.colorCallbackAnimation);
        }
    }
}
