package com.airbnb.lottie.parser;

import android.graphics.Rect;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.util.Collections;
import java.util.List;

public class LayerParser {
    private static final JsonReader.Options EFFECTS_NAMES = JsonReader.Options.of("ty", "nm");
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd");
    private static final JsonReader.Options TEXT_NAMES = JsonReader.Options.of("d", "a");

    private LayerParser() {
    }

    public static Layer parse(LottieComposition composition) {
        Rect bounds = composition.getBounds();
        List emptyList = Collections.emptyList();
        Layer.LayerType layerType = Layer.LayerType.PRE_COMP;
        List emptyList2 = Collections.emptyList();
        AnimatableTransform animatableTransform = r3;
        AnimatableTransform animatableTransform2 = new AnimatableTransform();
        return new Layer(emptyList, composition, "__container", -1, layerType, -1, (String) null, emptyList2, animatableTransform, 0, 0, 0, 0.0f, 0.0f, bounds.width(), bounds.height(), (AnimatableTextFrame) null, (AnimatableTextProperties) null, Collections.emptyList(), Layer.MatteType.NONE, (AnimatableFloatValue) null, false, (BlurEffect) null, (DropShadowEffect) null);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.airbnb.lottie.model.layer.Layer parse(com.airbnb.lottie.parser.moshi.JsonReader r65, com.airbnb.lottie.LottieComposition r66) throws java.io.IOException {
        /*
            r0 = r65
            r15 = r66
            java.lang.String r1 = "UNSET"
            r2 = 0
            r3 = 0
            r4 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = -1
            r13 = 1065353216(0x3f800000, float:1.0)
            r14 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            com.airbnb.lottie.model.layer.Layer$MatteType r22 = com.airbnb.lottie.model.layer.Layer.MatteType.NONE
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            java.util.ArrayList r27 = new java.util.ArrayList
            r27.<init>()
            r41 = r27
            java.util.ArrayList r27 = new java.util.ArrayList
            r27.<init>()
            r42 = r27
            r65.beginObject()
            r43 = r2
            r44 = r3
            r45 = r4
            r47 = r6
            r48 = r7
            r49 = r8
            r50 = r9
            r51 = r10
            r52 = r11
            r54 = r13
            r55 = r14
            r56 = r16
            r12 = r18
            r57 = r19
            r58 = r20
            r59 = r21
            r60 = r22
            r61 = r23
            r62 = r24
            r63 = r25
            r64 = r26
            r14 = r1
        L_0x0066:
            boolean r1 = r65.hasNext()
            if (r1 == 0) goto L_0x02a3
            com.airbnb.lottie.parser.moshi.JsonReader$Options r1 = NAMES
            int r1 = r0.selectName(r1)
            switch(r1) {
                case 0: goto L_0x0293;
                case 1: goto L_0x0286;
                case 2: goto L_0x027c;
                case 3: goto L_0x0258;
                case 4: goto L_0x024b;
                case 5: goto L_0x0237;
                case 6: goto L_0x0223;
                case 7: goto L_0x0215;
                case 8: goto L_0x020b;
                case 9: goto L_0x01bc;
                case 10: goto L_0x0199;
                case 11: goto L_0x0176;
                case 12: goto L_0x0138;
                case 13: goto L_0x00cf;
                case 14: goto L_0x00c7;
                case 15: goto L_0x00bf;
                case 16: goto L_0x00b0;
                case 17: goto L_0x00a1;
                case 18: goto L_0x0099;
                case 19: goto L_0x0091;
                case 20: goto L_0x008b;
                case 21: goto L_0x0086;
                case 22: goto L_0x0081;
                default: goto L_0x0075;
            }
        L_0x0075:
            r10 = r41
            r11 = r42
            r65.skipName()
            r65.skipValue()
            goto L_0x029d
        L_0x0081:
            boolean r57 = r65.nextBoolean()
            goto L_0x0066
        L_0x0086:
            java.lang.String r12 = r65.nextString()
            goto L_0x0066
        L_0x008b:
            r1 = 0
            com.airbnb.lottie.model.animatable.AnimatableFloatValue r64 = com.airbnb.lottie.parser.AnimatableValueParser.parseFloat(r0, r15, r1)
            goto L_0x0066
        L_0x0091:
            double r1 = r65.nextDouble()
            float r1 = (float) r1
            r17 = r1
            goto L_0x0066
        L_0x0099:
            double r1 = r65.nextDouble()
            float r1 = (float) r1
            r56 = r1
            goto L_0x0066
        L_0x00a1:
            int r1 = r65.nextInt()
            float r1 = (float) r1
            float r2 = com.airbnb.lottie.utils.Utils.dpScale()
            float r1 = r1 * r2
            int r1 = (int) r1
            r51 = r1
            goto L_0x0066
        L_0x00b0:
            int r1 = r65.nextInt()
            float r1 = (float) r1
            float r2 = com.airbnb.lottie.utils.Utils.dpScale()
            float r1 = r1 * r2
            int r1 = (int) r1
            r50 = r1
            goto L_0x0066
        L_0x00bf:
            double r1 = r65.nextDouble()
            float r1 = (float) r1
            r55 = r1
            goto L_0x0066
        L_0x00c7:
            double r1 = r65.nextDouble()
            float r1 = (float) r1
            r54 = r1
            goto L_0x0066
        L_0x00cf:
            r65.beginArray()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
        L_0x00d7:
            boolean r2 = r65.hasNext()
            if (r2 == 0) goto L_0x011d
            r65.beginObject()
        L_0x00e0:
            boolean r2 = r65.hasNext()
            if (r2 == 0) goto L_0x0119
            com.airbnb.lottie.parser.moshi.JsonReader$Options r2 = EFFECTS_NAMES
            int r2 = r0.selectName(r2)
            switch(r2) {
                case 0: goto L_0x00fe;
                case 1: goto L_0x00f6;
                default: goto L_0x00ef;
            }
        L_0x00ef:
            r65.skipName()
            r65.skipValue()
            goto L_0x00e0
        L_0x00f6:
            java.lang.String r2 = r65.nextString()
            r1.add(r2)
            goto L_0x00e0
        L_0x00fe:
            int r2 = r65.nextInt()
            r3 = 29
            if (r2 != r3) goto L_0x010b
            com.airbnb.lottie.model.content.BlurEffect r58 = com.airbnb.lottie.parser.BlurEffectParser.parse(r65, r66)
            goto L_0x00e0
        L_0x010b:
            r3 = 25
            if (r2 != r3) goto L_0x00e0
            com.airbnb.lottie.parser.DropShadowEffectParser r3 = new com.airbnb.lottie.parser.DropShadowEffectParser
            r3.<init>()
            com.airbnb.lottie.parser.DropShadowEffect r59 = r3.parse(r0, r15)
            goto L_0x00e0
        L_0x0119:
            r65.endObject()
            goto L_0x00d7
        L_0x011d:
            r65.endArray()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r1)
            java.lang.String r2 = r2.toString()
            r15.addWarning(r2)
            goto L_0x0066
        L_0x0138:
            r65.beginObject()
        L_0x013b:
            boolean r1 = r65.hasNext()
            if (r1 == 0) goto L_0x0171
            com.airbnb.lottie.parser.moshi.JsonReader$Options r1 = TEXT_NAMES
            int r1 = r0.selectName(r1)
            switch(r1) {
                case 0: goto L_0x016c;
                case 1: goto L_0x0151;
                default: goto L_0x014a;
            }
        L_0x014a:
            r65.skipName()
            r65.skipValue()
            goto L_0x013b
        L_0x0151:
            r65.beginArray()
            boolean r1 = r65.hasNext()
            if (r1 == 0) goto L_0x015e
            com.airbnb.lottie.model.animatable.AnimatableTextProperties r63 = com.airbnb.lottie.parser.AnimatableTextPropertiesParser.parse(r65, r66)
        L_0x015e:
            boolean r1 = r65.hasNext()
            if (r1 == 0) goto L_0x0168
            r65.skipValue()
            goto L_0x015e
        L_0x0168:
            r65.endArray()
            goto L_0x013b
        L_0x016c:
            com.airbnb.lottie.model.animatable.AnimatableTextFrame r62 = com.airbnb.lottie.parser.AnimatableValueParser.parseDocumentData(r65, r66)
            goto L_0x013b
        L_0x0171:
            r65.endObject()
            goto L_0x0066
        L_0x0176:
            r65.beginArray()
        L_0x0179:
            boolean r1 = r65.hasNext()
            if (r1 == 0) goto L_0x0190
            com.airbnb.lottie.model.content.ContentModel r1 = com.airbnb.lottie.parser.ContentModelParser.parse(r65, r66)
            if (r1 == 0) goto L_0x018b
            r11 = r42
            r11.add(r1)
            goto L_0x018d
        L_0x018b:
            r11 = r42
        L_0x018d:
            r42 = r11
            goto L_0x0179
        L_0x0190:
            r11 = r42
            r65.endArray()
            r10 = r41
            goto L_0x029d
        L_0x0199:
            r11 = r42
            r65.beginArray()
        L_0x019e:
            boolean r1 = r65.hasNext()
            if (r1 == 0) goto L_0x01ae
            com.airbnb.lottie.model.content.Mask r1 = com.airbnb.lottie.parser.MaskParser.parse(r65, r66)
            r10 = r41
            r10.add(r1)
            goto L_0x019e
        L_0x01ae:
            r10 = r41
            int r1 = r10.size()
            r15.incrementMatteOrMaskCount(r1)
            r65.endArray()
            goto L_0x029d
        L_0x01bc:
            r10 = r41
            r11 = r42
            int r1 = r65.nextInt()
            com.airbnb.lottie.model.layer.Layer$MatteType[] r2 = com.airbnb.lottie.model.layer.Layer.MatteType.values()
            int r2 = r2.length
            if (r1 < r2) goto L_0x01e3
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unsupported matte type: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r1)
            java.lang.String r2 = r2.toString()
            r15.addWarning(r2)
            goto L_0x029d
        L_0x01e3:
            com.airbnb.lottie.model.layer.Layer$MatteType[] r2 = com.airbnb.lottie.model.layer.Layer.MatteType.values()
            r60 = r2[r1]
            int[] r2 = com.airbnb.lottie.parser.LayerParser.AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType
            int r3 = r60.ordinal()
            r2 = r2[r3]
            switch(r2) {
                case 1: goto L_0x01fb;
                case 2: goto L_0x01f5;
                default: goto L_0x01f4;
            }
        L_0x01f4:
            goto L_0x0201
        L_0x01f5:
            java.lang.String r2 = "Unsupported matte type: Luma Inverted"
            r15.addWarning(r2)
            goto L_0x0201
        L_0x01fb:
            java.lang.String r2 = "Unsupported matte type: Luma"
            r15.addWarning(r2)
        L_0x0201:
            r2 = 1
            r15.incrementMatteOrMaskCount(r2)
            r41 = r10
            r42 = r11
            goto L_0x0066
        L_0x020b:
            r10 = r41
            r11 = r42
            com.airbnb.lottie.model.animatable.AnimatableTransform r61 = com.airbnb.lottie.parser.AnimatableTransformParser.parse(r65, r66)
            goto L_0x0066
        L_0x0215:
            r10 = r41
            r11 = r42
            java.lang.String r1 = r65.nextString()
            int r49 = android.graphics.Color.parseColor(r1)
            goto L_0x0066
        L_0x0223:
            r10 = r41
            r11 = r42
            int r1 = r65.nextInt()
            float r1 = (float) r1
            float r2 = com.airbnb.lottie.utils.Utils.dpScale()
            float r1 = r1 * r2
            int r1 = (int) r1
            r48 = r1
            goto L_0x0066
        L_0x0237:
            r10 = r41
            r11 = r42
            int r1 = r65.nextInt()
            float r1 = (float) r1
            float r2 = com.airbnb.lottie.utils.Utils.dpScale()
            float r1 = r1 * r2
            int r1 = (int) r1
            r47 = r1
            goto L_0x0066
        L_0x024b:
            r10 = r41
            r11 = r42
            int r1 = r65.nextInt()
            long r1 = (long) r1
            r52 = r1
            goto L_0x0066
        L_0x0258:
            r10 = r41
            r11 = r42
            int r1 = r65.nextInt()
            com.airbnb.lottie.model.layer.Layer$LayerType r2 = com.airbnb.lottie.model.layer.Layer.LayerType.UNKNOWN
            int r2 = r2.ordinal()
            if (r1 >= r2) goto L_0x0274
            com.airbnb.lottie.model.layer.Layer$LayerType[] r2 = com.airbnb.lottie.model.layer.Layer.LayerType.values()
            r43 = r2[r1]
            r41 = r10
            r42 = r11
            goto L_0x0066
        L_0x0274:
            com.airbnb.lottie.model.layer.Layer$LayerType r43 = com.airbnb.lottie.model.layer.Layer.LayerType.UNKNOWN
            r41 = r10
            r42 = r11
            goto L_0x0066
        L_0x027c:
            r10 = r41
            r11 = r42
            java.lang.String r44 = r65.nextString()
            goto L_0x0066
        L_0x0286:
            r10 = r41
            r11 = r42
            int r1 = r65.nextInt()
            long r1 = (long) r1
            r45 = r1
            goto L_0x0066
        L_0x0293:
            r10 = r41
            r11 = r42
            java.lang.String r14 = r65.nextString()
            goto L_0x0066
        L_0x029d:
            r41 = r10
            r42 = r11
            goto L_0x0066
        L_0x02a3:
            r10 = r41
            r11 = r42
            r65.endObject()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r9 = r1
            r8 = 0
            int r1 = (r56 > r8 ? 1 : (r56 == r8 ? 0 : -1))
            if (r1 <= 0) goto L_0x02ce
            com.airbnb.lottie.value.Keyframe r13 = new com.airbnb.lottie.value.Keyframe
            java.lang.Float r3 = java.lang.Float.valueOf(r8)
            java.lang.Float r4 = java.lang.Float.valueOf(r8)
            r5 = 0
            r6 = 0
            java.lang.Float r7 = java.lang.Float.valueOf(r56)
            r1 = r13
            r2 = r66
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r9.add(r1)
        L_0x02ce:
            int r1 = (r17 > r8 ? 1 : (r17 == r8 ? 0 : -1))
            if (r1 <= 0) goto L_0x02d5
            r13 = r17
            goto L_0x02da
        L_0x02d5:
            float r1 = r66.getEndFrame()
            r13 = r1
        L_0x02da:
            com.airbnb.lottie.value.Keyframe r16 = new com.airbnb.lottie.value.Keyframe
            r1 = 1065353216(0x3f800000, float:1.0)
            java.lang.Float r3 = java.lang.Float.valueOf(r1)
            java.lang.Float r4 = java.lang.Float.valueOf(r1)
            r5 = 0
            java.lang.Float r7 = java.lang.Float.valueOf(r13)
            r1 = r16
            r2 = r66
            r6 = r56
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r9.add(r1)
            com.airbnb.lottie.value.Keyframe r2 = new com.airbnb.lottie.value.Keyframe
            java.lang.Float r3 = java.lang.Float.valueOf(r8)
            java.lang.Float r4 = java.lang.Float.valueOf(r8)
            r6 = 2139095039(0x7f7fffff, float:3.4028235E38)
            java.lang.Float r6 = java.lang.Float.valueOf(r6)
            r8 = r2
            r7 = r9
            r9 = r66
            r41 = r10
            r10 = r3
            r3 = r11
            r11 = r4
            r4 = r12
            r12 = r5
            r5 = r14
            r14 = r6
            r8.<init>(r9, r10, r11, r12, r13, r14)
            r7.add(r2)
            java.lang.String r6 = ".ai"
            boolean r6 = r5.endsWith(r6)
            if (r6 != 0) goto L_0x032b
            java.lang.String r6 = "ai"
            boolean r6 = r6.equals(r4)
            if (r6 == 0) goto L_0x0330
        L_0x032b:
            java.lang.String r6 = "Convert your Illustrator layers to shape layers."
            r15.addWarning(r6)
        L_0x0330:
            com.airbnb.lottie.model.layer.Layer r6 = new com.airbnb.lottie.model.layer.Layer
            r14 = r6
            r15 = r3
            r16 = r66
            r17 = r5
            r18 = r45
            r20 = r43
            r21 = r52
            r23 = r44
            r24 = r41
            r25 = r61
            r26 = r47
            r27 = r48
            r28 = r49
            r29 = r54
            r30 = r55
            r31 = r50
            r32 = r51
            r33 = r62
            r34 = r63
            r35 = r7
            r36 = r60
            r37 = r64
            r38 = r57
            r39 = r58
            r40 = r59
            r14.<init>(r15, r16, r17, r18, r20, r21, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.LayerParser.parse(com.airbnb.lottie.parser.moshi.JsonReader, com.airbnb.lottie.LottieComposition):com.airbnb.lottie.model.layer.Layer");
    }

    /* renamed from: com.airbnb.lottie.parser.LayerParser$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType;

        static {
            int[] iArr = new int[Layer.MatteType.values().length];
            $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType = iArr;
            try {
                iArr[Layer.MatteType.LUMA.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType[Layer.MatteType.LUMA_INVERTED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }
}
