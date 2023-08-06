package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public class StampStyle extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StampStyle> CREATOR = new zzo();
    protected final BitmapDescriptor zza;

    /* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
    static abstract class Builder<T extends Builder<T>> {
        BitmapDescriptor zza;

        Builder() {
        }

        /* access modifiers changed from: protected */
        public abstract T self();

        public T stamp(BitmapDescriptor stamp) {
            this.zza = stamp;
            return self();
        }
    }

    StampStyle(IBinder iBinder) {
        this.zza = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
    }

    public BitmapDescriptor getStamp() {
        return this.zza;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zza.zza().asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    protected StampStyle(BitmapDescriptor stamp) {
        this.zza = stamp;
    }
}
