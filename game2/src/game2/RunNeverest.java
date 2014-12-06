package game2;

import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class RunNeverest {
    
    public static void main(String[] args) {
        DodgeWorld dodgy = new DodgeWorld();
        dodgy.bigBang(1200, 1200, 1.0/30.0);
//        AttackWorld atty = new AttackWorld();
//        atty.bigBang(800, 800, 1.0/30.0);
    }

}
