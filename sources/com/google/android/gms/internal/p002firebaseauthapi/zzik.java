package com.google.android.gms.internal.p002firebaseauthapi;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzik  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzik {
    public static final zzik zza = new zzik("SHA1");
    public static final zzik zzb = new zzik("SHA224");
    public static final zzik zzc = new zzik("SHA256");
    public static final zzik zzd = new zzik("SHA384");
    public static final zzik zze = new zzik("SHA512");
    private final String zzf;

    private zzik(String str) {
        this.zzf = str;
    }

    public final String toString() {
        return this.zzf;
    }
}
