package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ShapeKeyframeAnimation;
import com.airbnb.lottie.model.content.ShapePath;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

public class ShapeContent implements PathContent, BaseKeyframeAnimation.AnimationListener {
    private final boolean hidden;
    private boolean isPathValid;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final Path path = new Path();
    private final ShapeKeyframeAnimation shapeAnimation;
    private List<ShapeModifierContent> shapeModifierContents;
    private final CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();

    public ShapeContent(LottieDrawable lottieDrawable2, BaseLayer layer, ShapePath shape) {
        this.name = shape.getName();
        this.hidden = shape.isHidden();
        this.lottieDrawable = lottieDrawable2;
        ShapeKeyframeAnimation createAnimation = shape.getShapePath().createAnimation();
        this.shapeAnimation = createAnimation;
        layer.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
    }

    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> contentsBefore, List<Content> list) {
        List<ShapeModifierContent> shapeModifierContents2 = null;
        for (int i = 0; i < contentsBefore.size(); i++) {
            Content content = contentsBefore.get(i);
            if ((content instanceof TrimPathContent) && ((TrimPathContent) content).getType() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                TrimPathContent trimPath = (TrimPathContent) content;
                this.trimPaths.addTrimPath(trimPath);
                trimPath.addListener(this);
            } else if (content instanceof ShapeModifierContent) {
                if (shapeModifierContents2 == null) {
                    shapeModifierContents2 = new ArrayList<>();
                }
                shapeModifierContents2.add((ShapeModifierContent) content);
            }
        }
        this.shapeAnimation.setShapeModifiers(shapeModifierContents2);
    }

    public Path getPath() {
        if (this.isPathValid) {
            return this.path;
        }
        this.path.reset();
        if (this.hidden) {
            this.isPathValid = true;
            return this.path;
        }
        Path shapeAnimationPath = (Path) this.shapeAnimation.getValue();
        if (shapeAnimationPath == null) {
            return this.path;
        }
        this.path.set(shapeAnimationPath);
        this.path.setFillType(Path.FillType.EVEN_ODD);
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    public String getName() {
        return this.name;
    }
}
