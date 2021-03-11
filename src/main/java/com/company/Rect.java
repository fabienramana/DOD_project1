package com.company;

public class Rect extends Form{
    
    double x;
    double y;
    
    double width;
    double height;
    
    public Rect(double x, double y, double width, double height, String color){
        super(RECTANGLE, color);
        this.x = x;
        this.y = y;
        this.width = Math.abs(width);
        this.height = Math.abs(height);
    }
    
    @Override
    public String toString(){
        return "R (x: "+x+", y: "+y+", w: "+width+", h: "+height+", color: "+color+")";
    }
}
