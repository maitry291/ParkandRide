package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;

class ShapeStrokeParser {
    private static final JsonReader.Options DASH_PATTERN_NAMES = JsonReader.Options.of("n", "v");
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "c", "w", "o", "lc", "lj", "ml", "hd", "d");

    private ShapeStrokeParser() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0066, code lost:
        if (r11.equals("o") != false) goto L_0x007e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.content.ShapeStroke parse(com.airbnb.lottie.parser.moshi.JsonReader r22, com.airbnb.lottie.LottieComposition r23) throws java.io.IOException {
        /*
            r0 = r22
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
        L_0x0010:
            boolean r11 = r22.hasNext()
            if (r11 == 0) goto L_0x00f7
            com.airbnb.lottie.parser.moshi.JsonReader$Options r11 = NAMES
            int r11 = r0.selectName(r11)
            r12 = 1
            switch(r11) {
                case 0: goto L_0x00ef;
                case 1: goto L_0x00e7;
                case 2: goto L_0x00df;
                case 3: goto L_0x00d7;
                case 4: goto L_0x00c8;
                case 5: goto L_0x00b9;
                case 6: goto L_0x00b0;
                case 7: goto L_0x00a8;
                case 8: goto L_0x0026;
                default: goto L_0x0020;
            }
        L_0x0020:
            r15 = r23
            r22.skipValue()
            goto L_0x0010
        L_0x0026:
            r22.beginArray()
        L_0x0029:
            boolean r11 = r22.hasNext()
            r13 = 0
            if (r11 == 0) goto L_0x0092
            r11 = 0
            r14 = 0
            r22.beginObject()
        L_0x0035:
            boolean r15 = r22.hasNext()
            if (r15 == 0) goto L_0x0055
            com.airbnb.lottie.parser.moshi.JsonReader$Options r15 = DASH_PATTERN_NAMES
            int r15 = r0.selectName(r15)
            switch(r15) {
                case 0: goto L_0x0050;
                case 1: goto L_0x004b;
                default: goto L_0x0044;
            }
        L_0x0044:
            r22.skipName()
            r22.skipValue()
            goto L_0x0035
        L_0x004b:
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r14 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r22, r23)
            goto L_0x0035
        L_0x0050:
            java.lang.String r11 = r22.nextString()
            goto L_0x0035
        L_0x0055:
            r22.endObject()
            int r16 = r11.hashCode()
            switch(r16) {
                case 100: goto L_0x0073;
                case 103: goto L_0x0069;
                case 111: goto L_0x0060;
                default: goto L_0x005f;
            }
        L_0x005f:
            goto L_0x007d
        L_0x0060:
            java.lang.String r15 = "o"
            boolean r15 = r11.equals(r15)
            if (r15 == 0) goto L_0x005f
            goto L_0x007e
        L_0x0069:
            java.lang.String r13 = "g"
            boolean r13 = r11.equals(r13)
            if (r13 == 0) goto L_0x005f
            r13 = 2
            goto L_0x007e
        L_0x0073:
            java.lang.String r13 = "d"
            boolean r13 = r11.equals(r13)
            if (r13 == 0) goto L_0x005f
            r13 = 1
            goto L_0x007e
        L_0x007d:
            r13 = -1
        L_0x007e:
            switch(r13) {
                case 0: goto L_0x008d;
                case 1: goto L_0x0084;
                case 2: goto L_0x0084;
                default: goto L_0x0081;
            }
        L_0x0081:
            r15 = r23
            goto L_0x0091
        L_0x0084:
            r15 = r23
            r15.setHasDashPattern(r12)
            r10.add(r14)
            goto L_0x0091
        L_0x008d:
            r15 = r23
            r7 = r14
        L_0x0091:
            goto L_0x0029
        L_0x0092:
            r15 = r23
            r22.endArray()
            int r11 = r10.size()
            if (r11 != r12) goto L_0x0010
            java.lang.Object r11 = r10.get(r13)
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r11 = (com.airbnb.lottie.model.animatable.AnimatableFloatValue) r11
            r10.add(r11)
            goto L_0x0010
        L_0x00a8:
            r15 = r23
            boolean r9 = r22.nextBoolean()
            goto L_0x0010
        L_0x00b0:
            r15 = r23
            double r11 = r22.nextDouble()
            float r8 = (float) r11
            goto L_0x0010
        L_0x00b9:
            r15 = r23
            com.airbnb.lottie.model.content.ShapeStroke$LineJoinType[] r11 = com.airbnb.lottie.model.content.ShapeStroke.LineJoinType.values()
            int r13 = r22.nextInt()
            int r13 = r13 - r12
            r6 = r11[r13]
            goto L_0x0010
        L_0x00c8:
            r15 = r23
            com.airbnb.lottie.model.content.ShapeStroke$LineCapType[] r11 = com.airbnb.lottie.model.content.ShapeStroke.LineCapType.values()
            int r13 = r22.nextInt()
            int r13 = r13 - r12
            r5 = r11[r13]
            goto L_0x0010
        L_0x00d7:
            r15 = r23
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r4 = com.airbnb.lottie.parser.AnimatableValueParser.parseInteger(r22, r23)
            goto L_0x0010
        L_0x00df:
            r15 = r23
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r3 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r22, r23)
            goto L_0x0010
        L_0x00e7:
            r15 = r23
            com.airbnb.lottie.model.animatable.AnimatableColorValue r2 = com.airbnb.lottie.parser.AnimatableValueParser.parseColor(r22, r23)
            goto L_0x0010
        L_0x00ef:
            r15 = r23
            java.lang.String r1 = r22.nextString()
            goto L_0x0010
        L_0x00f7:
            r15 = r23
            if (r4 != 0) goto L_0x0112
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r11 = new com.airbnb.lottie.model.animatable.AnimatableIntegerValue
            com.airbnb.lottie.value.Keyframe r12 = new com.airbnb.lottie.value.Keyframe
            r13 = 100
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r12.<init>(r13)
            java.util.List r12 = java.util.Collections.singletonList(r12)
            r11.<init>(r12)
            r16 = r11
            goto L_0x0114
        L_0x0112:
            r16 = r4
        L_0x0114:
            com.airbnb.lottie.model.content.ShapeStroke r4 = new com.airbnb.lottie.model.content.ShapeStroke
            r11 = r4
            r12 = r1
            r13 = r7
            r14 = r10
            r15 = r2
            r17 = r3
            r18 = r5
            r19 = r6
            r20 = r8
            r21 = r9
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.ShapeStrokeParser.parse(com.airbnb.lottie.parser.moshi.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.content.ShapeStroke");
    }
}
