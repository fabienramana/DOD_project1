package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Mutation{
	
	public List<Form> mutate(List<Form> cercles){
		Conversion c = new Conversion();
		List<Form> newFormes = new ArrayList<>();
		
		Iterator<Form> formes = cercles.stream().iterator();
		
		while (formes.hasNext()) {
			Cercle cercle = (Cercle) formes.next();
			newFormes.add(c.convert(cercle));
		}
		return newFormes;
	}
}
