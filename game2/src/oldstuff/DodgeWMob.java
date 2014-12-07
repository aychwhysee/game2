package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class DodgeWMob {
    
    public Posn posn;
    
    public final int speed = 12;
    public int b_width;
    public int b_height;
    
    public final int width = 60;
    public final int height = 60;
    
    public int health; // shouldn't matter in this mode, but still need to keep track?
    
    public IColor color = new Yellow();
    
    public Random random = new Random();
    
    public DodgeWMob(Mob awm) { //similar to how DodgeWPlayer is constructed?)
        this.posn = awm.posn;
        this.b_width = awm.b_width;
        this.b_height = awm.b_height;
        this.health = awm.health;
    }
    
    
}
