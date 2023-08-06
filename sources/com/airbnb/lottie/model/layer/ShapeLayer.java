package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.parser.DropShadowEffect;
import java.util.Collections;
import java.util.List;

public class ShapeLayer extends BaseLayer {
    private final CompositionLayer compositionLayer;
    private final ContentGroup contentGroup;

    ShapeLayer(LottieDrawable lottieDrawable, Layer layerModel, CompositionLayer compositionLayer2) {
        super(lottieDrawable, layerModel);
        this.compositionLayer = compositionLayer2;
        ContentGroup contentGroup2 = new ContentGroup(lottieDrawable, this, new ShapeGroup("__container", layerModel.getShapes(), false));
        this.contentGroup = contentGroup2;
        contentGroup2.setContents(Collections.emptyList(), Collections.emptyList());
    }

    /* access modifiers changed from: package-private */
    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        this.contentGroup.draw(canvas, parentMatrix, parentAlpha);
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        super.getBounds(outBounds, parentMatrix, applyParents);
        this.contentGroup.getBounds(outBounds, this.boundsMatrix, applyParents);
    }

    public BlurEffect getBlurEffect() {
        BlurEffect layerBlur = super.getBlurEffect();
        if (layerBlur != null) {
            return layerBlur;
        }
        return this.compositionLayer.getBlurEffect();
    }

    public DropShadowEffect getDropShadowEffect() {
        DropShadowEffect layerDropShadow = super.getDropShadowEffect();
        if (layerDropShadow != null) {
            return layerDropShadow;
        }
        return this.compositionLayer.getDropShadowEffect();
    }

    /* access modifiers changed from: protected */
    public void resolveChildKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        this.contentGroup.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath);
    }
}
