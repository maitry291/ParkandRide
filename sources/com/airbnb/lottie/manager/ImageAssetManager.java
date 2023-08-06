package com.airbnb.lottie.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageAssetManager {
    private static final Object bitmapHashLock = new Object();
    private final Context context;
    private ImageAssetDelegate delegate;
    private final Map<String, LottieImageAsset> imageAssets;
    private final String imagesFolder;

    public ImageAssetManager(Drawable.Callback callback, String imagesFolder2, ImageAssetDelegate delegate2, Map<String, LottieImageAsset> imageAssets2) {
        if (TextUtils.isEmpty(imagesFolder2) || imagesFolder2.charAt(imagesFolder2.length() - 1) == '/') {
            this.imagesFolder = imagesFolder2;
        } else {
            this.imagesFolder = imagesFolder2 + '/';
        }
        if (!(callback instanceof View)) {
            Logger.warning("LottieDrawable must be inside of a view for images to work.");
            this.imageAssets = new HashMap();
            this.context = null;
            return;
        }
        this.context = ((View) callback).getContext();
        this.imageAssets = imageAssets2;
        setDelegate(delegate2);
    }

    public void setDelegate(ImageAssetDelegate assetDelegate) {
        this.delegate = assetDelegate;
    }

    public Bitmap updateBitmap(String id, Bitmap bitmap) {
        if (bitmap == null) {
            LottieImageAsset asset = this.imageAssets.get(id);
            Bitmap ret = asset.getBitmap();
            asset.setBitmap((Bitmap) null);
            return ret;
        }
        Bitmap prevBitmap = this.imageAssets.get(id).getBitmap();
        putBitmap(id, bitmap);
        return prevBitmap;
    }

    public LottieImageAsset getImageAssetById(String id) {
        return this.imageAssets.get(id);
    }

    public Bitmap bitmapForId(String id) {
        LottieImageAsset asset = this.imageAssets.get(id);
        if (asset == null) {
            return null;
        }
        Bitmap bitmap = asset.getBitmap();
        if (bitmap != null) {
            return bitmap;
        }
        ImageAssetDelegate imageAssetDelegate = this.delegate;
        if (imageAssetDelegate != null) {
            Bitmap bitmap2 = imageAssetDelegate.fetchBitmap(asset);
            if (bitmap2 != null) {
                putBitmap(id, bitmap2);
            }
            return bitmap2;
        }
        String filename = asset.getFileName();
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = true;
        opts.inDensity = 160;
        if (!filename.startsWith("data:") || filename.indexOf("base64,") <= 0) {
            try {
                if (!TextUtils.isEmpty(this.imagesFolder)) {
                    try {
                        return putBitmap(id, Utils.resizeBitmapIfNeeded(BitmapFactory.decodeStream(this.context.getAssets().open(this.imagesFolder + filename), (Rect) null, opts), asset.getWidth(), asset.getHeight()));
                    } catch (IllegalArgumentException e) {
                        Logger.warning("Unable to decode image.", e);
                        return null;
                    }
                } else {
                    throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
                }
            } catch (IOException e2) {
                Logger.warning("Unable to open asset.", e2);
                return null;
            }
        } else {
            try {
                byte[] data = Base64.decode(filename.substring(filename.indexOf(44) + 1), 0);
                return putBitmap(id, BitmapFactory.decodeByteArray(data, 0, data.length, opts));
            } catch (IllegalArgumentException e3) {
                Logger.warning("data URL did not have correct base64 format.", e3);
                return null;
            }
        }
    }

    public boolean hasSameContext(Context context2) {
        return (context2 == null && this.context == null) || this.context.equals(context2);
    }

    private Bitmap putBitmap(String key, Bitmap bitmap) {
        synchronized (bitmapHashLock) {
            this.imageAssets.get(key).setBitmap(bitmap);
        }
        return bitmap;
    }
}
