package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Cercle form1 = new Cercle( 50,50,50,"green");
        Rect form2 = new Rect(0,0,2,2,"red");
        Cercle form3 = new Cercle(5,5,1,"green");

        List<Form> formes =  new ArrayList<>();

        formes.add(form1);
        formes.add(form2);
        formes.add(form3);

        //TP1
        tp1(formes);
        
        //TP2
        tp2(formes);
    }
    
    public static void tp1(List<Form> formes){
        System.out.println("TP1");
        List<Form> espaces = new ArrayList<>();
        Conversion conversion = new Conversion();
        
        for(Form f :formes){
            System.out.println(f.toString());
            if(f instanceof Rect){
                espaces.add(f);
            }
            else if (f instanceof Cercle){
                espaces.add(conversion.convert((Cercle)f));
            }
        }
        Emission e = new Emission();
        Consommation c = new Consommation();
        Espace finalSpace = c.getEspace(e.create(espaces));
        System.out.println(finalSpace.toString());
        System.out.println("l'espace occupé est de: "+ e.calculate_dimensions(finalSpace));
    }
    
    public static void tp2(List<Form> formes){
        System.out.println("TP2");
        for(Form f: formes){
            System.out.println(f.toString());
        }
        Mutation m = new Mutation();
        Emission e = new Emission();
        List<Form> s1 = e.create(m.mutate(formes.stream().filter(forme -> forme.type.equals(Form.CERCLE)).collect(Collectors.toList())));
        List<Form> s2 = e.create(formes.stream().filter(forme -> forme.type.equals(Form.RECTANGLE)).collect(Collectors.toList()));

        List<Form> espaces = new ArrayList<>();
        espaces.addAll(s1);
        espaces.addAll(s2);

        for(Form r: espaces){
            System.out.println(r.toString());
        }

        //System.out.println("espace occupé=" +calculate_dimensions(dimensions));
        Consommation c = new Consommation();
        Espace finalSpace = c.getEspace(espaces);
        System.out.println("finalSpace"+finalSpace.toString());
        System.out.println("l'espace occupé est de: "+ e.calculate_dimensions(finalSpace));
    }
    
    public static void tp3(List<Form> formes){
        
    }
    
}