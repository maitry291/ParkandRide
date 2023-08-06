package com.airbnb.lottie.model;

public class DocumentData {
    public float baselineShift;
    public int color;
    public String fontName;
    public Justification justification;
    public float lineHeight;
    public float size;
    public int strokeColor;
    public boolean strokeOverFill;
    public float strokeWidth;
    public String text;
    public int tracking;

    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String text2, String fontName2, float size2, Justification justification2, int tracking2, float lineHeight2, float baselineShift2, int color2, int strokeColor2, float strokeWidth2, boolean strokeOverFill2) {
        set(text2, fontName2, size2, justification2, tracking2, lineHeight2, baselineShift2, color2, strokeColor2, strokeWidth2, strokeOverFill2);
    }

    public DocumentData() {
    }

    public void set(String text2, String fontName2, float size2, Justification justification2, int tracking2, float lineHeight2, float baselineShift2, int color2, int strokeColor2, float strokeWidth2, boolean strokeOverFill2) {
        this.text = text2;
        this.fontName = fontName2;
        this.size = size2;
        this.justification = justification2;
        this.tracking = tracking2;
        this.lineHeight = lineHeight2;
        this.baselineShift = baselineShift2;
        this.color = color2;
        this.strokeColor = strokeColor2;
        this.strokeWidth = strokeWidth2;
        this.strokeOverFill = strokeOverFill2;
    }

    public int hashCode() {
        long temp = (long) Float.floatToRawIntBits(this.lineHeight);
        return (((((((((int) (((float) (((this.text.hashCode() * 31) + this.fontName.hashCode()) * 31)) + this.size)) * 31) + this.justification.ordinal()) * 31) + this.tracking) * 31) + ((int) ((temp >>> 32) ^ temp))) * 31) + this.color;
    }
}
