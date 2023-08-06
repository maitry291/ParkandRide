package com.example.parkandride.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parkandride.ConfirmBooking;
import com.example.parkandride.Profie;
import com.example.parkandride.R;
import com.example.parkandride.models.BookingDetails;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.AuthKt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ktx.DatabaseKt;
import com.google.firebase.ktx.Firebase;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0016B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\rH\u0016J\u0018\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/example/parkandride/adapters/BookingAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/parkandride/adapters/BookingAdapter$ViewHolder;", "context", "Landroid/content/Context;", "list", "Ljava/util/ArrayList;", "Lcom/example/parkandride/models/BookingDetails;", "Lkotlin/collections/ArrayList;", "(Landroid/content/Context;Ljava/util/ArrayList;)V", "getContext", "()Landroid/content/Context;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: BookingAdapter.kt */
public final class BookingAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final Context context;
    private final ArrayList<BookingDetails> list;

    public final Context getContext() {
        return this.context;
    }

    public BookingAdapter(Context context2, ArrayList<BookingDetails> list2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(list2, "list");
        this.context = context2;
        this.list = list2;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/example/parkandride/adapters/BookingAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: BookingAdapter.kt */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(this.context).inflate(R.layout.sample_booking, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(view);
    }

    public int getItemCount() {
        return this.list.size();
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        BookingDetails bookingDetails = this.list.get(position);
        Intrinsics.checkNotNullExpressionValue(bookingDetails, "list[position]");
        BookingDetails model = bookingDetails;
        ((TextView) holder.itemView.findViewById(R.id.t1)).setText(model.getName());
        ((TextView) holder.itemView.findViewById(R.id.t2)).setText(model.getDate());
        ((TextView) holder.itemView.findViewById(R.id.t3)).setText(model.getIntime());
        ((TextView) holder.itemView.findViewById(R.id.t4)).setText(model.getOuttime());
        holder.itemView.setOnClickListener(new BookingAdapter$$ExternalSyntheticLambda2(this, model));
        holder.itemView.setOnLongClickListener(new BookingAdapter$$ExternalSyntheticLambda3(this, model, holder));
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-0  reason: not valid java name */
    public static final void m0onBindViewHolder$lambda0(BookingAdapter this$0, BookingDetails $model, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter($model, "$model");
        Intent i = new Intent(this$0.context, ConfirmBooking.class);
        i.putExtra("name", $model.getName());
        i.putExtra("date", $model.getDate());
        i.putExtra("intime", $model.getIntime());
        i.putExtra("outtime", $model.getOuttime());
        i.putExtra("phone", $model.getPhoneNum());
        i.putExtra("vehicle", $model.getVehicleNum());
        i.putExtra(TypedValues.TransitionType.S_DURATION, $model.getDuration());
        i.putExtra("time", $model.getTimeOfPay());
        i.putExtra("trans", $model.getTransId());
        i.putExtra("price", $model.getPrice());
        this$0.context.startActivity(i);
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-3  reason: not valid java name */
    public static final boolean m1onBindViewHolder$lambda3(BookingAdapter this$0, BookingDetails $model, ViewHolder $holder, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter($model, "$model");
        Intrinsics.checkNotNullParameter($holder, "$holder");
        new AlertDialog.Builder(this$0.context).setTitle((CharSequence) "Delete Booking ?").setPositiveButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new BookingAdapter$$ExternalSyntheticLambda0($model, $holder, this$0)).setNegativeButton((CharSequence) "No", (DialogInterface.OnClickListener) new BookingAdapter$$ExternalSyntheticLambda1()).show();
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-3$lambda-1  reason: not valid java name */
    public static final void m2onBindViewHolder$lambda3$lambda1(BookingDetails $model, ViewHolder $holder, BookingAdapter this$0, DialogInterface p0, int p1) {
        Intrinsics.checkNotNullParameter($model, "$model");
        Intrinsics.checkNotNullParameter($holder, "$holder");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DatabaseReference child = DatabaseKt.getDatabase(Firebase.INSTANCE).getReference().child("Bookings");
        FirebaseUser currentUser = AuthKt.getAuth(Firebase.INSTANCE).getCurrentUser();
        child.child(String.valueOf(currentUser != null ? currentUser.getUid() : null)).child($model.getBookingId()).setValue((Object) null);
        $holder.itemView.getContext().startActivity(new Intent(this$0.context, Profie.class));
    }

    /* access modifiers changed from: private */
    /* renamed from: onBindViewHolder$lambda-3$lambda-2  reason: not valid java name */
    public static final void m3onBindViewHolder$lambda3$lambda2(DialogInterface p0, int p1) {
        if (p0 != null) {
            p0.dismiss();
        }
    }
}
