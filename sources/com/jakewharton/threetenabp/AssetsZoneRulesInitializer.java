package com.jakewharton.threetenabp;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import org.threeten.bp.zone.TzdbZoneRulesProvider;
import org.threeten.bp.zone.ZoneRulesInitializer;
import org.threeten.bp.zone.ZoneRulesProvider;

final class AssetsZoneRulesInitializer extends ZoneRulesInitializer {
    private final String assetPath;
    private final Context context;

    AssetsZoneRulesInitializer(Context context2, String assetPath2) {
        this.context = context2;
        this.assetPath = assetPath2;
    }

    /* access modifiers changed from: protected */
    public void initializeProviders() {
        InputStream is = null;
        try {
            is = this.context.getAssets().open(this.assetPath);
            TzdbZoneRulesProvider provider = new TzdbZoneRulesProvider(is);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            ZoneRulesProvider.registerProvider(provider);
        } catch (IOException e2) {
            throw new IllegalStateException(this.assetPath + " missing from assets", e2);
        } catch (Throwable th) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e3) {
                }
            }
            throw th;
        }
    }
}
