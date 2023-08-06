package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public class CompositionLayer extends BaseLayer {
    private boolean clipToCompositionBounds = true;
    private Boolean hasMasks;
    private Boolean hasMatte;
    private final Paint layerPaint = new Paint();
    private final List<BaseLayer> layers = new ArrayList();
    private final RectF newClipRect = new RectF();
    private final RectF rect = new RectF();
    private BaseKeyframeAnimation<Float, Float> timeRemapping;

    public CompositionLayer(LottieDrawable lottieDrawable, Layer layerModel, List<Layer> layerModels, LottieComposition composition) {
        super(lottieDrawable, layerModel);
        BaseLayer parentLayer;
        AnimatableFloatValue timeRemapping2 = layerModel.getTimeRemapping();
        if (timeRemapping2 != null) {
            BaseKeyframeAnimation<Float, Float> createAnimation = timeRemapping2.createAnimation();
            this.timeRemapping = createAnimation;
            addAnimation(createAnimation);
            this.timeRemapping.addUpdateListener(this);
        } else {
            this.timeRemapping = null;
        }
        LongSparseArray<BaseLayer> layerMap = new LongSparseArray<>(composition.getLayers().size());
        BaseLayer mattedLayer = null;
        for (int i = layerModels.size() - 1; i >= 0; i--) {
            Layer lm = layerModels.get(i);
            BaseLayer layer = BaseLayer.forModel(this, lm, lottieDrawable, composition);
            if (layer != null) {
                layerMap.put(layer.getLayerModel().getId(), layer);
                if (mattedLayer == null) {
                    this.layers.add(0, layer);
                    switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType[lm.getMatteType().ordinal()]) {
                        case 1:
                        case 2:
                            mattedLayer = layer;
                            break;
                    }
                } else {
                    mattedLayer.setMatteLayer(layer);
                    mattedLayer = null;
                }
            }
        }
        for (int i2 = 0; i2 < layerMap.size(); i2++) {
            BaseLayer layerView = layerMap.get(layerMap.keyAt(i2));
            if (!(layerView == null || (parentLayer = layerMap.get(layerView.getLayerModel().getParentId())) == null)) {
                layerView.setParentLayer(parentLayer);
            }
        }
    }

    /* renamed from: com.airbnb.lottie.model.layer.CompositionLayer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType;

        static {
            int[] iArr = new int[Layer.MatteType.values().length];
            $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType = iArr;
            try {
                iArr[Layer.MatteType.ADD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$MatteType[Layer.MatteType.INVERT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public void setClipToCompositionBounds(boolean clipToCompositionBounds2) {
        this.clipToCompositionBounds = clipToCompositionBounds2;
    }

    public void setOutlineMasksAndMattes(boolean outline) {
        super.setOutlineMasksAndMattes(outline);
        for (BaseLayer layer : this.layers) {
            layer.setOutlineMasksAndMattes(outline);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        L.beginSection("CompositionLayer#draw");
        this.newClipRect.set(0.0f, 0.0f, (float) this.layerModel.getPreCompWidth(), (float) this.layerModel.getPreCompHeight());
        parentMatrix.mapRect(this.newClipRect);
        int childAlpha = 255;
        boolean isDrawingWithOffScreen = this.lottieDrawable.isApplyingOpacityToLayersEnabled() && this.layers.size() > 1 && parentAlpha != 255;
        if (isDrawingWithOffScreen) {
            this.layerPaint.setAlpha(parentAlpha);
            Utils.saveLayerCompat(canvas, this.newClipRect, this.layerPaint);
        } else {
            canvas.save();
        }
        if (!isDrawingWithOffScreen) {
            childAlpha = parentAlpha;
        }
        for (int i = this.layers.size() - 1; i >= 0; i--) {
            boolean nonEmptyClip = true;
            if (!(!this.clipToCompositionBounds && "__container".equals(this.layerModel.getName())) && !this.newClipRect.isEmpty()) {
                nonEmptyClip = canvas.clipRect(this.newClipRect);
            }
            if (nonEmptyClip) {
                this.layers.get(i).draw(canvas, parentMatrix, childAlpha);
            }
        }
        canvas.restore();
        L.endSection("CompositionLayer#draw");
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        super.getBounds(outBounds, parentMatrix, applyParents);
        for (int i = this.layers.size() - 1; i >= 0; i--) {
            this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.layers.get(i).getBounds(this.rect, this.boundsMatrix, true);
            outBounds.union(this.rect);
        }
    }

    public void setProgress(float progress) {
        super.setProgress(progress);
        if (this.timeRemapping != null) {
            progress = ((this.timeRemapping.getValue().floatValue() * this.layerModel.getComposition().getFrameRate()) - this.layerModel.getComposition().getStartFrame()) / (this.lottieDrawable.getComposition().getDurationFrames() + 0.01f);
        }
        if (this.timeRemapping == null) {
            progress -= this.layerModel.getStartProgress();
        }
        if (this.layerModel.getTimeStretch() != 0.0f && !"__container".equals(this.layerModel.getName())) {
            progress /= this.layerModel.getTimeStretch();
        }
        for (int i = this.layers.size() - 1; i >= 0; i--) {
            this.layers.get(i).setProgress(progress);
        }
    }

    public boolean hasMasks() {
        if (this.hasMasks == null) {
            for (int i = this.layers.size() - 1; i >= 0; i--) {
                BaseLayer layer = this.layers.get(i);
                if (layer instanceof ShapeLayer) {
                    if (layer.hasMasksOnThisLayer()) {
                        this.hasMasks = true;
                        return true;
                    }
                } else if ((layer instanceof CompositionLayer) && ((CompositionLayer) layer).hasMasks()) {
                    this.hasMasks = true;
                    return true;
                }
            }
            this.hasMasks = false;
        }
        return this.hasMasks.booleanValue();
    }

    public boolean hasMatte() {
        if (this.hasMatte == null) {
            if (hasMatteOnThisLayer()) {
                this.hasMatte = true;
                return true;
            }
            for (int i = this.layers.size() - 1; i >= 0; i--) {
                if (this.layers.get(i).hasMatteOnThisLayer()) {
                    this.hasMatte = true;
                    return true;
                }
            }
            this.hasMatte = false;
        }
        return this.hasMatte.booleanValue();
    }

    /* access modifiers changed from: protected */
    public void resolveChildKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        for (int i = 0; i < this.layers.size(); i++) {
            this.layers.get(i).resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath);
        }
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        super.addValueCallback(property, callback);
        if (property != LottieProperty.TIME_REMAP) {
            return;
        }
        if (callback == null) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation = this.timeRemapping;
            if (baseKeyframeAnimation != null) {
                baseKeyframeAnimation.setValueCallback((LottieValueCallback<Float>) null);
                return;
            }
            return;
        }
        ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(callback);
        this.timeRemapping = valueCallbackKeyframeAnimation;
        valueCallbackKeyframeAnimation.addUpdateListener(this);
        addAnimation(this.timeRemapping);
    }
}
