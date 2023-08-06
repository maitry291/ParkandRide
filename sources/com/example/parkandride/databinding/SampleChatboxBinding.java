package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.parkandride.R;

public final class SampleChatboxBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView userLastmsg;
    public final TextView userName;

    private SampleChatboxBinding(LinearLayout rootView2, TextView userLastmsg2, TextView userName2) {
        this.rootView = rootView2;
        this.userLastmsg = userLastmsg2;
        this.userName = userName2;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static SampleChatboxBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static SampleChatboxBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.sample_chatbox, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static SampleChatboxBinding bind(View rootView2) {
        int id = R.id.user_lastmsg;
        TextView userLastmsg2 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.user_lastmsg);
        if (userLastmsg2 != null) {
            id = R.id.user_name;
            TextView userName2 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.user_name);
            if (userName2 != null) {
                return new SampleChatboxBinding((LinearLayout) rootView2, userLastmsg2, userName2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(id)));
    }
}
