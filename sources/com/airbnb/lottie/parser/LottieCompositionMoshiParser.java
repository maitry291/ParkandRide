package com.airbnb.lottie.parser;

import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottieCompositionMoshiParser {
    static JsonReader.Options ASSETS_NAMES = JsonReader.Options.of("id", "layers", "w", "h", "p", "u");
    private static final JsonReader.Options FONT_NAMES = JsonReader.Options.of("list");
    private static final JsonReader.Options MARKER_NAMES = JsonReader.Options.of("cm", "tm", "dr");
    private static final JsonReader.Options NAMES = JsonReader.Options.of("w", "h", "ip", "op", "fr", "v", "layers", "assets", "fonts", "chars", "markers");

    public static LottieComposition parse(JsonReader reader) throws IOException {
        SparseArrayCompat<FontCharacter> characters;
        List<Marker> markers;
        JsonReader jsonReader = reader;
        float scale = Utils.dpScale();
        float startFrame = 0.0f;
        float endFrame = 0.0f;
        float frameRate = 0.0f;
        LongSparseArray<Layer> layerMap = new LongSparseArray<>();
        ArrayList arrayList = new ArrayList();
        Map<String, LottieImageAsset> images = new HashMap<>();
        Map<String, LottieImageAsset> images2 = new HashMap<>();
        Map<String, Font> fonts = new HashMap<>();
        List<Marker> markers2 = new ArrayList<>();
        SparseArrayCompat<FontCharacter> characters2 = new SparseArrayCompat<>();
        LottieComposition composition = new LottieComposition();
        reader.beginObject();
        int width = 0;
        int height = 0;
        while (true) {
            float frameRate2 = frameRate;
            if (reader.hasNext() != 0) {
                switch (jsonReader.selectName(NAMES)) {
                    case 0:
                        List<Marker> list = markers2;
                        width = reader.nextInt();
                        frameRate = frameRate2;
                        jsonReader = reader;
                        continue;
                    case 1:
                        List<Marker> list2 = markers2;
                        height = reader.nextInt();
                        frameRate = frameRate2;
                        jsonReader = reader;
                        continue;
                    case 2:
                        startFrame = (float) reader.nextDouble();
                        markers2 = markers2;
                        characters2 = characters2;
                        frameRate = frameRate2;
                        jsonReader = reader;
                        continue;
                    case 3:
                        endFrame = ((float) reader.nextDouble()) - 0.01f;
                        markers2 = markers2;
                        characters2 = characters2;
                        frameRate = frameRate2;
                        jsonReader = reader;
                        continue;
                    case 4:
                        markers2 = markers2;
                        characters2 = characters2;
                        frameRate = (float) reader.nextDouble();
                        jsonReader = reader;
                        continue;
                    case 5:
                        String[] versions = reader.nextString().split("\\.");
                        if (Utils.isAtLeastVersion(Integer.parseInt(versions[0]), Integer.parseInt(versions[1]), Integer.parseInt(versions[2]), 4, 4, 0)) {
                            characters = characters2;
                            markers = markers2;
                            break;
                        } else {
                            composition.addWarning("Lottie only supports bodymovin >= 4.4.0");
                            characters = characters2;
                            markers = markers2;
                            break;
                        }
                    case 6:
                        parseLayers(jsonReader, composition, arrayList, layerMap);
                        characters = characters2;
                        markers = markers2;
                        break;
                    case 7:
                        parseAssets(jsonReader, composition, images, images2);
                        characters = characters2;
                        markers = markers2;
                        break;
                    case 8:
                        parseFonts(jsonReader, fonts);
                        characters = characters2;
                        markers = markers2;
                        break;
                    case 9:
                        parseChars(jsonReader, composition, characters2);
                        characters = characters2;
                        markers = markers2;
                        break;
                    case 10:
                        parseMarkers(jsonReader, markers2);
                        characters = characters2;
                        markers = markers2;
                        break;
                    default:
                        characters = characters2;
                        markers = markers2;
                        reader.skipName();
                        reader.skipValue();
                        break;
                }
                markers2 = markers;
                characters2 = characters;
                frameRate = frameRate2;
                jsonReader = reader;
            } else {
                SparseArrayCompat<FontCharacter> characters3 = characters2;
                List<Marker> list3 = markers2;
                int scaledWidth = (int) (((float) width) * scale);
                int scaledHeight = (int) (((float) height) * scale);
                List<Marker> list4 = list3;
                int i = height;
                int i2 = width;
                LottieComposition composition2 = composition;
                int i3 = scaledHeight;
                int i4 = scaledWidth;
                List<Marker> list5 = list4;
                Map<String, LottieImageAsset> map = images;
                ArrayList arrayList2 = arrayList;
                composition.init(new Rect(0, 0, scaledWidth, scaledHeight), startFrame, endFrame, frameRate2, arrayList, layerMap, images, images2, characters3, fonts, list4);
                return composition2;
            }
        }
    }

    private static void parseLayers(JsonReader reader, LottieComposition composition, List<Layer> layers, LongSparseArray<Layer> layerMap) throws IOException {
        int imageCount = 0;
        reader.beginArray();
        while (reader.hasNext()) {
            Layer layer = LayerParser.parse(reader, composition);
            if (layer.getLayerType() == Layer.LayerType.IMAGE) {
                imageCount++;
            }
            layers.add(layer);
            layerMap.put(layer.getId(), layer);
            if (imageCount > 4) {
                Logger.warning("You have " + imageCount + " images. Lottie should primarily be used with shapes. If you are using Adobe Illustrator, convert the Illustrator layers to shape layers.");
            }
        }
        reader.endArray();
    }

    private static void parseAssets(JsonReader reader, LottieComposition composition, Map<String, List<Layer>> precomps, Map<String, LottieImageAsset> images) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            String id = null;
            List<Layer> layers = new ArrayList<>();
            LongSparseArray<Layer> layerMap = new LongSparseArray<>();
            int width = 0;
            int height = 0;
            String imageFileName = null;
            String relativeFolder = null;
            reader.beginObject();
            while (reader.hasNext()) {
                JsonReader jsonReader = reader;
                switch (reader.selectName(ASSETS_NAMES)) {
                    case 0:
                        id = reader.nextString();
                        break;
                    case 1:
                        reader.beginArray();
                        while (reader.hasNext()) {
                            Layer layer = LayerParser.parse(reader, composition);
                            layerMap.put(layer.getId(), layer);
                            layers.add(layer);
                        }
                        reader.endArray();
                        break;
                    case 2:
                        width = reader.nextInt();
                        break;
                    case 3:
                        height = reader.nextInt();
                        break;
                    case 4:
                        imageFileName = reader.nextString();
                        break;
                    case 5:
                        relativeFolder = reader.nextString();
                        break;
                    default:
                        reader.skipName();
                        reader.skipValue();
                        break;
                }
            }
            JsonReader jsonReader2 = reader;
            reader.endObject();
            if (imageFileName != null) {
                LottieImageAsset image = new LottieImageAsset(width, height, id, imageFileName, relativeFolder);
                images.put(image.getId(), image);
                Map<String, List<Layer>> map = precomps;
            } else {
                Map<String, LottieImageAsset> map2 = images;
                precomps.put(id, layers);
            }
        }
        JsonReader jsonReader3 = reader;
        Map<String, List<Layer>> map3 = precomps;
        Map<String, LottieImageAsset> map4 = images;
        reader.endArray();
    }

    private static void parseFonts(JsonReader reader, Map<String, Font> fonts) throws IOException {
        reader.beginObject();
        while (reader.hasNext()) {
            switch (reader.selectName(FONT_NAMES)) {
                case 0:
                    reader.beginArray();
                    while (reader.hasNext()) {
                        Font font = FontParser.parse(reader);
                        fonts.put(font.getName(), font);
                    }
                    reader.endArray();
                    break;
                default:
                    reader.skipName();
                    reader.skipValue();
                    break;
            }
        }
        reader.endObject();
    }

    private static void parseChars(JsonReader reader, LottieComposition composition, SparseArrayCompat<FontCharacter> characters) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            FontCharacter character = FontCharacterParser.parse(reader, composition);
            characters.put(character.hashCode(), character);
        }
        reader.endArray();
    }

    private static void parseMarkers(JsonReader reader, List<Marker> markers) throws IOException {
        reader.beginArray();
        while (reader.hasNext()) {
            String comment = null;
            float frame = 0.0f;
            float durationFrames = 0.0f;
            reader.beginObject();
            while (reader.hasNext()) {
                switch (reader.selectName(MARKER_NAMES)) {
                    case 0:
                        comment = reader.nextString();
                        break;
                    case 1:
                        frame = (float) reader.nextDouble();
                        break;
                    case 2:
                        durationFrames = (float) reader.nextDouble();
                        break;
                    default:
                        reader.skipName();
                        reader.skipValue();
                        break;
                }
            }
            reader.endObject();
            markers.add(new Marker(comment, frame, durationFrames));
        }
        reader.endArray();
    }
}
