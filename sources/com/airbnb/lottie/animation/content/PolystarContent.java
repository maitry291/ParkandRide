package com.airbnb.lottie.animation.content;

import android.graphics.Path;
import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.content.PolystarShape;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class PolystarContent implements PathContent, BaseKeyframeAnimation.AnimationListener, KeyPathElementContent {
    private static final float POLYGON_MAGIC_NUMBER = 0.25f;
    private static final float POLYSTAR_MAGIC_NUMBER = 0.47829f;
    private final boolean hidden;
    private final BaseKeyframeAnimation<?, Float> innerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> innerRoundednessAnimation;
    private boolean isPathValid;
    private final boolean isReversed;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<?, Float> outerRadiusAnimation;
    private final BaseKeyframeAnimation<?, Float> outerRoundednessAnimation;
    private final Path path = new Path();
    private final BaseKeyframeAnimation<?, Float> pointsAnimation;
    private final BaseKeyframeAnimation<?, PointF> positionAnimation;
    private final BaseKeyframeAnimation<?, Float> rotationAnimation;
    private final CompoundTrimPathContent trimPaths = new CompoundTrimPathContent();
    private final PolystarShape.Type type;

    public PolystarContent(LottieDrawable lottieDrawable2, BaseLayer layer, PolystarShape polystarShape) {
        this.lottieDrawable = lottieDrawable2;
        this.name = polystarShape.getName();
        PolystarShape.Type type2 = polystarShape.getType();
        this.type = type2;
        this.hidden = polystarShape.isHidden();
        this.isReversed = polystarShape.isReversed();
        BaseKeyframeAnimation<Float, Float> createAnimation = polystarShape.getPoints().createAnimation();
        this.pointsAnimation = createAnimation;
        BaseKeyframeAnimation<PointF, PointF> createAnimation2 = polystarShape.getPosition().createAnimation();
        this.positionAnimation = createAnimation2;
        BaseKeyframeAnimation<Float, Float> createAnimation3 = polystarShape.getRotation().createAnimation();
        this.rotationAnimation = createAnimation3;
        BaseKeyframeAnimation<Float, Float> createAnimation4 = polystarShape.getOuterRadius().createAnimation();
        this.outerRadiusAnimation = createAnimation4;
        BaseKeyframeAnimation<Float, Float> createAnimation5 = polystarShape.getOuterRoundedness().createAnimation();
        this.outerRoundednessAnimation = createAnimation5;
        if (type2 == PolystarShape.Type.STAR) {
            this.innerRadiusAnimation = polystarShape.getInnerRadius().createAnimation();
            this.innerRoundednessAnimation = polystarShape.getInnerRoundedness().createAnimation();
        } else {
            this.innerRadiusAnimation = null;
            this.innerRoundednessAnimation = null;
        }
        layer.addAnimation(createAnimation);
        layer.addAnimation(createAnimation2);
        layer.addAnimation(createAnimation3);
        layer.addAnimation(createAnimation4);
        layer.addAnimation(createAnimation5);
        if (type2 == PolystarShape.Type.STAR) {
            layer.addAnimation(this.innerRadiusAnimation);
            layer.addAnimation(this.innerRoundednessAnimation);
        }
        createAnimation.addUpdateListener(this);
        createAnimation2.addUpdateListener(this);
        createAnimation3.addUpdateListener(this);
        createAnimation4.addUpdateListener(this);
        createAnimation5.addUpdateListener(this);
        if (type2 == PolystarShape.Type.STAR) {
            this.innerRadiusAnimation.addUpdateListener(this);
            this.innerRoundednessAnimation.addUpdateListener(this);
        }
    }

    public void onValueChanged() {
        invalidate();
    }

    private void invalidate() {
        this.isPathValid = false;
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> contentsBefore, List<Content> list) {
        for (int i = 0; i < contentsBefore.size(); i++) {
            Content content = contentsBefore.get(i);
            if ((content instanceof TrimPathContent) && ((TrimPathContent) content).getType() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                TrimPathContent trimPath = (TrimPathContent) content;
                this.trimPaths.addTrimPath(trimPath);
                trimPath.addListener(this);
            }
        }
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
        switch (AnonymousClass1.$SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type[this.type.ordinal()]) {
            case 1:
                createStarPath();
                break;
            case 2:
                createPolygonPath();
                break;
        }
        this.path.close();
        this.trimPaths.apply(this.path);
        this.isPathValid = true;
        return this.path;
    }

    /* renamed from: com.airbnb.lottie.animation.content.PolystarContent$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type;

        static {
            int[] iArr = new int[PolystarShape.Type.values().length];
            $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type = iArr;
            try {
                iArr[PolystarShape.Type.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$content$PolystarShape$Type[PolystarShape.Type.POLYGON.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public String getName() {
        return this.name;
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x012f  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0242  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0244  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void createStarPath() {
        /*
            r47 = this;
            r0 = r47
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<?, java.lang.Float> r1 = r0.pointsAnimation
            java.lang.Object r1 = r1.getValue()
            java.lang.Float r1 = (java.lang.Float) r1
            float r1 = r1.floatValue()
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<?, java.lang.Float> r2 = r0.rotationAnimation
            if (r2 != 0) goto L_0x0015
            r2 = 0
            goto L_0x0020
        L_0x0015:
            java.lang.Object r2 = r2.getValue()
            java.lang.Float r2 = (java.lang.Float) r2
            float r2 = r2.floatValue()
            double r2 = (double) r2
        L_0x0020:
            r4 = 4636033603912859648(0x4056800000000000, double:90.0)
            double r2 = r2 - r4
            double r2 = java.lang.Math.toRadians(r2)
            r4 = 4618760256179416344(0x401921fb54442d18, double:6.283185307179586)
            double r6 = (double) r1
            double r4 = r4 / r6
            float r4 = (float) r4
            boolean r5 = r0.isReversed
            if (r5 == 0) goto L_0x003a
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r4 = r4 * r5
        L_0x003a:
            r5 = 1073741824(0x40000000, float:2.0)
            float r6 = r4 / r5
            int r7 = (int) r1
            float r7 = (float) r7
            float r7 = r1 - r7
            r8 = 0
            int r9 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r9 == 0) goto L_0x004e
            r9 = 1065353216(0x3f800000, float:1.0)
            float r9 = r9 - r7
            float r9 = r9 * r6
            double r9 = (double) r9
            double r2 = r2 + r9
        L_0x004e:
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<?, java.lang.Float> r9 = r0.outerRadiusAnimation
            java.lang.Object r9 = r9.getValue()
            java.lang.Float r9 = (java.lang.Float) r9
            float r9 = r9.floatValue()
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<?, java.lang.Float> r10 = r0.innerRadiusAnimation
            java.lang.Object r10 = r10.getValue()
            java.lang.Float r10 = (java.lang.Float) r10
            float r10 = r10.floatValue()
            r11 = 0
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<?, java.lang.Float> r12 = r0.innerRoundednessAnimation
            r13 = 1120403456(0x42c80000, float:100.0)
            if (r12 == 0) goto L_0x0079
            java.lang.Object r12 = r12.getValue()
            java.lang.Float r12 = (java.lang.Float) r12
            float r12 = r12.floatValue()
            float r11 = r12 / r13
        L_0x0079:
            r12 = 0
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<?, java.lang.Float> r14 = r0.outerRoundednessAnimation
            if (r14 == 0) goto L_0x008a
            java.lang.Object r14 = r14.getValue()
            java.lang.Float r14 = (java.lang.Float) r14
            float r14 = r14.floatValue()
            float r12 = r14 / r13
        L_0x008a:
            r13 = 0
            int r14 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r14 == 0) goto L_0x00b8
            float r14 = r9 - r10
            float r14 = r14 * r7
            float r13 = r10 + r14
            double r14 = (double) r13
            double r16 = java.lang.Math.cos(r2)
            double r14 = r14 * r16
            float r14 = (float) r14
            r16 = r9
            double r8 = (double) r13
            double r17 = java.lang.Math.sin(r2)
            double r8 = r8 * r17
            float r8 = (float) r8
            android.graphics.Path r9 = r0.path
            r9.moveTo(r14, r8)
            float r9 = r4 * r7
            float r9 = r9 / r5
            r18 = r6
            double r5 = (double) r9
            double r2 = r2 + r5
            r5 = r16
            r6 = r18
            goto L_0x00db
        L_0x00b8:
            r18 = r6
            r16 = r9
            r5 = r16
            double r8 = (double) r5
            double r19 = java.lang.Math.cos(r2)
            double r8 = r8 * r19
            float r14 = (float) r8
            double r8 = (double) r5
            double r19 = java.lang.Math.sin(r2)
            double r8 = r8 * r19
            float r8 = (float) r8
            android.graphics.Path r6 = r0.path
            r6.moveTo(r14, r8)
            r16 = r8
            r6 = r18
            double r8 = (double) r6
            double r2 = r2 + r8
            r8 = r16
        L_0x00db:
            r9 = 0
            r18 = r2
            double r2 = (double) r1
            double r2 = java.lang.Math.ceil(r2)
            r20 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r2 = r2 * r20
            r16 = 0
            r15 = r16
        L_0x00eb:
            r22 = r1
            double r0 = (double) r15
            int r23 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r23 >= 0) goto L_0x0259
            if (r9 == 0) goto L_0x00f6
            r0 = r5
            goto L_0x00f7
        L_0x00f6:
            r0 = r10
        L_0x00f7:
            r1 = r6
            r16 = 0
            int r23 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r23 == 0) goto L_0x0112
            r23 = r0
            r24 = r1
            double r0 = (double) r15
            double r25 = r2 - r20
            int r27 = (r0 > r25 ? 1 : (r0 == r25 ? 0 : -1))
            if (r27 != 0) goto L_0x010f
            float r0 = r4 * r7
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            goto L_0x011a
        L_0x010f:
            r1 = 1073741824(0x40000000, float:2.0)
            goto L_0x0118
        L_0x0112:
            r23 = r0
            r24 = r1
            r1 = 1073741824(0x40000000, float:2.0)
        L_0x0118:
            r0 = r24
        L_0x011a:
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r16 = 0
            int r17 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r17 == 0) goto L_0x012f
            r17 = r4
            r26 = r5
            double r4 = (double) r15
            double r27 = r2 - r24
            int r29 = (r4 > r27 ? 1 : (r4 == r27 ? 0 : -1))
            if (r29 != 0) goto L_0x0133
            r4 = r13
            goto L_0x0135
        L_0x012f:
            r17 = r4
            r26 = r5
        L_0x0133:
            r4 = r23
        L_0x0135:
            r5 = r14
            r23 = r8
            r27 = r2
            double r1 = (double) r4
            double r29 = java.lang.Math.cos(r18)
            double r1 = r1 * r29
            float r14 = (float) r1
            double r1 = (double) r4
            double r29 = java.lang.Math.sin(r18)
            double r1 = r1 * r29
            float r8 = (float) r1
            r1 = 0
            int r2 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r2 != 0) goto L_0x016a
            int r2 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r2 != 0) goto L_0x016a
            r2 = r47
            android.graphics.Path r1 = r2.path
            r1.lineTo(r14, r8)
            r39 = r0
            r36 = r10
            r37 = r11
            r38 = r12
            r40 = r23
            r16 = 0
            r23 = r4
            goto L_0x023b
        L_0x016a:
            r2 = r47
            r1 = r23
            r23 = r4
            double r3 = (double) r1
            r36 = r10
            r37 = r11
            double r10 = (double) r5
            double r3 = java.lang.Math.atan2(r3, r10)
            r10 = 4609753056924675352(0x3ff921fb54442d18, double:1.5707963267948966)
            double r3 = r3 - r10
            float r3 = (float) r3
            double r10 = (double) r3
            double r10 = java.lang.Math.cos(r10)
            float r4 = (float) r10
            double r10 = (double) r3
            double r10 = java.lang.Math.sin(r10)
            float r10 = (float) r10
            r38 = r12
            double r11 = (double) r8
            r39 = r0
            r40 = r1
            double r0 = (double) r14
            double r0 = java.lang.Math.atan2(r11, r0)
            r11 = 4609753056924675352(0x3ff921fb54442d18, double:1.5707963267948966)
            double r0 = r0 - r11
            float r0 = (float) r0
            double r11 = (double) r0
            double r11 = java.lang.Math.cos(r11)
            float r1 = (float) r11
            double r11 = (double) r0
            double r11 = java.lang.Math.sin(r11)
            float r11 = (float) r11
            if (r9 == 0) goto L_0x01b1
            r12 = r37
            goto L_0x01b3
        L_0x01b1:
            r12 = r38
        L_0x01b3:
            if (r9 == 0) goto L_0x01b8
            r29 = r38
            goto L_0x01ba
        L_0x01b8:
            r29 = r37
        L_0x01ba:
            r41 = r29
            if (r9 == 0) goto L_0x01c1
            r29 = r36
            goto L_0x01c3
        L_0x01c1:
            r29 = r26
        L_0x01c3:
            r42 = r29
            if (r9 == 0) goto L_0x01ca
            r29 = r26
            goto L_0x01cc
        L_0x01ca:
            r29 = r36
        L_0x01cc:
            r43 = r29
            float r29 = r42 * r12
            r30 = 1056236141(0x3ef4e26d, float:0.47829)
            float r29 = r29 * r30
            float r29 = r29 * r4
            float r31 = r42 * r12
            float r31 = r31 * r30
            float r31 = r31 * r10
            float r32 = r43 * r41
            float r32 = r32 * r30
            float r32 = r32 * r1
            float r33 = r43 * r41
            float r33 = r33 * r30
            float r33 = r33 * r11
            r16 = 0
            int r30 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x021a
            if (r15 != 0) goto L_0x0202
            float r29 = r29 * r7
            float r31 = r31 * r7
            r44 = r0
            r45 = r1
            r0 = r29
            r1 = r31
            r24 = r32
            r25 = r33
            goto L_0x0226
        L_0x0202:
            r44 = r0
            r45 = r1
            double r0 = (double) r15
            double r24 = r27 - r24
            int r30 = (r0 > r24 ? 1 : (r0 == r24 ? 0 : -1))
            if (r30 != 0) goto L_0x021e
            float r32 = r32 * r7
            float r33 = r33 * r7
            r0 = r29
            r1 = r31
            r24 = r32
            r25 = r33
            goto L_0x0226
        L_0x021a:
            r44 = r0
            r45 = r1
        L_0x021e:
            r0 = r29
            r1 = r31
            r24 = r32
            r25 = r33
        L_0x0226:
            r46 = r3
            android.graphics.Path r3 = r2.path
            float r30 = r5 - r0
            float r31 = r40 - r1
            float r32 = r14 + r24
            float r33 = r8 + r25
            r29 = r3
            r34 = r14
            r35 = r8
            r29.cubicTo(r30, r31, r32, r33, r34, r35)
        L_0x023b:
            r0 = r39
            double r3 = (double) r0
            double r18 = r18 + r3
            if (r9 != 0) goto L_0x0244
            r1 = 1
            goto L_0x0245
        L_0x0244:
            r1 = 0
        L_0x0245:
            r9 = r1
            int r15 = r15 + 1
            r0 = r2
            r4 = r17
            r1 = r22
            r5 = r26
            r2 = r27
            r10 = r36
            r11 = r37
            r12 = r38
            goto L_0x00eb
        L_0x0259:
            r27 = r2
            r17 = r4
            r2 = r47
            com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation<?, android.graphics.PointF> r0 = r2.positionAnimation
            java.lang.Object r0 = r0.getValue()
            android.graphics.PointF r0 = (android.graphics.PointF) r0
            android.graphics.Path r1 = r2.path
            float r3 = r0.x
            float r4 = r0.y
            r1.offset(r3, r4)
            android.graphics.Path r1 = r2.path
            r1.close()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.content.PolystarContent.createStarPath():void");
    }

    private void createPolygonPath() {
        float anglePerPoint;
        double currentAngle;
        double numPoints;
        int points;
        int points2 = (int) Math.floor((double) this.pointsAnimation.getValue().floatValue());
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation = this.rotationAnimation;
        double currentAngle2 = Math.toRadians((baseKeyframeAnimation == null ? 0.0d : (double) baseKeyframeAnimation.getValue().floatValue()) - 90.0d);
        float anglePerPoint2 = (float) (6.283185307179586d / ((double) points2));
        float roundedness = this.outerRoundednessAnimation.getValue().floatValue() / 100.0f;
        float radius = this.outerRadiusAnimation.getValue().floatValue();
        float x = (float) (((double) radius) * Math.cos(currentAngle2));
        float y = (float) (((double) radius) * Math.sin(currentAngle2));
        this.path.moveTo(x, y);
        double currentAngle3 = currentAngle2 + ((double) anglePerPoint2);
        double numPoints2 = Math.ceil((double) points2);
        int i = 0;
        while (((double) i) < numPoints2) {
            float previousX = x;
            float previousY = y;
            x = (float) (((double) radius) * Math.cos(currentAngle3));
            y = (float) (((double) radius) * Math.sin(currentAngle3));
            if (roundedness != 0.0f) {
                numPoints = numPoints2;
                float cp1Theta = (float) (Math.atan2((double) previousY, (double) previousX) - 1.5707963267948966d);
                float cp1Dx = (float) Math.cos((double) cp1Theta);
                points = points2;
                currentAngle = currentAngle3;
                anglePerPoint = anglePerPoint2;
                float cp2Theta = (float) (Math.atan2((double) y, (double) x) - 1.5707963267948966d);
                float f = cp2Theta;
                this.path.cubicTo(previousX - (((radius * roundedness) * POLYGON_MAGIC_NUMBER) * cp1Dx), previousY - (((radius * roundedness) * POLYGON_MAGIC_NUMBER) * ((float) Math.sin((double) cp1Theta))), x + (radius * roundedness * POLYGON_MAGIC_NUMBER * ((float) Math.cos((double) cp2Theta))), y + (radius * roundedness * POLYGON_MAGIC_NUMBER * ((float) Math.sin((double) cp2Theta))), x, y);
            } else {
                points = points2;
                currentAngle = currentAngle3;
                anglePerPoint = anglePerPoint2;
                numPoints = numPoints2;
                this.path.lineTo(x, y);
            }
            float anglePerPoint3 = anglePerPoint;
            currentAngle3 = currentAngle + ((double) anglePerPoint3);
            i++;
            anglePerPoint2 = anglePerPoint3;
            points2 = points;
            numPoints2 = numPoints;
        }
        double d = currentAngle3;
        float f2 = anglePerPoint2;
        double d2 = numPoints2;
        PointF position = this.positionAnimation.getValue();
        this.path.offset(position.x, position.y);
        this.path.close();
    }

    public void resolveKeyPath(KeyPath keyPath, int depth, List<KeyPath> accumulator, KeyPath currentPartialKeyPath) {
        MiscUtils.resolveKeyPath(keyPath, depth, accumulator, currentPartialKeyPath, this);
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation;
        BaseKeyframeAnimation<?, Float> baseKeyframeAnimation2;
        if (property == LottieProperty.POLYSTAR_POINTS) {
            this.pointsAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.POLYSTAR_ROTATION) {
            this.rotationAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.POSITION) {
            this.positionAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.POLYSTAR_INNER_RADIUS && (baseKeyframeAnimation2 = this.innerRadiusAnimation) != null) {
            baseKeyframeAnimation2.setValueCallback(callback);
        } else if (property == LottieProperty.POLYSTAR_OUTER_RADIUS) {
            this.outerRadiusAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.POLYSTAR_INNER_ROUNDEDNESS && (baseKeyframeAnimation = this.innerRoundednessAnimation) != null) {
            baseKeyframeAnimation.setValueCallback(callback);
        } else if (property == LottieProperty.POLYSTAR_OUTER_ROUNDEDNESS) {
            this.outerRoundednessAnimation.setValueCallback(callback);
        }
    }
}
