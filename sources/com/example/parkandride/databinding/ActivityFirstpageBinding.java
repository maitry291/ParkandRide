package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.example.parkandride.R;

public final class ActivityFirstpageBinding implements ViewBinding {
    public final ImageView imageView;
    public final AppCompatButton logIn;
    public final RelativeLayout rl;
    private final RelativeLayout rootView;
    public final AppCompatButton signUp;
    public final LottieAnimationView splash;
    public final TextView textView;
    public final TextView textView2;

    private ActivityFirstpageBinding(RelativeLayout rootView2, ImageView imageView2, AppCompatButton logIn2, RelativeLayout rl2, AppCompatButton signUp2, LottieAnimationView splash2, TextView textView3, TextView textView22) {
        this.rootView = rootView2;
        this.imageView = imageView2;
        this.logIn = logIn2;
        this.rl = rl2;
        this.signUp = signUp2;
        this.splash = splash2;
        this.textView = textView3;
        this.textView2 = textView22;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityFirstpageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityFirstpageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_firstpage, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityFirstpageBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.imageView;
        ImageView imageView2 = (ImageView) ViewBindings.findChildViewById(view, R.id.imageView);
        if (imageView2 != null) {
            id = R.id.logIn;
            AppCompatButton logIn2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.logIn);
            if (logIn2 != null) {
                id = R.id.rl;
                RelativeLayout rl2 = (RelativeLayout) ViewBindings.findChildViewById(view, R.id.rl);
                if (rl2 != null) {
                    id = R.id.signUp;
                    AppCompatButton signUp2 = (AppCompatButton) ViewBindings.findChildViewById(view, R.id.signUp);
                    if (signUp2 != null) {
                        id = R.id.splash;
                        LottieAnimationView splash2 = (LottieAnimationView) ViewBindings.findChildViewById(view, R.id.splash);
                        if (splash2 != null) {
                            id = R.id.textView;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.textView);
                            if (textView3 != null) {
                                id = R.id.textView2;
                                TextView textView22 = (TextView) ViewBindings.findChildViewById(view, R.id.textView2);
                                if (textView22 != null) {
                                    return new ActivityFirstpageBinding((RelativeLayout) view, imageView2, logIn2, rl2, signUp2, splash2, textView3, textView22);
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
