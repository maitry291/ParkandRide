package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.parkandride.R;

public final class ActivityParkingSlot2Binding implements ViewBinding {
    public final LinearLayout a1;
    public final LinearLayout a2;
    public final LinearLayout p;
    private final RelativeLayout rootView;

    private ActivityParkingSlot2Binding(RelativeLayout rootView2, LinearLayout a12, LinearLayout a22, LinearLayout p2) {
        this.rootView = rootView2;
        this.a1 = a12;
        this.a2 = a22;
        this.p = p2;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityParkingSlot2Binding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityParkingSlot2Binding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_parking_slot2, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityParkingSlot2Binding bind(View rootView2) {
        int id = R.id.a1;
        LinearLayout a12 = (LinearLayout) ViewBindings.findChildViewById(rootView2, R.id.a1);
        if (a12 != null) {
            id = R.id.a2;
            LinearLayout a22 = (LinearLayout) ViewBindings.findChildViewById(rootView2, R.id.a2);
            if (a22 != null) {
                id = R.id.p;
                LinearLayout p2 = (LinearLayout) ViewBindings.findChildViewById(rootView2, R.id.p);
                if (p2 != null) {
                    return new ActivityParkingSlot2Binding((RelativeLayout) rootView2, a12, a22, p2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}
