package org.threeten.bp.zone;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.temporal.TemporalAdjusters;

public final class ZoneOffsetTransitionRule implements Serializable {
    private static final int SECS_PER_DAY = 86400;
    private static final long serialVersionUID = 6889046316657758795L;
    private final int adjustDays;
    private final byte dom;
    private final DayOfWeek dow;
    private final Month month;
    private final ZoneOffset offsetAfter;
    private final ZoneOffset offsetBefore;
    private final ZoneOffset standardOffset;
    private final LocalTime time;
    private final TimeDefinition timeDefinition;

    public static ZoneOffsetTransitionRule of(Month month2, int dayOfMonthIndicator, DayOfWeek dayOfWeek, LocalTime time2, boolean timeEndOfDay, TimeDefinition timeDefnition, ZoneOffset standardOffset2, ZoneOffset offsetBefore2, ZoneOffset offsetAfter2) {
        int i = dayOfMonthIndicator;
        LocalTime localTime = time2;
        Jdk8Methods.requireNonNull(month2, "month");
        Jdk8Methods.requireNonNull(localTime, "time");
        Jdk8Methods.requireNonNull(timeDefnition, "timeDefnition");
        Jdk8Methods.requireNonNull(standardOffset2, "standardOffset");
        Jdk8Methods.requireNonNull(offsetBefore2, "offsetBefore");
        Jdk8Methods.requireNonNull(offsetAfter2, "offsetAfter");
        if (i < -28 || i > 31 || i == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        } else if (!timeEndOfDay || localTime.equals(LocalTime.MIDNIGHT)) {
            return new ZoneOffsetTransitionRule(month2, dayOfMonthIndicator, dayOfWeek, time2, timeEndOfDay ? 1 : 0, timeDefnition, standardOffset2, offsetBefore2, offsetAfter2);
        } else {
            throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
        }
    }

    ZoneOffsetTransitionRule(Month month2, int dayOfMonthIndicator, DayOfWeek dayOfWeek, LocalTime time2, int adjustDays2, TimeDefinition timeDefnition, ZoneOffset standardOffset2, ZoneOffset offsetBefore2, ZoneOffset offsetAfter2) {
        this.month = month2;
        this.dom = (byte) dayOfMonthIndicator;
        this.dow = dayOfWeek;
        this.time = time2;
        this.adjustDays = adjustDays2;
        this.timeDefinition = timeDefnition;
        this.standardOffset = standardOffset2;
        this.offsetBefore = offsetBefore2;
        this.offsetAfter = offsetAfter2;
    }

    private Object writeReplace() {
        return new Ser((byte) 3, this);
    }

    /* access modifiers changed from: package-private */
    public void writeExternal(DataOutput out) throws IOException {
        int timeSecs = this.time.toSecondOfDay() + (this.adjustDays * SECS_PER_DAY);
        int stdOffset = this.standardOffset.getTotalSeconds();
        int beforeDiff = this.offsetBefore.getTotalSeconds() - stdOffset;
        int afterDiff = this.offsetAfter.getTotalSeconds() - stdOffset;
        int timeByte = (timeSecs % 3600 != 0 || timeSecs > SECS_PER_DAY) ? 31 : timeSecs == SECS_PER_DAY ? 24 : this.time.getHour();
        int stdOffsetByte = stdOffset % TypedValues.Custom.TYPE_INT == 0 ? (stdOffset / TypedValues.Custom.TYPE_INT) + 128 : 255;
        int beforeByte = (beforeDiff == 0 || beforeDiff == 1800 || beforeDiff == 3600) ? beforeDiff / 1800 : 3;
        int afterByte = (afterDiff == 0 || afterDiff == 1800 || afterDiff == 3600) ? afterDiff / 1800 : 3;
        DayOfWeek dayOfWeek = this.dow;
        out.writeInt((this.month.getValue() << 28) + ((this.dom + 32) << 22) + ((dayOfWeek == null ? 0 : dayOfWeek.getValue()) << 19) + (timeByte << 14) + (this.timeDefinition.ordinal() << 12) + (stdOffsetByte << 4) + (beforeByte << 2) + afterByte);
        if (timeByte == 31) {
            out.writeInt(timeSecs);
        }
        if (stdOffsetByte == 255) {
            out.writeInt(stdOffset);
        }
        if (beforeByte == 3) {
            out.writeInt(this.offsetBefore.getTotalSeconds());
        }
        if (afterByte == 3) {
            out.writeInt(this.offsetAfter.getTotalSeconds());
        }
    }

