package com.google.android.gms.internal.p002firebaseauthapi;

import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.CheckForNull;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzx  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
final class zzx {
    private static final Logger zza = Logger.getLogger(zzx.class.getName());
    private static final zzw zzb = new zzw((zzv) null);

    private zzx() {
    }

    static zzq zza(String str) {
        return new zzt(Pattern.compile("[.-]"));
    }

    static String zzb(@CheckForNull String str) {
        return str == null ? "" : str;
    }

    static boolean zzc(@CheckForNull String str) {
        return str == null || str.isEmpty();
    }
}
