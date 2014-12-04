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

    public IColor color = new Red(); //fix later for color change in other world

    public Random random = new Random();

    public AttackWMob(int b_width, int b_height, int health) {
        this.b_width = b_width;
        this.b_height = b_height;
        this.health = health;
        this.posn = new Posn(randomX(b_width), randomY(b_height));
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

    public AttackWMob move() {
        int min = 1;
        int max = 4;
        int randomDir = random.nextInt((max - min) + 1) + min;
        // 1 = left, 2 = right, 3 = up, 4 = down
        // have it move in one direction at least 3 times before doing another
        // random direction. forloop? maybe not.....do something twice, then
        // return just one instance of the next frame?...
        if (randomDir == 1) {
            return new AttackWMob()
        }
        // randomly moving by itself
    }

    public AttackWMob react(AttackWPlayer player) {
        // calls helper functions and moves in reaction to them
        //*** possible to combine react into move? using ifs?
        // Decrease mob HP by player ATT
        // if hitOnLeft move right
        // if hitOnRight move left
        // if hitOnTop move down
        // if hitOnBot move up

        // Want to make it move away 5 times so using a forloop, but I don't
        // think it'll work. It'll just create 5 new mobs no?...... :(
        if (this.hitOnLeft(player)) {
            this.health--;
            for (int i = 0; i < 5; i++) {
                return new AttackWMob(
                        new Posn(this.posn.x + this.speed, this.posn.y),
                        this.b_width,
                        this.b_height,
                        this.health);
            }
        } else if (this.hitOnRight(player)) {
            this.health--;
            for (int i = 0; i < 5; i++) {
                return new AttackWMob(
                        new Posn(this.posn.x - this.speed, this.posn.y),
                        this.b_width,
                        this.b_height,
                        this.health);
            }
        } else if (this.hitOnTop(player)) {
            this.health--;
            for (int i = 0; i < 5; i++) {
                return new AttackWMob(
                        new Posn(this.posn.x, this.posn.y + this.speed),
                        this.b_width,
                        this.b_height,
                        this.health);
            }
        } else if (this.hitOnBot(player)) {
            this.health--;
            for (int i = 0; i < 5; i++) {
                return new AttackWMob(
                        new Posn(this.posn.x, this.posn.y - this.speed),
                        this.b_width,
                        this.b_height,
                        this.health);
            }
        } else {
            return this;
        }
    }

    // re-check these later, since we're changing some dimensions around
    // If we don't need another mob class for other mode...
    // Then these checkers would just be used in first mode. Need another
    // set of checkers for second mode, but in player class.
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

    public boolean isDeadHuh() {
        // check Mob health. if < 0, return true; else false.
        if (this.health <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }

}
