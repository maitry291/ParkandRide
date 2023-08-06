package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzal;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
final class zzab extends zzal {
    final /* synthetic */ TileProvider zza;

    zzab(TileOverlayOptions tileOverlayOptions, TileProvider tileProvider) {
        this.zza = tileProvider;
    }

    public final Tile zzb(int i, int i2, int i3) {
        return this.zza.getTile(i, i2, i3);
    }
}
