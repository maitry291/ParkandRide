package com.airbnb.lottie;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.airbnb.lottie.model.LottieCompositionCache;
import com.airbnb.lottie.parser.LottieCompositionMoshiParser;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import okio.BufferedSource;
import okio.Okio;
import org.json.JSONObject;

public class LottieCompositionFactory {
    private static final byte[] MAGIC = {80, 75, 3, 4};
    private static final Map<String, LottieTask<LottieComposition>> taskCache = new HashMap();

    private LottieCompositionFactory() {
    }

    public static void setMaxCacheSize(int size) {
        LottieCompositionCache.getInstance().resize(size);
    }

    public static void clearCache(Context context) {
        taskCache.clear();
        LottieCompositionCache.getInstance().clear();
        L.networkCache(context).clear();
    }

    public static LottieTask<LottieComposition> fromUrl(Context context, String url) {
        return fromUrl(context, url, "url_" + url);
    }

    public static LottieTask<LottieComposition> fromUrl(Context context, String url, String cacheKey) {
        return cache(cacheKey, new LottieCompositionFactory$$ExternalSyntheticLambda2(context, url, cacheKey));
    }

    static /* synthetic */ LottieResult lambda$fromUrl$0(Context context, String url, String cacheKey) throws Exception {
        LottieResult<LottieComposition> result = L.networkFetcher(context).fetchSync(url, cacheKey);
        if (!(cacheKey == null || result.getValue() == null)) {
            LottieCompositionCache.getInstance().put(cacheKey, result.getValue());
        }
        return result;
    }

    public static LottieResult<LottieComposition> fromUrlSync(Context context, String url) {
        return fromUrlSync(context, url, url);
    }

    public static LottieResult<LottieComposition> fromUrlSync(Context context, String url, String cacheKey) {
        LottieResult<LottieComposition> result = L.networkFetcher(context).fetchSync(url, cacheKey);
        if (!(cacheKey == null || result.getValue() == null)) {
            LottieCompositionCache.getInstance().put(cacheKey, result.getValue());
        }
        return result;
    }

    public static LottieTask<LottieComposition> fromAsset(Context context, String fileName) {
        return fromAsset(context, fileName, "asset_" + fileName);
    }

    public static LottieTask<LottieComposition> fromAsset(Context context, String fileName, String cacheKey) {
        return cache(cacheKey, new LottieCompositionFactory$$ExternalSyntheticLambda10(context.getApplicationContext(), fileName, cacheKey));
    }

    public static LottieResult<LottieComposition> fromAssetSync(Context context, String fileName) {
        return fromAssetSync(context, fileName, "asset_" + fileName);
    }

