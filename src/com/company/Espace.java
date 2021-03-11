package com.company;

public class Espace extends Form{
    double top_x;
    double top_y;
    double bottom_x;
    double bottom_y;

    public Espace(double top_x, double top_y, double bottom_x, double bottom_y, String color){
        super(ESPACE, color);
        this.top_x = top_x;
        this.top_y = top_y;
        this.bottom_x = bottom_x;
        this.bottom_y = bottom_y;
    }

    @Override
    public String toString(){
        return "E (top_x: "+top_x+", top_y: "+top_y+", bottom_x: "+bottom_x+", bottom_y: "+bottom_y+")";
    }
}
