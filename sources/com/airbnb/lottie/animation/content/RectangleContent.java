package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.RectangleShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class RectangleContent implements BaseKeyframeAnimation.AnimationListener, KeyPathElementContent, PathContent {
    private final BaseKeyframeAnimation<?, Float> cornerRadiusAnimation;
    private final boolean hidden;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final Path path = new Path();
    private final BaseKeyframeAnimation<?, PointF> positionAnimation;
    private final RectF rect = new RectF();
    private BaseKeyframeAnimation<Float, Float> roundedCornersAnimation = null;
    private final BaseKeyframeAnimation<?, PointF> sizeAnimation;
    private final CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();

    public RectangleContent(LottieDrawable lottieDrawable2, BaseLayer layer, RectangleShape rectShape) {
        this.name = rectShape.getName();
        this.hidden = rectShape.isHidden();
        this.lottieDrawable = lottieDrawable2;
        BaseKeyframeAnimation<PointF, PointF> createAnimation = rectShape.getPosition().createAnimation();
        this.positionAnimation = createAnimation;
        BaseKeyframeAnimation<PointF, PointF> createAnimation2 = rectShape.getSize().createAnimation();
        this.sizeAnimation = createAnimation2;
        BaseKeyframeAnimation<Float, Float> createAnimation3 = rectShape.getCornerRadius().createAnimation();
        this.cornerRadiusAnimation = createAnimation3;
        layer.addAnimation(createAnimation);
        layer.addAnimation(createAnimation2);
        layer.addAnimation(createAnimation3);
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
        createAnimation3.addUpdateListener(this);
    }

    public String getName() {
        return this.name;
    }

    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> contentsBefore, List<Content> list) {
        for (int i = 0; i < contentsBefore.size(); i++) {
            Content content = contentsBefore.get(i);
            if ((content instanceof TrimPathContent) && ((TrimPathContent) content).getType() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                TrimPathContent trimPath = (TrimPathContent) content;
                this.trimPaths.addTrimPath(trimPath);
                trimPath.addListener(this);
            } else if (content instanceof RoundedCornersContent) {
                this.roundedCornersAnimation = ((RoundedCornersContent) content).getRoundedCorners();
            }
        }
    }

    public Path getPath() {
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation;
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if (this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        PointF size = this.sizeAnimation.getValue();
        float halfWidth = size.x / 2.0f;
        float halfHeight = size.y / 2.0f;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.cornerRadiusAnimation;
        float radius = baseKeyframeAnimation2 == null ? 0.0f : ((FloatKeyframeAnimation) baseKeyframeAnimation2).getFloatValue();
        if (radius == 0.0f && (baseKeyframeAnimation = this.roundedCornersAnimation) != null) {
            radius = Math.min(baseKeyframeAnimation.getValue().floatValue(), Math.min(halfWidth, halfHeight));
        }
        float maxRadius = Math.min(halfWidth, halfHeight);
        if (radius > maxRadius) {
            radius = maxRadius;
        }
        PointF position = this.positionAnimation.getValue();
        this.path.moveTo(position.x + halfWidth, (position.y - halfHeight) + radius);
        this.path.lineTo(position.x + halfWidth, (position.y + halfHeight) - radius);
        if (radius > 0.0f) {
            this.rect.set((position.x + halfWidth) - (radius * 2.0f), (position.y + halfHeight) - (radius * 2.0f), position.x + halfWidth, position.y + halfHeight);
            this.path.arcTo(this.rect, 0.0f, 90.0f, false);
        }
        this.path.lineTo((position.x - halfWidth) + radius, position.y + halfHeight);
        if (radius > 0.0f) {
            this.rect.set(position.x - halfWidth, (position.y + halfHeight) - (radius * 2.0f), (position.x - halfWidth) + (radius * 2.0f), position.y + halfHeight);
            this.path.arcTo(this.rect, 90.0f, 90.0f, false);
        }
        this.path.lineTo(position.x - halfWidth, (position.y - halfHeight) + radius);
        if (radius > 0.0f) {
            this.rect.set(position.x - halfWidth, position.y - halfHeight, (position.x - halfWidth) + (radius * 2.0f), (position.y - halfHeight) + (radius * 2.0f));
            this.path.arcTo(this.rect, 180.0f, 90.0f, false);
        }
        this.path.lineTo((position.x + halfWidth) - radius, position.y - halfHeight);
        if (radius > 0.0f) {
            this.rect.set((position.x + halfWidth) - (radius * 2.0f), position.y - halfHeight, position.x + halfWidth, (position.y - halfHeight) + (2.0f * radius));
            this.path.arcTo(this.rect, 270.0f, 90.0f, false);
        }
        this.path.close();
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        if (property == LottieProperty.RECTANGLE_SIZE) {
            this.sizeAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.CORNER_RADIUS) {
            this.cornerRadiusAnimation.setValueCallback(callback);
        }
    }
}
