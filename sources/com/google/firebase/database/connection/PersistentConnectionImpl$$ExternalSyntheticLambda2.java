package com.google.firebase.database.connection;

import com.google.android.gms.tasks.OnFailureListener;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PersistentConnectionImpl$$ExternalSyntheticLambda2 implements OnFailureListener {
    public final /* synthetic */ PersistentConnectionImpl f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ PersistentConnectionImpl$$ExternalSyntheticLambda2(PersistentConnectionImpl persistentConnectionImpl, long j) {
        this.f$0 = persistentConnectionImpl;
        this.f$1 = j;
    }

    public final void onFailure(Exception exc) {
        this.f$0.m1367lambda$tryScheduleReconnect$2$comgooglefirebasedatabaseconnectionPersistentConnectionImpl(this.f$1, exc);
    }
}
