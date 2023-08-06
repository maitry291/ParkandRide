package com.example.parkandride;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.ktx.AuthKt;
import com.google.firebase.ktx.Firebase;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/example/parkandride/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "isAllFabsVisible", "", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: MainActivity.kt */
public final class MainActivity extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private boolean isAllFabsVisible;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        if (findViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        Intent i = new Intent(this, Booking.class);
        ((LinearLayout) _$_findCachedViewById(R.id.opt1)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda0(i, this));
        ((LinearLayout) _$_findCachedViewById(R.id.opt2)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda1(i, this));
        ((LinearLayout) _$_findCachedViewById(R.id.opt3)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda2(i, this));
        ((LinearLayout) _$_findCachedViewById(R.id.opt4)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda3(i, this));
        this.isAllFabsVisible = false;
        ((FloatingActionButton) _$_findCachedViewById(R.id.add_fab)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda4(this));
        ((TextView) _$_findCachedViewById(R.id.location)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda5(this));
        ((FloatingActionButton) _$_findCachedViewById(R.id.logout_fab)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda6(this));
        ((FloatingActionButton) _$_findCachedViewById(R.id.profile_fab)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda7(this));
        ((TextView) _$_findCachedViewById(R.id.h1)).setOnClickListener(new MainActivity$$ExternalSyntheticLambda8(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m36onCreate$lambda0(Intent $i, MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter($i, "$i");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        $i.putExtra("price", 20);
        this$0.startActivity($i);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1  reason: not valid java name */
    public static final void m37onCreate$lambda1(Intent $i, MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter($i, "$i");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        $i.putExtra("price", 50);
        this$0.startActivity($i);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final void m38onCreate$lambda2(Intent $i, MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter($i, "$i");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        $i.putExtra("price", 70);
        this$0.startActivity($i);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-3  reason: not valid java name */
    public static final void m39onCreate$lambda3(Intent $i, MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter($i, "$i");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        $i.putExtra("price", 100);
        this$0.startActivity($i);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-4  reason: not valid java name */
    public static final void m40onCreate$lambda4(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!this$0.isAllFabsVisible) {
            ((FloatingActionButton) this$0._$_findCachedViewById(R.id.profile_fab)).show();
            ((FloatingActionButton) this$0._$_findCachedViewById(R.id.logout_fab)).show();
            this$0.isAllFabsVisible = true;
            return;
        }
        ((FloatingActionButton) this$0._$_findCachedViewById(R.id.profile_fab)).hide();
        ((FloatingActionButton) this$0._$_findCachedViewById(R.id.logout_fab)).hide();
        this$0.isAllFabsVisible = false;
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-5  reason: not valid java name */
    public static final void m41onCreate$lambda5(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, MapsActivity.class));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-6  reason: not valid java name */
    public static final void m42onCreate$lambda6(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Toast.makeText(this$0, "Logging Out..", 0).show();
        AuthKt.getAuth(Firebase.INSTANCE).signOut();
        this$0.startActivity(new Intent(this$0, Firstpage.class));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-7  reason: not valid java name */
    public static final void m43onCreate$lambda7(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, Profie.class));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-8  reason: not valid java name */
    public static final void m44onCreate$lambda8(MainActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, ParkingSlots1.class));
    }

    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
