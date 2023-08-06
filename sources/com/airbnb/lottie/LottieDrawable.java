package com.airbnb.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.manager.FontAssetManager;
import com.airbnb.lottie.manager.ImageAssetManager;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.Marker;
import com.airbnb.lottie.model.layer.CompositionLayer;
import com.airbnb.lottie.parser.LayerParser;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.LottieValueAnimator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LottieDrawable extends Drawable implements Drawable.Callback, Animatable {
    public static final int INFINITE = -1;
    public static final int RESTART = 1;
    public static final int REVERSE = 2;
    private int alpha;
    /* access modifiers changed from: private */
    public final LottieValueAnimator animator;
    private Rect canvasClipBounds;
    private RectF canvasClipBoundsRectF;
    private boolean clipToCompositionBounds;
    private LottieComposition composition;
    /* access modifiers changed from: private */
    public CompositionLayer compositionLayer;
    private boolean enableMergePaths;
    FontAssetDelegate fontAssetDelegate;
    private FontAssetManager fontAssetManager;
    private boolean ignoreSystemAnimationsDisabled = false;
    private ImageAssetDelegate imageAssetDelegate;
    private ImageAssetManager imageAssetManager;
    private String imageAssetsFolder;
    private boolean isApplyingOpacityToLayersEnabled;
    private boolean isDirty;
    private final ArrayList<LazyCompositionTask> lazyCompositionTasks = new ArrayList<>();
    private boolean maintainOriginalImageBounds;
    private OnVisibleAction onVisibleAction = OnVisibleAction.NONE;
    private boolean outlineMasksAndMattes;
    private boolean performanceTrackingEnabled;
    private final ValueAnimator.AnimatorUpdateListener progressUpdateListener;
    private RenderMode renderMode;
    private final Matrix renderingMatrix;
    private boolean safeMode = false;
    private Bitmap softwareRenderingBitmap;
    private Canvas softwareRenderingCanvas;
    private Rect softwareRenderingDstBoundsRect;
    private RectF softwareRenderingDstBoundsRectF;
    private Matrix softwareRenderingOriginalCanvasMatrix;
    private Matrix softwareRenderingOriginalCanvasMatrixInverse;
    private Paint softwareRenderingPaint;
    private Rect softwareRenderingSrcBoundsRect;
    private RectF softwareRenderingTransformedBounds;
    private boolean systemAnimationsEnabled = true;
    TextDelegate textDelegate;
    private boolean useSoftwareRendering;

    private interface LazyCompositionTask {
        void run(LottieComposition lottieComposition);
    }

    private enum OnVisibleAction {
        NONE,
        PLAY,
        RESUME
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    public LottieDrawable() {
        LottieValueAnimator lottieValueAnimator = new LottieValueAnimator();
        this.animator = lottieValueAnimator;
        AnonymousClass1 r3 = new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                if (LottieDrawable.this.compositionLayer != null) {
                    LottieDrawable.this.compositionLayer.setProgress(LottieDrawable.this.animator.getAnimatedValueAbsolute());
                }
            }
        };
        this.progressUpdateListener = r3;
        this.maintainOriginalImageBounds = false;
        this.clipToCompositionBounds = true;
        this.alpha = 255;
        this.renderMode = RenderMode.AUTOMATIC;
        this.useSoftwareRendering = false;
        this.renderingMatrix = new Matrix();
        this.isDirty = false;
        lottieValueAnimator.addUpdateListener(r3);
    }

    public boolean hasMasks() {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        return compositionLayer2 != null && compositionLayer2.hasMasks();
    }

    public boolean hasMatte() {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        return compositionLayer2 != null && compositionLayer2.hasMatte();
    }

    public boolean enableMergePathsForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    public void enableMergePathsForKitKatAndAbove(boolean enable) {
        if (this.enableMergePaths != enable) {
            if (Build.VERSION.SDK_INT < 19) {
                Logger.warning("Merge paths are not supported pre-Kit Kat.");
                return;
            }
            this.enableMergePaths = enable;
            if (this.composition != null) {
                buildCompositionLayer();
            }
        }
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.enableMergePaths;
    }

    public void setClipToCompositionBounds(boolean clipToCompositionBounds2) {
        if (clipToCompositionBounds2 != this.clipToCompositionBounds) {
            this.clipToCompositionBounds = clipToCompositionBounds2;
            CompositionLayer compositionLayer2 = this.compositionLayer;
            if (compositionLayer2 != null) {
                compositionLayer2.setClipToCompositionBounds(clipToCompositionBounds2);
            }
            invalidateSelf();
        }
    }

    public boolean getClipToCompositionBounds() {
        return this.clipToCompositionBounds;
    }

    public void setImagesAssetsFolder(String imageAssetsFolder2) {
        this.imageAssetsFolder = imageAssetsFolder2;
    }

    public String getImageAssetsFolder() {
        return this.imageAssetsFolder;
    }

    public void setMaintainOriginalImageBounds(boolean maintainOriginalImageBounds2) {
        this.maintainOriginalImageBounds = maintainOriginalImageBounds2;
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.maintainOriginalImageBounds;
    }

    public boolean setComposition(LottieComposition composition2) {
        if (this.composition == composition2) {
            return false;
        }
        this.isDirty = true;
        clearComposition();
        this.composition = composition2;
        buildCompositionLayer();
        this.animator.setComposition(composition2);
        setProgress(this.animator.getAnimatedFraction());
        Iterator<LazyCompositionTask> it = new ArrayList(this.lazyCompositionTasks).iterator();
        while (it.hasNext()) {
            LazyCompositionTask t = it.next();
            if (t != null) {
                t.run(composition2);
            }
            it.remove();
        }
        this.lazyCompositionTasks.clear();
        composition2.setPerformanceTrackingEnabled(this.performanceTrackingEnabled);
        computeRenderMode();
        Drawable.Callback callback = getCallback();
        if (callback instanceof ImageView) {
            ((ImageView) callback).setImageDrawable((Drawable) null);
            ((ImageView) callback).setImageDrawable(this);
        }
        return true;
    }

    public void setRenderMode(RenderMode renderMode2) {
        this.renderMode = renderMode2;
        computeRenderMode();
    }

    public RenderMode getRenderMode() {
        return this.useSoftwareRendering ? RenderMode.SOFTWARE : RenderMode.HARDWARE;
    }

    private void computeRenderMode() {
        LottieComposition composition2 = this.composition;
        if (composition2 != null) {
            this.useSoftwareRendering = this.renderMode.useSoftwareRendering(Build.VERSION.SDK_INT, composition2.hasDashPattern(), composition2.getMaskAndMatteCount());
        }
    }

    public void setPerformanceTrackingEnabled(boolean enabled) {
        this.performanceTrackingEnabled = enabled;
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            lottieComposition.setPerformanceTrackingEnabled(enabled);
        }
    }

    public void setOutlineMasksAndMattes(boolean outline) {
        if (this.outlineMasksAndMattes != outline) {
            this.outlineMasksAndMattes = outline;
            CompositionLayer compositionLayer2 = this.compositionLayer;
            if (compositionLayer2 != null) {
                compositionLayer2.setOutlineMasksAndMattes(outline);
            }
        }
    }

    public PerformanceTracker getPerformanceTracker() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            return lottieComposition.getPerformanceTracker();
        }
        return null;
    }

    public void setApplyingOpacityToLayersEnabled(boolean isApplyingOpacityToLayersEnabled2) {
        this.isApplyingOpacityToLayersEnabled = isApplyingOpacityToLayersEnabled2;
    }

    @Deprecated
    public void disableExtraScaleModeInFitXY() {
    }

    public boolean isApplyingOpacityToLayersEnabled() {
        return this.isApplyingOpacityToLayersEnabled;
    }

    private void buildCompositionLayer() {
        LottieComposition composition2 = this.composition;
        if (composition2 != null) {
            CompositionLayer compositionLayer2 = new CompositionLayer(this, LayerParser.parse(composition2), composition2.getLayers(), composition2);
            this.compositionLayer = compositionLayer2;
            if (this.outlineMasksAndMattes) {
                compositionLayer2.setOutlineMasksAndMattes(true);
            }
            this.compositionLayer.setClipToCompositionBounds(this.clipToCompositionBounds);
        }
    }

    public void clearComposition() {
        if (this.animator.isRunning()) {
            this.animator.cancel();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
        this.composition = null;
        this.compositionLayer = null;
        this.imageAssetManager = null;
        this.animator.clearComposition();
        invalidateSelf();
    }

    public void setSafeMode(boolean safeMode2) {
        this.safeMode = safeMode2;
    }

    public void invalidateSelf() {
        if (!this.isDirty) {
            this.isDirty = true;
            Drawable.Callback callback = getCallback();
            if (callback != null) {
                callback.invalidateDrawable(this);
            }
        }
    }

    public void setAlpha(int alpha2) {
        this.alpha = alpha2;
        invalidateSelf();
    }

    public int getAlpha() {
        return this.alpha;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Logger.warning("Use addColorFilter instead.");
    }

    public int getOpacity() {
        return -3;
    }

    public void draw(Canvas canvas) {
        L.beginSection("Drawable#draw");
        if (this.safeMode) {
            try {
                if (this.useSoftwareRendering) {
                    renderAndDrawAsBitmap(canvas, this.compositionLayer);
                } else {
                    drawDirectlyToCanvas(canvas);
                }
            } catch (Throwable e) {
                Logger.error("Lottie crashed in draw!", e);
            }
        } else if (this.useSoftwareRendering) {
            renderAndDrawAsBitmap(canvas, this.compositionLayer);
        } else {
            drawDirectlyToCanvas(canvas);
        }
        this.isDirty = false;
        L.endSection("Drawable#draw");
    }

    public void draw(Canvas canvas, Matrix matrix) {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        LottieComposition composition2 = this.composition;
        if (compositionLayer2 != null && composition2 != null) {
            if (this.useSoftwareRendering) {
                canvas.save();
                canvas.concat(matrix);
                renderAndDrawAsBitmap(canvas, compositionLayer2);
                canvas.restore();
            } else {
                compositionLayer2.draw(canvas, matrix, this.alpha);
            }
            this.isDirty = false;
        }
    }

    public void start() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View) || !((View) callback).isInEditMode()) {
            playAnimation();
        }
    }

    public void stop() {
        endAnimation();
    }

    public boolean isRunning() {
        return isAnimating();
    }

    public void playAnimation() {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda2(this));
            return;
        }
        computeRenderMode();
        if (animationsEnabled() || getRepeatCount() == 0) {
            if (isVisible()) {
                this.animator.playAnimation();
                this.onVisibleAction = OnVisibleAction.NONE;
            } else {
                this.onVisibleAction = OnVisibleAction.PLAY;
            }
        }
        if (!animationsEnabled()) {
            setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
            this.animator.endAnimation();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$playAnimation$0$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1310lambda$playAnimation$0$comairbnblottieLottieDrawable(LottieComposition c) {
        playAnimation();
    }

    public void endAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.endAnimation();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public void resumeAnimation() {
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda10(this));
            return;
        }
        computeRenderMode();
        if (animationsEnabled() || getRepeatCount() == 0) {
            if (isVisible()) {
                this.animator.resumeAnimation();
                this.onVisibleAction = OnVisibleAction.NONE;
            } else {
                this.onVisibleAction = OnVisibleAction.RESUME;
            }
        }
        if (!animationsEnabled()) {
            setFrame((int) (getSpeed() < 0.0f ? getMinFrame() : getMaxFrame()));
            this.animator.endAnimation();
            if (!isVisible()) {
                this.onVisibleAction = OnVisibleAction.NONE;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$resumeAnimation$1$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1311lambda$resumeAnimation$1$comairbnblottieLottieDrawable(LottieComposition c) {
        resumeAnimation();
    }

    public void setMinFrame(int minFrame) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda7(this, minFrame));
        } else {
            this.animator.setMinFrame(minFrame);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMinFrame$2$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1320lambda$setMinFrame$2$comairbnblottieLottieDrawable(int minFrame, LottieComposition c) {
        setMinFrame(minFrame);
    }

    public float getMinFrame() {
        return this.animator.getMinFrame();
    }

    public void setMinProgress(float minProgress) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda8(this, minProgress));
        } else {
            setMinFrame((int) MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), minProgress));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMinProgress$3$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1322lambda$setMinProgress$3$comairbnblottieLottieDrawable(float minProgress, LottieComposition c) {
        setMinProgress(minProgress);
    }

    public void setMaxFrame(int maxFrame) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda9(this, maxFrame));
        } else {
            this.animator.setMaxFrame(((float) maxFrame) + 0.99f);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMaxFrame$4$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1313lambda$setMaxFrame$4$comairbnblottieLottieDrawable(int maxFrame, LottieComposition c) {
        setMaxFrame(maxFrame);
    }

    public float getMaxFrame() {
        return this.animator.getMaxFrame();
    }

    public void setMaxProgress(float maxProgress) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda1(this, maxProgress));
        } else {
            this.animator.setMaxFrame(MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), maxProgress));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMaxProgress$5$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1315lambda$setMaxProgress$5$comairbnblottieLottieDrawable(float maxProgress, LottieComposition c) {
        setMaxProgress(maxProgress);
    }

    public void setMinFrame(String markerName) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda13(this, markerName));
            return;
        }
        Marker marker = lottieComposition.getMarker(markerName);
        if (marker != null) {
            setMinFrame((int) marker.startFrame);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + markerName + ".");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMinFrame$6$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1321lambda$setMinFrame$6$comairbnblottieLottieDrawable(String markerName, LottieComposition c) {
        setMinFrame(markerName);
    }

    public void setMaxFrame(String markerName) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda11(this, markerName));
            return;
        }
        Marker marker = lottieComposition.getMarker(markerName);
        if (marker != null) {
            setMaxFrame((int) (marker.startFrame + marker.durationFrames));
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + markerName + ".");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMaxFrame$7$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1314lambda$setMaxFrame$7$comairbnblottieLottieDrawable(String markerName, LottieComposition c) {
        setMaxFrame(markerName);
    }

    public void setMinAndMaxFrame(String markerName) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda5(this, markerName));
            return;
        }
        Marker marker = lottieComposition.getMarker(markerName);
        if (marker != null) {
            int startFrame = (int) marker.startFrame;
            setMinAndMaxFrame(startFrame, ((int) marker.durationFrames) + startFrame);
            return;
        }
        throw new IllegalArgumentException("Cannot find marker with name " + markerName + ".");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMinAndMaxFrame$8$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1317lambda$setMinAndMaxFrame$8$comairbnblottieLottieDrawable(String markerName, LottieComposition c) {
        setMinAndMaxFrame(markerName);
    }

    public void setMinAndMaxFrame(String startMarkerName, String endMarkerName, boolean playEndMarkerStartFrame) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda12(this, startMarkerName, endMarkerName, playEndMarkerStartFrame));
            return;
        }
        Marker startMarker = lottieComposition.getMarker(startMarkerName);
        if (startMarker != null) {
            int startFrame = (int) startMarker.startFrame;
            Marker endMarker = this.composition.getMarker(endMarkerName);
            if (endMarker != null) {
                setMinAndMaxFrame(startFrame, (int) (endMarker.startFrame + (playEndMarkerStartFrame ? 1.0f : 0.0f)));
                return;
            }
            throw new IllegalArgumentException("Cannot find marker with name " + endMarkerName + ".");
        }
        throw new IllegalArgumentException("Cannot find marker with name " + startMarkerName + ".");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMinAndMaxFrame$9$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1318lambda$setMinAndMaxFrame$9$comairbnblottieLottieDrawable(String startMarkerName, String endMarkerName, boolean playEndMarkerStartFrame, LottieComposition c) {
        setMinAndMaxFrame(startMarkerName, endMarkerName, playEndMarkerStartFrame);
    }

    public void setMinAndMaxFrame(int minFrame, int maxFrame) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda4(this, minFrame, maxFrame));
        } else {
            this.animator.setMinAndMaxFrames((float) minFrame, ((float) maxFrame) + 0.99f);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMinAndMaxFrame$10$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1316lambda$setMinAndMaxFrame$10$comairbnblottieLottieDrawable(int minFrame, int maxFrame, LottieComposition c) {
        setMinAndMaxFrame(minFrame, maxFrame);
    }

    public void setMinAndMaxProgress(float minProgress, float maxProgress) {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda6(this, minProgress, maxProgress));
        } else {
            setMinAndMaxFrame((int) MiscUtils.lerp(lottieComposition.getStartFrame(), this.composition.getEndFrame(), minProgress), (int) MiscUtils.lerp(this.composition.getStartFrame(), this.composition.getEndFrame(), maxProgress));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setMinAndMaxProgress$11$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1319lambda$setMinAndMaxProgress$11$comairbnblottieLottieDrawable(float minProgress, float maxProgress, LottieComposition c) {
        setMinAndMaxProgress(minProgress, maxProgress);
    }

    public void reverseAnimationSpeed() {
        this.animator.reverseAnimationSpeed();
    }

    public void setSpeed(float speed) {
        this.animator.setSpeed(speed);
    }

    public float getSpeed() {
        return this.animator.getSpeed();
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener updateListener) {
        this.animator.addUpdateListener(updateListener);
    }

    public void removeAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener updateListener) {
        this.animator.removeUpdateListener(updateListener);
    }

    public void removeAllUpdateListeners() {
        this.animator.removeAllUpdateListeners();
        this.animator.addUpdateListener(this.progressUpdateListener);
    }

    public void addAnimatorListener(Animator.AnimatorListener listener) {
        this.animator.addListener(listener);
    }

    public void removeAnimatorListener(Animator.AnimatorListener listener) {
        this.animator.removeListener(listener);
    }

    public void removeAllAnimatorListeners() {
        this.animator.removeAllListeners();
    }

    public void addAnimatorPauseListener(Animator.AnimatorPauseListener listener) {
        this.animator.addPauseListener(listener);
    }

    public void removeAnimatorPauseListener(Animator.AnimatorPauseListener listener) {
        this.animator.removePauseListener(listener);
    }

    public void setFrame(int frame) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda3(this, frame));
        } else {
            this.animator.setFrame((float) frame);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setFrame$12$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1312lambda$setFrame$12$comairbnblottieLottieDrawable(int frame, LottieComposition c) {
        setFrame(frame);
    }

    public int getFrame() {
        return (int) this.animator.getFrame();
    }

    public void setProgress(float progress) {
        if (this.composition == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda0(this, progress));
            return;
        }
        L.beginSection("Drawable#setProgress");
        this.animator.setFrame(this.composition.getFrameForProgress(progress));
        L.endSection("Drawable#setProgress");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setProgress$13$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1323lambda$setProgress$13$comairbnblottieLottieDrawable(float progress, LottieComposition c) {
        setProgress(progress);
    }

    @Deprecated
    public void loop(boolean loop) {
        this.animator.setRepeatCount(loop ? -1 : 0);
    }

    public void setRepeatMode(int mode) {
        this.animator.setRepeatMode(mode);
    }

    public int getRepeatMode() {
        return this.animator.getRepeatMode();
    }

    public void setRepeatCount(int count) {
        this.animator.setRepeatCount(count);
    }

    public int getRepeatCount() {
        return this.animator.getRepeatCount();
    }

    public boolean isLooping() {
        return this.animator.getRepeatCount() == -1;
    }

    public boolean isAnimating() {
        LottieValueAnimator lottieValueAnimator = this.animator;
        if (lottieValueAnimator == null) {
            return false;
        }
        return lottieValueAnimator.isRunning();
    }

    /* access modifiers changed from: package-private */
    public boolean isAnimatingOrWillAnimateOnVisible() {
        if (isVisible()) {
            return this.animator.isRunning();
        }
        return this.onVisibleAction == OnVisibleAction.PLAY || this.onVisibleAction == OnVisibleAction.RESUME;
    }

    private boolean animationsEnabled() {
        return this.systemAnimationsEnabled || this.ignoreSystemAnimationsDisabled;
    }

    public void setSystemAnimationsAreEnabled(Boolean areEnabled) {
        this.systemAnimationsEnabled = areEnabled.booleanValue();
    }

    public void setIgnoreDisabledSystemAnimations(boolean ignore) {
        this.ignoreSystemAnimationsDisabled = ignore;
    }

    public void setImageAssetDelegate(ImageAssetDelegate assetDelegate) {
        this.imageAssetDelegate = assetDelegate;
        ImageAssetManager imageAssetManager2 = this.imageAssetManager;
        if (imageAssetManager2 != null) {
            imageAssetManager2.setDelegate(assetDelegate);
        }
    }

    public void setFontAssetDelegate(FontAssetDelegate assetDelegate) {
        this.fontAssetDelegate = assetDelegate;
        FontAssetManager fontAssetManager2 = this.fontAssetManager;
        if (fontAssetManager2 != null) {
            fontAssetManager2.setDelegate(assetDelegate);
        }
    }

    public void setTextDelegate(TextDelegate textDelegate2) {
        this.textDelegate = textDelegate2;
    }

    public TextDelegate getTextDelegate() {
        return this.textDelegate;
    }

    public boolean useTextGlyphs() {
        return this.textDelegate == null && this.composition.getCharacters().size() > 0;
    }

    public LottieComposition getComposition() {
        return this.composition;
    }

    public void cancelAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.cancel();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public void pauseAnimation() {
        this.lazyCompositionTasks.clear();
        this.animator.pauseAnimation();
        if (!isVisible()) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
    }

    public float getProgress() {
        return this.animator.getAnimatedValueAbsolute();
    }

    public int getIntrinsicWidth() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.getBounds().width();
    }

    public int getIntrinsicHeight() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return -1;
        }
        return lottieComposition.getBounds().height();
    }

    public List<KeyPath> resolveKeyPath(KeyPath keyPath) {
        if (this.compositionLayer == null) {
            Logger.warning("Cannot resolve KeyPath. Composition is not set yet.");
            return Collections.emptyList();
        }
        List<KeyPath> keyPaths = new ArrayList<>();
        this.compositionLayer.resolveKeyPath(keyPath, 0, keyPaths, new KeyPath(new String[0]));
        return keyPaths;
    }

    public <T> void addValueCallback(KeyPath keyPath, T property, LottieValueCallback<T> callback) {
        int invalidate;
        if (this.compositionLayer == null) {
            this.lazyCompositionTasks.add(new LottieDrawable$$ExternalSyntheticLambda14(this, keyPath, property, callback));
            return;
        }
        if (keyPath == KeyPath.COMPOSITION) {
            this.compositionLayer.addValueCallback(property, callback);
            invalidate = 1;
        } else if (keyPath.getResolvedElement() != null) {
            keyPath.getResolvedElement().addValueCallback(property, callback);
            invalidate = 1;
        } else {
            List<KeyPath> elements = resolveKeyPath(keyPath);
            for (int i = 0; i < elements.size(); i++) {
                elements.get(i).getResolvedElement().addValueCallback(property, callback);
            }
            invalidate = elements.isEmpty() ^ 1;
        }
        if (invalidate != 0) {
            invalidateSelf();
            if (property == LottieProperty.TIME_REMAP) {
                setProgress(getProgress());
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$addValueCallback$14$com-airbnb-lottie-LottieDrawable  reason: not valid java name */
    public /* synthetic */ void m1309lambda$addValueCallback$14$comairbnblottieLottieDrawable(KeyPath keyPath, Object property, LottieValueCallback callback, LottieComposition c) {
        addValueCallback(keyPath, property, callback);
    }

    public <T> void addValueCallback(KeyPath keyPath, T property, final SimpleLottieValueCallback<T> callback) {
        addValueCallback(keyPath, property, new LottieValueCallback<T>() {
            public T getValue(LottieFrameInfo<T> frameInfo) {
                return callback.getValue(frameInfo);
            }
        });
    }

    public Bitmap updateBitmap(String id, Bitmap bitmap) {
        ImageAssetManager bm = getImageAssetManager();
        if (bm == null) {
            Logger.warning("Cannot update bitmap. Most likely the drawable is not added to a View which prevents Lottie from getting a Context.");
            return null;
        }
        Bitmap ret = bm.updateBitmap(id, bitmap);
        invalidateSelf();
        return ret;
    }

    @Deprecated
    public Bitmap getImageAsset(String id) {
        ImageAssetManager bm = getImageAssetManager();
        if (bm != null) {
            return bm.bitmapForId(id);
        }
        LottieComposition lottieComposition = this.composition;
        LottieImageAsset imageAsset = lottieComposition == null ? null : lottieComposition.getImages().get(id);
        if (imageAsset != null) {
            return imageAsset.getBitmap();
        }
        return null;
    }

    public Bitmap getBitmapForId(String id) {
        ImageAssetManager assetManager = getImageAssetManager();
        if (assetManager != null) {
            return assetManager.bitmapForId(id);
        }
        return null;
    }

    public LottieImageAsset getLottieImageAssetForId(String id) {
        LottieComposition composition2 = this.composition;
        if (composition2 == null) {
            return null;
        }
        return composition2.getImages().get(id);
    }

    private ImageAssetManager getImageAssetManager() {
        if (getCallback() == null) {
            return null;
        }
        ImageAssetManager imageAssetManager2 = this.imageAssetManager;
        if (imageAssetManager2 != null && !imageAssetManager2.hasSameContext(getContext())) {
            this.imageAssetManager = null;
        }
        if (this.imageAssetManager == null) {
            this.imageAssetManager = new ImageAssetManager(getCallback(), this.imageAssetsFolder, this.imageAssetDelegate, this.composition.getImages());
        }
        return this.imageAssetManager;
    }

    public Typeface getTypeface(String fontFamily, String style) {
        FontAssetManager assetManager = getFontAssetManager();
        if (assetManager != null) {
            return assetManager.getTypeface(fontFamily, style);
        }
        return null;
    }

    private FontAssetManager getFontAssetManager() {
        if (getCallback() == null) {
            return null;
        }
        if (this.fontAssetManager == null) {
            this.fontAssetManager = new FontAssetManager(getCallback(), this.fontAssetDelegate);
        }
        return this.fontAssetManager;
    }

    private Context getContext() {
        Drawable.Callback callback = getCallback();
        if (callback != null && (callback instanceof View)) {
            return ((View) callback).getContext();
        }
        return null;
    }

    public boolean setVisible(boolean visible, boolean restart) {
        boolean wasNotVisibleAlready = !isVisible();
        boolean ret = super.setVisible(visible, restart);
        if (visible) {
            if (this.onVisibleAction == OnVisibleAction.PLAY) {
                playAnimation();
            } else if (this.onVisibleAction == OnVisibleAction.RESUME) {
                resumeAnimation();
            }
        } else if (this.animator.isRunning()) {
            pauseAnimation();
            this.onVisibleAction = OnVisibleAction.RESUME;
        } else if (!wasNotVisibleAlready) {
            this.onVisibleAction = OnVisibleAction.NONE;
        }
        return ret;
    }

    public void invalidateDrawable(Drawable who) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    public void unscheduleDrawable(Drawable who, Runnable what) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }

    private void drawDirectlyToCanvas(Canvas canvas) {
        CompositionLayer compositionLayer2 = this.compositionLayer;
        LottieComposition composition2 = this.composition;
        if (compositionLayer2 != null && composition2 != null) {
            this.renderingMatrix.reset();
            Rect bounds = getBounds();
            if (!bounds.isEmpty()) {
                this.renderingMatrix.preScale(((float) bounds.width()) / ((float) composition2.getBounds().width()), ((float) bounds.height()) / ((float) composition2.getBounds().height()));
            }
            compositionLayer2.draw(canvas, this.renderingMatrix, this.alpha);
        }
    }

    private void renderAndDrawAsBitmap(Canvas originalCanvas, CompositionLayer compositionLayer2) {
        if (this.composition != null && compositionLayer2 != null) {
            ensureSoftwareRenderingObjectsInitialized();
            originalCanvas.getMatrix(this.softwareRenderingOriginalCanvasMatrix);
            originalCanvas.getClipBounds(this.canvasClipBounds);
            convertRect(this.canvasClipBounds, this.canvasClipBoundsRectF);
            this.softwareRenderingOriginalCanvasMatrix.mapRect(this.canvasClipBoundsRectF);
            convertRect(this.canvasClipBoundsRectF, this.canvasClipBounds);
            if (this.clipToCompositionBounds) {
                this.softwareRenderingTransformedBounds.set(0.0f, 0.0f, (float) getIntrinsicWidth(), (float) getIntrinsicHeight());
            } else {
                compositionLayer2.getBounds(this.softwareRenderingTransformedBounds, (Matrix) null, false);
            }
            this.softwareRenderingOriginalCanvasMatrix.mapRect(this.softwareRenderingTransformedBounds);
            Rect bounds = getBounds();
            float scaleX = ((float) bounds.width()) / ((float) getIntrinsicWidth());
            float scaleY = ((float) bounds.height()) / ((float) getIntrinsicHeight());
            scaleRect(this.softwareRenderingTransformedBounds, scaleX, scaleY);
            if (!ignoreCanvasClipBounds()) {
                this.softwareRenderingTransformedBounds.intersect((float) this.canvasClipBounds.left, (float) this.canvasClipBounds.top, (float) this.canvasClipBounds.right, (float) this.canvasClipBounds.bottom);
            }
            int renderWidth = (int) Math.ceil((double) this.softwareRenderingTransformedBounds.width());
            int renderHeight = (int) Math.ceil((double) this.softwareRenderingTransformedBounds.height());
            if (renderWidth != 0 && renderHeight != 0) {
                ensureSoftwareRenderingBitmap(renderWidth, renderHeight);
                if (this.isDirty) {
                    this.renderingMatrix.set(this.softwareRenderingOriginalCanvasMatrix);
                    this.renderingMatrix.preScale(scaleX, scaleY);
                    this.renderingMatrix.postTranslate(-this.softwareRenderingTransformedBounds.left, -this.softwareRenderingTransformedBounds.top);
                    this.softwareRenderingBitmap.eraseColor(0);
                    compositionLayer2.draw(this.softwareRenderingCanvas, this.renderingMatrix, this.alpha);
                    this.softwareRenderingOriginalCanvasMatrix.invert(this.softwareRenderingOriginalCanvasMatrixInverse);
                    this.softwareRenderingOriginalCanvasMatrixInverse.mapRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingTransformedBounds);
                    convertRect(this.softwareRenderingDstBoundsRectF, this.softwareRenderingDstBoundsRect);
                }
                this.softwareRenderingSrcBoundsRect.set(0, 0, renderWidth, renderHeight);
                originalCanvas.drawBitmap(this.softwareRenderingBitmap, this.softwareRenderingSrcBoundsRect, this.softwareRenderingDstBoundsRect, this.softwareRenderingPaint);
            }
        }
    }

    private void ensureSoftwareRenderingObjectsInitialized() {
        if (this.softwareRenderingCanvas == null) {
            this.softwareRenderingCanvas = new Canvas();
            this.softwareRenderingTransformedBounds = new RectF();
            this.softwareRenderingOriginalCanvasMatrix = new Matrix();
            this.softwareRenderingOriginalCanvasMatrixInverse = new Matrix();
            this.canvasClipBounds = new Rect();
            this.canvasClipBoundsRectF = new RectF();
            this.softwareRenderingPaint = new LPaint();
            this.softwareRenderingSrcBoundsRect = new Rect();
            this.softwareRenderingDstBoundsRect = new Rect();
            this.softwareRenderingDstBoundsRectF = new RectF();
        }
    }

    private void ensureSoftwareRenderingBitmap(int renderWidth, int renderHeight) {
        Bitmap bitmap = this.softwareRenderingBitmap;
        if (bitmap == null || bitmap.getWidth() < renderWidth || this.softwareRenderingBitmap.getHeight() < renderHeight) {
            Bitmap createBitmap = Bitmap.createBitmap(renderWidth, renderHeight, Bitmap.Config.ARGB_8888);
            this.softwareRenderingBitmap = createBitmap;
            this.softwareRenderingCanvas.setBitmap(createBitmap);
            this.isDirty = true;
        } else if (this.softwareRenderingBitmap.getWidth() > renderWidth || this.softwareRenderingBitmap.getHeight() > renderHeight) {
            Bitmap createBitmap2 = Bitmap.createBitmap(this.softwareRenderingBitmap, 0, 0, renderWidth, renderHeight);
            this.softwareRenderingBitmap = createBitmap2;
            this.softwareRenderingCanvas.setBitmap(createBitmap2);
            this.isDirty = true;
        }
    }

    private void convertRect(RectF src, Rect dst) {
        dst.set((int) Math.floor((double) src.left), (int) Math.floor((double) src.top), (int) Math.ceil((double) src.right), (int) Math.ceil((double) src.bottom));
    }

    private void convertRect(Rect src, RectF dst) {
        dst.set((float) src.left, (float) src.top, (float) src.right, (float) src.bottom);
    }

    private void scaleRect(RectF rect, float scaleX, float scaleY) {
        rect.set(rect.left * scaleX, rect.top * scaleY, rect.right * scaleX, rect.bottom * scaleY);
    }

    private boolean ignoreCanvasClipBounds() {
        Drawable.Callback callback = getCallback();
        if (!(callback instanceof View)) {
            return false;
        }
        ViewParent parent = ((View) callback).getParent();
        if (Build.VERSION.SDK_INT < 18 || !(parent instanceof ViewGroup)) {
            return false;
        }
        return !((ViewGroup) parent).getClipChildren();
    }
}
