package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.ScaleXY;
import java.io.IOException;

public class ScaleXYParser implements ValueParser<ScaleXY> {
    public static final ScaleXYParser INSTANCE = new ScaleXYParser();

    private ScaleXYParser() {
    }

    public ScaleXY parse(JsonReader reader, float scale) throws IOException {
        boolean isArray = reader.peek() == JsonReader.Token.BEGIN_ARRAY;
        if (isArray) {
            reader.beginArray();
        }
        float sx = (float) reader.nextDouble();
        float sy = (float) reader.nextDouble();
        while (reader.hasNext()) {
            reader.skipValue();
        }
        if (isArray) {
            reader.endArray();
        }
        return new ScaleXY((sx / 100.0f) * scale, (sy / 100.0f) * scale);
    }
}