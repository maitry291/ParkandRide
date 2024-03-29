package com.airbnb.lottie.value;

public class ScaleXY {
    private float scaleX;
    private float scaleY;

    public ScaleXY(float sx, float sy) {
        this.scaleX = sx;
        this.scaleY = sy;
    }

    public ScaleXY() {
        this(1.0f, 1.0f);
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    public void set(float scaleX2, float scaleY2) {
        this.scaleX = scaleX2;
        this.scaleY = scaleY2;
    }

    public boolean equals(float scaleX2, float scaleY2) {
        return this.scaleX == scaleX2 && this.scaleY == scaleY2;
    }

    public String toString() {
        return getScaleX() + "x" + getScaleY();
    }
}
