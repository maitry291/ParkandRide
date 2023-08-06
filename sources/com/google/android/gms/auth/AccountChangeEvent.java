package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class AccountChangeEvent extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AccountChangeEvent> CREATOR = new zza();
    private final int zze;
    private final long zzf;
    private final String zzg;
    private final int zzh;
    private final int zzi;
    private final String zzj;

    AccountChangeEvent(int i, long j, String str, int i2, int i3, String str2) {
        this.zze = i;
        this.zzf = j;
        this.zzg = (String) Preconditions.checkNotNull(str);
        this.zzh = i2;
        this.zzi = i3;
        this.zzj = str2;
    }

    public AccountChangeEvent(long j, String str, int i, int i2, String str2) {
        this.zze = 1;
        this.zzf = j;
        this.zzg = (String) Preconditions.checkNotNull(str);
        this.zzh = i;
        this.zzi = i2;
        this.zzj = str2;
    }

    public String getAccountName() {
        return this.zzg;
    }

    public int getChangeType() {
        return this.zzh;
    }

    public int getEventIndex() {
        return this.zzi;
    }

    public String getChangeData() {
        return this.zzj;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zze);
        SafeParcelWriter.writeLong(parcel, 2, this.zzf);
        SafeParcelWriter.writeString(parcel, 3, this.zzg, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzh);
        SafeParcelWriter.writeInt(parcel, 5, this.zzi);
        SafeParcelWriter.writeString(parcel, 6, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public String toString() {
        String str;
        switch (this.zzh) {
            case 1:
                str = "ADDED";
                break;
            case 2:
                str = "REMOVED";
                break;
            case 3:
                str = "RENAMED_FROM";
                break;
            case 4:
                str = "RENAMED_TO";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        String str2 = this.zzg;
        String str3 = this.zzj;
        return new StringBuilder(String.valueOf(str2).length() + 91 + String.valueOf(str).length() + String.valueOf(str3).length()).append("AccountChangeEvent {accountName = ").append(str2).append(", changeType = ").append(str).append(", changeData = ").append(str3).append(", eventIndex = ").append(this.zzi).append("}").toString();
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zze), Long.valueOf(this.zzf), this.zzg, Integer.valueOf(this.zzh), Integer.valueOf(this.zzi), this.zzj);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccountChangeEvent)) {
            return false;
        }
        AccountChangeEvent accountChangeEvent = (AccountChangeEvent) obj;
        if (this.zze == accountChangeEvent.zze && this.zzf == accountChangeEvent.zzf && Objects.equal(this.zzg, accountChangeEvent.zzg) && this.zzh == accountChangeEvent.zzh && this.zzi == accountChangeEvent.zzi && Objects.equal(this.zzj, accountChangeEvent.zzj)) {
            return true;
        }
        return false;
    }
}
