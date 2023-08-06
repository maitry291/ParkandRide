package com.airbnb.lottie.model.layer;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import androidx.core.view.ViewCompat;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.DrawingContent;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.MaskKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.KeyPathElement;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.DropShadowEffect;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseLayer implements DrawingContent, BaseKeyframeAnimation.AnimationListener, KeyPathElement {
    private static final int CLIP_SAVE_FLAG = 2;
    private static final int CLIP_TO_LAYER_SAVE_FLAG = 16;
    private static final int MATRIX_SAVE_FLAG = 1;
    private static final int SAVE_FLAGS = 19;
    private final List<BaseKeyframeAnimation<?, ?>> animations;
    BlurMaskFilter blurMaskFilter;
    float blurMaskFilterRadius;
    final Matrix boundsMatrix;
    private final RectF canvasBounds;
    private final Matrix canvasMatrix = new Matrix();
    private final Paint clearPaint;
    private final Paint contentPaint = new LPaint(1);
    private final String drawTraceName;
    private final Paint dstInPaint = new LPaint(1, PorterDuff.Mode.DST_IN);
    private final Paint dstOutPaint = new LPaint(1, PorterDuff.Mode.DST_OUT);
    private FloatKeyframeAnimation inOutAnimation;
    final Layer layerModel;
    final LottieDrawable lottieDrawable;
    private MaskKeyframeAnimation mask;
    private final RectF maskBoundsRect;
    private final Matrix matrix = new Matrix();
    private final RectF matteBoundsRect;
    private BaseLayer matteLayer;
    private final Paint mattePaint;
    private boolean outlineMasksAndMattes;
    private Paint outlineMasksAndMattesPaint;
    private BaseLayer parentLayer;
    private List<BaseLayer> parentLayers;
    private final Path path = new Path();
    private final RectF rect;
    private final RectF tempMaskBoundsRect;
    final TransformKeyframeAnimation transform;
    private boolean visible;

    /* access modifiers changed from: package-private */
    public abstract void drawLayer(Canvas canvas, Matrix matrix2, int i);

    static BaseLayer forModel(CompositionLayer compositionLayer, Layer layerModel2, LottieDrawable drawable, LottieComposition composition) {
        switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[layerModel2.getLayerType().ordinal()]) {
            case 1:
                return new ShapeLayer(drawable, layerModel2, compositionLayer);
            case 2:
                return new CompositionLayer(drawable, layerModel2, composition.getPrecomps(layerModel2.getRefId()), composition);
            case 3:
                return new SolidLayer(drawable, layerModel2);
            case 4:
                return new ImageLayer(drawable, layerModel2);
            case 5:
                return new NullLayer(drawable, layerModel2);
            case 6:
                return new TextLayer(drawable, layerModel2);
            default:
                Logger.warning("Unknown layer type " + layerModel2.getLayerType());
                return null;
        }
    }

    BaseLayer(LottieDrawable lottieDrawable2, Layer layerModel2) {
        LPaint lPaint = new LPaint(1);
        this.mattePaint = lPaint;
        this.clearPaint = new LPaint(PorterDuff.Mode.CLEAR);
        this.rect = new RectF();
        this.canvasBounds = new RectF();
        this.maskBoundsRect = new RectF();
        this.matteBoundsRect = new RectF();
        this.tempMaskBoundsRect = new RectF();
        this.boundsMatrix = new Matrix();
        this.animations = new ArrayList();
        this.visible = true;
        this.blurMaskFilterRadius = 0.0f;
        this.lottieDrawable = lottieDrawable2;
        this.layerModel = layerModel2;
        this.drawTraceName = layerModel2.getName() + "#draw";
        if (layerModel2.getMatteType() == Layer.MatteType.INVERT) {
            lPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        } else {
            lPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        }
        TransformKeyframeAnimation createAnimation = layerModel2.getTransform().createAnimation();
        this.transform = createAnimation;
        createAnimation.addListener(this);
        if (layerModel2.getMasks() != null && !layerModel2.getMasks().isEmpty()) {
            MaskKeyframeAnimation maskKeyframeAnimation = new MaskKeyframeAnimation(layerModel2.getMasks());
            this.mask = maskKeyframeAnimation;
            for (BaseKeyframeAnimation<ShapeData, Path> animation : maskKeyframeAnimation.getMaskAnimations()) {
                animation.addUpdateListener(this);
            }
            for (BaseKeyframeAnimation<Integer, Integer> animation2 : this.mask.getOpacityAnimations()) {
                addAnimation(animation2);
                animation2.addUpdateListener(this);
            }
        }
        setupInOutAnimations();
    }

    /* access modifiers changed from: package-private */
    public void setOutlineMasksAndMattes(boolean outline) {
        if (outline && this.outlineMasksAndMattesPaint == null) {
            this.outlineMasksAndMattesPaint = new LPaint();
        }
        this.outlineMasksAndMattes = outline;
    }

    public void onValueChanged() {
        invalidateSelf();
    }

    /* access modifiers changed from: package-private */
    public Layer getLayerModel() {
        return this.layerModel;
    }

    /* access modifiers changed from: package-private */
    public void setMatteLayer(BaseLayer matteLayer2) {
        this.matteLayer = matteLayer2;
    }

    /* access modifiers changed from: package-private */
    public boolean hasMatteOnThisLayer() {
        return this.matteLayer != null;
    }

    /* access modifiers changed from: package-private */
    public void setParentLayer(BaseLayer parentLayer2) {
        this.parentLayer = parentLayer2;
    }

    private void setupInOutAnimations() {
        boolean z = true;
        if (!this.layerModel.getInOutKeyframes().isEmpty()) {
            FloatKeyframeAnimation floatKeyframeAnimation = new FloatKeyframeAnimation(this.layerModel.getInOutKeyframes());
            this.inOutAnimation = floatKeyframeAnimation;
            floatKeyframeAnimation.setIsDiscrete();
            this.inOutAnimation.addUpdateListener(new BaseLayer$$ExternalSyntheticLambda0(this));
            if (((Float) this.inOutAnimation.getValue()).floatValue() != 1.0f) {
                z = false;
            }
            setVisible(z);
            addAnimation(this.inOutAnimation);
            return;
        }
        setVisible(true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setupInOutAnimations$0$com-airbnb-lottie-model-layer-BaseLayer  reason: not valid java name */
    public /* synthetic */ void m1325lambda$setupInOutAnimations$0$comairbnblottiemodellayerBaseLayer() {
        setVisible(this.inOutAnimation.getFloatValue() == 1.0f);
    }

    private void invalidateSelf() {
        this.lottieDrawable.invalidateSelf();
    }

    public void addAnimation(BaseKeyframeAnimation<?, ?> newAnimation) {
        if (newAnimation != null) {
            this.animations.add(newAnimation);
        }
    }

    public void removeAnimation(BaseKeyframeAnimation<?, ?> animation) {
        this.animations.remove(animation);
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
        buildParentLayerListIfNeeded();
        this.boundsMatrix.set(parentMatrix);
        if (applyParents) {
            List<BaseLayer> list = this.parentLayers;
            if (list != null) {
                for (int i = list.size() - 1; i >= 0; i--) {
                    this.boundsMatrix.preConcat(this.parentLayers.get(i).transform.getMatrix());
                }
            } else {
                BaseLayer baseLayer = this.parentLayer;
                if (baseLayer != null) {
                    this.boundsMatrix.preConcat(baseLayer.transform.getMatrix());
                }
            }
        }
        this.boundsMatrix.preConcat(this.transform.getMatrix());
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        Paint paint;
        L.beginSection(this.drawTraceName);
        if (!this.visible || this.layerModel.isHidden()) {
            L.endSection(this.drawTraceName);
            return;
        }
        buildParentLayerListIfNeeded();
        L.beginSection("Layer#parentMatrix");
        this.matrix.reset();
        this.matrix.set(parentMatrix);
        for (int i = this.parentLayers.size() - 1; i >= 0; i--) {
            this.matrix.preConcat(this.parentLayers.get(i).transform.getMatrix());
        }
        L.endSection("Layer#parentMatrix");
        int alpha = (int) ((((((float) parentAlpha) / 255.0f) * ((float) (this.transform.getOpacity() == null ? 100 : this.transform.getOpacity().getValue().intValue()))) / 100.0f) * 255.0f);
        if (hasMatteOnThisLayer() || hasMasksOnThisLayer()) {
            L.beginSection("Layer#computeBounds");
            getBounds(this.rect, this.matrix, false);
            intersectBoundsWithMatte(this.rect, parentMatrix);
            this.matrix.preConcat(this.transform.getMatrix());
            intersectBoundsWithMask(this.rect, this.matrix);
            this.canvasBounds.set(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight());
            canvas.getMatrix(this.canvasMatrix);
            if (!this.canvasMatrix.isIdentity()) {
                Matrix matrix2 = this.canvasMatrix;
                matrix2.invert(matrix2);
                this.canvasMatrix.mapRect(this.canvasBounds);
            }
            if (!this.rect.intersect(this.canvasBounds)) {
                this.rect.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
            L.endSection("Layer#computeBounds");
            if (this.rect.width() >= 1.0f && this.rect.height() >= 1.0f) {
                L.beginSection("Layer#saveLayer");
                this.contentPaint.setAlpha(255);
                Utils.saveLayerCompat(canvas, this.rect, this.contentPaint);
                L.endSection("Layer#saveLayer");
                clearCanvas(canvas);
                L.beginSection("Layer#drawLayer");
                drawLayer(canvas, this.matrix, alpha);
                L.endSection("Layer#drawLayer");
                if (hasMasksOnThisLayer()) {
                    applyMasks(canvas, this.matrix);
                }
                if (hasMatteOnThisLayer()) {
                    L.beginSection("Layer#drawMatte");
                    L.beginSection("Layer#saveLayer");
                    Utils.saveLayerCompat(canvas, this.rect, this.mattePaint, 19);
                    L.endSection("Layer#saveLayer");
                    clearCanvas(canvas);
                    this.matteLayer.draw(canvas, parentMatrix, alpha);
                    L.beginSection("Layer#restoreLayer");
                    canvas.restore();
                    L.endSection("Layer#restoreLayer");
                    L.endSection("Layer#drawMatte");
                }
                L.beginSection("Layer#restoreLayer");
                canvas.restore();
                L.endSection("Layer#restoreLayer");
            }
            if (this.outlineMasksAndMattes && (paint = this.outlineMasksAndMattesPaint) != null) {
                paint.setStyle(Paint.Style.STROKE);
                this.outlineMasksAndMattesPaint.setColor(-251901);
                this.outlineMasksAndMattesPaint.setStrokeWidth(4.0f);
                canvas.drawRect(this.rect, this.outlineMasksAndMattesPaint);
                this.outlineMasksAndMattesPaint.setStyle(Paint.Style.FILL);
                this.outlineMasksAndMattesPaint.setColor(1357638635);
                canvas.drawRect(this.rect, this.outlineMasksAndMattesPaint);
            }
            recordRenderTime(L.endSection(this.drawTraceName));
            return;
        }
        this.matrix.preConcat(this.transform.getMatrix());
        L.beginSection("Layer#drawLayer");
        drawLayer(canvas, this.matrix, alpha);
        L.endSection("Layer#drawLayer");
        recordRenderTime(L.endSection(this.drawTraceName));
    }

    private void recordRenderTime(float ms) {
        this.lottieDrawable.getComposition().getPerformanceTracker().recordRenderTime(this.layerModel.getName(), ms);
    }

    private void clearCanvas(Canvas canvas) {
        L.beginSection("Layer#clearLayer");
        canvas.drawRect(this.rect.left - 1.0f, this.rect.top - 1.0f, this.rect.right + 1.0f, this.rect.bottom + 1.0f, this.clearPaint);
        L.endSection("Layer#clearLayer");
    }

    private void intersectBoundsWithMask(RectF rect2, Matrix matrix2) {
        this.maskBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
        if (hasMasksOnThisLayer()) {
            int size = this.mask.getMasks().size();
            for (int i = 0; i < size; i++) {
                Mask mask2 = this.mask.getMasks().get(i);
                Path maskPath = this.mask.getMaskAnimations().get(i).getValue();
                if (maskPath != null) {
                    this.path.set(maskPath);
                    this.path.transform(matrix2);
                    switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[mask2.getMaskMode().ordinal()]) {
                        case 1:
                            return;
                        case 2:
                            return;
                        case 3:
                        case 4:
                            if (mask2.isInverted()) {
                                return;
                            }
                            break;
                    }
                    this.path.computeBounds(this.tempMaskBoundsRect, false);
                    if (i == 0) {
                        this.maskBoundsRect.set(this.tempMaskBoundsRect);
                    } else {
                        RectF rectF = this.maskBoundsRect;
                        rectF.set(Math.min(rectF.left, this.tempMaskBoundsRect.left), Math.min(this.maskBoundsRect.top, this.tempMaskBoundsRect.top), Math.max(this.maskBoundsRect.right, this.tempMaskBoundsRect.right), Math.max(this.maskBoundsRect.bottom, this.tempMaskBoundsRect.bottom));
                    }
                }
            }
            if (!rect2.intersect(this.maskBoundsRect)) {
                rect2.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    /* renamed from: com.airbnb.lottie.model.layer.BaseLayer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode;
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType;

        static {
            int[] iArr = new int[Mask.MaskMode.values().length];
            $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode = iArr;
            try {
                iArr[Mask.MaskMode.MASK_MODE_NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[Mask.MaskMode.MASK_MODE_SUBTRACT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[Mask.MaskMode.MASK_MODE_INTERSECT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[Mask.MaskMode.MASK_MODE_ADD.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            int[] iArr2 = new int[Layer.LayerType.values().length];
            $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType = iArr2;
            try {
                iArr2[Layer.LayerType.SHAPE.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.PRE_COMP.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.SOLID.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.IMAGE.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.NULL.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.TEXT.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$layer$Layer$LayerType[Layer.LayerType.UNKNOWN.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
        }
    }

    private void intersectBoundsWithMatte(RectF rect2, Matrix matrix2) {
        if (hasMatteOnThisLayer() && this.layerModel.getMatteType() != Layer.MatteType.INVERT) {
            this.matteBoundsRect.set(0.0f, 0.0f, 0.0f, 0.0f);
            this.matteLayer.getBounds(this.matteBoundsRect, matrix2, true);
            if (!rect2.intersect(this.matteBoundsRect)) {
                rect2.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        }
    }

    private void applyMasks(Canvas canvas, Matrix matrix2) {
        L.beginSection("Layer#saveLayer");
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint, 19);
        if (Build.VERSION.SDK_INT < 28) {
            clearCanvas(canvas);
        }
        L.endSection("Layer#saveLayer");
        for (int i = 0; i < this.mask.getMasks().size(); i++) {
            Mask mask2 = this.mask.getMasks().get(i);
            BaseKeyframeAnimation<ShapeData, Path> maskAnimation = this.mask.getMaskAnimations().get(i);
            BaseKeyframeAnimation<Integer, Integer> opacityAnimation = this.mask.getOpacityAnimations().get(i);
            switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$Mask$MaskMode[mask2.getMaskMode().ordinal()]) {
                case 1:
                    if (!areAllMasksNone()) {
                        break;
                    } else {
                        this.contentPaint.setAlpha(255);
                        canvas.drawRect(this.rect, this.contentPaint);
                        break;
                    }
                case 2:
                    if (i == 0) {
                        this.contentPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
                        this.contentPaint.setAlpha(255);
                        canvas.drawRect(this.rect, this.contentPaint);
                    }
                    if (!mask2.isInverted()) {
                        applySubtractMask(canvas, matrix2, maskAnimation);
                        break;
                    } else {
                        applyInvertedSubtractMask(canvas, matrix2, maskAnimation, opacityAnimation);
                        break;
                    }
                case 3:
                    if (!mask2.isInverted()) {
                        applyIntersectMask(canvas, matrix2, maskAnimation, opacityAnimation);
                        break;
                    } else {
                        applyInvertedIntersectMask(canvas, matrix2, maskAnimation, opacityAnimation);
                        break;
                    }
                case 4:
                    if (!mask2.isInverted()) {
                        applyAddMask(canvas, matrix2, maskAnimation, opacityAnimation);
                        break;
                    } else {
                        applyInvertedAddMask(canvas, matrix2, maskAnimation, opacityAnimation);
                        break;
                    }
            }
        }
        L.beginSection("Layer#restoreLayer");
        canvas.restore();
        L.endSection("Layer#restoreLayer");
    }

    private boolean areAllMasksNone() {
        if (this.mask.getMaskAnimations().isEmpty()) {
            return false;
        }
        for (int i = 0; i < this.mask.getMasks().size(); i++) {
            if (this.mask.getMasks().get(i).getMaskMode() != Mask.MaskMode.MASK_MODE_NONE) {
                return false;
            }
        }
        return true;
    }

    private void applyAddMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> maskAnimation, BaseKeyframeAnimation<Integer, Integer> opacityAnimation) {
        this.path.set(maskAnimation.getValue());
        this.path.transform(matrix2);
        this.contentPaint.setAlpha((int) (((float) opacityAnimation.getValue().intValue()) * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
    }

    private void applyInvertedAddMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> maskAnimation, BaseKeyframeAnimation<Integer, Integer> opacityAnimation) {
        Utils.saveLayerCompat(canvas, this.rect, this.contentPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.path.set(maskAnimation.getValue());
        this.path.transform(matrix2);
        this.contentPaint.setAlpha((int) (((float) opacityAnimation.getValue().intValue()) * 2.55f));
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applySubtractMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> maskAnimation) {
        this.path.set(maskAnimation.getValue());
        this.path.transform(matrix2);
        canvas.drawPath(this.path, this.dstOutPaint);
    }

    private void applyInvertedSubtractMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> maskAnimation, BaseKeyframeAnimation<Integer, Integer> opacityAnimation) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstOutPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (((float) opacityAnimation.getValue().intValue()) * 2.55f));
        this.path.set(maskAnimation.getValue());
        this.path.transform(matrix2);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    private void applyIntersectMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> maskAnimation, BaseKeyframeAnimation<Integer, Integer> opacityAnimation) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint);
        this.path.set(maskAnimation.getValue());
        this.path.transform(matrix2);
        this.contentPaint.setAlpha((int) (((float) opacityAnimation.getValue().intValue()) * 2.55f));
        canvas.drawPath(this.path, this.contentPaint);
        canvas.restore();
    }

    private void applyInvertedIntersectMask(Canvas canvas, Matrix matrix2, BaseKeyframeAnimation<ShapeData, Path> maskAnimation, BaseKeyframeAnimation<Integer, Integer> opacityAnimation) {
        Utils.saveLayerCompat(canvas, this.rect, this.dstInPaint);
        canvas.drawRect(this.rect, this.contentPaint);
        this.dstOutPaint.setAlpha((int) (((float) opacityAnimation.getValue().intValue()) * 2.55f));
        this.path.set(maskAnimation.getValue());
        this.path.transform(matrix2);
        canvas.drawPath(this.path, this.dstOutPaint);
        canvas.restore();
    }

    /* access modifiers changed from: package-private */
    public boolean hasMasksOnThisLayer() {
        MaskKeyframeAnimation maskKeyframeAnimation = this.mask;
        return maskKeyframeAnimation != null && !maskKeyframeAnimation.getMaskAnimations().isEmpty();
    }

    private void setVisible(boolean visible2) {
        if (visible2 != this.visible) {
            this.visible = visible2;
            invalidateSelf();
        }
    }

    /* access modifiers changed from: package-private */
    public void setProgress(float progress) {
        this.transform.setProgress(progress);
        if (this.mask != null) {
            for (int i = 0; i < this.mask.getMaskAnimations().size(); i++) {
                this.mask.getMaskAnimations().get(i).setProgress(progress);
            }
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.inOutAnimation;
        if (floatKeyframeAnimation != null) {
            floatKeyframeAnimation.setProgress(progress);
        }
        BaseLayer baseLayer = this.matteLayer;
        if (baseLayer != null) {
            baseLayer.setProgress(progress);
        }
        for (int i2 = 0; i2 < this.animations.size(); i2++) {
            this.animations.get(i2).setProgress(progress);
        }
    }

    private void buildParentLayerListIfNeeded() {
        if (this.parentLayers == null) {
            if (this.parentLayer == null) {
                this.parentLayers = Collections.emptyList();
                return;
            }
            this.parentLayers = new ArrayList();
            for (BaseLayer layer = this.parentLayer; layer != null; layer = layer.parentLayer) {
                this.parentLayers.add(layer);
            }
        }
    }

    public String getName() {
        return this.layerModel.getName();
    }

    public BlurEffect getBlurEffect() {
        return this.layerModel.getBlurEffect();
    }

    public BlurMaskFilter getBlurMaskFilter(float radius) {
        if (this.blurMaskFilterRadius == radius) {
            return this.blurMaskFilter;
        }
        BlurMaskFilter blurMaskFilter2 = new BlurMaskFilter(radius / 2.0f, BlurMaskFilter.Blur.NORMAL);
        this.blurMaskFilter = blurMaskFilter2;
        this.blurMaskFilterRadius = radius;
        return blurMaskFilter2;
    }

    public DropShadowEffect getDropShadowEffect() {
        return this.layerModel.getDropShadowEffect();
    }

    public void setContents(List<Content> list, List<Content> list2) {
    }

    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        BaseLayer baseLayer = this.matteLayer;
        if (baseLayer != null) {
            KeyPath matteCurrentPartialKeyPath = currentPartialKeyPath.addKey(baseLayer.getName());
            if (keyPath.fullyResolvesTo(this.matteLayer.getName(), depth)) {
                accumulator.add(matteCurrentPartialKeyPath.resolve(this.matteLayer));
            }
            if (keyPath.propagateToChildren(getName(), depth)) {
                this.matteLayer.resolveChildKeyPath(keyPath, keyPath.incrementDepthBy(this.matteLayer.getName(), depth) + depth, accumulator, matteCurrentPartialKeyPath);
            }
        }
        if (keyPath.matches(getName(), depth)) {
            if (!"__container".equals(getName())) {
                currentPartialKeyPath = currentPartialKeyPath.addKey(getName());
                if (keyPath.fullyResolvesTo(getName(), depth)) {
                    accumulator.add(currentPartialKeyPath.resolve(this));
                }
            }
            if (keyPath.propagateToChildren(getName(), depth)) {
                resolveChildKeyPath(keyPath, keyPath.incrementDepthBy(getName(), depth) + depth, accumulator, currentPartialKeyPath);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resolveChildKeyPath(KeyPath keyPath, int depth, List<KeyPath> list, KeyPath currentPartialKeyPath) {
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        this.transform.applyValueCallback(property, callback);
    }
}
