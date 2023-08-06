package com.google.android.gms.wallet;

import com.google.android.gms.common.Feature;

/* compiled from: com.google.android.gms:play-services-wallet@@18.1.3 */
public final class zzj {
    public static final Feature zza;
    public static final Feature zzb;
    public static final Feature zzc;
    public static final Feature zzd;
    public static final Feature zze;
    public static final Feature zzf;
    public static final Feature[] zzg;

    static {
        Feature feature = new Feature("wallet", 1);
        zza = feature;
        Feature feature2 = new Feature("wallet_biometric_auth_keys", 1);
        zzb = feature2;
        Feature feature3 = new Feature("wallet_payment_dynamic_update", 2);
        zzc = feature3;
        Feature feature4 = new Feature("wallet_1p_initialize_buyflow", 1);
        zzd = feature4;
        Feature feature5 = new Feature("wallet_warm_up_ui_process", 1);
        zze = feature5;
        Feature feature6 = new Feature("wallet_get_setup_wizard_intent", 2);
        zzf = feature6;
        zzg = new Feature[]{feature, feature2, feature3, feature4, feature5, feature6};
    }
}
