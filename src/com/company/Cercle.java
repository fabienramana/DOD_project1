package com.company;

public class Cercle extends Form{
    double cx;
    double cy;

    double radius;

    public Cercle(double cx, double cy, double radius, String color){
        super(CERCLE, color);
        this.cx = cx;
        this.cy = cy;
        this.radius = Math.abs(radius);
    }

    @Override
    public String toString(){
        return "C (cx: "+cx+", cy: "+cy+", r: "+radius+", color: "+color+")";
    }
}
