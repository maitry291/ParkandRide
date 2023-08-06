package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@21.0.0 */
public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ActivityRecognitionResult> CREATOR = new zzd();
    List zza;
    long zzb;
    long zzc;
    int zzd;
    Bundle zze;

    public ActivityRecognitionResult(DetectedActivity mostProbableActivity, long time, long elapsedRealtimeMillis) {
        this(Collections.singletonList(mostProbableActivity), time, elapsedRealtimeMillis, 0, (Bundle) null);
    }

    public static ActivityRecognitionResult extractResult(Intent intent) {
        ActivityRecognitionResult activityRecognitionResult;
        if (!hasResult(intent)) {
            activityRecognitionResult = null;
        } else {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                activityRecognitionResult = null;
            } else {
                Object obj = extras.get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT");
                if (obj instanceof byte[]) {
                    activityRecognitionResult = (ActivityRecognitionResult) SafeParcelableSerializer.deserializeFromBytes((byte[]) obj, CREATOR);
                } else {
                    activityRecognitionResult = obj instanceof ActivityRecognitionResult ? (ActivityRecognitionResult) obj : null;
                }
            }
        }
        if (activityRecognitionResult != null) {
            return activityRecognitionResult;
        }
        List zza2 = zza(intent);
        if (zza2 == null || zza2.isEmpty()) {
            return null;
        }
        return (ActivityRecognitionResult) zza2.get(zza2.size() - 1);
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
            return true;
        }
        List zza2 = zza(intent);
        return zza2 != null && !zza2.isEmpty();
    }

    public static List zza(Intent intent) {
        if (intent != null && intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST")) {
            return SafeParcelableSerializer.deserializeIterableFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", CREATOR);
        }
        return null;
    }

    private static boolean zzb(Bundle bundle, Bundle bundle2) {
        int length;
        if (bundle == null) {
            return bundle2 == null;
        }
        if (bundle2 == null || bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            if (!bundle2.containsKey(str)) {
                return false;
            }
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if (obj == null) {
                if (obj2 != null) {
                    return false;
                }
            } else if (obj instanceof Bundle) {
                if (!zzb(bundle.getBundle(str), bundle2.getBundle(str))) {
                    return false;
                }
            } else if (obj.getClass().isArray()) {
                if (obj2 != null && obj2.getClass().isArray() && (length = Array.getLength(obj)) == Array.getLength(obj2)) {
                    int i = 0;
                    while (i < length) {
                        if (Objects.equal(Array.get(obj, i), Array.get(obj2, i))) {
                            i++;
                        }
                    }
                    continue;
                }
                return false;
            } else if (!obj.equals(obj2)) {
                return false;
            }
        }
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
        return this.zzb == activityRecognitionResult.zzb && this.zzc == activityRecognitionResult.zzc && this.zzd == activityRecognitionResult.zzd && Objects.equal(this.zza, activityRecognitionResult.zza) && zzb(this.zze, activityRecognitionResult.zze);
    }

    public int getActivityConfidence(int activityType) {
        for (DetectedActivity detectedActivity : this.zza) {
            if (detectedActivity.getType() == activityType) {
                return detectedActivity.getConfidence();
            }
        }
        return 0;
    }

    public long getElapsedRealtimeMillis() {
        return this.zzc;
    }

    public DetectedActivity getMostProbableActivity() {
        return (DetectedActivity) this.zza.get(0);
    }

    public List<DetectedActivity> getProbableActivities() {
        return this.zza;
    }

    public long getTime() {
        return this.zzb;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzb), Long.valueOf(this.zzc), Integer.valueOf(this.zzd), this.zza, this.zze);
    }

    public String toString() {
        String valueOf = String.valueOf(this.zza);
        long j = this.zzb;
        long j2 = this.zzc;
        return "ActivityRecognitionResult [probableActivities=" + valueOf + ", timeMillis=" + j + ", elapsedRealtimeMillis=" + j2 + "]";
    }

    public void writeToParcel(Parcel out, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(out);
        SafeParcelWriter.writeTypedList(out, 1, this.zza, false);
        SafeParcelWriter.writeLong(out, 2, this.zzb);
        SafeParcelWriter.writeLong(out, 3, this.zzc);
        SafeParcelWriter.writeInt(out, 4, this.zzd);
        SafeParcelWriter.writeBundle(out, 5, this.zze, false);
        SafeParcelWriter.finishObjectHeader(out, beginObjectHeader);
    }

    public ActivityRecognitionResult(List<DetectedActivity> probableActivities, long time, long elapsedRealtimeMillis) {
        this(probableActivities, time, elapsedRealtimeMillis, 0, (Bundle) null);
    }

    public ActivityRecognitionResult(List list, long j, long j2, int i, Bundle bundle) {
        boolean z;
        boolean z2 = true;
        if (list == null || list.size() <= 0) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "Must have at least 1 detected activity");
        Preconditions.checkArgument((j <= 0 || j2 <= 0) ? false : z2, "Must set times");
        this.zza = list;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = i;
        this.zze = bundle;
    }
}
