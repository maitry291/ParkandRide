package com.google.android.gms.internal.wallet;

import android.os.Bundle;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentData;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
final class zzaa extends zzx {
    private final TaskCompletionSource<PaymentData> zza;

    public zzaa(TaskCompletionSource<PaymentData> taskCompletionSource) {
        this.zza = taskCompletionSource;
    }

    public final void zze(Status status, PaymentData paymentData, Bundle bundle) {
        AutoResolveHelper.zzd(status, paymentData, this.zza);
    }
}
