package com.airbnb.lottie.utils;

public class GammaEvaluator {
    private static float OECF_sRGB(float linear) {
        return linear <= 0.0031308f ? 12.92f * linear : (float) ((Math.pow((double) linear, 0.4166666567325592d) * 1.0549999475479126d) - 0.054999999701976776d);
    }

    private static float EOCF_sRGB(float srgb) {
        return srgb <= 0.04045f ? srgb / 12.92f : (float) Math.pow((double) ((0.055f + srgb) / 1.055f), 2.4000000953674316d);
    }

    public static int evaluate(float fraction, int startInt, int endInt) {
        int i = startInt;
        int i2 = endInt;
        if (i == i2) {
            return i;
        }
        float startA = ((float) ((i >> 24) & 255)) / 255.0f;
        float startR = EOCF_sRGB(((float) ((i >> 16) & 255)) / 255.0f);
        float startG = EOCF_sRGB(((float) ((i >> 8) & 255)) / 255.0f);
        float startB = EOCF_sRGB(((float) (i & 255)) / 255.0f);
        return (Math.round(((((((float) ((i2 >> 24) & 255)) / 255.0f) - startA) * fraction) + startA) * 255.0f) << 24) | (Math.round(OECF_sRGB(((EOCF_sRGB(((float) ((i2 >> 16) & 255)) / 255.0f) - startR) * fraction) + startR) * 255.0f) << 16) | (Math.round(OECF_sRGB(((EOCF_sRGB(((float) ((i2 >> 8) & 255)) / 255.0f) - startG) * fraction) + startG) * 255.0f) << 8) | Math.round(OECF_sRGB(((EOCF_sRGB(((float) (i2 & 255)) / 255.0f) - startB) * fraction) + startB) * 255.0f);
    }
}
