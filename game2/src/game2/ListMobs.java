package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public interface ListMobs {
    
    public WorldImage drawListMobs();
    
    public ListMobs move();
    
    public ListMobs react(Player player);
    
    public ListMobs remove(Mob mob);
    
    

}
