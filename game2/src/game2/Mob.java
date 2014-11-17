package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class Mob {
    /*
    list of mobs interface
    each element in list is a mob with diff attributes (HP, posn)
    movement behavior of each mob is same so just need this one class
    have hit checker in mobs class
    */
    public Posn posn; // each mob has a posn
    
    public int speed; // mob speed (should all be the same)
    public int b_width; //board width
    public int b_height; //board height
    
    public final int width = 30; //mob width
    public final int height = 30; // mob height
    
    public IColor color = new Red(); // But I need a dif col for each mob
    
    public Mob move() {
        // randomly moving by itself
    }
    
    public Mob react() {
        // calls helper functions and moves in reaction to them
        //*** possible to combine react into move? using ifs?
        // if hitOnLeft move right
        // if hitOnRight move left
        // if hitOnTop move down
        // if hitOnBot move up
    }
    
    public boolean hitOnLeft(Player player) {
        // check left x posn
    }
    
    public boolean hitOnRight(Player player) {
        // check right x posn
    }
    
    public boolean hitOnTop(Player player) {
        // check top y posn
    }
    
    public boolean hitOnBot(Player player) {
        // check bottom y posn
    }
    
    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }

}
