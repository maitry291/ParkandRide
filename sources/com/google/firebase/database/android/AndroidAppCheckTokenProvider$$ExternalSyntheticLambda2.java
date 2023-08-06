package com.google.firebase.database.android;

import com.google.firebase.appcheck.AppCheckTokenResult;
import com.google.firebase.database.core.TokenProvider;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AndroidAppCheckTokenProvider$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ TokenProvider.TokenChangeListener f$0;
    public final /* synthetic */ AppCheckTokenResult f$1;

    public /* synthetic */ AndroidAppCheckTokenProvider$$ExternalSyntheticLambda2(TokenProvider.TokenChangeListener tokenChangeListener, AppCheckTokenResult appCheckTokenResult) {
        this.f$0 = tokenChangeListener;
        this.f$1 = appCheckTokenResult;
    }

    public final void run() {
        this.f$0.onTokenChange(this.f$1.getToken());
    }
}
