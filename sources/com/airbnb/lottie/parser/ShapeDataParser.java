package com.airbnb.lottie.parser;

import android.graphics.PointF;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShapeDataParser implements ValueParser<ShapeData> {
    public static final ShapeDataParser INSTANCE = new ShapeDataParser();
    private static final JsonReader.Options NAMES = JsonReader.Options.of("c", "v", "i", "o");

    private ShapeDataParser() {
    }

    public ShapeData parse(JsonReader reader, float scale) throws IOException {
        if (reader.peek() == JsonReader.Token.BEGIN_ARRAY) {
            reader.beginArray();
        }
        boolean closed = false;
        List<PointF> pointsArray = null;
        List<PointF> inTangents = null;
        List<PointF> outTangents = null;
        reader.beginObject();
        while (reader.hasNext()) {
            switch (reader.selectName(NAMES)) {
                case 0:
                    closed = reader.nextBoolean();
                    break;
                case 1:
                    pointsArray = JsonUtils.jsonToPoints(reader, scale);
                    break;
                case 2:
                    inTangents = JsonUtils.jsonToPoints(reader, scale);
                    break;
                case 3:
                    outTangents = JsonUtils.jsonToPoints(reader, scale);
                    break;
                default:
                    reader.skipName();
                    reader.skipValue();
                    break;
            }
        }
        JsonReader jsonReader = reader;
        reader.endObject();
        if (reader.peek() == JsonReader.Token.END_ARRAY) {
            reader.endArray();
        }
        if (pointsArray == null || inTangents == null || outTangents == null) {
            throw new IllegalArgumentException("Shape data was missing information.");
        } else if (pointsArray.isEmpty()) {
            return new ShapeData(new PointF(), false, Collections.emptyList());
        } else {
            int length = pointsArray.size();
            PointF initialPoint = pointsArray.get(0);
            List<CubicCurveData> curves = new ArrayList<>(length);
            for (int i = 1; i < length; i++) {
                PointF vertex = pointsArray.get(i);
                curves.add(new CubicCurveData(MiscUtils.addPoints(pointsArray.get(i - 1), outTangents.get(i - 1)), MiscUtils.addPoints(vertex, inTangents.get(i)), vertex));
            }
            if (closed) {
                PointF vertex2 = pointsArray.get(0);
                curves.add(new CubicCurveData(MiscUtils.addPoints(pointsArray.get(length - 1), outTangents.get(length - 1)), MiscUtils.addPoints(vertex2, inTangents.get(0)), vertex2));
            }
            return new ShapeData(initialPoint, closed, curves);
        }
    }
}
