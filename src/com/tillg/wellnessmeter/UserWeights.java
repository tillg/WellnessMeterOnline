package com.tillg.wellnessmeter;

import java.io.BufferedReader;
import java.io.Serializable;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.tillg.util.ChartURL;
import com.tillg.util.DateHelper;

public class UserWeights implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Weight> weights;
	private String NEWLINE = System.getProperty("line.separator");
	private static final Logger LOG = Logger.getLogger(UserWeights.class.getName());
	private static final String PROP_USERID = "PROP_USERID";
	private static final String PROP_WEIGHTS = "PROP_WEIGHTS";
	

	private String userId= null;
	
	// Constructor
	public UserWeights() {
		LOG.info("Creating new UserWeights-Object.");
//		this.initWeightsWithDummy();
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
		this.readFromDatastore();
	}
	
	public void addWeight(Weight weight) {
		if (weights == null)
			weights = new ArrayList<Weight> ();
		weights.add(weight);
		this.writeToDatastore();
	}

	public void addWeight(Date date, float weightValue) {
		Weight m = new Weight(date, weightValue);
		this.addWeight(m);
	}
	
	public void addWeight(String dateStr, String weightValueStr) throws Exception {
		DateFormat formatter = DateHelper.ENTRY_FORMATTER_SHORT;
		Date date = null;
		float weightValue = 0;
		try {
			date = formatter.parse(dateStr);
		} catch (Exception e) {
			throw new Exception("Verstehe nicht das Datum '" + dateStr + "'");
		}
		try {
			weightValue = Float.parseFloat(weightValueStr);
		} catch (Exception e) {
			throw new Exception("Verstehe nicht die Zahl '" + weightValueStr + "'");
		}
		addWeight(date, weightValue);
	}

	public void writeToDatastore () {
		// Writes the user weights to Googles Datastore
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity uw = new Entity("UserWeights", userId);
		uw.setProperty(PROP_USERID, this.userId);
		uw.setProperty(PROP_WEIGHTS, this.toString());
		datastore.put(uw);
	}
	
	public void readFromDatastore() {
		
		this.weights = new ArrayList<Weight>();
		if (userId == null) 
			return;

		// Get the stuff from the Datastore
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Key k = KeyFactory.createKey("UserWeights", this.userId);
		Entity uw;
		try {
			uw = datastore.get(k);
			String weightsStr = (String) uw.getProperty(PROP_WEIGHTS);
			this.fromString(weightsStr);
		} catch (EntityNotFoundException e) {
			LOG.info("Could not load UserWeights for userId " + this.userId);
		}
	}
	
	public String toString () {
		Iterator<Weight> it = weights.iterator();
		String s = new String();
			while (it.hasNext()) {
				Weight w = it.next();
				s += w.toString();
				s += NEWLINE;
			}
		return s;
	}
	
	public void fromString (String str) {
		// From the String we only read the weights - the userId must already be set
		weights = new ArrayList<Weight>();
		if (this.userId == null)
			return;
			
		try{
			// Create file 
			BufferedReader in = new BufferedReader(new StringReader(str));
		    String inputLine;
	            while ((inputLine = in.readLine( )) != null) {
	                addWeight(Weight.fromString(inputLine));
	            }
	            in.close( );
		} catch (Exception e){//Catch exception if any
			LOG.warning("Error: " + e.getMessage());
		}
		
	}

//	public static void writeToFile () {
//		String FILE = "weights.txt";
//		Iterator<Weight> it = weights.iterator();
//		try{
//			// Create file 
//			FileWriter fstream = new FileWriter(FILE);
//			BufferedWriter out = new BufferedWriter(fstream);
//			while (it.hasNext()) {
//				Weight w = it.next();
//				out.write(w.toString());
//				out.newLine();
//			}
//			out.close();
//		} catch (Exception e){//Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}
//	}

//	public static void readFromFile () {
//		Logger l = java.util.logging.Logger.getAnonymousLogger();
//		weights = new ArrayList<Weight>();
//		try{
//			// Create file 
//			FileReader fstream = new FileReader(FILE);
//			BufferedReader in = new BufferedReader(fstream);
//		    String inputLine;
//	            while ((inputLine = in.readLine( )) != null) {
//	                addWeight(Weight.fromString(inputLine));
//	            }
//	            in.close( );
//		} catch (Exception e){//Catch exception if any
//			System.err.println("Error: " + e.getMessage());
//		}
//	}

	@SuppressWarnings("unused")
	private void initWeightsWithDummy() {
		// Create & fill an array with dummy data if we don't have any weights
		// yet
		weights = new ArrayList<Weight>();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		try {
			weights.add(new Weight(formatter.parse("2010/11/12"),(float) 70.6 ));
			weights.add(new Weight(formatter.parse("2010/11/13"),(float) 72.8 ));
			weights.add(new Weight(formatter.parse("2010/11/14"),(float) 74.4 ));
			weights.add(new Weight(formatter.parse("2010/11/15"),(float) 71.5 ));
		} catch (ParseException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	public ArrayList<Weight> getWeights() {
		return weights;
	}

	public ArrayList<Weight> getWeightsForDisplay() {
		final int NUM=10; // The no of items to display
		
		// If there's nothing to sort...
		if (weights == null)
			return null;
		
		ArrayList<Weight> displayWeights = this.getWeights();
		Collections.sort(displayWeights);
		while (displayWeights.size() > NUM)
			displayWeights.remove(0);
		Collections.reverse(displayWeights);
		return displayWeights;
	}
	
	public String getChartURL () {
		ChartURL chartURL = new ChartURL();
		
		// Transform the weight in a Map<Date, Float>
		HashMap<Date, Float> datePoints = new HashMap<Date, Float>();
		Iterator<Weight> it = weights.iterator();
		while (it.hasNext()) {
			Weight w = it.next();
			datePoints.put(w.getDate(),w.getWeightValue());
		}
		return chartURL.getURL(datePoints);
	}
	
}
