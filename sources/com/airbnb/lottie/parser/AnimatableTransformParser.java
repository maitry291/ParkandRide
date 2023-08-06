package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePathValue;
import com.airbnb.lottie.model.animatable.AnimatableScaleValue;
import com.airbnb.lottie.model.animatable.AnimatableSplitDimensionPathValue;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.ScaleXY;
import java.io.IOException;

public class AnimatableTransformParser {
    private static final JsonReader.Options ANIMATABLE_NAMES = JsonReader.Options.of("k");
    private static final JsonReader.Options NAMES = JsonReader.Options.of("a", "p", "s", "rz", "r", "o", "so", "eo", "sk", "sa");

    private AnimatableTransformParser() {
    }

    public static AnimatableTransform parse(JsonReader reader, LottieComposition composition) throws IOException {
        AnimatableFloatValue skew;
        AnimatableFloatValue skewAngle;
        JsonReader jsonReader = reader;
        LottieComposition lottieComposition = composition;
        AnimatableFloatValue rotation = null;
        boolean z = false;
        boolean isObject = reader.peek() == JsonReader.Token.BEGIN_OBJECT;
        if (isObject) {
            reader.beginObject();
        }
        AnimatableScaleValue scale = null;
        AnimatableIntegerValue opacity = null;
        AnimatableFloatValue startOpacity = null;
        AnimatableFloatValue endOpacity = null;
        AnimatableFloatValue skew2 = null;
        AnimatableFloatValue skewAngle2 = null;
        AnimatablePathValue anchorPoint = null;
        AnimatableValue<PointF, PointF> position = null;
        while (reader.hasNext()) {
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    reader.beginObject();
                    while (reader.hasNext()) {
                        switch (jsonReader.selectName(ANIMATABLE_NAMES)) {
                            case 0:
                                anchorPoint = AnimatablePathValueParser.parse(reader, composition);
                                break;
                            default:
                                reader.skipName();
                                reader.skipValue();
                                break;
                        }
                    }
                    reader.endObject();
                    lottieComposition = composition;
                    z = false;
                    continue;
                case 1:
                    position = AnimatablePathValueParser.parseSplitPath(reader, composition);
                    lottieComposition = composition;
                    z = false;
                    continue;
                case 2:
                    scale = AnimatableValueParser.parseScale(reader, composition);
                    lottieComposition = composition;
                    z = false;
                    continue;
                case 3:
                    lottieComposition.addWarning("Lottie doesn't support 3D layers.");
                    break;
                case 4:
                    break;
                case 5:
                    opacity = AnimatableValueParser.parseInteger(reader, composition);
                    continue;
                case 6:
                    startOpacity = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, z);
                    continue;
                case 7:
                    endOpacity = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, z);
                    continue;
                case 8:
                    skew2 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, z);
                    continue;
                case 9:
                    skewAngle2 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, z);
                    continue;
                default:
                    reader.skipName();
                    reader.skipValue();
                    lottieComposition = composition;
                    z = false;
                    continue;
            }
            AnimatableFloatValue rotation2 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, z);
            if (rotation2.getKeyframes().isEmpty()) {
                Keyframe keyframe = r1;
                Keyframe keyframe2 = new Keyframe(composition, Float.valueOf(0.0f), Float.valueOf(0.0f), (Interpolator) null, 0.0f, Float.valueOf(composition.getEndFrame()));
                rotation2.getKeyframes().add(keyframe);
            } else if (((Keyframe) rotation2.getKeyframes().get(0)).startValue == null) {
                rotation2.getKeyframes().set(0, new Keyframe(composition, Float.valueOf(0.0f), Float.valueOf(0.0f), (Interpolator) null, 0.0f, Float.valueOf(composition.getEndFrame())));
            }
            lottieComposition = composition;
            rotation = rotation2;
            z = false;
        }
        if (isObject) {
            reader.endObject();
        }
        if (isAnchorPointIdentity(anchorPoint)) {
            anchorPoint = null;
        }
        if (isPositionIdentity(position)) {
            position = null;
        }
        if (isRotationIdentity(rotation)) {
            rotation = null;
        }
        if (isScaleIdentity(scale)) {
            scale = null;
        }
        if (isSkewIdentity(skew2)) {
            skew = null;
        } else {
            skew = skew2;
        }
        if (isSkewAngleIdentity(skewAngle2)) {
            skewAngle = null;
        } else {
            skewAngle = skewAngle2;
        }
        return new AnimatableTransform(anchorPoint, position, scale, rotation, opacity, startOpacity, endOpacity, skew, skewAngle);
    }

    private static boolean isAnchorPointIdentity(AnimatablePathValue anchorPoint) {
        return anchorPoint == null || (anchorPoint.isStatic() && ((PointF) anchorPoint.getKeyframes().get(0).startValue).equals(0.0f, 0.0f));
    }

    private static boolean isPositionIdentity(AnimatableValue<PointF, PointF> position) {
        if (position == null || (!(position instanceof AnimatableSplitDimensionPathValue) && position.isStatic() && ((PointF) position.getKeyframes().get(0).startValue).equals(0.0f, 0.0f))) {
            return true;
        }
        return false;
    }

    private static boolean isRotationIdentity(AnimatableFloatValue rotation) {
        return rotation == null || (rotation.isStatic() && ((Float) ((Keyframe) rotation.getKeyframes().get(0)).startValue).floatValue() == 0.0f);
    }

    private static boolean isScaleIdentity(AnimatableScaleValue scale) {
        return scale == null || (scale.isStatic() && ((ScaleXY) ((Keyframe) scale.getKeyframes().get(0)).startValue).equals(1.0f, 1.0f));
    }

    private static boolean isSkewIdentity(AnimatableFloatValue skew) {
        return skew == null || (skew.isStatic() && ((Float) ((Keyframe) skew.getKeyframes().get(0)).startValue).floatValue() == 0.0f);
    }

    private static boolean isSkewAngleIdentity(AnimatableFloatValue skewAngle) {
        return skewAngle == null || (skewAngle.isStatic() && ((Float) ((Keyframe) skewAngle.getKeyframes().get(0)).startValue).floatValue() == 0.0f);
    }
}
