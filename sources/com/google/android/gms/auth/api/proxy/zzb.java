package com.google.android.gms.auth.api.proxy;

import android.os.Parcelable;

public final class zzb implements Parcelable.Creator<ProxyResponse> {
    public final /* synthetic */ Object[] newArray(int i) {
        return new ProxyResponse[i];
    }

    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r11) {
        /*
            r10 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r11)
            r1 = 0
            r2 = 0
            r6 = r1
            r8 = r6
            r9 = r8
            r4 = 0
            r5 = 0
            r7 = 0
        L_0x0012:
            int r1 = r11.dataPosition()
            if (r1 >= r0) goto L_0x004f
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r11)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 1: goto L_0x0049;
                case 2: goto L_0x003f;
                case 3: goto L_0x0039;
                case 4: goto L_0x0033;
                case 5: goto L_0x002d;
                case 1000: goto L_0x0027;
                default: goto L_0x0023;
            }
        L_0x0023:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r11, r1)
            goto L_0x0012
        L_0x0027:
            int r4 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r11, r1)
            goto L_0x0012
        L_0x002d:
            byte[] r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createByteArray(r11, r1)
            goto L_0x0012
        L_0x0033:
            android.os.Bundle r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createBundle(r11, r1)
            goto L_0x0012
        L_0x0039:
            int r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r11, r1)
            goto L_0x0012
        L_0x003f:
            android.os.Parcelable$Creator r2 = android.app.PendingIntent.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r11, r1, r2)
            r6 = r1
            android.app.PendingIntent r6 = (android.app.PendingIntent) r6
            goto L_0x0012
        L_0x0049:
            int r5 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r11, r1)
            goto L_0x0012
        L_0x004f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r11, r0)
            com.google.android.gms.auth.api.proxy.ProxyResponse r11 = new com.google.android.gms.auth.api.proxy.ProxyResponse
            r3 = r11
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.proxy.zzb.createFromParcel(android.os.Parcel):java.lang.Object");
    }
}
