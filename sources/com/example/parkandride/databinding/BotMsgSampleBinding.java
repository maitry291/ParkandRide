package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.parkandride.R;

public final class BotMsgSampleBinding implements ViewBinding {
    public final TextView idTVBot;
    private final CardView rootView;

    private BotMsgSampleBinding(CardView rootView2, TextView idTVBot2) {
        this.rootView = rootView2;
        this.idTVBot = idTVBot2;
    }

    public CardView getRoot() {
        return this.rootView;
    }

    public static BotMsgSampleBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static BotMsgSampleBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.bot_msg_sample, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static BotMsgSampleBinding bind(View rootView2) {
        TextView idTVBot2 = (TextView) ViewBindings.findChildViewById(rootView2, R.id.idTVBot);
        if (idTVBot2 != null) {
            return new BotMsgSampleBinding((CardView) rootView2, idTVBot2);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(rootView2.getResources().getResourceName(R.id.idTVBot)));
    }
}
