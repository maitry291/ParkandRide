package com.airbnb.lottie.animation.keyframe;

import android.graphics.Path;
import com.airbnb.lottie.animation.content.ShapeModifierContent;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class ShapeKeyframeAnimation extends BaseKeyframeAnimation<ShapeData, Path> {
    private List<ShapeModifierContent> shapeModifiers;
    private final Path tempPath = new Path();
    private final ShapeData tempShapeData = new ShapeData();

    public ShapeKeyframeAnimation(List<Keyframe<ShapeData>> keyframes) {
        super(keyframes);
    }

    public Path getValue(Keyframe<ShapeData> keyframe, float keyframeProgress) {
        this.tempShapeData.interpolateBetween((ShapeData) keyframe.startValue, (ShapeData) keyframe.endValue, keyframeProgress);
        ShapeData modifiedShapeData = this.tempShapeData;
        List<ShapeModifierContent> list = this.shapeModifiers;
        if (list != null) {
            for (int i = list.size() - 1; i >= 0; i--) {
                modifiedShapeData = this.shapeModifiers.get(i).modifyShape(modifiedShapeData);
            }
        }
        MiscUtils.getPathFromData(modifiedShapeData, this.tempPath);
        return this.tempPath;
    }

    public void setShapeModifiers(List<ShapeModifierContent> shapeModifiers2) {
        this.shapeModifiers = shapeModifiers2;
    }
}
