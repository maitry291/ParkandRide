package com.airbnb.lottie;

import android.os.Handler;
import android.os.Looper;
import com.airbnb.lottie.utils.Logger;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class LottieTask<T> {
    public static Executor EXECUTOR = Executors.newCachedThreadPool();
    private final Set<LottieListener<Throwable>> failureListeners;
    private final Handler handler;
    private volatile LottieResult<T> result;
    private final Set<LottieListener<T>> successListeners;

    public LottieTask(Callable<LottieResult<T>> runnable) {
        this(runnable, false);
    }

    LottieTask(Callable<LottieResult<T>> runnable, boolean runNow) {
        this.successListeners = new LinkedHashSet(1);
        this.failureListeners = new LinkedHashSet(1);
        this.handler = new Handler(Looper.getMainLooper());
        this.result = null;
        if (runNow) {
            try {
                setResult(runnable.call());
            } catch (Throwable e) {
                setResult(new LottieResult(e));
            }
        } else {
            EXECUTOR.execute(new LottieFutureTask(runnable));
        }
    }

    /* access modifiers changed from: private */
    public void setResult(LottieResult<T> result2) {
        if (this.result == null) {
            this.result = result2;
            notifyListeners();
            return;
        }
        throw new IllegalStateException("A task may only be set once.");
    }

    public synchronized LottieTask<T> addListener(LottieListener<T> listener) {
        LottieResult<T> result2 = this.result;
        if (!(result2 == null || result2.getValue() == null)) {
            listener.onResult(result2.getValue());
        }
        this.successListeners.add(listener);
        return this;
    }

    public synchronized LottieTask<T> removeListener(LottieListener<T> listener) {
        this.successListeners.remove(listener);
        return this;
    }

    public synchronized LottieTask<T> addFailureListener(LottieListener<Throwable> listener) {
        LottieResult<T> result2 = this.result;
        if (!(result2 == null || result2.getException() == null)) {
            listener.onResult(result2.getException());
        }
        this.failureListeners.add(listener);
        return this;
    }

    public synchronized LottieTask<T> removeFailureListener(LottieListener<Throwable> listener) {
        this.failureListeners.remove(listener);
        return this;
    }

    private void notifyListeners() {
        this.handler.post(new LottieTask$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$notifyListeners$0$com-airbnb-lottie-LottieTask  reason: not valid java name */
    public /* synthetic */ void m1324lambda$notifyListeners$0$comairbnblottieLottieTask() {
        LottieResult<T> result2 = this.result;
        if (result2 != null) {
            if (result2.getValue() != null) {
                notifySuccessListeners(result2.getValue());
            } else {
                notifyFailureListeners(result2.getException());
            }
        }
    }

    private synchronized void notifySuccessListeners(T value) {
        for (LottieListener<T> l : new ArrayList<>(this.successListeners)) {
            l.onResult(value);
        }
    }

    private synchronized void notifyFailureListeners(Throwable e) {
        List<LottieListener<Throwable>> listenersCopy = new ArrayList<>(this.failureListeners);
        if (listenersCopy.isEmpty()) {
            Logger.warning("Lottie encountered an error but no failure listener was added:", e);
            return;
        }
        for (LottieListener<Throwable> l : listenersCopy) {
            l.onResult(e);
        }
    }

    private class LottieFutureTask extends FutureTask<LottieResult<T>> {
        LottieFutureTask(Callable<LottieResult<T>> callable) {
            super(callable);
        }

        /* access modifiers changed from: protected */
        public void done() {
            if (!isCancelled()) {
                try {
                    LottieTask.this.setResult((LottieResult) get());
                } catch (InterruptedException | ExecutionException e) {
                    LottieTask.this.setResult(new LottieResult((Throwable) e));
                }
            }
        }
    }
}
