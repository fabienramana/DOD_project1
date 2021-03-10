package com.company;

public class Conversion {
	
	Rectangle convert(Cercle c) {
		Double x = c.cx-c.radius;
		Double y = c.cy+c.radius;
		Double width = c.radius*2;
		Double height = c.radius*2;
		return new Rectangle(x,y, width, height, c.color);
	}
}