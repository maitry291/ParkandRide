package com.google.android.material.sidesheet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Sheet {
    public static final int RIGHT = 0;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SheetEdge {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SheetState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StableSheetState {
    }

    int getState();

    void setState(int i);
}
