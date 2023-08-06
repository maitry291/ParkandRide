package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-location@@21.0.0 */
public final class zzaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaj> CREATOR = new zzak();
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;

    public zzaj(int i, int i2, int i3, int i4) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        if (i < 0 || i > 23) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkState(z, "Start hour must be in range [0, 23].");
        if (i2 < 0 || i2 > 59) {
            z2 = false;
        } else {
            z2 = true;
        }
        Preconditions.checkState(z2, "Start minute must be in range [0, 59].");
        if (i3 < 0 || i3 > 23) {
            z3 = false;
        } else {
            z3 = true;
        }
        Preconditions.checkState(z3, "End hour must be in range [0, 23].");
        if (i4 < 0 || i4 > 59) {
            z4 = false;
        } else {
            z4 = true;
        }
        Preconditions.checkState(z4, "End minute must be in range [0, 59].");
        Preconditions.checkState(((i + i2) + i3) + i4 <= 0 ? false : z5, "Parameters can't be all 0.");
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = i4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaj)) {
            return false;
        }
        zzaj zzaj = (zzaj) obj;
        return this.zza == zzaj.zza && this.zzb == zzaj.zzb && this.zzc == zzaj.zzc && this.zzd == zzaj.zzd;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zza), Integer.valueOf(this.zzb), Integer.valueOf(this.zzc), Integer.valueOf(this.zzd));
    }

    public final String toString() {
        int i = this.zza;
        int i2 = this.zzb;
        int i3 = this.zzc;
        int i4 = this.zzd;
        return "UserPreferredSleepWindow [startHour=" + i + ", startMinute=" + i2 + ", endHour=" + i3 + ", endMinute=" + i4 + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Preconditions.checkNotNull(parcel);
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeInt(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
