package com.airbnb.lottie.parser;

import android.graphics.Color;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.IOException;

public class ColorParser implements ValueParser<Integer> {
    public static final ColorParser INSTANCE = new ColorParser();

    private ColorParser() {
    }

    public Integer parse(JsonReader reader, float scale) throws IOException {
        boolean isArray = reader.peek() == JsonReader.Token.BEGIN_ARRAY;
        if (isArray) {
            reader.beginArray();
        }
        double r = reader.nextDouble();
        double g = reader.nextDouble();
        double b = reader.nextDouble();
        double a = 1.0d;
        if (reader.peek() == JsonReader.Token.NUMBER) {
            a = reader.nextDouble();
        }
        if (isArray) {
            reader.endArray();
        }
        if (r <= 1.0d && g <= 1.0d && b <= 1.0d) {
            r *= 255.0d;
            g *= 255.0d;
            b *= 255.0d;
            if (a <= 1.0d) {
                a *= 255.0d;
            }
        }
        return Integer.valueOf(Color.argb((int) a, (int) r, (int) g, (int) b));
    }
}
