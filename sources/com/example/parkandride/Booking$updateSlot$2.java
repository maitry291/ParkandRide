package com.example.parkandride;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u000e"}, d2 = {"com/example/parkandride/Booking$updateSlot$2", "Lcom/google/firebase/database/ChildEventListener;", "onCancelled", "", "error", "Lcom/google/firebase/database/DatabaseError;", "onChildAdded", "snapshot", "Lcom/google/firebase/database/DataSnapshot;", "previousChildName", "", "onChildChanged", "onChildMoved", "onChildRemoved", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Booking.kt */
public final class Booking$updateSlot$2 implements ChildEventListener {
    final /* synthetic */ Booking this$0;

    Booking$updateSlot$2(Booking $receiver) {
        this.this$0 = $receiver;
    }

    public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        DatabaseReference child = this.this$0.ref.getReference("Slots").child("count");
        Integer num = (Integer) snapshot.getValue(Integer.TYPE);
        child.setValue(num != null ? Integer.valueOf(num.intValue() - 1) : null);
    }

    public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
    }

    public void onChildRemoved(DataSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        DatabaseReference child = this.this$0.ref.getReference("Slots").child("count");
        Integer num = (Integer) snapshot.getValue(Integer.TYPE);
        child.setValue(num != null ? Integer.valueOf(num.intValue() + 1) : null);
    }

    public void onChildMoved(DataSnapshot snapshot, String previousChildName) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        throw new NotImplementedError("An operation is not implemented: " + "Not yet implemented");
    }

    public void onCancelled(DatabaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        throw new NotImplementedError("An operation is not implemented: " + "Not yet implemented");
    }
}
