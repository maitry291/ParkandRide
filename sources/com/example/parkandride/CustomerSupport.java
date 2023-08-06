package com.example.parkandride;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parkandride.adapters.MessageAdapter;
import com.example.parkandride.models.Chat;
import com.example.parkandride.models.Message;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.ktx.AuthKt;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ktx.DatabaseKt;
import com.google.firebase.ktx.Firebase;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u0010\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\fH\u0007J\u0012\u0010\u0013\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0015J\b\u0010\u0016\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006j\n\u0012\u0004\u0012\u00020\u0007\u0018\u0001`\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/example/parkandride/CustomerSupport;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "database", "Lcom/google/firebase/database/FirebaseDatabase;", "messageModalArrayList", "Ljava/util/ArrayList;", "Lcom/example/parkandride/models/Message;", "Lkotlin/collections/ArrayList;", "messageRVAdapter", "Lcom/example/parkandride/adapters/MessageAdapter;", "receiverId", "", "senderId", "adminChat", "", "message", "dummy", "msg", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "readData", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: CustomerSupport.kt */
public final class CustomerSupport extends AppCompatActivity {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    /* access modifiers changed from: private */
    public final FirebaseDatabase database = DatabaseKt.getDatabase(Firebase.INSTANCE);
    /* access modifiers changed from: private */
    public ArrayList<Message> messageModalArrayList;
    /* access modifiers changed from: private */
    public MessageAdapter messageRVAdapter;
    /* access modifiers changed from: private */
    public String receiverId;
    /* access modifiers changed from: private */
    public final String senderId;

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

    public CustomerSupport() {
        FirebaseUser currentUser = AuthKt.getAuth(Firebase.INSTANCE).getCurrentUser();
        this.senderId = currentUser != null ? currentUser.getUid() : null;
        this.receiverId = "1k6t0v8JJjZXWQKfJqvWIguTSOS2";
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_customer_support);
        this.messageModalArrayList = new ArrayList<>();
        if (Intrinsics.areEqual((Object) this.senderId, (Object) this.receiverId)) {
            this.receiverId = String.valueOf(getIntent().getStringExtra("id"));
            String name = getIntent().getStringExtra("name");
            ((TextView) _$_findCachedViewById(R.id.load2)).setText("All Feedbacks from");
            ((TextView) _$_findCachedViewById(R.id.load1)).setText(name);
            readData();
            ((ImageView) _$_findCachedViewById(R.id.idIBSend)).setOnClickListener(new CustomerSupport$$ExternalSyntheticLambda2(this));
        } else {
            ArrayList<Message> arrayList = this.messageModalArrayList;
            Intrinsics.checkNotNull(arrayList);
            arrayList.add(new Message("Hi\nGreetings from Parking Zone\nHow can i help you?", "bot"));
            ((TextView) _$_findCachedViewById(R.id.load1)).setOnClickListener(new CustomerSupport$$ExternalSyntheticLambda3(this));
            ((ImageView) _$_findCachedViewById(R.id.idIBSend)).setOnClickListener(new CustomerSupport$$ExternalSyntheticLambda4(this));
        }
        ArrayList<Message> arrayList2 = this.messageModalArrayList;
        Intrinsics.checkNotNull(arrayList2);
        this.messageRVAdapter = new MessageAdapter(this, arrayList2);
        LinearLayoutManager m = new LinearLayoutManager(this, 1, false);
        m.setStackFromEnd(true);
        ((RecyclerView) _$_findCachedViewById(R.id.idRVChats)).setLayoutManager(m);
        ((RecyclerView) _$_findCachedViewById(R.id.idRVChats)).setAdapter(this.messageRVAdapter);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-0  reason: not valid java name */
    public static final void m20onCreate$lambda0(CustomerSupport this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (((EditText) this$0._$_findCachedViewById(R.id.idEdtMessage)).getText().toString().length() == 0) {
            Toast.makeText(this$0, "Please enter your message..", 0).show();
            return;
        }
        String msg = ((EditText) this$0._$_findCachedViewById(R.id.idEdtMessage)).getText().toString();
        ((EditText) this$0._$_findCachedViewById(R.id.idEdtMessage)).setText("");
        this$0.adminChat(new Message(msg, "user"));
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-1  reason: not valid java name */
    public static final void m21onCreate$lambda1(CustomerSupport this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.readData();
        ((LinearLayout) this$0._$_findCachedViewById(R.id.load)).setVisibility(8);
    }

    /* access modifiers changed from: private */
    /* renamed from: onCreate$lambda-2  reason: not valid java name */
    public static final void m22onCreate$lambda2(CustomerSupport this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = true;
        if (((EditText) this$0._$_findCachedViewById(R.id.idEdtMessage)).getText().toString().length() == 0) {
            Toast.makeText(this$0, "Please enter your message..", 0).show();
            return;
        }
        String msg = ((EditText) this$0._$_findCachedViewById(R.id.idEdtMessage)).getText().toString();
        ((EditText) this$0._$_findCachedViewById(R.id.idEdtMessage)).setText("");
        LinearLayout $this$isVisible$iv = (LinearLayout) this$0._$_findCachedViewById(R.id.load);
        Intrinsics.checkNotNullExpressionValue($this$isVisible$iv, "load");
        if ($this$isVisible$iv.getVisibility() != 0) {
            z = false;
        }
        if (z) {
            this$0.dummy(msg);
        } else {
            this$0.adminChat(new Message(msg, "user"));
        }
    }

    private final void readData() {
        this.database.getReference("CustomerSupport").child(this.senderId + this.receiverId).addValueEventListener(new CustomerSupport$readData$1(this));
    }

    private final void adminChat(Message message) {
        Message m = new Message(message.getMsg(), "bot");
        Chat chat = new Chat();
        chat.setName(String.valueOf(getIntent().getStringExtra("name")));
        chat.setSender(String.valueOf(this.senderId));
        chat.setMsg(message.getMsg());
        chat.setType(message.getSender());
        chat.setMessageId(message.getMessageId());
        if (!Intrinsics.areEqual((Object) this.senderId, (Object) "1k6t0v8JJjZXWQKfJqvWIguTSOS2")) {
            this.database.getReference("Chats").child(String.valueOf(this.senderId)).setValue(chat);
        }
        this.database.getReference("CustomerSupport").child(this.senderId + this.receiverId).push().setValue(message).addOnSuccessListener(new CustomerSupport$$ExternalSyntheticLambda1(this, m));
    }

    /* access modifiers changed from: private */
    /* renamed from: adminChat$lambda-4  reason: not valid java name */
    public static final void m18adminChat$lambda4(CustomerSupport this$0, Message $m, Void it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter($m, "$m");
        this$0.database.getReference("CustomerSupport").child(this$0.receiverId + this$0.senderId).push().setValue($m).addOnSuccessListener(new CustomerSupport$$ExternalSyntheticLambda0());
    }

    /* access modifiers changed from: private */
    /* renamed from: adminChat$lambda-4$lambda-3  reason: not valid java name */
    public static final void m19adminChat$lambda4$lambda3(Void it) {
    }

    public final void dummy(String msg) {
        Intrinsics.checkNotNullParameter(msg, NotificationCompat.CATEGORY_MESSAGE);
        ArrayList<Message> arrayList = this.messageModalArrayList;
        if (arrayList != null) {
            arrayList.add(new Message(msg, "user"));
        }
        Integer num = null;
        if (StringsKt.contains$default((CharSequence) msg, (CharSequence) "hey", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) msg, (CharSequence) "hi", false, 2, (Object) null)) {
            ArrayList<Message> arrayList2 = this.messageModalArrayList;
            if (arrayList2 != null) {
                arrayList2.add(new Message("Do you need help with your bookings?", "bot"));
            }
        } else if (StringsKt.contains$default((CharSequence) msg, (CharSequence) "yes", false, 2, (Object) null)) {
            ArrayList<Message> arrayList3 = this.messageModalArrayList;
            if (arrayList3 != null) {
                arrayList3.add(new Message("You can contact us:\n\nEmail id: parkingzone@yahoo.com\n\nPhone number: +918247646465", "bot"));
            }
        } else if (StringsKt.contains$default((CharSequence) msg, (CharSequence) "no", false, 2, (Object) null)) {
            ArrayList<Message> arrayList4 = this.messageModalArrayList;
            if (arrayList4 != null) {
                arrayList4.add(new Message("Do you need any other help?", "bot"));
            }
        } else {
            ArrayList<Message> arrayList5 = this.messageModalArrayList;
            if (arrayList5 != null) {
                num = Integer.valueOf(arrayList5.size());
            }
            Intrinsics.checkNotNull(num);
            if (num.intValue() > 7) {
                adminChat(new Message(msg, "user"));
            } else {
                ArrayList<Message> arrayList6 = this.messageModalArrayList;
                if (arrayList6 != null) {
                    arrayList6.add(new Message("Thanks for taking my help.\n\nPlease give your feedback...", "bot"));
                }
            }
        }
        MessageAdapter messageAdapter = this.messageRVAdapter;
        Intrinsics.checkNotNull(messageAdapter);
        messageAdapter.notifyDataSetChanged();
        ((EditText) _$_findCachedViewById(R.id.idEdtMessage)).setText("");
    }
}
