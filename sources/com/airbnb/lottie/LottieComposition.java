package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.MiscUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class LottieComposition {
    private Rect bounds;
    private SparseArrayCompat<FontCharacter> characters;
    private float endFrame;
    private Map<String, Font> fonts;
    private float frameRate;
    private boolean hasDashPattern;
    private Map<String, LottieImageAsset> images;
    private LongSparseArray<Layer> layerMap;
    private List<Layer> layers;
    private List<Marker> markers;
    private int maskAndMatteCount = 0;
    private final PerformanceTracker performanceTracker = new PerformanceTracker();
    private Map<String, List<Layer>> precomps;
    private float startFrame;
    private final HashSet<String> warnings = new HashSet<>();

    public void init(Rect bounds2, float startFrame2, float endFrame2, float frameRate2, List<Layer> layers2, LongSparseArray<Layer> layerMap2, Map<String, List<Layer>> precomps2, Map<String, LottieImageAsset> images2, SparseArrayCompat<FontCharacter> characters2, Map<String, Font> fonts2, List<Marker> markers2) {
        this.bounds = bounds2;
        this.startFrame = startFrame2;
        this.endFrame = endFrame2;
        this.frameRate = frameRate2;
        this.layers = layers2;
        this.layerMap = layerMap2;
        this.precomps = precomps2;
        this.images = images2;
        this.characters = characters2;
        this.fonts = fonts2;
        this.markers = markers2;
    }

    public void addWarning(String warning) {
        Logger.warning(warning);
        this.warnings.add(warning);
    }

    public void setHasDashPattern(boolean hasDashPattern2) {
        this.hasDashPattern = hasDashPattern2;
    }

    public void incrementMatteOrMaskCount(int amount) {
        this.maskAndMatteCount += amount;
    }

    public boolean hasDashPattern() {
        return this.hasDashPattern;
    }

    public int getMaskAndMatteCount() {
        return this.maskAndMatteCount;
    }

    public ArrayList<String> getWarnings() {
        HashSet<String> hashSet = this.warnings;
        return new ArrayList<>(Arrays.asList((String[]) hashSet.toArray(new String[hashSet.size()])));
    }

    public void setPerformanceTrackingEnabled(boolean enabled) {
        this.performanceTracker.setEnabled(enabled);
    }

    public PerformanceTracker getPerformanceTracker() {
        return this.performanceTracker;
    }

    public Layer layerModelForId(long id) {
        return this.layerMap.get(id);
    }

    public Rect getBounds() {
        return this.bounds;
    }

    public float getDuration() {
        return (float) ((long) ((getDurationFrames() / this.frameRate) * 1000.0f));
    }

    public float getStartFrame() {
        return this.startFrame;
    }

    public float getEndFrame() {
        return this.endFrame;
    }

    public float getFrameForProgress(float progress) {
        return MiscUtils.lerp(this.startFrame, this.endFrame, progress);
    }

    public float getProgressForFrame(float frame) {
        float f = this.startFrame;
        return (frame - f) / (this.endFrame - f);
    }

    public float getFrameRate() {
        return this.frameRate;
    }

    public List<Layer> getLayers() {
        return this.layers;
    }

    public List<Layer> getPrecomps(String id) {
        return this.precomps.get(id);
    }

    public SparseArrayCompat<FontCharacter> getCharacters() {
        return this.characters;
    }

    public Map<String, Font> getFonts() {
        return this.fonts;
    }

    public List<Marker> getMarkers() {
        return this.markers;
    }

    public Marker getMarker(String markerName) {
        int size = this.markers.size();
        for (int i = 0; i < size; i++) {
            Marker marker = this.markers.get(i);
            if (marker.matchesName(markerName)) {
                return marker;
            }
        }
        return null;
    }

    public boolean hasImages() {
        return !this.images.isEmpty();
    }

    public Map<String, LottieImageAsset> getImages() {
        return this.images;
    }

    public float getDurationFrames() {
        return this.endFrame - this.startFrame;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        for (Layer layer : this.layers) {
            sb.append(layer.toString("\t"));
        }
        return sb.toString();
    }

    @Deprecated
    public static class Factory {
        private Factory() {
        }

        @Deprecated
        public static Cancellable fromAssetFileName(Context context, String fileName, OnCompositionLoadedListener l) {
            ListenerAdapter listener = new ListenerAdapter(l);
            LottieCompositionFactory.fromAsset(context, fileName).addListener(listener);
            return listener;
        }

        @Deprecated
        public static Cancellable fromRawFile(Context context, int resId, OnCompositionLoadedListener l) {
            ListenerAdapter listener = new ListenerAdapter(l);
            LottieCompositionFactory.fromRawRes(context, resId).addListener(listener);
            return listener;
        }

        @Deprecated
        public static Cancellable fromInputStream(InputStream stream, OnCompositionLoadedListener l) {
            ListenerAdapter listener = new ListenerAdapter(l);
            LottieCompositionFactory.fromJsonInputStream(stream, (String) null).addListener(listener);
            return listener;
        }

        @Deprecated
        public static Cancellable fromJsonString(String jsonString, OnCompositionLoadedListener l) {
            ListenerAdapter listener = new ListenerAdapter(l);
            LottieCompositionFactory.fromJsonString(jsonString, (String) null).addListener(listener);
            return listener;
        }

        @Deprecated
        public static Cancellable fromJsonReader(JsonReader reader, OnCompositionLoadedListener l) {
            ListenerAdapter listener = new ListenerAdapter(l);
            LottieCompositionFactory.fromJsonReader(reader, (String) null).addListener(listener);
            return listener;
        }

        @Deprecated
        public static LottieComposition fromFileSync(Context context, String fileName) {
            return LottieCompositionFactory.fromAssetSync(context, fileName).getValue();
        }

        @Deprecated
        public static LottieComposition fromInputStreamSync(InputStream stream) {
            return LottieCompositionFactory.fromJsonInputStreamSync(stream, (String) null).getValue();
        }

        @Deprecated
        public static LottieComposition fromInputStreamSync(InputStream stream, boolean close) {
            if (close) {
                Logger.warning("Lottie now auto-closes input stream!");
            }
            return LottieCompositionFactory.fromJsonInputStreamSync(stream, (String) null).getValue();
        }

        @Deprecated
        public static LottieComposition fromJsonSync(Resources res, JSONObject json) {
            return LottieCompositionFactory.fromJsonSync(json, (String) null).getValue();
        }

        @Deprecated
        public static LottieComposition fromJsonSync(String json) {
            return LottieCompositionFactory.fromJsonStringSync(json, (String) null).getValue();
        }

        @Deprecated
        public static LottieComposition fromJsonSync(JsonReader reader) {
            return LottieCompositionFactory.fromJsonReaderSync(reader, (String) null).getValue();
        }

        private static final class ListenerAdapter implements LottieListener<LottieComposition>, Cancellable {
            private boolean cancelled;
            private final OnCompositionLoadedListener listener;

            private ListenerAdapter(OnCompositionLoadedListener listener2) {
                this.cancelled = false;
                this.listener = listener2;
            }

            public void onResult(LottieComposition composition) {
                if (!this.cancelled) {
                    this.listener.onCompositionLoaded(composition);
                }
            }

            public void cancel() {
                this.cancelled = true;
            }
        }
    }
}
