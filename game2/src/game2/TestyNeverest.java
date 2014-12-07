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

    public static Player randomPlayer() {
        return new Player(
                new Posn(randomInt(20, 980), randomInt(20, 980)),
                1000,
                1000,
                randomInt(10, 100),
                randomInt(1, 50));
    }

    public static Mob randomMob() {
        // Just need to use first constructor since first constructor already
        // sets mob in random posn.
        return new Mob(
                1000,
                1000,
                randomInt(1, 15000));
    }

    public static AttackWorld randomAW() {
        return new AttackWorld(
                randomPlayer(),
                randomMob(),
                randomInt(1, 5000),
                randomInt(1, 999),
                false);
    }

    public static DodgeWorld randomDW() {
        return new DodgeWorld(
                randomPlayer(),
                randomMob(),
                randomInt(1, 5000),
                randomInt(1, 299),
                false);
    }

    static int testMobReact = 0;
    static int testMobChase = 0;

    public boolean testPlayerMove(Tester t) {
        Player p = randomPlayer();
        Player np1 = p.move("left");
        Player np2 = p.move("right");
        Player np3 = p.move("up");
        Player np4 = p.move("down");
        Player np5 = p.move("z");
        return t.checkExpect(p.posn.x - p.movementSpeed,
                np1.posn.x, "test move - left" + "\n")
                && t.checkExpect(p.posn.x + p.movementSpeed,
                        np2.posn.x, "test move - right" + "\n")
                && t.checkExpect(p.posn.y - p.movementSpeed,
                        np3.posn.y, "test move - up" + "\n")
                && t.checkExpect(p.posn.y + p.movementSpeed,
                        np4.posn.y, "test move - down" + "\n")
                && t.checkExpect(p.posn,
                        np5.posn, "test move - false case" + "\n");
    }

    public boolean testMobMove(Tester t) {
        Mob m = randomMob();
        Mob m1 = m.move(1);
        Mob m2 = m.move(2);
        Mob m3 = m.move(3);
        Mob m4 = m.move(4);
        Mob m5 = m.move(5);
        return t.checkExpect(m.posn.x - m.speed,
                m1.posn.x, "test move - left" + "\n")
                && t.checkExpect(m.posn.x + m.speed,
                        m2.posn.x, "test move - right" + "\n")
                && t.checkExpect(m.posn.y - m.speed,
                        m3.posn.y, "test move - up" + "\n")
                && t.checkExpect(m.posn.y + m.speed,
                        m4.posn.y, "test move - down" + "\n")
                && t.checkExpect(m.posn,
                        m5.posn, "test move - false case" + "\n");
    }

    // testing react also tests the hit checkers, so no need to test hit checkers
    // since if react works as it should, then the hit checkers also work.
    public static void testMobReact() throws Exception {
        Player p = randomPlayer();
        Mob m = randomMob();
        Mob rm = m.react(p);
        for (int i = 0; i < 50; i++) {
            if (rm.posn == m.posn) {
                throw new Exception("React is not working");
            }
            if (m.hitAtAll(p)) {
                if (rm.health == m.health) {
                    throw new Exception("React is not taking away health");
                }
            }
            testMobReact++;
        }
    }
    
    public static void testMobChase() throws Exception {
        Player p = randomPlayer();
        Mob m = randomMob();
        Mob cm = m.chase(p);
        for (int i = 0; i < 50; i++) {
            if (cm.posn == m.posn) {
                throw new Exception("Not chasing/moving");
            }
            testMobChase++;
        }
    }

    public static void main(String[] args) throws Exception {
        TestyNeverest tn = new TestyNeverest();
        Tester.runReport(tn, false, false);
        //Doing a mix of Game Worlds' provided Tester functions (checkExpect)
        //and a mix of my own property tests
        testMobReact();
        System.out.println("Tested mobreact " + testMobReact + " times successfully");
        
        testMobChase();
        System.out.println("Tested mobchase " + testMobChase + " times successfully");
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
