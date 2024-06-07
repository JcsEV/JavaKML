package me.jcsev.javakml;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class KmlFactory {
	private Element root;
	
	public Element createKML () {
		List<String> list = StringUtil.kmlHeader();
//		root = DocumentHelper.createElement("kml");
		root = DocumentHelper.createElement(new QName("kml", Namespace.get(list.get(0))));
		//根节点添加属性
		root.addNamespace("gx", list.get(1));
		root.addNamespace("xsi", list.get(2));
		root.addNamespace("atom", list.get(3));
//		root.addAttribute("xsi:schemaLocation", list.get(4));
		return root;
	}
	
	public Element createKML (String name, String description) {
		Element root = createKML();
		return createDocument(root, name, description);
	}
	
	public void writeKML (String name) throws IOException {
		OutputStream output = Files.newOutputStream(Paths.get(name));
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		XMLWriter xmlWriter = new XMLWriter(output, format);
		xmlWriter.write(DocumentHelper.createDocument(root));//创建kml到本地
		xmlWriter.close();
	}
	
	private static Element addElement (Element e, QName s) {
		return (Element) e.addElement(s);
	}
	
	private static Element addElement (Element e, String s) {
		return (Element) e.addElement(s);
	}
	
	private static void addElement (Element e, QName s, String text) {
		if (text == null) {return;}
		e.addElement(s).addText(text);
	}
	
	private static void addElement (Element e, String s, String text) {
		if (text == null) {return;}
		e.addElement(s).addText(text);
	}
	
	private static void addStyleUrl (Element e, String styleUrl) {
		if (styleUrl == null) {return;}
		addElement(e, "styleUrl", String.format("#%s", styleUrl));
	}
	
	private static void addImageUrl (Element e, String imageUrl) {
		if (imageUrl == null) {return;}
//		Element image = addElement(e, new QName("Image", Namespace.get(StringUtil.getNameSpace("gx"))));
//		QName qName = new QName("ImageUrl", Namespace.get(StringUtil.getNameSpace("gx")));
//		addElement(image, qName, String.format("data:image/png;base64,%s", imageUrl));
		Element image = addElement(e, "gx:Image");
		addElement(image, "gx:ImageUrl", String.format("data:image/png;base64,%s", imageUrl));
	}
	
	public static Element createDocument (Element e, String name, String description) {
		Element document = addElement(e, "Document");
		document.addAttribute("xsi:schemaLocation", StringUtil.kmlHeader().get(4));
		addElement(document, "name", name); //名称
		addElement(document, "description", description); //显示在Google Earth之中的对description的简短概要.
		return document;
	}
	
	public static Element createFolder (Element e, String name, String description, String style) {
		Element folder = addElement(e, "Folder");
		addElement(folder, "name", name);
		addElement(folder, "description", description);
		addStyleUrl(folder, style);
		return folder;
	}
	
	public static Element createPlacemark (Element e, String name, String description, String style) {
		Element placemark = addElement(e, "Placemark");
		addElement(placemark, "name", name);
		addElement(placemark, "visibility", "0");
		addElement(placemark, "description", description);
		addStyleUrl(placemark, style);
		return placemark;
	}
	
	public static Element createExtendedData (Element e) {
		return (Element) e.addElement("ExtendedData");
	}
	
	public static Element createData (Element e, String name, String displayName, String value) {
		Element data = addElement(e, "Data");
		addElement(data, "name", name);
		addElement(data, "displayName", displayName);
		addElement(data, "value", value);
		return data;
	}
	
	public static Element createImage (Element e, String imageUrl) {
		return createImage(e, List.of(imageUrl));
	}
	
	public static Element createImage (Element e, List<String> list) {
		if (list.isEmpty()) {return null;}
//		Element image = addElement(e, new QName("Carousel", Namespace.get(StringUtil.getNameSpace("gx"))));
		Element image = addElement(e, "gx:Carousel");
		for (String imageUrl : list) {
			addImageUrl(image, imageUrl);
		}
		return image;
	}
	
	public static Element createPoint (Element e, String extrude, String altitudeMode, String coordinates) {
		Element point = addElement(e, "Point");
		addElement(point, "extrude", extrude);
		addElement(point, "altitudeMode", altitudeMode);
		addElement(point, "coordinates", coordinates);
		return point;
	}
	
	public static Element createLineString (Element e, String tessellate, String altitudeMode, String coordinates) {
		Element lineString = addElement(e, "LineString");
		addElement(lineString, "tessellate", tessellate);
		addElement(lineString, "altitudeMode", altitudeMode);
		addElement(lineString, "coordinates", coordinates);
		return lineString;
	}
}
