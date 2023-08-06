package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zzab();
    private static final Integer zza = Integer.valueOf(Color.argb(255, 236, 233, 225));
    private Boolean zzb;
    private Boolean zzc;
    private int zzd = -1;
    private CameraPosition zze;
    private Boolean zzf;
    private Boolean zzg;
    private Boolean zzh;
    private Boolean zzi;
    private Boolean zzj;
    private Boolean zzk;
    private Boolean zzl;
    private Boolean zzm;
    private Boolean zzn;
    private Float zzo = null;
    private Float zzp = null;
    private LatLngBounds zzq = null;
    private Boolean zzr;
    private Integer zzs = null;
    private String zzt = null;

    public GoogleMapOptions() {
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attrs) {
        String string;
        if (context == null || attrs == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attrs, R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_mapType)) {
            googleMapOptions.mapType(obtainAttributes.getInt(R.styleable.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_zOrderOnTop)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(R.styleable.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_useViewLifecycle)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(R.styleable.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiCompass)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiRotateGestures)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom)) {
            googleMapOptions.scrollGesturesEnabledDuringRotateOrZoom(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiScrollGestures)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiTiltGestures)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiZoomGestures)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiZoomControls)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiZoomControls, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_liteMode)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(R.styleable.MapAttrs_liteMode, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiMapToolbar)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiMapToolbar, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_ambientEnabled)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_ambientEnabled, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraMinZoomPreference)) {
            googleMapOptions.minZoomPreference(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraMinZoomPreference, Float.NEGATIVE_INFINITY));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraMinZoomPreference)) {
            googleMapOptions.maxZoomPreference(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_backgroundColor)) {
            googleMapOptions.backgroundColor(Integer.valueOf(obtainAttributes.getColor(R.styleable.MapAttrs_backgroundColor, zza.intValue())));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_mapId) && (string = obtainAttributes.getString(R.styleable.MapAttrs_mapId)) != null && !string.isEmpty()) {
            googleMapOptions.mapId(string);
        }
        googleMapOptions.latLngBoundsForCameraTarget(zzb(context, attrs));
        googleMapOptions.camera(zza(context, attrs));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    public static CameraPosition zza(Context context, AttributeSet attributeSet) {
        float f;
        float f2;
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraTargetLat)) {
            f = obtainAttributes.getFloat(R.styleable.MapAttrs_cameraTargetLat, 0.0f);
        } else {
            f = 0.0f;
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraTargetLng)) {
            f2 = obtainAttributes.getFloat(R.styleable.MapAttrs_cameraTargetLng, 0.0f);
        } else {
            f2 = 0.0f;
        }
        LatLng latLng = new LatLng((double) f, (double) f2);
        CameraPosition.Builder builder = CameraPosition.builder();
        builder.target(latLng);
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraZoom)) {
            builder.zoom(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraZoom, 0.0f));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraBearing)) {
            builder.bearing(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraBearing, 0.0f));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_cameraTilt)) {
            builder.tilt(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraTilt, 0.0f));
        }
        obtainAttributes.recycle();
        return builder.build();
    }

    public static LatLngBounds zzb(Context context, AttributeSet attributeSet) {
        Float f;
        Float f2;
        Float f3;
        Float f4;
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsSouthWestLatitude)) {
            f = Float.valueOf(obtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsSouthWestLatitude, 0.0f));
        } else {
            f = null;
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsSouthWestLongitude)) {
            f2 = Float.valueOf(obtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsSouthWestLongitude, 0.0f));
        } else {
            f2 = null;
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsNorthEastLatitude)) {
            f3 = Float.valueOf(obtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsNorthEastLatitude, 0.0f));
        } else {
            f3 = null;
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_latLngBoundsNorthEastLongitude)) {
            f4 = Float.valueOf(obtainAttributes.getFloat(R.styleable.MapAttrs_latLngBoundsNorthEastLongitude, 0.0f));
        } else {
            f4 = null;
        }
        obtainAttributes.recycle();
        if (f == null || f2 == null || f3 == null || f4 == null) {
            return null;
        }
        return new LatLngBounds(new LatLng((double) f.floatValue(), (double) f2.floatValue()), new LatLng((double) f3.floatValue(), (double) f4.floatValue()));
    }

    public GoogleMapOptions ambientEnabled(boolean enabled) {
        this.zzn = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions backgroundColor(Integer num) {
        this.zzs = num;
        return this;
    }

    public GoogleMapOptions camera(CameraPosition cameraPosition) {
        this.zze = cameraPosition;
        return this;
    }

    public GoogleMapOptions compassEnabled(boolean enabled) {
        this.zzg = Boolean.valueOf(enabled);
        return this;
    }

    public Boolean getAmbientEnabled() {
        return this.zzn;
    }

    public Integer getBackgroundColor() {
        return this.zzs;
    }

    public CameraPosition getCamera() {
        return this.zze;
    }

    public Boolean getCompassEnabled() {
        return this.zzg;
    }

    public LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.zzq;
    }

    public Boolean getLiteMode() {
        return this.zzl;
    }

    public String getMapId() {
        return this.zzt;
    }

    public Boolean getMapToolbarEnabled() {
        return this.zzm;
    }

    public int getMapType() {
        return this.zzd;
    }

    public Float getMaxZoomPreference() {
        return this.zzp;
    }

    public Float getMinZoomPreference() {
        return this.zzo;
    }

    public Boolean getRotateGesturesEnabled() {
        return this.zzk;
    }

    public Boolean getScrollGesturesEnabled() {
        return this.zzh;
    }

    public Boolean getScrollGesturesEnabledDuringRotateOrZoom() {
        return this.zzr;
    }

    public Boolean getTiltGesturesEnabled() {
        return this.zzj;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.zzc;
    }

    public Boolean getZOrderOnTop() {
        return this.zzb;
    }

    public Boolean getZoomControlsEnabled() {
        return this.zzf;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.zzi;
    }

    public GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds latLngBounds) {
        this.zzq = latLngBounds;
        return this;
    }

    public GoogleMapOptions liteMode(boolean enabled) {
        this.zzl = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions mapId(String str) {
        this.zzt = str;
        return this;
    }

    public GoogleMapOptions mapToolbarEnabled(boolean enabled) {
        this.zzm = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions mapType(int i) {
        this.zzd = i;
        return this;
    }

    public GoogleMapOptions maxZoomPreference(float maxZoomPreference) {
        this.zzp = Float.valueOf(maxZoomPreference);
        return this;
    }

    public GoogleMapOptions minZoomPreference(float minZoomPreference) {
        this.zzo = Float.valueOf(minZoomPreference);
        return this;
    }

    public GoogleMapOptions rotateGesturesEnabled(boolean enabled) {
        this.zzk = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions scrollGesturesEnabled(boolean enabled) {
        this.zzh = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions scrollGesturesEnabledDuringRotateOrZoom(boolean enabled) {
        this.zzr = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions tiltGesturesEnabled(boolean enabled) {
        this.zzj = Boolean.valueOf(enabled);
        return this;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("MapType", Integer.valueOf(this.zzd)).add("LiteMode", this.zzl).add("Camera", this.zze).add("CompassEnabled", this.zzg).add("ZoomControlsEnabled", this.zzf).add("ScrollGesturesEnabled", this.zzh).add("ZoomGesturesEnabled", this.zzi).add("TiltGesturesEnabled", this.zzj).add("RotateGesturesEnabled", this.zzk).add("ScrollGesturesEnabledDuringRotateOrZoom", this.zzr).add("MapToolbarEnabled", this.zzm).add("AmbientEnabled", this.zzn).add("MinZoomPreference", this.zzo).add("MaxZoomPreference", this.zzp).add("BackgroundColor", this.zzs).add("LatLngBoundsForCameraTarget", this.zzq).add("ZOrderOnTop", this.zzb).add("UseViewLifecycleInFragment", this.zzc).toString();
    }

    public GoogleMapOptions useViewLifecycleInFragment(boolean useViewLifecycleInFragment) {
        this.zzc = Boolean.valueOf(useViewLifecycleInFragment);
        return this;
    }

    public void writeToParcel(Parcel out, int flags) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(out);
        SafeParcelWriter.writeByte(out, 2, zza.zza(this.zzb));
        SafeParcelWriter.writeByte(out, 3, zza.zza(this.zzc));
        SafeParcelWriter.writeInt(out, 4, getMapType());
        SafeParcelWriter.writeParcelable(out, 5, getCamera(), flags, false);
        SafeParcelWriter.writeByte(out, 6, zza.zza(this.zzf));
        SafeParcelWriter.writeByte(out, 7, zza.zza(this.zzg));
        SafeParcelWriter.writeByte(out, 8, zza.zza(this.zzh));
        SafeParcelWriter.writeByte(out, 9, zza.zza(this.zzi));
        SafeParcelWriter.writeByte(out, 10, zza.zza(this.zzj));
        SafeParcelWriter.writeByte(out, 11, zza.zza(this.zzk));
        SafeParcelWriter.writeByte(out, 12, zza.zza(this.zzl));
        SafeParcelWriter.writeByte(out, 14, zza.zza(this.zzm));
        SafeParcelWriter.writeByte(out, 15, zza.zza(this.zzn));
        SafeParcelWriter.writeFloatObject(out, 16, getMinZoomPreference(), false);
        SafeParcelWriter.writeFloatObject(out, 17, getMaxZoomPreference(), false);
        SafeParcelWriter.writeParcelable(out, 18, getLatLngBoundsForCameraTarget(), flags, false);
        SafeParcelWriter.writeByte(out, 19, zza.zza(this.zzr));
        SafeParcelWriter.writeIntegerObject(out, 20, getBackgroundColor(), false);
        SafeParcelWriter.writeString(out, 21, getMapId(), false);
        SafeParcelWriter.finishObjectHeader(out, beginObjectHeader);
    }

    public GoogleMapOptions zOrderOnTop(boolean zOrderOnTop) {
        this.zzb = Boolean.valueOf(zOrderOnTop);
        return this;
    }

    public GoogleMapOptions zoomControlsEnabled(boolean enabled) {
        this.zzf = Boolean.valueOf(enabled);
        return this;
    }

    public GoogleMapOptions zoomGesturesEnabled(boolean enabled) {
        this.zzi = Boolean.valueOf(enabled);
        return this;
    }

    GoogleMapOptions(byte b, byte b2, int i, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10, byte b11, Float f, Float f2, LatLngBounds latLngBounds, byte b12, Integer num, String str) {
        this.zzb = zza.zzb(b);
        this.zzc = zza.zzb(b2);
        this.zzd = i;
        this.zze = cameraPosition;
        this.zzf = zza.zzb(b3);
        this.zzg = zza.zzb(b4);
        this.zzh = zza.zzb(b5);
        this.zzi = zza.zzb(b6);
        this.zzj = zza.zzb(b7);
        this.zzk = zza.zzb(b8);
        this.zzl = zza.zzb(b9);
        this.zzm = zza.zzb(b10);
        this.zzn = zza.zzb(b11);
        this.zzo = f;
        this.zzp = f2;
        this.zzq = latLngBounds;
        this.zzr = zza.zzb(b12);
        this.zzs = num;
        this.zzt = str;
    }
}
