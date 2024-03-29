package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class zzab implements Parcelable.Creator {
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        CameraPosition cameraPosition = null;
        Float f = null;
        Float f2 = null;
        LatLngBounds latLngBounds = null;
        Integer num = null;
        String str = null;
        byte b = -1;
        byte b2 = -1;
        int i = 0;
        byte b3 = -1;
        byte b4 = -1;
        byte b5 = -1;
        byte b6 = -1;
        byte b7 = -1;
        byte b8 = -1;
        byte b9 = -1;
        byte b10 = -1;
        byte b11 = -1;
        byte b12 = -1;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    b = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 3:
                    b2 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel2, readHeader);
                    break;
                case 5:
                    cameraPosition = (CameraPosition) SafeParcelReader.createParcelable(parcel2, readHeader, CameraPosition.CREATOR);
                    break;
                case 6:
                    b3 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 7:
                    b4 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 8:
                    b5 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 9:
                    b6 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 10:
                    b7 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 11:
                    b8 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 12:
                    b9 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 14:
                    b10 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 15:
                    b11 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 16:
                    f = SafeParcelReader.readFloatObject(parcel2, readHeader);
                    break;
                case 17:
                    f2 = SafeParcelReader.readFloatObject(parcel2, readHeader);
                    break;
                case 18:
                    latLngBounds = (LatLngBounds) SafeParcelReader.createParcelable(parcel2, readHeader, LatLngBounds.CREATOR);
                    break;
                case 19:
                    b12 = SafeParcelReader.readByte(parcel2, readHeader);
                    break;
                case 20:
                    num = SafeParcelReader.readIntegerObject(parcel2, readHeader);
                    break;
                case 21:
                    str = SafeParcelReader.createString(parcel2, readHeader);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel2, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel2, validateObjectHeader);
        return new GoogleMapOptions(b, b2, i, cameraPosition, b3, b4, b5, b6, b7, b8, b9, b10, b11, f, f2, latLngBounds, b12, num, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleMapOptions[i];
    }
}
