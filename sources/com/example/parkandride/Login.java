package com.example.parkandride;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.AuthKt;
import com.google.firebase.ktx.Firebase;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0014J\b\u0010\t\u001a\u00020\u0006H\u0014J\b\u0010\n\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/example/parkandride/Login;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "updateUI", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Login.kt */
public final class Login extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private FirebaseAuth auth;

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
        setContentView((int) R.layout.activity_login);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        ((TextView) _$_findCachedViewById(R.id.signUp)).setOnClickListener(new Login$$ExternalSyntheticLambda1(this));
        this.auth = AuthKt.getAuth(Firebase.INSTANCE);
        ((AppCompatButton) _$_findCachedViewById(R.id.logIn1)).setOnClickListener(new Login$$ExternalSyntheticLambda2(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m29onCreate$lambda0(Login this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, Signup.class));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final void m30onCreate$lambda2(Login this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = false;
        ((LottieAnimationView) this$0._$_findCachedViewById(R.id.progress_login)).setVisibility(0);
        String email = ((EditText) this$0._$_findCachedViewById(R.id.email_id)).getText().toString();
        String password = ((EditText) this$0._$_findCachedViewById(R.id.password_login)).getText().toString();
        if (email.length() == 0) {
            ((EditText) this$0._$_findCachedViewById(R.id.email_id)).setError("Please enter email ID");
            ((LottieAnimationView) this$0._$_findCachedViewById(R.id.progress_login)).setVisibility(8);
            return;
        }
        if (password.length() == 0) {
            z = true;
        }
        if (z) {
            ((EditText) this$0._$_findCachedViewById(R.id.password_login)).setError("Please enter Password");
            ((LottieAnimationView) this$0._$_findCachedViewById(R.id.progress_login)).setVisibility(8);
            return;
        }
        FirebaseAuth firebaseAuth = this$0.auth;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) this$0, new Login$$ExternalSyntheticLambda0(this$0));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2$lambda-1  reason: not valid java name */
    public static final void m31onCreate$lambda2$lambda1(Login this$0, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Log.d("ContentValues", "signInWithEmail:success");
            FirebaseAuth firebaseAuth = this$0.auth;
            if (firebaseAuth == null) {
                Intrinsics.throwUninitializedPropertyAccessException("auth");
                firebaseAuth = null;
            }
            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            this$0.updateUI();
            return;
        }
        Log.w("ContentValues", "signInWithEmail:failure", task.getException());
        Toast.makeText(this$0.getBaseContext(), "Authentication failed.", 0).show();
        ((LottieAnimationView) this$0._$_findCachedViewById(R.id.progress_login)).setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        FirebaseAuth firebaseAuth = this.auth;
        if (firebaseAuth == null) {
            Intrinsics.throwUninitializedPropertyAccessException("auth");
            firebaseAuth = null;
        }
        if (firebaseAuth.getCurrentUser() != null) {
            updateUI();
        }
    }

    private final void updateUI() {
        Toast.makeText(this, "Welcome to Parking Zone", 0).show();
        startActivity(new Intent(this, MapsActivity.class));
    }
}
