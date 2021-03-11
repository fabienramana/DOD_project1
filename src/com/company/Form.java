package com.company;

public abstract class Form {

    public static final String RECTANGLE="Rectangle";
    public static final String CERCLE="Cercle";
    public static final String ESPACE="Espace";

    String type;
    String color;

    protected Form(String type, String color) {
        this.type = type;
        this.color = color;
    }

}
