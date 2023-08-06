package com.airbnb.lottie.network;

import android.util.Pair;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

public class NetworkFetcher {
    private final LottieNetworkFetcher fetcher;
    private final NetworkCache networkCache;

    public NetworkFetcher(NetworkCache networkCache2, LottieNetworkFetcher fetcher2) {
        this.networkCache = networkCache2;
        this.fetcher = fetcher2;
    }

    public LottieResult<LottieComposition> fetchSync(String url, String cacheKey) {
        LottieComposition result = fetchFromCache(url, cacheKey);
        if (result != null) {
            return new LottieResult<>(result);
        }
        Logger.debug("Animation for " + url + " not found in cache. Fetching from network.");
        return fetchFromNetwork(url, cacheKey);
    }

    private LottieComposition fetchFromCache(String url, String cacheKey) {
        Pair<FileExtension, InputStream> cacheResult;
        LottieResult<LottieComposition> result;
        if (cacheKey == null || (cacheResult = this.networkCache.fetch(url)) == null) {
            return null;
        }
        FileExtension extension = (FileExtension) cacheResult.first;
        InputStream inputStream = (InputStream) cacheResult.second;
        if (extension == FileExtension.ZIP) {
            result = LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(inputStream), url);
        } else {
            result = LottieCompositionFactory.fromJsonInputStreamSync(inputStream, url);
        }
        if (result.getValue() != null) {
            return result.getValue();
        }
        return null;
    }

    private LottieResult<LottieComposition> fetchFromNetwork(String url, String cacheKey) {
        Logger.debug("Fetching " + url);
        LottieFetchResult fetchResult = null;
        try {
            fetchResult = this.fetcher.fetchSync(url);
            if (fetchResult.isSuccessful()) {
                LottieResult<LottieComposition> result = fromInputStream(url, fetchResult.bodyByteStream(), fetchResult.contentType(), cacheKey);
                Logger.debug("Completed fetch from network. Success: " + (result.getValue() != null));
                if (fetchResult != null) {
                    try {
                        fetchResult.close();
                    } catch (IOException e) {
                        Logger.warning("LottieFetchResult close failed ", e);
                    }
                }
                return result;
            }
            LottieResult<LottieComposition> lottieResult = new LottieResult<>((Throwable) new IllegalArgumentException(fetchResult.error()));
            if (fetchResult != null) {
                try {
                    fetchResult.close();
                } catch (IOException e2) {
                    Logger.warning("LottieFetchResult close failed ", e2);
                }
            }
            return lottieResult;
        } catch (Exception e3) {
            LottieResult<LottieComposition> lottieResult2 = new LottieResult<>((Throwable) e3);
            if (fetchResult != null) {
                try {
                    fetchResult.close();
                } catch (IOException e4) {
                    Logger.warning("LottieFetchResult close failed ", e4);
                }
            }
            return lottieResult2;
        } catch (Throwable th) {
            if (fetchResult != null) {
                try {
                    fetchResult.close();
                } catch (IOException e5) {
                    Logger.warning("LottieFetchResult close failed ", e5);
                }
            }
            throw th;
        }
    }

    private LottieResult<LottieComposition> fromInputStream(String url, InputStream inputStream, String contentType, String cacheKey) throws IOException {
        LottieResult<LottieComposition> result;
        FileExtension extension;
        if (contentType == null) {
            contentType = "application/json";
        }
        if (contentType.contains("application/zip") || contentType.contains("application/x-zip") || contentType.contains("application/x-zip-compressed") || url.split("\\?")[0].endsWith(".lottie")) {
            Logger.debug("Handling zip response.");
            extension = FileExtension.ZIP;
            result = fromZipStream(url, inputStream, cacheKey);
        } else {
            Logger.debug("Received json response.");
            extension = FileExtension.JSON;
            result = fromJsonStream(url, inputStream, cacheKey);
        }
        if (!(cacheKey == null || result.getValue() == null)) {
            this.networkCache.renameTempFile(url, extension);
        }
        return result;
    }

    private LottieResult<LottieComposition> fromZipStream(String url, InputStream inputStream, String cacheKey) throws IOException {
        if (cacheKey == null) {
            return LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(inputStream), (String) null);
        }
        return LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(new FileInputStream(this.networkCache.writeTempCacheFile(url, inputStream, FileExtension.ZIP))), url);
    }

    private LottieResult<LottieComposition> fromJsonStream(String url, InputStream inputStream, String cacheKey) throws IOException {
        if (cacheKey == null) {
            return LottieCompositionFactory.fromJsonInputStreamSync(inputStream, (String) null);
        }
        return LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(this.networkCache.writeTempCacheFile(url, inputStream, FileExtension.JSON).getAbsolutePath()), url);
    }
}
