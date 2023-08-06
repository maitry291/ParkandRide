package com.airbnb.lottie.model.animatable;

public class AnimatableTextProperties {
    public final AnimatableColorValue color;
    public final AnimatableColorValue stroke;
    public final AnimatableFloatValue strokeWidth;
    public final AnimatableFloatValue tracking;

    public AnimatableTextProperties(AnimatableColorValue color2, AnimatableColorValue stroke2, AnimatableFloatValue strokeWidth2, AnimatableFloatValue tracking2) {
        this.color = color2;
        this.stroke = stroke2;
        this.strokeWidth = strokeWidth2;
        this.tracking = tracking2;
    }
}
