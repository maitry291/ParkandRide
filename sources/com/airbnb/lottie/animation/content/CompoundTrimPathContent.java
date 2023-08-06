package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class CompoundTrimPathContent {
    private final List<TrimPathContent> contents = new ArrayList();

    /* access modifiers changed from: package-private */
    public void addTrimPath(TrimPathContent trimPath) {
        this.contents.add(trimPath);
    }

    public void apply(Path path) {
        for (int i = this.contents.size() - 1; i >= 0; i--) {
            Utils.applyTrimPathIfNeeded(path, this.contents.get(i));
        }
    }
}