    static ZoneOffsetTransitionRule readExternal(DataInput in) throws IOException {
        int data = in.readInt();
        Month month2 = Month.of(data >>> 28);
        int dom2 = ((264241152 & data) >>> 22) - 32;
        int dowByte = (3670016 & data) >>> 19;
        DayOfWeek dow2 = dowByte == 0 ? null : DayOfWeek.of(dowByte);
        int timeByte = (507904 & data) >>> 14;
        TimeDefinition defn = TimeDefinition.values()[(data & 12288) >>> 12];
        int stdByte = (data & 4080) >>> 4;
        int beforeByte = (data & 12) >>> 2;
        int afterByte = data & 3;
        int timeOfDaysSecs = timeByte == 31 ? in.readInt() : timeByte * 3600;
        ZoneOffset std = ZoneOffset.ofTotalSeconds(stdByte == 255 ? in.readInt() : (stdByte - 128) * TypedValues.Custom.TYPE_INT);
        ZoneOffset before = ZoneOffset.ofTotalSeconds(beforeByte == 3 ? in.readInt() : std.getTotalSeconds() + (beforeByte * 1800));
        ZoneOffset after = ZoneOffset.ofTotalSeconds(afterByte == 3 ? in.readInt() : std.getTotalSeconds() + (afterByte * 1800));
        if (dom2 < -28 || dom2 > 31 || dom2 == 0) {
            int i = afterByte;
            int i2 = beforeByte;
            int i3 = stdByte;
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        }
        int i4 = timeOfDaysSecs;
        int i5 = afterByte;
        int i6 = beforeByte;
        int i7 = stdByte;
        return new ZoneOffsetTransitionRule(month2, dom2, dow2, LocalTime.ofSecondOfDay((long) Jdk8Methods.floorMod(timeOfDaysSecs, (int) SECS_PER_DAY)), Jdk8Methods.floorDiv(timeOfDaysSecs, (int) SECS_PER_DAY), defn, std, before, after);
    }

    public Month getMonth() {
        return this.month;
    }

    public int getDayOfMonthIndicator() {
        return this.dom;
    }

    public DayOfWeek getDayOfWeek() {
        return this.dow;
    }

    public LocalTime getLocalTime() {
        return this.time;
    }

    public boolean isMidnightEndOfDay() {
        return this.adjustDays == 1 && this.time.equals(LocalTime.MIDNIGHT);
    }

    public TimeDefinition getTimeDefinition() {
        return this.timeDefinition;
    }

    public ZoneOffset getStandardOffset() {
        return this.standardOffset;
    }

    public ZoneOffset getOffsetBefore() {
        return this.offsetBefore;
    }

    public ZoneOffset getOffsetAfter() {
        return this.offsetAfter;
    }

    public ZoneOffsetTransition createTransition(int year) {
        LocalDate date;
        byte b = this.dom;
        if (b < 0) {
            Month month2 = this.month;
            date = LocalDate.of(year, month2, month2.length(IsoChronology.INSTANCE.isLeapYear((long) year)) + 1 + this.dom);
            DayOfWeek dayOfWeek = this.dow;
            if (dayOfWeek != null) {
                date = date.with(TemporalAdjusters.previousOrSame(dayOfWeek));
            }
        } else {
            date = LocalDate.of(year, this.month, (int) b);
            DayOfWeek dayOfWeek2 = this.dow;
            if (dayOfWeek2 != null) {
                date = date.with(TemporalAdjusters.nextOrSame(dayOfWeek2));
            }
        }
        return new ZoneOffsetTransition(this.timeDefinition.createDateTime(LocalDateTime.of(date.plusDays((long) this.adjustDays), this.time), this.standardOffset, this.offsetBefore), this.offsetBefore, this.offsetAfter);
    }

