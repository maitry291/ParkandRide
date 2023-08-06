package com.airbnb.lottie.utils;

import android.view.Choreographer;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieComposition;

public class LottieValueAnimator extends BaseLottieAnimator implements Choreographer.FrameCallback {
    private LottieComposition composition;
    private float frame = 0.0f;
    private long lastFrameTimeNs = 0;
    private float maxFrame = 2.14748365E9f;
    private float minFrame = -2.14748365E9f;
    private int repeatCount = 0;
    protected boolean running = false;
    private float speed = 1.0f;
    private boolean speedReversedForRepeatMode = false;

    public Object getAnimatedValue() {
        return Float.valueOf(getAnimatedValueAbsolute());
    }

    public float getAnimatedValueAbsolute() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        return (this.frame - lottieComposition.getStartFrame()) / (this.composition.getEndFrame() - this.composition.getStartFrame());
    }

    public float getAnimatedFraction() {
        if (this.composition == null) {
            return 0.0f;
        }
        if (isReversed()) {
            return (getMaxFrame() - this.frame) / (getMaxFrame() - getMinFrame());
        }
        return (this.frame - getMinFrame()) / (getMaxFrame() - getMinFrame());
    }

    public long getDuration() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0;
        }
        return (long) lottieComposition.getDuration();
    }

    public float getFrame() {
        return this.frame;
    }

    public boolean isRunning() {
        return this.running;
    }

    public void doFrame(long frameTimeNanos) {
        postFrameCallback();
        if (this.composition != null && isRunning()) {
            L.beginSection("LottieValueAnimator#doFrame");
            long j = this.lastFrameTimeNs;
            long timeSinceFrame = 0;
            if (j != 0) {
                timeSinceFrame = frameTimeNanos - j;
            }
            float dFrames = ((float) timeSinceFrame) / getFrameDurationNs();
            float f = this.frame + (isReversed() ? -dFrames : dFrames);
            this.frame = f;
            boolean ended = !MiscUtils.contains(f, getMinFrame(), getMaxFrame());
            this.frame = MiscUtils.clamp(this.frame, getMinFrame(), getMaxFrame());
            this.lastFrameTimeNs = frameTimeNanos;
            notifyUpdate();
            if (ended) {
                if (getRepeatCount() == -1 || this.repeatCount < getRepeatCount()) {
                    notifyRepeat();
                    this.repeatCount++;
                    if (getRepeatMode() == 2) {
                        this.speedReversedForRepeatMode = !this.speedReversedForRepeatMode;
                        reverseAnimationSpeed();
                    } else {
                        this.frame = isReversed() ? getMaxFrame() : getMinFrame();
                    }
                    this.lastFrameTimeNs = frameTimeNanos;
                } else {
                    this.frame = this.speed < 0.0f ? getMinFrame() : getMaxFrame();
                    removeFrameCallback();
                    notifyEnd(isReversed());
                }
            }
            verifyFrame();
            L.endSection("LottieValueAnimator#doFrame");
        }
    }

    private float getFrameDurationNs() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return Float.MAX_VALUE;
        }
        return (1.0E9f / lottieComposition.getFrameRate()) / Math.abs(this.speed);
    }

    public void clearComposition() {
        this.composition = null;
        this.minFrame = -2.14748365E9f;
        this.maxFrame = 2.14748365E9f;
    }

    public void setComposition(LottieComposition composition2) {
        boolean keepMinAndMaxFrames = this.composition == null;
        this.composition = composition2;
        if (keepMinAndMaxFrames) {
            setMinAndMaxFrames(Math.max(this.minFrame, composition2.getStartFrame()), Math.min(this.maxFrame, composition2.getEndFrame()));
        } else {
            setMinAndMaxFrames((float) ((int) composition2.getStartFrame()), (float) ((int) composition2.getEndFrame()));
        }
        float frame2 = this.frame;
        this.frame = 0.0f;
        setFrame((float) ((int) frame2));
        notifyUpdate();
    }

    public void setFrame(float frame2) {
        if (this.frame != frame2) {
            this.frame = MiscUtils.clamp(frame2, getMinFrame(), getMaxFrame());
            this.lastFrameTimeNs = 0;
            notifyUpdate();
        }
    }

    public void setMinFrame(int minFrame2) {
        setMinAndMaxFrames((float) minFrame2, (float) ((int) this.maxFrame));
    }

    public void setMaxFrame(float maxFrame2) {
        setMinAndMaxFrames(this.minFrame, maxFrame2);
    }

    public void setMinAndMaxFrames(float minFrame2, float maxFrame2) {
        if (minFrame2 <= maxFrame2) {
            LottieComposition lottieComposition = this.composition;
            float compositionMinFrame = lottieComposition == null ? -3.4028235E38f : lottieComposition.getStartFrame();
            LottieComposition lottieComposition2 = this.composition;
            float compositionMaxFrame = lottieComposition2 == null ? Float.MAX_VALUE : lottieComposition2.getEndFrame();
            float newMinFrame = MiscUtils.clamp(minFrame2, compositionMinFrame, compositionMaxFrame);
            float newMaxFrame = MiscUtils.clamp(maxFrame2, compositionMinFrame, compositionMaxFrame);
            if (newMinFrame != this.minFrame || newMaxFrame != this.maxFrame) {
                this.minFrame = newMinFrame;
                this.maxFrame = newMaxFrame;
                setFrame((float) ((int) MiscUtils.clamp(this.frame, newMinFrame, newMaxFrame)));
                return;
            }
            return;
        }
        throw new IllegalArgumentException(String.format("minFrame (%s) must be <= maxFrame (%s)", new Object[]{Float.valueOf(minFrame2), Float.valueOf(maxFrame2)}));
    }

    public void reverseAnimationSpeed() {
        setSpeed(-getSpeed());
    }

    public void setSpeed(float speed2) {
        this.speed = speed2;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setRepeatMode(int value) {
        super.setRepeatMode(value);
        if (value != 2 && this.speedReversedForRepeatMode) {
            this.speedReversedForRepeatMode = false;
            reverseAnimationSpeed();
        }
    }

    public void playAnimation() {
        this.running = true;
        notifyStart(isReversed());
        setFrame((float) ((int) (isReversed() ? getMaxFrame() : getMinFrame())));
        this.lastFrameTimeNs = 0;
        this.repeatCount = 0;
        postFrameCallback();
    }

    public void endAnimation() {
        removeFrameCallback();
        notifyEnd(isReversed());
    }

    public void pauseAnimation() {
        removeFrameCallback();
    }

    public void resumeAnimation() {
        this.running = true;
        postFrameCallback();
        this.lastFrameTimeNs = 0;
        if (isReversed() && getFrame() == getMinFrame()) {
            this.frame = getMaxFrame();
        } else if (!isReversed() && getFrame() == getMaxFrame()) {
            this.frame = getMinFrame();
        }
    }

    public void cancel() {
        notifyCancel();
        removeFrameCallback();
    }

    private boolean isReversed() {
        return getSpeed() < 0.0f;
    }

    public float getMinFrame() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f = this.minFrame;
        return f == -2.14748365E9f ? lottieComposition.getStartFrame() : f;
    }

    public float getMaxFrame() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        float f = this.maxFrame;
        return f == 2.14748365E9f ? lottieComposition.getEndFrame() : f;
    }

    /* access modifiers changed from: package-private */
    public void notifyCancel() {
        super.notifyCancel();
        notifyEnd(isReversed());
    }

    /* access modifiers changed from: protected */
    public void postFrameCallback() {
        if (isRunning()) {
            removeFrameCallback(false);
            Choreographer.getInstance().postFrameCallback(this);
        }
    }

    /* access modifiers changed from: protected */
    public void removeFrameCallback() {
        removeFrameCallback(true);
    }

    /* access modifiers changed from: protected */
    public void removeFrameCallback(boolean stopRunning) {
        Choreographer.getInstance().removeFrameCallback(this);
        if (stopRunning) {
            this.running = false;
        }
    }

    private void verifyFrame() {
        if (this.composition != null) {
            float f = this.frame;
            if (f < this.minFrame || f > this.maxFrame) {
                throw new IllegalStateException(String.format("Frame must be [%f,%f]. It is %f", new Object[]{Float.valueOf(this.minFrame), Float.valueOf(this.maxFrame), Float.valueOf(this.frame)}));
            }
        }
    }
}
