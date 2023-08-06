package com.google.firebase.database.ktx;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lcom/google/firebase/database/DataSnapshot;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.google.firebase.database.ktx.DatabaseKt$snapshots$1", f = "Database.kt", i = {}, l = {89}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Database.kt */
final class DatabaseKt$snapshots$1 extends SuspendLambda implements Function2<ProducerScope<? super DataSnapshot>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Query $this_snapshots;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatabaseKt$snapshots$1(Query query, Continuation<? super DatabaseKt$snapshots$1> continuation) {
        super(2, continuation);
        this.$this_snapshots = query;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DatabaseKt$snapshots$1 databaseKt$snapshots$1 = new DatabaseKt$snapshots$1(this.$this_snapshots, continuation);
        databaseKt$snapshots$1.L$0 = obj;
        return databaseKt$snapshots$1;
    }

    public final Object invoke(ProducerScope<? super DataSnapshot> producerScope, Continuation<? super Unit> continuation) {
        return ((DatabaseKt$snapshots$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                ProducerScope $this$callbackFlow = (ProducerScope) this.L$0;
                Query query = this.$this_snapshots;
                final ValueEventListener listener = query.addValueEventListener(new DatabaseKt$snapshots$1$listener$1(query, $this$callbackFlow));
                Intrinsics.checkNotNullExpressionValue(listener, "Query.snapshots\n    get(…\n            }\n        })");
                final Query query2 = this.$this_snapshots;
                this.label = 1;
                if (ProduceKt.awaitClose($this$callbackFlow, new Function0<Unit>() {
                    public final void invoke() {
                        query2.removeEventListener(listener);
                    }
                }, this) != coroutine_suspended) {
                    break;
                } else {
                    return coroutine_suspended;
                }
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