    public static LottieResult<LottieComposition> fromAssetSync(Context context, String fileName, String cacheKey) {
        try {
            if (!fileName.endsWith(".zip")) {
                if (!fileName.endsWith(".lottie")) {
                    return fromJsonInputStreamSync(context.getAssets().open(fileName), cacheKey);
                }
            }
            return fromZipStreamSync(new ZipInputStream(context.getAssets().open(fileName)), cacheKey);
        } catch (IOException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    public static LottieTask<LottieComposition> fromRawRes(Context context, int rawRes) {
        return fromRawRes(context, rawRes, rawResCacheKey(context, rawRes));
    }

    public static LottieTask<LottieComposition> fromRawRes(Context context, int rawRes, String cacheKey) {
        return cache(cacheKey, new LottieCompositionFactory$$ExternalSyntheticLambda1(new WeakReference<>(context), context.getApplicationContext(), rawRes, cacheKey));
    }

    static /* synthetic */ LottieResult lambda$fromRawRes$2(WeakReference contextRef, Context appContext, int rawRes, String cacheKey) throws Exception {
        Context originalContext = (Context) contextRef.get();
        return fromRawResSync(originalContext != null ? originalContext : appContext, rawRes, cacheKey);
    }

    public static LottieResult<LottieComposition> fromRawResSync(Context context, int rawRes) {
        return fromRawResSync(context, rawRes, rawResCacheKey(context, rawRes));
    }

    public static LottieResult<LottieComposition> fromRawResSync(Context context, int rawRes, String cacheKey) {
        try {
            BufferedSource source = Okio.buffer(Okio.source(context.getResources().openRawResource(rawRes)));
            if (isZipCompressed(source).booleanValue()) {
                return fromZipStreamSync(new ZipInputStream(source.inputStream()), cacheKey);
            }
            return fromJsonInputStreamSync(source.inputStream(), cacheKey);
        } catch (Resources.NotFoundException e) {
            return new LottieResult<>((Throwable) e);
        }
    }

    private static String rawResCacheKey(Context context, int resId) {
        return "rawRes" + (isNightMode(context) ? "_night_" : "_day_") + resId;
    }

    private static boolean isNightMode(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static LottieTask<LottieComposition> fromJsonInputStream(InputStream stream, String cacheKey) {
        return cache(cacheKey, new LottieCompositionFactory$$ExternalSyntheticLambda8(stream, cacheKey));
    }

    public static LottieResult<LottieComposition> fromJsonInputStreamSync(InputStream stream, String cacheKey) {
        return fromJsonInputStreamSync(stream, cacheKey, true);
    }

    private static LottieResult<LottieComposition> fromJsonInputStreamSync(InputStream stream, String cacheKey, boolean close) {
        try {
            return fromJsonReaderSync(JsonReader.of(Okio.buffer(Okio.source(stream))), cacheKey);
        } finally {
            if (close) {
                Utils.closeQuietly(stream);
            }
        }
    }

    @Deprecated
    public static LottieTask<LottieComposition> fromJson(JSONObject json, String cacheKey) {
        return cache(cacheKey, new LottieCompositionFactory$$ExternalSyntheticLambda3(json, cacheKey));
    }

    @Deprecated
    public static LottieResult<LottieComposition> fromJsonSync(JSONObject json, String cacheKey) {
        return fromJsonStringSync(json.toString(), cacheKey);
    }

    public static LottieTask<LottieComposition> fromJsonString(String json, String cacheKey) {
        return cache(cacheKey, new LottieCompositionFactory$$ExternalSyntheticLambda7(json, cacheKey));
    }

    public static LottieResult<LottieComposition> fromJsonStringSync(String json, String cacheKey) {
        return fromJsonReaderSync(JsonReader.of(Okio.buffer(Okio.source((InputStream) new ByteArrayInputStream(json.getBytes())))), cacheKey);
    }

    public static LottieTask<LottieComposition> fromJsonReader(JsonReader reader, String cacheKey) {
        return cache(cacheKey, new LottieCompositionFactory$$ExternalSyntheticLambda0(reader, cacheKey));
    }

    public static LottieResult<LottieComposition> fromJsonReaderSync(JsonReader reader, String cacheKey) {
        return fromJsonReaderSyncInternal(reader, cacheKey, true);
    }

    private static LottieResult<LottieComposition> fromJsonReaderSyncInternal(JsonReader reader, String cacheKey, boolean close) {
        try {
            LottieComposition composition = LottieCompositionMoshiParser.parse(reader);
            if (cacheKey != null) {
                LottieCompositionCache.getInstance().put(cacheKey, composition);
            }
            LottieResult<LottieComposition> lottieResult = new LottieResult<>(composition);
            if (close) {
                Utils.closeQuietly(reader);
            }
            return lottieResult;
        } catch (Exception e) {
            LottieResult<LottieComposition> lottieResult2 = new LottieResult<>((Throwable) e);
            if (close) {
                Utils.closeQuietly(reader);
            }
            return lottieResult2;
        } catch (Throwable th) {
            if (close) {
                Utils.closeQuietly(reader);
            }
            throw th;
        }
    }

    public static LottieTask<LottieComposition> fromZipStream(ZipInputStream inputStream, String cacheKey) {
        return cache(cacheKey, new LottieCompositionFactory$$ExternalSyntheticLambda9(inputStream, cacheKey));
    }

    public static LottieResult<LottieComposition> fromZipStreamSync(ZipInputStream inputStream, String cacheKey) {
        try {
            return fromZipStreamSyncInternal(inputStream, cacheKey);
        } finally {
            Utils.closeQuietly(inputStream);
        }
    }

    private static LottieResult<LottieComposition> fromZipStreamSyncInternal(ZipInputStream inputStream, String cacheKey) {
        LottieComposition composition = null;
        Map<String, Bitmap> images = new HashMap<>();
        try {
            ZipEntry entry = inputStream.getNextEntry();
            while (entry != null) {
                String entryName = entry.getName();
                if (entryName.contains("__MACOSX")) {
                    inputStream.closeEntry();
                } else if (entry.getName().equalsIgnoreCase("manifest.json")) {
                    inputStream.closeEntry();
                } else if (entry.getName().contains(".json")) {
                    composition = fromJsonReaderSyncInternal(JsonReader.of(Okio.buffer(Okio.source((InputStream) inputStream))), (String) null, false).getValue();
                } else {
                    if (!entryName.contains(".png") && !entryName.contains(".webp") && !entryName.contains(".jpg")) {
                        if (!entryName.contains(".jpeg")) {
                            inputStream.closeEntry();
                        }
                    }
                    String[] splitName = entryName.split("/");
                    images.put(splitName[splitName.length - 1], BitmapFactory.decodeStream(inputStream));
                }
                entry = inputStream.getNextEntry();
            }
            if (composition == null) {
                return new LottieResult<>((Throwable) new IllegalArgumentException("Unable to parse composition"));
            }
            for (Map.Entry<String, Bitmap> e : images.entrySet()) {
                LottieImageAsset imageAsset = findImageAssetForFileName(composition, e.getKey());
                if (imageAsset != null) {
                    imageAsset.setBitmap(Utils.resizeBitmapIfNeeded(e.getValue(), imageAsset.getWidth(), imageAsset.getHeight()));
                }
            }
            for (Map.Entry<String, LottieImageAsset> entry2 : composition.getImages().entrySet()) {
                if (entry2.getValue().getBitmap() == null) {
                    return new LottieResult<>((Throwable) new IllegalStateException("There is no image for " + entry2.getValue().getFileName()));
                }
            }
            if (cacheKey != null) {
                LottieCompositionCache.getInstance().put(cacheKey, composition);
            }
            return new LottieResult<>(composition);
        } catch (IOException e2) {
            return new LottieResult<>((Throwable) e2);
        }
    }

    private static Boolean isZipCompressed(BufferedSource inputSource) {
        try {
            BufferedSource peek = inputSource.peek();
            for (byte b : MAGIC) {
                if (peek.readByte() != b) {
                    return false;
                }
            }
            peek.close();
            return true;
        } catch (NoSuchMethodError e) {
            return false;
        } catch (Exception e2) {
            Logger.error("Failed to check zip file header", e2);
            return false;
        }
    }

    private static LottieImageAsset findImageAssetForFileName(LottieComposition composition, String fileName) {
        for (LottieImageAsset asset : composition.getImages().values()) {
            if (asset.getFileName().equals(fileName)) {
                return asset;
            }
        }
        return null;
    }

    private static LottieTask<LottieComposition> cache(String cacheKey, Callable<LottieResult<LottieComposition>> callable) {
        LottieComposition cachedComposition = cacheKey == null ? null : LottieCompositionCache.getInstance().get(cacheKey);
        if (cachedComposition != null) {
            return new LottieTask<>(new LottieCompositionFactory$$ExternalSyntheticLambda4(cachedComposition));
        }
        if (cacheKey != null) {
            Map<String, LottieTask<LottieComposition>> map = taskCache;
            if (map.containsKey(cacheKey)) {
                return map.get(cacheKey);
            }
        }
        LottieTask<LottieComposition> task = new LottieTask<>(callable);
        if (cacheKey != null) {
            AtomicBoolean resultAlreadyCalled = new AtomicBoolean(false);
            task.addListener(new LottieCompositionFactory$$ExternalSyntheticLambda5(cacheKey, resultAlreadyCalled));
            task.addFailureListener(new LottieCompositionFactory$$ExternalSyntheticLambda6(cacheKey, resultAlreadyCalled));
            if (!resultAlreadyCalled.get()) {
                taskCache.put(cacheKey, task);
            }
        }
        return task;
    }

    static /* synthetic */ LottieResult lambda$cache$8(LottieComposition cachedComposition) throws Exception {
        return new LottieResult(cachedComposition);
    }

    static /* synthetic */ void lambda$cache$9(String cacheKey, AtomicBoolean resultAlreadyCalled, LottieComposition result) {
        taskCache.remove(cacheKey);
        resultAlreadyCalled.set(true);
    }

    static /* synthetic */ void lambda$cache$10(String cacheKey, AtomicBoolean resultAlreadyCalled, Throwable result) {
        taskCache.remove(cacheKey);
        resultAlreadyCalled.set(true);
    }
}
