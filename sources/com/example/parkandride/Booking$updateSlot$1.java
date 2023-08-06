package com.example.parkandride;

import com.example.parkandride.models.BookingDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/example/parkandride/Booking$updateSlot$1", "Lcom/google/firebase/database/ValueEventListener;", "onCancelled", "", "error", "Lcom/google/firebase/database/DatabaseError;", "onDataChange", "snapshot", "Lcom/google/firebase/database/DataSnapshot;", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Booking.kt */
public final class Booking$updateSlot$1 implements ValueEventListener {
    final /* synthetic */ String $user;
    final /* synthetic */ Booking this$0;

    Booking$updateSlot$1(Booking $receiver, String $user2) {
        this.this$0 = $receiver;
        this.$user = $user2;
    }

    public void onDataChange(DataSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        for (DataSnapshot snap : snapshot.getChildren()) {
            BookingDetails model = (BookingDetails) snap.getValue(BookingDetails.class);
            if (model != null) {
                model.setBookingId(String.valueOf(snap.getKey()));
                this.this$0.ref.getReference("Bookings").child(this.$user).child(model.getBookingId()).setValue(model);
            }
        }
    }

    public void onCancelled(DatabaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        throw new NotImplementedError("An operation is not implemented: " + "Not yet implemented");
    }
}
