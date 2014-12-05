package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class AttackWorld extends World {
    
    public static final int b_width = 800;
    public static final int b_height = 800;
    
    public int score; //will literally just be a tick counter
    public int timer; //will determine when to switch to Dodge mode
    
    public AttackWPlayer player;
    public AttackWMob mob;
    
    public boolean gameOver; // will be 'true' once mob hp is <= 0
    
    
}
