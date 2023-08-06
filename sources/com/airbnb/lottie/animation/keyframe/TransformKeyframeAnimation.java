package com.airbnb.lottie.animation.keyframe;

import android.graphics.Matrix;
import android.graphics.PointF;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import java.util.Collections;

public class TransformKeyframeAnimation {
    private BaseKeyframeAnimation<PointF, PointF> anchorPoint;
    private BaseKeyframeAnimation<?, Float> endOpacity;
    private final Matrix matrix = new Matrix();
    private BaseKeyframeAnimation<Integer, Integer> opacity;
    private BaseKeyframeAnimation<?, PointF> position;
    private BaseKeyframeAnimation<Float, Float> rotation;
    private BaseKeyframeAnimation<ScaleXY, ScaleXY> scale;
    private FloatKeyframeAnimation skew;
    private FloatKeyframeAnimation skewAngle;
    private final Matrix skewMatrix1;
    private final Matrix skewMatrix2;
    private final Matrix skewMatrix3;
    private final float[] skewValues;
    private BaseKeyframeAnimation<?, Float> startOpacity;

    public TransformKeyframeAnimation(AnimatableTransform animatableTransform) {
        this.anchorPoint = animatableTransform.getAnchorPoint() == null ? null : animatableTransform.getAnchorPoint().createAnimation();
        this.position = animatableTransform.getPosition() == null ? null : animatableTransform.getPosition().createAnimation();
        this.scale = animatableTransform.getScale() == null ? null : animatableTransform.getScale().createAnimation();
        this.rotation = animatableTransform.getRotation() == null ? null : animatableTransform.getRotation().createAnimation();
        FloatKeyframeAnimation floatKeyframeAnimation = animatableTransform.getSkew() == null ? null : (FloatKeyframeAnimation) animatableTransform.getSkew().createAnimation();
        this.skew = floatKeyframeAnimation;
        if (floatKeyframeAnimation != null) {
            this.skewMatrix1 = new Matrix();
            this.skewMatrix2 = new Matrix();
            this.skewMatrix3 = new Matrix();
            this.skewValues = new float[9];
        } else {
            this.skewMatrix1 = null;
            this.skewMatrix2 = null;
            this.skewMatrix3 = null;
            this.skewValues = null;
        }
        this.skewAngle = animatableTransform.getSkewAngle() == null ? null : (FloatKeyframeAnimation) animatableTransform.getSkewAngle().createAnimation();
        if (animatableTransform.getOpacity() != null) {
            this.opacity = animatableTransform.getOpacity().createAnimation();
        }
        if (animatableTransform.getStartOpacity() != null) {
            this.startOpacity = animatableTransform.getStartOpacity().createAnimation();
        } else {
            this.startOpacity = null;
        }
        if (animatableTransform.getEndOpacity() != null) {
            this.endOpacity = animatableTransform.getEndOpacity().createAnimation();
        } else {
            this.endOpacity = null;
        }
    }

    public void addAnimationsToLayer(BaseLayer layer) {
        layer.addAnimation(this.opacity);
        layer.addAnimation(this.startOpacity);
        layer.addAnimation(this.endOpacity);
        layer.addAnimation(this.anchorPoint);
        layer.addAnimation(this.position);
        layer.addAnimation(this.scale);
        layer.addAnimation(this.rotation);
        layer.addAnimation(this.skew);
        layer.addAnimation(this.skewAngle);
    }

