package com.airbnb.lottie.model;

import android.graphics.Typeface;

public class Font {
    private final float ascent;
    private final String family;
    private final String name;
    private final String style;
    private Typeface typeface;

    public Font(String family2, String name2, String style2, float ascent2) {
        this.family = family2;
        this.name = name2;
        this.style = style2;
        this.ascent = ascent2;
    }

    public String getFamily() {
        return this.family;
    }

    public String getName() {
        return this.name;
    }

    public String getStyle() {
        return this.style;
    }

    /* access modifiers changed from: package-private */
    public float getAscent() {
        return this.ascent;
    }

    public Typeface getTypeface() {
        return this.typeface;
    }

    public void setTypeface(Typeface typeface2) {
        this.typeface = typeface2;
    }
}
