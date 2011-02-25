package com.tillg.util;

import java.util.Date;
import java.util.Random;

public class RandomHelper {
	
	public static String getRandomString() {
		Random r = new Random();
		String str = Long.toString(Math.abs(r.nextLong()), 36);
		return str;
	}
	
	public static Date getRandomDate(Date date1, Date date2) {
		/**
		 * Generates a random date between date1 and date2
		 */
		long dateMs1 = date1.getTime();
		long dateMs2 = date2.getTime();
		Random r = new Random();
		long low, high;
		if (dateMs1 < dateMs2) {
			low = dateMs1;
			high = dateMs2;
		} else {
			low = dateMs2;
			high = dateMs1;
		}
		long range = high - low;
		float percentageVal = r.nextFloat();
		long absVal = ((Float) (range * percentageVal)).longValue();
		absVal += low;
		return new Date(absVal);
	}
	
	public static Date getRandomDate(Date date) {
		/** 
		 * Generates a random date within the year before 'date'.  
		 */
		long sec = 1000; // No of ms in a sec
		long min = sec * 60;
		long hour = min * 60;
		long day = hour * 24;
		long year = 365 * day;
		Date date1 = new Date(date.getTime() - year);
		return getRandomDate(date1, date);
	}
	
	public static Date getRandomDate () {
		/**
		 * Generates a date within the last year.
		 */
		return getRandomDate(new Date());
	}
}
