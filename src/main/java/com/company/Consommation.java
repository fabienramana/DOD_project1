package com.company;

import java.util.List;

public class Consommation {
	public Espace getEspace(List<Form> espaces) {
		
		Double top_x = espaces.stream().map(espace -> (Espace) espace).mapToDouble(espace -> espace.top_x).min().getAsDouble();
		Double top_y = espaces.stream().map(espace -> (Espace) espace).mapToDouble(espace -> espace.top_y).min().getAsDouble();
		Double bottom_x = espaces.stream().map(espace -> (Espace) espace).mapToDouble(espace -> espace.bottom_x).max().getAsDouble();
		Double bottom_y = espaces.stream().map(espace -> (Espace) espace).mapToDouble(espace -> espace.bottom_y).max().getAsDouble();
		return new Espace(top_x,top_y,bottom_x,bottom_y,null);
	}
}
