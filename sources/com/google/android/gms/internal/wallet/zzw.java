package com.google.android.gms.internal.wallet;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.WalletConstants;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
final class zzw extends zzx {
    private final WeakReference<Activity> zza;
    private final int zzb;

    public zzw(Activity activity, int i) {
        this.zza = new WeakReference<>(activity);
        this.zzb = i;
    }

    public final void zzb(int i, FullWallet fullWallet, Bundle bundle) {
        PendingIntent pendingIntent;
        int i2;
        int i3;
        Activity activity = (Activity) this.zza.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onFullWalletLoaded, Activity has gone");
            return;
        }
        if (bundle != null) {
            pendingIntent = (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        } else {
            pendingIntent = null;
        }
        ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, this.zzb);
            } catch (IntentSender.SendIntentException e) {
                Log.w("WalletClientImpl", "Exception starting pending intent", e);
            }
        } else {
            Intent intent = new Intent();
            if (connectionResult.isSuccess()) {
                intent.putExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET", fullWallet);
                i2 = -1;
            } else {
                if (i == 408) {
                    i3 = 0;
                } else {
                    i3 = 1;
                }
                intent.putExtra(WalletConstants.EXTRA_ERROR_CODE, i);
                i2 = i3;
            }
            PendingIntent createPendingResult = activity.createPendingResult(this.zzb, intent, BasicMeasure.EXACTLY);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onFullWalletLoaded");
                return;
            }
            try {
                createPendingResult.send(i2);
            } catch (PendingIntent.CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }
    }

    public final void zzd(int i, MaskedWallet maskedWallet, Bundle bundle) {
        PendingIntent pendingIntent;
        int i2;
        int i3;
        Activity activity = (Activity) this.zza.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onMaskedWalletLoaded, Activity has gone");
            return;
        }
        if (bundle != null) {
            pendingIntent = (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT");
        } else {
            pendingIntent = null;
        }
        ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, this.zzb);
            } catch (IntentSender.SendIntentException e) {
                Log.w("WalletClientImpl", "Exception starting pending intent", e);
            }
        } else {
            Intent intent = new Intent();
            if (connectionResult.isSuccess()) {
                intent.putExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET", maskedWallet);
                i2 = -1;
            } else {
                if (i == 408) {
                    i3 = 0;
                } else {
                    i3 = 1;
                }
                intent.putExtra(WalletConstants.EXTRA_ERROR_CODE, i);
                i2 = i3;
            }
            PendingIntent createPendingResult = activity.createPendingResult(this.zzb, intent, BasicMeasure.EXACTLY);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onMaskedWalletLoaded");
                return;
            }
            try {
                createPendingResult.send(i2);
            } catch (PendingIntent.CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }
    }

    public final void zzf(int i, boolean z, Bundle bundle) {
        Activity activity = (Activity) this.zza.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onPreAuthorizationDetermined, Activity has gone");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(WalletConstants.EXTRA_IS_USER_PREAUTHORIZED, z);
        PendingIntent createPendingResult = activity.createPendingResult(this.zzb, intent, BasicMeasure.EXACTLY);
        if (createPendingResult == null) {
            Log.w("WalletClientImpl", "Null pending result returned for onPreAuthorizationDetermined");
            return;
        }
        try {
            createPendingResult.send(-1);
        } catch (PendingIntent.CanceledException e) {
            Log.w("WalletClientImpl", "Exception setting pending result", e);
        }
    }

    public final void zzg(int i, Bundle bundle) {
        Preconditions.checkNotNull(bundle, "Bundle should not be null");
        Activity activity = (Activity) this.zza.get();
        if (activity == null) {
            Log.d("WalletClientImpl", "Ignoring onWalletObjectsCreated, Activity has gone");
            return;
        }
        ConnectionResult connectionResult = new ConnectionResult(i, (PendingIntent) bundle.getParcelable("com.google.android.gms.wallet.EXTRA_PENDING_INTENT"));
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(activity, this.zzb);
            } catch (IntentSender.SendIntentException e) {
                Log.w("WalletClientImpl", "Exception starting pending intent", e);
            }
        } else {
            String valueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 75);
            sb.append("Create Wallet Objects confirmation UI will not be shown connection result: ");
            sb.append(valueOf);
            Log.e("WalletClientImpl", sb.toString());
            Intent intent = new Intent();
            intent.putExtra(WalletConstants.EXTRA_ERROR_CODE, WalletConstants.ERROR_CODE_UNKNOWN);
            PendingIntent createPendingResult = activity.createPendingResult(this.zzb, intent, BasicMeasure.EXACTLY);
            if (createPendingResult == null) {
                Log.w("WalletClientImpl", "Null pending result returned for onWalletObjectsCreated");
                return;
            }
            try {
                createPendingResult.send(1);
            } catch (PendingIntent.CanceledException e2) {
                Log.w("WalletClientImpl", "Exception setting pending result", e2);
            }
        }
    }
}
