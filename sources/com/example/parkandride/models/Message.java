package com.example.parkandride.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u0005¢\u0006\u0002\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\u001a\u0010\u000f\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/example/parkandride/models/Message;", "", "cmsg", "", "csender", "(Ljava/lang/String;Ljava/lang/String;)V", "()V", "messageId", "getMessageId", "()Ljava/lang/String;", "setMessageId", "(Ljava/lang/String;)V", "msg", "getMsg", "setMsg", "sender", "getSender", "setSender", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Message.kt */
public final class Message {
    private String messageId;
    private String msg;
    private String sender;

    public Message() {
        this.msg = "";
        this.sender = "";
        this.messageId = "";
    }

    public final String getMsg() {
        return this.msg;
    }

    public final void setMsg(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.msg = str;
    }

    public final String getSender() {
        return this.sender;
    }

    public final void setSender(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sender = str;
    }

    public final String getMessageId() {
        return this.messageId;
    }

    public final void setMessageId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.messageId = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Message(String cmsg, String csender) {
        this();
        Intrinsics.checkNotNullParameter(cmsg, "cmsg");
        Intrinsics.checkNotNullParameter(csender, "csender");
        this.msg = cmsg;
        this.sender = csender;
    }
}
