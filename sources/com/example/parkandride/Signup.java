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
import com.example.parkandride.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.AuthKt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ktx.DatabaseKt;
import com.google.firebase.ktx.Firebase;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\b\u0010\u000b\u001a\u00020\bH\u0014J\b\u0010\f\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/example/parkandride/Signup;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "db", "Lcom/google/firebase/database/FirebaseDatabase;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "updateUI", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Signup.kt */
public final class Signup extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private FirebaseAuth auth;
    private final FirebaseDatabase db = DatabaseKt.getDatabase(Firebase.INSTANCE);

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
        setContentView((int) R.layout.activity_signup);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        ((TextView) _$_findCachedViewById(R.id.logIn)).setOnClickListener(new Signup$$ExternalSyntheticLambda1(this));
        this.auth = AuthKt.getAuth(Firebase.INSTANCE);
        DatabaseReference ref = this.db.getReference("Users");
        Intrinsics.checkNotNullExpressionValue(ref, "db.getReference(\"Users\")");
        ((AppCompatButton) _$_findCachedViewById(R.id.signup)).setOnClickListener(new Signup$$ExternalSyntheticLambda2(this, ref));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m51onCreate$lambda0(Signup this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0, Login.class));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-3  reason: not valid java name */
    public static final void m52onCreate$lambda3(Signup this$0, DatabaseReference $ref, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter($ref, "$ref");
        boolean z = false;
        ((LottieAnimationView) this$0._$_findCachedViewById(R.id.progress_signup)).setVisibility(0);
        String email = ((EditText) this$0._$_findCachedViewById(R.id.email_signup)).getText().toString();
        String password = ((EditText) this$0._$_findCachedViewById(R.id.password_signup)).getText().toString();
        int flag = 0;
        if (email.length() == 0) {
            ((EditText) this$0._$_findCachedViewById(R.id.email_signup)).setError("Please enter email ID");
            flag = 1;
            ((LottieAnimationView) this$0._$_findCachedViewById(R.id.progress_signup)).setVisibility(8);
        }
        if (password.length() == 0) {
            z = true;
        }
        if (z) {
            ((EditText) this$0._$_findCachedViewById(R.id.password_signup)).setError("Please enter Password");
            flag = 1;
            ((LottieAnimationView) this$0._$_findCachedViewById(R.id.progress_signup)).setVisibility(8);
        }
        if (flag == 0) {
            FirebaseAuth firebaseAuth = this$0.auth;
            if (firebaseAuth == null) {
                Intrinsics.throwUninitializedPropertyAccessException("auth");
                firebaseAuth = null;
            }
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) this$0, new Signup$$ExternalSyntheticLambda0(this$0, email, password, $ref));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-3$lambda-2  reason: not valid java name */
    public static final void m53onCreate$lambda3$lambda2(Signup this$0, String $email, String $password, DatabaseReference $ref, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter($email, "$email");
        Intrinsics.checkNotNullParameter($password, "$password");
        Intrinsics.checkNotNullParameter($ref, "$ref");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Log.d("ContentValues", "createUserWithEmail:success");
            FirebaseAuth firebaseAuth = this$0.auth;
            FirebaseAuth firebaseAuth2 = null;
            if (firebaseAuth == null) {
                Intrinsics.throwUninitializedPropertyAccessException("auth");
                firebaseAuth = null;
            }
            FirebaseUser user = firebaseAuth.getCurrentUser();
            User newUser = new User(((EditText) this$0._$_findCachedViewById(R.id.person)).getText().toString(), $email, $password, String.valueOf(user != null ? user.getUid() : null));
            FirebaseAuth firebaseAuth3 = this$0.auth;
            if (firebaseAuth3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("auth");
            } else {
                firebaseAuth2 = firebaseAuth3;
            }
            String it1 = firebaseAuth2.getUid();
            if (it1 != null) {
                $ref.child(it1).setValue(newUser);
            }
            this$0.updateUI();
            return;
        }
        Log.w("ContentValues", "createUserWithEmail:failure", task.getException());
        Toast.makeText(this$0.getBaseContext(), "Authentication failed.", 0).show();
        ((LottieAnimationView) this$0._$_findCachedViewById(R.id.progress_signup)).setVisibility(8);
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
