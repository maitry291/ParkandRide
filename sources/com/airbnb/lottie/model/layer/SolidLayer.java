package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.value.LottieValueCallback;

public class SolidLayer extends BaseLayer {
    private BaseKeyframeAnimation<ColorFilter, ColorFilter> colorFilterAnimation;
    private final Layer layerModel;
    private final Paint paint;
    private final Path path;
    private final float[] points;
    private final RectF rect = new RectF();

    SolidLayer(LottieDrawable lottieDrawable, Layer layerModel2) {
        super(lottieDrawable, layerModel2);
        LPaint lPaint = new LPaint();
        this.paint = lPaint;
        this.points = new float[8];
        this.path = new Path();
        this.layerModel = layerModel2;
        lPaint.setAlpha(0);
        lPaint.setStyle(Paint.Style.FILL);
        lPaint.setColor(layerModel2.getSolidColor());
    }

    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        int backgroundAlpha = Color.alpha(this.layerModel.getSolidColor());
        if (backgroundAlpha != 0) {
            int alpha = (int) ((((float) parentAlpha) / 255.0f) * (((((float) backgroundAlpha) / 255.0f) * ((float) (this.transform.getOpacity() == null ? 100 : this.transform.getOpacity().getValue().intValue()))) / 100.0f) * 255.0f);
            this.paint.setAlpha(alpha);
            BaseKeyframeAnimation<ColorFilter, ColorFilter> baseKeyframeAnimation = this.colorFilterAnimation;
            if (baseKeyframeAnimation != null) {
                this.paint.setColorFilter(baseKeyframeAnimation.getValue());
            }
            if (alpha > 0) {
                float[] fArr = this.points;
                fArr[0] = 0.0f;
                fArr[1] = 0.0f;
                fArr[2] = (float) this.layerModel.getSolidWidth();
                float[] fArr2 = this.points;
                fArr2[3] = 0.0f;
                fArr2[4] = (float) this.layerModel.getSolidWidth();
                this.points[5] = (float) this.layerModel.getSolidHeight();
                float[] fArr3 = this.points;
                fArr3[6] = 0.0f;
                fArr3[7] = (float) this.layerModel.getSolidHeight();
                parentMatrix.mapPoints(this.points);
                this.path.reset();
                Path path2 = this.path;
                float[] fArr4 = this.points;
                path2.moveTo(fArr4[0], fArr4[1]);
                Path path3 = this.path;
                float[] fArr5 = this.points;
                path3.lineTo(fArr5[2], fArr5[3]);
                Path path4 = this.path;
                float[] fArr6 = this.points;
                path4.lineTo(fArr6[4], fArr6[5]);
                Path path5 = this.path;
                float[] fArr7 = this.points;
                path5.lineTo(fArr7[6], fArr7[7]);
                Path path6 = this.path;
                float[] fArr8 = this.points;
                path6.lineTo(fArr8[0], fArr8[1]);
                this.path.close();
                canvas.drawPath(this.path, this.paint);
                return;
            }
            Canvas canvas2 = canvas;
            Matrix matrix = parentMatrix;
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        super.getBounds(outBounds, parentMatrix, applyParents);
        this.rect.set(0.0f, 0.0f, (float) this.layerModel.getSolidWidth(), (float) this.layerModel.getSolidHeight());
        this.boundsMatrix.mapRect(this.rect);
        outBounds.set(this.rect);
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        super.addValueCallback(property, callback);
        if (property != LottieProperty.COLOR_FILTER) {
            return;
        }
        if (callback == null) {
            this.colorFilterAnimation = null;
        } else {
            this.colorFilterAnimation = new ValueCallbackKeyframeAnimation(callback);
        }
    }
}
