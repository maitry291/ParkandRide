package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;

public class ValueCallbackKeyframeAnimation<K, A> extends BaseKeyframeAnimation<K, A> {
    private final A valueCallbackValue;

    public ValueCallbackKeyframeAnimation(LottieValueCallback<A> valueCallback) {
        this(valueCallback, (Object) null);
    }

    public ValueCallbackKeyframeAnimation(LottieValueCallback<A> valueCallback, A valueCallbackValue2) {
        super(Collections.emptyList());
        setValueCallback(valueCallback);
        this.valueCallbackValue = valueCallbackValue2;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    /* access modifiers changed from: package-private */
    public float getEndProgress() {
        return 1.0f;
    }

    public void notifyListeners() {
        if (this.valueCallback != null) {
            super.notifyListeners();
        }
    }

    public A getValue() {
        LottieValueCallback lottieValueCallback = this.valueCallback;
        A a = this.valueCallbackValue;
        return lottieValueCallback.getValueInternal(0.0f, 0.0f, a, a, getProgress(), getProgress(), getProgress());
    }

    /* access modifiers changed from: package-private */
    public A getValue(Keyframe<K> keyframe, float keyframeProgress) {
        return getValue();
    }
}
