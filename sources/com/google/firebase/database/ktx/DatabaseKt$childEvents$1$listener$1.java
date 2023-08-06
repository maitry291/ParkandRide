package com.google.firebase.database.ktx;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ktx.ChildEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u000e"}, d2 = {"com/google/firebase/database/ktx/DatabaseKt$childEvents$1$listener$1", "Lcom/google/firebase/database/ChildEventListener;", "onCancelled", "", "error", "Lcom/google/firebase/database/DatabaseError;", "onChildAdded", "snapshot", "Lcom/google/firebase/database/DataSnapshot;", "previousChildName", "", "onChildChanged", "onChildMoved", "onChildRemoved", "com.google.firebase-firebase-database-ktx"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Database.kt */
public final class DatabaseKt$childEvents$1$listener$1 implements ChildEventListener {
    final /* synthetic */ ProducerScope<ChildEvent> $$this$callbackFlow;
    final /* synthetic */ Query $this_childEvents;

    DatabaseKt$childEvents$1$listener$1(Query $receiver, ProducerScope<? super ChildEvent> $$this$callbackFlow2) {
        this.$this_childEvents = $receiver;
        this.$$this$callbackFlow = $$this$callbackFlow2;
    }

    public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this.$this_childEvents.getRepo().scheduleNow(new DatabaseKt$childEvents$1$listener$1$$ExternalSyntheticLambda0(this.$$this$callbackFlow, snapshot, previousChildName));
    }

    /* access modifiers changed from: private */
    /* renamed from: onChildAdded$lambda-0  reason: not valid java name */
    public static final void m1374onChildAdded$lambda0(ProducerScope $$this$callbackFlow2, DataSnapshot $snapshot, String $previousChildName) {
        Intrinsics.checkNotNullParameter($$this$callbackFlow2, "$$this$callbackFlow");
        Intrinsics.checkNotNullParameter($snapshot, "$snapshot");
        ChannelsKt.trySendBlocking($$this$callbackFlow2, new ChildEvent.Added($snapshot, $previousChildName));
    }

    public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this.$this_childEvents.getRepo().scheduleNow(new DatabaseKt$childEvents$1$listener$1$$ExternalSyntheticLambda1(this.$$this$callbackFlow, snapshot, previousChildName));
    }

    /* access modifiers changed from: private */
    /* renamed from: onChildChanged$lambda-1  reason: not valid java name */
    public static final void m1375onChildChanged$lambda1(ProducerScope $$this$callbackFlow2, DataSnapshot $snapshot, String $previousChildName) {
        Intrinsics.checkNotNullParameter($$this$callbackFlow2, "$$this$callbackFlow");
        Intrinsics.checkNotNullParameter($snapshot, "$snapshot");
        ChannelsKt.trySendBlocking($$this$callbackFlow2, new ChildEvent.Changed($snapshot, $previousChildName));
    }

    public void onChildRemoved(DataSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this.$this_childEvents.getRepo().scheduleNow(new DatabaseKt$childEvents$1$listener$1$$ExternalSyntheticLambda2(this.$$this$callbackFlow, snapshot));
    }

    /* access modifiers changed from: private */
    /* renamed from: onChildRemoved$lambda-2  reason: not valid java name */
    public static final void m1377onChildRemoved$lambda2(ProducerScope $$this$callbackFlow2, DataSnapshot $snapshot) {
        Intrinsics.checkNotNullParameter($$this$callbackFlow2, "$$this$callbackFlow");
        Intrinsics.checkNotNullParameter($snapshot, "$snapshot");
        ChannelsKt.trySendBlocking($$this$callbackFlow2, new ChildEvent.Removed($snapshot));
    }

    public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this.$this_childEvents.getRepo().scheduleNow(new DatabaseKt$childEvents$1$listener$1$$ExternalSyntheticLambda3(this.$$this$callbackFlow, snapshot, previousChildName));
    }

    /* access modifiers changed from: private */
    /* renamed from: onChildMoved$lambda-3  reason: not valid java name */
    public static final void m1376onChildMoved$lambda3(ProducerScope $$this$callbackFlow2, DataSnapshot $snapshot, String $previousChildName) {
        Intrinsics.checkNotNullParameter($$this$callbackFlow2, "$$this$callbackFlow");
        Intrinsics.checkNotNullParameter($snapshot, "$snapshot");
        ChannelsKt.trySendBlocking($$this$callbackFlow2, new ChildEvent.Moved($snapshot, $previousChildName));
    }

    public void onCancelled(DatabaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        CoroutineScopeKt.cancel(this.$$this$callbackFlow, "Error getting Query childEvent", error.toException());
    }
}
