package com.airbnb.lottie;

import java.util.HashMap;
import java.util.Map;

public class TextDelegate {
    private final LottieAnimationView animationView;
    private boolean cacheText;
    private final LottieDrawable drawable;
    private final Map<String, String> stringMap;

    TextDelegate() {
        this.stringMap = new HashMap();
        this.cacheText = true;
        this.animationView = null;
        this.drawable = null;
    }

    public TextDelegate(LottieAnimationView animationView2) {
        this.stringMap = new HashMap();
        this.cacheText = true;
        this.animationView = animationView2;
        this.drawable = null;
    }

    public TextDelegate(LottieDrawable drawable2) {
        this.stringMap = new HashMap();
        this.cacheText = true;
        this.drawable = drawable2;
        this.animationView = null;
    }

    public String getText(String layerName, String input) {
        return getText(input);
    }

    public String getText(String input) {
        return input;
    }

    public void setText(String input, String output) {
        this.stringMap.put(input, output);
        invalidate();
    }

    public void setCacheText(boolean cacheText2) {
        this.cacheText = cacheText2;
    }

    public void invalidateText(String input) {
        this.stringMap.remove(input);
        invalidate();
    }

    public void invalidateAllText() {
        this.stringMap.clear();
        invalidate();
    }

    public final String getTextInternal(String layerName, String input) {
        if (this.cacheText && this.stringMap.containsKey(input)) {
            return this.stringMap.get(input);
        }
        String text = getText(layerName, input);
        if (this.cacheText) {
            this.stringMap.put(input, text);
        }
        return text;
    }

    private void invalidate() {
        LottieAnimationView lottieAnimationView = this.animationView;
        if (lottieAnimationView != null) {
            lottieAnimationView.invalidate();
        }
        LottieDrawable lottieDrawable = this.drawable;
        if (lottieDrawable != null) {
            lottieDrawable.invalidateSelf();
        }
    }
}