    public boolean equals(Object otherRule) {
        if (otherRule == this) {
            return true;
        }
        if (!(otherRule instanceof ZoneOffsetTransitionRule)) {
            return false;
        }
        ZoneOffsetTransitionRule other = (ZoneOffsetTransitionRule) otherRule;
        if (this.month == other.month && this.dom == other.dom && this.dow == other.dow && this.timeDefinition == other.timeDefinition && this.adjustDays == other.adjustDays && this.time.equals(other.time) && this.standardOffset.equals(other.standardOffset) && this.offsetBefore.equals(other.offsetBefore) && this.offsetAfter.equals(other.offsetAfter)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int secondOfDay = ((this.time.toSecondOfDay() + this.adjustDays) << 15) + (this.month.ordinal() << 11) + ((this.dom + 32) << 5);
        DayOfWeek dayOfWeek = this.dow;
        return ((this.standardOffset.hashCode() ^ ((secondOfDay + ((dayOfWeek == null ? 7 : dayOfWeek.ordinal()) << 2)) + this.timeDefinition.ordinal())) ^ this.offsetBefore.hashCode()) ^ this.offsetAfter.hashCode();
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("TransitionRule[").append(this.offsetBefore.compareTo(this.offsetAfter) > 0 ? "Gap " : "Overlap ").append(this.offsetBefore).append(" to ").append(this.offsetAfter).append(", ");
        DayOfWeek dayOfWeek = this.dow;
        if (dayOfWeek != null) {
            byte b = this.dom;
            if (b == -1) {
                buf.append(dayOfWeek.name()).append(" on or before last day of ").append(this.month.name());
            } else if (b < 0) {
                buf.append(dayOfWeek.name()).append(" on or before last day minus ").append((-this.dom) - 1).append(" of ").append(this.month.name());
            } else {
                buf.append(dayOfWeek.name()).append(" on or after ").append(this.month.name()).append(' ').append(this.dom);
            }
        } else {
            buf.append(this.month.name()).append(' ').append(this.dom);
        }
        buf.append(" at ");
        if (this.adjustDays == 0) {
            buf.append(this.time);
        } else {
            long timeOfDaysMins = (long) ((this.time.toSecondOfDay() / 60) + (this.adjustDays * 24 * 60));
            appendZeroPad(buf, Jdk8Methods.floorDiv(timeOfDaysMins, 60));
            buf.append(':');
            appendZeroPad(buf, (long) Jdk8Methods.floorMod(timeOfDaysMins, 60));
        }
        buf.append(" ").append(this.timeDefinition).append(", standard offset ").append(this.standardOffset).append(']');
        return buf.toString();
    }

    private void appendZeroPad(StringBuilder sb, long number) {
        if (number < 10) {
            sb.append(0);
        }
        sb.append(number);
    }

    /* renamed from: org.threeten.bp.zone.ZoneOffsetTransitionRule$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition;

        static {
            int[] iArr = new int[TimeDefinition.values().length];
            $SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition = iArr;
            try {
                iArr[TimeDefinition.UTC.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition[TimeDefinition.STANDARD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum TimeDefinition {
        UTC,
        WALL,
        STANDARD;

        public LocalDateTime createDateTime(LocalDateTime dateTime, ZoneOffset standardOffset, ZoneOffset wallOffset) {
            switch (AnonymousClass1.$SwitchMap$org$threeten$bp$zone$ZoneOffsetTransitionRule$TimeDefinition[ordinal()]) {
                case 1:
                    return dateTime.plusSeconds((long) (wallOffset.getTotalSeconds() - ZoneOffset.UTC.getTotalSeconds()));
                case 2:
                    return dateTime.plusSeconds((long) (wallOffset.getTotalSeconds() - standardOffset.getTotalSeconds()));
                default:
                    return dateTime;
            }
        }
    }
}
