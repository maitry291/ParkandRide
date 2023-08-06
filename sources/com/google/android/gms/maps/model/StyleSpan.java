package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class StyleSpan extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StyleSpan> CREATOR = new zzw();
    private final StrokeStyle zza;
    private final double zzb;

    public StyleSpan(int color) {
        this.zza = StrokeStyle.colorBuilder(color).build();
        this.zzb = 1.0d;
    }

    public double getSegments() {
        return this.zzb;
    }

    public StrokeStyle getStyle() {
        return this.zza;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStyle(), i, false);
        SafeParcelWriter.writeDouble(parcel, 3, getSegments());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public StyleSpan(int color, double segments) {
        if (segments > 0.0d) {
            this.zza = StrokeStyle.colorBuilder(color).build();
            this.zzb = segments;
            return;
        }
        throw new IllegalArgumentException("A style must be applied to some segments on a polyline.");
    }

    public StyleSpan(StrokeStyle style) {
        this.zza = style;
        this.zzb = 1.0d;
    }

    public StyleSpan(StrokeStyle style, double segments) {
        if (segments > 0.0d) {
            this.zza = style;
            this.zzb = segments;
            return;
        }
        throw new IllegalArgumentException("A style must be applied to some segments on a polyline.");
    }
}
