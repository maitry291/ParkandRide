package com.example.parkandride;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ktx.AuthKt;
import com.google.firebase.ktx.Firebase;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014J\b\u0010\n\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/example/parkandride/Firstpage;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "updateUI", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Firstpage.kt */
public final class Firstpage extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final FirebaseAuth auth = AuthKt.getAuth(Firebase.INSTANCE);

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
        setContentView((int) R.layout.activity_firstpage);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        ((LottieAnimationView) _$_findCachedViewById(R.id.splash)).playAnimation();
        new Handler().postDelayed(new Firstpage$$ExternalSyntheticLambda0(this), 5000);
        ((AppCompatButton) _$_findCachedViewById(R.id.signUp)).setOnClickListener(new Firstpage$$ExternalSyntheticLambda1(this));
        ((AppCompatButton) _$_findCachedViewById(R.id.logIn)).setOnClickListener(new Firstpage$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m24onCreate$lambda0(Firstpage this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((LottieAnimationView) this$0._$_findCachedViewById(R.id.splash)).setVisibility(8);
        ((RelativeLayout) this$0._$_findCachedViewById(R.id.rl)).setVisibility(0);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1  reason: not valid java name */
    public static final void m25onCreate$lambda1(Firstpage this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, Signup.class));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final void m26onCreate$lambda2(Firstpage this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, Login.class));
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.auth.getCurrentUser() != null) {
            updateUI();
        }
    }

    private final void updateUI() {
        Toast.makeText(this, "Welcome to Parking Zone", 0).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}
