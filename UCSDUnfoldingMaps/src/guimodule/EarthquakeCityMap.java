package guimodule;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PApplet;

public class EarthquakeCityMap extends PApplet
{
	private UnfoldingMap map;
	
	public void setup() 
	{
		size(950, 600, OPENGL); // Set canvas dimension
		map = new UnfoldingMap(this, 200,50,700,500,new Google.GoogleMapProvider());
	 //200,50 is x and y coordinate of map on the canvas,700,500 is map width & height
	 //In this case we use GoogleMapProvider
		map.zoomToLevel(2); // we can choose defual zoom level
		MapUtils.createDefaultEventDispatcher(this,map);
	}
	
	public void draw() 
	{
		background(220); // Set canvas background color
		map.draw();
		//addKey(); 
		// Draw a map legend using methods from processing library, need to be implemented
	}
}

