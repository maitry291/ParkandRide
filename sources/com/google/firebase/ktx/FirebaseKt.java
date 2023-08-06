package com.google.firebase.ktx;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0001\u001a\u0014\u0010\f\u001a\u0004\u0018\u00010\u0003*\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e\u001a\u001a\u0010\f\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b\u001a\"\u0010\f\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"LIBRARY_NAME", "", "app", "Lcom/google/firebase/FirebaseApp;", "Lcom/google/firebase/ktx/Firebase;", "getApp", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/FirebaseApp;", "options", "Lcom/google/firebase/FirebaseOptions;", "getOptions", "(Lcom/google/firebase/ktx/Firebase;)Lcom/google/firebase/FirebaseOptions;", "name", "initialize", "context", "Landroid/content/Context;", "com.google.firebase-firebase-common-ktx"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* compiled from: Firebase.kt */
public final class FirebaseKt {
    public static final String LIBRARY_NAME = "fire-core-ktx";

    public static final FirebaseApp getApp(Firebase $this$app) {
        Intrinsics.checkNotNullParameter($this$app, "<this>");
        FirebaseApp instance = FirebaseApp.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance()");
        return instance;
    }

    public static final FirebaseApp app(Firebase $this$app, String name) {
        Intrinsics.checkNotNullParameter($this$app, "<this>");
        Intrinsics.checkNotNullParameter(name, "name");
        FirebaseApp instance = FirebaseApp.getInstance(name);
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(name)");
        return instance;
    }

    public static final FirebaseApp initialize(Firebase $this$initialize, Context context) {
        Intrinsics.checkNotNullParameter($this$initialize, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        return FirebaseApp.initializeApp(context);
    }

    public static final FirebaseApp initialize(Firebase $this$initialize, Context context, FirebaseOptions options) {
        Intrinsics.checkNotNullParameter($this$initialize, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        FirebaseApp initializeApp = FirebaseApp.initializeApp(context, options);
        Intrinsics.checkNotNullExpressionValue(initializeApp, "initializeApp(context, options)");
        return initializeApp;
    }

    public static final FirebaseApp initialize(Firebase $this$initialize, Context context, FirebaseOptions options, String name) {
        Intrinsics.checkNotNullParameter($this$initialize, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(name, "name");
        FirebaseApp initializeApp = FirebaseApp.initializeApp(context, options, name);
        Intrinsics.checkNotNullExpressionValue(initializeApp, "initializeApp(context, options, name)");
        return initializeApp;
    }

    public static final FirebaseOptions getOptions(Firebase $this$options) {
        Intrinsics.checkNotNullParameter($this$options, "<this>");
        FirebaseOptions options = getApp(Firebase.INSTANCE).getOptions();
        Intrinsics.checkNotNullExpressionValue(options, "Firebase.app.options");
        return options;
    }
}
