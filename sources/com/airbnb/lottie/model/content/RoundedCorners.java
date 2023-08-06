package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.RoundedCornersContent;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class RoundedCorners implements ContentModel {
    private final AnimatableValue<Float, Float> cornerRadius;
    private final String name;

    public RoundedCorners(String name2, AnimatableValue<Float, Float> cornerRadius2) {
        this.name = name2;
        this.cornerRadius = cornerRadius2;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableValue<Float, Float> getCornerRadius() {
        return this.cornerRadius;
    }

    public Content toContent(LottieDrawable drawable, BaseLayer layer) {
        return new RoundedCornersContent(drawable, layer, this);
    }
}
