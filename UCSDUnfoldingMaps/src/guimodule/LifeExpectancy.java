package guimodule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.Marker;

public class LifeExpectancy extends PApplet
{
	UnfoldingMap map;
	List<Feature> countries = new ArrayList<Feature>();
	List<Marker> countryMarkers = new ArrayList<Marker>();
	// Map: Keys -> Values
	// Keys: String countryID
	// Values: Float lifeExp
	Map<String, Float> lifeExpByCountry;
	
	public void setup() 
	{
		size(800,600,OPENGL);
		map = new UnfoldingMap(this, 50, 50, 700, 500, new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		

		
		// Create Object of type Map, Read in data
		lifeExpByCountry = loadLifeExpectancyFromCSV("LifeExpectancyWorldBankModule3.csv");
		
		// 1 Feature + 1 Marker per Country
		// Using helper methods provided in UnfoldingMaps
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
		map.addMarkers(countryMarkers);
		
		// Country markers are shaded according to life expectancy (only once)
		shadeCountries();

	}
	
	public void draw() 
	{
		map.draw();
	}
	
	// Helper Method
	private Map<String, Float> loadLifeExpectancyFromCSV(String fileName)
	{
		Map<String, Float> lifeExpMap = new HashMap<String, Float>(); //Constructor
		
		// Read file
		
		String[] rows = loadStrings(fileName);
		
		// Reads country name and population density value from CSV row
		// NOTE: Splitting on just a comma is not a great idea here, because
		// the csv file might have commas in their entries, as this one does.  
		// We do a smarter thing in ParseFeed, but for simplicity, 
		// we just use a comma here, and ignore the fact that the first field is split.
		for(String row: rows) 
		{
			String[] columns = row.split(",");
			if(columns.length == 6 && !columns[5].equals("..")) 
			{
				float value = Float.parseFloat(columns[5]); //cast str to float
				lifeExpMap.put(columns[4],value);
			}
		}
		return lifeExpMap;
		
	}
	
	// Helper Method
	private void shadeCountries() 
	{
		for(Marker marker: countryMarkers) 
		{
			String countryID = marker.getId();
			
			if(lifeExpByCountry.containsKey(countryID)) 
			{
				float lifeExp = lifeExpByCountry.get(countryID);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255); // built in map() method in processing
				// 40, 90 is range  -->translate to color--> 0,255 is new range
				// cast float to int since rgb just deals with int
				marker.setColor(color(255-colorLevel, 100, colorLevel));
				// bright red (high R) for low life exp, bright blue (low R) for high life exp
			}
			else 
			{
				// If one statistics is not provided, set it to grey (as default color)
				marker.setColor(color(150, 150, 150));
			}
		}
	}
}
