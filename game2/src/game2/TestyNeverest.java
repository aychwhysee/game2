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
                randomInt(0, 15000));
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
    static int testOnTickAW = 0;
    static int testOnTickDW = 0;

    //Move tests
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

    // Mob tests
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
                if (rm.health != m.health - p.attackStat) {
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

    // World tests
    // AttackWorld property tests
    public boolean testOnKeyEventAW(Tester t) {
        Player p = randomPlayer();
        Mob m = randomMob();
        AttackWorld aw = new AttackWorld(p, m, 0, 0, false);
        AttackWorld aw1 = new AttackWorld(p.move("left"), m, 0, 0, false);
        AttackWorld aw2 = new AttackWorld(p.move("right"), m, 0, 0, false);
        AttackWorld aw3 = new AttackWorld(p.move("up"), m, 0, 0, false);
        AttackWorld aw4 = new AttackWorld(p.move("down"), m, 0, 0, false);
        return t.checkExpect(aw.onKeyEvent("left"),
                aw1, "test onKeyEventAW - left")
                && t.checkExpect(aw.onKeyEvent("right"),
                        aw2, "test onKeyEventAW - left")
                && t.checkExpect(aw.onKeyEvent("up"),
                        aw3, "test onKeyEventAW")
                && t.checkExpect(aw.onKeyEvent("down"),
                        aw4, "test onkeyEventAW - down")
                && t.checkExpect(aw.onKeyEvent("z"),
                        aw);
    }

    // Test stat increase
    public static void testOnTickAW() throws Exception {
        Player p = randomPlayer();
        Player leveledP = new Player(p.posn, p.b_width, p.b_height,
                p.movementSpeed + randomInt(1, 3), p.attackStat + randomInt(1, 3));
        Mob m = randomMob();
        int sc = randomInt(0, 1000);
        int ti = randomInt(0, 200);
        World aw = new AttackWorld(p, m, sc, ti, false);
        World awt = new AttackWorld(p, m.react(p), sc + 1, ti - 1, true);
        World awpl = new AttackWorld(leveledP, m.react(p), sc + 1, ti - 1, false);
        World dw = new DodgeWorld(p, m, sc + 1, 300, false);
        for (int i = 0; i < 50; i++) {
            if (m.health <= 0) {
                if (aw.onTick() != awt) {
                    throw new Exception("Game not ending");
                }
            }
            if (sc % 480 == 0) {
                if (aw.onTick() != awpl) {
                    throw new Exception("Player stats not increasing");
                }
            }
            if (ti <= 0) {
                if (aw.onTick() != dw) {
                    throw new Exception("Not changing modes");
                }
            }
            testOnTickAW++;
        }
    }

    // DodgeWorld tests
    public boolean testOnKeyEventDW(Tester t) {
        Player p = randomPlayer();
        Mob m = randomMob();
        DodgeWorld dw = new DodgeWorld(p, m, 0, 0, false);
        DodgeWorld dw1 = new DodgeWorld(p.move("left"), m, 0, 0, false);
        DodgeWorld dw2 = new DodgeWorld(p.move("right"), m, 0, 0, false);
        DodgeWorld dw3 = new DodgeWorld(p.move("up"), m, 0, 0, false);
        DodgeWorld dw4 = new DodgeWorld(p.move("down"), m, 0, 0, false);
        return t.checkExpect(dw.onKeyEvent("left"),
                dw1, "test onKeyEventAW - left")
                && t.checkExpect(dw.onKeyEvent("right"),
                        dw2, "test onKeyEventAW - left")
                && t.checkExpect(dw.onKeyEvent("up"),
                        dw3, "test onKeyEventAW")
                && t.checkExpect(dw.onKeyEvent("down"),
                        dw4, "test onkeyEventAW - down")
                && t.checkExpect(dw.onKeyEvent("z"),
                        dw);
    }

    public static void testOnTickDW() throws Exception {
        Player p = randomPlayer();
        Mob m = randomMob();
        int sc = randomInt(0, 1000);
        int ti = randomInt(0, 200);
        World dw = new DodgeWorld(p, m, sc, ti, false);
        World dwt = new DodgeWorld(p, m.chase(p), sc, ti - 1, true);
        World aw = new AttackWorld(p, m, sc, 1000, false);
        for (int i = 0; i < 50; i++) {
            if (p.hitByMobX(m) && p.hitByMobY(m)) {
                if (dw.onTick() != dwt) {
                    throw new Exception("Game not ending");
                }
            }
            if (ti <= 0) {
                if (dw.onTick() != aw) {
                    throw new Exception("Not changing modes");
                }
            }
            testOnTickDW++;
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

        testOnTickAW();
        System.out.println("Tested onTickAW " + testOnTickAW + " times successfully");

        testOnTickDW();
        System.out.println("tested onTickDW " + testOnTickDW + " times successfully");
        /*
         Things to test/check
         - Player movement speed works X
         - Mob runs away from player X
         - Mob HP drops by player ATT stat X
         - Player stats work X (also tested in above test)
        
         //// Game reqs
         - Different modes w/ transitions......TESTED. Both ways! woooo
         - Independent Mob.....................TESTED. wooooooooo
         - Player attributes...................TESTED. wooooooooo
        
         - Mode changes (to diff world) every x seconds/ticks X
         - and vice versa
        
         - Player dies/game ends if they get hit in 2nd mode
         - mob chases playerX
        

        
         */
    }

}
