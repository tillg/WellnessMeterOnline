package com.tillg.wellnessmeter;

import java.io.BufferedReader;
import java.io.Serializable;
import java.io.StringReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.tillg.util.DateHelper;

public class UserMeasurements implements Serializable {

	private static final long serialVersionUID = 1L;
	private static String NEWLINE = System.getProperty("line.separator");
	private static final String PROP_USERID = "PROP_USERID";
	private static final String PROP_MEASUREMENTS = "PROP_MEASUREMENTS";
	private static final String PROP_MEASUREMENT_TYPES = "PROP_MEASUREMENT_TYPES";
	private static final String USER_MEASUREMENTS = "USER_MEASUREMENTS";
	
	private static final Logger LOG = Logger.getLogger(UserMeasurements.class.getName());

	private String userId= null;
	private List<MeasurementType> measurementTypes;
	private List<Measurement> measurements;

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public List<MeasurementType> getMeasurementTypes() {
		if (this.measurementTypes != null)
			Collections.sort(this.measurementTypes);
		return this.measurementTypes;
	}

	public void setMeasurementTypes(List<MeasurementType> measurementTypes) {
		this.measurementTypes = measurementTypes;
	}

	public String measurementsToString () {
		if (measurements == null)
			return "";
		sortMeasurements();
		Iterator<Measurement> it = measurements.iterator();
		String s = "";
			while (it.hasNext()) {
				Measurement m = it.next();
				s += m.toString();
				s += NEWLINE;
			}
		return s;
	}
	
	public static List<Measurement> measurementsFromString(String str) {
		ArrayList<Measurement> measurements = new ArrayList<Measurement>();
		try{
			BufferedReader in = new BufferedReader(new StringReader(str));
		    String inputLine;
	            while ((inputLine = in.readLine( )) != null) {
	            	Measurement m = Measurement.fromString(inputLine);
	            	measurements.add(m);
	            }
	            in.close( );
		} catch (Exception e){
			LOG.warning("Error: " + e.getMessage());
		}
		return measurements;
	}

	private void sortMeasurements() {
		Collections.sort(this.measurements);
	}

	public String measurementTypesToString() {
		if (measurementTypes == null)
			return "";
		sortMeasurementTypes();
		Iterator<MeasurementType> it = measurementTypes.iterator();
		String s = "";
		while (it.hasNext()) {
			s += it.next().getTypeId();
			if (it.hasNext())
				s += NEWLINE;
		}
		return s.trim();
	}

	public static List<MeasurementType> measurementTypesFromString(String str) {
		// TODO: Richtig handeln, wenn aus dem Store Mist ausgelesen wird. Es darf keine null's in der Liste der MeasurementTypes geben!
		ArrayList<MeasurementType> measurementTypes = new ArrayList<MeasurementType>();
		try{
			BufferedReader in = new BufferedReader(new StringReader(str));
		    String inputLine;
	            while ((inputLine = in.readLine( )) != null) {
	            	MeasurementType m = MeasurementType.getMeasurementTypeById(inputLine.trim());
	            	if (m != null)
	            		measurementTypes.add(m);
	            }
	            in.close( );
		} catch (Exception e){
			LOG.warning("Error: " + e.getMessage());
		}
		return measurementTypes;
	}

	private void sortMeasurementTypes () {
		Collections.sort(this.measurementTypes);
	}

	@Override
	public boolean equals (Object o) {
		if (!(o instanceof UserMeasurements))
			return false;
		return (this.toString().trim().equals(o.toString().trim()));
	}
	
	public void setUserId (String userId) {
		this.userId = userId;
		if (! this.readFromDatastore()) {
			this.initDefaults();
		}
	}
	
	private void initDefaults() {
		this.initMeasurementTypesDefaults();
		this.initMeasurementsDefaults();
	}
	
	private void initMeasurementTypesDefaults () {
		// Fills the UserMeasurements with default values
		this.measurementTypes = new ArrayList<MeasurementType> ();
		this.measurementTypes.add(MeasurementType.getMeasurementTypeById(MeasurementType.WEIGHT));
		this.measurementTypes.add(MeasurementType.getMeasurementTypeById(MeasurementType.WELLNESS));
	}
	private void initMeasurementsDefaults () {
		this.measurements = new ArrayList<Measurement>();
	}

