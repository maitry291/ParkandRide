package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.TextDelegate;
import com.airbnb.lottie.animation.content.ContentGroup;
import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ValueCallbackKeyframeAnimation;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.Font;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextLayer extends BaseLayer {
    private final LongSparseArray<String> codePointCache = new LongSparseArray<>();
    private BaseKeyframeAnimation<Integer, Integer> colorAnimation;
    private BaseKeyframeAnimation<Integer, Integer> colorCallbackAnimation;
    private final LottieComposition composition;
    private final Map<FontCharacter, List<ContentGroup>> contentsForCharacter = new HashMap();
    private final Paint fillPaint = new Paint(1) {
        {
            setStyle(Paint.Style.FILL);
        }
    };
    private final LottieDrawable lottieDrawable;
    private final Matrix matrix = new Matrix();
    private final RectF rectF = new RectF();
    private final StringBuilder stringBuilder = new StringBuilder(2);
    private BaseKeyframeAnimation<Integer, Integer> strokeColorAnimation;
    private BaseKeyframeAnimation<Integer, Integer> strokeColorCallbackAnimation;
    private final Paint strokePaint = new Paint(1) {
        {
            setStyle(Paint.Style.STROKE);
        }
    };
    private BaseKeyframeAnimation<Float, Float> strokeWidthAnimation;
    private BaseKeyframeAnimation<Float, Float> strokeWidthCallbackAnimation;
    private final TextKeyframeAnimation textAnimation;
    private BaseKeyframeAnimation<Float, Float> textSizeCallbackAnimation;
    private BaseKeyframeAnimation<Float, Float> trackingAnimation;
    private BaseKeyframeAnimation<Float, Float> trackingCallbackAnimation;
    private BaseKeyframeAnimation<Typeface, Typeface> typefaceCallbackAnimation;

    TextLayer(LottieDrawable lottieDrawable2, Layer layerModel) {
        super(lottieDrawable2, layerModel);
        this.lottieDrawable = lottieDrawable2;
        this.composition = layerModel.getComposition();
        TextKeyframeAnimation createAnimation = layerModel.getText().createAnimation();
        this.textAnimation = createAnimation;
        createAnimation.addUpdateListener(this);
        addAnimation(createAnimation);
        AnimatableTextProperties textProperties = layerModel.getTextProperties();
        if (!(textProperties == null || textProperties.color == null)) {
            BaseKeyframeAnimation<Integer, Integer> createAnimation2 = textProperties.color.createAnimation();
            this.colorAnimation = createAnimation2;
            createAnimation2.addUpdateListener(this);
            addAnimation(this.colorAnimation);
        }
        if (!(textProperties == null || textProperties.stroke == null)) {
            BaseKeyframeAnimation<Integer, Integer> createAnimation3 = textProperties.stroke.createAnimation();
            this.strokeColorAnimation = createAnimation3;
            createAnimation3.addUpdateListener(this);
            addAnimation(this.strokeColorAnimation);
        }
        if (!(textProperties == null || textProperties.strokeWidth == null)) {
            BaseKeyframeAnimation<Float, Float> createAnimation4 = textProperties.strokeWidth.createAnimation();
            this.strokeWidthAnimation = createAnimation4;
            createAnimation4.addUpdateListener(this);
            addAnimation(this.strokeWidthAnimation);
        }
        if (textProperties != null && textProperties.tracking != null) {
            BaseKeyframeAnimation<Float, Float> createAnimation5 = textProperties.tracking.createAnimation();
            this.trackingAnimation = createAnimation5;
            createAnimation5.addUpdateListener(this);
            addAnimation(this.trackingAnimation);
        }
    }

    public void getBounds(RectF outBounds, Matrix parentMatrix, boolean applyParents) {
        super.getBounds(outBounds, parentMatrix, applyParents);
        outBounds.set(0.0f, 0.0f, (float) this.composition.getBounds().width(), (float) this.composition.getBounds().height());
    }

    /* access modifiers changed from: package-private */
    public void drawLayer(Canvas canvas, Matrix parentMatrix, int parentAlpha) {
        canvas.save();
        if (!this.lottieDrawable.useTextGlyphs()) {
            canvas.concat(parentMatrix);
        }
        DocumentData documentData = (DocumentData) this.textAnimation.getValue();
        Font font = this.composition.getFonts().get(documentData.fontName);
        if (font == null) {
            canvas.restore();
            return;
        }
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.colorCallbackAnimation;
        if (baseKeyframeAnimation != null) {
            this.fillPaint.setColor(baseKeyframeAnimation.getValue().intValue());
        } else {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.colorAnimation;
            if (baseKeyframeAnimation2 != null) {
                this.fillPaint.setColor(baseKeyframeAnimation2.getValue().intValue());
            } else {
                this.fillPaint.setColor(documentData.color);
            }
        }
        BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation3 = this.strokeColorCallbackAnimation;
        if (baseKeyframeAnimation3 != null) {
            this.strokePaint.setColor(baseKeyframeAnimation3.getValue().intValue());
        } else {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation4 = this.strokeColorAnimation;
            if (baseKeyframeAnimation4 != null) {
                this.strokePaint.setColor(baseKeyframeAnimation4.getValue().intValue());
            } else {
                this.strokePaint.setColor(documentData.strokeColor);
            }
        }
        int alpha = ((this.transform.getOpacity() == null ? 100 : this.transform.getOpacity().getValue().intValue()) * 255) / 100;
        this.fillPaint.setAlpha(alpha);
        this.strokePaint.setAlpha(alpha);
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation5 = this.strokeWidthCallbackAnimation;
        if (baseKeyframeAnimation5 != null) {
            this.strokePaint.setStrokeWidth(baseKeyframeAnimation5.getValue().floatValue());
        } else {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation6 = this.strokeWidthAnimation;
            if (baseKeyframeAnimation6 != null) {
                this.strokePaint.setStrokeWidth(baseKeyframeAnimation6.getValue().floatValue());
            } else {
                this.strokePaint.setStrokeWidth(documentData.strokeWidth * Utils.dpScale() * Utils.getScale(parentMatrix));
            }
        }
        if (this.lottieDrawable.useTextGlyphs()) {
            drawTextGlyphs(documentData, parentMatrix, font, canvas);
        } else {
            drawTextWithFont(documentData, font, canvas);
        }
        canvas.restore();
    }

    private void drawTextGlyphs(DocumentData documentData, Matrix parentMatrix, Font font, Canvas canvas) {
        float textSize;
        DocumentData documentData2 = documentData;
        Canvas canvas2 = canvas;
        BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation = this.textSizeCallbackAnimation;
        if (baseKeyframeAnimation != null) {
            textSize = baseKeyframeAnimation.getValue().floatValue();
        } else {
            textSize = documentData2.size;
        }
        float fontScale = textSize / 100.0f;
        float parentScale = Utils.getScale(parentMatrix);
        String text = documentData2.text;
        float lineHeight = documentData2.lineHeight * Utils.dpScale();
        List<String> textLines = getTextLines(text);
        int textLineCount = textLines.size();
        int l = 0;
        while (l < textLineCount) {
            String textLine = textLines.get(l);
            float textLineWidth = getTextLineWidthForGlyphs(textLine, font, fontScale, parentScale);
            canvas.save();
            applyJustification(documentData2.justification, canvas2, textLineWidth);
            float translateY = (((float) l) * lineHeight) - ((((float) (textLineCount - 1)) * lineHeight) / 2.0f);
            canvas2.translate(0.0f, translateY);
            float f = translateY;
            float f2 = textLineWidth;
            String str = textLine;
            drawGlyphTextLine(textLine, documentData, parentMatrix, font, canvas, parentScale, fontScale);
            canvas.restore();
            l++;
            textLineCount = textLineCount;
            textLines = textLines;
        }
    }

    private void drawGlyphTextLine(String text, DocumentData documentData, Matrix parentMatrix, Font font, Canvas canvas, float parentScale, float fontScale) {
        for (int i = 0; i < text.length(); i++) {
            String str = text;
            FontCharacter character = this.composition.getCharacters().get(FontCharacter.hashFor(text.charAt(i), font.getFamily(), font.getStyle()));
            if (character == null) {
                DocumentData documentData2 = documentData;
                Canvas canvas2 = canvas;
            } else {
                drawCharacterAsGlyph(character, parentMatrix, fontScale, documentData, canvas);
                float tx = ((float) character.getWidth()) * fontScale * Utils.dpScale() * parentScale;
                float tracking = ((float) documentData.tracking) / 10.0f;
                BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation = this.trackingCallbackAnimation;
                if (baseKeyframeAnimation != null) {
                    tracking += baseKeyframeAnimation.getValue().floatValue();
                } else {
                    BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.trackingAnimation;
                    if (baseKeyframeAnimation2 != null) {
                        tracking += baseKeyframeAnimation2.getValue().floatValue();
                    }
                }
                canvas.translate(tx + (tracking * parentScale), 0.0f);
            }
        }
        String str2 = text;
        DocumentData documentData3 = documentData;
        Canvas canvas3 = canvas;
    }

    private void drawTextWithFont(DocumentData documentData, Font font, Canvas canvas) {
        float textSize;
        DocumentData documentData2 = documentData;
        Canvas canvas2 = canvas;
        Typeface typeface = getTypeface(font);
        if (typeface != null) {
            String text = documentData2.text;
            TextDelegate textDelegate = this.lottieDrawable.getTextDelegate();
            if (textDelegate != null) {
                text = textDelegate.getTextInternal(getName(), text);
            }
            this.fillPaint.setTypeface(typeface);
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation = this.textSizeCallbackAnimation;
            if (baseKeyframeAnimation != null) {
                textSize = baseKeyframeAnimation.getValue().floatValue();
            } else {
                textSize = documentData2.size;
            }
            this.fillPaint.setTextSize(Utils.dpScale() * textSize);
            this.strokePaint.setTypeface(this.fillPaint.getTypeface());
            this.strokePaint.setTextSize(this.fillPaint.getTextSize());
            float lineHeight = documentData2.lineHeight * Utils.dpScale();
            float tracking = ((float) documentData2.tracking) / 10.0f;
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2 = this.trackingCallbackAnimation;
            if (baseKeyframeAnimation2 != null) {
                tracking += baseKeyframeAnimation2.getValue().floatValue();
            } else {
                BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation3 = this.trackingAnimation;
                if (baseKeyframeAnimation3 != null) {
                    tracking += baseKeyframeAnimation3.getValue().floatValue();
                }
            }
            float tracking2 = ((Utils.dpScale() * tracking) * textSize) / 100.0f;
            List<String> textLines = getTextLines(text);
            int textLineCount = textLines.size();
            int l = 0;
            while (l < textLineCount) {
                String textLine = textLines.get(l);
                float textLineWidth = this.strokePaint.measureText(textLine) + (((float) (textLine.length() - 1)) * tracking2);
                canvas.save();
                applyJustification(documentData2.justification, canvas2, textLineWidth);
                canvas2.translate(0.0f, (((float) l) * lineHeight) - ((((float) (textLineCount - 1)) * lineHeight) / 2.0f));
                drawFontTextLine(textLine, documentData2, canvas2, tracking2);
                canvas.restore();
                l++;
                Font font2 = font;
                typeface = typeface;
            }
        }
    }

    private Typeface getTypeface(Font font) {
        Typeface callbackTypeface;
        BaseKeyframeAnimation<Typeface, Typeface> baseKeyframeAnimation = this.typefaceCallbackAnimation;
        if (baseKeyframeAnimation != null && (callbackTypeface = baseKeyframeAnimation.getValue()) != null) {
            return callbackTypeface;
        }
        Typeface drawableTypeface = this.lottieDrawable.getTypeface(font.getFamily(), font.getStyle());
        if (drawableTypeface != null) {
            return drawableTypeface;
        }
        return font.getTypeface();
    }

    private List<String> getTextLines(String text) {
        return Arrays.asList(text.replaceAll("\r\n", "\r").replaceAll("\n", "\r").split("\r"));
    }

    private void drawFontTextLine(String text, DocumentData documentData, Canvas canvas, float tracking) {
        int i = 0;
        while (i < text.length()) {
            String charString = codePointToString(text, i);
            i += charString.length();
            drawCharacterFromFont(charString, documentData, canvas);
            canvas.translate(this.fillPaint.measureText(charString) + tracking, 0.0f);
        }
    }

    private float getTextLineWidthForGlyphs(String textLine, Font font, float fontScale, float parentScale) {
        float textLineWidth = 0.0f;
        for (int i = 0; i < textLine.length(); i++) {
            FontCharacter character = this.composition.getCharacters().get(FontCharacter.hashFor(textLine.charAt(i), font.getFamily(), font.getStyle()));
            if (character != null) {
                textLineWidth = (float) (((double) textLineWidth) + (character.getWidth() * ((double) fontScale) * ((double) Utils.dpScale()) * ((double) parentScale)));
            }
        }
        return textLineWidth;
    }

    /* renamed from: com.airbnb.lottie.model.layer.TextLayer$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification;

        static {
            int[] iArr = new int[DocumentData.Justification.values().length];
            $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification = iArr;
            try {
                iArr[DocumentData.Justification.LEFT_ALIGN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[DocumentData.Justification.RIGHT_ALIGN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[DocumentData.Justification.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private void applyJustification(DocumentData.Justification justification, Canvas canvas, float textLineWidth) {
        switch (AnonymousClass3.$SwitchMap$com$airbnb$lottie$model$DocumentData$Justification[justification.ordinal()]) {
            case 2:
                canvas.translate(-textLineWidth, 0.0f);
                return;
            case 3:
                canvas.translate((-textLineWidth) / 2.0f, 0.0f);
                return;
            default:
                return;
        }
    }

    private void drawCharacterAsGlyph(FontCharacter character, Matrix parentMatrix, float fontScale, DocumentData documentData, Canvas canvas) {
        List<ContentGroup> contentGroups = getContentsForCharacter(character);
        for (int j = 0; j < contentGroups.size(); j++) {
            Path path = contentGroups.get(j).getPath();
            path.computeBounds(this.rectF, false);
            this.matrix.set(parentMatrix);
            this.matrix.preTranslate(0.0f, (-documentData.baselineShift) * Utils.dpScale());
            this.matrix.preScale(fontScale, fontScale);
            path.transform(this.matrix);
            if (documentData.strokeOverFill) {
                drawGlyph(path, this.fillPaint, canvas);
                drawGlyph(path, this.strokePaint, canvas);
            } else {
                drawGlyph(path, this.strokePaint, canvas);
                drawGlyph(path, this.fillPaint, canvas);
            }
        }
    }

    private void drawGlyph(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() != 0) {
            if (paint.getStyle() != Paint.Style.STROKE || paint.getStrokeWidth() != 0.0f) {
                canvas.drawPath(path, paint);
            }
        }
    }

    private void drawCharacterFromFont(String character, DocumentData documentData, Canvas canvas) {
        if (documentData.strokeOverFill) {
            drawCharacter(character, this.fillPaint, canvas);
            drawCharacter(character, this.strokePaint, canvas);
            return;
        }
        drawCharacter(character, this.strokePaint, canvas);
        drawCharacter(character, this.fillPaint, canvas);
    }

    private void drawCharacter(String character, Paint paint, Canvas canvas) {
        if (paint.getColor() != 0) {
            if (paint.getStyle() != Paint.Style.STROKE || paint.getStrokeWidth() != 0.0f) {
                canvas.drawText(character, 0, character.length(), 0.0f, 0.0f, paint);
            }
        }
    }

    private List<ContentGroup> getContentsForCharacter(FontCharacter character) {
        if (this.contentsForCharacter.containsKey(character)) {
            return this.contentsForCharacter.get(character);
        }
        List<ShapeGroup> shapes = character.getShapes();
        int size = shapes.size();
        List<ContentGroup> contents = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            contents.add(new ContentGroup(this.lottieDrawable, this, shapes.get(i)));
        }
        this.contentsForCharacter.put(character, contents);
        return contents;
    }

    private String codePointToString(String text, int startIndex) {
        int firstCodePoint = text.codePointAt(startIndex);
        int key = firstCodePoint;
        int index = startIndex + Character.charCount(firstCodePoint);
        while (index < text.length()) {
            int nextCodePoint = text.codePointAt(index);
            if (!isModifier(nextCodePoint)) {
                break;
            }
            index += Character.charCount(nextCodePoint);
            key = (key * 31) + nextCodePoint;
        }
        if (this.codePointCache.containsKey((long) key)) {
            return this.codePointCache.get((long) key);
        }
        this.stringBuilder.setLength(0);
        int i = startIndex;
        while (i < index) {
            int codePoint = text.codePointAt(i);
            this.stringBuilder.appendCodePoint(codePoint);
            i += Character.charCount(codePoint);
        }
        String str = this.stringBuilder.toString();
        this.codePointCache.put((long) key, str);
        return str;
    }

    private boolean isModifier(int codePoint) {
        return Character.getType(codePoint) == 16 || Character.getType(codePoint) == 27 || Character.getType(codePoint) == 6 || Character.getType(codePoint) == 28 || Character.getType(codePoint) == 8 || Character.getType(codePoint) == 19;
    }

    public <T> void addValueCallback(T property, LottieValueCallback<T> callback) {
        super.addValueCallback(property, callback);
        if (property == LottieProperty.COLOR) {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation = this.colorCallbackAnimation;
            if (baseKeyframeAnimation != null) {
                removeAnimation(baseKeyframeAnimation);
            }
            if (callback == null) {
                this.colorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation = new ValueCallbackKeyframeAnimation(callback);
            this.colorCallbackAnimation = valueCallbackKeyframeAnimation;
            valueCallbackKeyframeAnimation.addUpdateListener(this);
            addAnimation(this.colorCallbackAnimation);
        } else if (property == LottieProperty.STROKE_COLOR) {
            BaseKeyframeAnimation<Integer, Integer> baseKeyframeAnimation2 = this.strokeColorCallbackAnimation;
            if (baseKeyframeAnimation2 != null) {
                removeAnimation(baseKeyframeAnimation2);
            }
            if (callback == null) {
                this.strokeColorCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation2 = new ValueCallbackKeyframeAnimation(callback);
            this.strokeColorCallbackAnimation = valueCallbackKeyframeAnimation2;
            valueCallbackKeyframeAnimation2.addUpdateListener(this);
            addAnimation(this.strokeColorCallbackAnimation);
        } else if (property == LottieProperty.STROKE_WIDTH) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation3 = this.strokeWidthCallbackAnimation;
            if (baseKeyframeAnimation3 != null) {
                removeAnimation(baseKeyframeAnimation3);
            }
            if (callback == null) {
                this.strokeWidthCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation3 = new ValueCallbackKeyframeAnimation(callback);
            this.strokeWidthCallbackAnimation = valueCallbackKeyframeAnimation3;
            valueCallbackKeyframeAnimation3.addUpdateListener(this);
            addAnimation(this.strokeWidthCallbackAnimation);
        } else if (property == LottieProperty.TEXT_TRACKING) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation4 = this.trackingCallbackAnimation;
            if (baseKeyframeAnimation4 != null) {
                removeAnimation(baseKeyframeAnimation4);
            }
            if (callback == null) {
                this.trackingCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation4 = new ValueCallbackKeyframeAnimation(callback);
            this.trackingCallbackAnimation = valueCallbackKeyframeAnimation4;
            valueCallbackKeyframeAnimation4.addUpdateListener(this);
            addAnimation(this.trackingCallbackAnimation);
        } else if (property == LottieProperty.TEXT_SIZE) {
            BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation5 = this.textSizeCallbackAnimation;
            if (baseKeyframeAnimation5 != null) {
                removeAnimation(baseKeyframeAnimation5);
            }
            if (callback == null) {
                this.textSizeCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation5 = new ValueCallbackKeyframeAnimation(callback);
            this.textSizeCallbackAnimation = valueCallbackKeyframeAnimation5;
            valueCallbackKeyframeAnimation5.addUpdateListener(this);
            addAnimation(this.textSizeCallbackAnimation);
        } else if (property == LottieProperty.TYPEFACE) {
            BaseKeyframeAnimation<Typeface, Typeface> baseKeyframeAnimation6 = this.typefaceCallbackAnimation;
            if (baseKeyframeAnimation6 != null) {
                removeAnimation(baseKeyframeAnimation6);
            }
            if (callback == null) {
                this.typefaceCallbackAnimation = null;
                return;
            }
            ValueCallbackKeyframeAnimation valueCallbackKeyframeAnimation6 = new ValueCallbackKeyframeAnimation(callback);
            this.typefaceCallbackAnimation = valueCallbackKeyframeAnimation6;
            valueCallbackKeyframeAnimation6.addUpdateListener(this);
            addAnimation(this.typefaceCallbackAnimation);
        } else if (property == LottieProperty.TEXT) {
            this.textAnimation.setStringValueCallback(callback);
        }
    }
}
