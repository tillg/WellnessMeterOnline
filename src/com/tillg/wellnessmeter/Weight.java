package com.tillg.wellnessmeter;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import com.tillg.util.DateHelper;

public class Weight implements Comparable<Weight>, Serializable {

	private static final long serialVersionUID = 1L;
	static DateFormat FORMATTER = DateHelper.TECH_FORMATTER_DATE;
	public static  Set<Weight> measurements = new HashSet<Weight>() ;

	Date date = null;
	float weightValue = 0;

	public Weight(){
	}
	public Weight(Date date, float weightValue) {
		this.date = date;
		this.weightValue = weightValue;
	}
	public Date getDate() {
		return date;
	}
	public String getDateStr4Display() {
		return DateHelper.DISPLAY_FORMATTER.format(getDate());
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getWeightValue() {
		return weightValue;
	}
	public void setWeightValue(float weight) {
		this.weightValue = weight;
	}
	public static void addMeasurement(Weight m) {
		measurements.add(m);
	}	
	public String toString() {
		return FORMATTER.format(this.date) + "\t" + this.getWeightValue();
	}
	public static Weight fromString(String str) throws Exception {
		Date date;
		float weightValue;
		String dateStr;
		String weightValueStr;
		str = str.trim();
		StringTokenizer st = new StringTokenizer(str);
		if (st.hasMoreTokens()) {
			dateStr = st.nextToken();
		}
		else {
			throw new Exception ("Didn't find the needed data for a Weight in " + str);
		}
		if (st.hasMoreTokens()) {
			weightValueStr = st.nextToken();
		}
		else {
			throw new Exception ("Didn't find the needed data for a Weight in " + str);
		}
		date = FORMATTER.parse(dateStr);
		weightValue = Float.parseFloat(weightValueStr);	
		Weight w = new Weight(date, weightValue);
		return w;
	}
	public int compareTo(Weight o) {
		if (!(o instanceof Weight))
			throw new ClassCastException("A Weight object expected.");
		return this.date.compareTo(((Weight) o).date);
	}
}
