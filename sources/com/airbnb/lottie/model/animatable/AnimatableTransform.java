package com.airbnb.lottie.model.animatable;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.ModifierContent;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.layer.BaseLayer;

public class AnimatableTransform implements ModifierContent, ContentModel {
    private final AnimatablePathValue anchorPoint;
    private final AnimatableFloatValue endOpacity;
    private final AnimatableIntegerValue opacity;
    private final AnimatableValue<PointF, PointF> position;
    private final AnimatableFloatValue rotation;
    private final AnimatableScaleValue scale;
    private final AnimatableFloatValue skew;
    private final AnimatableFloatValue skewAngle;
    private final AnimatableFloatValue startOpacity;

    public AnimatableTransform() {
        this((AnimatablePathValue) null, (AnimatableValue<PointF, PointF>) null, (AnimatableScaleValue) null, (AnimatableFloatValue) null, (AnimatableIntegerValue) null, (AnimatableFloatValue) null, (AnimatableFloatValue) null, (AnimatableFloatValue) null, (AnimatableFloatValue) null);
    }

    public AnimatableTransform(AnimatablePathValue anchorPoint2, AnimatableValue<PointF, PointF> position2, AnimatableScaleValue scale2, AnimatableFloatValue rotation2, AnimatableIntegerValue opacity2, AnimatableFloatValue startOpacity2, AnimatableFloatValue endOpacity2, AnimatableFloatValue skew2, AnimatableFloatValue skewAngle2) {
        this.anchorPoint = anchorPoint2;
        this.position = position2;
        this.scale = scale2;
        this.rotation = rotation2;
        this.opacity = opacity2;
        this.startOpacity = startOpacity2;
        this.endOpacity = endOpacity2;
        this.skew = skew2;
        this.skewAngle = skewAngle2;
    }

    public AnimatablePathValue getAnchorPoint() {
        return this.anchorPoint;
    }

    public AnimatableValue<PointF, PointF> getPosition() {
        return this.position;
    }

    public AnimatableScaleValue getScale() {
        return this.scale;
    }

    public AnimatableFloatValue getRotation() {
        return this.rotation;
    }

    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    public AnimatableFloatValue getStartOpacity() {
        return this.startOpacity;
    }

    public AnimatableFloatValue getEndOpacity() {
        return this.endOpacity;
    }

    public AnimatableFloatValue getSkew() {
        return this.skew;
    }

    public AnimatableFloatValue getSkewAngle() {
        return this.skewAngle;
    }

    public TransformKeyframeAnimation createAnimation() {
        return new TransformKeyframeAnimation(this);
    }

    public Content toContent(LottieDrawable drawable, BaseLayer layer) {
        return null;
    }
}
