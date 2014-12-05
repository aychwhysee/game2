package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class DodgeWorld extends World {

    public static final int b_width = 800;
    public static final int b_height = 800;
    
    public int score; //hmm...maybe not needed for this mode. maybe instead a timer?
    
    public AttackWPlayer player; //need to initialize to be the same one from first mode..? how?
    public AttackWMob mob; //same here, though HP of mob probs doesnt matter.
    
    public boolean gameOver; // will be 'true' once player gets hit by mob.
}
