package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.collection.SparseArrayCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.lang.ref.WeakReference;

class KeyframeParser {
    static JsonReader.Options INTERPOLATOR_NAMES = JsonReader.Options.of("x", "y");
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    private static final float MAX_CP_VALUE = 100.0f;
    static JsonReader.Options NAMES = JsonReader.Options.of("t", "s", "e", "o", "i", "h", TypedValues.TransitionType.S_TO, "ti");
    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache;

    KeyframeParser() {
    }

    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache() {
        if (pathInterpolatorCache == null) {
            pathInterpolatorCache = new SparseArrayCompat<>();
        }
        return pathInterpolatorCache;
    }

    private static WeakReference<Interpolator> getInterpolator(int hash) {
        WeakReference<Interpolator> weakReference;
        synchronized (KeyframeParser.class) {
            weakReference = pathInterpolatorCache().get(hash);
        }
        return weakReference;
    }

    private static void putInterpolator(int hash, WeakReference<Interpolator> interpolator) {
        synchronized (KeyframeParser.class) {
            pathInterpolatorCache.put(hash, interpolator);
        }
    }

    static <T> Keyframe<T> parse(JsonReader reader, LottieComposition composition, float scale, ValueParser<T> valueParser, boolean animated, boolean multiDimensional) throws IOException {
        if (animated && multiDimensional) {
            return parseMultiDimensionalKeyframe(composition, reader, scale, valueParser);
        }
        if (animated) {
            return parseKeyframe(composition, reader, scale, valueParser);
        }
        return parseStaticValue(reader, scale, valueParser);
    }

