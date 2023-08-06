package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzq;

/* compiled from: com.google.android.gms:play-services-location@@21.0.0 */
final class zzcw extends zzq {
    /* access modifiers changed from: private */
    public final zzcs zza;

    zzcw(zzcs zzcs) {
        this.zza = zzcs;
    }

    public final void zzd(LocationAvailability locationAvailability) throws RemoteException {
        this.zza.zza().notifyListener(new zzcu(this, locationAvailability));
    }

    public final void zze(LocationResult locationResult) throws RemoteException {
        this.zza.zza().notifyListener(new zzct(this, locationResult));
    }

    public final void zzf() {
        this.zza.zza().notifyListener(new zzcv(this));
    }

    /* access modifiers changed from: package-private */
    public final zzcw zzg(ListenerHolder listenerHolder) {
        this.zza.zzc(listenerHolder);
        return this;
    }

    /* access modifiers changed from: package-private */
    public final void zzh() {
        this.zza.zza().clear();
    }
}
