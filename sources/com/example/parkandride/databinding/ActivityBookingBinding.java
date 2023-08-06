package com.example.parkandride.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import com.example.parkandride.R;

public final class ActivityBookingBinding implements ViewBinding {
    public final LinearLayout b1;
    public final ImageView bi;
    public final AppCompatButton book;
    public final EditText customer;
    public final LinearLayout customerName;
    public final AppCompatButton date;
    public final EditText duration;
    public final AppCompatButton inTime;
    public final AppCompatButton outTime;
    public final EditText phoneNumber;
    private final LinearLayout rootView;
    public final TextView textView3;
    public final EditText vehicleNumber;

    private ActivityBookingBinding(LinearLayout rootView2, LinearLayout b12, ImageView bi2, AppCompatButton book2, EditText customer2, LinearLayout customerName2, AppCompatButton date2, EditText duration2, AppCompatButton inTime2, AppCompatButton outTime2, EditText phoneNumber2, TextView textView32, EditText vehicleNumber2) {
        this.rootView = rootView2;
        this.b1 = b12;
        this.bi = bi2;
        this.book = book2;
        this.customer = customer2;
        this.customerName = customerName2;
        this.date = date2;
        this.duration = duration2;
        this.inTime = inTime2;
        this.outTime = outTime2;
        this.phoneNumber = phoneNumber2;
        this.textView3 = textView32;
        this.vehicleNumber = vehicleNumber2;
    }

    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ActivityBookingBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, (ViewGroup) null, false);
    }

    public static ActivityBookingBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_booking, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    /*  JADX ERROR: NullPointerException in pass: CodeShrinkVisitor
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.InsnArg.wrapInstruction(InsnArg.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.inline(CodeShrinkVisitor.java:146)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:71)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    public static com.example.parkandride.databinding.ActivityBookingBinding bind(android.view.View r29) {
        /*
            r0 = r29
            r15 = r0
            android.widget.LinearLayout r15 = (android.widget.LinearLayout) r15
            r1 = 2131361903(0x7f0a006f, float:1.8343571E38)
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r16 = r2
            android.widget.ImageView r16 = (android.widget.ImageView) r16
            if (r16 == 0) goto L_0x00c6
            r1 = 2131361905(0x7f0a0071, float:1.8343576E38)
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r17 = r2
            androidx.appcompat.widget.AppCompatButton r17 = (androidx.appcompat.widget.AppCompatButton) r17
            if (r17 == 0) goto L_0x00c5
            r1 = 2131361970(0x7f0a00b2, float:1.8343707E38)
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r18 = r2
            android.widget.EditText r18 = (android.widget.EditText) r18
            if (r18 == 0) goto L_0x00c4
            r1 = 2131361971(0x7f0a00b3, float:1.834371E38)
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r19 = r2
            android.widget.LinearLayout r19 = (android.widget.LinearLayout) r19
            if (r19 == 0) goto L_0x00c3
            r1 = 2131361975(0x7f0a00b7, float:1.8343718E38)
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r20 = r2
            androidx.appcompat.widget.AppCompatButton r20 = (androidx.appcompat.widget.AppCompatButton) r20
            if (r20 == 0) goto L_0x00c2
            r1 = 2131362008(0x7f0a00d8, float:1.8343784E38)
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r21 = r2
            android.widget.EditText r21 = (android.widget.EditText) r21
            if (r21 == 0) goto L_0x00c1
            r1 = 2131362083(0x7f0a0123, float:1.8343937E38)
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r22 = r2
            androidx.appcompat.widget.AppCompatButton r22 = (androidx.appcompat.widget.AppCompatButton) r22
            if (r22 == 0) goto L_0x00c0
            r1 = 2131362208(0x7f0a01a0, float:1.834419E38)
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r23 = r2
            androidx.appcompat.widget.AppCompatButton r23 = (androidx.appcompat.widget.AppCompatButton) r23
            if (r23 == 0) goto L_0x00bf
            r1 = 2131362232(0x7f0a01b8, float:1.8344239E38)
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r24 = r2
            android.widget.EditText r24 = (android.widget.EditText) r24
            if (r24 == 0) goto L_0x00be
            r1 = 2131362362(0x7f0a023a, float:1.8344502E38)
            android.view.View r2 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r1)
            r25 = r2
            android.widget.TextView r25 = (android.widget.TextView) r25
            if (r25 == 0) goto L_0x00bd
            r14 = 2131362404(0x7f0a0264, float:1.8344588E38)
            android.view.View r1 = androidx.viewbinding.ViewBindings.findChildViewById(r0, r14)
            r26 = r1
            android.widget.EditText r26 = (android.widget.EditText) r26
            if (r26 == 0) goto L_0x00b8
            com.example.parkandride.databinding.ActivityBookingBinding r27 = new com.example.parkandride.databinding.ActivityBookingBinding
            r2 = r0
            android.widget.LinearLayout r2 = (android.widget.LinearLayout) r2
            r1 = r27
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r18
            r7 = r19
            r8 = r20
            r9 = r21
            r10 = r22
            r11 = r23
            r12 = r24
            r13 = r25
            r28 = r14
            r14 = r26
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r27
        L_0x00b8:
            r28 = r14
            r1 = r28
            goto L_0x00c7
        L_0x00bd:
            goto L_0x00c7
        L_0x00be:
            goto L_0x00c7
        L_0x00bf:
            goto L_0x00c7
        L_0x00c0:
            goto L_0x00c7
        L_0x00c1:
            goto L_0x00c7
        L_0x00c2:
            goto L_0x00c7
        L_0x00c3:
            goto L_0x00c7
        L_0x00c4:
            goto L_0x00c7
        L_0x00c5:
            goto L_0x00c7
        L_0x00c6:
        L_0x00c7:
            android.content.res.Resources r2 = r29.getResources()
            java.lang.String r2 = r2.getResourceName(r1)
            java.lang.NullPointerException r3 = new java.lang.NullPointerException
            java.lang.String r4 = "Missing required view with ID: "
            java.lang.String r4 = r4.concat(r2)
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.parkandride.databinding.ActivityBookingBinding.bind(android.view.View):com.example.parkandride.databinding.ActivityBookingBinding");
    }
}
