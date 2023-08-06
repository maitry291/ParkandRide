package com.example.parkandride;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.example.parkandride.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.AuthKt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\tH\u0016J+\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0016¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\fH\u0002J\b\u0010\u001c\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/example/parkandride/MapsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "binding", "Lcom/example/parkandride/databinding/ActivityMapsBinding;", "fusedLocClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "mMap", "Lcom/google/android/gms/maps/GoogleMap;", "map", "getCurrentLocation", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "googleMap", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "requestLocPermissions", "setupLocClient", "Companion", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: MapsActivity.kt */
public final class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int REQUEST_LOCATION = 1;
    private static final String TAG = "MapsActivity";
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocClient;
    private GoogleMap mMap;
    private GoogleMap map;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map2 = this._$_findViewCache;
        View view = map2.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        if (findViewById == null) {
            return null;
        }
        map2.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        ActivityMapsBinding inflate = ActivityMapsBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        setContentView((View) inflate.getRoot());
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.map);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.google.android.gms.maps.SupportMapFragment");
        ((SupportMapFragment) findFragmentById).getMapAsync(this);
        ((AppCompatButton) _$_findCachedViewById(R.id.confirm)).setVisibility(0);
        setupLocClient();
        ((AppCompatButton) _$_findCachedViewById(R.id.confirm)).setOnClickListener(new MapsActivity$$ExternalSyntheticLambda1(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m47onCreate$lambda0(MapsActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, MainActivity.class));
    }

    public void onMapReady(GoogleMap googleMap) {
        Intrinsics.checkNotNullParameter(googleMap, "googleMap");
        try {
            if (!googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle))) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
        this.map = googleMap;
        getCurrentLocation();
    }

    private final void setupLocClient() {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(this)");
        this.fusedLocClient = fusedLocationProviderClient;
    }

    private final void requestLocPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/example/parkandride/MapsActivity$Companion;", "", "()V", "REQUEST_LOCATION", "", "TAG", "", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: MapsActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            requestLocPermissions();
            return;
        }
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocClient;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocClient");
            fusedLocationProviderClient = null;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new MapsActivity$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: getCurrentLocation$lambda-2  reason: not valid java name */
    public static final void m46getCurrentLocation$lambda2(MapsActivity this$0, Task it) {
        DatabaseReference ref;
        String it1;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        Location location = (Location) it.getResult();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Intrinsics.checkNotNullExpressionValue(database, "getInstance()");
        FirebaseUser currentUser = AuthKt.getAuth(Firebase.INSTANCE).getCurrentUser();
        GoogleMap googleMap = null;
        if (currentUser == null || (it1 = currentUser.getUid()) == null) {
            ref = null;
        } else {
            ref = database.getReference("UserLocations").child(it1);
        }
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            GoogleMap googleMap2 = this$0.map;
            if (googleMap2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("map");
                googleMap2 = null;
            }
            googleMap2.addMarker(new MarkerOptions().position(latLng).title("You are currently here!"));
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, 16.0f);
            Intrinsics.checkNotNullExpressionValue(update, "newLatLngZoom(latLng, 16.0f)");
            GoogleMap googleMap3 = this$0.map;
            if (googleMap3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("map");
            } else {
                googleMap = googleMap3;
            }
            googleMap.moveCamera(update);
            if (ref != null) {
                ref.setValue(location);
                return;
            }
            return;
        }
        Log.e(TAG, "No location found");
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 1) {
            return;
        }
        if (grantResults.length == 1 && grantResults[0] == 0) {
            getCurrentLocation();
        } else {
            Log.e(TAG, "Location permission has been denied");
        }
    }
}
