package com.example.parkandride;

import com.example.parkandride.models.Message;
import com.google.android.gms.tasks.OnSuccessListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CustomerSupport$$ExternalSyntheticLambda1 implements OnSuccessListener {
    public final /* synthetic */ CustomerSupport f$0;
    public final /* synthetic */ Message f$1;

    public /* synthetic */ CustomerSupport$$ExternalSyntheticLambda1(CustomerSupport customerSupport, Message message) {
        this.f$0 = customerSupport;
        this.f$1 = message;
    }

    public final void onSuccess(Object obj) {
        CustomerSupport.m18adminChat$lambda4(this.f$0, this.f$1, (Void) obj);
    }
}
