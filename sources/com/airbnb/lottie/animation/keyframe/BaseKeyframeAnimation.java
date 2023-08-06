package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.L;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseKeyframeAnimation<K, A> {
    private float cachedEndProgress = -1.0f;
    private A cachedGetValue = null;
    private float cachedStartDelayProgress = -1.0f;
    private boolean isDiscrete = false;
    private final KeyframesWrapper<K> keyframesWrapper;
    final List<AnimationListener> listeners = new ArrayList(1);
    protected float progress = 0.0f;
    protected LottieValueCallback<A> valueCallback;

    public interface AnimationListener {
        void onValueChanged();
    }

    private interface KeyframesWrapper<T> {
        Keyframe<T> getCurrentKeyframe();

        float getEndProgress();

        float getStartDelayProgress();

        boolean isCachedValueEnabled(float f);

        boolean isEmpty();

        boolean isValueChanged(float f);
    }

    /* access modifiers changed from: package-private */
    public abstract A getValue(Keyframe<K> keyframe, float f);

    BaseKeyframeAnimation(List<? extends Keyframe<K>> keyframes) {
        this.keyframesWrapper = wrap(keyframes);
    }

    public void setIsDiscrete() {
        this.isDiscrete = true;
    }

    public void addUpdateListener(AnimationListener listener) {
        this.listeners.add(listener);
    }

    public void setProgress(float progress2) {
        if (!this.keyframesWrapper.isEmpty()) {
            if (progress2 < getStartDelayProgress()) {
                progress2 = getStartDelayProgress();
            } else if (progress2 > getEndProgress()) {
                progress2 = getEndProgress();
            }
            if (progress2 != this.progress) {
                this.progress = progress2;
                if (this.keyframesWrapper.isValueChanged(progress2)) {
                    notifyListeners();
                }
            }
        }
    }

    public void notifyListeners() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).onValueChanged();
        }
    }

    /* access modifiers changed from: protected */
    public Keyframe<K> getCurrentKeyframe() {
        L.beginSection("BaseKeyframeAnimation#getCurrentKeyframe");
        Keyframe<K> keyframe = this.keyframesWrapper.getCurrentKeyframe();
        L.endSection("BaseKeyframeAnimation#getCurrentKeyframe");
        return keyframe;
    }

    /* access modifiers changed from: package-private */
    public float getLinearCurrentKeyframeProgress() {
        if (this.isDiscrete) {
            return 0.0f;
        }
        Keyframe<K> keyframe = getCurrentKeyframe();
        if (keyframe.isStatic()) {
            return 0.0f;
        }
        return (this.progress - keyframe.getStartProgress()) / (keyframe.getEndProgress() - keyframe.getStartProgress());
    }

    /* access modifiers changed from: protected */
    public float getInterpolatedCurrentKeyframeProgress() {
        Keyframe<K> keyframe = getCurrentKeyframe();
        if (keyframe == null || keyframe.isStatic()) {
            return 0.0f;
        }
        return keyframe.interpolator.getInterpolation(getLinearCurrentKeyframeProgress());
    }

    private float getStartDelayProgress() {
        if (this.cachedStartDelayProgress == -1.0f) {
            this.cachedStartDelayProgress = this.keyframesWrapper.getStartDelayProgress();
        }
        return this.cachedStartDelayProgress;
    }

    /* access modifiers changed from: package-private */
    public float getEndProgress() {
        if (this.cachedEndProgress == -1.0f) {
            this.cachedEndProgress = this.keyframesWrapper.getEndProgress();
        }
        return this.cachedEndProgress;
    }

    public A getValue() {
        A value;
        float linearProgress = getLinearCurrentKeyframeProgress();
        if (this.valueCallback == null && this.keyframesWrapper.isCachedValueEnabled(linearProgress)) {
            return this.cachedGetValue;
        }
        Keyframe<K> keyframe = getCurrentKeyframe();
        if (keyframe.xInterpolator == null || keyframe.yInterpolator == null) {
            value = getValue(keyframe, getInterpolatedCurrentKeyframeProgress());
        } else {
            value = getValue(keyframe, linearProgress, keyframe.xInterpolator.getInterpolation(linearProgress), keyframe.yInterpolator.getInterpolation(linearProgress));
        }
        this.cachedGetValue = value;
        return value;
    }

    public float getProgress() {
        return this.progress;
    }

    public void setValueCallback(LottieValueCallback<A> valueCallback2) {
        LottieValueCallback<A> lottieValueCallback = this.valueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation((BaseKeyframeAnimation<?, ?>) null);
        }
        this.valueCallback = valueCallback2;
        if (valueCallback2 != null) {
            valueCallback2.setAnimation(this);
        }
    }

    /* access modifiers changed from: protected */
    public A getValue(Keyframe<K> keyframe, float linearKeyframeProgress, float xKeyframeProgress, float yKeyframeProgress) {
        throw new UnsupportedOperationException("This animation does not support split dimensions!");
    }

    private static <T> KeyframesWrapper<T> wrap(List<? extends Keyframe<T>> keyframes) {
        if (keyframes.isEmpty()) {
            return new EmptyKeyframeWrapper();
        }
        if (keyframes.size() == 1) {
            return new SingleKeyframeWrapper(keyframes);
        }
        return new KeyframesWrapperImpl(keyframes);
    }

    private static final class EmptyKeyframeWrapper<T> implements KeyframesWrapper<T> {
        private EmptyKeyframeWrapper() {
        }

        public boolean isEmpty() {
            return true;
        }

        public boolean isValueChanged(float progress) {
            return false;
        }

        public Keyframe<T> getCurrentKeyframe() {
            throw new IllegalStateException("not implemented");
        }

        public float getStartDelayProgress() {
            return 0.0f;
        }

        public float getEndProgress() {
            return 1.0f;
        }

        public boolean isCachedValueEnabled(float progress) {
            throw new IllegalStateException("not implemented");
        }
    }

    private static final class SingleKeyframeWrapper<T> implements KeyframesWrapper<T> {
        private float cachedInterpolatedProgress = -1.0f;
        private final Keyframe<T> keyframe;

        SingleKeyframeWrapper(List<? extends Keyframe<T>> keyframes) {
            this.keyframe = (Keyframe) keyframes.get(0);
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean isValueChanged(float progress) {
            return !this.keyframe.isStatic();
        }

        public Keyframe<T> getCurrentKeyframe() {
            return this.keyframe;
        }

        public float getStartDelayProgress() {
            return this.keyframe.getStartProgress();
        }

        public float getEndProgress() {
            return this.keyframe.getEndProgress();
        }

        public boolean isCachedValueEnabled(float progress) {
            if (this.cachedInterpolatedProgress == progress) {
                return true;
            }
            this.cachedInterpolatedProgress = progress;
            return false;
        }
    }

    private static final class KeyframesWrapperImpl<T> implements KeyframesWrapper<T> {
        private Keyframe<T> cachedCurrentKeyframe = null;
        private float cachedInterpolatedProgress = -1.0f;
        private Keyframe<T> currentKeyframe;
        private final List<? extends Keyframe<T>> keyframes;

        KeyframesWrapperImpl(List<? extends Keyframe<T>> keyframes2) {
            this.keyframes = keyframes2;
            this.currentKeyframe = findKeyframe(0.0f);
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean isValueChanged(float progress) {
            if (this.currentKeyframe.containsProgress(progress)) {
                return !this.currentKeyframe.isStatic();
            }
            this.currentKeyframe = findKeyframe(progress);
            return true;
        }

        private Keyframe<T> findKeyframe(float progress) {
            List<? extends Keyframe<T>> list = this.keyframes;
            Keyframe<T> keyframe = (Keyframe) list.get(list.size() - 1);
            if (progress >= keyframe.getStartProgress()) {
                return keyframe;
            }
            for (int i = this.keyframes.size() - 2; i >= 1; i--) {
                Keyframe<T> keyframe2 = (Keyframe) this.keyframes.get(i);
                if (this.currentKeyframe != keyframe2 && keyframe2.containsProgress(progress)) {
                    return keyframe2;
                }
            }
            return (Keyframe) this.keyframes.get(0);
        }

        public Keyframe<T> getCurrentKeyframe() {
            return this.currentKeyframe;
        }

        public float getStartDelayProgress() {
            return ((Keyframe) this.keyframes.get(0)).getStartProgress();
        }

        public float getEndProgress() {
            List<? extends Keyframe<T>> list = this.keyframes;
            return ((Keyframe) list.get(list.size() - 1)).getEndProgress();
        }

        public boolean isCachedValueEnabled(float progress) {
            Keyframe<T> keyframe = this.cachedCurrentKeyframe;
            Keyframe<T> keyframe2 = this.currentKeyframe;
            if (keyframe == keyframe2 && this.cachedInterpolatedProgress == progress) {
                return true;
            }
            this.cachedCurrentKeyframe = keyframe2;
            this.cachedInterpolatedProgress = progress;
            return false;
        }
    }
}
