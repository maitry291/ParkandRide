package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.parkandride.R;

public final class ActivityMapsBinding implements ViewBinding {
    public final AppCompatButton confirm;
    private final RelativeLayout rootView;

    private ActivityMapsBinding(RelativeLayout rootView2, AppCompatButton confirm2) {
        this.rootView = rootView2;
        this.confirm = confirm2;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMapsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityMapsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_maps, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityMapsBinding bind(View rootView2) {
        AppCompatButton confirm2 = (AppCompatButton) ViewBindings.findChildViewById(rootView2, R.id.confirm);
        if (confirm2 != null) {
            return new ActivityMapsBinding((RelativeLayout) rootView2, confirm2);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(R.id.confirm)));
    }
}
