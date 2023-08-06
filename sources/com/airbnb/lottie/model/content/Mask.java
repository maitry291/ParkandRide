package com.airbnb.lottie.model.content;

import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;

public class Mask {
    private final boolean inverted;
    private final MaskMode maskMode;
    private final AnimatableShapeValue maskPath;
    private final AnimatableIntegerValue opacity;

    public enum MaskMode {
        MASK_MODE_ADD,
        MASK_MODE_SUBTRACT,
        MASK_MODE_INTERSECT,
        MASK_MODE_NONE
    }

    public Mask(MaskMode maskMode2, AnimatableShapeValue maskPath2, AnimatableIntegerValue opacity2, boolean inverted2) {
        this.maskMode = maskMode2;
        this.maskPath = maskPath2;
        this.opacity = opacity2;
        this.inverted = inverted2;
    }

    public MaskMode getMaskMode() {
        return this.maskMode;
    }

    public AnimatableShapeValue getMaskPath() {
        return this.maskPath;
    }

    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    public boolean isInverted() {
        return this.inverted;
    }
}
