package me.jcsev.javakml;

import me.jcsev.javakml.entity.Paths;
import me.jcsev.javakml.entity.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {
	private static final List<String> list = new ArrayList<String>() {{
		add("http://www.opengis.net/kml/2.2");
		add("http://www.google.com/kml/ext/2.2");
		add("http://www.w3.org/2001/XMLSchema-instance");
		add("http://www.w3.org/2005/Atom");
		add("http://www.opengis.net/kml/2.2 " + "http://schemas.opengis.net/kml/2.2.0/ogckml22.xsd " +
			"http://www.google.com/kml/ext/2.2 " + "http://code.google.com/apis/kml/schema/kml22gx.xsd");
	}};
	private static final HashMap<String, String> map = new HashMap<>(Map.of(
		"xmlns", list.get(0),
		"gx", list.get(1),
		"xsi", list.get(2),
		"atom", list.get(3),
		"schemaLocation", list.get(4)
	));
	
	public static List<String> kmlHeader () {
		return list;
	}
	
	public static String getNameSpace (String key) {
		return map.get(key);
	}
	
	public static String setCoordinates (double longitude, double latitude, int altitude) {
		return String.format("%s,%s,%s", longitude, latitude, altitude);
	}
	
	public static String setCoordinates (Point point) {
		return String.format("%s,%s,%s", point.getLongitude(), point.getLatitude(), point.getAltitude());
	}
	
	public static String setCoordinates (Paths paths) {
		StringBuilder result = new StringBuilder();
		for (Point point : paths.getList()) {
			result.append(String.format("%s,%s,%s ", point.getLongitude(), point.getLatitude(), point.getAltitude()));
		}
		return result.toString();
	}
}
