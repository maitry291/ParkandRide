package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzaa;
import com.google.android.gms.internal.maps.zzad;
import com.google.android.gms.internal.maps.zzag;
import com.google.android.gms.internal.maps.zzaj;
import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.internal.maps.zzo;
import com.google.android.gms.internal.maps.zzr;
import com.google.android.gms.internal.maps.zzx;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
public interface IGoogleMapDelegate extends IInterface {
    zzl addCircle(CircleOptions circleOptions) throws RemoteException;

    zzo addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException;

    zzaa addMarker(MarkerOptions markerOptions) throws RemoteException;

    void addOnMapCapabilitiesChangedListener(zzak zzak) throws RemoteException;

    zzad addPolygon(PolygonOptions polygonOptions) throws RemoteException;

    zzag addPolyline(PolylineOptions polylineOptions) throws RemoteException;

    zzaj addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException;

    void animateCamera(IObjectWrapper iObjectWrapper) throws RemoteException;

    void animateCameraWithCallback(IObjectWrapper iObjectWrapper, @Nullable zzd zzd) throws RemoteException;

    void animateCameraWithDurationAndCallback(IObjectWrapper iObjectWrapper, int i, @Nullable zzd zzd) throws RemoteException;

    void clear() throws RemoteException;

    CameraPosition getCameraPosition() throws RemoteException;

    zzr getFocusedBuilding() throws RemoteException;

    void getMapAsync(zzas zzas) throws RemoteException;

    zzx getMapCapabilities() throws RemoteException;

    int getMapType() throws RemoteException;

    float getMaxZoomLevel() throws RemoteException;

    float getMinZoomLevel() throws RemoteException;

    Location getMyLocation() throws RemoteException;

    IProjectionDelegate getProjection() throws RemoteException;

    IUiSettingsDelegate getUiSettings() throws RemoteException;

    boolean isBuildingsEnabled() throws RemoteException;

    boolean isIndoorEnabled() throws RemoteException;

    boolean isMyLocationEnabled() throws RemoteException;

    boolean isTrafficEnabled() throws RemoteException;

    void moveCamera(IObjectWrapper iObjectWrapper) throws RemoteException;

    void onCreate(Bundle bundle) throws RemoteException;

    void onDestroy() throws RemoteException;

    void onEnterAmbient(Bundle bundle) throws RemoteException;

    void onExitAmbient() throws RemoteException;

    void onLowMemory() throws RemoteException;

    void onPause() throws RemoteException;

    void onResume() throws RemoteException;

    void onSaveInstanceState(Bundle bundle) throws RemoteException;

    void onStart() throws RemoteException;

    void onStop() throws RemoteException;

    void removeOnMapCapabilitiesChangedListener(zzak zzak) throws RemoteException;

    void resetMinMaxZoomPreference() throws RemoteException;

    void setBuildingsEnabled(boolean z) throws RemoteException;

    void setContentDescription(@Nullable String str) throws RemoteException;

    boolean setIndoorEnabled(boolean z) throws RemoteException;

    void setInfoWindowAdapter(@Nullable zzi zzi) throws RemoteException;

    void setLatLngBoundsForCameraTarget(@Nullable LatLngBounds latLngBounds) throws RemoteException;

    void setLocationSource(@Nullable ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException;

    boolean setMapStyle(@Nullable MapStyleOptions mapStyleOptions) throws RemoteException;

    void setMapType(int i) throws RemoteException;

    void setMaxZoomPreference(float f) throws RemoteException;

    void setMinZoomPreference(float f) throws RemoteException;

    void setMyLocationEnabled(boolean z) throws RemoteException;

    void setOnCameraChangeListener(@Nullable zzn zzn) throws RemoteException;

    void setOnCameraIdleListener(@Nullable zzp zzp) throws RemoteException;

    void setOnCameraMoveCanceledListener(@Nullable zzr zzr) throws RemoteException;

    void setOnCameraMoveListener(@Nullable zzt zzt) throws RemoteException;

    void setOnCameraMoveStartedListener(@Nullable zzv zzv) throws RemoteException;

    void setOnCircleClickListener(@Nullable zzx zzx) throws RemoteException;

    void setOnGroundOverlayClickListener(@Nullable zzz zzz) throws RemoteException;

    void setOnIndoorStateChangeListener(@Nullable zzab zzab) throws RemoteException;

    void setOnInfoWindowClickListener(@Nullable zzad zzad) throws RemoteException;

    void setOnInfoWindowCloseListener(@Nullable zzaf zzaf) throws RemoteException;

    void setOnInfoWindowLongClickListener(@Nullable zzah zzah) throws RemoteException;

    void setOnMapClickListener(@Nullable zzam zzam) throws RemoteException;

    void setOnMapLoadedCallback(@Nullable zzao zzao) throws RemoteException;

    void setOnMapLongClickListener(@Nullable zzaq zzaq) throws RemoteException;

    void setOnMarkerClickListener(@Nullable zzau zzau) throws RemoteException;

    void setOnMarkerDragListener(@Nullable zzaw zzaw) throws RemoteException;

    void setOnMyLocationButtonClickListener(@Nullable zzay zzay) throws RemoteException;

    void setOnMyLocationChangeListener(@Nullable zzba zzba) throws RemoteException;

    void setOnMyLocationClickListener(@Nullable zzbc zzbc) throws RemoteException;

    void setOnPoiClickListener(@Nullable zzbe zzbe) throws RemoteException;

    void setOnPolygonClickListener(@Nullable zzbg zzbg) throws RemoteException;

    void setOnPolylineClickListener(@Nullable zzbi zzbi) throws RemoteException;

    void setPadding(int i, int i2, int i3, int i4) throws RemoteException;

    void setTrafficEnabled(boolean z) throws RemoteException;

    void setWatermarkEnabled(boolean z) throws RemoteException;

    void snapshot(zzbv zzbv, @Nullable IObjectWrapper iObjectWrapper) throws RemoteException;

    void snapshotForTest(zzbv zzbv) throws RemoteException;

    void stopAnimation() throws RemoteException;

    boolean useViewLifecycleWhenInFragment() throws RemoteException;
}
