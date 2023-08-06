package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class TextKeyframeAnimation extends KeyframeAnimation<DocumentData> {
    public TextKeyframeAnimation(List<Keyframe<DocumentData>> keyframes) {
        super(keyframes);
    }

    /* access modifiers changed from: package-private */
    public DocumentData getValue(Keyframe<DocumentData> keyframe, float keyframeProgress) {
        if (this.valueCallback != null) {
            return (DocumentData) this.valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame == null ? Float.MAX_VALUE : keyframe.endFrame.floatValue(), (DocumentData) keyframe.startValue, (DocumentData) (keyframe.endValue == null ? keyframe.startValue : keyframe.endValue), keyframeProgress, getInterpolatedCurrentKeyframeProgress(), getProgress());
        } else if (keyframeProgress != 1.0f || keyframe.endValue == null) {
            return (DocumentData) keyframe.startValue;
        } else {
            return (DocumentData) keyframe.endValue;
        }
    }

    public void setStringValueCallback(final LottieValueCallback<String> valueCallback) {
        final LottieFrameInfo<String> stringFrameInfo = new LottieFrameInfo<>();
        final DocumentData documentData = new DocumentData();
        super.setValueCallback(new LottieValueCallback<DocumentData>() {
            /* JADX WARNING: type inference failed for: r15v0, types: [com.airbnb.lottie.value.LottieFrameInfo, com.airbnb.lottie.value.LottieFrameInfo<com.airbnb.lottie.model.DocumentData>] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public com.airbnb.lottie.model.DocumentData getValue(com.airbnb.lottie.value.LottieFrameInfo<com.airbnb.lottie.model.DocumentData> r15) {
                /*
                    r14 = this;
                    com.airbnb.lottie.value.LottieFrameInfo r0 = r0
                    float r1 = r15.getStartFrame()
                    float r2 = r15.getEndFrame()
                    java.lang.Object r3 = r15.getStartValue()
                    com.airbnb.lottie.model.DocumentData r3 = (com.airbnb.lottie.model.DocumentData) r3
                    java.lang.String r3 = r3.text
                    java.lang.Object r4 = r15.getEndValue()
                    com.airbnb.lottie.model.DocumentData r4 = (com.airbnb.lottie.model.DocumentData) r4
                    java.lang.String r4 = r4.text
                    float r5 = r15.getLinearKeyframeProgress()
                    float r6 = r15.getInterpolatedKeyframeProgress()
                    float r7 = r15.getOverallProgress()
                    r0.set(r1, r2, r3, r4, r5, r6, r7)
                    com.airbnb.lottie.value.LottieValueCallback r0 = r4
                    com.airbnb.lottie.value.LottieFrameInfo r1 = r0
                    java.lang.Object r0 = r0.getValue(r1)
                    java.lang.String r0 = (java.lang.String) r0
                    float r1 = r15.getInterpolatedKeyframeProgress()
                    r2 = 1065353216(0x3f800000, float:1.0)
                    int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                    if (r1 != 0) goto L_0x0042
                    java.lang.Object r1 = r15.getEndValue()
                    goto L_0x0046
                L_0x0042:
                    java.lang.Object r1 = r15.getStartValue()
                L_0x0046:
                    com.airbnb.lottie.model.DocumentData r1 = (com.airbnb.lottie.model.DocumentData) r1
                    r13 = r1
                    com.airbnb.lottie.model.DocumentData r1 = r1
                    java.lang.String r3 = r13.fontName
                    float r4 = r13.size
                    com.airbnb.lottie.model.DocumentData$Justification r5 = r13.justification
                    int r6 = r13.tracking
                    float r7 = r13.lineHeight
                    float r8 = r13.baselineShift
                    int r9 = r13.color
                    int r10 = r13.strokeColor
                    float r11 = r13.strokeWidth
                    boolean r12 = r13.strokeOverFill
                    r2 = r0
                    r1.set(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
                    com.airbnb.lottie.model.DocumentData r1 = r1
                    return r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation.AnonymousClass1.getValue(com.airbnb.lottie.value.LottieFrameInfo):com.airbnb.lottie.model.DocumentData");
            }
        });
    }
}
