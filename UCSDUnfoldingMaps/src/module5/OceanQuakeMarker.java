package module5;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.utils.ScreenPosition;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {

	public OceanQuakeMarker(PointFeature quake) {
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = false;
	}
	

	/** Draw the earthquake as a square */
	// If this marker has been clicked on, lines are drawn between this marker
	// and all cities within its threat circle. 
	// If this marker has not been clicked, these lines should disappear.
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		pg.rect(x-radius, y-radius, 2*radius, 2*radius);

		if(this.getClicked()) 
		{
			for(Marker cMarker: getAffectedCities()) 
			{
				drawLines(pg,x,y,cMarker);
			}
		}
	}
	
	/* Draw a line between a cityMarker and an oceanQuakeMarker
	 * ScreenPositions class is referred to the whole screen
	 * not the map, and the map's specific position is (200, 50)
	 * We modify the offset
	 */
	private void drawLines(PGraphics pg, float x, float y, Marker cMarker) 
	{
		ScreenPosition cityScreenPosition = EarthquakeCityMap.getScreenPosition(cMarker);
		float cityX = cityScreenPosition.x;
		float cityY = cityScreenPosition.y;
		pg.stroke(pg.color(57,213,186));
		pg.strokeWeight(2);
		pg.line(x, y, cityX-200, cityY-50);
	}
	
	// Get list of cities within threat circle 
	private List<Marker> getAffectedCities()
	{
		List<Marker> cityMarkers = EarthquakeCityMap.getCityMarkers();
		List<Marker> affectedCityMarkers = new ArrayList<Marker>();
		for(Marker cMarker: cityMarkers)
		{
			if(cMarker.getDistanceTo(this.getLocation()) < this.threatCircle()) 
			{
				affectedCityMarkers.add(cMarker);
			}
		}
		return affectedCityMarkers;
	}
	
}
