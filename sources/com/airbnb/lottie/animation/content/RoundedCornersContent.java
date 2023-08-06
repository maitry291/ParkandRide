package com.airbnb.lottie.animation.content;

import android.graphics.PointF;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.model.CubicCurveData;
import com.airbnb.lottie.model.content.RoundedCorners;
import com.airbnb.lottie.model.content.ShapeData;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.ArrayList;
import java.util.List;

public class RoundedCornersContent implements ShapeModifierContent, BaseKeyframeAnimation.AnimationListener {
    private static final float ROUNDED_CORNER_MAGIC_NUMBER = 0.5519f;
    private final LottieDrawable lottieDrawable;
    private final String name;
    private final BaseKeyframeAnimation<Float, Float> roundedCorners;
    private ShapeData shapeData;

    public RoundedCornersContent(LottieDrawable lottieDrawable2, BaseLayer layer, RoundedCorners roundedCorners2) {
        this.lottieDrawable = lottieDrawable2;
        this.name = roundedCorners2.getName();
        BaseKeyframeAnimation<Float, Float> createAnimation = roundedCorners2.getCornerRadius().createAnimation();
        this.roundedCorners = createAnimation;
        layer.addAnimation(createAnimation);
        createAnimation.addUpdateListener(this);
    }

    public String getName() {
        return this.name;
    }

    public void onValueChanged() {
        this.lottieDrawable.invalidateSelf();
    }

    public void setContents(List<Content> list, List<Content> list2) {
    }

    public BaseKeyframeAnimation<Float, Float> getRoundedCorners() {
        return this.roundedCorners;
    }

