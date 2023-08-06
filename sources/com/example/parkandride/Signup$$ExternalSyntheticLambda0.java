package com.example.parkandride;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Signup$$ExternalSyntheticLambda0 implements OnCompleteListener {
    public final /* synthetic */ Signup f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ DatabaseReference f$3;

    public /* synthetic */ Signup$$ExternalSyntheticLambda0(Signup signup, String str, String str2, DatabaseReference databaseReference) {
        this.f$0 = signup;
        this.f$1 = str;
        this.f$2 = str2;
        this.f$3 = databaseReference;
    }

    public final void onComplete(Task task) {
        Signup.m53onCreate$lambda3$lambda2(this.f$0, this.f$1, this.f$2, this.f$3, task);
    }
}
