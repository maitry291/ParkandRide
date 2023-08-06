package com.google.android.gms.internal.p002firebaseauthapi;

import java.io.IOException;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzadn  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public class zzadn extends IOException {
    private zzaek zza = null;

    public zzadn(IOException iOException) {
        super(iOException.getMessage(), iOException);
    }

    static zzadm zza() {
        return new zzadm("Protocol message tag had invalid wire type.");
    }

    static zzadn zzb() {
        return new zzadn("Protocol message end-group tag did not match expected tag.");
    }

    static zzadn zzc() {
        return new zzadn("Protocol message contained an invalid tag (zero).");
    }

    static zzadn zzd() {
        return new zzadn("Protocol message had invalid UTF-8.");
    }

    static zzadn zze() {
        return new zzadn("CodedInputStream encountered a malformed varint.");
    }

    static zzadn zzf() {
        return new zzadn("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzadn zzg() {
        return new zzadn("Failed to parse the message.");
    }

    static zzadn zzi() {
        return new zzadn("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final zzadn zzh(zzaek zzaek) {
        this.zza = zzaek;
        return this;
    }

    public zzadn(String str) {
        super(str);
    }
}
