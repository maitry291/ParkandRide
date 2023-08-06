package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.example.parkandride.R;

public final class ActivityProfieBinding implements ViewBinding {
    public final RecyclerView allBookings;
    public final AppCompatButton customerSupport;
    public final ImageView logo;
    public final LottieAnimationView p1;
    public final RelativeLayout r1;
    private final RelativeLayout rootView;
    public final TextView total;
    public final TextView userEmail;
    public final TextView userName;

    private ActivityProfieBinding(RelativeLayout rootView2, RecyclerView allBookings2, AppCompatButton customerSupport2, ImageView logo2, LottieAnimationView p12, RelativeLayout r12, TextView total2, TextView userEmail2, TextView userName2) {
        this.rootView = rootView2;
        this.allBookings = allBookings2;
        this.customerSupport = customerSupport2;
        this.logo = logo2;
        this.p1 = p12;
        this.r1 = r12;
        this.total = total2;
        this.userEmail = userEmail2;
        this.userName = userName2;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityProfieBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityProfieBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_profie, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityProfieBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.allBookings;
        RecyclerView allBookings2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.allBookings);
        if (allBookings2 != null) {
            id = R.id.customerSupport;
            AppCompatButton customerSupport2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.customerSupport);
            if (customerSupport2 != null) {
                id = R.id.logo;
                ImageView logo2 = (ImageView) ViewBindings.findChildViewById(view, R.id.logo);
                if (logo2 != null) {
                    id = R.id.p1;
                    LottieAnimationView p12 = (LottieAnimationView) ViewBindings.findChildViewById(view, R.id.p1);
                    if (p12 != null) {
                        id = R.id.r1;
                        RelativeLayout r12 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.r1);
                        if (r12 != null) {
                            id = R.id.total;
                            TextView total2 = (TextView) ViewBindings.findChildViewById(view, R.id.total);
                            if (total2 != null) {
                                id = R.id.userEmail;
                                TextView userEmail2 = (TextView) ViewBindings.findChildViewById(view, R.id.userEmail);
                                if (userEmail2 != null) {
                                    id = R.id.userName;
                                    TextView userName2 = (TextView) ViewBindings.findChildViewById(view, R.id.userName);
                                    if (userName2 != null) {
                                        return new ActivityProfieBinding((RelativeLayout) view, allBookings2, customerSupport2, logo2, p12, r12, total2, userEmail2, userName2);
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
