package com.company;

import java.util.concurrent.Callable;

public class Tache implements Callable<Form> {
    Rect espace;
    
    public Tache(Form forme) {
        espace = (Rect) forme;
    }

    public Tache(Form forme, Conversion c) {
        espace = c.convert((Cercle)forme);
    }

    @Override
    public Rect call() throws Exception {
        return espace;
    }
}