	public String getUserId () {
		return userId;
	}
	
	public String toString () {
		String s ="USerId: " + userId + NEWLINE;
		s += "MeasurementTypes: " + measurementTypesToString() + NEWLINE;
		s += "Measurements: " + measurementsToString();
		return s.trim();
	}
	
	public void writeToDatastore () {
		// Writes the user weights to Googles Datastore
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity um = new Entity(USER_MEASUREMENTS, userId);
		um.setProperty(PROP_USERID, this.userId);
		Text measurementsText = new Text(this.measurementsToString());
		um.setProperty(PROP_MEASUREMENTS, measurementsText);
		Text measurementTypesText = new Text (measurementTypesToString());
		um.setProperty(PROP_MEASUREMENT_TYPES, measurementTypesText);
		datastore.put(um);
	}

	public boolean readFromDatastore() {
		this.measurements = new ArrayList<Measurement>();
		this.measurementTypes = new ArrayList<MeasurementType>();
		if (userId == null) 
			return false;

		// Get the stuff from the Datastore
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key k = KeyFactory.createKey(USER_MEASUREMENTS, this.userId);
		Entity um;
		try {
			um = datastore.get(k);
			Text measurementsText = (Text) um.getProperty(PROP_MEASUREMENTS);
			String measurementsStr = measurementsText.getValue();
			Text measurementTypesText = (Text) um.getProperty(PROP_MEASUREMENT_TYPES);
			String measurementTypesStr = measurementTypesText.getValue();
			this.measurements = measurementsFromString(measurementsStr);
			this.measurementTypes = measurementTypesFromString(measurementTypesStr);
			
			// Make sure we don't start with no MeasurementTypes at all
			if (measurementTypes.size() == 0)
				initMeasurementTypesDefaults();
		} catch (EntityNotFoundException e) {
			LOG.info("Could not load Measurements for userId " + this.userId);
			return false;
		}
		return true;
	}
	
	public String getChartUrl () {
		return "https://chart.googleapis.com/chart?chxt=x,x,y,y&cht=lc&chd=s:cEAELFJHHHKUju9uuXUc&chco=76A4FB&chls=2.0&chs=700x300";
	}
	
	public List<Measurement> getMeasurementsForDisplay () {
		Collections.sort(this.measurements);
		return this.measurements;
	}
	
	public List<String> getMeasurementHeadersForDisplay () {
		List<Measurement> measurements = this.getMeasurementsForDisplay();
		Set<String> headersSet = new HashSet<String> ();
		Iterator<Measurement> it = measurements.iterator();
		while (it.hasNext()){
			Measurement m = it.next();
			headersSet.addAll(m.getValuesStr().keySet());
		}
		List<String> headersList = new ArrayList<String>(headersSet);
		Collections.sort(headersList);
		return headersList;
	}
	
	public Measurement getMeasurementByDate(Date date) {
		Iterator<Measurement> it = measurements.iterator();
		while (it.hasNext()) {
			Measurement m = it.next();
			if (m.getDate().equals(date))
				return m;
		}
		return null;
	}
	
	public boolean containsDate(Date date) {
		return (this.getMeasurementByDate(date) != null);
	}
	
	public void addMeasurement(Measurement m) {
		if (measurements == null)
			initMeasurementsDefaults();
		
		// Check if we already have a measurement for that date
		if (this.containsDate(m.getDate())) {
			Measurement m0 = this.getMeasurementByDate(m.getDate());
			measurements.remove(m0);
			m0.completeWith(m);
			m = m0;
		}
		measurements.add(m);
		this.writeToDatastore();
	}
	
	public void addMeasurement(Date date, Map<String, String> valuesStr) {
		// TODO: Check that the passed valuesStr is correct, i.e. it only contains proper types and corresponding values.
		Measurement m = new Measurement(date, valuesStr);
		this.addMeasurement(m);
	}
	
	public void addMeasurement(String dateStr, Map <String, String> valuesStr) throws Exception {
		DateFormat formatter = DateHelper.ENTRY_FORMATTER_SHORT;
		Date date;
		try {
			date = formatter.parse(dateStr);
		} catch (Exception e) {
			throw new Exception("Verstehe nicht das Datum '" + dateStr + "'");
		}
		addMeasurement(date, valuesStr);
	}



}
