package com.airbnb.lottie.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Build;
import android.provider.Settings;
import com.airbnb.lottie.L;
import com.airbnb.lottie.animation.LPaint;
import com.airbnb.lottie.animation.content.TrimPathContent;
import com.airbnb.lottie.animation.keyframe.FloatKeyframeAnimation;
import java.io.Closeable;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import java.nio.channels.ClosedChannelException;
import javax.net.ssl.SSLException;

public final class Utils {
    private static final float INV_SQRT_2 = ((float) (Math.sqrt(2.0d) / 2.0d));
    public static final int SECOND_IN_NANOS = 1000000000;
    private static final ThreadLocal<PathMeasure> threadLocalPathMeasure = new ThreadLocal<PathMeasure>() {
        /* access modifiers changed from: protected */
        public PathMeasure initialValue() {
            return new PathMeasure();
        }
    };
    private static final ThreadLocal<float[]> threadLocalPoints = new ThreadLocal<float[]>() {
        /* access modifiers changed from: protected */
        public float[] initialValue() {
            return new float[4];
        }
    };
    private static final ThreadLocal<Path> threadLocalTempPath = new ThreadLocal<Path>() {
        /* access modifiers changed from: protected */
        public Path initialValue() {
            return new Path();
        }
    };
    private static final ThreadLocal<Path> threadLocalTempPath2 = new ThreadLocal<Path>() {
        /* access modifiers changed from: protected */
        public Path initialValue() {
            return new Path();
        }
    };

    private Utils() {
    }

    public static Path createPath(PointF startPoint, PointF endPoint, PointF cp1, PointF cp2) {
        Path path = new Path();
        path.moveTo(startPoint.x, startPoint.y);
        if (cp1 == null || cp2 == null || (cp1.length() == 0.0f && cp2.length() == 0.0f)) {
            path.lineTo(endPoint.x, endPoint.y);
        } else {
            Path path2 = path;
            path2.cubicTo(cp1.x + startPoint.x, cp1.y + startPoint.y, cp2.x + endPoint.x, cp2.y + endPoint.y, endPoint.x, endPoint.y);
        }
        return path;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }

    public static float getScale(Matrix matrix) {
        float[] points = threadLocalPoints.get();
        points[0] = 0.0f;
        points[1] = 0.0f;
        float f = INV_SQRT_2;
        points[2] = f;
        points[3] = f;
        matrix.mapPoints(points);
        return (float) Math.hypot((double) (points[2] - points[0]), (double) (points[3] - points[1]));
    }

    public static boolean hasZeroScaleAxis(Matrix matrix) {
        float[] points = threadLocalPoints.get();
        points[0] = 0.0f;
        points[1] = 0.0f;
        points[2] = 37394.73f;
        points[3] = 39575.234f;
        matrix.mapPoints(points);
        if (points[0] == points[2] || points[1] == points[3]) {
            return true;
        }
        return false;
    }

    public static void applyTrimPathIfNeeded(Path path, TrimPathContent trimPath) {
        if (trimPath != null && !trimPath.isHidden()) {
            applyTrimPathIfNeeded(path, ((FloatKeyframeAnimation) trimPath.getStart()).getFloatValue() / 100.0f, ((FloatKeyframeAnimation) trimPath.getEnd()).getFloatValue() / 100.0f, ((FloatKeyframeAnimation) trimPath.getOffset()).getFloatValue() / 360.0f);
        }
    }

