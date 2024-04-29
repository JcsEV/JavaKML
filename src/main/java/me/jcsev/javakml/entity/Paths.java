package me.jcsev.javakml.entity;

import java.util.ArrayList;
import java.util.List;

public class Paths {
	private List<Point> list;
	public Paths () {
		this.list = new ArrayList<>();
	}
	
	public Paths (List<Point> list) {
		this.list = list;
	}
	
	public Boolean isEmpty() {return this.list.isEmpty();}
	
	public List<Point> getList() {
		return this.list;
	}
	
	public void setList (List<Point> list) {
		this.list = list;
	}
	
	public Point getPoint(int position) {
		return this.list.get(position);
	}
	
	public void setPoint(int position, Point point) {
		this.list.set(position, point);
	}
	
	public Point getPoint() {
		return this.list.get(list.size() - 1);
	}
	
	public void setPoint(Point point) {
		this.list.add(point);
	}
}
