package com.airbnb.lottie;

import java.util.Arrays;

public final class LottieResult<V> {
    private final Throwable exception;
    private final V value;

    public LottieResult(V value2) {
        this.value = value2;
        this.exception = null;
    }

    public LottieResult(Throwable exception2) {
        this.exception = exception2;
        this.value = null;
    }

    public V getValue() {
        return this.value;
    }

    public Throwable getException() {
        return this.exception;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottieResult)) {
            return false;
        }
        LottieResult<?> that = (LottieResult) o;
        if (getValue() != null && getValue().equals(that.getValue())) {
            return true;
        }
        if (getException() == null || that.getException() == null) {
            return false;
        }
        return getException().toString().equals(getException().toString());
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{getValue(), getException()});
    }
}
