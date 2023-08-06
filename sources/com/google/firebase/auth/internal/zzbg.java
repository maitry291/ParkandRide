package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.p002firebaseauthapi.zzqx;
import com.google.android.gms.internal.p002firebaseauthapi.zzzy;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorInfo;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzbg {
    private final Context zza;
    private final String zzb;
    private final SharedPreferences zzc;
    private final Logger zzd = new Logger("StorageHelpers", new String[0]);

    public zzbg(Context context, String str) {
        Preconditions.checkNotNull(context);
        String checkNotEmpty = Preconditions.checkNotEmpty(str);
        this.zzb = checkNotEmpty;
        Context applicationContext = context.getApplicationContext();
        this.zza = applicationContext;
        this.zzc = applicationContext.getSharedPreferences(String.format("com.google.firebase.auth.api.Store.%s", new Object[]{checkNotEmpty}), 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0084, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0085, code lost:
        android.util.Log.d("DefaultAuthUserInfo", "Failed to unpack UserInfo from JSON");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0091, code lost:
        throw new com.google.android.gms.internal.p002firebaseauthapi.zzqx(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x013f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0145, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0146, code lost:
        r26.zzd.wtf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x014e, code lost:
        return null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x013f A[ExcHandler: zzqx | ArrayIndexOutOfBoundsException | IllegalArgumentException (e java.lang.Throwable), Splitter:B:1:0x000c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.firebase.auth.internal.zzx zzf(org.json.JSONObject r27) {
        /*
            r26 = this;
            r1 = r27
            java.lang.String r2 = "enrollmentTimestamp"
            java.lang.String r3 = "userMultiFactorInfo"
            java.lang.String r0 = "userMetadata"
            java.lang.String r5 = "cachedTokenState"
            java.lang.String r5 = r1.getString(r5)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r6 = "applicationName"
            java.lang.String r6 = r1.getString(r6)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r7 = "anonymous"
            boolean r7 = r1.getBoolean(r7)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r8 = "2"
            java.lang.String r9 = "version"
            java.lang.String r9 = r1.getString(r9)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            if (r9 == 0) goto L_0x0028
            r8 = r9
        L_0x0028:
            java.lang.String r9 = "userInfos"
            org.json.JSONArray r9 = r1.getJSONArray(r9)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            int r10 = r9.length()     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r11.<init>(r10)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r13 = 0
        L_0x0039:
            java.lang.String r14 = "displayName"
            java.lang.String r15 = "phoneNumber"
            if (r13 >= r10) goto L_0x0092
            java.lang.String r12 = r9.getString(r13)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            android.os.Parcelable$Creator<com.google.firebase.auth.internal.zzt> r16 = com.google.firebase.auth.internal.zzt.CREATOR     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r4.<init>(r12)     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r12 = "userId"
            java.lang.String r18 = r4.optString(r12)     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r12 = "providerId"
            java.lang.String r19 = r4.optString(r12)     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r12 = "email"
            java.lang.String r20 = r4.optString(r12)     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r21 = r4.optString(r15)     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r22 = r4.optString(r14)     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r12 = "photoUrl"
            java.lang.String r23 = r4.optString(r12)     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r12 = "isEmailVerified"
            boolean r24 = r4.optBoolean(r12)     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r12 = "rawUserInfo"
            java.lang.String r25 = r4.optString(r12)     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            com.google.firebase.auth.internal.zzt r4 = new com.google.firebase.auth.internal.zzt     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r17 = r4
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25)     // Catch:{ JSONException -> 0x0084, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r11.add(r4)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            int r13 = r13 + 1
            goto L_0x0039
        L_0x0084:
            r0 = move-exception
            java.lang.String r1 = "DefaultAuthUserInfo"
            java.lang.String r2 = "Failed to unpack UserInfo from JSON"
            android.util.Log.d(r1, r2)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            com.google.android.gms.internal.firebase-auth-api.zzqx r1 = new com.google.android.gms.internal.firebase-auth-api.zzqx     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r1.<init>(r0)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            throw r1     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
        L_0x0092:
            com.google.firebase.FirebaseApp r4 = com.google.firebase.FirebaseApp.getInstance(r6)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            com.google.firebase.auth.internal.zzx r6 = new com.google.firebase.auth.internal.zzx     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r6.<init>(r4, r11)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            boolean r4 = android.text.TextUtils.isEmpty(r5)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            if (r4 != 0) goto L_0x00a8
            com.google.android.gms.internal.firebase-auth-api.zzzy r4 = com.google.android.gms.internal.p002firebaseauthapi.zzzy.zzd(r5)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r6.zzh(r4)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
        L_0x00a8:
            if (r7 != 0) goto L_0x00ad
            r6.zzm()     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
        L_0x00ad:
            r6.zzl(r8)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            boolean r4 = r1.has(r0)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            if (r4 == 0) goto L_0x00d9
            org.json.JSONObject r0 = r1.getJSONObject(r0)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            android.os.Parcelable$Creator<com.google.firebase.auth.internal.zzz> r4 = com.google.firebase.auth.internal.zzz.CREATOR     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            if (r0 != 0) goto L_0x00c0
            r0 = 0
            goto L_0x00d4
        L_0x00c0:
            java.lang.String r4 = "lastSignInTimestamp"
            long r4 = r0.getLong(r4)     // Catch:{ JSONException -> 0x00d2, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r7 = "creationTimestamp"
            long r7 = r0.getLong(r7)     // Catch:{ JSONException -> 0x00d2, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            com.google.firebase.auth.internal.zzz r0 = new com.google.firebase.auth.internal.zzz     // Catch:{ JSONException -> 0x00d2, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r0.<init>(r4, r7)     // Catch:{ JSONException -> 0x00d2, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            goto L_0x00d4
        L_0x00d2:
            r0 = move-exception
            r0 = 0
        L_0x00d4:
            if (r0 == 0) goto L_0x00d9
            r6.zzr(r0)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
        L_0x00d9:
            boolean r0 = r1.has(r3)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            if (r0 == 0) goto L_0x013e
            org.json.JSONArray r0 = r1.getJSONArray(r3)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            if (r0 == 0) goto L_0x013e
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r1.<init>()     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r12 = 0
        L_0x00ec:
            int r3 = r0.length()     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            if (r12 >= r3) goto L_0x013b
            java.lang.String r3 = r0.getString(r12)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r4.<init>(r3)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r3 = "phone"
            java.lang.String r5 = "factorIdKey"
            java.lang.String r5 = r4.optString(r5)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            boolean r3 = r3.equals(r5)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            if (r3 == 0) goto L_0x0134
            android.os.Parcelable$Creator<com.google.firebase.auth.PhoneMultiFactorInfo> r3 = com.google.firebase.auth.PhoneMultiFactorInfo.CREATOR     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            boolean r3 = r4.has(r2)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            if (r3 == 0) goto L_0x012c
            java.lang.String r3 = "uid"
            java.lang.String r18 = r4.optString(r3)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r19 = r4.optString(r14)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            long r20 = r4.optLong(r2)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r22 = r4.optString(r15)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            com.google.firebase.auth.PhoneMultiFactorInfo r3 = new com.google.firebase.auth.PhoneMultiFactorInfo     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            r17 = r3
            r17.<init>(r18, r19, r20, r22)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            goto L_0x0135
        L_0x012c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            java.lang.String r1 = "An enrollment timestamp in seconds of UTC time since Unix epoch is required to build a PhoneMultiFactorInfo instance."
            r0.<init>(r1)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            throw r0     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
        L_0x0134:
            r3 = 0
        L_0x0135:
            r1.add(r3)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
            int r12 = r12 + 1
            goto L_0x00ec
        L_0x013b:
            r6.zzi(r1)     // Catch:{ JSONException -> 0x0145, ArrayIndexOutOfBoundsException -> 0x0143, IllegalArgumentException -> 0x0141, zzqx -> 0x013f }
        L_0x013e:
            return r6
        L_0x013f:
            r0 = move-exception
            goto L_0x0146
        L_0x0141:
            r0 = move-exception
            goto L_0x0146
        L_0x0143:
            r0 = move-exception
            goto L_0x0146
        L_0x0145:
            r0 = move-exception
        L_0x0146:
            r1 = r26
            com.google.android.gms.common.logging.Logger r2 = r1.zzd
            r2.wtf(r0)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzbg.zzf(org.json.JSONObject):com.google.firebase.auth.internal.zzx");
    }

    public final FirebaseUser zza() {
        String string = this.zzc.getString("com.google.firebase.auth.FIREBASE_USER", (String) null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(string);
            if (jSONObject.has("type") && "com.google.firebase.auth.internal.DefaultFirebaseUser".equalsIgnoreCase(jSONObject.optString("type"))) {
                return zzf(jSONObject);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public final zzzy zzb(FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(firebaseUser);
        String string = this.zzc.getString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}), (String) null);
        if (string != null) {
            return zzzy.zzd(string);
        }
        return null;
    }

    public final void zzc(String str) {
        this.zzc.edit().remove(str).apply();
    }

    public final void zzd(FirebaseUser firebaseUser) {
        String str;
        Preconditions.checkNotNull(firebaseUser);
        JSONObject jSONObject = new JSONObject();
        if (zzx.class.isAssignableFrom(firebaseUser.getClass())) {
            zzx zzx = (zzx) firebaseUser;
            try {
                jSONObject.put("cachedTokenState", zzx.zzf());
                jSONObject.put("applicationName", zzx.zza().getName());
                jSONObject.put("type", "com.google.firebase.auth.internal.DefaultFirebaseUser");
                if (zzx.zzo() != null) {
                    JSONArray jSONArray = new JSONArray();
                    List zzo = zzx.zzo();
                    int size = zzo.size();
                    if (zzo.size() > 30) {
                        this.zzd.w("Provider user info list size larger than max size, truncating list to %d. Actual list size: %d", 30, Integer.valueOf(zzo.size()));
                        size = 30;
                    }
                    for (int i = 0; i < size; i++) {
                        jSONArray.put(((zzt) zzo.get(i)).zzb());
                    }
                    jSONObject.put("userInfos", jSONArray);
                }
                jSONObject.put("anonymous", zzx.isAnonymous());
                jSONObject.put("version", "2");
                if (zzx.getMetadata() != null) {
                    jSONObject.put("userMetadata", ((zzz) zzx.getMetadata()).zza());
                }
                List<MultiFactorInfo> enrolledFactors = new zzac(zzx).getEnrolledFactors();
                if (!enrolledFactors.isEmpty()) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i2 = 0; i2 < enrolledFactors.size(); i2++) {
                        jSONArray2.put(enrolledFactors.get(i2).toJson());
                    }
                    jSONObject.put("userMultiFactorInfo", jSONArray2);
                }
                str = jSONObject.toString();
            } catch (Exception e) {
                this.zzd.wtf("Failed to turn object into JSON", e, new Object[0]);
                throw new zzqx(e);
            }
        } else {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            this.zzc.edit().putString("com.google.firebase.auth.FIREBASE_USER", str).apply();
        }
    }

    public final void zze(FirebaseUser firebaseUser, zzzy zzzy) {
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzzy);
        this.zzc.edit().putString(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{firebaseUser.getUid()}), zzzy.zzh()).apply();
    }
}
