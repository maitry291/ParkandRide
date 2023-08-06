package com.example.parkandride;

import android.widget.TextView;
import com.example.parkandride.adapters.BookingAdapter;
import com.example.parkandride.models.BookingDetails;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\f\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u000e"}, d2 = {"com/example/parkandride/Profie$onCreate$4", "Lcom/google/firebase/database/ChildEventListener;", "onCancelled", "", "error", "Lcom/google/firebase/database/DatabaseError;", "onChildAdded", "snapshot", "Lcom/google/firebase/database/DataSnapshot;", "previousChildName", "", "onChildChanged", "onChildMoved", "onChildRemoved", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Profie.kt */
public final class Profie$onCreate$4 implements ChildEventListener {
    final /* synthetic */ BookingAdapter $adapter;
    final /* synthetic */ ArrayList<BookingDetails> $list;
    final /* synthetic */ Profie this$0;

    Profie$onCreate$4(Profie $receiver, ArrayList<BookingDetails> $list2, BookingAdapter $adapter2) {
        this.this$0 = $receiver;
        this.$list = $list2;
        this.$adapter = $adapter2;
    }

    public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        BookingDetails it = (BookingDetails) snapshot.getValue(BookingDetails.class);
        if (it != null) {
            this.$list.add(it);
        }
        ((TextView) this.this$0._$_findCachedViewById(R.id.total)).setText("Total Bookings : " + this.$list.size());
        this.$adapter.notifyDataSetChanged();
    }

    public void onChildChanged(DataSnapshot snapshot, String previousChildName) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this.$adapter.notifyDataSetChanged();
    }

    public void onChildRemoved(DataSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        ((TextView) this.this$0._$_findCachedViewById(R.id.total)).setText("Total Bookings : " + this.$list.size());
        TypeIntrinsics.asMutableCollection(this.$list).remove(snapshot.getValue(BookingDetails.class));
        this.$adapter.notifyDataSetChanged();
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
