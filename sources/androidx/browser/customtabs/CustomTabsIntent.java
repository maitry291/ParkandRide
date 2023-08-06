package androidx.browser.customtabs;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.SparseArray;
import android.widget.RemoteViews;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsSession;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.BundleCompat;
import androidx.core.content.ContextCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public final class CustomTabsIntent {
    public static final int COLOR_SCHEME_DARK = 2;
    public static final int COLOR_SCHEME_LIGHT = 1;
    private static final int COLOR_SCHEME_MAX = 2;
    public static final int COLOR_SCHEME_SYSTEM = 0;
    public static final String EXTRA_ACTION_BUTTON_BUNDLE = "android.support.customtabs.extra.ACTION_BUTTON_BUNDLE";
    public static final String EXTRA_CLOSE_BUTTON_ICON = "android.support.customtabs.extra.CLOSE_BUTTON_ICON";
    public static final String EXTRA_COLOR_SCHEME = "androidx.browser.customtabs.extra.COLOR_SCHEME";
    public static final String EXTRA_COLOR_SCHEME_PARAMS = "androidx.browser.customtabs.extra.COLOR_SCHEME_PARAMS";
    @Deprecated
    public static final String EXTRA_DEFAULT_SHARE_MENU_ITEM = "android.support.customtabs.extra.SHARE_MENU_ITEM";
    public static final String EXTRA_ENABLE_INSTANT_APPS = "android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS";
    public static final String EXTRA_ENABLE_URLBAR_HIDING = "android.support.customtabs.extra.ENABLE_URLBAR_HIDING";
    public static final String EXTRA_EXIT_ANIMATION_BUNDLE = "android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE";
    public static final String EXTRA_MENU_ITEMS = "android.support.customtabs.extra.MENU_ITEMS";
    public static final String EXTRA_NAVIGATION_BAR_COLOR = "androidx.browser.customtabs.extra.NAVIGATION_BAR_COLOR";
    public static final String EXTRA_NAVIGATION_BAR_DIVIDER_COLOR = "androidx.browser.customtabs.extra.NAVIGATION_BAR_DIVIDER_COLOR";
    public static final String EXTRA_REMOTEVIEWS = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS";
    public static final String EXTRA_REMOTEVIEWS_CLICKED_ID = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_CLICKED_ID";
    public static final String EXTRA_REMOTEVIEWS_PENDINGINTENT = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT";
    public static final String EXTRA_REMOTEVIEWS_VIEW_IDS = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS";
    public static final String EXTRA_SECONDARY_TOOLBAR_COLOR = "android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR";
    public static final String EXTRA_SESSION = "android.support.customtabs.extra.SESSION";
    public static final String EXTRA_SESSION_ID = "android.support.customtabs.extra.SESSION_ID";
    public static final String EXTRA_SHARE_STATE = "androidx.browser.customtabs.extra.SHARE_STATE";
    public static final String EXTRA_TINT_ACTION_BUTTON = "android.support.customtabs.extra.TINT_ACTION_BUTTON";
    public static final String EXTRA_TITLE_VISIBILITY_STATE = "android.support.customtabs.extra.TITLE_VISIBILITY";
    public static final String EXTRA_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";
    public static final String EXTRA_TOOLBAR_ITEMS = "android.support.customtabs.extra.TOOLBAR_ITEMS";
    private static final String EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS = "android.support.customtabs.extra.user_opt_out";
    public static final String KEY_DESCRIPTION = "android.support.customtabs.customaction.DESCRIPTION";
    public static final String KEY_ICON = "android.support.customtabs.customaction.ICON";
    public static final String KEY_ID = "android.support.customtabs.customaction.ID";
    public static final String KEY_MENU_ITEM_TITLE = "android.support.customtabs.customaction.MENU_ITEM_TITLE";
    public static final String KEY_PENDING_INTENT = "android.support.customtabs.customaction.PENDING_INTENT";
    private static final int MAX_TOOLBAR_ITEMS = 5;
    public static final int NO_TITLE = 0;
    public static final int SHARE_STATE_DEFAULT = 0;
    private static final int SHARE_STATE_MAX = 2;
    public static final int SHARE_STATE_OFF = 2;
    public static final int SHARE_STATE_ON = 1;
    public static final int SHOW_PAGE_TITLE = 1;
    public static final int TOOLBAR_ACTION_BUTTON_ID = 0;
    public final Intent intent;
    public final Bundle startAnimationBundle;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ColorScheme {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ShareState {
    }

    public void launchUrl(Context context, Uri url) {
        this.intent.setData(url);
        ContextCompat.startActivity(context, this.intent, this.startAnimationBundle);
    }

    CustomTabsIntent(Intent intent2, Bundle startAnimationBundle2) {
        this.intent = intent2;
        this.startAnimationBundle = startAnimationBundle2;
    }

    public static final class Builder {
        private ArrayList<Bundle> mActionButtons;
        private SparseArray<Bundle> mColorSchemeParamBundles;
        private final CustomTabColorSchemeParams.Builder mDefaultColorSchemeBuilder = new CustomTabColorSchemeParams.Builder();
        private Bundle mDefaultColorSchemeBundle;
        private boolean mInstantAppsEnabled = true;
        private final Intent mIntent = new Intent("android.intent.action.VIEW");
        private ArrayList<Bundle> mMenuItems;
        private int mShareState = 0;
        private Bundle mStartAnimationBundle;

        public Builder() {
        }

        public Builder(CustomTabsSession session) {
            if (session != null) {
                setSession(session);
            }
        }

        public Builder setSession(CustomTabsSession session) {
            this.mIntent.setPackage(session.getComponentName().getPackageName());
            setSessionParameters(session.getBinder(), session.getId());
            return this;
        }

        public Builder setPendingSession(CustomTabsSession.PendingSession session) {
            setSessionParameters((IBinder) null, session.getId());
            return this;
        }

        private void setSessionParameters(IBinder binder, PendingIntent sessionId) {
            Bundle bundle = new Bundle();
            BundleCompat.putBinder(bundle, CustomTabsIntent.EXTRA_SESSION, binder);
            if (sessionId != null) {
                bundle.putParcelable(CustomTabsIntent.EXTRA_SESSION_ID, sessionId);
            }
            this.mIntent.putExtras(bundle);
        }

        @Deprecated
        public Builder setToolbarColor(int color) {
            this.mDefaultColorSchemeBuilder.setToolbarColor(color);
            return this;
        }

        @Deprecated
        public Builder enableUrlBarHiding() {
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_ENABLE_URLBAR_HIDING, true);
            return this;
        }

        public Builder setUrlBarHidingEnabled(boolean enabled) {
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_ENABLE_URLBAR_HIDING, enabled);
            return this;
        }

        public Builder setCloseButtonIcon(Bitmap icon) {
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_CLOSE_BUTTON_ICON, icon);
            return this;
        }

        public Builder setShowTitle(boolean showTitle) {
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_TITLE_VISIBILITY_STATE, showTitle);
            return this;
        }

        public Builder addMenuItem(String label, PendingIntent pendingIntent) {
            if (this.mMenuItems == null) {
                this.mMenuItems = new ArrayList<>();
            }
            Bundle bundle = new Bundle();
            bundle.putString(CustomTabsIntent.KEY_MENU_ITEM_TITLE, label);
            bundle.putParcelable(CustomTabsIntent.KEY_PENDING_INTENT, pendingIntent);
            this.mMenuItems.add(bundle);
            return this;
        }

        @Deprecated
        public Builder addDefaultShareMenuItem() {
            setShareState(1);
            return this;
        }

        @Deprecated
        public Builder setDefaultShareMenuItemEnabled(boolean enabled) {
            if (enabled) {
                setShareState(1);
            } else {
                setShareState(2);
            }
            return this;
        }

        public Builder setShareState(int shareState) {
            if (shareState < 0 || shareState > 2) {
                throw new IllegalArgumentException("Invalid value for the shareState argument");
            }
            this.mShareState = shareState;
            if (shareState == 1) {
                this.mIntent.putExtra(CustomTabsIntent.EXTRA_DEFAULT_SHARE_MENU_ITEM, true);
            } else if (shareState == 2) {
                this.mIntent.putExtra(CustomTabsIntent.EXTRA_DEFAULT_SHARE_MENU_ITEM, false);
            } else {
                this.mIntent.removeExtra(CustomTabsIntent.EXTRA_DEFAULT_SHARE_MENU_ITEM);
            }
            return this;
        }

        public Builder setActionButton(Bitmap icon, String description, PendingIntent pendingIntent, boolean shouldTint) {
            Bundle bundle = new Bundle();
            bundle.putInt(CustomTabsIntent.KEY_ID, 0);
            bundle.putParcelable(CustomTabsIntent.KEY_ICON, icon);
            bundle.putString(CustomTabsIntent.KEY_DESCRIPTION, description);
            bundle.putParcelable(CustomTabsIntent.KEY_PENDING_INTENT, pendingIntent);
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_ACTION_BUTTON_BUNDLE, bundle);
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_TINT_ACTION_BUTTON, shouldTint);
            return this;
        }

        public Builder setActionButton(Bitmap icon, String description, PendingIntent pendingIntent) {
            return setActionButton(icon, description, pendingIntent, false);
        }

        @Deprecated
        public Builder addToolbarItem(int id, Bitmap icon, String description, PendingIntent pendingIntent) throws IllegalStateException {
            if (this.mActionButtons == null) {
                this.mActionButtons = new ArrayList<>();
            }
            if (this.mActionButtons.size() < 5) {
                Bundle bundle = new Bundle();
                bundle.putInt(CustomTabsIntent.KEY_ID, id);
                bundle.putParcelable(CustomTabsIntent.KEY_ICON, icon);
                bundle.putString(CustomTabsIntent.KEY_DESCRIPTION, description);
                bundle.putParcelable(CustomTabsIntent.KEY_PENDING_INTENT, pendingIntent);
                this.mActionButtons.add(bundle);
                return this;
            }
            throw new IllegalStateException("Exceeded maximum toolbar item count of 5");
        }

        @Deprecated
        public Builder setSecondaryToolbarColor(int color) {
            this.mDefaultColorSchemeBuilder.setSecondaryToolbarColor(color);
            return this;
        }

        @Deprecated
        public Builder setNavigationBarColor(int color) {
            this.mDefaultColorSchemeBuilder.setNavigationBarColor(color);
            return this;
        }

        @Deprecated
        public Builder setNavigationBarDividerColor(int color) {
            this.mDefaultColorSchemeBuilder.setNavigationBarDividerColor(color);
            return this;
        }

        public Builder setSecondaryToolbarViews(RemoteViews remoteViews, int[] clickableIDs, PendingIntent pendingIntent) {
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_REMOTEVIEWS, remoteViews);
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_REMOTEVIEWS_VIEW_IDS, clickableIDs);
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_REMOTEVIEWS_PENDINGINTENT, pendingIntent);
            return this;
        }

        public Builder setInstantAppsEnabled(boolean enabled) {
            this.mInstantAppsEnabled = enabled;
            return this;
        }

        public Builder setStartAnimations(Context context, int enterResId, int exitResId) {
            this.mStartAnimationBundle = ActivityOptionsCompat.makeCustomAnimation(context, enterResId, exitResId).toBundle();
            return this;
        }

        public Builder setExitAnimations(Context context, int enterResId, int exitResId) {
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_EXIT_ANIMATION_BUNDLE, ActivityOptionsCompat.makeCustomAnimation(context, enterResId, exitResId).toBundle());
            return this;
        }

        public Builder setColorScheme(int colorScheme) {
            if (colorScheme < 0 || colorScheme > 2) {
                throw new IllegalArgumentException("Invalid value for the colorScheme argument");
            }
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_COLOR_SCHEME, colorScheme);
            return this;
        }

        public Builder setColorSchemeParams(int colorScheme, CustomTabColorSchemeParams params) {
            if (colorScheme < 0 || colorScheme > 2 || colorScheme == 0) {
                throw new IllegalArgumentException("Invalid colorScheme: " + colorScheme);
            }
            if (this.mColorSchemeParamBundles == null) {
                this.mColorSchemeParamBundles = new SparseArray<>();
            }
            this.mColorSchemeParamBundles.put(colorScheme, params.toBundle());
            return this;
        }

        public Builder setDefaultColorSchemeParams(CustomTabColorSchemeParams params) {
            this.mDefaultColorSchemeBundle = params.toBundle();
            return this;
        }

        public CustomTabsIntent build() {
            if (!this.mIntent.hasExtra(CustomTabsIntent.EXTRA_SESSION)) {
                setSessionParameters((IBinder) null, (PendingIntent) null);
            }
            ArrayList<Bundle> arrayList = this.mMenuItems;
            if (arrayList != null) {
                this.mIntent.putParcelableArrayListExtra(CustomTabsIntent.EXTRA_MENU_ITEMS, arrayList);
            }
            ArrayList<Bundle> arrayList2 = this.mActionButtons;
            if (arrayList2 != null) {
                this.mIntent.putParcelableArrayListExtra(CustomTabsIntent.EXTRA_TOOLBAR_ITEMS, arrayList2);
            }
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_ENABLE_INSTANT_APPS, this.mInstantAppsEnabled);
            this.mIntent.putExtras(this.mDefaultColorSchemeBuilder.build().toBundle());
            Bundle bundle = this.mDefaultColorSchemeBundle;
            if (bundle != null) {
                this.mIntent.putExtras(bundle);
            }
            if (this.mColorSchemeParamBundles != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putSparseParcelableArray(CustomTabsIntent.EXTRA_COLOR_SCHEME_PARAMS, this.mColorSchemeParamBundles);
                this.mIntent.putExtras(bundle2);
            }
            this.mIntent.putExtra(CustomTabsIntent.EXTRA_SHARE_STATE, this.mShareState);
            return new CustomTabsIntent(this.mIntent, this.mStartAnimationBundle);
        }
    }

    public static int getMaxToolbarItems() {
        return 5;
    }

    public static Intent setAlwaysUseBrowserUI(Intent intent2) {
        if (intent2 == null) {
            intent2 = new Intent("android.intent.action.VIEW");
        }
        intent2.addFlags(268435456);
        intent2.putExtra(EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS, true);
        return intent2;
    }

    public static boolean shouldAlwaysUseBrowserUI(Intent intent2) {
        if (!intent2.getBooleanExtra(EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS, false) || (intent2.getFlags() & 268435456) == 0) {
            return false;
        }
        return true;
    }

    public static CustomTabColorSchemeParams getColorSchemeParams(Intent intent2, int colorScheme) {
        Bundle bundleForScheme;
        if (colorScheme < 0 || colorScheme > 2 || colorScheme == 0) {
            throw new IllegalArgumentException("Invalid colorScheme: " + colorScheme);
        }
        Bundle extras = intent2.getExtras();
        if (extras == null) {
            return CustomTabColorSchemeParams.fromBundle((Bundle) null);
        }
        CustomTabColorSchemeParams defaults = CustomTabColorSchemeParams.fromBundle(extras);
        SparseArray<Bundle> paramBundles = extras.getSparseParcelableArray(EXTRA_COLOR_SCHEME_PARAMS);
        if (paramBundles == null || (bundleForScheme = paramBundles.get(colorScheme)) == null) {
            return defaults;
        }
        return CustomTabColorSchemeParams.fromBundle(bundleForScheme).withDefaults(defaults);
    }
}
