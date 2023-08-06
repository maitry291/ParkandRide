package com.example.parkandride;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MapsActivity$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ MapsActivity f$0;

    public /* synthetic */ MapsActivity$$ExternalSyntheticLambda0(MapsActivity mapsActivity) {
        this.f$0 = mapsActivity;
    }

    public final void onComplete(Task task) {
        MapsActivity.m46getCurrentLocation$lambda2(this.f$0, task);
    }
}
