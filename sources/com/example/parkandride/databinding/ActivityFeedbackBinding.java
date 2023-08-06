package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.parkandride.R;

public final class ActivityFeedbackBinding implements ViewBinding {
    private final ConstraintLayout rootView;

    private ActivityFeedbackBinding(ConstraintLayout rootView2) {
        this.rootView = rootView2;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFeedbackBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityFeedbackBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_feedback, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityFeedbackBinding bind(View rootView2) {
        if (rootView2 != null) {
            return new ActivityFeedbackBinding((ConstraintLayout) rootView2);
        }
        throw new NullPointerException("rootView");
    }
}
