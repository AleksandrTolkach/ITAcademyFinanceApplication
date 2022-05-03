package by.tolkach.schedulerAccount.dto;

public enum ScheduleTimeUnit {

    SECOND(ScheduleTimeUnit.SECOND_SCALE),
    MINUTE(ScheduleTimeUnit.MINUTE_SCALE),
    HOUR(ScheduleTimeUnit.HOUR_SCALE),
    DAY(ScheduleTimeUnit.DAY_SCALE),
    WEEK(ScheduleTimeUnit.WEEK_SCALE),
    MONTH(ScheduleTimeUnit.MONTH_SCALE),
    YEAR(ScheduleTimeUnit.YEAR_SCALE);

    private static final long SECOND_SCALE = 1L;
    private static final long MINUTE_SCALE = 60L * SECOND_SCALE;
    private static final long HOUR_SCALE = 60L * MINUTE_SCALE;
    private static final long DAY_SCALE = 24L * HOUR_SCALE;
    private static final long WEEK_SCALE = 7L * DAY_SCALE;
    private static final long MONTH_SCALE = 30L * DAY_SCALE;
    private static final long YEAR_SCALE = 12L * MONTH_SCALE;

    private final long scale;
    private final long maxSecs;
    private final int secRatio;

    ScheduleTimeUnit(long s) {
        this.scale = s;
        long sr = (s >= SECOND_SCALE) ? (s / SECOND_SCALE) : (SECOND_SCALE / s);
        this.secRatio = (int)sr;
        this.maxSecs = Long.MAX_VALUE / sr;
    }

    private static long cvt(long d, long dst, long src) {
        long r, m;
        if (src == dst)
            return d;
        else if (src < dst)
            return d / (dst / src);
        else if (d > (m = Long.MAX_VALUE / (r = src / dst)))
            return Long.MAX_VALUE;
        else if (d < -m)
            return Long.MIN_VALUE;
        else
            return d * r;
    }

    public long toSeconds(long duration) {
        long s, m;
        if ((s = scale) <= SECOND_SCALE)
            return (s == SECOND_SCALE) ? duration : duration / secRatio;
        else if (duration > (m = maxSecs))
            return Long.MAX_VALUE;
        else if (duration < -m)
            return Long.MIN_VALUE;
        else
            return duration * secRatio;
    }

    public long toMinutes(long duration) {
        return cvt(duration, MINUTE_SCALE, scale);
    }

    public long toHours(long duration) {
        return cvt(duration, HOUR_SCALE, scale);
    }

    public long toDays(long duration) {
        return cvt(duration, DAY_SCALE, scale);
    }

    public long toWeek(long duration) {
        return cvt(duration, WEEK_SCALE, scale);
    }

    public long toMonth(long duration) {
        return cvt(duration, MONTH_SCALE, scale);
    }

    public long toYear(long duration) {
        return cvt(duration, YEAR_SCALE, scale);
    }
}
