package com.google.android.gms.internal.p002firebaseauthapi;

import androidx.core.internal.view.SupportMenu;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzacr  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzacr {
    private final Object zza;
    private final int zzb;

    zzacr(Object obj, int i) {
        this.zza = obj;
        this.zzb = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzacr)) {
            return false;
        }
        zzacr zzacr = (zzacr) obj;
        if (this.zza == zzacr.zza && this.zzb == zzacr.zzb) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zza) * SupportMenu.USER_MASK) + this.zzb;
    }
}
