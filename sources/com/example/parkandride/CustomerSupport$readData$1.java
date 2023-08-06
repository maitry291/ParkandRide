package com.example.parkandride;

import com.example.parkandride.adapters.MessageAdapter;
import com.example.parkandride.models.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0017¨\u0006\t"}, d2 = {"com/example/parkandride/CustomerSupport$readData$1", "Lcom/google/firebase/database/ValueEventListener;", "onCancelled", "", "error", "Lcom/google/firebase/database/DatabaseError;", "onDataChange", "snapshot", "Lcom/google/firebase/database/DataSnapshot;", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CustomerSupport.kt */
public final class CustomerSupport$readData$1 implements ValueEventListener {
    final /* synthetic */ CustomerSupport this$0;

    CustomerSupport$readData$1(CustomerSupport $receiver) {
        this.this$0 = $receiver;
    }

    public void onDataChange(DataSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        ArrayList access$getMessageModalArrayList$p = this.this$0.messageModalArrayList;
        Intrinsics.checkNotNull(access$getMessageModalArrayList$p);
        access$getMessageModalArrayList$p.clear();
        for (DataSnapshot snap : snapshot.getChildren()) {
            Message model = (Message) snap.getValue(Message.class);
            if (model != null) {
                model.setMessageId(String.valueOf(snap.getKey()));
                this.this$0.database.getReference("CustomerSupport").child(this.this$0.senderId + this.this$0.receiverId).child(model.getMessageId()).setValue(model);
                ArrayList access$getMessageModalArrayList$p2 = this.this$0.messageModalArrayList;
                Intrinsics.checkNotNull(access$getMessageModalArrayList$p2);
                access$getMessageModalArrayList$p2.add(model);
            }
        }
        MessageAdapter access$getMessageRVAdapter$p = this.this$0.messageRVAdapter;
        Intrinsics.checkNotNull(access$getMessageRVAdapter$p);
        access$getMessageRVAdapter$p.notifyDataSetChanged();
    }

    public void onCancelled(DatabaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        throw new NotImplementedError("An operation is not implemented: " + "Not yet implemented");
    }
}
