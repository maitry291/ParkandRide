package com.example.parkandride;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.example.parkandride.models.BookingDetails;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.AuthKt;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ktx.DatabaseKt;
import com.google.firebase.ktx.Firebase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007H\u0016J\u0010\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0007H\u0016J\b\u0010\u0015\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XD¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/example/parkandride/Booking;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/razorpay/PaymentResultListener;", "()V", "ref", "Lcom/google/firebase/database/FirebaseDatabase;", "tag", "", "time1", "Landroid/app/TimePickerDialog$OnTimeSetListener;", "time2", "move", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPaymentError", "i", "", "s", "onPaymentSuccess", "updateSlot", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Booking.kt */
public final class Booking extends AppCompatActivity implements PaymentResultListener {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    /* access modifiers changed from: private */
    public final FirebaseDatabase ref = DatabaseKt.getDatabase(Firebase.INSTANCE);
    private final String tag = "0507";
    private final TimePickerDialog.OnTimeSetListener time1 = new Booking$$ExternalSyntheticLambda0(this);
    private final TimePickerDialog.OnTimeSetListener time2 = new Booking$$ExternalSyntheticLambda1(this);

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

    /* access modifiers changed from: private */
    /* renamed from: time1$lambda-0  reason: not valid java name */
    public static final void m12time1$lambda0(Booking this$0, TimePicker view, int hourOfDay, int minute) {
        String formattedTime;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (hourOfDay == 0) {
            if (minute < 10) {
                formattedTime = (hourOfDay + 12) + ":0" + minute + " am";
            } else {
                formattedTime = (hourOfDay + 12) + ':' + minute + " am";
            }
        } else if (hourOfDay > 12) {
            if (minute < 10) {
                formattedTime = (hourOfDay - 12) + ":0" + minute + " pm";
            } else {
                formattedTime = (hourOfDay - 12) + ':' + minute + " pm";
            }
        } else if (hourOfDay == 12) {
            if (minute < 10) {
                formattedTime = hourOfDay + ":0" + minute + " pm";
            } else {
                formattedTime = hourOfDay + ':' + minute + " pm";
            }
        } else if (minute < 10) {
            formattedTime = hourOfDay + ':' + minute + " am";
        } else {
            formattedTime = hourOfDay + ':' + minute + " am";
        }
        ((AppCompatButton) this$0._$_findCachedViewById(R.id.inTime)).setText("In time:" + formattedTime);
    }

