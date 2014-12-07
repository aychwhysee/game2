package game2;

import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class TestyNeverest {

    public Mob mob;
    public Player player;
    public int score;
    public int timer;
    public boolean gameOver;
    public Posn posn;

    public static Random rand = new Random();

    public static int randomInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public Player randomPlayer() {
        return new Player(
                new Posn(randomInt(20, 980), randomInt(20, 980)),
                1000,
                1000,
                randomInt(10, 100),
                randomInt(1, 50));
    }

    public Mob randomMob() {
        // Just need to use first constructor since first constructor already
        // sets mob in random posn.
        return new Mob(
                1000,
                1000,
                randomInt(1, 15000));
    }

    public AttackWorld randomAW() {
        return new AttackWorld(
                randomPlayer(),
                randomMob(),
                randomInt(1, 5000),
                randomInt(1, 999),
                false);
    }

    public DodgeWorld randomDW() {
        return new DodgeWorld(
                randomPlayer(),
                randomMob(),
                randomInt(1, 5000),
                randomInt(1, 299),
                false);
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
