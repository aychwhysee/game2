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
    
    public int speed = 10; // mob speed (should all be the same)
    public int b_width; //board width
    public int b_height; //board height
    
    public final int width = 30; //mob width
    public final int height = 30; // mob height
    
    public int health; // mob HP
    
    public IColor color = new Red(); // But I need a dif col for each mob
    
    public Random random = new Random();
    
    public Mob(int b_width, int b_height, int health) {
        this.b_width = b_width;
        this.b_height = b_height;
        this.health = health;
        this.posn = new Posn(randomX(b_width), randomY(b_height));
    }
    
    public Mob(Posn posn, int b_width, int b_height, int health) {
        this.posn = posn;
        this.b_width = b_width;
        this.b_height = b_height;
        this.health = health;
    }
    
    public int randomX(int b_width) {
        return random.nextInt(b_width - (width * 2)) + width;
    }
    
    public int randomY(int b_height) {
        return random.nextInt(b_height - (height * 2)) + height;
    }
    
    public Mob move() {
        // randomly moving by itself
    }
    
    public Mob react(Player player) {
        // calls helper functions and moves in reaction to them
        //*** possible to combine react into move? using ifs?
        // Decrease mob HP by player ATT
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
    
    public boolean isDeadHuh() {
        // check Mob health. if < 0, return true; else false.
    }
    
    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }

}
