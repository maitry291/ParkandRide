package com.example.parkandride.models;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\bR\u001a\u0010'\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0006\"\u0004\b)\u0010\b¨\u0006*"}, d2 = {"Lcom/example/parkandride/models/BookingDetails;", "", "()V", "bookingId", "", "getBookingId", "()Ljava/lang/String;", "setBookingId", "(Ljava/lang/String;)V", "date", "getDate", "setDate", "duration", "getDuration", "setDuration", "intime", "getIntime", "setIntime", "name", "getName", "setName", "outtime", "getOuttime", "setOuttime", "phoneNum", "getPhoneNum", "setPhoneNum", "price", "", "getPrice", "()I", "setPrice", "(I)V", "timeOfPay", "getTimeOfPay", "setTimeOfPay", "transId", "getTransId", "setTransId", "vehicleNum", "getVehicleNum", "setVehicleNum", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: BookingDetails.kt */
public final class BookingDetails {
    private String bookingId = "";
    private String date = "";
    private String duration = "";
    private String intime = "";
    private String name = "";
    private String outtime = "";
    private String phoneNum = "";
    private int price;
    private String timeOfPay = "";
    private String transId = "";
    private String vehicleNum = "";

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.name = str;
    }

    public final String getVehicleNum() {
        return this.vehicleNum;
    }

    public final void setVehicleNum(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vehicleNum = str;
    }

    public final String getPhoneNum() {
        return this.phoneNum;
    }

    public final void setPhoneNum(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.phoneNum = str;
    }

    public final String getIntime() {
        return this.intime;
    }

    public final void setIntime(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.intime = str;
    }

    public final String getOuttime() {
        return this.outtime;
    }

    public final void setOuttime(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.outtime = str;
    }

    public final String getDuration() {
        return this.duration;
    }

    public final void setDuration(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.duration = str;
    }

    public final String getDate() {
        return this.date;
    }

    public final void setDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.date = str;
    }

    public final String getTransId() {
        return this.transId;
    }

    public final void setTransId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.transId = str;
    }

    public final String getTimeOfPay() {
        return this.timeOfPay;
    }

    public final void setTimeOfPay(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.timeOfPay = str;
    }

    public final String getBookingId() {
        return this.bookingId;
    }

    public final void setBookingId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bookingId = str;
    }

    public final int getPrice() {
        return this.price;
    }

    public final void setPrice(int i) {
        this.price = i;
    }
}