    public void addListener(BaseKeyframeAnimation.AnimationListener listener) {
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.opacity;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.addUpdateListener(listener);
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.startOpacity;
        if (baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.addUpdateListener(listener);
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation3 = this.endOpacity;
        if (baseKeyframeAnimation3 != null) {
            baseKeyframeAnimation3.addUpdateListener(listener);
        }
        BaseKeyframeAnimation<PointF, PointF> baseKeyframeAnimation4 = this.anchorPoint;
        if (baseKeyframeAnimation4 != null) {
            baseKeyframeAnimation4.addUpdateListener(listener);
        }
        BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation5 = this.position;
        if (baseKeyframeAnimation5 != null) {
            baseKeyframeAnimation5.addUpdateListener(listener);
        }
        BaseKeyframeAnimation<ScaleXY, ScaleXY> baseKeyframeAnimation6 = this.scale;
        if (baseKeyframeAnimation6 != null) {
            baseKeyframeAnimation6.addUpdateListener(listener);
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation7 = this.rotation;
        if (baseKeyframeAnimation7 != null) {
            baseKeyframeAnimation7.addUpdateListener(listener);
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.skew;
        if (floatKeyframeAnimation != null) {
            floatKeyframeAnimation.addUpdateListener(listener);
        }
        FloatKeyframeAnimation floatKeyframeAnimation2 = this.skewAngle;
        if (floatKeyframeAnimation2 != null) {
            floatKeyframeAnimation2.addUpdateListener(listener);
        }
    }

    public void setProgress(float progress) {
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.opacity;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.setProgress(progress);
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2 = this.startOpacity;
        if (baseKeyframeAnimation2 != null) {
            baseKeyframeAnimation2.setProgress(progress);
        }
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation3 = this.endOpacity;
        if (baseKeyframeAnimation3 != null) {
            baseKeyframeAnimation3.setProgress(progress);
        }
        BaseKeyframeAnimation<PointF, PointF> baseKeyframeAnimation4 = this.anchorPoint;
        if (baseKeyframeAnimation4 != null) {
            baseKeyframeAnimation4.setProgress(progress);
        }
        BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation5 = this.position;
        if (baseKeyframeAnimation5 != null) {
            baseKeyframeAnimation5.setProgress(progress);
        }
        BaseKeyframeAnimation<ScaleXY, ScaleXY> baseKeyframeAnimation6 = this.scale;
        if (baseKeyframeAnimation6 != null) {
            baseKeyframeAnimation6.setProgress(progress);
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation7 = this.rotation;
        if (baseKeyframeAnimation7 != null) {
            baseKeyframeAnimation7.setProgress(progress);
        }
        FloatKeyframeAnimation floatKeyframeAnimation = this.skew;
        if (floatKeyframeAnimation != null) {
            floatKeyframeAnimation.setProgress(progress);
        }
        FloatKeyframeAnimation floatKeyframeAnimation2 = this.skewAngle;
        if (floatKeyframeAnimation2 != null) {
            floatKeyframeAnimation2.setProgress(progress);
        }
    }

    public BaseKeyframeAnimation<?, Integer> getOpacity() {
        return this.opacity;
    }

    public BaseKeyframeAnimation<?, Float> getStartOpacity() {
        return this.startOpacity;
    }

    public BaseKeyframeAnimation<?, Float> getEndOpacity() {
        return this.endOpacity;
    }

    public Matrix getMatrix() {
        float rotationValue;
        PointF positionValue;
        this.matrix.reset();
        BaseKeyframeAnimation<?, PointF> position2 = this.position;
        if (!(position2 == null || (positionValue = position2.getValue()) == null || (positionValue.x == 0.0f && positionValue.y == 0.0f))) {
            this.matrix.preTranslate(positionValue.x, positionValue.y);
        }
        BaseKeyframeAnimation<Float, Float> rotation2 = this.rotation;
        if (rotation2 != null) {
            if (rotation2 instanceof ValueCallbackKeyframeAnimation) {
                rotationValue = rotation2.getValue().floatValue();
            } else {
                rotationValue = ((FloatKeyframeAnimation) rotation2).getFloatValue();
            }
            if (rotationValue != 0.0f) {
                this.matrix.preRotate(rotationValue);
            }
        }
        FloatKeyframeAnimation skew2 = this.skew;
        if (skew2 != null) {
            FloatKeyframeAnimation floatKeyframeAnimation = this.skewAngle;
            float mCos = floatKeyframeAnimation == null ? 0.0f : (float) Math.cos(Math.toRadians((double) ((-floatKeyframeAnimation.getFloatValue()) + 90.0f)));
            FloatKeyframeAnimation floatKeyframeAnimation2 = this.skewAngle;
            float mSin = floatKeyframeAnimation2 == null ? 1.0f : (float) Math.sin(Math.toRadians((double) ((-floatKeyframeAnimation2.getFloatValue()) + 90.0f)));
            clearSkewValues();
            float[] fArr = this.skewValues;
            fArr[0] = mCos;
            fArr[1] = mSin;
            fArr[3] = -mSin;
            fArr[4] = mCos;
            fArr[8] = 1.0f;
            this.skewMatrix1.setValues(fArr);
            clearSkewValues();
            float[] fArr2 = this.skewValues;
            fArr2[0] = 1.0f;
            fArr2[3] = (float) Math.tan(Math.toRadians((double) skew2.getFloatValue()));
            fArr2[4] = 1.0f;
            fArr2[8] = 1.0f;
            this.skewMatrix2.setValues(fArr2);
            clearSkewValues();
            float[] fArr3 = this.skewValues;
            fArr3[0] = mCos;
            fArr3[1] = -mSin;
            fArr3[3] = mSin;
            fArr3[4] = mCos;
            fArr3[8] = 1.0f;
            this.skewMatrix3.setValues(fArr3);
            this.skewMatrix2.preConcat(this.skewMatrix1);
            this.skewMatrix3.preConcat(this.skewMatrix2);
            this.matrix.preConcat(this.skewMatrix3);
        }
        BaseKeyframeAnimation<ScaleXY, ScaleXY> scale2 = this.scale;
        if (scale2 != null) {
            ScaleXY scaleTransform = scale2.getValue();
            if (!(scaleTransform.getScaleX() == 1.0f && scaleTransform.getScaleY() == 1.0f)) {
                this.matrix.preScale(scaleTransform.getScaleX(), scaleTransform.getScaleY());
            }
        }
        BaseKeyframeAnimation<PointF, PointF> anchorPoint2 = this.anchorPoint;
        if (anchorPoint2 != null) {
            PointF anchorPointValue = anchorPoint2.getValue();
            if (!(anchorPointValue.x == 0.0f && anchorPointValue.y == 0.0f)) {
                this.matrix.preTranslate(-anchorPointValue.x, -anchorPointValue.y);
            }
        }
        return this.matrix;
    }

    private void clearSkewValues() {
        for (int i = 0; i < 9; i++) {
            this.skewValues[i] = 0.0f;
        }
    }

    public Matrix getMatrixForRepeater(float amount) {
        BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation = this.position;
        PointF anchorPoint2 = null;
        PointF position2 = baseKeyframeAnimation == null ? null : baseKeyframeAnimation.getValue();
        BaseKeyframeAnimation<ScaleXY, ScaleXY> baseKeyframeAnimation2 = this.scale;
        ScaleXY scale2 = baseKeyframeAnimation2 == null ? null : baseKeyframeAnimation2.getValue();
        this.matrix.reset();
        if (position2 != null) {
            this.matrix.preTranslate(position2.x * amount, position2.y * amount);
        }
        if (scale2 != null) {
            this.matrix.preScale((float) Math.pow((double) scale2.getScaleX(), (double) amount), (float) Math.pow((double) scale2.getScaleY(), (double) amount));
        }
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation3 = this.rotation;
        if (baseKeyframeAnimation3 != null) {
            float rotation2 = baseKeyframeAnimation3.getValue().floatValue();
            BaseKeyframeAnimation<PointF, PointF> baseKeyframeAnimation4 = this.anchorPoint;
            if (baseKeyframeAnimation4 != null) {
                anchorPoint2 = baseKeyframeAnimation4.getValue();
            }
            Matrix matrix2 = this.matrix;
            float f = rotation2 * amount;
            float f2 = 0.0f;
            float f3 = anchorPoint2 == null ? 0.0f : anchorPoint2.x;
            if (anchorPoint2 != null) {
                f2 = anchorPoint2.y;
            }
            matrix2.preRotate(f, f3, f2);
        }
        return this.matrix;
    }

    public <T> boolean applyValueCallback(T property, LottieValueCallback<T> callback) {
        if (property == LottieProperty.TRANSFORM_ANCHOR_POINT) {
            BaseKeyframeAnimation<PointF, PointF> baseKeyframeAnimation = this.anchorPoint;
            if (baseKeyframeAnimation == null) {
                this.anchorPoint = new ValueCallbackKeyframeAnimation(callback, new PointF());
                return true;
            }
            baseKeyframeAnimation.setValueCallback(callback);
            return true;
        } else if (property == LottieProperty.TRANSFORM_POSITION) {
            BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation2 = this.position;
            if (baseKeyframeAnimation2 == null) {
                this.position = new ValueCallbackKeyframeAnimation(callback, new PointF());
                return true;
            }
            baseKeyframeAnimation2.setValueCallback(callback);
            return true;
        } else {
            if (property == LottieProperty.TRANSFORM_POSITION_X) {
                BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation3 = this.position;
                if (baseKeyframeAnimation3 instanceof SplitDimensionPathKeyframeAnimation) {
                    ((SplitDimensionPathKeyframeAnimation) baseKeyframeAnimation3).setXValueCallback(callback);
                    return true;
                }
            }
            if (property == LottieProperty.TRANSFORM_POSITION_Y) {
                BaseKeyframeAnimation<?, PointF> baseKeyframeAnimation4 = this.position;
                if (baseKeyframeAnimation4 instanceof SplitDimensionPathKeyframeAnimation) {
                    ((SplitDimensionPathKeyframeAnimation) baseKeyframeAnimation4).setYValueCallback(callback);
                    return true;
                }
            }
            if (property == LottieProperty.TRANSFORM_SCALE) {
                BaseKeyframeAnimation<ScaleXY, ScaleXY> baseKeyframeAnimation5 = this.scale;
                if (baseKeyframeAnimation5 == null) {
                    this.scale = new ValueCallbackKeyframeAnimation(callback, new ScaleXY());
                    return true;
                }
                baseKeyframeAnimation5.setValueCallback(callback);
                return true;
            } else if (property == LottieProperty.TRANSFORM_ROTATION) {
                BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation6 = this.rotation;
                if (baseKeyframeAnimation6 == null) {
                    this.rotation = new ValueCallbackKeyframeAnimation(callback, Float.valueOf(0.0f));
                    return true;
                }
                baseKeyframeAnimation6.setValueCallback(callback);
                return true;
            } else if (property == LottieProperty.TRANSFORM_OPACITY) {
                BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation7 = this.opacity;
                if (baseKeyframeAnimation7 == null) {
                    this.opacity = new ValueCallbackKeyframeAnimation(callback, 100);
                    return true;
                }
                baseKeyframeAnimation7.setValueCallback(callback);
                return true;
            } else if (property == LottieProperty.TRANSFORM_START_OPACITY) {
                BaseKeyframeAnimation<?, Float> baseKeyframeAnimation8 = this.startOpacity;
                if (baseKeyframeAnimation8 == null) {
                    this.startOpacity = new ValueCallbackKeyframeAnimation(callback, Float.valueOf(100.0f));
                    return true;
                }
                baseKeyframeAnimation8.setValueCallback(callback);
                return true;
            } else if (property == LottieProperty.TRANSFORM_END_OPACITY) {
                BaseKeyframeAnimation<?, Float> baseKeyframeAnimation9 = this.endOpacity;
                if (baseKeyframeAnimation9 == null) {
                    this.endOpacity = new ValueCallbackKeyframeAnimation(callback, Float.valueOf(100.0f));
                    return true;
                }
                baseKeyframeAnimation9.setValueCallback(callback);
                return true;
            } else if (property == LottieProperty.TRANSFORM_SKEW) {
                if (this.skew == null) {
                    this.skew = new FloatKeyframeAnimation(Collections.singletonList(new Keyframe(Float.valueOf(0.0f))));
                }
                this.skew.setValueCallback(callback);
                return true;
            } else if (property != LottieProperty.TRANSFORM_SKEW_ANGLE) {
                return false;
            } else {
                if (this.skewAngle == null) {
                    this.skewAngle = new FloatKeyframeAnimation(Collections.singletonList(new Keyframe(Float.valueOf(0.0f))));
                }
                this.skewAngle.setValueCallback(callback);
                return true;
            }
        }
    }
}
