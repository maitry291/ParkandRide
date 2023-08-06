package com.google.android.gms.maps.model;

import com.google.android.gms.maps.model.StampStyle;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public class SpriteStyle extends StampStyle {

    /* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
    public static final class Builder extends StampStyle.Builder<Builder> {
        private Builder() {
        }

        /* synthetic */ Builder(zzn zzn) {
        }

        public SpriteStyle build() {
            return new SpriteStyle(this.zza);
        }

        /* access modifiers changed from: protected */
        public Builder self() {
            return this;
        }
    }

    public SpriteStyle(BitmapDescriptor stamp) {
        super(stamp);
    }

    public static Builder newBuilder(BitmapDescriptor stamp) {
        return (Builder) new Builder((zzn) null).stamp(stamp);
    }
}
