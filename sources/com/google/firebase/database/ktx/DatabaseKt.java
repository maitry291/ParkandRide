package com.google.firebase.database.ktx;

import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.ktx.Firebase;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u001a\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0001\u001a\u0012\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0001\u001a\u001c\u0010\u0013\u001a\u0004\u0018\u0001H\u0014\"\u0006\b\u0000\u0010\u0014\u0018\u0001*\u00020\u000eH\b¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0013\u001a\u0004\u0018\u0001H\u0014\"\u0006\b\u0000\u0010\u0014\u0018\u0001*\u00020\u0016H\b¢\u0006\u0002\u0010\u0017\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\"\u0015\u0010\b\u001a\u00020\t*\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u001b\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003*\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0007¨\u0006\u0018"}, d2 = {"LIBRARY_NAME", "", "childEvents", "Lkotlinx/coroutines/flow/Flow;", "Lcom/google/firebase/database/ktx/ChildEvent;", "Lcom/google/firebase/database/Query;", "getChildEvents", "(Lcom/google/firebase/database/Query;)Lkotlinx/coroutines/flow/Flow;", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "Lcom/google/firebase/ktx/Firebase;", "getDatabase", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/database/FirebaseDatabase;", "snapshots", "Lcom/google/firebase/database/DataSnapshot;", "getSnapshots", "app", "Lcom/google/firebase/FirebaseApp;", "url", "getValue", "T", "(Lcom/google/firebase/database/DataSnapshot;)Ljava/lang/Object;", "Lcom/google/firebase/database/MutableData;", "(Lcom/google/firebase/database/MutableData;)Ljava/lang/Object;", "com.google.firebase-firebase-database-ktx"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: Database.kt */
public final class DatabaseKt {
    public static final String LIBRARY_NAME = "fire-db-ktx";

    public static final FirebaseDatabase getDatabase(Firebase $this$database) {
        Intrinsics.checkNotNullParameter($this$database, "<this>");
        FirebaseDatabase instance = FirebaseDatabase.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final FirebaseDatabase database(Firebase $this$database, String url) {
        Intrinsics.checkNotNullParameter($this$database, "<this>");
        Intrinsics.checkNotNullParameter(url, ImagesContract.URL);
        FirebaseDatabase instance = FirebaseDatabase.getInstance(url);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(url)");
        return instance;
    }

    public static final FirebaseDatabase database(Firebase $this$database, FirebaseApp app) {
        Intrinsics.checkNotNullParameter($this$database, "<this>");
        Intrinsics.checkNotNullParameter(app, "app");
        FirebaseDatabase instance = FirebaseDatabase.getInstance(app);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app)");
        return instance;
    }

    public static final FirebaseDatabase database(Firebase $this$database, FirebaseApp app, String url) {
        Intrinsics.checkNotNullParameter($this$database, "<this>");
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(url, ImagesContract.URL);
        FirebaseDatabase instance = FirebaseDatabase.getInstance(app, url);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(app, url)");
        return instance;
    }

    public static final /* synthetic */ <T> T getValue(DataSnapshot $this$getValue) {
        Intrinsics.checkNotNullParameter($this$getValue, "<this>");
        Intrinsics.needClassReification();
        return $this$getValue.getValue(new DatabaseKt$getValue$1());
    }

    public static final /* synthetic */ <T> T getValue(MutableData $this$getValue) {
        Intrinsics.checkNotNullParameter($this$getValue, "<this>");
        Intrinsics.needClassReification();
        return $this$getValue.getValue(new DatabaseKt$getValue$2());
    }

    public static final Flow<DataSnapshot> getSnapshots(Query $this$snapshots) {
        Intrinsics.checkNotNullParameter($this$snapshots, "<this>");
        return FlowKt.callbackFlow(new DatabaseKt$snapshots$1($this$snapshots, (Continuation<? super DatabaseKt$snapshots$1>) null));
    }

    public static final Flow<ChildEvent> getChildEvents(Query $this$childEvents) {
        Intrinsics.checkNotNullParameter($this$childEvents, "<this>");
        return FlowKt.callbackFlow(new DatabaseKt$childEvents$1($this$childEvents, (Continuation<? super DatabaseKt$childEvents$1>) null));
    }
}
