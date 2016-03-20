package com.frlgrd.rssstream.utils;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class DateUtils {
	public static final DateFormat DATETIME_ISO_FORMAT = new ISO8601DateFormat();
	public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat.forPattern("dd MMMM yyyy à HH:mm");
	public static final DateTimeFormatter HOUR_FORMAT = DateTimeFormat.forPattern("HH:mm");
	public static final DateTimeFormatter DATE_DAY = DateTimeFormat.forPattern("dd MMMM yyyy");
	public static final DateTimeFormatter DATE_WS = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	public static final DateTimeFormatter DATE_WEEK = DateTimeFormat.forPattern("dd/MM");
	public static final DateTimeFormatter DATE_DAY_LABEL = DateTimeFormat.forPattern("EEEE");
	public static final DateTimeFormatter LOCALE_DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");
	/**
	 * This formatter was inspired by <a href="https://github.com/JodaOrg/joda-time/blob/v2.3/src/main/java/org/joda/time/format/PeriodFormat.java#L94">PeriodFormat.wordbased()</a>.
	 * It uses default full suffix for values weeks, months and years. For smaller values it uses a shorter suffix.
	 */
	public static final PeriodFormatter PERIOD_FORMATTER_SHORT;
	public static final PeriodFormatter DURATION_FORMAT = new PeriodFormatterBuilder()
			.appendHours()
			.appendSeparatorIfFieldsBefore(":")
			.minimumPrintedDigits(2)
			.appendMinutes()
			.appendSeparator(":")
			.minimumPrintedDigits(2)
			.appendSeconds()
			.toFormatter();
	private static final String BUNDLE_NAME = "org.joda.time.format.messages";

	static {
		ResourceBundle b = ResourceBundle.getBundle(BUNDLE_NAME, Locale.getDefault());
		String[] variants = {
				b.getString("PeriodFormat.space"), b.getString("PeriodFormat.comma"),
				b.getString("PeriodFormat.commandand"), b.getString("PeriodFormat.commaspaceand")};
		PERIOD_FORMATTER_SHORT = new PeriodFormatterBuilder()
				.appendYears()
				.appendSuffix(b.getString("PeriodFormat.year"), b.getString("PeriodFormat.years"))
				.appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
				.appendMonths()
				.appendSuffix(b.getString("PeriodFormat.month"), b.getString("PeriodFormat.months"))
				.appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
				.appendWeeks()
				.appendSuffix(b.getString("PeriodFormat.week"), b.getString("PeriodFormat.weeks"))
				.appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
				.appendDays()
				.appendSuffix(b.getString("PeriodFormat.day"), b.getString("PeriodFormat.days"))
				.appendSeparator(b.getString("PeriodFormat.commaspace"), b.getString("PeriodFormat.spaceandspace"), variants)
				.appendHours()
				.appendSeparatorIfFieldsBefore("H")
				.appendMinutes()
				.appendSeparatorIfFieldsBefore("min")
				.appendSeconds()
				.appendSeparatorIfFieldsBefore("s")
				.toFormatter();
	}

	public static String getHours() {
		return getPrint(DateTime.now(), HOUR_FORMAT);
	}

	private static String getPrint(DateTime dateTime, DateTimeFormatter dateTimeFormatMysql) {
		return dateTimeFormatMysql.print(dateTime);
	}

	public static String toShortDate(DateTime dateTime) {
		return dateTime == null ? null : DateTimeFormat.shortDate().print(dateTime);
	}

	public static String format(DateTimeFormatter formatter, DateTime dateTime) {
		return getPrint(dateTime, formatter.withLocale(Locale.FRANCE));
	}

	public static int daysBetween(DateTime d1, DateTime d2) {
		return Math.abs(Days.daysBetween(d1, d2).getDays());
	}

	public static boolean sameDay(DateTime then, DateTime now) {
		if (then == null || now == null) {
			return false;
		}
		return sameDay(then.toDate(), now.toDate());
	}

	public static boolean sameDay(Date then, Date now) {

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(then);
		cal2.setTime(now);

		return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
				cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);

	}
}