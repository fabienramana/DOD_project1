package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Cercle form1 = new Cercle( 3,2,1,"green");
        Rectangle form2 = new Rectangle(0,0,2,2,"red");
        Cercle form3 = new Cercle(5,5,1,"green");

        List<Form> formes =  new ArrayList<Form>();

        formes.add(form1);
        formes.add(form2);
        formes.add(form3);
        
        //TP1
        Map<String, Integer> dimensions = dimensions_espace_occupation(formes);
        /*System.out.println("top_x " + dimensions.get("top_x"));
        System.out.println("top_y " + dimensions.get("top_y"));
        System.out.println("bottom_x " + dimensions.get("bottom_x"));
        System.out.println("bottom_y " + dimensions.get("bottom_y"));*/
        System.out.println("espace occupé=" +calculate_dimensions(dimensions));
        
        
        //TP2
        //Filtre rectangle
        List<Rectangle> rectangles = new ArrayList<>();
        for(Form f: formes){
            if(f instanceof Rectangle){
                rectangles.add((Rectangle)f);
            }
        }
        for(Rectangle r: rectangles){
            System.out.println(r.toString());   
        }
        
        //Filtre cercle
        List<Cercle> cercles = new ArrayList<>();
        for(Form f: formes){
            if(f instanceof Cercle){
                cercles.add((Cercle)f);
            }
        }
        for(Cercle c: cercles){
            System.out.println(c.toString());
        }
        
        //mutation convert cercle to rectangle
        List<Rectangle> rec = new ArrayList<>();
        for(Cercle c : cercles){
            Map<String, Double> item_dimensions = transform_cercle_to_rectangle(c);
            rec.add(new Rectangle(item_dimensions.get("x"), item_dimensions.get("y"),item_dimensions.get("width"),item_dimensions.get("width"), "red"));
        }
        
        for(Rectangle r: rec){
            System.out.println(r.toString());
        }
        
    }
    
    public static Map<String, Integer> dimensions_espace_occupation(List<Form> formes){
        Map<String, Integer> dimensions = new HashMap<>();
        dimensions.put("top_x",0);
        dimensions.put("top_y",0);
        dimensions.put("bottom_x",0);
        dimensions.put("bottom_y",0);
        
        for(Form f: formes){
            if(f instanceof Rectangle){
                Map<String, Double> item_dimensions = new HashMap<>();
                item_dimensions = get_dimensions_of_rectangle((Rectangle)f);
                dimensions = update_dimensions(dimensions, item_dimensions);
            }
            else{
                Map<String, Double> item_dimensions = new HashMap<>();
                item_dimensions = get_dimensions_of_circle((Cercle)f);
                dimensions = update_dimensions(dimensions, item_dimensions);
            }
        }
        return dimensions;
    }
    
    public static Map<String, Double> get_dimensions_of_circle(Cercle c){
        Map<String, Double> item_dimensions = new HashMap<>();
        double x = c.cx-c.radius;
        double y = c.cy+c.radius;
        item_dimensions.put("top_x", x);
        item_dimensions.put("top_y", y);
        item_dimensions.put("bottom_x", x+c.radius*2);
        item_dimensions.put("bottom_y", y-c.radius*2);
        return item_dimensions;
    }

    public static Map<String, Double> get_dimensions_of_rectangle(Rectangle r){
        Map<String, Double> item_dimensions = new HashMap<>();
        item_dimensions.put("top_x", r.x);
        item_dimensions.put("top_y", r.y);
        item_dimensions.put("bottom_x", r.x + r.width);
        item_dimensions.put("bottom_y", r.y - r.height);
        return item_dimensions;
    }
    
    public static Map<String, Double> transform_cercle_to_rectangle(Cercle c){
        Map<String, Double> item_dimensions = new HashMap<>();
        item_dimensions.put("x", c.cx-c.radius);
        item_dimensions.put("y", c.cy+c.radius);
        item_dimensions.put("width", c.radius*2);
        return item_dimensions;
    }
    
    public static Map<String, Integer> update_dimensions(Map<String, Integer> dimensions, Map<String, Double> item_dimensions){
        if(dimensions.get("top_x") > item_dimensions.get("top_x")){
            dimensions.replace("top_x", (int)Math.floor(item_dimensions.get("top_x")));
        }
        if(dimensions.get("top_y") < item_dimensions.get("top_y")){
            dimensions.replace("top_y", (int)Math.ceil(item_dimensions.get("top_y")));
        }
        if(dimensions.get("bottom_x") < item_dimensions.get("bottom_x")){
            dimensions.replace("bottom_x", (int)Math.ceil(item_dimensions.get("bottom_x")));
        }
        if(dimensions.get("bottom_y") > item_dimensions.get("bottom_y")){
            dimensions.replace("bottom_y", (int)Math.floor(item_dimensions.get("bottom_y")));
        }
        
        return dimensions;
    }
    
    public static Integer calculate_dimensions(Map<String, Integer> dimensions){
        int x = dimensions.get("top_y")-dimensions.get("bottom_y");
        int y = dimensions.get("bottom_x")-dimensions.get("top_x");
        return x*y;
    }
    
}
