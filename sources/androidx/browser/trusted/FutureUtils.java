package androidx.browser.trusted;

import androidx.concurrent.futures.ResolvableFuture;
import com.google.common.util.concurrent.ListenableFuture;

class FutureUtils {
    static <T> ListenableFuture<T> immediateFailedFuture(Throwable cause) {
        ResolvableFuture<T> future = ResolvableFuture.create();
        future.setException(cause);
        return future;
    }

    private FutureUtils() {
    }
}
