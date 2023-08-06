package com.example.parkandride;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.common.util.GmsVersion;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014J\b\u0010\u0007\u001a\u00020\u0004H\u0002¨\u0006\b"}, d2 = {"Lcom/example/parkandride/ConfirmBooking;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "show", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: ConfirmBooking.kt */
public final class ConfirmBooking extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

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
        setContentView((int) R.layout.activity_confirm_booking);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        ((LottieAnimationView) _$_findCachedViewById(R.id.animation_view)).playAnimation();
        ((AppCompatButton) _$_findCachedViewById(R.id.done)).setOnClickListener(new ConfirmBooking$$ExternalSyntheticLambda0(this));
        if (StringsKt.equals$default(getIntent().getStringExtra("flag"), "0", false, 2, (Object) null)) {
            new Handler().postDelayed(new ConfirmBooking$$ExternalSyntheticLambda1(this), 2000);
        } else {
            show();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m15onCreate$lambda0(ConfirmBooking this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, Profie.class));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1  reason: not valid java name */
    public static final void m16onCreate$lambda1(ConfirmBooking this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.show();
    }

    private final void show() {
        ((TextView) _$_findCachedViewById(R.id.transId)).append(String.valueOf(Random.Default.nextInt(12345, GmsVersion.VERSION_ORLA)));
        ((TextView) _$_findCachedViewById(R.id.custName)).setText(getIntent().getStringExtra("name"));
        ((TextView) _$_findCachedViewById(R.id.cusPhone)).setText(getIntent().getStringExtra("phone"));
        ((TextView) _$_findCachedViewById(R.id.custVehicle)).setText(getIntent().getStringExtra("vehicle"));
        ((TextView) _$_findCachedViewById(R.id.parkingDate)).setText(getIntent().getStringExtra("date"));
        ((TextView) _$_findCachedViewById(R.id.custIn)).setText(getIntent().getStringExtra("intime"));
        ((TextView) _$_findCachedViewById(R.id.custOut)).append(getIntent().getStringExtra("outtime"));
        ((TextView) _$_findCachedViewById(R.id.custDuration)).append(getIntent().getStringExtra(TypedValues.TransitionType.S_DURATION) + " hour");
        ((TextView) _$_findCachedViewById(R.id.timedate)).setText(getIntent().getStringExtra("time"));
        ((TextView) _$_findCachedViewById(R.id.price)).setText(new StringBuilder().append(8377).append(getIntent().getIntExtra("price", 10)).toString());
        ((LottieAnimationView) _$_findCachedViewById(R.id.animation_view)).setVisibility(8);
        ((LinearLayout) _$_findCachedViewById(R.id.booking)).setVisibility(0);
        ((AppCompatButton) _$_findCachedViewById(R.id.done)).setVisibility(0);
        ((LottieAnimationView) _$_findCachedViewById(R.id.animation_view2)).setVisibility(0);
    }
}
