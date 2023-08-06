package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.example.parkandride.R;

public final class ActivityConfirmBookingBinding implements ViewBinding {
    public final LottieAnimationView animationView;
    public final LottieAnimationView animationView2;
    public final LinearLayout booking;
    public final TextView cusPhone;
    public final TextView custDuration;
    public final TextView custIn;
    public final TextView custName;
    public final TextView custOut;
    public final TextView custVehicle;
    public final AppCompatButton done;
    public final TextView parkingDate;
    public final TextView price;
    private final RelativeLayout rootView;
    public final TextView timedate;
    public final TextView transId;

    private ActivityConfirmBookingBinding(RelativeLayout rootView2, LottieAnimationView animationView3, LottieAnimationView animationView22, LinearLayout booking2, TextView cusPhone2, TextView custDuration2, TextView custIn2, TextView custName2, TextView custOut2, TextView custVehicle2, AppCompatButton done2, TextView parkingDate2, TextView price2, TextView timedate2, TextView transId2) {
        this.rootView = rootView2;
        this.animationView = animationView3;
        this.animationView2 = animationView22;
        this.booking = booking2;
        this.cusPhone = cusPhone2;
        this.custDuration = custDuration2;
        this.custIn = custIn2;
        this.custName = custName2;
        this.custOut = custOut2;
        this.custVehicle = custVehicle2;
        this.done = done2;
        this.parkingDate = parkingDate2;
        this.price = price2;
        this.timedate = timedate2;
        this.transId = transId2;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityConfirmBookingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityConfirmBookingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_confirm_booking, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityConfirmBookingBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.animation_view;
        LottieAnimationView animationView3 = (LottieAnimationView) ViewBindings.findChildViewById(view, R.id.animation_view);
        if (animationView3 != null) {
            id = R.id.animation_view2;
            LottieAnimationView animationView22 = (LottieAnimationView) ViewBindings.findChildViewById(view, R.id.animation_view2);
            if (animationView22 != null) {
                id = R.id.booking;
                LinearLayout booking2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.booking);
                if (booking2 != null) {
                    id = R.id.cusPhone;
                    TextView cusPhone2 = (TextView) ViewBindings.findChildViewById(view, R.id.cusPhone);
                    if (cusPhone2 != null) {
                        id = R.id.custDuration;
                        TextView custDuration2 = (TextView) ViewBindings.findChildViewById(view, R.id.custDuration);
                        if (custDuration2 != null) {
                            id = R.id.custIn;
                            TextView custIn2 = (TextView) ViewBindings.findChildViewById(view, R.id.custIn);
                            if (custIn2 != null) {
                                id = R.id.custName;
                                TextView custName2 = (TextView) ViewBindings.findChildViewById(view, R.id.custName);
                                if (custName2 != null) {
                                    id = R.id.custOut;
                                    TextView custOut2 = (TextView) ViewBindings.findChildViewById(view, R.id.custOut);
                                    if (custOut2 != null) {
                                        id = R.id.custVehicle;
                                        TextView custVehicle2 = (TextView) ViewBindings.findChildViewById(view, R.id.custVehicle);
                                        if (custVehicle2 != null) {
                                            id = R.id.done;
                                            AppCompatButton done2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.done);
                                            if (done2 != null) {
                                                id = R.id.parkingDate;
                                                TextView parkingDate2 = (TextView) ViewBindings.findChildViewById(view, R.id.parkingDate);
                                                if (parkingDate2 != null) {
                                                    id = R.id.price;
                                                    TextView price2 = (TextView) ViewBindings.findChildViewById(view, R.id.price);
                                                    if (price2 != null) {
                                                        id = R.id.timedate;
                                                        TextView timedate2 = (TextView) ViewBindings.findChildViewById(view, R.id.timedate);
                                                        if (timedate2 != null) {
                                                            id = R.id.transId;
                                                            TextView transId2 = (TextView) ViewBindings.findChildViewById(view, R.id.transId);
                                                            if (transId2 != null) {
                                                                return new ActivityConfirmBookingBinding((RelativeLayout) view, animationView3, animationView22, booking2, cusPhone2, custDuration2, custIn2, custName2, custOut2, custVehicle2, done2, parkingDate2, price2, timedate2, transId2);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}
