package com.airbnb.lottie.animation.content;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

public class TrimPathContent implements Content, BaseKeyframeAnimation.AnimationListener {
    private final BaseKeyframeAnimation<?, Float> endAnimation;
    private final boolean hidden;
    private final List<BaseKeyframeAnimation.AnimationListener> listeners = new ArrayList();
    private final String name;
    private final BaseKeyframeAnimation<?, Float> offsetAnimation;
    private final BaseKeyframeAnimation<?, Float> startAnimation;
    private final ShapeTrimPath.Type type;

    public TrimPathContent(BaseLayer layer, ShapeTrimPath trimPath) {
        this.name = trimPath.getName();
        this.hidden = trimPath.isHidden();
        this.type = trimPath.getType();
        BaseKeyframeAnimation<Float, Float> createAnimation = trimPath.getStart().createAnimation();
        this.startAnimation = createAnimation;
        BaseKeyframeAnimation<Float, Float> createAnimation2 = trimPath.getEnd().createAnimation();
        this.endAnimation = createAnimation2;
        BaseKeyframeAnimation<Float, Float> createAnimation3 = trimPath.getOffset().createAnimation();
        this.offsetAnimation = createAnimation3;
        layer.addAnimation(createAnimation);
        layer.addAnimation(createAnimation2);
        layer.addAnimation(createAnimation3);
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
        createAnimation3.addUpdateListener(this);
    }

    public void onValueChanged() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).onValueChanged();
        }
    }

    public void setContents(List<Content> list, List<Content> list2) {
    }

    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: package-private */
    public void addListener(BaseKeyframeAnimation.AnimationListener listener) {
        this.listeners.add(listener);
    }

    /* access modifiers changed from: package-private */
    public ShapeTrimPath.Type getType() {
        return this.type;
    }

    public BaseKeyframeAnimation<?, Float> getStart() {
        return this.startAnimation;
    }

    public BaseKeyframeAnimation<?, Float> getEnd() {
        return this.endAnimation;
    }

    public BaseKeyframeAnimation<?, Float> getOffset() {
        return this.offsetAnimation;
    }

    public boolean isHidden() {
        return this.hidden;
    }
}
