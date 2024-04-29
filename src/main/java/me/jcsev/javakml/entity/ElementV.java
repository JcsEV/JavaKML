package me.jcsev.javakml.entity;

import org.dom4j.Element;

public class ElementV {
	private Element element;
	public ElementV() {this.element = null;}
	public ElementV(Element e) {this.element = e;}
	
	public void setElement (Element element) {
		this.element = element;
	}
	
	public Element getElement() {
		return this.element;
	}
}
