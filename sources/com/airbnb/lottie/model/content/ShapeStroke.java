package com.airbnb.lottie.model.content;

import android.graphics.Paint;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.StrokeContent;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.List;

public class ShapeStroke implements ContentModel {
    private final LineCapType capType;
    private final AnimatableColorValue color;
    private final boolean hidden;
    private final LineJoinType joinType;
    private final List<AnimatableFloatValue> lineDashPattern;
    private final float miterLimit;
    private final String name;
    private final AnimatableFloatValue offset;
    private final AnimatableIntegerValue opacity;
    private final AnimatableFloatValue width;

    public enum LineCapType {
        BUTT,
        ROUND,
        UNKNOWN;

        public Paint.Cap toPaintCap() {
            switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType[ordinal()]) {
                case 1:
                    return Paint.Cap.BUTT;
                case 2:
                    return Paint.Cap.ROUND;
                default:
                    return Paint.Cap.SQUARE;
            }
        }
    }

    /* renamed from: com.airbnb.lottie.model.content.ShapeStroke$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType;
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType;

        static {
            int[] iArr = new int[LineJoinType.values().length];
            $SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType = iArr;
            try {
                iArr[LineJoinType.BEVEL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType[LineJoinType.MITER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType[LineJoinType.ROUND.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            int[] iArr2 = new int[LineCapType.values().length];
            $SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType = iArr2;
            try {
                iArr2[LineCapType.BUTT.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType[LineCapType.ROUND.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineCapType[LineCapType.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public enum LineJoinType {
        MITER,
        ROUND,
        BEVEL;

        public Paint.Join toPaintJoin() {
            switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$ShapeStroke$LineJoinType[ordinal()]) {
                case 1:
                    return Paint.Join.BEVEL;
                case 2:
                    return Paint.Join.MITER;
                case 3:
                    return Paint.Join.ROUND;
                default:
                    return null;
            }
        }
    }

    public ShapeStroke(String name2, AnimatableFloatValue offset2, List<AnimatableFloatValue> lineDashPattern2, AnimatableColorValue color2, AnimatableIntegerValue opacity2, AnimatableFloatValue width2, LineCapType capType2, LineJoinType joinType2, float miterLimit2, boolean hidden2) {
        this.name = name2;
        this.offset = offset2;
        this.lineDashPattern = lineDashPattern2;
        this.color = color2;
        this.opacity = opacity2;
        this.width = width2;
        this.capType = capType2;
        this.joinType = joinType2;
        this.miterLimit = miterLimit2;
        this.hidden = hidden2;
    }

    public Content toContent(LottieDrawable drawable, BaseLayer layer) {
        return new StrokeContent(drawable, layer, this);
    }

    public String getName() {
        return this.name;
    }

    public AnimatableColorValue getColor() {
        return this.color;
    }

    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    public AnimatableFloatValue getWidth() {
        return this.width;
    }

    public List<AnimatableFloatValue> getLineDashPattern() {
        return this.lineDashPattern;
    }

    public AnimatableFloatValue getDashOffset() {
        return this.offset;
    }

    public LineCapType getCapType() {
        return this.capType;
    }

    public LineJoinType getJoinType() {
        return this.joinType;
    }

    public float getMiterLimit() {
        return this.miterLimit;
    }

    public boolean isHidden() {
        return this.hidden;
    }
}
