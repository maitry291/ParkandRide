package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.parkandride.R;

public final class UserMsgSampleBinding implements ViewBinding {
    public final TextView idTVUser;
    private final CardView rootView;

    private UserMsgSampleBinding(CardView rootView2, TextView idTVUser2) {
        this.rootView = rootView2;
        this.idTVUser = idTVUser2;
    }

    public CardView getRoot() {
        return this.rootView;
    }

    public static UserMsgSampleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static UserMsgSampleBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.user_msg_sample, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static UserMsgSampleBinding bind(View rootView2) {
        TextView idTVUser2 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.idTVUser);
        if (idTVUser2 != null) {
            return new UserMsgSampleBinding((CardView) rootView2, idTVUser2);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(R.id.idTVUser)));
    }
}
