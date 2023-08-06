package com.google.android.material.sidesheet;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

abstract class SheetDelegate {
    /* access modifiers changed from: package-private */
    public abstract <V extends View> int calculateTargetStateOnStopNestedScroll(V v);

    /* access modifiers changed from: package-private */
    public abstract int calculateTargetStateOnViewReleased(View view, float f, float f2);

    /* access modifiers changed from: package-private */
    public abstract int getExpandedOffset();

    /* access modifiers changed from: package-private */
    public abstract int getHiddenOffset();

    /* access modifiers changed from: package-private */
    public abstract <V extends View> int getOutwardEdge(V v);

    /* access modifiers changed from: package-private */
    public abstract int getSheetEdge();

    /* access modifiers changed from: package-private */
    public abstract <V extends View> boolean hasReachedExpandedOffset(V v);

    /* access modifiers changed from: package-private */
    public abstract boolean isSettling(View view, int i, boolean z);

    /* access modifiers changed from: package-private */
    public abstract <V extends View> void setTargetStateOnNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3);

    /* access modifiers changed from: package-private */
    public abstract boolean shouldHide(View view, float f);

    SheetDelegate() {
    }
}
