package com.airbnb.lottie.model.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.RectangleContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class RectangleShape implements ContentModel {
    private final AnimatableFloatValue cornerRadius;
    private final boolean hidden;
    private final String name;
    private final AnimatableValue<PointF, PointF> position;
    private final AnimatableValue<PointF, PointF> size;

    public RectangleShape(String name2, AnimatableValue<PointF, PointF> position2, AnimatableValue<PointF, PointF> size2, AnimatableFloatValue cornerRadius2, boolean hidden2) {
        this.name = name2;
        this.position = position2;
        this.size = size2;
        this.cornerRadius = cornerRadius2;
        this.hidden = hidden2;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableFloatValue getCornerRadius() {
        return this.cornerRadius;
    }

    public AnimatableValue<PointF, PointF> getSize() {
        return this.size;
    }

    public AnimatableValue<PointF, PointF> getPosition() {
        return this.position;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public Content toContent(LottieDrawable drawable, BaseLayer layer) {
        return new RectangleContent(drawable, layer, this);
    }

    public String toString() {
        return "RectangleShape{position=" + this.position + ", size=" + this.size + '}';
    }
}
