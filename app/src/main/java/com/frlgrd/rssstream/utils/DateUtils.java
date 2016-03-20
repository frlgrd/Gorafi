package com.frlgrd.rssstream.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	public static final SimpleDateFormat RFC822Formatter = new SimpleDateFormat("EEE, dd MMMM yyyy HH:mm:ss Z", Locale.US);

	public static DateTime format(String date) {
		try {
			return new DateTime(RFC822Formatter.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String toMediumDate(DateTime dateTime) {
		return dateTime == null ? null : DateTimeFormat.mediumDate().print(dateTime);
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