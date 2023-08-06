package com.airbnb.lottie.animation.content;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.MaskFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.DropShadowKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.model.content.GradientFill;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public class GradientFillContent implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private static final int CACHE_STEPS_MS = 32;
    private BaseKeyframeAnimation<Float, Float> blurAnimation;
    float blurMaskFilterRadius;
    private final RectF boundsRect;
    private final int cacheSteps;
    private final BaseKeyframeAnimation<GradientColor, GradientColor> colorAnimation;
    private ValueCallbackKeyframeAnimation colorCallbackAnimation;
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private DropShadowKeyframeAnimation dropShadowAnimation;
    private final BaseKeyframeAnimation<PointF, PointF> endPointAnimation;
    private final boolean hidden;
    private final BaseLayer layer;
    private final LongSparseArray<LinearGradient> linearGradientCache = new LongSparseArray<>();
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Integer, Integer> opacityAnimation;
    private final Paint paint;
    private final Path path;
    private final List<PathContent> paths;
    private final LongSparseArray<RadialGradient> radialGradientCache = new LongSparseArray<>();
    private final BaseKeyframeAnimation<PointF, PointF> startPointAnimation;
    private final GradientType type;

    public GradientFillContent(LottieDrawable lottieDrawable2, BaseLayer layer2, GradientFill fill) {
        Path path2 = new Path();
        this.path = path2;
        this.paint = new LPaint(1);
        this.boundsRect = new RectF();
        this.paths = new ArrayList();
        this.blurMaskFilterRadius = 0.0f;
        this.layer = layer2;
        this.name = fill.getName();
        this.hidden = fill.isHidden();
        this.lottieDrawable = lottieDrawable2;
        this.type = fill.getGradientType();
        path2.setFillType(fill.getFillType());
        this.cacheSteps = (int) (lottieDrawable2.getComposition().getDuration() / 32.0f);
        BaseKeyframeAnimation<GradientColor, GradientColor> createAnimation = fill.getGradientColor().createAnimation();
        this.colorAnimation = createAnimation;
        createAnimation.addUpdateListener(this);
        layer2.addAnimation(createAnimation);
        BaseKeyframeAnimation<Integer, Integer> createAnimation2 = fill.getOpacity().createAnimation();
        this.opacityAnimation = createAnimation2;
        createAnimation2.addUpdateListener(this);
        layer2.addAnimation(createAnimation2);
        BaseKeyframeAnimation<PointF, PointF> createAnimation3 = fill.getStartPoint().createAnimation();
        this.startPointAnimation = createAnimation3;
        createAnimation3.addUpdateListener(this);
        layer2.addAnimation(createAnimation3);
        BaseKeyframeAnimation<PointF, PointF> createAnimation4 = fill.getEndPoint().createAnimation();
        this.endPointAnimation = createAnimation4;
        createAnimation4.addUpdateListener(this);
        layer2.addAnimation(createAnimation4);
        if (layer2.getBlurEffect() != null) {
            BaseKeyframeAnimation<Float, Float> createAnimation5 = layer2.getBlurEffect().getBlurriness().createAnimation();
            this.blurAnimation = createAnimation5;
            createAnimation5.addUpdateListener(this);
            layer2.addAnimation(this.blurAnimation);
        }
        if (layer2.getDropShadowEffect() != null) {
            this.dropShadowAnimation = new DropShadowKeyframeAnimation(this, layer2, layer2.getDropShadowEffect());
        }
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

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        Shader shader;
        if (!this.hidden) {
            L.beginSection("GradientFillContent#draw");
            this.path.reset();
            for (int i = 0; i < this.paths.size(); i++) {
                this.path.addPath(this.paths.get(i).getPath(), parentMatrix);
            }
            this.path.computeBounds(this.boundsRect, false);
            if (this.type == GradientType.LINEAR) {
                shader = getLinearGradient();
            } else {
                shader = getRadialGradient();
            }
            shader.setLocalMatrix(parentMatrix);
            this.paint.setShader(shader);
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
                    this.paint.setMaskFilter(new BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL));
                }
                this.blurMaskFilterRadius = blurRadius;
            }
            DropShadowKeyframeAnimation dropShadowKeyframeAnimation = this.dropShadowAnimation;
            if (dropShadowKeyframeAnimation != null) {
                dropShadowKeyframeAnimation.applyTo(this.paint);
            }
            this.paint.setAlpha(MiscUtils.clamp((int) ((((((float) parentAlpha) / 255.0f) * ((float) this.opacityAnimation.getValue().intValue())) / 100.0f) * 255.0f), 0, 255));
            canvas.drawPath(this.path, this.paint);
            L.endSection("GradientFillContent#draw");
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
        LinearGradient gradient2 = new LinearGradient(startPoint.x, startPoint.y, endPoint.x, endPoint.y, colors, gradientColor.getPositions(), Shader.TileMode.CLAMP);
        this.linearGradientCache.put((long) gradientHash, gradient2);
        return gradient2;
    }

    private RadialGradient getRadialGradient() {
        float r;
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
        float r2 = (float) Math.hypot((double) (x1 - x0), (double) (y1 - y0));
        if (r2 <= 0.0f) {
            r = 0.001f;
        } else {
            r = r2;
        }
        float f = y1;
        float f2 = x1;
        float f3 = y0;
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

    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation2;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation3;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation4;
        DropShadowKeyframeAnimation dropShadowKeyframeAnimation5;
        if (property == LottieProperty.OPACITY) {
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
        } else if (property == LottieProperty.GRADIENT_COLOR) {
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = this.colorCallbackAnimation;
            if (valueCallbackKeyframeAnimation2 != null) {
                this.layer.removeAnimation(valueCallbackKeyframeAnimation2);
            }
            if (callback == null) {
                this.colorCallbackAnimation = null;
                return;
            }
            this.linearGradientCache.clear();
            this.radialGradientCache.clear();
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation3 = new ValueCallbackKeyframeAnimation(callback);
            this.colorCallbackAnimation = valueCallbackKeyframeAnimation3;
            valueCallbackKeyframeAnimation3.addUpdateListener(this);
            this.layer.addAnimation(this.colorCallbackAnimation);
        } else if (property == LottieProperty.BLUR_RADIUS) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.blurAnimation;
            if (baseKeyframeAnimation2 != null) {
                baseKeyframeAnimation2.setValueCallback(callback);
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation4 = new ValueCallbackKeyframeAnimation(callback);
            this.blurAnimation = valueCallbackKeyframeAnimation4;
            valueCallbackKeyframeAnimation4.addUpdateListener(this);
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
