package com.airbnb.lottie.utils;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Build;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public abstract class BaseLottieAnimator extends ValueAnimator {
    private final Set<Animator.AnimatorListener> listeners = new CopyOnWriteArraySet();
    private final Set<ValueAnimator.AnimatorUpdateListener> updateListeners = new CopyOnWriteArraySet();

    public long getStartDelay() {
        throw new UnsupportedOperationException("LottieAnimator does not support getStartDelay.");
    }

    public void setStartDelay(long startDelay) {
        throw new UnsupportedOperationException("LottieAnimator does not support setStartDelay.");
    }

    public ValueAnimator setDuration(long duration) {
        throw new UnsupportedOperationException("LottieAnimator does not support setDuration.");
    }

    public void setInterpolator(TimeInterpolator value) {
        throw new UnsupportedOperationException("LottieAnimator does not support setInterpolator.");
    }

    public void addUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
        this.updateListeners.add(listener);
    }

    public void removeUpdateListener(ValueAnimator.AnimatorUpdateListener listener) {
        this.updateListeners.remove(listener);
    }

    public void removeAllUpdateListeners() {
        this.updateListeners.clear();
    }

    public void addListener(Animator.AnimatorListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Animator.AnimatorListener listener) {
        this.listeners.remove(listener);
    }

    public void removeAllListeners() {
        this.listeners.clear();
    }

    /* access modifiers changed from: package-private */
    public void notifyStart(boolean isReverse) {
        for (Animator.AnimatorListener listener : this.listeners) {
            if (Build.VERSION.SDK_INT >= 26) {
                listener.onAnimationStart(this, isReverse);
            } else {
                listener.onAnimationStart(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyRepeat() {
        for (Animator.AnimatorListener listener : this.listeners) {
            listener.onAnimationRepeat(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyEnd(boolean isReverse) {
        for (Animator.AnimatorListener listener : this.listeners) {
            if (Build.VERSION.SDK_INT >= 26) {
                listener.onAnimationEnd(this, isReverse);
            } else {
                listener.onAnimationEnd(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyCancel() {
        for (Animator.AnimatorListener listener : this.listeners) {
            listener.onAnimationCancel(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyUpdate() {
        for (ValueAnimator.AnimatorUpdateListener listener : this.updateListeners) {
            listener.onAnimationUpdate(this);
        }
    }
}
