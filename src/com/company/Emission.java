package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Emission {
    public List<Form> create(List<Form> formes){
        List<Form> espaces = new ArrayList<>();
        Iterator<Form> itFormes = formes.stream().iterator();
        while (itFormes.hasNext()) {
            Rect r = (Rect) itFormes.next();
            espaces.add(new Espace(r.x, r.y, r.x + r.width, r.y + r.height, r.color));
        }
        return espaces;
    }

    public Double calculate_dimensions(Espace dimensions){
        double y = dimensions.top_y+dimensions.bottom_y;
        double x = dimensions.bottom_x-dimensions.top_x;
        return x*y;
    }
    
}