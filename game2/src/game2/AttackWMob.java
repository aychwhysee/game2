package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class AttackWMob {
    /*
     list of mobs interface
     each element in list is a mob with diff attributes (HP, posn)
     movement behavior of each mob is same so just need this one class
     have hit checker in mobs class
     */

    public Posn posn; // each mob has a posn

    public final int speed = 12; // mob speed. will always be this speed.
    public int b_width; //board width
    public int b_height; //board height

    public final int width = 60; //mob width
    public final int height = 60; // mob height

    public int health; // mob HP
    // initial health = ?

    public IColor color; //fix later for color change in other world

    public Random random = new Random();

    public AttackWMob(int b_width, int b_height, int health) {
        this.b_width = b_width;
        this.b_height = b_height;
        this.health = health;
        this.posn = new Posn(randomX(b_width), randomY(b_height)); //random starting posn
    }

    public AttackWMob(Posn posn, int b_width, int b_height, int health) {
        this.posn = posn;
        this.b_width = b_width;
        this.b_height = b_height;
        this.health = health;
    }

    public int randomX(int b_width) {
        return random.nextInt(b_width - (width * 2)) + width;
    }

    public int randomY(int b_height) {
        return random.nextInt(b_height - (height * 2)) + height;
    }

//    public AttackWMob move() { 
//        // hmm...need variable to keep track of last movement direction?
//        // have die roll that is weighted towards last dir?
//        // OR DON'T EVEN JUST HAVE THIS, AND ONLY HAVE THE MOB REACT TO PLAYER
//        // WHEN IT IS TOUCHED. SIMPLIFICATION AND STILL MEETING THE REQS? 
//        int min = 1;
//        int max = 4;
//        int randomDir = random.nextInt((max - min) + 1) + min;
//        // 1 = left, 2 = right, 3 = up, 4 = down
//        // have it move in one direction at least 3 times before doing another
//        // random direction. forloop? maybe not.....do something twice, then
//        // return just one instance of the next frame?...
//        if (randomDir == 1) {
//            return new AttackWMob()
//        }
//        // randomly moving by itself
//    }
//
//    public AttackWMob react(AttackWPlayer player) {
//        // calls helper functions and moves in reaction to them
//        //*** possible to combine react into move? using ifs?
//        // Decrease mob HP by player ATT
//        // if hitOnLeft move right
//        // if hitOnRight move left
//        // if hitOnTop move down
//        // if hitOnBot move up
//
//        // Want to make it move away 5 times so using a forloop, but I don't
//        // think it'll work. It'll just create 5 new mobs no?...... :(
//        if (this.hitOnLeft(player)) {
//            this.health = this.health - player.attackStat;
//            for (int i = 0; i < 5; i++) {
//                return new AttackWMob(
//                        new Posn(this.posn.x + this.speed, this.posn.y),
//                        this.b_width,
//                        this.b_height,
//                        this.health);
//            }
//        } else if (this.hitOnRight(player)) {
//            this.health = this.health - player.attackStat;
//            for (int i = 0; i < 5; i++) {
//                return new AttackWMob(
//                        new Posn(this.posn.x - this.speed, this.posn.y),
//                        this.b_width,
//                        this.b_height,
//                        this.health);
//            }
//        } else if (this.hitOnTop(player)) {
//            this.health = this.health - player.attackStat;
//            for (int i = 0; i < 5; i++) {
//                return new AttackWMob(
//                        new Posn(this.posn.x, this.posn.y + this.speed),
//                        this.b_width,
//                        this.b_height,
//                        this.health);
//            }
//        } else if (this.hitOnBot(player)) {
//            this.health = this.health - player.attackStat;
//            for (int i = 0; i < 5; i++) {
//                return new AttackWMob(
//                        new Posn(this.posn.x, this.posn.y - this.speed),
//                        this.b_width,
//                        this.b_height,
//                        this.health);
//            }
//        } else {
//            return this;
//        }
//    }
    // To be used in DodgeWorld
    public AttackWMob chase(AttackWPlayer player) {
        // Compare player.posn and this.posn. If player.posn.x < this.posn.x make
        // the mob move left (towards player), and similar algorithms for
        // other way and down/up. Is this a correct way of thinking about it?
        if (player.posn.x < this.posn.x) {
//            return new AttackWMob(
//                    new Posn(this.posn.x - this.speed, this.posn.y),
//                    this.b_width,
//                    this.b_height,
//                    this.health);
            return this.move(1);
        } else if (player.posn.x > this.posn.x) {
//            return new AttackWMob(
//                    new Posn(this.posn.x + this.speed, this.posn.y),
//                    this.b_width,
//                    this.b_height,
//                    this.health);
            return this.move(2);
        } else if (player.posn.y < this.posn.y) {
//            return new AttackWMob(
//                    new Posn(this.posn.x, this.posn.y - this.speed),
//                    this.b_width,
//                    this.b_height,
//                    this.health);
            return this.move(3);
        } else if (player.posn.y > this.posn.y) {
//            return new AttackWMob(
//                    new Posn(this.posn.x, this.posn.y + this.speed),
//                    this.b_width,
//                    this.b_height,
//                    this.health);
            return this.move(4);
        } else {
            return this;
        }
    }
        // To be used in AttackWorld
    public AttackWMob react(AttackWPlayer player) {
        // use the below checkers to make mob react into opposite direction...
        // but how do I keep it moving? if hitOnLeft, move right X amt of times
        // and then take a diff dir, OR just keep moving right until hit again?
        // So need a move(dir) function that takes in a dir and moves mob in that dir?
        if (this.hitOnLeft(player)) {
            this.health = this.health - player.attackStat;
            return this.move(1);
        }
    }

    public AttackWMob move(int dir) {
        // 1 = left, 2 = right, 3 = up, 4 = down
        // random thing just in case..
//        int min = 1;
//        int max = 4;
//        int randomDir = random.nextInt((max - min) + 1) + min;
        if (dir == 1) {
            if (this.posn.x - this.width / 2 <= 0) {
                return this.move(2);
            } else {
                return new AttackWMob(
                        new Posn(this.posn.x - this.speed, this.posn.y),
                        this.b_width,
                        this.b_height,
                        this.health);
            }
        } else if (dir == 2) {
            if (this.posn.x + this.width / 2 >= b_width) {
                return this.move(1);
            } else {
                return new AttackWMob(
                        new Posn(this.posn.x + this.speed, this.posn.y),
                        this.b_width,
                        this.b_height,
                        this.health);
            }
        } else if (dir == 3) {
            if (this.posn.y - this.height / 2 <= 0) {
                return this.move(4);
            } else {
                return new AttackWMob(
                        new Posn(this.posn.x, this.posn.y - this.speed),
                        this.b_width,
                        this.b_height,
                        this.health);
            }
        } else if (dir == 4) {
            if (this.posn.y + this.height/2 >= b_height) {
                return this.move(3);
            } else {
                return new AttackWMob(
                new Posn(this.posn.x, this.posn.y + this.speed),
                this.b_width,
                this.b_height,
                this.health);
            }
        } else {
            return this;
        }
    }

    // If we don't need another mob class for other mode...
    // Then these checkers would just be used in first mode. Need another
    // set of checkers for second mode, but in player class.
    // need four separate checkers since mob will need to react depending on
    // which side it was hit on.
    public boolean hitOnLeft(AttackWPlayer player) {
        // check left x posn
        return ((player.posn.x + player.width / 2 >= this.posn.x - this.width / 2)
                && (player.posn.x + player.width / 2 <= this.posn.x + this.width / 2)
                && (player.posn.y - player.height / 2 <= this.posn.y + this.height / 2)
                && (player.posn.y + player.height / 2 >= this.posn.y - this.height / 2));
    }

    public boolean hitOnRight(AttackWPlayer player) {
        // check right x posn
        return ((player.posn.x - player.width / 2 <= this.posn.x + this.width / 2)
                && (player.posn.x - player.width / 2 >= this.posn.x - this.width / 2)
                && (player.posn.y - player.height / 2 <= this.posn.y + this.height / 2)
                && (player.posn.y + player.height / 2 >= this.posn.y - this.height / 2));
    }

    public boolean hitOnTop(AttackWPlayer player) {
        // check top y posn
        return ((player.posn.y + player.height / 2 >= this.posn.y - this.height / 2)
                && (player.posn.y + player.height / 2 <= this.posn.y + this.height / 2)
                && (player.posn.x - player.width / 2 <= this.posn.x + this.width / 2)
                && (player.posn.x + player.width / 2 >= this.posn.x - this.width / 2));
    }

    public boolean hitOnBot(AttackWPlayer player) {
        // check bottom y posn
        return ((player.posn.y - player.height / 2 <= this.posn.y + this.height / 2)
                && (player.posn.y - player.height / 2 >= this.posn.y - this.height / 2)
                && (player.posn.x - player.width / 2 <= this.posn.x + this.width / 2)
                && (player.posn.x + player.width / 2 >= this.posn.x - this.width / 2));
    }

    public boolean hitAtAll(AttackWPlayer player) {
        return (this.hitOnLeft(player) || this.hitOnRight(player)
                || this.hitOnTop(player) || this.hitOnBot(player));
    } // I have no idea what I'm doing

//    public boolean isDeadHuh() {
//         //check Mob health. if < 0, return true; else false.
//        if (this.health <= 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }

}