    public ShapeData modifyShape(ShapeData startingShapeData) {
        List<CubicCurveData> startingCurves;
        boolean isEndOfCurve;
        float roundedness;
        boolean isClosed;
        List<CubicCurveData> startingCurves2 = startingShapeData.getCurves();
        if (startingCurves2.size() <= 2) {
            return startingShapeData;
        }
        float roundedness2 = this.roundedCorners.getValue().floatValue();
        if (roundedness2 == 0.0f) {
            return startingShapeData;
        }
        ShapeData modifiedShapeData = getShapeData(startingShapeData);
        modifiedShapeData.setInitialPoint(startingShapeData.getInitialPoint().x, startingShapeData.getInitialPoint().y);
        List<CubicCurveData> modifiedCurves = modifiedShapeData.getCurves();
        int modifiedCurvesIndex = 0;
        boolean isClosed2 = startingShapeData.isClosed();
        int i = 0;
        while (i < startingCurves2.size()) {
            CubicCurveData startingCurve = startingCurves2.get(i);
            CubicCurveData previousCurve = startingCurves2.get(floorMod(i - 1, startingCurves2.size()));
            CubicCurveData previousPreviousCurve = startingCurves2.get(floorMod(i - 2, startingCurves2.size()));
            PointF vertex = (i != 0 || isClosed2) ? previousCurve.getVertex() : startingShapeData.getInitialPoint();
            PointF inPoint = (i != 0 || isClosed2) ? previousCurve.getControlPoint2() : vertex;
            PointF outPoint = startingCurve.getControlPoint1();
            PointF previousVertex = previousPreviousCurve.getVertex();
            PointF nextVertex = startingCurve.getVertex();
            if (startingShapeData.isClosed() || i != 0) {
                startingCurves = startingCurves2;
            } else {
                startingCurves = startingCurves2;
                if (i == startingCurves2.size() - 1) {
                    isEndOfCurve = true;
                    if (inPoint.equals(vertex) || outPoint.equals(vertex) || isEndOfCurve) {
                        roundedness = roundedness2;
                        isClosed = isClosed2;
                        CubicCurveData startingCurve2 = startingCurve;
                        CubicCurveData previousCurve2 = previousCurve;
                        CubicCurveData cubicCurveData = previousPreviousCurve;
                        PointF pointF = vertex;
                        PointF pointF2 = inPoint;
                        PointF pointF3 = outPoint;
                        CubicCurveData previousCurveData = modifiedCurves.get(floorMod(modifiedCurvesIndex - 1, modifiedCurves.size()));
                        previousCurveData.setControlPoint2(previousCurve2.getVertex().x, previousCurve2.getVertex().y);
                        previousCurveData.setVertex(previousCurve2.getVertex().x, previousCurve2.getVertex().y);
                        modifiedCurves.get(modifiedCurvesIndex).setControlPoint1(startingCurve2.getVertex().x, startingCurve2.getVertex().y);
                    } else {
                        boolean z = isEndOfCurve;
                        float dxToPreviousVertex = vertex.x - previousVertex.x;
                        isClosed = isClosed2;
                        float dyToPreviousVertex = vertex.y - previousVertex.y;
                        CubicCurveData cubicCurveData2 = previousPreviousCurve;
                        float dxToNextVertex = nextVertex.x - vertex.x;
                        PointF pointF4 = inPoint;
                        float dyToNextVertex = nextVertex.y - vertex.y;
                        PointF pointF5 = outPoint;
                        CubicCurveData cubicCurveData3 = startingCurve;
                        CubicCurveData cubicCurveData4 = previousCurve;
                        float dToPreviousVertex = (float) Math.hypot((double) dxToPreviousVertex, (double) dyToPreviousVertex);
                        float dxToPreviousVertex2 = dxToPreviousVertex;
                        float f = dyToPreviousVertex;
                        float dToNextVertex = (float) Math.hypot((double) dxToNextVertex, (double) dyToNextVertex);
                        float previousVertexPercent = Math.min(roundedness2 / dToPreviousVertex, 0.5f);
                        float nextVertexPercent = Math.min(roundedness2 / dToNextVertex, 0.5f);
                        float f2 = dToNextVertex;
                        roundedness = roundedness2;
                        float newVertex1X = vertex.x + ((previousVertex.x - vertex.x) * previousVertexPercent);
                        float f3 = dxToNextVertex;
                        float newVertex1Y = vertex.y + ((previousVertex.y - vertex.y) * previousVertexPercent);
                        float f4 = previousVertexPercent;
                        float newVertex2X = vertex.x + ((nextVertex.x - vertex.x) * nextVertexPercent);
                        float f5 = dToPreviousVertex;
                        float newVertex2Y = vertex.y + ((nextVertex.y - vertex.y) * nextVertexPercent);
                        float newVertex1OutPointX = newVertex1X - ((newVertex1X - vertex.x) * ROUNDED_CORNER_MAGIC_NUMBER);
                        float f6 = dxToPreviousVertex2;
                        float newVertex1OutPointY = newVertex1Y - ((newVertex1Y - vertex.y) * ROUNDED_CORNER_MAGIC_NUMBER);
                        float f7 = dyToNextVertex;
                        float newVertex2InPointX = newVertex2X - ((newVertex2X - vertex.x) * ROUNDED_CORNER_MAGIC_NUMBER);
                        float f8 = nextVertexPercent;
                        float newVertex2InPointY = newVertex2Y - ((newVertex2Y - vertex.y) * ROUNDED_CORNER_MAGIC_NUMBER);
                        PointF pointF6 = vertex;
                        CubicCurveData previousCurveData2 = modifiedCurves.get(floorMod(modifiedCurvesIndex - 1, modifiedCurves.size()));
                        CubicCurveData currentCurveData = modifiedCurves.get(modifiedCurvesIndex);
                        previousCurveData2.setControlPoint2(newVertex1X, newVertex1Y);
                        previousCurveData2.setVertex(newVertex1X, newVertex1Y);
                        if (i == 0) {
                            modifiedShapeData.setInitialPoint(newVertex1X, newVertex1Y);
                        }
                        currentCurveData.setControlPoint1(newVertex1OutPointX, newVertex1OutPointY);
                        modifiedCurvesIndex++;
                        CubicCurveData previousCurveData3 = currentCurveData;
                        previousCurveData3.setControlPoint2(newVertex2InPointX, newVertex2InPointY);
                        previousCurveData3.setVertex(newVertex2X, newVertex2Y);
                        modifiedCurves.get(modifiedCurvesIndex).setControlPoint1(newVertex2X, newVertex2Y);
                    }
                    modifiedCurvesIndex++;
                    i++;
                    startingCurves2 = startingCurves;
                    isClosed2 = isClosed;
                    roundedness2 = roundedness;
                }
            }
            isEndOfCurve = false;
            if (inPoint.equals(vertex) && outPoint.equals(vertex)) {
            }
            roundedness = roundedness2;
            isClosed = isClosed2;
            CubicCurveData startingCurve22 = startingCurve;
            CubicCurveData previousCurve22 = previousCurve;
            CubicCurveData cubicCurveData5 = previousPreviousCurve;
            PointF pointF7 = vertex;
            PointF pointF22 = inPoint;
            PointF pointF32 = outPoint;
            CubicCurveData previousCurveData4 = modifiedCurves.get(floorMod(modifiedCurvesIndex - 1, modifiedCurves.size()));
            previousCurveData4.setControlPoint2(previousCurve22.getVertex().x, previousCurve22.getVertex().y);
            previousCurveData4.setVertex(previousCurve22.getVertex().x, previousCurve22.getVertex().y);
            modifiedCurves.get(modifiedCurvesIndex).setControlPoint1(startingCurve22.getVertex().x, startingCurve22.getVertex().y);
            modifiedCurvesIndex++;
            i++;
            startingCurves2 = startingCurves;
            isClosed2 = isClosed;
            roundedness2 = roundedness;
        }
        return modifiedShapeData;
    }

