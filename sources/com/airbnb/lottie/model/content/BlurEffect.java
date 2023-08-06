package com.airbnb.lottie.model.content;

import com.airbnb.lottie.model.animatable.AnimatableFloatValue;

public class BlurEffect {
    final AnimatableFloatValue blurriness;

    public BlurEffect(AnimatableFloatValue blurriness2) {
        this.blurriness = blurriness2;
    }

    public AnimatableFloatValue getBlurriness() {
        return this.blurriness;
    }
}
