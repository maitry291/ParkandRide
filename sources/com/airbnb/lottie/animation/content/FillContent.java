package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ColorKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.ShapeFill;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public class FillContent implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private BaseKeyframeAnimation<Float, Float> blurAnimation;
    float blurMaskFilterRadius;
    private final BaseKeyframeAnimation<Integer, Integer> colorAnimation;
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private DropShadowKeyframeAnimation dropShadowAnimation;
    private final boolean hidden;
    private final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Integer, Integer> opacityAnimation;
    private final Paint paint = new LPaint(1);
    private final Path path;
    private final List<PathContent> paths = new ArrayList();

    public FillContent(LottieDrawable lottieDrawable2, BaseLayer layer2, ShapeFill fill) {
        Path path2 = new Path();
        this.path = path2;
        this.layer = layer2;
        this.name = fill.getName();
        this.hidden = fill.isHidden();
        this.lottieDrawable = lottieDrawable2;
        if (layer2.getBlurEffect() != null) {
            BaseKeyframeAnimation<Float, Float> createAnimation = layer2.getBlurEffect().getBlurriness().createAnimation();
            this.blurAnimation = createAnimation;
            createAnimation.addUpdateListener(this);
            layer2.addAnimation(this.blurAnimation);
        }
        if (layer2.getDropShadowEffect() != null) {
            this.dropShadowAnimation = new DropShadowKeyframeAnimation(this, layer2, layer2.getDropShadowEffect());
        }
        if (fill.getColor() == null || fill.getOpacity() == null) {
            this.colorAnimation = null;
            this.opacityAnimation = null;
            return;
        }
        path2.setFillType(fill.getFillType());
        BaseKeyframeAnimation<Integer, Integer> createAnimation2 = fill.getColor().createAnimation();
        this.colorAnimation = createAnimation2;
        createAnimation2.addUpdateListener(this);
        layer2.addAnimation(createAnimation2);
        BaseKeyframeAnimation<Integer, Integer> createAnimation3 = fill.getOpacity().createAnimation();
        this.opacityAnimation = createAnimation3;
        createAnimation3.addUpdateListener(this);
        layer2.addAnimation(createAnimation3);
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> list, List<Content> contentsAfter) {
        for (int i = 0; i < contentsAfter.size(); i++) {
            Content content = contentsAfter.get(i);
            if (content instanceof PathContent) {
                this.paths.add((PathContent) content);
            }
        }
    }

    public String getName() {
        return this.name;
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        if (!this.hidden) {
            L.beginSection("FillContent#draw");
            this.paint.setColor((MiscUtils.clamp((int) ((((((float) parentAlpha) / 255.0f) * ((float) this.opacityAnimation.getValue().intValue())) / 100.0f) * 255.0f), 0, 255) << 24) | (16777215 & ((ColorKeyframeAnimation) this.colorAnimation).getIntValue()));
            BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.colorFilterAnimation;
            if (baseKeyframeAnimation != null) {
                this.paint.setColorFilter(baseKeyframeAnimation.getValue());
            }
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.blurAnimation;
            if (baseKeyframeAnimation2 != null) {
                float blurRadius = baseKeyframeAnimation2.getValue().floatValue();
                if (blurRadius == 0.0f) {
                    this.paint.setMaskFilter((MaskFilter) null);
                } else if (blurRadius != this.blurMaskFilterRadius) {
                    this.paint.setMaskFilter(this.layer.getBlurMaskFilter(blurRadius));
                }
                this.blurMaskFilterRadius = blurRadius;
            }
            DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.dropShadowAnimation;
            if (dropShadowKeyframeAnimation != null) {
                dropShadowKeyframeAnimation.applyTo(this.paint);
            }
            this.path.reset();
            for (int i = 0; i < this.paths.size(); i++) {
                this.path.addPath(this.paths.get(i).getPath(), parentMatrix);
            }
            canvas.drawPath(this.path, this.paint);
            L.endSection("FillContent#draw");
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        this.path.reset();
        for (int i = 0; i < this.paths.size(); i++) {
            this.path.addPath(this.paths.get(i).getPath(), parentMatrix);
        }
        this.path.computeBounds(outBounds, false);
        outBounds.set(outBounds.left - 1.0f, outBounds.top - 1.0f, outBounds.right + 1.0f, outBounds.bottom + 1.0f);
    }

    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation2;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation3;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation4;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation5;
        if (property == LottieProperty.COLOR) {
            this.colorAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.OPACITY) {
            this.opacityAnimation.setValueCallback(callback);
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
            this.layer.addAnimation(this.colorFilterAnimation);
        } else if (property == LottieProperty.BLUR_RADIUS) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.blurAnimation;
            if (baseKeyframeAnimation2 != null) {
                baseKeyframeAnimation2.setValueCallback(callback);
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(callback);
            this.blurAnimation = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            this.layer.addAnimation(this.blurAnimation);
        } else if (property == LottieProperty.DROP_SHADOW_COLOR && (dropShadowKeyframeAnimation5 = this.dropShadowAnimation) != null) {
            dropShadowKeyframeAnimation5.setColorCallback(callback);
        } else if (property == LottieProperty.DROP_SHADOW_OPACITY && (dropShadowKeyframeAnimation4 = this.dropShadowAnimation) != null) {
            dropShadowKeyframeAnimation4.setOpacityCallback(callback);
        } else if (property == LottieProperty.DROP_SHADOW_DIRECTION && (dropShadowKeyframeAnimation3 = this.dropShadowAnimation) != null) {
            dropShadowKeyframeAnimation3.setDirectionCallback(callback);
        } else if (property == LottieProperty.DROP_SHADOW_DISTANCE && (dropShadowKeyframeAnimation2 = this.dropShadowAnimation) != null) {
            dropShadowKeyframeAnimation2.setDistanceCallback(callback);
        } else if (property == LottieProperty.DROP_SHADOW_RADIUS && (dropShadowKeyframeAnimation = this.dropShadowAnimation) != null) {
            dropShadowKeyframeAnimation.setRadiusCallback(callback);
        }
    }
}
