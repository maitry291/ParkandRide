package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.parkandride.R;

public final class ActivityCustomerSupportBinding implements ViewBinding {
    public final EditText idEdtMessage;
    public final ImageView idIBSend;
    public final LinearLayout idLLMessage;
    public final RecyclerView idRVChats;
    public final LinearLayout load;
    public final TextView load1;
    public final TextView load2;
    private final RelativeLayout rootView;

    private ActivityCustomerSupportBinding(RelativeLayout rootView2, EditText idEdtMessage2, ImageView idIBSend2, LinearLayout idLLMessage2, RecyclerView idRVChats2, LinearLayout load3, TextView load12, TextView load22) {
        this.rootView = rootView2;
        this.idEdtMessage = idEdtMessage2;
        this.idIBSend = idIBSend2;
        this.idLLMessage = idLLMessage2;
        this.idRVChats = idRVChats2;
        this.load = load3;
        this.load1 = load12;
        this.load2 = load22;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static ActivityCustomerSupportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityCustomerSupportBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_customer_support, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityCustomerSupportBinding bind(View rootView2) {
        View view = rootView2;
        int id = R.id.idEdtMessage;
        EditText idEdtMessage2 = (EditText) ViewBindings.findChildViewById(view, R.id.idEdtMessage);
        if (idEdtMessage2 != null) {
            id = R.id.idIBSend;
            ImageView idIBSend2 = (ImageView) ViewBindings.findChildViewById(view, R.id.idIBSend);
            if (idIBSend2 != null) {
                id = R.id.idLLMessage;
                LinearLayout idLLMessage2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.idLLMessage);
                if (idLLMessage2 != null) {
                    id = R.id.idRVChats;
                    RecyclerView idRVChats2 = (RecyclerView) ViewBindings.findChildViewById(view, R.id.idRVChats);
                    if (idRVChats2 != null) {
                        id = R.id.load;
                        LinearLayout load3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.load);
                        if (load3 != null) {
                            id = R.id.load1;
                            TextView load12 = (TextView) ViewBindings.findChildViewById(view, R.id.load1);
                            if (load12 != null) {
                                id = R.id.load2;
                                TextView load22 = (TextView) ViewBindings.findChildViewById(view, R.id.load2);
                                if (load22 != null) {
                                    return new ActivityCustomerSupportBinding((RelativeLayout) view, idEdtMessage2, idIBSend2, idLLMessage2, idRVChats2, load3, load12, load22);
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
