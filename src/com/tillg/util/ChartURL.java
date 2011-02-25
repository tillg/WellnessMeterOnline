package com.tillg.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class ChartURL {

	private String emptyChart = "/img/nodata.jpg";
	private int width = 700; // Default width of the chart
	private int height = 300;
	private int numXlabels = 7;

	public String getURL(Map<Date, Float> datePoints) {

		if (datePoints.size() == 0)
			return emptyChart;

		String url = "http://chart.googleapis.com/chart?";
		url += "&cht=lxy"; // A line chart that lets you specify both x- and y-coordinates for each point, rather just the y values.
		url += "&chs=" + width + "x" + height;
		url += "&chco=008000"; // Our line will be green
		url += "&chls=3"; // Our line will be 3 pixels thick

		// Understand the date range
		ArrayList<Date> datesSorted = new ArrayList<Date>(datePoints.keySet());
		Collections.sort(datesSorted);
		Date start = datesSorted.get(0);
		Date end = datesSorted.get(datesSorted.size()-1);
		long startMilliSec = start.getTime();
		long endMilliSec = end.getTime();
		long rangeMilliSec = endMilliSec - startMilliSec;		
		if (rangeMilliSec == 0)
			return emptyChart;
		
		// Understand the values range
		Float lowVal = datePoints.get(start);
		Float highVal = lowVal;
		Iterator<Date> it = datePoints.keySet().iterator();
		while (it.hasNext()) {
			Float val = datePoints.get(it.next());
			if (val < lowVal)
				lowVal = val;
			if (val > highVal)
				highVal = val;
		}
		Float valRange = highVal - lowVal;
		
		// Prepare data string
		String xVals = "";
		String yVals = "";
		it = datesSorted.iterator();
		while (it.hasNext()) {
			Date date = (Date) it.next();
			Float val = datePoints.get(date);
			long dateMilliSec = date.getTime();
			long x = ((dateMilliSec - startMilliSec) * 100) / rangeMilliSec;
			float y = (val - lowVal) / valRange * 100;
			xVals += x;
			yVals += y;
			if (it.hasNext()) {
				xVals += ",";
				yVals += ",";
			}
		}		
		url += "&chd=t:" + xVals + "|" + yVals;	
		
		// Labels for Y axis
		url += "&chxt=x,y"; // Have the x & y axis visible
		url += "&chxr=1,"+lowVal+","+highVal;
		
		// Labels for X axis
		int numXsegments = numXlabels - 1;
		long segmentXlength = rangeMilliSec / numXsegments;
		url += "&chxl=0:|";
		for (int i=0; i < numXlabels; i++) {
			float xLabelValue = i * segmentXlength + startMilliSec; 
			String xLabel = DateHelper.DISPLAY_FORMATTER_SHORT.format(new Date((new Float(xLabelValue)).longValue()));
			url += xLabel + "|";
		}
		
		// Put the labels of the X axis in the right positions
		url += "&chxp=0,";
		for (int i=0; i < numXlabels; i++) {
			float labelXpos = (i * segmentXlength * 100) / rangeMilliSec;  
			url += labelXpos;
			if (i < numXlabels - 1)
				url += ",";
		}
		
		
		// Ticks going across the chart
		url += "&chxtc=1,-"+width; // Have "ticks" going thru the entire charts width - that's horizontal lines 

		
		// Parameters that still need to be handled
		//	   &chxr=0,0,25|1,60,70
		//	   &chd=t:1,2,3,6,8,11,13,19,21,100|67,67.5,67,67.8,68,68.5,67.4,67,65.5,65
		//	   &chg=25,25,5,5


		return url;

	}

}
