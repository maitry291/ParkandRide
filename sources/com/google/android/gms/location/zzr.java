package com.google.android.gms.location;

import android.os.IInterface;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-location@@21.0.0 */
public interface zzr extends IInterface {
    void zzd(LocationAvailability locationAvailability) throws RemoteException;

    void zze(LocationResult locationResult) throws RemoteException;

    void zzf() throws RemoteException;
}
