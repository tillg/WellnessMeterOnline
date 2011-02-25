package com.tillg.wellnessmeter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.tillg.util.FloatHelper;

public class MeasurementType implements Serializable, Comparable<MeasurementType> {
	
	private static final long serialVersionUID = 1L;
	private String typeId;
	private String label;
	private String subLabel;
	private String htmlType;
	private Integer order;
	
	public static final String WEIGHT = "WEIGHT";
	public static final String WELLNESS = "WELLNESS";
	
	public static MeasurementType getMeasurementTypeById(String typeId) {
		if (typeId.equals(WEIGHT)) 
			return getMeasurementTypeWeight();
		if (typeId.equals(WELLNESS))
			return getMeasurementTypeWellness();
		return null;
	}
	
	public static String getLabelById(String typeId) {
		return getMeasurementTypeById(typeId).getLabel();
	}
	
	public static Set<String> getMeasurementTypeIds() {
		HashSet<String> typeIds = new HashSet<String>();
		typeIds.add(WEIGHT);
		typeIds.add(WELLNESS);
		return typeIds;
	}

	public static Set<MeasurementType> getMeasurementTypes() {
		HashSet<MeasurementType> types = new HashSet<MeasurementType>();
		types.add(getMeasurementTypeWeight());
		types.add(getMeasurementTypeWellness());
		return types;
	}
	
	public static int getNoOfMeasurementTypes() {
		return getMeasurementTypeIds().size();
	}
	
	public static boolean isMeasurementTypeId (String type) {
		return (getMeasurementTypeIds().contains(type));
	}
	
	public static String valueToString (String typeId, String valueStr) throws Exception {
		/**
		 * Transforms a value of a given typeId to a string in order to persist it to the DataStore.
		 * Requires that the generated String does not contain any blanks and can be catches as one
		 * token by the tokenizer.
		 */
		// TO DO Test this!/
		// TO DO: Test that for every valid typeId we get something different to null!

		// Check that the typeId is a valid one
		if (! MeasurementType.isMeasurementTypeId(typeId))
			throw new Exception ("Falsche typeId: " + typeId);
		
		if (typeId.equals(WEIGHT)) {
			if ((valueStr == null) || (valueStr.equals("")))
				return Measurement.NA_VALUE;
			return valueStr;
		}
		if (typeId.equals(WELLNESS)) {
			if ((valueStr == null) || (valueStr.equals("")))
				return Measurement.NA_VALUE;
			return valueStr;
		}
		return null; // We should never reach this line!
	}
	
	public static String stringToValue (String typeId, String string) throws Exception {
		// TO DO Test this!
		// TO DO: Test that for every valid typeId we get reasonable stuff for reasonable entries and vice versa
		// TO DO: Numbers should be "simplified". I.e. "0.0000" should be modified to "0".
		
		// Check that the typeId is a valid one
		if (! MeasurementType.isMeasurementTypeId(typeId))
			throw new Exception ("Falsche typeId: " + typeId);

		if (typeId.equals(WEIGHT)) {
			// Make sure we have a valid float here
			if (FloatHelper.isValidFloatStr(string) || string.equals(Measurement.NA_VALUE))
				return string;
			throw new Exception ("Falscher Gewichtswert: " + string);
		}
		
		if (typeId.equals(WELLNESS)) {
			// Make sure we have a valid float here
			if (FloatHelper.isValidFloatStr(string) || string.equals(Measurement.NA_VALUE))
				return string;
			throw new Exception ("Falscher Wellness-Wert: " + string);
		}
		return null; // We should never reach this line!
	}

	private static MeasurementType getMeasurementTypeWeight() {
		MeasurementType m = new MeasurementType();
		m.typeId = WEIGHT;
		m.label = "Gewicht";
		m.subLabel = "in kg";
		m.htmlType = "TEXT";
		m.order = 2;
		return m;
	}

	private static MeasurementType getMeasurementTypeWellness() {
		MeasurementType m = new MeasurementType();
		m.typeId = WELLNESS;
		m.label = "Wellness";
		m.subLabel = "0:Bššh - 5:Jupiduh!";
		m.htmlType = "TEXT";
		m.order = 1;
		return m;
	}

	
	public String getTypeId() {
		return typeId;
	}
	
	public String getLabel() {
		return label;
	}
	public String getSubLabel() {
		return subLabel;
	}
	public String getHtmlType() {
		return htmlType;
	}

	private MeasurementType() {
	}
	
	@Override
	public String toString () {
		return "";
	}

	@Override
	public int compareTo(MeasurementType o) {
		return (this.order.compareTo(o.order));
	}
	
	@Override
	public boolean equals(Object o) {
		if (! o.getClass().equals(this.getClass()))
			return false;
		return (this.getTypeId().equals(((MeasurementType) o).getTypeId()));
	}
}
