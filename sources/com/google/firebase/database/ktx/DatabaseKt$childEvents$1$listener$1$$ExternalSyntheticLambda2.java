package com.google.firebase.database.ktx;

import com.google.firebase.database.DataSnapshot;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DatabaseKt$childEvents$1$listener$1$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ProducerScope f$0;
    public final /* synthetic */ DataSnapshot f$1;

    public /* synthetic */ DatabaseKt$childEvents$1$listener$1$$ExternalSyntheticLambda2(ProducerScope producerScope, DataSnapshot dataSnapshot) {
        this.f$0 = producerScope;
        this.f$1 = dataSnapshot;
    }

    public final void run() {
        DatabaseKt$childEvents$1$listener$1.m1377onChildRemoved$lambda2(this.f$0, this.f$1);
    }
}
