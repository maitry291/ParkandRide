package com.example.parkandride.adapters;

import android.content.DialogInterface;
import com.example.parkandride.adapters.BookingAdapter;
import com.example.parkandride.models.BookingDetails;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BookingAdapter$$ExternalSyntheticLambda0 implements DialogInterface.OnClickListener {
    public final /* synthetic */ BookingDetails f$0;
    public final /* synthetic */ BookingAdapter.ViewHolder f$1;
    public final /* synthetic */ BookingAdapter f$2;

    public /* synthetic */ BookingAdapter$$ExternalSyntheticLambda0(BookingDetails bookingDetails, BookingAdapter.ViewHolder viewHolder, BookingAdapter bookingAdapter) {
        this.f$0 = bookingDetails;
        this.f$1 = viewHolder;
        this.f$2 = bookingAdapter;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        BookingAdapter.m2onBindViewHolder$lambda3$lambda1(this.f$0, this.f$1, this.f$2, dialogInterface, i);
    }
}
