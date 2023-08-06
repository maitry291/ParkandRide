package com.airbnb.lottie.parser;

import android.graphics.Path;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.content.GradientFill;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.Collections;

class GradientFillParser {
    private static final JsonReader.Options GRADIENT_NAMES = JsonReader.Options.of("p", "k");
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "g", "o", "t", "s", "e", "r", "hd");

    private GradientFillParser() {
    }

    static GradientFill parse(JsonReader reader, LottieComposition composition) throws IOException {
        JsonReader jsonReader = reader;
        String name = null;
        AnimatableGradientColorValue color = null;
        AnimatableIntegerValue opacity = null;
        GradientType gradientType = null;
        AnimatablePointValue startPoint = null;
        AnimatablePointValue endPoint = null;
        Path.FillType fillType = Path.FillType.WINDING;
        boolean hidden = false;
        while (reader.hasNext()) {
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    LottieComposition lottieComposition = composition;
                    name = reader.nextString();
                    break;
                case 1:
                    int points = -1;
                    reader.beginObject();
                    while (reader.hasNext()) {
                        switch (jsonReader.selectName(GRADIENT_NAMES)) {
                            case 0:
                                LottieComposition lottieComposition2 = composition;
                                points = reader.nextInt();
                                break;
                            case 1:
                                color = AnimatableValueParser.parseGradientColor(jsonReader, composition, points);
                                break;
                            default:
                                LottieComposition lottieComposition3 = composition;
                                reader.skipName();
                                reader.skipValue();
                                break;
                        }
                    }
                    LottieComposition lottieComposition4 = composition;
                    reader.endObject();
                    break;
                case 2:
                    opacity = AnimatableValueParser.parseInteger(reader, composition);
                    break;
                case 3:
                    gradientType = reader.nextInt() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    break;
                case 4:
                    startPoint = AnimatableValueParser.parsePoint(reader, composition);
                    break;
                case 5:
                    endPoint = AnimatableValueParser.parsePoint(reader, composition);
                    break;
                case 6:
                    fillType = reader.nextInt() == 1 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD;
                    break;
                case 7:
                    hidden = reader.nextBoolean();
                    break;
                default:
                    LottieComposition lottieComposition5 = composition;
                    reader.skipName();
                    reader.skipValue();
                    break;
            }
        }
        LottieComposition lottieComposition6 = composition;
        return new GradientFill(name, gradientType, fillType, color, opacity == null ? new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100))) : opacity, startPoint, endPoint, (AnimatableFloatValue) null, (AnimatableFloatValue) null, hidden);
    }
}
