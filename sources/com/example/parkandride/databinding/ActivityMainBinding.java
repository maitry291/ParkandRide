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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public final class ActivityMainBinding implements ViewBinding {
    public final FloatingActionButton addFab;
    public final LinearLayout area;
    public final TextView h1;
    public final TextView head;
    public final LinearLayout ll1;
    public final LinearLayout ll2;
    public final TextView location;
    public final FloatingActionButton logoutFab;
    public final LinearLayout opt1;
    public final LinearLayout opt2;
    public final LinearLayout opt3;
    public final LinearLayout opt4;
    public final FloatingActionButton profileFab;
    private final RelativeLayout rootView;

    private ActivityMainBinding(RelativeLayout rootView2, FloatingActionButton addFab2, LinearLayout area2, TextView h12, TextView head2, LinearLayout ll12, LinearLayout ll22, TextView location2, FloatingActionButton logoutFab2, LinearLayout opt12, LinearLayout opt22, LinearLayout opt32, LinearLayout opt42, FloatingActionButton profileFab2) {
        this.rootView = rootView2;
        this.addFab = addFab2;
        this.area = area2;
        this.h1 = h12;
        this.head = head2;
        this.ll1 = ll12;
        this.ll2 = ll22;
        this.location = location2;
        this.logoutFab = logoutFab2;
        this.opt1 = opt12;
        this.opt2 = opt22;
        this.opt3 = opt32;
        this.opt4 = opt42;
        this.profileFab = profileFab2;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityMainBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.add_fab;
        FloatingActionButton addFab2 = (FloatingActionButton) ViewBindings.findChildViewById(view, R.id.add_fab);
        if (addFab2 != null) {
            id = R.id.area;
            LinearLayout area2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.area);
            if (area2 != null) {
                id = R.id.h1;
                TextView h12 = (TextView) ViewBindings.findChildViewById(view, R.id.h1);
                if (h12 != null) {
                    id = R.id.head;
                    TextView head2 = (TextView) ViewBindings.findChildViewById(view, R.id.head);
                    if (head2 != null) {
                        id = R.id.ll1;
                        LinearLayout ll12 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll1);
                        if (ll12 != null) {
                            id = R.id.ll2;
                            LinearLayout ll22 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.ll2);
                            if (ll22 != null) {
                                id = R.id.location;
                                TextView location2 = (TextView) ViewBindings.findChildViewById(view, R.id.location);
                                if (location2 != null) {
                                    id = R.id.logout_fab;
                                    FloatingActionButton logoutFab2 = (FloatingActionButton) ViewBindings.findChildViewById(view, R.id.logout_fab);
                                    if (logoutFab2 != null) {
                                        id = R.id.opt1;
                                        LinearLayout opt12 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.opt1);
                                        if (opt12 != null) {
                                            id = R.id.opt2;
                                            LinearLayout opt22 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.opt2);
                                            if (opt22 != null) {
                                                id = R.id.opt3;
                                                LinearLayout opt32 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.opt3);
                                                if (opt32 != null) {
                                                    id = R.id.opt4;
                                                    LinearLayout opt42 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.opt4);
                                                    if (opt42 != null) {
                                                        id = R.id.profile_fab;
                                                        FloatingActionButton profileFab2 = (FloatingActionButton) ViewBindings.findChildViewById(view, R.id.profile_fab);
                                                        if (profileFab2 != null) {
                                                            return new ActivityMainBinding((RelativeLayout) view, addFab2, area2, h12, head2, ll12, ll22, location2, logoutFab2, opt12, opt22, opt32, opt42, profileFab2);
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
