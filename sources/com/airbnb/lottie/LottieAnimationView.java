package com.airbnb.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.utils.Logger;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottieAnimationView extends AppCompatImageView {
    /* access modifiers changed from: private */
    public static final LottieListener<Throwable> DEFAULT_FAILURE_LISTENER = new LottieAnimationView$$ExternalSyntheticLambda2();
    private static final String TAG = LottieAnimationView.class.getSimpleName();
    private String animationName;
    private int animationResId;
    private boolean autoPlay = false;
    private boolean cacheComposition = true;
    private LottieComposition composition;
    private LottieTask<LottieComposition> compositionTask;
    /* access modifiers changed from: private */
    public LottieListener<Throwable> failureListener;
    /* access modifiers changed from: private */
    public int fallbackResource = 0;
    private boolean ignoreUnschedule = false;
    private final LottieListener<LottieComposition> loadedListener = new LottieAnimationView$$ExternalSyntheticLambda1(this);
    private final LottieDrawable lottieDrawable = new LottieDrawable();
    private final Set<LottieOnCompositionLoadedListener> lottieOnCompositionLoadedListeners = new HashSet();
    private final Set<UserActionTaken> userActionsTaken = new HashSet();
    private final LottieListener<Throwable> wrappedFailureListener = new LottieListener<Throwable>() {
        public void onResult(Throwable result) {
            if (LottieAnimationView.this.fallbackResource != 0) {
                LottieAnimationView lottieAnimationView = LottieAnimationView.this;
                lottieAnimationView.setImageResource(lottieAnimationView.fallbackResource);
            }
            (LottieAnimationView.this.failureListener == null ? LottieAnimationView.DEFAULT_FAILURE_LISTENER : LottieAnimationView.this.failureListener).onResult(result);
        }
    };

    private enum UserActionTaken {
        SET_ANIMATION,
        SET_PROGRESS,
        SET_REPEAT_MODE,
        SET_REPEAT_COUNT,
        SET_IMAGE_ASSETS,
        PLAY_OPTION
    }

    static /* synthetic */ void lambda$static$0(Throwable throwable) {
        if (Utils.isNetworkException(throwable)) {
            Logger.warning("Unable to load composition.", throwable);
            return;
        }
        throw new IllegalStateException("Unable to parse composition", throwable);
    }

    public LottieAnimationView(Context context) {
        super(context);
        init((AttributeSet) null, R.attr.lottieAnimationViewStyle);
    }

    public LottieAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, R.attr.lottieAnimationViewStyle);
    }

    public LottieAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        String url;
        boolean z = false;
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.LottieAnimationView, defStyleAttr, 0);
        this.cacheComposition = ta.getBoolean(R.styleable.LottieAnimationView_lottie_cacheComposition, true);
        boolean hasRawRes = ta.hasValue(R.styleable.LottieAnimationView_lottie_rawRes);
        boolean hasFileName = ta.hasValue(R.styleable.LottieAnimationView_lottie_fileName);
        boolean hasUrl = ta.hasValue(R.styleable.LottieAnimationView_lottie_url);
        if (!hasRawRes || !hasFileName) {
            if (hasRawRes) {
                int rawResId = ta.getResourceId(R.styleable.LottieAnimationView_lottie_rawRes, 0);
                if (rawResId != 0) {
                    setAnimation(rawResId);
                }
            } else if (hasFileName) {
                String fileName = ta.getString(R.styleable.LottieAnimationView_lottie_fileName);
                if (fileName != null) {
                    setAnimation(fileName);
                }
            } else if (hasUrl && (url = ta.getString(R.styleable.LottieAnimationView_lottie_url)) != null) {
                setAnimationFromUrl(url);
            }
            setFallbackResource(ta.getResourceId(R.styleable.LottieAnimationView_lottie_fallbackRes, 0));
            if (ta.getBoolean(R.styleable.LottieAnimationView_lottie_autoPlay, false)) {
                this.autoPlay = true;
            }
            if (ta.getBoolean(R.styleable.LottieAnimationView_lottie_loop, false)) {
                this.lottieDrawable.setRepeatCount(-1);
            }
            if (ta.hasValue(R.styleable.LottieAnimationView_lottie_repeatMode)) {
                setRepeatMode(ta.getInt(R.styleable.LottieAnimationView_lottie_repeatMode, 1));
            }
            if (ta.hasValue(R.styleable.LottieAnimationView_lottie_repeatCount)) {
                setRepeatCount(ta.getInt(R.styleable.LottieAnimationView_lottie_repeatCount, -1));
            }
            if (ta.hasValue(R.styleable.LottieAnimationView_lottie_speed)) {
                setSpeed(ta.getFloat(R.styleable.LottieAnimationView_lottie_speed, 1.0f));
            }
            if (ta.hasValue(R.styleable.LottieAnimationView_lottie_clipToCompositionBounds)) {
                setClipToCompositionBounds(ta.getBoolean(R.styleable.LottieAnimationView_lottie_clipToCompositionBounds, true));
            }
            setImageAssetsFolder(ta.getString(R.styleable.LottieAnimationView_lottie_imageAssetsFolder));
            setProgress(ta.getFloat(R.styleable.LottieAnimationView_lottie_progress, 0.0f));
            enableMergePathsForKitKatAndAbove(ta.getBoolean(R.styleable.LottieAnimationView_lottie_enableMergePathsForKitKatAndAbove, false));
            if (ta.hasValue(R.styleable.LottieAnimationView_lottie_colorFilter)) {
                SimpleColorFilter filter = new SimpleColorFilter(AppCompatResources.getColorStateList(getContext(), ta.getResourceId(R.styleable.LottieAnimationView_lottie_colorFilter, -1)).getDefaultColor());
                addValueCallback(new KeyPath("**"), LottieProperty.COLOR_FILTER, new LottieValueCallback<>(filter));
            }
            if (ta.hasValue(R.styleable.LottieAnimationView_lottie_renderMode)) {
                int renderModeOrdinal = ta.getInt(R.styleable.LottieAnimationView_lottie_renderMode, RenderMode.AUTOMATIC.ordinal());
                if (renderModeOrdinal >= RenderMode.values().length) {
                    renderModeOrdinal = RenderMode.AUTOMATIC.ordinal();
                }
                setRenderMode(RenderMode.values()[renderModeOrdinal]);
            }
            setIgnoreDisabledSystemAnimations(ta.getBoolean(R.styleable.LottieAnimationView_lottie_ignoreDisabledSystemAnimations, false));
            ta.recycle();
            LottieDrawable lottieDrawable2 = this.lottieDrawable;
            if (Utils.getAnimationScale(getContext()) != 0.0f) {
                z = true;
            }
            lottieDrawable2.setSystemAnimationsAreEnabled(Boolean.valueOf(z));
            return;
        }
        throw new IllegalArgumentException("lottie_rawRes and lottie_fileName cannot be used at the same time. Please use only one at once.");
    }

    public void setImageResource(int resId) {
        cancelLoaderTask();
        super.setImageResource(resId);
    }

    public void setImageDrawable(Drawable drawable) {
        cancelLoaderTask();
        super.setImageDrawable(drawable);
    }

    public void setImageBitmap(Bitmap bm) {
        cancelLoaderTask();
        super.setImageBitmap(bm);
    }

    public void unscheduleDrawable(Drawable who) {
        LottieDrawable lottieDrawable2;
        if (!this.ignoreUnschedule && who == (lottieDrawable2 = this.lottieDrawable) && lottieDrawable2.isAnimating()) {
            pauseAnimation();
        } else if (!this.ignoreUnschedule && (who instanceof LottieDrawable) && ((LottieDrawable) who).isAnimating()) {
            ((LottieDrawable) who).pauseAnimation();
        }
        super.unscheduleDrawable(who);
    }

    public void invalidate() {
        super.invalidate();
        Drawable d = getDrawable();
        if ((d instanceof LottieDrawable) && ((LottieDrawable) d).getRenderMode() == RenderMode.SOFTWARE) {
            this.lottieDrawable.invalidateSelf();
        }
    }

    public void invalidateDrawable(Drawable dr) {
        Drawable drawable = getDrawable();
        LottieDrawable lottieDrawable2 = this.lottieDrawable;
        if (drawable == lottieDrawable2) {
            super.invalidateDrawable(lottieDrawable2);
        } else {
            super.invalidateDrawable(dr);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.animationName = this.animationName;
        ss.animationResId = this.animationResId;
        ss.progress = this.lottieDrawable.getProgress();
        ss.isAnimating = this.lottieDrawable.isAnimatingOrWillAnimateOnVisible();
        ss.imageAssetsFolder = this.lottieDrawable.getImageAssetsFolder();
        ss.repeatMode = this.lottieDrawable.getRepeatMode();
        ss.repeatCount = this.lottieDrawable.getRepeatCount();
        return ss;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable state) {
        int i;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        this.animationName = ss.animationName;
        if (!this.userActionsTaken.contains(UserActionTaken.SET_ANIMATION) && !TextUtils.isEmpty(this.animationName)) {
            setAnimation(this.animationName);
        }
        this.animationResId = ss.animationResId;
        if (!this.userActionsTaken.contains(UserActionTaken.SET_ANIMATION) && (i = this.animationResId) != 0) {
            setAnimation(i);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_PROGRESS)) {
            setProgress(ss.progress);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.PLAY_OPTION) && ss.isAnimating) {
            playAnimation();
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_IMAGE_ASSETS)) {
            setImageAssetsFolder(ss.imageAssetsFolder);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_REPEAT_MODE)) {
            setRepeatMode(ss.repeatMode);
        }
        if (!this.userActionsTaken.contains(UserActionTaken.SET_REPEAT_COUNT)) {
            setRepeatCount(ss.repeatCount);
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode() && this.autoPlay) {
            this.lottieDrawable.playAnimation();
        }
    }

    public void setIgnoreDisabledSystemAnimations(boolean ignore) {
        this.lottieDrawable.setIgnoreDisabledSystemAnimations(ignore);
    }

    public void enableMergePathsForKitKatAndAbove(boolean enable) {
        this.lottieDrawable.enableMergePathsForKitKatAndAbove(enable);
    }

    public boolean isMergePathsEnabledForKitKatAndAbove() {
        return this.lottieDrawable.isMergePathsEnabledForKitKatAndAbove();
    }

    public void setClipToCompositionBounds(boolean clipToCompositionBounds) {
        this.lottieDrawable.setClipToCompositionBounds(clipToCompositionBounds);
    }

    public boolean getClipToCompositionBounds() {
        return this.lottieDrawable.getClipToCompositionBounds();
    }

    public void setCacheComposition(boolean cacheComposition2) {
        this.cacheComposition = cacheComposition2;
    }

    public void setOutlineMasksAndMattes(boolean outline) {
        this.lottieDrawable.setOutlineMasksAndMattes(outline);
    }

    public void setAnimation(int rawRes) {
        this.animationResId = rawRes;
        this.animationName = null;
        setCompositionTask(fromRawRes(rawRes));
    }

    private LottieTask<LottieComposition> fromRawRes(int rawRes) {
        if (isInEditMode()) {
            return new LottieTask<>(new LottieAnimationView$$ExternalSyntheticLambda0(this, rawRes), true);
        }
        return this.cacheComposition ? LottieCompositionFactory.fromRawRes(getContext(), rawRes) : LottieCompositionFactory.fromRawRes(getContext(), rawRes, (String) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$fromRawRes$1$com-airbnb-lottie-LottieAnimationView  reason: not valid java name */
    public /* synthetic */ LottieResult m1308lambda$fromRawRes$1$comairbnblottieLottieAnimationView(int rawRes) throws Exception {
        return this.cacheComposition ? LottieCompositionFactory.fromRawResSync(getContext(), rawRes) : LottieCompositionFactory.fromRawResSync(getContext(), rawRes, (String) null);
    }

    public void setAnimation(String assetName) {
        this.animationName = assetName;
        this.animationResId = 0;
        setCompositionTask(fromAssets(assetName));
    }

    private LottieTask<LottieComposition> fromAssets(String assetName) {
        if (isInEditMode()) {
            return new LottieTask<>(new LottieAnimationView$$ExternalSyntheticLambda3(this, assetName), true);
        }
        return this.cacheComposition ? LottieCompositionFactory.fromAsset(getContext(), assetName) : LottieCompositionFactory.fromAsset(getContext(), assetName, (String) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$fromAssets$2$com-airbnb-lottie-LottieAnimationView  reason: not valid java name */
    public /* synthetic */ LottieResult m1307lambda$fromAssets$2$comairbnblottieLottieAnimationView(String assetName) throws Exception {
        return this.cacheComposition ? LottieCompositionFactory.fromAssetSync(getContext(), assetName) : LottieCompositionFactory.fromAssetSync(getContext(), assetName, (String) null);
    }

    @Deprecated
    public void setAnimationFromJson(String jsonString) {
        setAnimationFromJson(jsonString, (String) null);
    }

    public void setAnimationFromJson(String jsonString, String cacheKey) {
        setAnimation(new ByteArrayInputStream(jsonString.getBytes()), cacheKey);
    }

    public void setAnimation(InputStream stream, String cacheKey) {
        setCompositionTask(LottieCompositionFactory.fromJsonInputStream(stream, cacheKey));
    }

    public void setAnimationFromUrl(String url) {
        setCompositionTask(this.cacheComposition ? LottieCompositionFactory.fromUrl(getContext(), url) : LottieCompositionFactory.fromUrl(getContext(), url, (String) null));
    }

    public void setAnimationFromUrl(String url, String cacheKey) {
        setCompositionTask(LottieCompositionFactory.fromUrl(getContext(), url, cacheKey));
    }

    public void setFailureListener(LottieListener<Throwable> failureListener2) {
        this.failureListener = failureListener2;
    }

    public void setFallbackResource(int fallbackResource2) {
        this.fallbackResource = fallbackResource2;
    }

    private void setCompositionTask(LottieTask<LottieComposition> compositionTask2) {
        this.userActionsTaken.add(UserActionTaken.SET_ANIMATION);
        clearComposition();
        cancelLoaderTask();
        this.compositionTask = compositionTask2.addListener(this.loadedListener).addFailureListener(this.wrappedFailureListener);
    }

    private void cancelLoaderTask() {
        LottieTask<LottieComposition> lottieTask = this.compositionTask;
        if (lottieTask != null) {
            lottieTask.removeListener(this.loadedListener);
            this.compositionTask.removeFailureListener(this.wrappedFailureListener);
        }
    }

    public void setComposition(LottieComposition composition2) {
        if (L.DBG) {
            Log.v(TAG, "Set Composition \n" + composition2);
        }
        this.lottieDrawable.setCallback(this);
        this.composition = composition2;
        this.ignoreUnschedule = true;
        boolean isNewComposition = this.lottieDrawable.setComposition(composition2);
        this.ignoreUnschedule = false;
        if (getDrawable() != this.lottieDrawable || isNewComposition) {
            if (!isNewComposition) {
                setLottieDrawable();
            }
            onVisibilityChanged(this, getVisibility());
            requestLayout();
            for (LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener : this.lottieOnCompositionLoadedListeners) {
                lottieOnCompositionLoadedListener.onCompositionLoaded(composition2);
            }
        }
    }

    public LottieComposition getComposition() {
        return this.composition;
    }

    public boolean hasMasks() {
        return this.lottieDrawable.hasMasks();
    }

    public boolean hasMatte() {
        return this.lottieDrawable.hasMatte();
    }

    public void playAnimation() {
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.playAnimation();
    }

    public void resumeAnimation() {
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.resumeAnimation();
    }

    public void setMinFrame(int startFrame) {
        this.lottieDrawable.setMinFrame(startFrame);
    }

    public float getMinFrame() {
        return this.lottieDrawable.getMinFrame();
    }

    public void setMinProgress(float startProgress) {
        this.lottieDrawable.setMinProgress(startProgress);
    }

    public void setMaxFrame(int endFrame) {
        this.lottieDrawable.setMaxFrame(endFrame);
    }

    public float getMaxFrame() {
        return this.lottieDrawable.getMaxFrame();
    }

    public void setMaxProgress(float endProgress) {
        this.lottieDrawable.setMaxProgress(endProgress);
    }

    public void setMinFrame(String markerName) {
        this.lottieDrawable.setMinFrame(markerName);
    }

    public void setMaxFrame(String markerName) {
        this.lottieDrawable.setMaxFrame(markerName);
    }

    public void setMinAndMaxFrame(String markerName) {
        this.lottieDrawable.setMinAndMaxFrame(markerName);
    }

    public void setMinAndMaxFrame(String startMarkerName, String endMarkerName, boolean playEndMarkerStartFrame) {
        this.lottieDrawable.setMinAndMaxFrame(startMarkerName, endMarkerName, playEndMarkerStartFrame);
    }

    public void setMinAndMaxFrame(int minFrame, int maxFrame) {
        this.lottieDrawable.setMinAndMaxFrame(minFrame, maxFrame);
    }

    public void setMinAndMaxProgress(float minProgress, float maxProgress) {
        this.lottieDrawable.setMinAndMaxProgress(minProgress, maxProgress);
    }

    public void reverseAnimationSpeed() {
        this.lottieDrawable.reverseAnimationSpeed();
    }

    public void setSpeed(float speed) {
        this.lottieDrawable.setSpeed(speed);
    }

    public float getSpeed() {
        return this.lottieDrawable.getSpeed();
    }

    public void addAnimatorUpdateListener(ValueAnimator.AnimatorUpdateListener updateListener) {
        this.lottieDrawable.addAnimatorUpdateListener(updateListener);
    }

    public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener updateListener) {
        this.lottieDrawable.removeAnimatorUpdateListener(updateListener);
    }

    public void removeAllUpdateListeners() {
        this.lottieDrawable.removeAllUpdateListeners();
    }

    public void addAnimatorListener(Animator.AnimatorListener listener) {
        this.lottieDrawable.addAnimatorListener(listener);
    }

    public void removeAnimatorListener(Animator.AnimatorListener listener) {
        this.lottieDrawable.removeAnimatorListener(listener);
    }

    public void removeAllAnimatorListeners() {
        this.lottieDrawable.removeAllAnimatorListeners();
    }

    public void addAnimatorPauseListener(Animator.AnimatorPauseListener listener) {
        this.lottieDrawable.addAnimatorPauseListener(listener);
    }

    public void removeAnimatorPauseListener(Animator.AnimatorPauseListener listener) {
        this.lottieDrawable.removeAnimatorPauseListener(listener);
    }

    @Deprecated
    public void loop(boolean loop) {
        this.lottieDrawable.setRepeatCount(loop ? -1 : 0);
    }

    public void setRepeatMode(int mode) {
        this.userActionsTaken.add(UserActionTaken.SET_REPEAT_MODE);
        this.lottieDrawable.setRepeatMode(mode);
    }

    public int getRepeatMode() {
        return this.lottieDrawable.getRepeatMode();
    }

    public void setRepeatCount(int count) {
        this.userActionsTaken.add(UserActionTaken.SET_REPEAT_COUNT);
        this.lottieDrawable.setRepeatCount(count);
    }

    public int getRepeatCount() {
        return this.lottieDrawable.getRepeatCount();
    }

    public boolean isAnimating() {
        return this.lottieDrawable.isAnimating();
    }

    public void setImageAssetsFolder(String imageAssetsFolder) {
        this.lottieDrawable.setImagesAssetsFolder(imageAssetsFolder);
    }

    public String getImageAssetsFolder() {
        return this.lottieDrawable.getImageAssetsFolder();
    }

    public void setMaintainOriginalImageBounds(boolean maintainOriginalImageBounds) {
        this.lottieDrawable.setMaintainOriginalImageBounds(maintainOriginalImageBounds);
    }

    public boolean getMaintainOriginalImageBounds() {
        return this.lottieDrawable.getMaintainOriginalImageBounds();
    }

    public Bitmap updateBitmap(String id, Bitmap bitmap) {
        return this.lottieDrawable.updateBitmap(id, bitmap);
    }

    public void setImageAssetDelegate(ImageAssetDelegate assetDelegate) {
        this.lottieDrawable.setImageAssetDelegate(assetDelegate);
    }

    public void setFontAssetDelegate(FontAssetDelegate assetDelegate) {
        this.lottieDrawable.setFontAssetDelegate(assetDelegate);
    }

    public void setTextDelegate(TextDelegate textDelegate) {
        this.lottieDrawable.setTextDelegate(textDelegate);
    }

    public List<KeyPath> resolveKeyPath(KeyPath keyPath) {
        return this.lottieDrawable.resolveKeyPath(keyPath);
    }

    public <T> void addValueCallback(KeyPath keyPath, T property, LottieValueCallback<T> callback) {
        this.lottieDrawable.addValueCallback(keyPath, property, callback);
    }

    public <T> void addValueCallback(KeyPath keyPath, T property, final SimpleLottieValueCallback<T> callback) {
        this.lottieDrawable.addValueCallback(keyPath, property, new LottieValueCallback<T>() {
            public T getValue(LottieFrameInfo<T> frameInfo) {
                return callback.getValue(frameInfo);
            }
        });
    }

    public void cancelAnimation() {
        this.userActionsTaken.add(UserActionTaken.PLAY_OPTION);
        this.lottieDrawable.cancelAnimation();
    }

    public void pauseAnimation() {
        this.autoPlay = false;
        this.lottieDrawable.pauseAnimation();
    }

    public void setFrame(int frame) {
        this.lottieDrawable.setFrame(frame);
    }

    public int getFrame() {
        return this.lottieDrawable.getFrame();
    }

    public void setProgress(float progress) {
        this.userActionsTaken.add(UserActionTaken.SET_PROGRESS);
        this.lottieDrawable.setProgress(progress);
    }

    public float getProgress() {
        return this.lottieDrawable.getProgress();
    }

    public long getDuration() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition != null) {
            return (long) lottieComposition.getDuration();
        }
        return 0;
    }

    public void setPerformanceTrackingEnabled(boolean enabled) {
        this.lottieDrawable.setPerformanceTrackingEnabled(enabled);
    }

    public PerformanceTracker getPerformanceTracker() {
        return this.lottieDrawable.getPerformanceTracker();
    }

    private void clearComposition() {
        this.composition = null;
        this.lottieDrawable.clearComposition();
    }

    public void setSafeMode(boolean safeMode) {
        this.lottieDrawable.setSafeMode(safeMode);
    }

    public void setRenderMode(RenderMode renderMode) {
        this.lottieDrawable.setRenderMode(renderMode);
    }

    public RenderMode getRenderMode() {
        return this.lottieDrawable.getRenderMode();
    }

    public void setApplyingOpacityToLayersEnabled(boolean isApplyingOpacityToLayersEnabled) {
        this.lottieDrawable.setApplyingOpacityToLayersEnabled(isApplyingOpacityToLayersEnabled);
    }

    @Deprecated
    public void disableExtraScaleModeInFitXY() {
        this.lottieDrawable.disableExtraScaleModeInFitXY();
    }

    public boolean addLottieOnCompositionLoadedListener(LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener) {
        LottieComposition composition2 = this.composition;
        if (composition2 != null) {
            lottieOnCompositionLoadedListener.onCompositionLoaded(composition2);
        }
        return this.lottieOnCompositionLoadedListeners.add(lottieOnCompositionLoadedListener);
    }

    public boolean removeLottieOnCompositionLoadedListener(LottieOnCompositionLoadedListener lottieOnCompositionLoadedListener) {
        return this.lottieOnCompositionLoadedListeners.remove(lottieOnCompositionLoadedListener);
    }

    public void removeAllLottieOnCompositionLoadedListener() {
        this.lottieOnCompositionLoadedListeners.clear();
    }

    private void setLottieDrawable() {
        boolean wasAnimating = isAnimating();
        setImageDrawable((Drawable) null);
        setImageDrawable(this.lottieDrawable);
        if (wasAnimating) {
            this.lottieDrawable.resumeAnimation();
        }
    }

    private static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        String animationName;
        int animationResId;
        String imageAssetsFolder;
        boolean isAnimating;
        float progress;
        int repeatCount;
        int repeatMode;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.animationName = in.readString();
            this.progress = in.readFloat();
            this.isAnimating = in.readInt() != 1 ? false : true;
            this.imageAssetsFolder = in.readString();
            this.repeatMode = in.readInt();
            this.repeatCount = in.readInt();
        }

        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(this.animationName);
            out.writeFloat(this.progress);
            out.writeInt(this.isAnimating ? 1 : 0);
            out.writeString(this.imageAssetsFolder);
            out.writeInt(this.repeatMode);
            out.writeInt(this.repeatCount);
        }
    }
}
