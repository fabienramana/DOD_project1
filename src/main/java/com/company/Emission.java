package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Emission {
	public List<Form> create(List<Form> formes){
		Conversion c = new Conversion();
		List<Form> espaces = new ArrayList<>();
		Iterator<Form> itFormes = formes.stream().iterator();
		while (itFormes.hasNext()) {
			Rectangle r = (Rectangle) itFormes.next();
			espaces.add(new Espace(r.x, r.y, r.x + r.width, r.y - r.height, r.color));
		}
		return espaces;
	}
}
