package com.tillg.wellnessmeter;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import com.tillg.util.DateHelper;

public class Measurement implements Serializable, Comparable<Measurement> {
	
	private static final long serialVersionUID = 1L;
	static DateFormat TECH_FORMATTER_DATE = DateHelper.TECH_FORMATTER_DATE;
	private static String NEWLINE = System.getProperty("line.separator");
	private static String TAB = "\t";
	public static final String NA_VALUE = "-";
	
	private static final Logger LOG = Logger.getLogger(Measurement.class.getName());

	private Date date = null;
	private Map<String,String> valuesStr;

	public Measurement(Date date, Map<String, String> valuesStr) {
		this.date = date;
		this.valuesStr = valuesStr;
	}
	
	public Set<String> getTypeIds() {
		/**
		 * Returns a set containing the typeIds of all the types used in this measurement.
		 */
		return valuesStr.keySet();
	}
	
	@Override
	public boolean equals (Object o) {
		if (!(o instanceof Measurement))
			return false;
		return (this.toString().trim().equals(o.toString().trim()));
	}
	

	public String toString() {
		String s = TECH_FORMATTER_DATE.format(this.date);
		ArrayList<String> keys = new ArrayList<String>(valuesStr.keySet());
		Collections.sort(keys);
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String typeId = it.next();
			s += TAB + typeId + TAB;
			String valueStr = valuesStr.get(typeId);
			String string = "";
			try {
				string = MeasurementType.valueToString(typeId, valueStr);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.warning("Error when converting to String: " + valueStr);
			}
			s += string;
		}
		return s.trim();
	}
	
	public static Measurement fromString(String str) throws Exception {
		Date date;
		Map<String,String> valuesStr = new HashMap<String,String>();
		String dateStr;
		str = str.trim();
		StringTokenizer st = new StringTokenizer(str, NEWLINE + TAB, false);
		
		// Read the date
		if (st.hasMoreTokens()) {
			dateStr = st.nextToken();
		}
		else {
			throw new Exception ("Konnte kein Datum für den Messpunkt finden in: " + str);
		}
		date = TECH_FORMATTER_DATE.parse(dateStr);

		// Read the different values
		while (st.hasMoreTokens()) {
			String typeId = st.nextToken();
			String string = st.nextToken();
			String valueStr = MeasurementType.stringToValue(typeId, string);
			valuesStr.put(typeId, valueStr);
		}
		Measurement m = new Measurement(date, valuesStr);
		return m;
	}
	
	public Map<String, String> getValuesStr () {
		return this.valuesStr;
	}

	@Override
	public int compareTo(Measurement o) {
		return this.date.compareTo(o.date);
	}
	public String getDateStr4Display() {
		return DateHelper.DISPLAY_FORMATTER.format(getDate());
	}

	public Date getDate() {
		return this.date;
	}
	
	public String getValueForDisplay(String type) {
		if (valuesStr.keySet().contains(type))
			return valuesStr.get(type);
		return "-";
	}
	
	public boolean containsType (String type) {
		/**
		 * Check whether the type "type" has a set value in this measurement.
		 */
		return this.getValuesStr().keySet().contains(type);
	}
	
	public void completeWith(Measurement m) {
		/**
		 * Completes this measurement with the additional measures from m.
		 * Measures that are already set in this measurement will not be
		 * modified.
		 */
		
		// TODO Write a test for this!
		
		// Check they have the same date
		if (!m.getDate().equals(this.getDate()))
			return;
		
		Map<String, String> otherValuesStr = m.getValuesStr();
		otherValuesStr.putAll(this.valuesStr);
		this.valuesStr = otherValuesStr;
	}
	
	public boolean validate() {
		/**
		 * Validates & fixes the Measurement.
		 * Rules against we validate:
		 * - All typeIds must be valid ones
		 */
		
		Iterator<String> it = valuesStr.keySet().iterator();
		while (it.hasNext())
			if (!MeasurementType.isMeasurementTypeId(it.next()))
				return false;
		return true;
	}
	
}