    private ShapeData getShapeData(ShapeData startingShapeData) {
        List<CubicCurveData> startingCurves = startingShapeData.getCurves();
        boolean isClosed = startingShapeData.isClosed();
        int vertices = 0;
        int i = startingCurves.size() - 1;
        while (true) {
            boolean isEndOfCurve = false;
            if (i < 0) {
                break;
            }
            CubicCurveData startingCurve = startingCurves.get(i);
            CubicCurveData previousCurve = startingCurves.get(floorMod(i - 1, startingCurves.size()));
            PointF vertex = (i != 0 || isClosed) ? previousCurve.getVertex() : startingShapeData.getInitialPoint();
            PointF inPoint = (i != 0 || isClosed) ? previousCurve.getControlPoint2() : vertex;
            PointF outPoint = startingCurve.getControlPoint1();
            if (!startingShapeData.isClosed() && i == 0 && i == startingCurves.size() - 1) {
                isEndOfCurve = true;
            }
            if (!inPoint.equals(vertex) || !outPoint.equals(vertex) || isEndOfCurve) {
                vertices++;
            } else {
                vertices += 2;
            }
            i--;
        }
        ShapeData shapeData2 = this.shapeData;
        if (shapeData2 == null || shapeData2.getCurves().size() != vertices) {
            List<CubicCurveData> newCurves = new ArrayList<>(vertices);
            for (int i2 = 0; i2 < vertices; i2++) {
                newCurves.add(new CubicCurveData());
            }
            this.shapeData = new ShapeData(new PointF(0.0f, 0.0f), false, newCurves);
        }
        this.shapeData.setClosed(isClosed);
        return this.shapeData;
    }

    private static int floorMod(int x, int y) {
        return x - (floorDiv(x, y) * y);
    }

    private static int floorDiv(int x, int y) {
        int r = x / y;
        if ((x ^ y) >= 0 || r * y == x) {
            return r;
        }
        return r - 1;
    }
}