    /* access modifiers changed from: private */
    /* renamed from: time2$lambda-1  reason: not valid java name */
    public static final void m13time2$lambda1(Booking this$0, TimePicker view, int hourOfDay, int minute) {
        String formattedTime;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (hourOfDay == 0) {
            if (minute < 10) {
                formattedTime = (hourOfDay + 12) + ":0" + minute + " am";
            } else {
                formattedTime = (hourOfDay + 12) + ':' + minute + " am";
            }
        } else if (hourOfDay > 12) {
            if (minute < 10) {
                formattedTime = (hourOfDay - 12) + ":0" + minute + " pm";
            } else {
                formattedTime = (hourOfDay - 12) + ':' + minute + " pm";
            }
        } else if (hourOfDay == 12) {
            if (minute < 10) {
                formattedTime = hourOfDay + ":0" + minute + " pm";
            } else {
                formattedTime = hourOfDay + ':' + minute + " pm";
            }
        } else if (minute < 10) {
            formattedTime = hourOfDay + ':' + minute + " am";
        } else {
            formattedTime = hourOfDay + ':' + minute + " am";
        }
        ((AppCompatButton) this$0._$_findCachedViewById(R.id.outTime)).setText("Out time:" + formattedTime);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_booking);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        ((AppCompatButton) _$_findCachedViewById(R.id.inTime)).setOnClickListener(new Booking$$ExternalSyntheticLambda2(this));
        ((AppCompatButton) _$_findCachedViewById(R.id.outTime)).setOnClickListener(new Booking$$ExternalSyntheticLambda3(this));
        ((AppCompatButton) _$_findCachedViewById(R.id.date)).setOnClickListener(new Booking$$ExternalSyntheticLambda4(this));
        ((AppCompatButton) _$_findCachedViewById(R.id.book)).setOnClickListener(new Booking$$ExternalSyntheticLambda5(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final void m7onCreate$lambda2(Booking this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new TimePickerDialog(this$0, this$0.time1, 12, 10, false).show();
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-3  reason: not valid java name */
    public static final void m8onCreate$lambda3(Booking this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        new TimePickerDialog(this$0, this$0.time2, 12, 10, false).show();
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-5  reason: not valid java name */
    public static final void m9onCreate$lambda5(Booking this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Calendar c = Calendar.getInstance();
        Intrinsics.checkNotNullExpressionValue(c, "getInstance()");
        new DatePickerDialog(this$0, new Booking$$ExternalSyntheticLambda6(this$0), c.get(1), c.get(2), c.get(5)).show();
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-5$lambda-4  reason: not valid java name */
    public static final void m10onCreate$lambda5$lambda4(Booking this$0, DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((AppCompatButton) this$0._$_findCachedViewById(R.id.date)).setText("Date:" + dayOfMonth + '-' + (monthOfYear + 1) + '-' + year);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-6  reason: not valid java name */
    public static final void m11onCreate$lambda6(Booking this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = true;
        if (!(((EditText) this$0._$_findCachedViewById(R.id.customer)).getText().toString().length() == 0)) {
            if (!(((EditText) this$0._$_findCachedViewById(R.id.vehicleNumber)).getText().toString().length() == 0)) {
                if (!(((EditText) this$0._$_findCachedViewById(R.id.phoneNumber)).getText().toString().length() == 0)) {
                    if (((EditText) this$0._$_findCachedViewById(R.id.duration)).getText().toString().length() != 0) {
                        z = false;
                    }
                    if (!z) {
                        int amount = MathKt.roundToInt(Float.parseFloat(String.valueOf(this$0.getIntent().getIntExtra("price", 10))) * ((float) 100));
                        Checkout checkout = new Checkout();
                        checkout.setKeyID("rzp_test_oGcS1S5FYEcJjG");
                        JSONObject object = new JSONObject();
                        try {
                            object.put("name", "Parking Zone");
                            object.put("description", "Booking payment");
                            object.put("theme.color", "");
                            object.put("currency", "INR");
                            object.put("amount", amount);
                            object.put("prefill.contact", "9687482595");
                            object.put("prefill.email", "maitrymakwana196@gmail.com");
                            checkout.open(this$0, object);
                            return;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d(this$0.tag, e.toString());
                            this$0.move();
                            return;
                        }
                    }
                }
            }
        }
        Snackbar.make((View) (LinearLayout) this$0._$_findCachedViewById(R.id.b1), (CharSequence) "Please fill up all details", -1).show();
    }

    public void onPaymentSuccess(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        Toast.makeText(this, "Payment is successful", 0).show();
        Log.d(this.tag, "payment " + s);
        move();
    }

    public void onPaymentError(int i, String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        Toast.makeText(this, "Booking Successful", 0).show();
        Log.d(this.tag, "Booking Successful: " + s);
        move();
    }

    private final void move() {
        BookingDetails model = new BookingDetails();
        String currentDate = new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date());
        model.setName(((EditText) _$_findCachedViewById(R.id.customer)).getText().toString());
        model.setVehicleNum(((EditText) _$_findCachedViewById(R.id.vehicleNumber)).getText().toString());
        model.setPhoneNum(((EditText) _$_findCachedViewById(R.id.phoneNumber)).getText().toString());
        model.setIntime(((AppCompatButton) _$_findCachedViewById(R.id.inTime)).getText().toString());
        model.setOuttime(((AppCompatButton) _$_findCachedViewById(R.id.outTime)).getText().toString());
        model.setDuration(((EditText) _$_findCachedViewById(R.id.duration)).getText().toString());
        model.setDate(((AppCompatButton) _$_findCachedViewById(R.id.date)).getText().toString());
        model.setTimeOfPay(currentDate.toString());
        model.setPrice(getIntent().getIntExtra("price", 0));
        DatabaseReference reference = this.ref.getReference("Bookings");
        FirebaseUser currentUser = AuthKt.getAuth(Firebase.INSTANCE).getCurrentUser();
        reference.child(String.valueOf(currentUser != null ? currentUser.getUid() : null)).push().setValue(model);
        updateSlot();
        Log.d(this.tag, "uploaded");
        Intent i = new Intent(this, ConfirmBooking.class);
        i.putExtra("name", model.getName());
        i.putExtra("date", model.getDate());
        i.putExtra("intime", model.getIntime());
        i.putExtra("outtime", model.getOuttime());
        i.putExtra("phone", model.getPhoneNum());
        i.putExtra("vehicle", model.getVehicleNum());
        i.putExtra(TypedValues.TransitionType.S_DURATION, model.getDuration());
        i.putExtra("flag", "0");
        i.putExtra("time", model.getTimeOfPay());
        i.putExtra("price", model.getPrice());
        startActivity(i);
    }

    private final void updateSlot() {
        FirebaseUser currentUser = AuthKt.getAuth(Firebase.INSTANCE).getCurrentUser();
        String user = String.valueOf(currentUser != null ? currentUser.getUid() : null);
        this.ref.getReference("Bookings").child(user).addValueEventListener(new Booking$updateSlot$1(this, user));
        this.ref.getReference("Slots").addChildEventListener(new Booking$updateSlot$2(this));
    }
}