    public static void applyTrimPathIfNeeded(Path path, float startValue, float endValue, float offsetValue) {
        Path path2 = path;
        L.beginSection("applyTrimPathIfNeeded");
        PathMeasure pathMeasure = threadLocalPathMeasure.get();
        Path tempPath = threadLocalTempPath.get();
        Path tempPath2 = threadLocalTempPath2.get();
        pathMeasure.setPath(path, false);
        float length = pathMeasure.getLength();
        if (startValue == 1.0f && endValue == 0.0f) {
            L.endSection("applyTrimPathIfNeeded");
        } else if (length < 1.0f || ((double) Math.abs((endValue - startValue) - 1.0f)) < 0.01d) {
            L.endSection("applyTrimPathIfNeeded");
        } else {
            float start = length * startValue;
            float end = length * endValue;
            float offset = offsetValue * length;
            float newStart = Math.min(start, end) + offset;
            float newEnd = Math.max(start, end) + offset;
            if (newStart >= length && newEnd >= length) {
                newStart = (float) MiscUtils.floorMod(newStart, length);
                newEnd = (float) MiscUtils.floorMod(newEnd, length);
            }
            if (newStart < 0.0f) {
                newStart = (float) MiscUtils.floorMod(newStart, length);
            }
            if (newEnd < 0.0f) {
                newEnd = (float) MiscUtils.floorMod(newEnd, length);
            }
            if (newStart == newEnd) {
                path.reset();
                L.endSection("applyTrimPathIfNeeded");
                return;
            }
            if (newStart >= newEnd) {
                newStart -= length;
            }
            tempPath.reset();
            pathMeasure.getSegment(newStart, newEnd, tempPath, true);
            if (newEnd > length) {
                tempPath2.reset();
                pathMeasure.getSegment(0.0f, newEnd % length, tempPath2, true);
                tempPath.addPath(tempPath2);
            } else if (newStart < 0.0f) {
                tempPath2.reset();
                pathMeasure.getSegment(length + newStart, length, tempPath2, true);
                tempPath.addPath(tempPath2);
            }
            path.set(tempPath);
            L.endSection("applyTrimPathIfNeeded");
        }
    }

    public static boolean isAtLeastVersion(int major, int minor, int patch, int minMajor, int minMinor, int minPatch) {
        if (major < minMajor) {
            return false;
        }
        if (major > minMajor) {
            return true;
        }
        if (minor < minMinor) {
            return false;
        }
        if (minor <= minMinor && patch < minPatch) {
            return false;
        }
        return true;
    }

    public static int hashFor(float a, float b, float c, float d) {
        int result = 17;
        if (a != 0.0f) {
            result = (int) (((float) (17 * 31)) * a);
        }
        if (b != 0.0f) {
            result = (int) (((float) (result * 31)) * b);
        }
        if (c != 0.0f) {
            result = (int) (((float) (result * 31)) * c);
        }
        if (d != 0.0f) {
            return (int) (((float) (result * 31)) * d);
        }
        return result;
    }

    public static float dpScale() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    public static float getAnimationScale(Context context) {
        if (Build.VERSION.SDK_INT >= 17) {
            return Settings.Global.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f);
        }
        return Settings.System.getFloat(context.getContentResolver(), "animator_duration_scale", 1.0f);
    }

    public static Bitmap resizeBitmapIfNeeded(Bitmap bitmap, int width, int height) {
        if (bitmap.getWidth() == width && bitmap.getHeight() == height) {
            return bitmap;
        }
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        bitmap.recycle();
        return resizedBitmap;
    }

    public static boolean isNetworkException(Throwable e) {
        return (e instanceof SocketException) || (e instanceof ClosedChannelException) || (e instanceof InterruptedIOException) || (e instanceof ProtocolException) || (e instanceof SSLException) || (e instanceof UnknownHostException) || (e instanceof UnknownServiceException);
    }

    public static void saveLayerCompat(Canvas canvas, RectF rect, Paint paint) {
        saveLayerCompat(canvas, rect, paint, 31);
    }

    public static void saveLayerCompat(Canvas canvas, RectF rect, Paint paint, int flag) {
        L.beginSection("Utils#saveLayer");
        if (Build.VERSION.SDK_INT < 23) {
            canvas.saveLayer(rect, paint, flag);
        } else {
            canvas.saveLayer(rect, paint);
        }
        L.endSection("Utils#saveLayer");
    }

    public static Bitmap renderPath(Path path) {
        RectF bounds = new RectF();
        path.computeBounds(bounds, false);
        Bitmap bitmap = Bitmap.createBitmap((int) bounds.right, (int) bounds.bottom, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new LPaint();
        paint.setAntiAlias(true);
        paint.setColor(-16776961);
        canvas.drawPath(path, paint);
        return bitmap;
    }
}
