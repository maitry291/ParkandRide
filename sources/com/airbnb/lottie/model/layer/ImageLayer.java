package com.airbnb.lottie.model.layer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieImageAsset;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;

public class ImageLayer extends BaseLayer {
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private final Rect dst = new Rect();
    private BaseKeyframeAnimation<Bitmap, Bitmap> imageAnimation;
    private final LottieImageAsset lottieImageAsset;
    private final Paint paint = new LPaint(3);
    private final Rect src = new Rect();

    ImageLayer(LottieDrawable lottieDrawable, Layer layerModel) {
        super(lottieDrawable, layerModel);
        this.lottieImageAsset = lottieDrawable.getLottieImageAssetForId(layerModel.getRefId());
    }

    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        Bitmap bitmap = getBitmap();
        if (bitmap != null && !bitmap.isRecycled() && this.lottieImageAsset != null) {
            float density = Utils.dpScale();
            this.paint.setAlpha(parentAlpha);
            BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.colorFilterAnimation;
            if (baseKeyframeAnimation != null) {
                this.paint.setColorFilter(baseKeyframeAnimation.getValue());
            }
            canvas.save();
            canvas.concat(parentMatrix);
            this.src.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
            if (this.lottieDrawable.getMaintainOriginalImageBounds()) {
                this.dst.set(0, 0, (int) (((float) this.lottieImageAsset.getWidth()) * density), (int) (((float) this.lottieImageAsset.getHeight()) * density));
            } else {
                this.dst.set(0, 0, (int) (((float) bitmap.getWidth()) * density), (int) (((float) bitmap.getHeight()) * density));
            }
            canvas.drawBitmap(bitmap, this.src, this.dst, this.paint);
            canvas.restore();
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        super.getBounds(outBounds, parentMatrix, applyParents);
        if (this.lottieImageAsset != null) {
            float scale = Utils.dpScale();
            outBounds.set(0.0f, 0.0f, ((float) this.lottieImageAsset.getWidth()) * scale, ((float) this.lottieImageAsset.getHeight()) * scale);
            this.boundsMatrix.mapRect(outBounds);
        }
    }

    private Bitmap getBitmap() {
        Bitmap callbackBitmap;
        BaseKeyframeAnimation<Bitmap, Bitmap> baseKeyframeAnimation = this.imageAnimation;
        if (baseKeyframeAnimation != null && (callbackBitmap = baseKeyframeAnimation.getValue()) != null) {
            return callbackBitmap;
        }
        Bitmap bitmapFromDrawable = this.lottieDrawable.getBitmapForId(this.layerModel.getRefId());
        if (bitmapFromDrawable != null) {
            return bitmapFromDrawable;
        }
        LottieImageAsset asset = this.lottieImageAsset;
        if (asset != null) {
            return asset.getBitmap();
        }
        return null;
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        super.addValueCallback(property, callback);
        if (property == LottieProperty.COLOR_FILTER) {
            if (callback == null) {
                this.colorFilterAnimation = null;
            } else {
                this.colorFilterAnimation = new ValueCallbackKeyframeAnimation(callback);
            }
        } else if (property != LottieProperty.IMAGE) {
        } else {
            if (callback == null) {
                this.imageAnimation = null;
            } else {
                this.imageAnimation = new ValueCallbackKeyframeAnimation(callback);
            }
        }
    }
}
