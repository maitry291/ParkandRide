package com.airbnb.lottie.parser;

class MaskParser {
    private MaskParser() {
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0091, code lost:
        if (r5.equals("a") != false) goto L_0x0095;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.airbnb.lottie.model.content.Mask parse(com.airbnb.lottie.parser.moshi.JsonReader r12, com.airbnb.lottie.LottieComposition r13) throws java.io.IOException {
        /*
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r12.beginObject()
        L_0x0007:
            boolean r4 = r12.hasNext()
            if (r4 == 0) goto L_0x00cb
            java.lang.String r4 = r12.nextName()
            int r5 = r4.hashCode()
            r6 = 0
            r7 = 3
            r8 = 1
            r9 = 2
            r10 = -1
            switch(r5) {
                case 111: goto L_0x003c;
                case 3588: goto L_0x0032;
                case 104433: goto L_0x0028;
                case 3357091: goto L_0x001e;
                default: goto L_0x001d;
            }
        L_0x001d:
            goto L_0x0046
        L_0x001e:
            java.lang.String r5 = "mode"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x001d
            r5 = 0
            goto L_0x0047
        L_0x0028:
            java.lang.String r5 = "inv"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x001d
            r5 = 3
            goto L_0x0047
        L_0x0032:
            java.lang.String r5 = "pt"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x001d
            r5 = 1
            goto L_0x0047
        L_0x003c:
            java.lang.String r5 = "o"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L_0x001d
            r5 = 2
            goto L_0x0047
        L_0x0046:
            r5 = -1
        L_0x0047:
            switch(r5) {
                case 0: goto L_0x0061;
                case 1: goto L_0x005b;
                case 2: goto L_0x0055;
                case 3: goto L_0x004f;
                default: goto L_0x004a;
            }
        L_0x004a:
            r12.skipValue()
            goto L_0x00c9
        L_0x004f:
            boolean r3 = r12.nextBoolean()
            goto L_0x00c9
        L_0x0055:
            com.airbnb.lottie.model.animatable.AnimatableIntegerValue r2 = com.airbnb.lottie.parser.AnimatableValueParser.parseInteger(r12, r13)
            goto L_0x00c9
        L_0x005b:
            com.airbnb.lottie.model.animatable.AnimatableShapeValue r1 = com.airbnb.lottie.parser.AnimatableValueParser.parseShapeData(r12, r13)
            goto L_0x00c9
        L_0x0061:
            java.lang.String r5 = r12.nextString()
            int r11 = r5.hashCode()
            switch(r11) {
                case 97: goto L_0x008b;
                case 105: goto L_0x0081;
                case 110: goto L_0x0077;
                case 115: goto L_0x006d;
                default: goto L_0x006c;
            }
        L_0x006c:
            goto L_0x0094
        L_0x006d:
            java.lang.String r6 = "s"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x006c
            r6 = 1
            goto L_0x0095
        L_0x0077:
            java.lang.String r6 = "n"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x006c
            r6 = 2
            goto L_0x0095
        L_0x0081:
            java.lang.String r6 = "i"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x006c
            r6 = 3
            goto L_0x0095
        L_0x008b:
            java.lang.String r7 = "a"
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x006c
            goto L_0x0095
        L_0x0094:
            r6 = -1
        L_0x0095:
            switch(r6) {
                case 0: goto L_0x00c5;
                case 1: goto L_0x00c2;
                case 2: goto L_0x00bf;
                case 3: goto L_0x00b7;
                default: goto L_0x0098;
            }
        L_0x0098:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Unknown mask mode "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r4)
            java.lang.String r6 = ". Defaulting to Add."
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.airbnb.lottie.utils.Logger.warning(r5)
            com.airbnb.lottie.model.content.Mask$MaskMode r0 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_ADD
            goto L_0x00c8
        L_0x00b7:
            java.lang.String r5 = "Animation contains intersect masks. They are not supported but will be treated like add masks."
            r13.addWarning(r5)
            com.airbnb.lottie.model.content.Mask$MaskMode r0 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_INTERSECT
            goto L_0x00c8
        L_0x00bf:
            com.airbnb.lottie.model.content.Mask$MaskMode r0 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_NONE
            goto L_0x00c8
        L_0x00c2:
            com.airbnb.lottie.model.content.Mask$MaskMode r0 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_SUBTRACT
            goto L_0x00c8
        L_0x00c5:
            com.airbnb.lottie.model.content.Mask$MaskMode r0 = com.airbnb.lottie.model.content.Mask.MaskMode.MASK_MODE_ADD
        L_0x00c8:
        L_0x00c9:
            goto L_0x0007
        L_0x00cb:
            r12.endObject()
            com.airbnb.lottie.model.content.Mask r4 = new com.airbnb.lottie.model.content.Mask
            r4.<init>(r0, r1, r2, r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.MaskParser.parse(com.airbnb.lottie.parser.moshi.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.content.Mask");
    }
}
