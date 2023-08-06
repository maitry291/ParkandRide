package com.airbnb.lottie;

import android.content.Context;
import androidx.core.os.TraceCompat;
import com.airbnb.lottie.network.DefaultLottieNetworkFetcher;
import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import com.airbnb.lottie.network.NetworkCache;
import com.airbnb.lottie.network.NetworkFetcher;
import java.io.File;

public class L {
    public static boolean DBG = false;
    private static final int MAX_DEPTH = 20;
    public static final String TAG = "LOTTIE";
    private static LottieNetworkCacheProvider cacheProvider;
    private static int depthPastMaxDepth = 0;
    private static LottieNetworkFetcher fetcher;
    private static volatile NetworkCache networkCache;
    private static volatile NetworkFetcher networkFetcher;
    private static String[] sections;
    private static long[] startTimeNs;
    private static int traceDepth = 0;
    private static boolean traceEnabled = false;

    private L() {
    }

    public static void setTraceEnabled(boolean enabled) {
        if (traceEnabled != enabled) {
            traceEnabled = enabled;
            if (enabled) {
                sections = new String[20];
                startTimeNs = new long[20];
            }
        }
    }

    public static void beginSection(String section) {
        if (traceEnabled) {
            int i = traceDepth;
            if (i == 20) {
                depthPastMaxDepth++;
                return;
            }
            sections[i] = section;
            startTimeNs[i] = System.nanoTime();
            TraceCompat.beginSection(section);
            traceDepth++;
        }
    }

    public static float endSection(String section) {
        int i = depthPastMaxDepth;
        if (i > 0) {
            depthPastMaxDepth = i - 1;
            return 0.0f;
        } else if (!traceEnabled) {
            return 0.0f;
        } else {
            int i2 = traceDepth - 1;
            traceDepth = i2;
            if (i2 == -1) {
                throw new IllegalStateException("Can't end trace section. There are none.");
            } else if (section.equals(sections[i2])) {
                TraceCompat.endSection();
                return ((float) (System.nanoTime() - startTimeNs[traceDepth])) / 1000000.0f;
            } else {
                throw new IllegalStateException("Unbalanced trace call " + section + ". Expected " + sections[traceDepth] + ".");
            }
        }
    }

    public static void setFetcher(LottieNetworkFetcher customFetcher) {
        fetcher = customFetcher;
    }

    public static void setCacheProvider(LottieNetworkCacheProvider customProvider) {
        cacheProvider = customProvider;
    }

    public static NetworkFetcher networkFetcher(Context context) {
        NetworkFetcher local = networkFetcher;
        if (local == null) {
            synchronized (NetworkFetcher.class) {
                local = networkFetcher;
                if (local == null) {
                    NetworkCache networkCache2 = networkCache(context);
                    LottieNetworkFetcher lottieNetworkFetcher = fetcher;
                    if (lottieNetworkFetcher == null) {
                        lottieNetworkFetcher = new DefaultLottieNetworkFetcher();
                    }
                    NetworkFetcher networkFetcher2 = new NetworkFetcher(networkCache2, lottieNetworkFetcher);
                    local = networkFetcher2;
                    networkFetcher = networkFetcher2;
                }
            }
        }
        return local;
    }

    public static NetworkCache networkCache(Context context) {
        final Context appContext = context.getApplicationContext();
        NetworkCache local = networkCache;
        if (local == null) {
            synchronized (NetworkCache.class) {
                local = networkCache;
                if (local == null) {
                    LottieNetworkCacheProvider lottieNetworkCacheProvider = cacheProvider;
                    if (lottieNetworkCacheProvider == null) {
                        lottieNetworkCacheProvider = new LottieNetworkCacheProvider() {
                            public File getCacheDir() {
                                return new File(appContext.getCacheDir(), "lottie_network_cache");
                            }
                        };
                    }
                    NetworkCache networkCache2 = new NetworkCache(lottieNetworkCacheProvider);
                    local = networkCache2;
                    networkCache = networkCache2;
                }
            }
        }
        return local;
    }
}
