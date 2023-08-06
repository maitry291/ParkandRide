package com.airbnb.lottie.parser;

import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;

public class DropShadowEffect {
    private final AnimatableColorValue color;
    private final AnimatableFloatValue direction;
    private final AnimatableFloatValue distance;
    private final AnimatableFloatValue opacity;
    private final AnimatableFloatValue radius;

    DropShadowEffect(AnimatableColorValue color2, AnimatableFloatValue opacity2, AnimatableFloatValue direction2, AnimatableFloatValue distance2, AnimatableFloatValue radius2) {
        this.color = color2;
        this.opacity = opacity2;
        this.direction = direction2;
        this.distance = distance2;
        this.radius = radius2;
    }

    public AnimatableColorValue getColor() {
        return this.color;
    }

    public AnimatableFloatValue getOpacity() {
        return this.opacity;
    }

    public AnimatableFloatValue getDirection() {
        return this.direction;
    }

    public AnimatableFloatValue getDistance() {
        return this.distance;
    }

    public AnimatableFloatValue getRadius() {
        return this.radius;
    }
}
