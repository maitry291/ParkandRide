package com.google.firebase.auth.internal;

import android.content.Context;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzau implements OnSuccessListener {
    final /* synthetic */ TaskCompletionSource zza;
    final /* synthetic */ Context zzb;

    zzau(zzax zzax, TaskCompletionSource taskCompletionSource, Context context) {
        this.zza = taskCompletionSource;
        this.zzb = context;
    }

    public final /* bridge */ /* synthetic */ void onSuccess(Object obj) {
        this.zza.setResult((AuthResult) obj);
        zzax.zze(this.zzb);
    }
}
