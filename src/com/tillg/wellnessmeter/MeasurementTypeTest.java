package com.tillg.wellnessmeter;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class MeasurementTypeTest {
	static final int NO_TEST_RUNS = 200;
	
	public static String getRandomMeasurementTypeId() {
		/** 
		 * Returns radomely a valid MeasurementTypeId.
		 */
		Random r = new Random();
		String[] typeIdArray = MeasurementType.getMeasurementTypeIds().toArray(new String[0]);
		int typeNo = r.nextInt(typeIdArray.length);
		return typeIdArray[typeNo];
	}
	public static MeasurementType getRandomMeasurementType() {
		String typeId = getRandomMeasurementTypeId();
		MeasurementType type = MeasurementType.getMeasurementTypeById(typeId);
		return type;
	}
	
	public static String getRandomMeasuerementValueStr (String typeId) {
		/** 
		 * Returns a correct, random value for the gives type. 
		 */
		Random r = new Random();
		if (typeId == MeasurementType.WEIGHT) {
			return "" + r.nextFloat();
		}
		if (typeId == MeasurementType.WELLNESS) {
			return "" + r.nextInt(4) + 1;
		}
		return null; // We should never get here!
	}
	public static Set<MeasurementType> getRandomMeasurementTypes() {
		return MeasurementType.getMeasurementTypes();
	}
	
	@Test
	public void checkValueToStringFixedValues() throws Exception {
		String v;
		String s;
		
		v = "77.4";
		s = MeasurementType.valueToString(MeasurementType.WEIGHT, v);
		assertEquals("77.4", s);

		v = "12345678.456789";
		s = MeasurementType.valueToString(MeasurementType.WEIGHT, v);
		assertEquals("12345678.456789", s);

		v = "0.000000";
		s = MeasurementType.valueToString(MeasurementType.WEIGHT, v);
		assertEquals("0.000000", s);

		v = "-50.0000";
		s = MeasurementType.valueToString(MeasurementType.WEIGHT, v);
		assertEquals("-50.0000", s);
	}

	@Test
	public void checkValueToStringRandomValues() throws Exception {
		String v;
		String s;
		Random r = new Random();
		
		for (int i=0; i < NO_TEST_RUNS; i++) {
			v = "" + r.nextFloat();
			s = MeasurementType.valueToString(MeasurementType.WEIGHT, v);
			assertEquals(v, s);
		}
	}
	
	@Test
	public void checkStringToValueFixedValues() throws Exception {
		String v;
		String s;
		
		s = "77.4";
		v = MeasurementType.stringToValue(MeasurementType.WEIGHT, s);
		assertEquals("77.4", v);

		s = "12345678.456789";
		v = MeasurementType.stringToValue(MeasurementType.WEIGHT, s);
		assertEquals("12345678.456789", v);

		s = "0.000000";
		v = MeasurementType.stringToValue(MeasurementType.WEIGHT, s);
		assertEquals("0.000000", v);

		s = "-50.0000";
		v = MeasurementType.stringToValue(MeasurementType.WEIGHT, s);
		assertEquals("-50.0000", v);
	}

	@Test
	public void checkStringToValueRandomValues() throws Exception {
		String v;
		String s;
		Random r = new Random();
		
		for (int i=0; i < NO_TEST_RUNS; i++) {
			s = "" + r.nextFloat();
			v = MeasurementType.stringToValue(MeasurementType.WEIGHT, s);
			assertEquals(s, v);
		}
	}

	@Test
	public void checkRandomMeasurementTypeId() {
		for (int i=0;i<NO_TEST_RUNS;i++) {
			String typeId = getRandomMeasurementTypeId();
			assertTrue(MeasurementType.isMeasurementTypeId(typeId));
		}
	}
	
	@Test
	public void checkRandomMeasurementType() {
		for (int i=0;i<NO_TEST_RUNS;i++) {
			MeasurementType m = getRandomMeasurementType();
			assertTrue(m != null);
		}
	}
	

}
