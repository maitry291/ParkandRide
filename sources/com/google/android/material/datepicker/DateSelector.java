package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.core.util.Pair;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import com.google.android.material.internal.ViewUtils;
import java.util.Collection;

public interface DateSelector<S> extends Parcelable {
    int getDefaultThemeResId(Context context);

    int getDefaultTitleResId();

    Collection<Long> getSelectedDays();

    Collection<Pair<Long, Long>> getSelectedRanges();

    S getSelection();

    String getSelectionContentDescription(Context context);

    String getSelectionDisplayString(Context context);

    boolean isSelectionComplete();

    View onCreateTextInputView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, CalendarConstraints calendarConstraints, OnSelectionChangedListener<S> onSelectionChangedListener);

    void select(long j);

    void setSelection(S s);

    @SynthesizedClassV2(kind = 7, versionHash = "5e5398f0546d1d7afd62641edb14d82894f11ddc41bce363a0c8d0dac82c9c5a")
    /* renamed from: com.google.android.material.datepicker.DateSelector$-CC  reason: invalid class name */
    public final /* synthetic */ class CC<S> {
        public static void showKeyboardWithAutoHideBehavior(EditText... editTexts) {
            if (editTexts.length != 0) {
                View.OnFocusChangeListener listener = new DateSelector$$ExternalSyntheticLambda0(editTexts);
                for (EditText editText : editTexts) {
                    editText.setOnFocusChangeListener(listener);
                }
                ViewUtils.requestFocusAndShowKeyboard(editTexts[0]);
            }
        }

        public static /* synthetic */ void lambda$showKeyboardWithAutoHideBehavior$0(EditText[] editTexts, View view, boolean hasFocus) {
            int length = editTexts.length;
            int i = 0;
            while (i < length) {
                if (!editTexts[i].hasFocus()) {
                    i++;
                } else {
                    return;
                }
            }
            ViewUtils.hideKeyboard(view);
        }
    }
}