    private static <T> Keyframe<T> parseKeyframe(LottieComposition composition, JsonReader reader, float scale, ValueParser<T> valueParser) throws IOException {
        Interpolator interpolator;
        JsonReader jsonReader = reader;
        float f = scale;
        ValueParser<T> valueParser2 = valueParser;
        PointF cp1 = null;
        PointF cp2 = null;
        float startFrame = 0.0f;
        T startValue = null;
        T endValue = null;
        boolean hold = false;
        reader.beginObject();
        PointF pathCp1 = null;
        PointF pathCp2 = null;
        while (reader.hasNext()) {
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    startFrame = (float) reader.nextDouble();
                    break;
                case 1:
                    startValue = valueParser2.parse(jsonReader, f);
                    break;
                case 2:
                    endValue = valueParser2.parse(jsonReader, f);
                    break;
                case 3:
                    cp1 = JsonUtils.jsonToPoint(jsonReader, 1.0f);
                    break;
                case 4:
                    cp2 = JsonUtils.jsonToPoint(jsonReader, 1.0f);
                    break;
                case 5:
                    boolean z = true;
                    if (reader.nextInt() != 1) {
                        z = false;
                    }
                    hold = z;
                    break;
                case 6:
                    pathCp1 = JsonUtils.jsonToPoint(reader, scale);
                    break;
                case 7:
                    pathCp2 = JsonUtils.jsonToPoint(reader, scale);
                    break;
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        if (hold) {
            endValue = startValue;
            interpolator = LINEAR_INTERPOLATOR;
        } else if (cp1 == null || cp2 == null) {
            interpolator = LINEAR_INTERPOLATOR;
        } else {
            interpolator = interpolatorFor(cp1, cp2);
        }
        Keyframe<T> keyframe = new Keyframe<>(composition, startValue, endValue, interpolator, startFrame, (Float) null);
        keyframe.pathCp1 = pathCp1;
        keyframe.pathCp2 = pathCp2;
        return keyframe;
    }

    private static <T> Keyframe<T> parseMultiDimensionalKeyframe(LottieComposition composition, JsonReader reader, float scale, ValueParser<T> valueParser) throws IOException {
        Interpolator yInterpolator;
        Interpolator xInterpolator;
        Interpolator interpolator;
        T endValue;
        Keyframe<T> keyframe;
        float yCp2x;
        float yCp2y;
        JsonReader jsonReader = reader;
        float f = scale;
        ValueParser<T> valueParser2 = valueParser;
        PointF cp1 = null;
        PointF cp2 = null;
        PointF xCp1 = null;
        PointF xCp2 = null;
        PointF yCp1 = null;
        PointF yCp2 = null;
        float startFrame = 0.0f;
        T startValue = null;
        boolean hold = false;
        Interpolator xInterpolator2 = null;
        Interpolator yInterpolator2 = null;
        reader.beginObject();
        T endValue2 = null;
        T endValue3 = null;
        PointF pathCp2 = null;
        while (reader.hasNext()) {
            Interpolator xInterpolator3 = xInterpolator2;
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    float f2 = startFrame;
                    T t = endValue3;
                    PointF pointF = pathCp2;
                    Interpolator interpolator2 = yInterpolator2;
                    startFrame = (float) reader.nextDouble();
                    xInterpolator2 = xInterpolator3;
                    yCp2 = yCp2;
                    break;
                case 1:
                    float f3 = startFrame;
                    T t2 = endValue3;
                    PointF pointF2 = pathCp2;
                    Interpolator interpolator3 = yInterpolator2;
                    startValue = valueParser2.parse(jsonReader, f);
                    xInterpolator2 = xInterpolator3;
                    break;
                case 2:
                    float f4 = startFrame;
                    T t3 = endValue3;
                    PointF pointF3 = pathCp2;
                    Interpolator interpolator4 = yInterpolator2;
                    endValue2 = valueParser2.parse(jsonReader, f);
                    xInterpolator2 = xInterpolator3;
                    break;
                case 3:
                    PointF yCp22 = yCp2;
                    float startFrame2 = startFrame;
                    T pathCp1 = endValue3;
                    PointF pathCp22 = pathCp2;
                    Interpolator yInterpolator3 = yInterpolator2;
                    if (reader.peek() != JsonReader.Token.BEGIN_OBJECT) {
                        cp1 = JsonUtils.jsonToPoint(reader, scale);
                        xInterpolator2 = xInterpolator3;
                        yInterpolator2 = yInterpolator3;
                        pathCp2 = pathCp22;
                        endValue3 = pathCp1;
                        startFrame = startFrame2;
                        yCp2 = yCp22;
                        break;
                    } else {
                        reader.beginObject();
                        float xCp1x = 0.0f;
                        float xCp1y = 0.0f;
                        float yCp1x = 0.0f;
                        float yCp1y = 0.0f;
                        while (reader.hasNext()) {
                            switch (jsonReader.selectName(INTERPOLATOR_NAMES)) {
                                case 0:
                                    if (reader.peek() != JsonReader.Token.NUMBER) {
                                        reader.beginArray();
                                        xCp1x = (float) reader.nextDouble();
                                        if (reader.peek() == JsonReader.Token.NUMBER) {
                                            yCp1x = (float) reader.nextDouble();
                                        } else {
                                            yCp1x = xCp1x;
                                        }
                                        reader.endArray();
                                        break;
                                    } else {
                                        xCp1x = (float) reader.nextDouble();
                                        yCp1x = xCp1x;
                                        break;
                                    }
                                case 1:
                                    if (reader.peek() != JsonReader.Token.NUMBER) {
                                        reader.beginArray();
                                        xCp1y = (float) reader.nextDouble();
                                        if (reader.peek() == JsonReader.Token.NUMBER) {
                                            yCp1y = (float) reader.nextDouble();
                                        } else {
                                            yCp1y = xCp1y;
                                        }
                                        reader.endArray();
                                        break;
                                    } else {
                                        xCp1y = (float) reader.nextDouble();
                                        yCp1y = xCp1y;
                                        break;
                                    }
                                default:
                                    reader.skipValue();
                                    break;
                            }
                        }
                        xCp1 = new PointF(xCp1x, xCp1y);
                        yCp1 = new PointF(yCp1x, yCp1y);
                        reader.endObject();
                        xInterpolator2 = xInterpolator3;
                        yInterpolator2 = yInterpolator3;
                        pathCp2 = pathCp22;
                        endValue3 = pathCp1;
                        startFrame = startFrame2;
                        yCp2 = yCp22;
                        break;
                    }
                case 4:
                    Interpolator yInterpolator4 = yInterpolator2;
                    if (reader.peek() != JsonReader.Token.BEGIN_OBJECT) {
                        PointF pointF4 = yCp2;
                        float f5 = startFrame;
                        T t4 = endValue3;
                        PointF pointF5 = pathCp2;
                        cp2 = JsonUtils.jsonToPoint(reader, scale);
                        xInterpolator2 = xInterpolator3;
                        yInterpolator2 = yInterpolator4;
                        break;
                    } else {
                        reader.beginObject();
                        float xCp2x = 0.0f;
                        float xCp2y = 0.0f;
                        T pathCp12 = endValue3;
                        PointF pathCp23 = pathCp2;
                        float yCp2x2 = 0.0f;
                        float yCp2y2 = 0.0f;
                        while (reader.hasNext()) {
                            float startFrame3 = startFrame;
                            switch (jsonReader.selectName(INTERPOLATOR_NAMES)) {
                                case 0:
                                    PointF yCp23 = yCp2;
                                    if (reader.peek() != JsonReader.Token.NUMBER) {
                                        reader.beginArray();
                                        xCp2x = (float) reader.nextDouble();
                                        if (reader.peek() == JsonReader.Token.NUMBER) {
                                            yCp2x = (float) reader.nextDouble();
                                        } else {
                                            yCp2x = xCp2x;
                                        }
                                        yCp2x2 = yCp2x;
                                        reader.endArray();
                                        startFrame = startFrame3;
                                        yCp2 = yCp23;
                                        break;
                                    } else {
                                        xCp2x = (float) reader.nextDouble();
                                        yCp2x2 = xCp2x;
                                        startFrame = startFrame3;
                                        yCp2 = yCp23;
                                        break;
                                    }
                                case 1:
                                    PointF yCp24 = yCp2;
                                    if (reader.peek() != JsonReader.Token.NUMBER) {
                                        reader.beginArray();
                                        xCp2y = (float) reader.nextDouble();
                                        if (reader.peek() == JsonReader.Token.NUMBER) {
                                            yCp2y = (float) reader.nextDouble();
                                        } else {
                                            yCp2y = xCp2y;
                                        }
                                        yCp2y2 = yCp2y;
                                        reader.endArray();
                                        startFrame = startFrame3;
                                        yCp2 = yCp24;
                                        break;
                                    } else {
                                        xCp2y = (float) reader.nextDouble();
                                        yCp2y2 = xCp2y;
                                        startFrame = startFrame3;
                                        yCp2 = yCp24;
                                        break;
                                    }
                                default:
                                    PointF pointF6 = yCp2;
                                    reader.skipValue();
                                    startFrame = startFrame3;
                                    break;
                            }
                        }
                        float f6 = startFrame;
                        xCp2 = new PointF(xCp2x, xCp2y);
                        yCp2 = new PointF(yCp2x2, yCp2y2);
                        reader.endObject();
                        xInterpolator2 = xInterpolator3;
                        yInterpolator2 = yInterpolator4;
                        pathCp2 = pathCp23;
                        endValue3 = pathCp12;
                        break;
                    }
                case 5:
                    Interpolator yInterpolator5 = yInterpolator2;
                    boolean z = true;
                    if (reader.nextInt() != 1) {
                        z = false;
                    }
                    hold = z;
                    xInterpolator2 = xInterpolator3;
                    yInterpolator2 = yInterpolator5;
                    break;
                case 6:
                    endValue3 = JsonUtils.jsonToPoint(reader, scale);
                    xInterpolator2 = xInterpolator3;
                    break;
                case 7:
                    pathCp2 = JsonUtils.jsonToPoint(reader, scale);
                    xInterpolator2 = xInterpolator3;
                    break;
                default:
                    PointF pointF7 = yCp2;
                    float f7 = startFrame;
                    T t5 = endValue3;
                    PointF pointF8 = pathCp2;
                    Interpolator interpolator5 = yInterpolator2;
                    reader.skipValue();
                    xInterpolator2 = xInterpolator3;
                    break;
            }
        }
        PointF yCp25 = yCp2;
        float startFrame4 = startFrame;
        T pathCp13 = endValue3;
        PointF pathCp24 = pathCp2;
        Interpolator xInterpolator4 = xInterpolator2;
        Interpolator yInterpolator6 = yInterpolator2;
        reader.endObject();
        if (hold) {
            endValue = startValue;
            interpolator = LINEAR_INTERPOLATOR;
            xInterpolator = xInterpolator4;
            yInterpolator = yInterpolator6;
            PointF pointF9 = yCp25;
        } else if (cp1 != null && cp2 != null) {
            interpolator = interpolatorFor(cp1, cp2);
            endValue = endValue2;
            xInterpolator = xInterpolator4;
            yInterpolator = yInterpolator6;
            PointF pointF10 = yCp25;
        } else if (xCp1 == null || yCp1 == null || xCp2 == null || yCp25 == null) {
            interpolator = LINEAR_INTERPOLATOR;
            endValue = endValue2;
            xInterpolator = xInterpolator4;
            yInterpolator = yInterpolator6;
        } else {
            xInterpolator = interpolatorFor(xCp1, xCp2);
            yInterpolator = interpolatorFor(yCp1, yCp25);
            endValue = endValue2;
            interpolator = null;
        }
        if (xInterpolator == null || yInterpolator == null) {
            keyframe = new Keyframe<>(composition, startValue, endValue, interpolator, startFrame4, (Float) null);
        } else {
            keyframe = new Keyframe<>(composition, startValue, endValue, xInterpolator, yInterpolator, startFrame4, (Float) null);
        }
        keyframe.pathCp1 = pathCp13;
        keyframe.pathCp2 = pathCp24;
        return keyframe;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: android.view.animation.Interpolator} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.view.animation.Interpolator interpolatorFor(android.graphics.PointF r8, android.graphics.PointF r9) {
        /*
            r0 = 0
            float r1 = r8.x
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            r3 = 1065353216(0x3f800000, float:1.0)
            float r1 = com.airbnb.lottie.utils.MiscUtils.clamp((float) r1, (float) r2, (float) r3)
            r8.x = r1
            float r1 = r8.y
            r4 = -1027080192(0xffffffffc2c80000, float:-100.0)
            r5 = 1120403456(0x42c80000, float:100.0)
            float r1 = com.airbnb.lottie.utils.MiscUtils.clamp((float) r1, (float) r4, (float) r5)
            r8.y = r1
            float r1 = r9.x
            float r1 = com.airbnb.lottie.utils.MiscUtils.clamp((float) r1, (float) r2, (float) r3)
            r9.x = r1
            float r1 = r9.y
            float r1 = com.airbnb.lottie.utils.MiscUtils.clamp((float) r1, (float) r4, (float) r5)
            r9.y = r1
            float r1 = r8.x
            float r2 = r8.y
            float r4 = r9.x
            float r5 = r9.y
            int r1 = com.airbnb.lottie.utils.Utils.hashFor(r1, r2, r4, r5)
            java.lang.ref.WeakReference r2 = getInterpolator(r1)
            if (r2 == 0) goto L_0x0042
            java.lang.Object r4 = r2.get()
            r0 = r4
            android.view.animation.Interpolator r0 = (android.view.animation.Interpolator) r0
        L_0x0042:
            if (r2 == 0) goto L_0x0046
            if (r0 != 0) goto L_0x0087
        L_0x0046:
            float r4 = r8.x     // Catch:{ IllegalArgumentException -> 0x0054 }
            float r5 = r8.y     // Catch:{ IllegalArgumentException -> 0x0054 }
            float r6 = r9.x     // Catch:{ IllegalArgumentException -> 0x0054 }
            float r7 = r9.y     // Catch:{ IllegalArgumentException -> 0x0054 }
            android.view.animation.Interpolator r3 = androidx.core.view.animation.PathInterpolatorCompat.create(r4, r5, r6, r7)     // Catch:{ IllegalArgumentException -> 0x0054 }
            r0 = r3
            goto L_0x007d
        L_0x0054:
            r4 = move-exception
            java.lang.String r5 = r4.getMessage()
            java.lang.String r6 = "The Path cannot loop back on itself."
            boolean r5 = r6.equals(r5)
            if (r5 == 0) goto L_0x0077
            float r5 = r8.x
            float r3 = java.lang.Math.min(r5, r3)
            float r5 = r8.y
            float r6 = r9.x
            r7 = 0
            float r6 = java.lang.Math.max(r6, r7)
            float r7 = r9.y
            android.view.animation.Interpolator r0 = androidx.core.view.animation.PathInterpolatorCompat.create(r3, r5, r6, r7)
            goto L_0x007d
        L_0x0077:
            android.view.animation.LinearInterpolator r3 = new android.view.animation.LinearInterpolator
            r3.<init>()
            r0 = r3
        L_0x007d:
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0086 }
            r3.<init>(r0)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0086 }
            putInterpolator(r1, r3)     // Catch:{ ArrayIndexOutOfBoundsException -> 0x0086 }
            goto L_0x0087
        L_0x0086:
            r3 = move-exception
        L_0x0087:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.KeyframeParser.interpolatorFor(android.graphics.PointF, android.graphics.PointF):android.view.animation.Interpolator");
    }

    private static <T> Keyframe<T> parseStaticValue(JsonReader reader, float scale, ValueParser<T> valueParser) throws IOException {
        return new Keyframe<>(valueParser.parse(reader, scale));
    }
}
