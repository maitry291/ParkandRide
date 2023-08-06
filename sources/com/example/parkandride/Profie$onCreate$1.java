package com.example.parkandride;

import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.example.parkandride.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"com/example/parkandride/Profie$onCreate$1", "Lcom/google/firebase/database/ValueEventListener;", "onCancelled", "", "error", "Lcom/google/firebase/database/DatabaseError;", "onDataChange", "snapshot", "Lcom/google/firebase/database/DataSnapshot;", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Profie.kt */
public final class Profie$onCreate$1 implements ValueEventListener {
    final /* synthetic */ Intent $i;
    final /* synthetic */ Profie this$0;

    Profie$onCreate$1(Profie $receiver, Intent $i2) {
        this.this$0 = $receiver;
        this.$i = $i2;
    }

    public void onDataChange(DataSnapshot snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        User model = (User) snapshot.getValue(User.class);
        String str = null;
        ((TextView) this.this$0._$_findCachedViewById(R.id.userName)).append(model != null ? model.getName() : null);
        this.$i.putExtra("name", model != null ? model.getName() : null);
        TextView textView = (TextView) this.this$0._$_findCachedViewById(R.id.userEmail);
        if (model != null) {
            str = model.getEmail();
        }
        textView.setText(str);
        ((RelativeLayout) this.this$0._$_findCachedViewById(R.id.r1)).setVisibility(0);
        ((LottieAnimationView) this.this$0._$_findCachedViewById(R.id.p1)).setVisibility(8);
    }

    public void onCancelled(DatabaseError error) {
        Intrinsics.checkNotNullParameter(error, "error");
        throw new NotImplementedError("An operation is not implemented: " + "Not yet implemented");
    }
}
