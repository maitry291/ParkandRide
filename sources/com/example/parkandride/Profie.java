package com.example.parkandride;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parkandride.adapters.BookingAdapter;
import com.example.parkandride.adapters.ChatAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.AuthKt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ktx.DatabaseKt;
import com.google.firebase.ktx.Firebase;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lcom/example/parkandride/Profie;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "count", "", "getCount", "()I", "setCount", "(I)V", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Profie.kt */
public final class Profie extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private int count;

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

    public final int getCount() {
        return this.count;
    }

    public final void setCount(int i) {
        this.count = i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_profie);
        Intent i = new Intent(this, CustomerSupport.class);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        DatabaseReference child = DatabaseKt.getDatabase(Firebase.INSTANCE).getReference().child("Users");
        FirebaseUser currentUser = AuthKt.getAuth(Firebase.INSTANCE).getCurrentUser();
        Intrinsics.checkNotNull(currentUser);
        child.child(currentUser.getUid()).addValueEventListener(new Profie$onCreate$1(this, i));
        FirebaseUser currentUser2 = AuthKt.getAuth(Firebase.INSTANCE).getCurrentUser();
        if (Intrinsics.areEqual((Object) currentUser2 != null ? currentUser2.getUid() : null, (Object) "1k6t0v8JJjZXWQKfJqvWIguTSOS2")) {
            ((AppCompatButton) _$_findCachedViewById(R.id.customerSupport)).setVisibility(8);
            ((TextView) _$_findCachedViewById(R.id.total)).setText("Admin account\n\nAll Feedbacks from users:");
            ArrayList list = new ArrayList();
            ChatAdapter adapter = new ChatAdapter(this, list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            DatabaseKt.getDatabase(Firebase.INSTANCE).getReference().child("Chats").addChildEventListener(new Profie$onCreate$2(adapter, list));
            ((RecyclerView) _$_findCachedViewById(R.id.allBookings)).setLayoutManager(linearLayoutManager);
            ((RecyclerView) _$_findCachedViewById(R.id.allBookings)).setAdapter(adapter);
            return;
        }
        ((AppCompatButton) _$_findCachedViewById(R.id.customerSupport)).setOnClickListener(new Profie$$ExternalSyntheticLambda0(i, this));
        ArrayList list2 = new ArrayList();
        BookingAdapter adapter2 = new BookingAdapter(this, list2);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        DatabaseReference child2 = DatabaseKt.getDatabase(Firebase.INSTANCE).getReference().child("Bookings");
        FirebaseUser currentUser3 = AuthKt.getAuth(Firebase.INSTANCE).getCurrentUser();
        Intrinsics.checkNotNull(currentUser3);
        child2.child(currentUser3.getUid()).addChildEventListener(new Profie$onCreate$4(this, list2, adapter2));
        ((RecyclerView) _$_findCachedViewById(R.id.allBookings)).setLayoutManager(linearLayoutManager2);
        ((RecyclerView) _$_findCachedViewById(R.id.allBookings)).setAdapter(adapter2);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m49onCreate$lambda0(Intent $i, Profie this$0, View it) {
        Intrinsics.checkNotNullParameter($i, "$i");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FirebaseUser currentUser = AuthKt.getAuth(Firebase.INSTANCE).getCurrentUser();
        $i.putExtra("id", currentUser != null ? currentUser.getUid() : null);
        this$0.startActivity($i);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
