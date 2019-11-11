 OOP in Java from Coursera UCSD Notes

[TOC]

## Week-1

###Defining Classes and Creating Objects

- A `class` is a type of data

- An `object` is one such piece of data with associtated functionality

- ```java
  public class SimpleLocation{
  	public double latitude;
  	public double longitude;
  	
  	public SimpleLocation(double lat, double lon){
  		this.latitude = lat;
  		this.longitude = lon;
  	}
  	public doulbe distance(SimpleLocation other){
  		return getDist(this.latitude, this.longitude, other.latitude, 														other.longitude)
  	}
  
  }
  ```

  ```java
  public class LocationTester{
  	public static void main(String[] args){
  		SimpleLocation ucsd = new SimpleLocation(32.9, -117.2);
  		SimpleLocation lima = new SimpleLocation(-12.0, -77.0);
  		
  		System.out.println(ucsd.distance(lima));
  	}
  }
  ```



### Overloading Methods

**=> Same method name but must with different parameters **(parameter list)

- **Concept:** The two parts of a method signature are the method name and the parameter list (number, type, and order of parameters). As long as the parameter list differs, Java allows you to overload the method, and even change the return type because the return type is not part of the method signature (it is ignored).

- Overloading the constructor method
- Overloading the distance method

```java
public class SimpleLocation{
	// Member variables
  public double latitude;
	public double longitude;
	
	public SimpleLocation(){  // Default constructor
		this.latitude = 32.9;
		this.longitude = -117.2
	}
	public SimpleLocation(double lat, double lon){  // Parameter constructor
		this.latitude = lat;
		this.longitude = lon;
	}
  public double distance(SimpleLocation other){
 		//Body   
  }
  public double distance(double otherLat, double otherLon){
 		//Body   
  }
}
```



### Public and Private (Level of access)

- #### `public` means can access from any class
- `private` means can access **only** from the class (in this case SimpleLocation)

```java
public class SimpleLocation{
	private double latitude;
	private double longitude;
}
```

- use getter and setter for private variable

- ```java
  public double getLatitude(){
  	return this.latitude;
  }
  public double setLatitude(double lat){
  	if(lat < -180 || lat > 180){
      System.out.println("Illegal value for latitude");
    }
    this.latitude = lat;
  }
  ```

- Quiz

- ```java
  public class SimpleLocation
  {
    private double latitude;
    private double longitude;
    
    public SimpleLocation(double lat, double lon)
    {
      this.latitude = lat;
      this.longitude = lon;
    }
    
    // more code here (defines the method distance, etc.)
    // ...
    
    public static void main(String[] args)
    {
      SimpleLocation ucsd = new SimpleLocation(32.9, -117.2);
      SimpleLocation lima = new SimpleLocation(-12.0, -77.0);
      lima.latitude = -12.04;
      System.out.println(ucsd.distance(lima);
    }
  }
  ```

  No error, it's fine.

  If main were in another class, like LocationTester (time 2:28 in the video), this would cause an <u>compilation error</u></u> since the other class would be unable to access the instance variable. But because we're in the SimpleLocation class, it is actually able to access the instance variable. In fact, accessing private instance variables from in the main method in the same class is fairly commonly done when testing a class.

  

## Week-2

###Memory Models

####Memory Models with Primitive Data

