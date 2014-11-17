package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class ConsListMobs implements ListMobs {
    
    public Mob first;
    public ListMobs rest;
    
    public ConsListMobs(Mob first, ListMobs rest) {
        this.first = first;
        this.rest = rest;
    }
    
    public WorldImage drawListMobs() {
        return new OverlayImages(first.drawImage(), rest.drawListMobs());
    }
    
    public ListMobs move() {
        return new ConsListMobs(first.move(), rest.move());
    }

}
