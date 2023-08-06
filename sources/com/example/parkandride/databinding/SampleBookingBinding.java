package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.parkandride.R;

public final class SampleBookingBinding implements ViewBinding {
    public final LinearLayout l1;
    private final RelativeLayout rootView;
    public final TextView t1;
    public final TextView t2;
    public final TextView t3;
    public final TextView t4;

    private SampleBookingBinding(RelativeLayout rootView2, LinearLayout l12, TextView t12, TextView t22, TextView t32, TextView t42) {
        this.rootView = rootView2;
        this.l1 = l12;
        this.t1 = t12;
        this.t2 = t22;
        this.t3 = t32;
        this.t4 = t42;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static SampleBookingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static SampleBookingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.sample_booking, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static SampleBookingBinding bind(View rootView2) {
        int id = R.id.l1;
        LinearLayout l12 = (LinearLayout) ViewBindings.findChildViewById(rootView2, R.id.l1);
        if (l12 != null) {
            id = R.id.t1;
            TextView t12 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.t1);
            if (t12 != null) {
                id = R.id.t2;
                TextView t22 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.t2);
                if (t22 != null) {
                    id = R.id.t3;
                    TextView t32 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.t3);
                    if (t32 != null) {
                        id = R.id.t4;
                        TextView t42 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.t4);
                        if (t42 != null) {
                            return new SampleBookingBinding((RelativeLayout) rootView2, l12, t12, t22, t32, t42);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}
