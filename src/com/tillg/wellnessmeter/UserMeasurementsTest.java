package com.tillg.wellnessmeter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.tillg.util.RandomHelper;


public class UserMeasurementsTest {
	static final int NO_TEST_RUNS = 200;

    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

	public static UserMeasurements getRandomUserMeasurements() {
		UserMeasurements um = new UserMeasurements();
		Random r = new Random();
		
		// UserId
		um.setUserId(RandomHelper.getRandomString());

		// Measurement types
		um.setMeasurementTypes(new ArrayList<MeasurementType>(MeasurementTypeTest.getRandomMeasurementTypes()));
		
		// Measures
		int numMeasures = r.nextInt(29) + 1;
		List<Measurement> measurements = new ArrayList<Measurement>();
		for (int i=0; i < numMeasures; i++) {
			measurements.add(MeasurementTest.getRandomMeasurement());
		}
		um.setMeasurements(measurements);
		return um;
	}

	@Test
	public void checkReadWriteToDatastore () {
		UserMeasurements um1 = UserMeasurementsTest.getRandomUserMeasurements();
		um1.writeToDatastore();

		UserMeasurements um2 = new UserMeasurements();
		um2.setUserId(um1.getUserId());
		um2.readFromDatastore();
		
		assertEquals(um1, um2);
	}
	
	@Test
	public void checkReadWriteToDatastoreMult () {
		for (int i = 0;i<NO_TEST_RUNS; i++) {
			checkReadWriteToDatastore();
		}
	}
	

}
