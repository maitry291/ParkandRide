package com.example.parkandride.adapters;

import android.view.View;
import com.example.parkandride.adapters.BookingAdapter;
import com.example.parkandride.models.BookingDetails;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BookingAdapter$$ExternalSyntheticLambda3 implements View.OnLongClickListener {
    public final /* synthetic */ BookingAdapter f$0;
    public final /* synthetic */ BookingDetails f$1;
    public final /* synthetic */ BookingAdapter.ViewHolder f$2;

    public /* synthetic */ BookingAdapter$$ExternalSyntheticLambda3(BookingAdapter bookingAdapter, BookingDetails bookingDetails, BookingAdapter.ViewHolder viewHolder) {
        this.f$0 = bookingAdapter;
        this.f$1 = bookingDetails;
        this.f$2 = viewHolder;
    }

    public final boolean onLongClick(View view) {
        return BookingAdapter.m1onBindViewHolder$lambda3(this.f$0, this.f$1, this.f$2, view);
    }
}
