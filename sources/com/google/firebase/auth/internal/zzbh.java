package com.google.firebase.auth.internal;

import com.google.android.gms.common.api.internal.BackgroundDetector;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzbh implements BackgroundDetector.BackgroundStateChangeListener {
    final /* synthetic */ zzbi zza;

    zzbh(zzbi zzbi) {
        this.zza = zzbi;
    }

    public final void onBackgroundStateChanged(boolean z) {
        if (z) {
            this.zza.zzc = true;
            this.zza.zzc();
            return;
        }
        this.zza.zzc = false;
        if (this.zza.zzg()) {
            this.zza.zzb.zzc();
        }
    }
}
