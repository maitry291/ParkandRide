package kotlinx.coroutines.tasks;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001a&\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a(\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002\u001a\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001\u001a!\u0010\b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010\t\u001a)\u0010\b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001a+\u0010\u000b\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"asDeferred", "Lkotlinx/coroutines/Deferred;", "T", "Lcom/google/android/gms/tasks/Task;", "cancellationTokenSource", "Lcom/google/android/gms/tasks/CancellationTokenSource;", "asDeferredImpl", "asTask", "await", "(Lcom/google/android/gms/tasks/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lcom/google/android/gms/tasks/Task;Lcom/google/android/gms/tasks/CancellationTokenSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitImpl", "kotlinx-coroutines-play-services"}, k = 2, mv = {1, 6, 0}, xi = 48)
/* compiled from: Tasks.kt */
public final class TasksKt {
    public static final <T> Task<T> asTask(Deferred<? extends T> $this$asTask) {
        CancellationTokenSource cancellation = new CancellationTokenSource();
        TaskCompletionSource source = new TaskCompletionSource(cancellation.getToken());
        $this$asTask.invokeOnCompletion(new TasksKt$asTask$1(cancellation, $this$asTask, source));
        return source.getTask();
    }

    public static final <T> Deferred<T> asDeferred(Task<T> $this$asDeferred) {
        return asDeferredImpl($this$asDeferred, (CancellationTokenSource) null);
    }

    public static final <T> Deferred<T> asDeferred(Task<T> $this$asDeferred, CancellationTokenSource cancellationTokenSource) {
        return asDeferredImpl($this$asDeferred, cancellationTokenSource);
    }

    private static final <T> Deferred<T> asDeferredImpl(Task<T> $this$asDeferredImpl, CancellationTokenSource cancellationTokenSource) {
        CompletableDeferred deferred = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        if ($this$asDeferredImpl.isComplete()) {
            Exception e = $this$asDeferredImpl.getException();
            if (e != null) {
                deferred.completeExceptionally(e);
            } else if ($this$asDeferredImpl.isCanceled()) {
                Job.DefaultImpls.cancel$default((Job) deferred, (CancellationException) null, 1, (Object) null);
            } else {
                deferred.complete($this$asDeferredImpl.getResult());
            }
        } else {
            $this$asDeferredImpl.addOnCompleteListener((Executor) DirectExecutor.INSTANCE, (OnCompleteListener<T>) new TasksKt$$ExternalSyntheticLambda0(deferred));
        }
        if (cancellationTokenSource != null) {
            deferred.invokeOnCompletion(new TasksKt$asDeferredImpl$2(cancellationTokenSource));
        }
        return new TasksKt$asDeferredImpl$3(deferred);
    }

    /* access modifiers changed from: private */
    /* renamed from: asDeferredImpl$lambda-0  reason: not valid java name */
    public static final void m1258asDeferredImpl$lambda0(CompletableDeferred $deferred, Task it) {
        Exception e = it.getException();
        if (e != null) {
            $deferred.completeExceptionally(e);
        } else if (it.isCanceled()) {
            Job.DefaultImpls.cancel$default((Job) $deferred, (CancellationException) null, 1, (Object) null);
        } else {
            $deferred.complete(it.getResult());
        }
    }

    public static final <T> Object await(Task<T> $this$await, Continuation<? super T> $completion) {
        return awaitImpl($this$await, (CancellationTokenSource) null, $completion);
    }

    public static final <T> Object await(Task<T> $this$await, CancellationTokenSource cancellationTokenSource, Continuation<? super T> $completion) {
        return awaitImpl($this$await, cancellationTokenSource, $completion);
    }

    /* access modifiers changed from: private */
    public static final <T> Object awaitImpl(Task<T> $this$awaitImpl, CancellationTokenSource cancellationTokenSource, Continuation<? super T> $completion) {
        if ($this$awaitImpl.isComplete()) {
            Exception e = $this$awaitImpl.getException();
            if (e != null) {
                throw e;
            } else if (!$this$awaitImpl.isCanceled()) {
                return $this$awaitImpl.getResult();
            } else {
                throw new CancellationException("Task " + $this$awaitImpl + " was cancelled normally.");
            }
        } else {
            CancellableContinuationImpl cancellable$iv = new CancellableContinuationImpl(IntrinsicsKt.intercepted($completion), 1);
            cancellable$iv.initCancellability();
            CancellableContinuation cont = cancellable$iv;
            $this$awaitImpl.addOnCompleteListener((Executor) DirectExecutor.INSTANCE, (OnCompleteListener<T>) new TasksKt$awaitImpl$2$1(cont));
            if (cancellationTokenSource != null) {
                cont.invokeOnCancellation(new TasksKt$awaitImpl$2$2(cancellationTokenSource));
            }
            Object result = cancellable$iv.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended($completion);
            }
            return result;
        }
    }
}
