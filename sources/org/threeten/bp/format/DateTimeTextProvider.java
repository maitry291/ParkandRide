package org.threeten.bp.format;

import com.google.android.gms.common.api.internal.zap$$ExternalSyntheticBackportWithForwarding0;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.threeten.bp.temporal.TemporalField;

public abstract class DateTimeTextProvider {
    /* access modifiers changed from: private */
    public static final AtomicReference<DateTimeTextProvider> MUTABLE_PROVIDER = new AtomicReference<>();

    public abstract String getText(TemporalField temporalField, long j, TextStyle textStyle, Locale locale);

    public abstract Iterator<Map.Entry<String, Long>> getTextIterator(TemporalField temporalField, TextStyle textStyle, Locale locale);

    static DateTimeTextProvider getInstance() {
        return ProviderSingleton.PROVIDER;
    }

    public static void setInitializer(DateTimeTextProvider provider) {
        if (!zap$$ExternalSyntheticBackportWithForwarding0.m(MUTABLE_PROVIDER, (Object) null, provider)) {
            throw new IllegalStateException("Provider was already set, possibly with a default during initialization");
        }
    }

    static class ProviderSingleton {
        static final DateTimeTextProvider PROVIDER = initialize();

        ProviderSingleton() {
        }

        static DateTimeTextProvider initialize() {
            zap$$ExternalSyntheticBackportWithForwarding0.m(DateTimeTextProvider.MUTABLE_PROVIDER, (Object) null, new SimpleDateTimeTextProvider());
            return (DateTimeTextProvider) DateTimeTextProvider.MUTABLE_PROVIDER.get();
        }
    }
}
