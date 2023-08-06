package com.google.android.material.color.utilities;

public final class ViewingConditions {
    public static final ViewingConditions DEFAULT = make(new double[]{ColorUtils.whitePointD65()[0], ColorUtils.whitePointD65()[1], ColorUtils.whitePointD65()[2]}, (ColorUtils.yFromLstar(50.0d) * 63.66197723675813d) / 100.0d, 50.0d, 2.0d, false);
    private final double aw;
    private final double c;
    private final double fl;
    private final double flRoot;
    private final double n;
    private final double nbb;
    private final double nc;
    private final double ncb;
    private final double[] rgbD;
    private final double z;

    public double getAw() {
        return this.aw;
    }

    public double getN() {
        return this.n;
    }

    public double getNbb() {
        return this.nbb;
    }

    /* access modifiers changed from: package-private */
    public double getNcb() {
        return this.ncb;
    }

    /* access modifiers changed from: package-private */
    public double getC() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public double getNc() {
        return this.nc;
    }

    public double[] getRgbD() {
        return this.rgbD;
    }

    /* access modifiers changed from: package-private */
    public double getFl() {
        return this.fl;
    }

    public double getFlRoot() {
        return this.flRoot;
    }

    /* access modifiers changed from: package-private */
    public double getZ() {
        return this.z;
    }

    static ViewingConditions make(double[] whitePoint, double adaptingLuminance, double backgroundLstar, double surround, boolean discountingIlluminant) {
        double c2;
        double d;
        double d2 = adaptingLuminance;
        double[][] matrix = Cam16.XYZ_TO_CAM16RGB;
        double[] xyz = whitePoint;
        double rW = (xyz[0] * matrix[0][0]) + (xyz[1] * matrix[0][1]) + (xyz[2] * matrix[0][2]);
        double gW = (xyz[0] * matrix[1][0]) + (xyz[1] * matrix[1][1]) + (xyz[2] * matrix[1][2]);
        double bW = (xyz[0] * matrix[2][0]) + (xyz[1] * matrix[2][1]) + (xyz[2] * matrix[2][2]);
        double f = (surround / 10.0d) + 0.8d;
        if (f >= 0.9d) {
            c2 = MathUtils.lerp(0.59d, 0.69d, (f - 0.9d) * 10.0d);
        } else {
            c2 = MathUtils.lerp(0.525d, 0.59d, 10.0d * (f - 0.8d));
        }
        if (discountingIlluminant) {
            d = 1.0d;
        } else {
            d = (1.0d - (Math.exp(((-d2) - 42.0d) / 92.0d) * 0.2777777777777778d)) * f;
        }
        double d3 = MathUtils.clampDouble(0.0d, 1.0d, d);
        double[] rgbD2 = {(((100.0d / rW) * d3) + 1.0d) - d3, (((100.0d / gW) * d3) + 1.0d) - d3, (((100.0d / bW) * d3) + 1.0d) - d3};
        double k = 1.0d / ((d2 * 5.0d) + 1.0d);
        double k4 = k * k * k * k;
        double k4F = 1.0d - k4;
        double fl2 = (k4 * d2) + (0.1d * k4F * k4F * Math.cbrt(5.0d * d2));
        double d4 = d3;
        double n2 = ColorUtils.yFromLstar(backgroundLstar) / whitePoint[1];
        double d5 = n2;
        double nbb2 = 0.725d / Math.pow(n2, 0.2d);
        double d6 = nbb2;
        double ncb2 = nbb2;
        double[] rgbD3 = rgbD2;
        double[] rgbAFactors = {Math.pow(((rgbD2[0] * fl2) * rW) / 100.0d, 0.42d), Math.pow(((rgbD3[1] * fl2) * gW) / 100.0d, 0.42d), Math.pow(((rgbD3[2] * fl2) * bW) / 100.0d, 0.42d)};
        double[] rgbA = {(rgbAFactors[0] * 400.0d) / (rgbAFactors[0] + 27.13d), (rgbAFactors[1] * 400.0d) / (rgbAFactors[1] + 27.13d), (rgbAFactors[2] * 400.0d) / (rgbAFactors[2] + 27.13d)};
        return new ViewingConditions(d5, ((rgbA[0] * 2.0d) + rgbA[1] + (rgbA[2] * 0.05d)) * nbb2, d6, ncb2, c2, f, rgbD2, fl2, Math.pow(fl2, 0.25d), Math.sqrt(n2) + 1.48d);
    }

    private ViewingConditions(double n2, double aw2, double nbb2, double ncb2, double c2, double nc2, double[] rgbD2, double fl2, double flRoot2, double z2) {
        this.n = n2;
        this.aw = aw2;
        this.nbb = nbb2;
        this.ncb = ncb2;
        this.c = c2;
        this.nc = nc2;
        this.rgbD = rgbD2;
        this.fl = fl2;
        this.flRoot = flRoot2;
        this.z = z2;
    }
}
