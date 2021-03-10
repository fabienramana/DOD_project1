package main;

public abstract class Forme {

	String type;
	public static final String RECTANGLE = "R";
	public static final String CERCLE = "C";
	public static final String ESPACE = "E";
	
	String color;
	
	public Forme(String type, String color) {
		this.type = type;
		this.color = color;
	}

}
