package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.animation.keyframe.PathKeyframe;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class KeyframesParser {
    static JsonReader.Options NAMES = JsonReader.Options.of("k");

    private KeyframesParser() {
    }

    static <T> List<Keyframe<T>> parse(JsonReader reader, LottieComposition composition, float scale, ValueParser<T> valueParser, boolean multiDimensional) throws IOException {
        List<Keyframe<T>> keyframes = new ArrayList<>();
        if (reader.peek() == JsonReader.Token.STRING) {
            composition.addWarning("Lottie doesn't support expressions.");
            return keyframes;
        }
        reader.beginObject();
        while (reader.hasNext()) {
            switch (reader.selectName(NAMES)) {
                case 0:
                    if (reader.peek() != JsonReader.Token.BEGIN_ARRAY) {
                        keyframes.add(KeyframeParser.parse(reader, composition, scale, valueParser, false, multiDimensional));
                        break;
                    } else {
                        reader.beginArray();
                        if (reader.peek() == JsonReader.Token.NUMBER) {
                            keyframes.add(KeyframeParser.parse(reader, composition, scale, valueParser, false, multiDimensional));
                        } else {
                            while (reader.hasNext()) {
                                keyframes.add(KeyframeParser.parse(reader, composition, scale, valueParser, true, multiDimensional));
                            }
                        }
                        reader.endArray();
                        break;
                    }
                default:
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
        setEndFrames(keyframes);
        return keyframes;
    }

    public static <T> void setEndFrames(List<? extends Keyframe<T>> keyframes) {
        int size = keyframes.size();
        for (int i = 0; i < size - 1; i++) {
            Keyframe<T> keyframe = (Keyframe) keyframes.get(i);
            Keyframe<T> nextKeyframe = (Keyframe) keyframes.get(i + 1);
            keyframe.endFrame = Float.valueOf(nextKeyframe.startFrame);
            if (keyframe.endValue == null && nextKeyframe.startValue != null) {
                keyframe.endValue = nextKeyframe.startValue;
                if (keyframe instanceof PathKeyframe) {
                    ((PathKeyframe) keyframe).createPath();
                }
            }
        }
        Keyframe<?> lastKeyframe = (Keyframe) keyframes.get(size - 1);
        if ((lastKeyframe.startValue == null || lastKeyframe.endValue == null) && keyframes.size() > 1) {
            keyframes.remove(lastKeyframe);
        }
    }
}
