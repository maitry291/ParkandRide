package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.StatusCallback;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-location@@21.0.0 */
public final /* synthetic */ class zzab implements RemoteCall {
    public final /* synthetic */ PendingIntent zza;

    public /* synthetic */ zzab(PendingIntent pendingIntent) {
        this.zza = pendingIntent;
    }

    public final void accept(Object obj, Object obj2) {
        PendingIntent pendingIntent = this.zza;
        Api api = zzag.zzb;
        zzaf zzaf = new zzaf((TaskCompletionSource) obj2);
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        Preconditions.checkNotNull(zzaf, "ResultHolder not provided.");
        ((zzo) ((zzf) obj).getService()).zzp(pendingIntent, new StatusCallback(zzaf));
    }
}
