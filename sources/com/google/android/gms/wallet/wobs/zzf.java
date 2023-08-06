package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.util.ArrayUtils;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public final class zzf implements Parcelable.Creator<LabelValueRow> {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList<LabelValue> newArrayList = ArrayUtils.newArrayList();
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    newArrayList = SafeParcelReader.createTypedList(parcel, readHeader, LabelValue.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new LabelValueRow(str, str2, newArrayList);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new LabelValueRow[i];
    }
}
