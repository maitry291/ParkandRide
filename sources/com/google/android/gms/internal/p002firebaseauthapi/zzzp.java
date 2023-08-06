package com.google.android.gms.internal.p002firebaseauthapi;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Strings;
import com.google.firebase.auth.zze;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.google.android.gms.internal.firebase-auth-api.zzzp  reason: invalid package */
/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzzp extends AbstractSafeParcelable implements zzxn<zzzp> {
    public static final Parcelable.Creator<zzzp> CREATOR = new zzzq();
    private static final String zza = zzzp.class.getSimpleName();
    private zzzt zzb;

    public zzzp() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final /* bridge */ /* synthetic */ zzxn zza(String str) throws zzvg {
        zzzt zzzt;
        int i;
        zzzr zzzr;
        String str2 = str;
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (!jSONObject.has("users")) {
                this.zzb = new zzzt();
            } else {
                JSONArray optJSONArray = jSONObject.optJSONArray("users");
                Parcelable.Creator<zzzt> creator = zzzt.CREATOR;
                if (optJSONArray != null) {
                    if (optJSONArray.length() != 0) {
                        ArrayList arrayList = new ArrayList(optJSONArray.length());
                        boolean z = false;
                        int i2 = 0;
                        while (i2 < optJSONArray.length()) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i2);
                            if (jSONObject2 == null) {
                                zzzr = new zzzr();
                                i = i2;
                            } else {
                                String emptyToNull = Strings.emptyToNull(jSONObject2.optString("localId", (String) null));
                                String emptyToNull2 = Strings.emptyToNull(jSONObject2.optString("email", (String) null));
                                boolean optBoolean = jSONObject2.optBoolean("emailVerified", z);
                                String emptyToNull3 = Strings.emptyToNull(jSONObject2.optString("displayName", (String) null));
                                String emptyToNull4 = Strings.emptyToNull(jSONObject2.optString("photoUrl", (String) null));
                                zzaag zza2 = zzaag.zza(jSONObject2.optJSONArray("providerUserInfo"));
                                String emptyToNull5 = Strings.emptyToNull(jSONObject2.optString("rawPassword", (String) null));
                                String emptyToNull6 = Strings.emptyToNull(jSONObject2.optString("phoneNumber", (String) null));
                                i = i2;
                                long optLong = jSONObject2.optLong("createdAt", 0);
                                zzzr = new zzzr(emptyToNull, emptyToNull2, optBoolean, emptyToNull3, emptyToNull4, zza2, emptyToNull5, emptyToNull6, optLong, jSONObject2.optLong("lastLoginAt", 0), false, (zze) null, zzaac.zzf(jSONObject2.optJSONArray("mfaInfo")));
                            }
                            arrayList.add(zzzr);
                            i2 = i + 1;
                            z = false;
                        }
                        zzzt = new zzzt(arrayList);
                        this.zzb = zzzt;
                    }
                }
                zzzt = new zzzt(new ArrayList());
                this.zzb = zzzt;
            }
            return this;
        } catch (NullPointerException | JSONException e) {
            throw zzabk.zza(e, zza, str2);
        }
    }

    public final List zzb() {
        return this.zzb.zzb();
    }

    zzzp(zzzt zzzt) {
        zzzt zzzt2;
        if (zzzt == null) {
            zzzt2 = new zzzt();
        } else {
            zzzt2 = zzzt.zza(zzzt);
        }
        this.zzb = zzzt2;
    }
}
