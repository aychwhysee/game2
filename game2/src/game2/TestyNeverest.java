package game2;

import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class TestyNeverest {

    public AttackWMob mob;
    public AttackWPlayer player;
    public int score;
    public int timer;
    public boolean gameOver;
    public Posn posn;

    public static Random rand = new Random();

    public static int randomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public static void main(String[] args) {
        System.out.println("hello world!");
        /*
         Things to test/check
         - Player movement speed works
         - Mob runs away from player
         - Mob HP drops by player ATT stat
         - 
        
         - Mode changes (to diff world) every x seconds/ticks
         - and vice versa
        
         - Player dies/game ends if they get hit in 2nd mode
        - mob chases player
        

        
         */
    }

}
