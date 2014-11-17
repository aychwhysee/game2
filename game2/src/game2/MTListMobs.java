package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class MTListMobs implements ListMobs {
    
    public MTListMobs() {
    }
    
    public WorldImage drawListMobs() {
        return new RectangleImage(new Posn(0, 0), 0, 0, new Black());
    }

}
