package com.example.parkandride.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\u0018\u00002\u00020\u0001B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007B\u0005¢\u0006\u0002\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001a\u0010\u0005\u001a\u00020\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/example/parkandride/models/User;", "", "name", "", "email", "password", "uid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "()V", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "getName", "setName", "getPassword", "setPassword", "getUid", "setUid", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: User.kt */
public final class User {
    public String email;
    public String name;
    public String password;
    public String uid;

    public User() {
    }

    public final String getName() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("name");
        return null;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getEmail() {
        String str = this.email;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("email");
        return null;
    }

    public final void setEmail(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.email = str;
    }

    public final String getPassword() {
        String str = this.password;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("password");
        return null;
    }

    public final void setPassword(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.password = str;
    }

    public final String getUid() {
        String str = this.uid;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("uid");
        return null;
    }

    public final void setUid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uid = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public User(String name2, String email2, String password2, String uid2) {
        this();
        Intrinsics.checkNotNullParameter(name2, "name");
        Intrinsics.checkNotNullParameter(email2, "email");
        Intrinsics.checkNotNullParameter(password2, "password");
        Intrinsics.checkNotNullParameter(uid2, "uid");
        setName(name2);
        setEmail(email2);
        setPassword(password2);
        setUid(uid2);
    }
}
