package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.core.ServerValues;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbd {
    private static final List zza = new ArrayList(Arrays.asList(new String[]{"firebaseAppName", "firebaseUserUid", "operation", "tenantId", "verifyAssertionRequest", "statusCode", "statusMessage", ServerValues.NAME_OP_TIMESTAMP}));
    private static final zzbd zzb = new zzbd();
    private Task zzc;
    private Task zzd;
    private long zze = 0;

    private zzbd() {
    }

    public static zzbd zzc() {
        return zzb;
    }

    private static final void zzf(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (String remove : zza) {
            edit.remove(remove);
        }
        edit.commit();
    }

    public final Task zza() {
        if (DefaultClock.getInstance().currentTimeMillis() - this.zze < 3600000) {
            return this.zzc;
        }
        return null;
    }

    public final Task zzb() {
        if (DefaultClock.getInstance().currentTimeMillis() - this.zze < 3600000) {
            return this.zzd;
        }
        return null;
    }

    public final void zzd(Context context) {
        Preconditions.checkNotNull(context);
        zzf(context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0));
        this.zzc = null;
        this.zze = 0;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0073, code lost:
        if (r4.equals("com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN") != false) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0105, code lost:
        if (r1.equals("com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA") != false) goto L_0x0109;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(com.google.firebase.auth.FirebaseAuth r13) {
        /*
            r12 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)
            com.google.firebase.FirebaseApp r0 = r13.getApp()
            android.content.Context r0 = r0.getApplicationContext()
            java.lang.String r1 = "com.google.firebase.auth.internal.ProcessDeathHelper"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            java.lang.String r1 = "firebaseAppName"
            java.lang.String r3 = ""
            java.lang.String r1 = r0.getString(r1, r3)
            com.google.firebase.FirebaseApp r4 = r13.getApp()
            java.lang.String r4 = r4.getName()
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L_0x0029
            return
        L_0x0029:
            java.lang.String r1 = "verifyAssertionRequest"
            boolean r4 = r0.contains(r1)
            r5 = -1
            java.lang.String r6 = "operation"
            r7 = 0
            java.lang.String r9 = "timestamp"
            r10 = 0
            if (r4 == 0) goto L_0x00df
            java.lang.String r1 = r0.getString(r1, r3)
            android.os.Parcelable$Creator<com.google.android.gms.internal.firebase-auth-api.zzaay> r4 = com.google.android.gms.internal.p002firebaseauthapi.zzaay.CREATOR
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeFromString(r1, r4)
            com.google.android.gms.internal.firebase-auth-api.zzaay r1 = (com.google.android.gms.internal.p002firebaseauthapi.zzaay) r1
            java.lang.String r4 = r0.getString(r6, r3)
            java.lang.String r6 = "tenantId"
            java.lang.String r6 = r0.getString(r6, r10)
            java.lang.String r11 = "firebaseUserUid"
            java.lang.String r3 = r0.getString(r11, r3)
            long r7 = r0.getLong(r9, r7)
            r12.zze = r7
            if (r6 == 0) goto L_0x0064
            r13.setTenantId(r6)
            r1.zzf(r6)
        L_0x0064:
            int r6 = r4.hashCode()
            switch(r6) {
                case -98509410: goto L_0x0080;
                case 175006864: goto L_0x0076;
                case 1450464913: goto L_0x006c;
                default: goto L_0x006b;
            }
        L_0x006b:
            goto L_0x008a
        L_0x006c:
            java.lang.String r6 = "com.google.firebase.auth.internal.NONGMSCORE_SIGN_IN"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x006b
            goto L_0x008b
        L_0x0076:
            java.lang.String r2 = "com.google.firebase.auth.internal.NONGMSCORE_LINK"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x006b
            r2 = 1
            goto L_0x008b
        L_0x0080:
            java.lang.String r2 = "com.google.firebase.auth.internal.NONGMSCORE_REAUTHENTICATE"
            boolean r2 = r4.equals(r2)
            if (r2 == 0) goto L_0x006b
            r2 = 2
            goto L_0x008b
        L_0x008a:
            r2 = -1
        L_0x008b:
            switch(r2) {
                case 0: goto L_0x00d1;
                case 1: goto L_0x00b1;
                case 2: goto L_0x0091;
                default: goto L_0x008e;
            }
        L_0x008e:
            r12.zzc = r10
            goto L_0x00db
        L_0x0091:
            com.google.firebase.auth.FirebaseUser r2 = r13.getCurrentUser()
            java.lang.String r2 = r2.getUid()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00ae
            com.google.firebase.auth.FirebaseUser r2 = r13.getCurrentUser()
            com.google.firebase.auth.zze r1 = com.google.firebase.auth.zze.zzb(r1)
            com.google.android.gms.tasks.Task r13 = r13.zzf(r2, r1)
            r12.zzc = r13
            goto L_0x00db
        L_0x00ae:
            r12.zzc = r10
            goto L_0x00db
        L_0x00b1:
            com.google.firebase.auth.FirebaseUser r2 = r13.getCurrentUser()
            java.lang.String r2 = r2.getUid()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x00ce
            com.google.firebase.auth.FirebaseUser r2 = r13.getCurrentUser()
            com.google.firebase.auth.zze r1 = com.google.firebase.auth.zze.zzb(r1)
            com.google.android.gms.tasks.Task r13 = r13.zzd(r2, r1)
            r12.zzc = r13
            goto L_0x00db
        L_0x00ce:
            r12.zzc = r10
            goto L_0x00db
        L_0x00d1:
            com.google.firebase.auth.zze r1 = com.google.firebase.auth.zze.zzb(r1)
            com.google.android.gms.tasks.Task r13 = r13.signInWithCredential(r1)
            r12.zzc = r13
        L_0x00db:
            zzf(r0)
            return
        L_0x00df:
            java.lang.String r13 = "recaptchaToken"
            boolean r1 = r0.contains(r13)
            if (r1 == 0) goto L_0x0119
            java.lang.String r13 = r0.getString(r13, r3)
            java.lang.String r1 = r0.getString(r6, r3)
            long r3 = r0.getLong(r9, r7)
            r12.zze = r3
            int r3 = r1.hashCode()
            switch(r3) {
                case -214796028: goto L_0x00fe;
                default: goto L_0x00fd;
            }
        L_0x00fd:
            goto L_0x0108
        L_0x00fe:
            java.lang.String r3 = "com.google.firebase.auth.internal.ACTION_SHOW_RECAPTCHA"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x00fd
            goto L_0x0109
        L_0x0108:
            r2 = -1
        L_0x0109:
            switch(r2) {
                case 0: goto L_0x010f;
                default: goto L_0x010c;
            }
        L_0x010c:
            r12.zzd = r10
            goto L_0x0115
        L_0x010f:
            com.google.android.gms.tasks.Task r13 = com.google.android.gms.tasks.Tasks.forResult(r13)
            r12.zzd = r13
        L_0x0115:
            zzf(r0)
            return
        L_0x0119:
            java.lang.String r13 = "statusCode"
            boolean r1 = r0.contains(r13)
            if (r1 == 0) goto L_0x0146
            r1 = 17062(0x42a6, float:2.3909E-41)
            int r13 = r0.getInt(r13, r1)
            java.lang.String r1 = "statusMessage"
            java.lang.String r1 = r0.getString(r1, r3)
            com.google.android.gms.common.api.Status r2 = new com.google.android.gms.common.api.Status
            r2.<init>((int) r13, (java.lang.String) r1)
            long r3 = r0.getLong(r9, r7)
            r12.zze = r3
            zzf(r0)
            com.google.firebase.FirebaseException r13 = com.google.android.gms.internal.p002firebaseauthapi.zzxc.zza(r2)
            com.google.android.gms.tasks.Task r13 = com.google.android.gms.tasks.Tasks.forException(r13)
            r12.zzc = r13
        L_0x0146:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzbd.zze(com.google.firebase.auth.FirebaseAuth):void");
    }
}
