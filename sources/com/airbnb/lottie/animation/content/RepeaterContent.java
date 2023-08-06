package com.airbnb.lottie.animation.content;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.Repeater;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class RepeaterContent implements DrawingContent, PathContent, GreedyContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private ContentGroup contentGroup;
    private final BaseKeyframeAnimation<Float, Float> copies;
    private final boolean hidden;
    private final BaseLayer layer;
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix = new Matrix();
    private final String name;
    private final BaseKeyframeAnimation<Float, Float> offset;
    private final Path path = new Path();
    private final TransformKeyframeAnimation transform;

    public RepeaterContent(LottieDrawable lottieDrawable2, BaseLayer layer2, Repeater repeater) {
        this.lottieDrawable = lottieDrawable2;
        this.layer = layer2;
        this.name = repeater.getName();
        this.hidden = repeater.isHidden();
        BaseKeyframeAnimation<Float, Float> createAnimation = repeater.getCopies().createAnimation();
        this.copies = createAnimation;
        layer2.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
        BaseKeyframeAnimation<Float, Float> createAnimation2 = repeater.getOffset().createAnimation();
        this.offset = createAnimation2;
        layer2.addAnimation(createAnimation2);
        createAnimation2.addUpdateListener(this);
        TransformKeyframeAnimation createAnimation3 = repeater.getTransform().createAnimation();
        this.transform = createAnimation3;
        createAnimation3.addAnimationsToLayer(layer2);
        createAnimation3.addListener(this);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x0005 A[LOOP:0: B:3:0x0005->B:6:0x000f, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void absorbContent(java.util.ListIterator<com.airbnb.lottie.animation.content.Content> r10) {
        /*
            r9 = this;
            com.airbnb.lottie.animation.content.ContentGroup r0 = r9.contentGroup
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            boolean r0 = r10.hasPrevious()
            if (r0 == 0) goto L_0x0012
            java.lang.Object r0 = r10.previous()
            if (r0 == r9) goto L_0x0012
            goto L_0x0005
        L_0x0012:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L_0x0017:
            boolean r1 = r10.hasPrevious()
            if (r1 == 0) goto L_0x002a
            java.lang.Object r1 = r10.previous()
            com.airbnb.lottie.animation.content.Content r1 = (com.airbnb.lottie.animation.content.Content) r1
            r0.add(r1)
            r10.remove()
            goto L_0x0017
        L_0x002a:
            java.util.Collections.reverse(r0)
            com.airbnb.lottie.animation.content.ContentGroup r8 = new com.airbnb.lottie.animation.content.ContentGroup
            com.airbnb.lottie.LottieDrawable r2 = r9.lottieDrawable
            com.airbnb.lottie.model.layer.BaseLayer r3 = r9.layer
            boolean r5 = r9.hidden
            r7 = 0
            java.lang.String r4 = "Repeater"
            r1 = r8
            r6 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r9.contentGroup = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.RepeaterContent.absorbContent(java.util.ListIterator):void");
    }

    public String getName() {
        return this.name;
    }

    public void setContents(List<Content> contentsBefore, List<Content> contentsAfter) {
        this.contentGroup.setContents(contentsBefore, contentsAfter);
    }

    public Path getPath() {
        Path contentPath = this.contentGroup.getPath();
        this.path.reset();
        float copies2 = this.copies.getValue().floatValue();
        float offset2 = this.offset.getValue().floatValue();
        for (int i = ((int) copies2) - 1; i >= 0; i--) {
            this.matrix.set(this.transform.getMatrixForRepeater(((float) i) + offset2));
            this.path.addPath(contentPath, this.matrix);
        }
        return this.path;
    }

    public void draw(Canvas canvas, Matrix parentMatrix, int alpha) {
        float copies2 = this.copies.getValue().floatValue();
        float offset2 = this.offset.getValue().floatValue();
        float startOpacity = this.transform.getStartOpacity().getValue().floatValue() / 100.0f;
        float endOpacity = this.transform.getEndOpacity().getValue().floatValue() / 100.0f;
        for (int i = ((int) copies2) - 1; i >= 0; i--) {
            this.matrix.set(parentMatrix);
            this.matrix.preConcat(this.transform.getMatrixForRepeater(((float) i) + offset2));
            this.contentGroup.draw(canvas, this.matrix, (int) (((float) alpha) * MiscUtils.lerp(startOpacity, endOpacity, ((float) i) / copies2)));
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        this.contentGroup.getBounds(outBounds, parentMatrix, applyParents);
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        if (!this.transform.applyValueCallback(property, callback)) {
            if (property == LottieProperty.REPEATER_COPIES) {
                this.copies.setValueCallback(callback);
            } else if (property == LottieProperty.REPEATER_OFFSET) {
                this.offset.setValueCallback(callback);
            }
        }
    }
}
