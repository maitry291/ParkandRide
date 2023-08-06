package androidx.browser.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.IPostMessageService;
import android.util.Log;

public abstract class PostMessageServiceConnection implements PostMessageBackend, ServiceConnection {
    private static final String TAG = "PostMessageServConn";
    private final Object mLock = new Object();
    private boolean mMessageChannelCreated;
    private String mPackageName;
    private IPostMessageService mService;
    private final ICustomTabsCallback mSessionBinder;

    public PostMessageServiceConnection(CustomTabsSessionToken session) {
        IBinder binder = session.getCallbackBinder();
        if (binder != null) {
            this.mSessionBinder = ICustomTabsCallback.Stub.asInterface(binder);
            return;
        }
        throw new IllegalArgumentException("Provided session must have binder.");
    }

    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }

    public boolean bindSessionToPostMessageService(Context context, String packageName) {
        Intent intent = new Intent();
        intent.setClassName(packageName, PostMessageService.class.getName());
        boolean success = context.bindService(intent, this, 1);
        if (!success) {
            Log.w(TAG, "Could not bind to PostMessageService in client.");
        }
        return success;
    }

    public boolean bindSessionToPostMessageService(Context appContext) {
        String str = this.mPackageName;
        if (str != null) {
            return bindSessionToPostMessageService(appContext, str);
        }
        throw new IllegalStateException("setPackageName must be called before bindSessionToPostMessageService.");
    }

    private boolean isBoundToService() {
        return this.mService != null;
    }

    public void unbindFromContext(Context context) {
        if (isBoundToService()) {
            context.unbindService(this);
            this.mService = null;
        }
    }

    public final void onServiceConnected(ComponentName name, IBinder service) {
        this.mService = IPostMessageService.Stub.asInterface(service);
        onPostMessageServiceConnected();
    }

    public final void onServiceDisconnected(ComponentName name) {
        this.mService = null;
        onPostMessageServiceDisconnected();
    }

    public final boolean onNotifyMessageChannelReady(Bundle extras) {
        return notifyMessageChannelReady(extras);
    }

    public final boolean notifyMessageChannelReady(Bundle extras) {
        this.mMessageChannelCreated = true;
        return notifyMessageChannelReadyInternal(extras);
    }

    private boolean notifyMessageChannelReadyInternal(Bundle extras) {
        if (this.mService == null) {
            return false;
        }
        synchronized (this.mLock) {
            try {
                this.mService.onMessageChannelReady(this.mSessionBinder, extras);
            } catch (RemoteException e) {
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    public final boolean onPostMessage(String message, Bundle extras) {
        return postMessage(message, extras);
    }

    public final boolean postMessage(String message, Bundle extras) {
        if (this.mService == null) {
            return false;
        }
        synchronized (this.mLock) {
            try {
                this.mService.onPostMessage(this.mSessionBinder, message, extras);
            } catch (RemoteException e) {
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
        return true;
    }

    public void onDisconnectChannel(Context appContext) {
        unbindFromContext(appContext);
    }

    public void onPostMessageServiceConnected() {
        if (this.mMessageChannelCreated) {
            notifyMessageChannelReadyInternal((Bundle) null);
        }
    }

    public void onPostMessageServiceDisconnected() {
    }

    public void cleanup(Context context) {
        if (isBoundToService()) {
            unbindFromContext(context);
        }
    }
}
