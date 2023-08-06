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

public final class ActivityParkingSlots1Binding implements ViewBinding {
    public final LinearLayout a1;
    public final LinearLayout a2;
    public final LinearLayout a3;
    public final LinearLayout a4;
    public final LinearLayout a5;
    public final TextView carSlot2;
    private final RelativeLayout rootView;

    private ActivityParkingSlots1Binding(RelativeLayout rootView2, LinearLayout a12, LinearLayout a22, LinearLayout a32, LinearLayout a42, LinearLayout a52, TextView carSlot22) {
        this.rootView = rootView2;
        this.a1 = a12;
        this.a2 = a22;
        this.a3 = a32;
        this.a4 = a42;
        this.a5 = a52;
        this.carSlot2 = carSlot22;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityParkingSlots1Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityParkingSlots1Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_parking_slots1, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityParkingSlots1Binding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.a1;
        LinearLayout a12 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.a1);
        if (a12 != null) {
            id = R.id.a2;
            LinearLayout a22 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.a2);
            if (a22 != null) {
                id = R.id.a3;
                LinearLayout a32 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.a3);
                if (a32 != null) {
                    id = R.id.a4;
                    LinearLayout a42 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.a4);
                    if (a42 != null) {
                        id = R.id.a5;
                        LinearLayout a52 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.a5);
                        if (a52 != null) {
                            id = R.id.carSlot2;
                            TextView carSlot22 = (TextView) ViewBindings.findChildViewById(view, R.id.carSlot2);
                            if (carSlot22 != null) {
                                return new ActivityParkingSlots1Binding((RelativeLayout) view, a12, a22, a32, a42, a52, carSlot22);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}
