package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@21.0.0 */
public final class LocationResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationResult> CREATOR = new zzy();
    static final List zza = Collections.emptyList();
    private final List zzb;

    LocationResult(List list) {
        this.zzb = list;
    }

    public static LocationResult create(List<Location> locations) {
        if (locations == null) {
            locations = zza;
        }
        return new LocationResult(locations);
    }

    public static LocationResult extractResult(Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        LocationResult locationResult = (LocationResult) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.location.EXTRA_LOCATION_RESULT_BYTES", CREATOR);
        return locationResult == null ? (LocationResult) intent.getParcelableExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT") : locationResult;
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        return intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT") || intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT_BYTES");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r10) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof com.google.android.gms.location.LocationResult
            r1 = 0
            if (r0 == 0) goto L_0x008d
            com.google.android.gms.location.LocationResult r10 = (com.google.android.gms.location.LocationResult) r10
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L_0x0017
            java.util.List r0 = r9.zzb
            java.util.List r10 = r10.zzb
            boolean r10 = r0.equals(r10)
            return r10
        L_0x0017:
            java.util.List r0 = r9.zzb
            int r0 = r0.size()
            java.util.List r2 = r10.zzb
            int r2 = r2.size()
            if (r0 == r2) goto L_0x0026
            return r1
        L_0x0026:
            java.util.List r0 = r9.zzb
            java.util.Iterator r0 = r0.iterator()
            java.util.List r10 = r10.zzb
            java.util.Iterator r10 = r10.iterator()
        L_0x0032:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x008b
            java.lang.Object r2 = r0.next()
            android.location.Location r2 = (android.location.Location) r2
            java.lang.Object r3 = r10.next()
            android.location.Location r3 = (android.location.Location) r3
            double r4 = r2.getLatitude()
            double r6 = r3.getLatitude()
            int r4 = java.lang.Double.compare(r4, r6)
            if (r4 == 0) goto L_0x0053
            return r1
        L_0x0053:
            double r4 = r2.getLongitude()
            double r6 = r3.getLongitude()
            int r4 = java.lang.Double.compare(r4, r6)
            if (r4 == 0) goto L_0x0062
            return r1
        L_0x0062:
            long r4 = r2.getTime()
            long r6 = r3.getTime()
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x006f
            return r1
        L_0x006f:
            long r4 = r2.getElapsedRealtimeNanos()
            long r6 = r3.getElapsedRealtimeNanos()
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x007c
            return r1
        L_0x007c:
            java.lang.String r2 = r2.getProvider()
            java.lang.String r3 = r3.getProvider()
            boolean r2 = com.google.android.gms.common.internal.Objects.equal(r2, r3)
            if (r2 != 0) goto L_0x0032
            return r1
        L_0x008b:
            r10 = 1
            return r10
        L_0x008d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.LocationResult.equals(java.lang.Object):boolean");
    }

    public Location getLastLocation() {
        int size = this.zzb.size();
        if (size == 0) {
            return null;
        }
        return (Location) this.zzb.get(size - 1);
    }

    public List<Location> getLocations() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzb);
    }

    public String toString() {
        return "LocationResult".concat(String.valueOf(String.valueOf(this.zzb)));
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getLocations(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
