package org.threeten.bp.chrono;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.threeten.bp.Clock;
import org.threeten.bp.DateTimeException;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.ChronoField;
import org.threeten.bp.temporal.ChronoUnit;
import org.threeten.bp.temporal.TemporalAccessor;
import org.threeten.bp.temporal.TemporalAdjusters;
import org.threeten.bp.temporal.TemporalField;
import org.threeten.bp.temporal.TemporalUnit;
import org.threeten.bp.temporal.ValueRange;

public final class HijrahChronology extends Chronology implements Serializable {
    private static final HashMap<String, String[]> ERA_FULL_NAMES;
    private static final HashMap<String, String[]> ERA_NARROW_NAMES;
    private static final HashMap<String, String[]> ERA_SHORT_NAMES;
    private static final String FALLBACK_LANGUAGE = "en";
    public static final HijrahChronology INSTANCE = new HijrahChronology();
    private static final long serialVersionUID = 3127340209035924785L;

    static {
        HashMap<String, String[]> hashMap = new HashMap<>();
        ERA_NARROW_NAMES = hashMap;
        HashMap<String, String[]> hashMap2 = new HashMap<>();
        ERA_SHORT_NAMES = hashMap2;
        HashMap<String, String[]> hashMap3 = new HashMap<>();
        ERA_FULL_NAMES = hashMap3;
        hashMap.put(FALLBACK_LANGUAGE, new String[]{"BH", "HE"});
        hashMap2.put(FALLBACK_LANGUAGE, new String[]{"B.H.", "H.E."});
        hashMap3.put(FALLBACK_LANGUAGE, new String[]{"Before Hijrah", "Hijrah Era"});
    }

    private HijrahChronology() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    public String getId() {
        return "Hijrah-umalqura";
    }

    public String getCalendarType() {
        return "islamic-umalqura";
    }

    public HijrahDate date(Era era, int yearOfEra, int month, int dayOfMonth) {
        return (HijrahDate) super.date(era, yearOfEra, month, dayOfMonth);
    }

    public HijrahDate date(int prolepticYear, int month, int dayOfMonth) {
        return HijrahDate.of(prolepticYear, month, dayOfMonth);
    }

    public HijrahDate dateYearDay(Era era, int yearOfEra, int dayOfYear) {
        return (HijrahDate) super.dateYearDay(era, yearOfEra, dayOfYear);
    }

    public HijrahDate dateYearDay(int prolepticYear, int dayOfYear) {
        return HijrahDate.of(prolepticYear, 1, 1).plusDays((long) (dayOfYear - 1));
    }

    public HijrahDate dateEpochDay(long epochDay) {
        return HijrahDate.of(LocalDate.ofEpochDay(epochDay));
    }

    public HijrahDate date(TemporalAccessor temporal) {
        if (temporal instanceof HijrahDate) {
            return (HijrahDate) temporal;
        }
        return HijrahDate.ofEpochDay(temporal.getLong(ChronoField.EPOCH_DAY));
    }

    public ChronoLocalDateTime<HijrahDate> localDateTime(TemporalAccessor temporal) {
        return super.localDateTime(temporal);
    }

    public ChronoZonedDateTime<HijrahDate> zonedDateTime(TemporalAccessor temporal) {
        return super.zonedDateTime(temporal);
    }

    public ChronoZonedDateTime<HijrahDate> zonedDateTime(Instant instant, ZoneId zone) {
        return super.zonedDateTime(instant, zone);
    }

    public HijrahDate dateNow() {
        return (HijrahDate) super.dateNow();
    }

    public HijrahDate dateNow(ZoneId zone) {
        return (HijrahDate) super.dateNow(zone);
    }

    public HijrahDate dateNow(Clock clock) {
        Jdk8Methods.requireNonNull(clock, "clock");
        return (HijrahDate) super.dateNow(clock);
    }

    public boolean isLeapYear(long prolepticYear) {
        return HijrahDate.isLeapYear(prolepticYear);
    }

    public int prolepticYear(Era era, int yearOfEra) {
        if (era instanceof HijrahEra) {
            return era == HijrahEra.AH ? yearOfEra : 1 - yearOfEra;
        }
        throw new ClassCastException("Era must be HijrahEra");
    }

