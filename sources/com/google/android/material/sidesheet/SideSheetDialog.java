package com.google.android.material.sidesheet;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.R;

public class SideSheetDialog extends SheetDialog {
    private static final int SIDE_SHEET_DIALOG_DEFAULT_THEME_RES = R.style.Theme_Material3_Light_SideSheetDialog;
    private static final int SIDE_SHEET_DIALOG_THEME_ATTR = R.attr.sideSheetDialogTheme;

    public SideSheetDialog(Context context) {
        this(context, 0);
    }

    public SideSheetDialog(Context context, int theme) {
        super(context, theme, SIDE_SHEET_DIALOG_THEME_ATTR, SIDE_SHEET_DIALOG_DEFAULT_THEME_RES);
        supportRequestWindowFeature(1);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResId() {
        return R.layout.m3_side_sheet_dialog;
    }

    /* access modifiers changed from: protected */
    public int getDialogId() {
        return R.id.m3_side_sheet;
    }

    /* access modifiers changed from: protected */
    public Sheet getBehaviorFromSheet(FrameLayout sheet) {
        return SideSheetBehavior.from(sheet);
    }

    /* access modifiers changed from: protected */
    public int getStateOnStart() {
        return 3;
    }

    public SideSheetBehavior<? extends View> getBehavior() {
        Sheet sheetBehavior = super.getBehavior();
        if (sheetBehavior instanceof SideSheetBehavior) {
            return (SideSheetBehavior) sheetBehavior;
        }
        throw new IllegalStateException("The view is not associated with SideSheetBehavior");
    }
}
