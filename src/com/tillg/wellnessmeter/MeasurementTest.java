package com.tillg.wellnessmeter;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.tillg.util.RandomHelper;


public class MeasurementTest {
	static final int NO_TEST_RUNS = 200;

	public static Measurement getRandomMeasurement() {
		Random r = new Random();
		int valuesSize = r.nextInt(MeasurementType.getNoOfMeasurementTypes()) + 1;
		Map<String,String> valuesStr = new HashMap<String,String>();
		for (int i=0; i<valuesSize; i++) {
			// Let's find a typeId that we haven'tused yet
			String typeId = MeasurementTypeTest.getRandomMeasurementTypeId();
			while (valuesStr.keySet().contains(typeId))
				typeId = MeasurementTypeTest.getRandomMeasurementTypeId();
			valuesStr.put(typeId, MeasurementTypeTest.getRandomMeasuerementValueStr(typeId));
		}
		Measurement m = new Measurement(RandomHelper.getRandomDate(),valuesStr);
		return m;
	}

	@Test
	public void checkToFromString() throws Exception {
		Measurement m1 = getRandomMeasurement();
		String mStr = m1.toString();
		Measurement m2 = Measurement.fromString(mStr);
		assertEquals(m1, m2);
	}
	
	@Test
	public void checkToFromStringMult() throws Exception {
		for (int i=0;i<NO_TEST_RUNS;i++) {
			checkToFromString();
		}
	}
	
	@Test
	public void checkFromToStringForMeasurementWithEmptyValues () {
		/**
		 * When saving & reading measurement with empty values (i.e. a correct typeId in the Map 
		 * but no value with it) we might get errors.
		 */
		Map<String,String> valuesStr = new HashMap<String,String>();
		valuesStr.put(MeasurementType.WEIGHT, "71");
		valuesStr.put(MeasurementType.WELLNESS, "");
		Measurement m = new Measurement(new Date(), valuesStr);
		String mStr = m.toString();
		Measurement m1 = null;
		try {
			m1 = Measurement.fromString(mStr);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
		assertEquals(m, m1);
	}
}