    public HijrahEra eraOf(int eraValue) {
        switch (eraValue) {
            case 0:
                return HijrahEra.BEFORE_AH;
            case 1:
                return HijrahEra.AH;
            default:
                throw new DateTimeException("invalid Hijrah era");
        }
    }

    public List<Era> eras() {
        return Arrays.asList(HijrahEra.values());
    }

    public ValueRange range(ChronoField field) {
        return field.range();
    }

    public HijrahDate resolveDate(Map<TemporalField, Long> fieldValues, ResolverStyle resolverStyle) {
        if (fieldValues.containsKey(ChronoField.EPOCH_DAY)) {
            return dateEpochDay(fieldValues.remove(ChronoField.EPOCH_DAY).longValue());
        }
        Long prolepticMonth = fieldValues.remove(ChronoField.PROLEPTIC_MONTH);
        if (prolepticMonth != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.PROLEPTIC_MONTH.checkValidValue(prolepticMonth.longValue());
            }
            updateResolveMap(fieldValues, ChronoField.MONTH_OF_YEAR, (long) (Jdk8Methods.floorMod(prolepticMonth.longValue(), 12) + 1));
            updateResolveMap(fieldValues, ChronoField.YEAR, Jdk8Methods.floorDiv(prolepticMonth.longValue(), 12));
        }
        Long yoeLong = fieldValues.remove(ChronoField.YEAR_OF_ERA);
        if (yoeLong != null) {
            if (resolverStyle != ResolverStyle.LENIENT) {
                ChronoField.YEAR_OF_ERA.checkValidValue(yoeLong.longValue());
            }
            Long era = fieldValues.remove(ChronoField.ERA);
            if (era == null) {
                Long year = fieldValues.get(ChronoField.YEAR);
                if (resolverStyle != ResolverStyle.STRICT) {
                    updateResolveMap(fieldValues, ChronoField.YEAR, (year == null || year.longValue() > 0) ? yoeLong.longValue() : Jdk8Methods.safeSubtract(1, yoeLong.longValue()));
                } else if (year != null) {
                    ChronoField chronoField = ChronoField.YEAR;
                    int i = (year.longValue() > 0 ? 1 : (year.longValue() == 0 ? 0 : -1));
                    long longValue = yoeLong.longValue();
                    if (i <= 0) {
                        longValue = Jdk8Methods.safeSubtract(1, longValue);
                    }
                    updateResolveMap(fieldValues, chronoField, longValue);
                } else {
                    fieldValues.put(ChronoField.YEAR_OF_ERA, yoeLong);
                }
            } else if (era.longValue() == 1) {
                updateResolveMap(fieldValues, ChronoField.YEAR, yoeLong.longValue());
            } else if (era.longValue() == 0) {
                updateResolveMap(fieldValues, ChronoField.YEAR, Jdk8Methods.safeSubtract(1, yoeLong.longValue()));
            } else {
                throw new DateTimeException("Invalid value for era: " + era);
            }
        } else if (fieldValues.containsKey(ChronoField.ERA)) {
            ChronoField.ERA.checkValidValue(fieldValues.get(ChronoField.ERA).longValue());
        }
        if (!fieldValues.containsKey(ChronoField.YEAR)) {
            return null;
        }
        if (fieldValues.containsKey(ChronoField.MONTH_OF_YEAR)) {
            if (fieldValues.containsKey(ChronoField.DAY_OF_MONTH)) {
                int y = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return date(y, 1, 1).plusMonths(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1)).plusDays(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_MONTH).longValue(), 1));
                }
                int moy = range(ChronoField.MONTH_OF_YEAR).checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR).longValue(), ChronoField.MONTH_OF_YEAR);
                int dom = range(ChronoField.DAY_OF_MONTH).checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_MONTH).longValue(), ChronoField.DAY_OF_MONTH);
                if (resolverStyle == ResolverStyle.SMART && dom > 28) {
                    dom = Math.min(dom, date(y, moy, 1).lengthOfMonth());
                }
                return date(y, moy, dom);
            } else if (fieldValues.containsKey(ChronoField.ALIGNED_WEEK_OF_MONTH)) {
                if (fieldValues.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH)) {
                    int y2 = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long months = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1);
                        return date(y2, 1, 1).plus(months, (TemporalUnit) ChronoUnit.MONTHS).plus(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue(), 1), (TemporalUnit) ChronoUnit.DAYS);
                    }
                    int moy2 = ChronoField.MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR).longValue());
                    HijrahDate date = date(y2, moy2, 1).plus((long) (((ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue()) - 1) * 7) + (ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_MONTH).longValue()) - 1)), (TemporalUnit) ChronoUnit.DAYS);
                    if (resolverStyle != ResolverStyle.STRICT || date.get(ChronoField.MONTH_OF_YEAR) == moy2) {
                        return date;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                } else if (fieldValues.containsKey(ChronoField.DAY_OF_WEEK)) {
                    int y3 = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR).longValue());
                    if (resolverStyle == ResolverStyle.LENIENT) {
                        long months2 = Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.MONTH_OF_YEAR).longValue(), 1);
                        return date(y3, 1, 1).plus(months2, (TemporalUnit) ChronoUnit.MONTHS).plus(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue(), 1), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_WEEK).longValue(), 1), (TemporalUnit) ChronoUnit.DAYS);
                    }
                    int moy3 = ChronoField.MONTH_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.MONTH_OF_YEAR).longValue());
                    HijrahDate date2 = date(y3, moy3, 1).plus((long) (ChronoField.ALIGNED_WEEK_OF_MONTH.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_MONTH).longValue()) - 1), (TemporalUnit) ChronoUnit.WEEKS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(ChronoField.DAY_OF_WEEK.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_WEEK).longValue()))));
                    if (resolverStyle != ResolverStyle.STRICT || date2.get(ChronoField.MONTH_OF_YEAR) == moy3) {
                        return date2;
                    }
                    throw new DateTimeException("Strict mode rejected date parsed to a different month");
                }
            }
        }
        if (fieldValues.containsKey(ChronoField.DAY_OF_YEAR)) {
            int y4 = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR).longValue());
            if (resolverStyle != ResolverStyle.LENIENT) {
                return dateYearDay(y4, ChronoField.DAY_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_YEAR).longValue()));
            }
            return dateYearDay(y4, 1).plusDays(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_YEAR).longValue(), 1));
        } else if (!fieldValues.containsKey(ChronoField.ALIGNED_WEEK_OF_YEAR)) {
            return null;
        } else {
            if (fieldValues.containsKey(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR)) {
                int y5 = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return date(y5, 1, 1).plus(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue(), 1), (TemporalUnit) ChronoUnit.DAYS);
                }
                HijrahDate date3 = date(y5, 1, 1).plusDays((long) (((ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue()) - 1) * 7) + (ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR).longValue()) - 1)));
                if (resolverStyle != ResolverStyle.STRICT || date3.get(ChronoField.YEAR) == y5) {
                    return date3;
                }
                throw new DateTimeException("Strict mode rejected date parsed to a different year");
            } else if (!fieldValues.containsKey(ChronoField.DAY_OF_WEEK)) {
                return null;
            } else {
                int y6 = ChronoField.YEAR.checkValidIntValue(fieldValues.remove(ChronoField.YEAR).longValue());
                if (resolverStyle == ResolverStyle.LENIENT) {
                    return date(y6, 1, 1).plus(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue(), 1), (TemporalUnit) ChronoUnit.WEEKS).plus(Jdk8Methods.safeSubtract(fieldValues.remove(ChronoField.DAY_OF_WEEK).longValue(), 1), (TemporalUnit) ChronoUnit.DAYS);
                }
                HijrahDate date4 = date(y6, 1, 1).plus((long) (ChronoField.ALIGNED_WEEK_OF_YEAR.checkValidIntValue(fieldValues.remove(ChronoField.ALIGNED_WEEK_OF_YEAR).longValue()) - 1), (TemporalUnit) ChronoUnit.WEEKS).with(TemporalAdjusters.nextOrSame(DayOfWeek.of(ChronoField.DAY_OF_WEEK.checkValidIntValue(fieldValues.remove(ChronoField.DAY_OF_WEEK).longValue()))));
                if (resolverStyle != ResolverStyle.STRICT || date4.get(ChronoField.YEAR) == y6) {
                    return date4;
                }
                throw new DateTimeException("Strict mode rejected date parsed to a different month");
            }
        }
    }
}
