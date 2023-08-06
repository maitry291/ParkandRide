package com.airbnb.lottie.utils;

import android.util.Log;
import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieLogger;
import java.util.HashSet;
import java.util.Set;

public class LogcatLogger implements LottieLogger {
    private static final Set<String> loggedMessages = new HashSet();

    public void debug(String message) {
        debug(message, (Throwable) null);
    }

    public void debug(String message, Throwable exception) {
        if (L.DBG) {
            Log.d(L.TAG, message, exception);
        }
    }

    public void warning(String message) {
        warning(message, (Throwable) null);
    }

    public void warning(String message, Throwable exception) {
        Set<String> set = loggedMessages;
        if (!set.contains(message)) {
            Log.w(L.TAG, message, exception);
            set.add(message);
        }
    }

    public void error(String message, Throwable exception) {
        if (L.DBG) {
            Log.d(L.TAG, message, exception);
        }
    }
}
