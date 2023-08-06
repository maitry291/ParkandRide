package com.airbnb.lottie.network;

import android.util.Pair;
import com.airbnb.lottie.utils.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NetworkCache {
    private final LottieNetworkCacheProvider cacheProvider;

    public NetworkCache(LottieNetworkCacheProvider cacheProvider2) {
        this.cacheProvider = cacheProvider2;
    }

    public void clear() {
        File parentDir = parentDir();
        if (parentDir.exists()) {
            File[] files = parentDir.listFiles();
            if (files != null && files.length > 0) {
                for (File file : parentDir.listFiles()) {
                    file.delete();
                }
            }
            parentDir.delete();
        }
    }

    /* access modifiers changed from: package-private */
    public Pair<FileExtension, InputStream> fetch(String url) {
        FileExtension extension;
        try {
            File cachedFile = getCachedFile(url);
            if (cachedFile == null) {
                return null;
            }
            try {
                FileInputStream inputStream = new FileInputStream(cachedFile);
                if (cachedFile.getAbsolutePath().endsWith(".zip")) {
                    extension = FileExtension.ZIP;
                } else {
                    extension = FileExtension.JSON;
                }
                Logger.debug("Cache hit for " + url + " at " + cachedFile.getAbsolutePath());
                return new Pair<>(extension, inputStream);
            } catch (FileNotFoundException e) {
                return null;
            }
        } catch (FileNotFoundException e2) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public File writeTempCacheFile(String url, InputStream stream, FileExtension extension) throws IOException {
        OutputStream output;
        File file = new File(parentDir(), filenameForUrl(url, extension, true));
        try {
            output = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            while (true) {
                int read = stream.read(buffer);
                int read2 = read;
                if (read != -1) {
                    output.write(buffer, 0, read2);
                } else {
                    output.flush();
                    output.close();
                    stream.close();
                    return file;
                }
            }
        } catch (Throwable th) {
            stream.close();
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    public void renameTempFile(String url, FileExtension extension) {
        File file = new File(parentDir(), filenameForUrl(url, extension, true));
        File newFile = new File(file.getAbsolutePath().replace(".temp", ""));
        boolean renamed = file.renameTo(newFile);
        Logger.debug("Copying temp file to real file (" + newFile + ")");
        if (!renamed) {
            Logger.warning("Unable to rename cache file " + file.getAbsolutePath() + " to " + newFile.getAbsolutePath() + ".");
        }
    }

    private File getCachedFile(String url) throws FileNotFoundException {
        File jsonFile = new File(parentDir(), filenameForUrl(url, FileExtension.JSON, false));
        if (jsonFile.exists()) {
            return jsonFile;
        }
        File zipFile = new File(parentDir(), filenameForUrl(url, FileExtension.ZIP, false));
        if (zipFile.exists()) {
            return zipFile;
        }
        return null;
    }

    private File parentDir() {
        File file = this.cacheProvider.getCacheDir();
        if (file.isFile()) {
            file.delete();
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private static String filenameForUrl(String url, FileExtension extension, boolean isTemp) {
        return "lottie_cache_" + url.replaceAll("\\W+", "") + (isTemp ? extension.tempExtension() : extension.extension);
    }
}
