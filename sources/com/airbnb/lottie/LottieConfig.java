package com.airbnb.lottie;

import com.airbnb.lottie.network.LottieNetworkCacheProvider;
import com.airbnb.lottie.network.LottieNetworkFetcher;
import java.io.File;

public class LottieConfig {
    final LottieNetworkCacheProvider cacheProvider;
    final boolean enableSystraceMarkers;
    final LottieNetworkFetcher networkFetcher;

    private LottieConfig(LottieNetworkFetcher networkFetcher2, LottieNetworkCacheProvider cacheProvider2, boolean enableSystraceMarkers2) {
        this.networkFetcher = networkFetcher2;
        this.cacheProvider = cacheProvider2;
        this.enableSystraceMarkers = enableSystraceMarkers2;
    }

    public static final class Builder {
        private LottieNetworkCacheProvider cacheProvider;
        private boolean enableSystraceMarkers = false;
        private LottieNetworkFetcher networkFetcher;

        public Builder setNetworkFetcher(LottieNetworkFetcher fetcher) {
            this.networkFetcher = fetcher;
            return this;
        }

        public Builder setNetworkCacheDir(final File file) {
            if (this.cacheProvider == null) {
                this.cacheProvider = new LottieNetworkCacheProvider() {
                    public File getCacheDir() {
                        if (file.isDirectory()) {
                            return file;
                        }
                        throw new IllegalArgumentException("cache file must be a directory");
                    }
                };
                return this;
            }
            throw new IllegalStateException("There is already a cache provider!");
        }

        public Builder setNetworkCacheProvider(final LottieNetworkCacheProvider fileCacheProvider) {
            if (this.cacheProvider == null) {
                this.cacheProvider = new LottieNetworkCacheProvider() {
                    public File getCacheDir() {
                        File file = fileCacheProvider.getCacheDir();
                        if (file.isDirectory()) {
                            return file;
                        }
                        throw new IllegalArgumentException("cache file must be a directory");
                    }
                };
                return this;
            }
            throw new IllegalStateException("There is already a cache provider!");
        }

        public Builder setEnableSystraceMarkers(boolean enable) {
            this.enableSystraceMarkers = enable;
            return this;
        }

        public LottieConfig build() {
            return new LottieConfig(this.networkFetcher, this.cacheProvider, this.enableSystraceMarkers);
        }
    }
}