![WechatIMG146](https://ws3.sinaimg.cn/large/006tNc79ly1g358nqaw14j31hc0u0k25.jpg)



```java
int var1 = 17;
int var2 = var1 + 1;
var1 = var2 + 1;
System.out.println("var1: " + var1 + " var2: " + var2); // 19, 18
```

Graph: 

var1: `17` -> `19`

var2: `17+1=18` 

####Memory Models with Objects

- **Primitive types vs Object types**
  - **Primitive types**: byte, short,int, long, float, double,char
  - **Object types**: and classes
- **Heap** is seperate part of memory
- Examples
  - 

![Screen Shot 2019-05-17 at 10.12.18 PM](https://ws4.sinaimg.cn/large/006tNc79ly1g358zo13snj30h506wmz7.jpg)



![Screen Shot 2019-05-17 at 10.13.12 PM](https://ws1.sinaimg.cn/large/006tNc79ly1g3590jm8ifj30hq06l0u3.jpg)

![Screen Shot 2019-05-17 at 10.13.40 PM](https://ws4.sinaimg.cn/large/006tNc79ly1g35910sxdoj30hb0613zz.jpg)



- 

![Screen Shot 2019-05-17 at 10.19.09 PM](https://ws3.sinaimg.cn/large/006tNc79ly1g3596qf2q6j309r0abgmz.jpg)



- ![Screen Shot 2019-05-17 at 10.24.13 PM](https://ws3.sinaimg.cn/large/006tNc79ly1g359cpfz3nj30h409yn15.jpg)

![Screen Shot 2019-05-17 at 10.25.24 PM](https://ws3.sinaimg.cn/large/006tNc79ly1g359d7zve8j307d04ujrv.jpg)

![Screen Shot 2019-05-17 at 10.25.52 PM](https://ws3.sinaimg.cn/large/006tNc79ly1g359dpsqh1j306204874r.jpg)

So the print result is -8.3, 37.6

- ![Screen Shot 2019-05-17 at 10.28.39 PM](https://ws2.sinaimg.cn/large/006tNc79ly1g359goq6p1j30dd08ywhj.jpg)

---

### Scope

- The scope of a variable is the area where it is defined to have a value.

**Variables**

- **Local variables** are declared inside a method.
- **Parameters** behave like local variables
- **Member variables** are declared outside any method, it is belong to the class

**`this` keyword** is optional. It works as a refernce to the current Objects whose Method or Constructor is being invoked. `this` keyword can be used to refer to any member of the current object from within an instance Method or a constructor.

---



```java
public class ArrayLocation{
	private double coords[];
	
	public ArrayLocation(double[] coords){
		this.coords = coords;
	}
}
public static void main (String[] args){
	double[] coords = {5.0, 0.0};
	ArrayLocation accra = new ArrayLocation(coords);
	coords[0] = 32.9;
	coords[1] = -117.2;
	System.out.println(accra.coords[0] +","+ accra.coords[1]);  // 32.9,-117.2
}
```

##Week-3

### Core: Using PApplet

Create GUIs using PApplet: **processing**

https://processing.org/reference/libraries/

```java
import processing .core.*; // Tell java where to find PApplet
public class MyPApplet extends PApplet 
{
	private String URL = "http://...jpg";
	private PImage backgroundImg;
	
  public void setup(){   // Configure canvas, execute once
		size(200,200);
		backgroundImg = loadImage(URL,"jpg");
	}
	public void draw(){    // Display content, Loops often
		backgroundImg.resize(50,100);
		backgroundImg.resize(0,height); 
		// lazy way, To make the image scale proportionally, use 0 as the value of the wide 		or high parameter.  height is dynamic (height of canvas)
		image(backgroundImg,0,0); // show background image
		fill(255,209,0);
		ellipse(width/4,height/5,width/5,height/5);
	}
  
}
```



`ellipse(a,b,c,d) `Draws an ellipse (oval) to the screen. An ellipse with equal width and height is a circle. By default, the first two parameters set the location, and the third and fourth parameters set the shape's width and height. The origin may be changed with the ellipseMode() function

---

###Colors

- RGB 0-255

- rgb(a,b,c)

- `fill (255,209,0);` // static

  https://processing.org/reference/fill_.html

---

###Coordinates:

![Screen Shot 2019-05-24 at 5.12.57 PM](https://ws3.sinaimg.cn/large/006tNc79ly1g3d3ofxy80j30kj0a60u1.jpg)

---

### Happy Face Code

```java
package guimodule;

import processing.core.PApplet;

public class MyDisplay extends PApplet
{
	public void setup() 
	{
		size(400, 400);
		background(200,200,200);
	}
	
	public void draw() 
	{
		fill(255,255,0);
		ellipse(200,200,390,390);
		fill(0,0,0);
		ellipse(120,130,50,70);
		ellipse(280,130,50,70);
		noFill();
		arc(200,280,75,75,0,PI);
	}
}

```

---

### Loading and Displaying Images

- Example was to display an image, and then put a sun on top of that image by putting an ellipse there. And change the color of that ellipse depending on the time of day.

```java
package guimodule;

import processing.core.PApplet;
import processing.core.PImage;

public class MyPApplet extends PApplet{
	PImage img;
	
	public void setup() 
	{
		//Add setup code for MyPApplet
		size(400,400);	// set canvas size
		background(255);// set canvas color
		stroke(0);		// set pen color, 	Sets the color used to draw lines and borders around shapes.
		img = loadImage("http://cseweb.ucsd.edu/~minnes/palmTrees.jpg","jpg");
		img.resize(0, height); // resize loaded image to full height of canvas
		image(img,0,0); // display image
		
	}
	public void draw() 
	{
		//Add drawing code for MyPApplet
		int[] color = sunColorSec(second());  //second() is built-in method using system clock
		//	Processing communicates with the clock on your computer. The second() function returns the current second as a value from 0 - 59
		fill(color[0],color[1],color[2]); // set sun color
		ellipse(width/4,height/5,width/4,height/5); //draw sun
	}
	
	public int[] sunColorSec(float seconds) 
	{
		int[] rgb = new int[3];
		// Scale the brightness of the yellow based on the seconds.
		// is black. 0 seconds is bright yellow.
		float diffFrom30 = Math.abs(30-seconds);
		
		float ratio = diffFrom30/30; // ratio is a number between 0 and 1
		// 255,255,0 is yellow, we do customization below
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		//System.out.println("R = " + rgb[0] + " G = " + rgb[1] + " B= " +rgb[2]);
		return rgb;
		
	}
}

```



### Setting up map visualization & Adding markers

- Placing earthquake data around the world

1. Set up map
2. Add content to the map

---

**Set up map**

1. ```java
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
   		background(10); // Set canvas background color
   		map.draw();
   		addKey(); 
   		// Draw a map legend using methods from processing library, need to be implemented
   	}
   }
   
   ```

   ---

**Add content to the map**

```java
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
		// Add makers
		Location valLoc = new Location(-38.14f,-73.03f);
		SimplePointMarker val = new SimplePointMarker(valLoc);
		// Creates a point marker for the given location.
		map.addMarker(val);
	}
	
	public void draw() 
	{
		background(10); // Set canvas background color
		map.draw();
		addKey(); 
		// Draw a map legend using methods from processing library, need to be implemented
	}
}


```

![Screen Shot 2019-05-25 at 1.18.38 AM](https://ws1.sinaimg.cn/large/006tNc79ly1g3dhpqq5hrj31l60nkjvg.jpg)



```markdown
**SimplePointMarker** -which you use to place a marker on the map. It's constructor takes a Location object so it knows where to plot the marker.

**Location** - which denotes a location (lat/long). You pass it to the SimplePointMarker constructor.

And don't forget you have to add it to the map using the addMarker method for it to be visible.
```

---

### Using Live Data



![Screen Shot 2019-05-25 at 1.24.10 AM](https://ws4.sinaimg.cn/large/006tNc79ly1g3dhvewlfij314o0l80vl.jpg)

```java
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
		
		// Add makers
		Location valLoc = new Location(-38.14f,-73.03f);
		SimplePointMarker val = new SimplePointMarker(valLoc);
		// Creates a point marker for the given location.
		map.addMarker(val);
		
		...
		// Using feature
		Location valLoc = new Location(-38.14f,-73.03f);
		Feature valEq = new PointFeature(valLoc);
		valEq.addProperty("title", "Valdivia, Chile");
		valEq.addProperty("magnitude", "9.5");
		valEq.addProperty("date", "May 22, 1960");
		valEq.addProperty("year", "1960");
		
		Marker valMk = new SimplePointMaker(valLoc, valEq.getProperties());
		map.addMarker(valMk);
		
		...
		
		List<PointFeature> bigEqs = new ArrayList<PointFeature>();
		bigEqs.add(valEq);
		bigEqs.add(alaskaEq);
		bigEqs.add(sumatraEq);
		bigEqs.add(japanEq);
		bigEqs.add(kamchatkaEq);
		// For each ... in ...
		List<Marker> markers = new ArrayList<Marker>();
		for (PointFeature eq: bigEarthquakes)
		{
			markers.add(new SimplePointMarker(eq.getLocation(),
																				eq.getProperties()));
		}
		map.addMarkers(markers);
		
		...
		
		int yellow = color(255,255,0);
		int gray = color(150,150,150);
		
		for(Marker mk: markers)
		{
			if((int) mk.getProperty("year") > 2000 )
			{
				mk.setColor(yellow);
			}
			else
			{
				mk.setColor(gray);
			}
		}
		
	}
	
	public void draw() 
	{
		background(10); // Set canvas background color
		map.draw();
		addKey(); 
		// Draw a map legend using methods from processing library, need to be implemented
	}
}


```



**Question**: Using Live Data!

```java
public class ParseFeed
{
	public static List<PointFeature> parseEarthquake(PApplet p, String fileName)
	{
		...
	}
}
```

---

### Bonus Project: To visualize average life expectancy around the world

1. Setup the map
2. Read data for each country
3. Display data for each country

- Abstract data type: `List` Ordered list of things of type `Feature`

```java
List<Feature> countries = new ArrayList<Feature>();
List<Marker> countryMarkers = new ArrayList<Marker>();
```



```java
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

```

---

### Support: ArrayLists and Generics

- Abstact data type: :List

- ```java
  List<Feature> countries = new ArrayList<Feature>();
  ```

  - Why types don't match?
    - List: Java "Interface" Specifies some behaviours, not implementation
    - ArrayList: Actual Java Class, Implements List behaviours

- What can ArrayList do?

  - Check java docs
  - **ArrayList like arrays**
    - Array version: countryArray[0] = f;
    - ArrayList verson: countries.set(0,f); // must have at least 1 element
    - 
    - Array version: int len  = countryArray.length;
    - ArrayList verson: countries.size();
  - ArrayLists are resizable(arrays are not)
    - add(E e);
    - add(int index, E element);

- Generics

- ```
  public class LifeExpectancy extends PApplet
  {
  	unfoldingMap map;
  	Map<String,Float> lifeExpMap;
  	
  	List<Feature> countries;
  	List<Marker> countryMarkers;
  	
  	List<Feature> countries = new ArrayList<Feature>();
  	List<Marker> countryMarkers = new ArrayList<Marker>();
  	
  	...
  	
  	Feature f = countries.get(0);
  	Marker m = countryMarkers.get(0);
  }
  ```

  - Lists and ArrayLists declare what type they store
  - type in <type> matches
  - E get(int index)
    - Here return type is **E** which is whatever type you declare the container to hold



## Week-4

### Why use Inheritance

- Good for complex, large projects
- keyword: extends

---

Fully written person class now needs to handle:

1. Students
2. Faculty

Different students behave differently. eg. parttime fulltime

**What do we want then?**

1. Keep common behaviour in one class
2. Split diffrent behaviour into seperate classes
3. Keep all of the objects in a single data structure

```java
public class Person
{
	private String name;
}
```

---

### Extends

"extends" means "inherit from"

**What is inherited?**

- public instance variables
- public methods
- **private** instance variables. Note: Private vars can be accesses <u>only</u> through public methods!

```java
public class Person    // Super class
{
	private String name;
  public String getName(){ return name;}
}
```

```java
public class Student extends Person  // Subclass
{
	
}
```

- UML diagram

![Screen Shot 2019-05-31 at 12.46.22 AM](https://ws4.sinaimg.cn/large/006tNc79ly1g3kei0msj6j308509bwf6.jpg)

---

![Screen Shot 2019-05-31 at 12.48.18 AM](https://ws3.sinaimg.cn/large/006tNc79ly1g3kek1lhw0j30nu09wwiu.jpg)

---

### Reference vs. Object Type "is-a"

- `Person p = new Person();`     A Person "is-a" Person

- `Student s = new Student();`  A Student "is-a" Student

- Should `Person p = new Student();` be allowed? 
  - Yes, because <u>A Student "is-a" Person</u>
- A **Person** array CAN store **Student** and **Faculty** objects.

```java
// in main
Person[] p = new Person[3];
p[0] = new Person();
p[1] = new Student();
p[2] = new Faculty();
```

- Can `Student s = new Person();` be allowed?
  - No, because not all the features of a Student are necessarily within a Person

---

### Concept Challenge: Reference and Objects

![Screen Shot 2019-05-31 at 12.59.02 AM](https://ws4.sinaimg.cn/large/006tNc79ly1g3kev5x067j30lp08542g.jpg)

- Which statement cause error and which works?

  - Works: 1,2,5

  - Errors:

    - 3    This line will not work because even though p now refers to a student object (after the line above), the compiler does not know this. P is a Person reference, the compiler does not know that p actually refers to a Student object. (Compiler doesn't know object type) It's gonna say p is a reference to a Person, I don't know about this getID() method, so I'm gonna cause error.

      -You could fix this error with a cast: `int m = ((Student)p).getID();`

    - 4    This will cause an error because a Person is not necessarily a Faculty

---

### Visibility Modifiers

Less Restrictive `public` ———————  More Restrictive `private`



- `public` can access from **any class**

- `protected` can access from **same class, same package, any subclass**

- `package(default)` can access from **same class, same package**

- `private` can access from **same class**

  

- **Rule of thumb**: Make member variables private (and methods either public or private)

- ![Screen Shot 2019-05-31 at 1.14.11 AM](https://ws3.sinaimg.cn/large/006tNc79ly1g3kfawv1w5j30jv0avq72.jpg)

![Screen Shot 2019-05-31 at 1.14.54 AM](https://ws4.sinaimg.cn/large/006tNc79ly1g3kfbngdqvj30je0am0w8.jpg)

---



![Screen Shot 2019-05-31 at 1.16.40 AM](https://ws1.sinaimg.cn/large/006tNc79ly1g3kfdmn80hj30ok0es0yr.jpg)



---

`Protected` is not recommended because it can be access from same package

![Screen Shot 2019-05-31 at 1.17.05 AM](https://ws4.sinaimg.cn/large/006tNc79ly1g3kfe33b7bj30os0fbag9.jpg)

---

`Package` is not recommended either.

![Screen Shot 2019-05-31 at 1.20.53 AM](https://ws1.sinaimg.cn/large/006tNc79ly1g3kfhum8r0j30nx09ogoo.jpg)



---

---

**Rule of thumb: always use either public or private**

![Screen Shot 2019-05-31 at 1.21.26 AM](https://ws4.sinaimg.cn/large/006tNc79ly1g3kfip4li6j30hg0d5420.jpg)

---

---



### Object Creation in Java

- `Student s = new Student();`
  - **new** allocates space 
  - ![Screen Shot 2019-05-31 at 1.24.58 AM](https://ws3.sinaimg.cn/large/006tNc79ly1g3kfm4kf3dj30i9067wgi.jpg)
- Objects are created from the **inside out**
  - ![Screen Shot 2019-05-31 at 1.25.47 AM](https://ws4.sinaimg.cn/large/006tNc79ly1g3kfmyjz8zj30dk0czmyv.jpg)
- 

```
The very first line of code of the person constructor is gonna immediately send you to it's indirect superclass or in this case, Object.

Now, the object constructor can initialize the variables associated with object,

essentially filling in that part of the object.

Once it's done, it returns back to Person. Person now can initialize it's variables.

And then it's gonna return back to Student. And Student now is gonna initialize the variables associated with Student. And through this process we've essentially initialized all these variables, went all the way up to object, and all the way back down. And that's what we mean by initializing inside out.
```



- When constructing objects, in what order are instance variables initialized?
  - They are initialized starting at Object and working their way down through the inheritance hierarchy to your subclass. (So instance variable in your Subclass are initialized last.)
    - This is correct. The subclass constructor calls constructors up the hierarchy until it reaches Object, then initializes variables starting with Object all the way back down to your subclass.

---

### Rules for Class Construction

`Your Code` -> `Java Compiler` -> `Bytecode`

- Threes Rules

  1. No superclass? Compiler inserts: extends Object

     ![Screen Shot 2019-05-31 at 1.31.16 AM](https://ws2.sinaimg.cn/large/006tNc79ly1g3kfsql66nj30ol09u42c.jpg)

  2. No constructor? Java gives you one for you

     - ![Screen Shot 2019-05-31 at 1.32.10 AM](https://ws1.sinaimg.cn/large/006tNc79ly1g3kftl3ulbj30ov09h787.jpg)	

  3.  First line must be `this(argsopt)` (same class contructor call) or `super(argsopt)`(super class constructor call), Otherwise, Java inserts `super();`

     - ![Screen Shot 2019-05-31 at 1.33.51 AM](https://ws4.sinaimg.cn/large/006tNc79ly1g3kfvex3rkj30dt09c76n.jpg)	

     - ![Screen Shot 2019-05-31 at 1.34.37 AM](https://ws3.sinaimg.cn/large/006tNc79ly1g3kfwh4c8cj30or08ajv1.jpg)

---



- So, The Compiler makes this happen!
  - ![Screen Shot 2019-05-31 at 1.36.27 AM](https://ws2.sinaimg.cn/large/006tNc79ly1g3kfy9zs02j30f40bpwgx.jpg)

- How do we initialize `name`? Next class

---

###Variable Initialization in a Class Hierarchy

![Screen Shot 2019-05-31 at 2.24.13 AM](https://ws4.sinaimg.cn/large/006tNc79ly1g3khbtwj1kj30f30c9q6y.jpg)

---

![Screen Shot 2019-05-31 at 2.25.23 AM](https://ws1.sinaimg.cn/large/006tNc79ly1g3khcz8gc3j30em0e8dji.jpg)

If you start to recognize that I'm not allowed to say this.name, you're right.  That's a private variable in the person class. I'm not allowed to direct the access of that in the student class,  I'd have to use a getter or a setter to do so. But I don't have a getter or a setter. Is there a way for me to do this? Can I initialize name without having the public getter setter? 

The answer is yes. 

All I have to do is change this now to call the superclass constructor that takes an argument, which is gonna initialize to be named.

```java
public class Student extends Person
{
	public Student(String n)
	{
		super(n);
	}
}
```

- Let's add a no-arg default constructor
  
- ![Screen Shot 2019-05-31 at 2.27.39 AM](https://ws2.sinaimg.cn/large/006tNc79ly1g3khfe63t0j30o20aojw7.jpg)
  
- But there is a better way !

  - Use our same class constructor

  - ```java
    public class Student extends Person
    {
    	public Student(String n)
    	{
    		super(n);
    	}
    	public Student()
    	{
    		this("Student");  // Use our same class constructor
    	}
    }
    ```

    

### Concept Challenge: Inheritance Constructors 1

```java
public class Person 
{
    private String name;
    public Person( String n ) {
        this.name = n;
        System.out.print("#1 ");
    }
}

public class Student extends Person {
    public Student () {
        this("Student"); 
        System.out.print("#2 ");
    }
    public Student( String n ) 
    { 
        super(n);
        System.out.print("#3 ");
    }
}
```

**Suppose in a main method you call** `Student s = new Student();`

**Q: What is the order of the statements printed?**   #1 #3 #2

---

### Concept Challenge: Inheritance Constructors 2

```java
public class Person {
    private String name;
 
    public Person( String n ) {
        super();
        this.name = n;
    }
    public void setName( String n ) {
        this.name = n;
    }
}

public class Student extends Person {
    public Student () {
        this.setName("Student"); 
    }
}
```

**Suppose in a main method you call** `Student s = new Student();`

**Q: What will be the value of the name variable for this object?**

**A: There is a compile error**

This is the correct answer, and make sure you know why. It is because the Person class has no default (no-argument) constructor. Since the Student constructor doesn't explicitly call super with an argument, Java will attempt to call the Person's non-existent no-argument constructor automatically. (Review 3 Rules of Compiler)

```
All right, now that you've worked through this yourself, let's work through it together. All we're doing here is we're calling the Student default constructor. So let's trace the code and see what happens. So we go over here to the Student default constructor, andyou might be tempted to just directly go and execute this line that says this.setName to the string Student, but the problem with that is, you'd be forgetting that the compiler actually inserts some code into this constructor. If the first line of the constructor is not either a call to the superclass constructor or a call to a constructor within the class, the compiler is going to insert a line to the superclass constructor that takes no arguments for us. 

So, in order to trace through this code, we need to insert that line so we know exactly what's happening. 

So let's do that now. So now that we see this call to the superclass constructor, we know that we have to go up to the Person class and find the constructor with no arguments. 

So we go up to the Person class and we look for a constructor with no arguments.

I don't really see one, do you? It's not there, and it turns out that Java will not insert one for us if we already had a constructor that takes an argument. So this is gonna cause a compile error.
```

---

### Method Overriding

- If we want our subclasses to behave differently all we have to do is override a method and now we get a different behaviour

####Overloading vs Overriding

- **<u>Overloading</u>**: **Same class** that has the **same method name** but **different parameters**.
- **<u>Overriding</u>**: **Subclass** has **same method name** with the **same parameter**s as the superclass.



![Screen Shot 2019-05-31 at 2.44.29 AM](https://ws3.sinaimg.cn/large/006tNc79ly1g3khww2ymhj30oe09pwiz.jpg)



```java
public class Person
{
	private String name;
  @Override
	public String toString()
	{
		return this.getName();
	}
}
```

```java
// Assume ctor
Person p = new Person("Tim");
System.out.println(p.toString()); //Calls Person's toString()
```

**Note toString() in the syscall is unnecessary, ** **because print line actually automatically calls toString if you ever pass an object as a parameter to print line.**

```java
// Assume ctor
Person p = new Person("Tim");
System.out.println(p); //println Automatically calls toString()
```

Output:  `Tim`



```java
public class Student extends Person
{
	private int studentID;
	
	public int getSID()
	{
		return studentID;
	}
	@Override
	public String toString()
	{
		return this.getSID() + ": " + super.toString(); // super refers to superclass
	}
}
```

```java
// Assume ctor
Student s = new Student("Cara", 1234);
System.out.println(s);
```

Output: `1234: Cara`



```java
// Assume ctor
Person s = new Student("Cara", 1234);
System.out.println(s);
```

Output: `1234: Cara` Why? Polymorphism!

---

### Polymorphism

- Superclass reference to subclass object

  - `Person s = new Student("Cara", 1234);`

- ![Screen Shot 2019-05-31 at 6.21.50 PM](https://ws2.sinaimg.cn/large/006tNc79ly1g3l909a0auj30ik08p42x.jpg)
  - ```java
    // Assume appropriate ctors
    Person p[] = new Person[3];
    p[0] = new Person("Tim");
    p[1] = new Student("Cara",1234);
    p[3] = new Faculty("Mia","ABCD");
    
    for(int i = 0; i < p.length; i++)
    {
    	System.out.println(p[i]);
    }
    ```

    - Do you think the method "toString" in the Person class will be called when p[1] is printed or do you think the "toString" method in the Student class will be called when p[1] is printed?

      - The "`toString`" method in Student will be called for p[1]

      - The **dynamic** (or actual) type of the object is Student, so <u>the appropriate method will be called.</u> This is what is commonly referred to as polymorphism.

      - OUTPUT: 

      - ```
        Tim
        1234: Cara
        ABCD: Mia
        ```

    ---

####Rules to Follow for Polymorphism

"Think like a compiler, act like a runtime environment" - Rick Ord

- **Compile Time Rules**

  - Compiler <u>ONLY</u> knows reference type not runtime type
  - Can only look in reference type class for method
  - Outputs a method signature

  ![Screen Shot 2019-05-31 at 6.31.15 PM](https://ws1.sinaimg.cn/large/006tNc79ly1g3l9a3mz52j30nr0dkqcj.jpg)

- Run TIme Rules

  - Follow exact runtime type of object to find method
  - Must match compile time method signature to appropriate method in actual actual object's class

  ![Screen Shot 2019-05-31 at 6.32.08 PM](https://ws1.sinaimg.cn/large/006tNc79ly1g3l9auwy41j30nj0dqwoc.jpg)

---

So ![Screen Shot 2019-05-31 at 6.33.28 PM](https://ws1.sinaimg.cn/large/006tNc79ly1g3l9cf5upuj30k10eujt4.jpg)

Problem, Person reference has no getSID() method -> Compile Time Error!

What should we do? CASTING!

---

####Casting Objects

**Casting**

- Automatic type promotion (like `int` to `double` )
  - Superclass superRef = new Subclass();     (==Widening==)
- Explicit casting (like `double` to `int` )
  - Subclass ref = (Subclass) superRef;    	 	(==Narrowing==)
  - Be careful: Compiler trusts you
- ![Screen Shot 2019-05-31 at 6.39.11 PM](https://ws2.sinaimg.cn/large/006tNc79ly1g3l9i858fwj30no08sn0u.jpg)

---



- ![Screen Shot 2019-05-31 at 6.39.57 PM](https://ws3.sinaimg.cn/large/006tNc79ly1g3l9j0e6hnj30ns08xted.jpg)

Problem: There is no getSID method in Person. What it tried to do is it tried to cast Person to a Student and it couldn't because it is a Person Object. In a sense you've broken the compiler's trust at this point. 

Again you told the compiler, I know this is gonna be a student and it didn't turn out to be.

![Screen Shot 2019-05-31 at 6.50.07 PM](https://ws4.sinaimg.cn/large/006tNc79ly1g3l9tpw9vzj30jy0awtac.jpg)

Here class Student extends Person, but we cannot cast a reference to Person object to Student because the real object is not Student but Person.

---

- **Runtime Type Check**
  - `instanceof`
  - Provides runtime check of **is-a** relationship

- ```java
  if(s instanceof Student)
  {
  	// Only executes if s is-a student at runtime
  	((Student) s).getSID();
  }
  ```

----

#### Concept Challenge: Polymorphism Part 1

Consider the following class definitions:

```java
public class Person
{
  private String name; 

  public Person(String name)  { this.name = name; }
  public boolean isAsleep(int hr)  { return 22 < hr || 7 > hr; }
  public String toString()      { return name; }

  public void status( int hr )
  {
    if ( this.isAsleep( hr ) )
      System.out.println( "Now offline: " + this );
    else
      System.out.println( "Now online: " + this );
  }
}
```

```java
public class Student extends Person
{
  public Student(String name)  {
    super(name);
  }

  public boolean isAsleep( int hr ) // override 
  { return 2 < hr && 8 > hr; }
}
```

Q: What will the following code, which appears in some main method, print:

```java
Person p;   // p is a person reference
p = new Student("Sally");  
// student is-a person, the actual object p refers to is a student
p.status(1);  // We are calling .status(1) on a student object
```

Print: Now online: Sally

A: Although someone who is just a Person is asleep at 1 in the morning, a student is awake at that hour. Because the object is actually a Student, and not just a person, Java calls the student's isAsleep method from the Person's status method at **run time.**

```java
  public void status( int hr )
  {
    if ( this.isAsleep( hr ) )  
      // "this" is at runtime determined to be a student object, cause student object is the one that called this method originially
      System.out.println( "Now offline: " + this );
    else
      System.out.println( "Now online: " + this );
  }
```

---

#### Concept Challenge: Polymorphism Part 2

![Screen Shot 2019-05-31 at 7.02.54 PM](https://ws3.sinaimg.cn/large/006tNc79ly1g3la6wqw0fj308h0czq4n.jpg)

```java
public class Person {
    public void method1() {
        System.out.print("Person 1 ");
    }
    public void method2() {
        System.out.print("Person 2 ");
    }
}
```

```java
public class Student extends Person {
    public void method1() {
        System.out.print("Student 1 ");
        super.method1();
        method2();
    }
    public void method2() {
        System.out.print("Student 2 ");
    }
}
```

```java
public class Undergrad extends Student {
     public void method2() {
         System.out.print("Undergrad 2 ");
     }
}
```

Q: What will the following code, which is placed in a main method somewhere with access to all of the above classes, print?

```java
Person u = new Undergrad();
u.method1();
```

Print: Student 1 Person 1 Undergrad 2

**Tracing steps:**

1. From u.method1(); 
2. in undergrad class we don't see method1(), but undergrad extends student, so java is gonna look in Student class for method1(), 
3. it finds method1() and executes `print("Student 1");`
4.  `super.method1();`  so we go up from student class to person class and find method1(), and executes `print("Person 1")`
5. Now, return back to method1() in Student class and execute `method2();`
6. So which `method2()`?  Since we dont have a calling object at this point, java will insert a `this` -> `this.method2();`
7. So what is the actual object type of the object `this` at runtime? It was an Undergrad Object
8. We go to Undergrad class and call `method2()`, so it `print("Undergrad 2 ");`

 the purpose of this example was to show you the difference between 



#####What happens with a `call to super` and then `a method` and a `call to this` and `a method`.

- Those calls to **super** and then a method get bound to **compile time**.
  - So in compile time, Java is going to look at the code and figure out what class it's in and what the super class of that class is. 

- But when we call **this** and then a method name, that's gonna happen at **runtime**.
  - So Java is gonna use the <u>actual type of the object at runtime</u>.

---

### Abstract Classes and Interfaces

![Screen Shot 2019-06-01 at 1.47.58 AM](https://ws4.sinaimg.cn/large/006tNc79ly1g3llwccoqrj30hm0crn3k.jpg)

- **Abstract**

  - Can make any class **abstract** with keyword
    - `public abstract class Person{}`
  - Class must be **abstract** if any methods are 
    - `public abstract void monthlyStatement(){}`
      - // concrete subclasses must override this abstract method

- **Implementation vs. Inferface**

  - <u>Abstract classes offer both inheritance of interface and inheritance of implementation</u> 
    - You gain both the methods from the class (interface) and any implementation components (like instance variables, non-abstract methods, etc.)
  - **Implementation: **instance variables and methods define common behaviour
  - **Interface: **methods signatures which define required behaviours

- What if we only want **interface**?

  - Interfaces only define required methods

  - Classes can inherit from multiple interfaces

  - Example: Comprarable Interface

  - ```java
    //Defined in java.lang.Comparable
    package java.lang;
    
    public interface Comparable<E>
    {
    	// Compare the object's name to o's name
    	// Return < 0, 0, > 0 if this object compares 
    	// less than, equal to, greater than o
    	public abstract int compareTo(E o);
     }
    ```

    

  ```java
  public class Person implements Comparable<Person>
  {
  	private String name;
  	...
  	@Override
  	public int compareTo(Person o)
  	{
  		return this.getName().compareTo(o.getName());
    }
  }
  ```

- Abstact class or Interface?

  - If you just want to define a required method
    - Interface
  - If you want to define potentially required methods AND common behaviours
    - Abstract class

---



## Week-5

### Event-Driven Programming

**Procedural**

- Code execution follows predictable sequence, based on control logic and program state.

- ```java
  int[] vals = new int[7];
  for(int i=0; i < vals.length; i++)
  {
  	vals[i] = i;
  }
  ```

  

**Event driven**

```java
public void keyPressed()
{
  ...
}
public void mousePressed()
{
  ...
}
```

---

###Implementing Events in UnfoldingMaps

```java
import processing.core*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.providers.Google;

public class MyPApplet extends PApplet
{
	private UnfoldingMap map;
	
	public void setup()    // execute once
	{
		size(800,600,OPENGL);
		map = new UnfoldingMap(this,50,50,700,500,
					new Google.GoogleMapProvider());
	}
	
	public void draw()     // continuous loop
	{
		map.draw();
	}
	
	public void keyPressed()
	{
		if(key == `w`)
		{
			background(255,255,255);  // code for white
		}
	}
	
}
```

- What happens when we run keyPressed( ) ? 
  - the background of the canvas will be white

```java
...
import de.fhpotsdam.unfolding.utils.MapUtils;

public class DefaultEventExample extends PApplet
{
	private UnfoldingMap map;
	
	public void setup()   
	{
		map = new UnfoldingMap(this,50,50,700,500,
					new Google.GoogleMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map); // <-Include this
	}
	...
}
```

- **We don’t need to handle this event, by including `	MapUtils.createDefaultEventDispatcher(this, map);` in our setup method, our application will be <u>interactive(to listen for events)</u>**
- Customize Interaction
  - For `keypressed( ) ` we added above , We’re overriding the key press method from the super class, PApplet

---

###Implementing Buttons in UnfoldingMaps

```java
import processing.core*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;

public class MyPApplet extends PApplet
{
	private UnfoldingMap map;
	
	public void setup()    // execute once
	{
		size(800,600,OPENGL);
		map = new UnfoldingMap(this,50,50,700,500,
					new Google.GoogleMapProvider());
    MapUtils.createDefaultEventDispatcher(this, map); 
	}
	
	public void draw()     // continuous loop
	{
		map.draw();
	}
	
	public void keyPressed()
	{
		if(key == `w`)
		{
			background(255,255,255);  // code for white
		}
	}
	
}
```

- We’ll Add buttons to control the colour of the background

![Screen Shot 2019-06-10 at 3.12.53 PM](http://ww1.sinaimg.cn/large/006tNc79ly1g3wnqpbhk6j30zu0b8ten.jpg)

- `rect(x,y,width,length)`

- Where should we add this code? setup or draw?

  - IN `draw( )`. where we see them on top of the map where they’re supposed to be.

- **Add button functionality**

  - ```java
    ...
      public void draw()
      {
        map.draw();
        drawButton();
      }
    
      private void drawButtons()
      {
        fill(255,255,255);
        rect(100,100,25,25);
    
        fill(100,100,100);
        rect(100,150,25,25);
      }
    }
    ```

    ---

    

![Screen Shot 2019-06-10 at 3.16.36 PM](http://ww4.sinaimg.cn/large/006tNc79ly1g3wnukd7d9j30rq0jedlt.jpg)

- To custom them

  - `mouseReleased()` 

    - check if position of mouse is inside one of the boxes .. then act
    - find coordinates for white button
      - (100,100) ,  (125,100),  (100,125),  (125,125)

  - ```java
    public void mouseReleased()
    {
        if(mouseX > 100 && mouseX < 125 &&  // check if in white bt
            mouseY > 100 && mouseY < 125)
        {
          background(255,255,255);
        }
        else if (mouseX > 100 && mouseX < 125 &&   // if in gray bt
            mouseY > 150 && mouseY < 175)
        {
          background(100,100,100);
        }
    }
    ```

    - `mouseX,mouseY` are inherited from PApplet

---

### Listener Hierachy

- ![listenerHierarchy](http://ww1.sinaimg.cn/large/006tNc79ly1g3wo9svofyj30ui0nsta3.jpg)

- The PApplet listening for user input, like most clicks and keyboard input, 

  - what we're doing actually is saying the <u>PApplet implements two interfaces.</u> 
  - <u>**PApplet** is a class</u>. And it implements the interface which means that it implements the methods that are required by the interface MouseListener. 
  - And it also, implements the methods that are required by the interface KeyListener. It's fulfilling the promises made by those interfaces. 

- **MouseListener** as an interface. 

  - The MouseListener makes a promise that methods like mousePressed, mouseClicked, and mouseReleased will be implemented.

  

  ---

  

- There are also qualitatively different events and qualitatively different listeners. 

  So for example, it turns out that, 

  - <u>the Map object itself can act as a listener</u>. 
  - Just slightly different kind of listener, there's this other interface called EventListener and the UnfoldingMap implements it. 
  - And what the UnfoldingMap listens for is for events to do with the map itself, like zoom and pan. 
  - ![Screen Shot 2019-06-10 at 3.30.47 PM](http://ww2.sinaimg.cn/large/006tNc79ly1g3wo9bm8gnj30yu0d6tf5.jpg)

  

- The point of this is that the notion of event driven program can be used very generally, and can help us design programs that makes sense in the particular context that we're working with. 

- Sometimes it's for UI, user interface, but other times, it's for interacting with more complicated objects.



---

## Week-6

- Searching
- Basic Sorting

###Searching

###Linear Search

- **Airport code is a 3-letter code.**
- ![Screen Shot 2019-06-15 at 1.42.55 PM](http://ww1.sinaimg.cn/large/006tNc79ly1g42d8ohz2cj30p10bgjxe.jpg)

- ```java
  public class Airport // Need to represent airport data
  {
  	private String city;
  	private String country;
  	private String code3;
  	...
  	
  	public String getCity(){ return this.city;}
  	public String getCountry(){	return this.country;}
  	public String getCode(){return this.code3;}
  }
  ```

  ![Screen Shot 2019-06-15 at 1.44.52 PM](http://ww2.sinaimg.cn/large/006tNc79ly1g42dall2sfj30ou0ar0xk.jpg)

- Array of Airport object
- For simplification
- ![Screen Shot 2019-06-15 at 1.45.49 PM](http://ww3.sinaimg.cn/large/006tNc79ly1g42dbpvq6ij30op06xad5.jpg)

- Ex: Find Beijing: Linear Search
- `toFind[Beijing]`
  
  - start at beginning, keep going.   `index[0]`

---

**LinearSearch Basic Algorithm** (Pseudocode)

```c
Start at the first index in the array
while index is less than the length of the array; // Loop
		if the city to find equals the city at the current index,
			return the airport code
		increment index by 1
return a value to indicate the airport was not found
```



```java
// toFind is a city name
public static String findAirportCode(String toFind, Airport[] airports)
{
  for(int i = 0; i < airports.length();i++)
  {
    if(airports[i].getCountry().equals(toFind)) 
    {
      return airports[i].getCode; 
  	}
  }
  return null; // indicate not found
}
```

If we’re very unlucky, we will have to look through n elements.

 **Differences between equals and == operator**

- `.equals` is saying to Java is, I want to compare the string representations in these two objects by essentially their characters. I wanna know, are they the same string?. I don't care if their the exact same object in memory, I just wanna know, is it the same string of characters? and the .equals method will do that. 
- The `== operator`, compares the references that are stored in two particular variables. And so that will only be true if those are actually the same literal object in Java's memory.
- We can use == operators for reference comparison (**address comparison**) and .equals() method for **content comparison**. In simple words, == checks if both objects point to the same memory location whereas .equals() evaluates to the comparison of values in the objects.
- If a class does not [override the equals method](https://www.geeksforgeeks.org/overriding-equals-method-in-java/), then by default it uses equals(Object o) method of the closest parent class that has overridden this method. See [this ](https://www.geeksforgeeks.org/override-equalsobject-hashcode-method/)for detail

---

#### Binary Search

Much better than linear search!

- Linear search: search through each position in the array, in order, element by element.

- Binary search: cut the list in half, only search half the list
  - List must be sorted on what you are searching(city) !
  - What part of the array still needs to be searched? `toFind[Beijing]`
    - Range: low 0 hight 7
    - Midpoint = (low+high)/2 = integer division will truncate 0.5 so = 3
    - Then, compare to the middle element. Match?
      - which half of the array is no longer active?
        - whether beijing is smaller or greater than middle point city(Essen)?
        - Since Beijing comes before Essen, Beijing is smaller. Anything greater than Essen is no long active.
        - Low 0, change High = mid -1 = 2, mid = (0+2)/2 = 1
        - Compare again, match!
        - ![Screen Shot 2019-06-15 at 3.51.30 PM](http://ww2.sinaimg.cn/large/006tNc79ly1g42gycj2rnj30p109bn1j.jpg)

---

**Binary Search: Basic Algorithm (Pseudocode)**

```c
Initialize low = 0, high = size of list - 1
while low <= high:
	mid = (high+low)/2;
	if the city to find equals the city at mid
		return the airport code
	if the city is alphabetically less than the city at mid
		high = mid - 1;
	else low = mid + 1;
return a value to indicate not found
```

- When to stop the while loop?
  - low <= high

```java
//toFind is a city name 
public static String findAirportCodeBS(String toFind, Airport[] airports)
{
	int low = -;
	int high = airports.length;
	int mid;
	while(low <= high)
	{
		//mid = (low+high)/2; May cause overflow if low and high are two large
    mid = low + (high-low)/2;
		int compare = toFind.compareTo(airports[mid].getCity());
		if(compare < 0) // toFindelement is less
		{
			high = mid - 1;
		}
		else if (compare > 0)
		{
			low = mid+1;
		}
		else return airports[mid].getCity();
	}
	return null;
}
```

Note:

```java
mid = (low+high)/2; //may cause overflow if low and high are too large.
//we should using range instead
mid = low + ((high-low)/2);
```

![Screen Shot 2019-06-15 at 4.11.54 PM](http://ww2.sinaimg.cn/large/006tNc79ly1g42hjpjoazj30og07emzs.jpg)

Performan is much better:

Half-Half-Half….  => how many times n can be divided by 2. 

$log_2(n)$ = y    i.e. $2^y = n$      Like n=16, then log2(n) = 4.

---

###Basic Sorting

Sorting City, Country… according to alphabet order can both help in linear and binary search.

Finding duplicates? we can sort it firstly.

----

####Selection Sort Algorithm

- Example of Array of integers:  `7 16 66 43 97 51`
- Want to Organize from smallest to largest

**Algorithm: Selection sort**

- Find smallest element, swap it with element in location 0
- Find next smallest element sawp it with element in location 1
- ...

![Screen Shot 2019-06-15 at 4.49.16 PM](http://ww4.sinaimg.cn/large/006tNc79ly1g42imii94yj30kx08ejue.jpg)

<u>second last postion: length-2 :</u>A small optimization: once we’ve sorted the first all last elements of the array, then the last element is going to be in the correct position.

<u>Invariant property:</u>  Once we worked on postion 0,1,2,3,.. the elements in those positions are sorted and not gonna change at all.

**Find smallest element in postion i to length-1, Swap it with element in position i**

**Implementation**

```java
public static void selectionSort(int[] vals)
{
  	int indexMin;
		for(int i = 0; i < vals.length-1; i++)
    {
      indexMin = i;
      for(int j = i + 1; j < vals.length; j++)
      {
          if(vals[j] < vals[indexMin])
          { 
            indexMin = j;
          }
      }
      swap(vals,indexMin,i);
    }
}
```

**Selection Sort Analysis**

https://www.khanacademy.org/computing/computer-science/algorithms/sorting-algorithms/a/analysis-of-selection-sort

Performance: 

- Slow. 
- The running time of selection sort is $\Theta(n^2)$
- Selection sort gets no benefit from sorting an already sorted array, because its inner loop always runs to the end of the array to be sure its found the smallest remaining element.

---

#### Insertion Sort

```java
public static void mysterySort(int[] vals)
{
	int currInd;
	for(int pos = 1; pos < vals.length; pos++)
	{
		currInd = pos;
		
		while(currInd > 0 && vals[currInd] < vals[currInd-1])
		{
			swap(vals, currInd, currInd-1);
			currInd = currInd - 1;
		}
	}
}
```

Example: `7 16 66 43 97 51`

1. pos: 1 currInd: 1
   1. 16 < 7 ? No
2. pos: 2 currInd: 2
   1. 66 < 16? No
3. Pos: 3 currInd: 3
   1. 43 < 66? Yes
      1. swap 43 and 6 -> `7 16 43 66 97 51`, currInd = 2
         1. check 43 < 16? No
   2. Pos: 4 currInd: 4
      - ….

![Screen Shot 2019-06-17 at 7.57.44 PM](http://ww2.sinaimg.cn/large/006tNc79ly1g44zbauj1oj30gg0a0dir.jpg)



Growing an array that is relatively sorted even though there might need to be some elements that get put in into that array later on, as we process that array.  

![Screen Shot 2019-06-17 at 7.59.22 PM](http://ww2.sinaimg.cn/large/006tNc79ly1g44zcykc4jj30ge079jt6.jpg)

![Screen Shot 2019-06-17 at 8.01.09 PM](http://ww1.sinaimg.cn/large/006tNc79ly1g44zepbw36j30jd054wgw.jpg)

This is **<u>Insertion Sort.</u>** 

To sum up the running times for insertion sort:

- Worst case: $\Theta(n^2)$
- Best case: $\Theta(n)$
- Average case for a random array: $\Theta(n^2).$
- "Almost sorted" case: $\Theta(n).$

---

---

#### Java Built-in Sort

- Use pre-defined Java method to sort
- Explain the properties of the built-in sort

<u>Review of basic sorting algorithms</u>

- **Selection Sort**
  - For each **position i** from **0** to **length - 2**
  - Find Smallest element in “still unsorted”, Swap it to **position i**
- **Insertion Sort**
  - For each **position i** from **1** to **length - 1**
  - Find correct location of **i-th** element relative to first **i-1**, Swap successive pairs to get there 

---

```java
import java.util.*;

public class MyBuiltInSortingTest
{
	public static void main(String[] args)
	{
		Random random = new Random();
		List<Integer> numsToSort = new ArrayList<Integer>();
		
		for(int i = 0; i < 5; i ++)
		{
			numsToSort.add(random.nextInt(100));
		}
		
		Collections.sort(numsToSort); // Built-in Sort
		System.out.println("New array after built-in sort: " 
				+ numsToSort.toString());	
		}
}
```

- <u>It is an Optimized merge sort</u>
  - Fast
    - In worst case
    - On nearly sorted data
  - Stable
- https://docs.oracle.com/javase/tutorial/collections/algorithms/



---

### Comparable Interface

What is the data we want to compare is not integers? like airports.

1. implements Comparable<...>
2. override compareTo( ) method

![Screen Shot 2019-06-17 at 8.23.36 PM](http://ww1.sinaimg.cn/large/006tNc79ly1g45024madvj30m606vgo3.jpg)

```java
// we implement Comparable interface for the class Airport
public class Airport implements Comparable<Airport>
{
	private String city;
	private String country;
	private String code3;
	...
	
	public String getCity(){ return this.city;}
	public String getCountry(){	return this.country;}
	public String getCode(){return this.code3;}
  
  
  public static void main(String[] args)
  {
    ArrayList<Airport> airports = new ArrayList();
    ...
    airports.add(...);
    ...
    Collections.sort(airports);
  }
  //Must override compareTo() method
  // smaller.compareTo(bigger)   				Negative
  // sameSize.compareTo(otherSameSize)  0
  // bigger.compareTo(smaller)   				Positive
  public int compareTo(Airport other)
  {
		return (this.getCity()).compareTo(other.getCity());	
		// Here we use city names
		// We want to be lazy so just use pre-defined compareTo for String inputs
		// Strings are comparable.
	
		//return (this.getCountry()).compareTo(other.getCountry());	
  }
}
```



![Screen Shot 2019-06-17 at 8.26.31 PM](http://ww4.sinaimg.cn/large/006tNc79ly1g4505661w7j30k406gdhx.jpg)

---

### Concept Challenge: Which Sort?

![Screen Shot 2019-06-17 at 8.33.31 PM](http://ww4.sinaimg.cn/large/006tNc79ly1g450cdtac3j30ga0cpdj7.jpg)

- It must **not be selection sort**

  - You should find 1 as the first element at the first iteration.

  ![Screen Shot 2019-06-17 at 8.39.13 PM](http://ww4.sinaimg.cn/large/006tNc79ly1g450ies7ejj30fs0aemyz.jpg)

- **It’s insertion sort.** The first 4 elements are in order, which is what is guaranteed after the first 3 iterations of the outer loop in insertion sort.

  ![Screen Shot 2019-06-17 at 8.37.54 PM](http://ww3.sinaimg.cn/large/006tNc79ly1g450gy8ofwj30fw0alaby.jpg)

---

