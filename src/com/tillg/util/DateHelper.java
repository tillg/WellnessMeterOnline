package com.tillg.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
	
	private static final int MILLISECS_PER_DAY = (1000*60*60*24);
	public static DateFormat DISPLAY_FORMATTER = new SimpleDateFormat("EEEE dd.MM.yyyy", Locale.GERMAN);
	public static DateFormat DISPLAY_FORMATTER_SHORT = new SimpleDateFormat("dd.MM.", Locale.GERMAN);
	public static DateFormat TECH_FORMATTER_DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat ENTRY_FORMATTER_SHORT = new SimpleDateFormat("dd.MM.yyyy");
	
	public static int dateDiffInDays(Date date1, Date date2) {
		Date earlyDate = date1;
		Date lateDate = date2;
		if (earlyDate.after(lateDate)) {
			earlyDate = date2;
			lateDate = date1;
		}
		int deltaDays = new Long ((lateDate.getTime() - earlyDate.getTime())/ MILLISECS_PER_DAY).intValue();
		return deltaDays;
	}
}
