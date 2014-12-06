package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class AttackWPlayer {

    public Posn posn;

    public int b_width;
    public int b_height;

    public int width = 20;
    public int height = 20;

    // Eh, maybe too many player attributes to handle..? not anymore hopefully
    public int movementSpeed; //initial movementSpeed
    public int attackStat; //initial att
//    public int money = 0; //initial money---dont need money anymore

    public IColor color; // fix later for changing colors in transition b/w modes

    public AttackWPlayer(Posn posn, int b_width, int b_height,
            int movementSpeed, int attackStat) {
        this.posn = posn;
        this.b_width = b_width;
        this.b_height = b_height;
        this.movementSpeed = movementSpeed;
        this.attackStat = attackStat;
//        this.money = money;
    }

    public AttackWPlayer move(String kee) {
        if (kee.equals("left")) {
            if (this.posn.x - this.width / 2 <= 0) {
                return this;
            } else {
                return new AttackWPlayer(new Posn(this.posn.x - movementSpeed, this.posn.y),
                        this.b_width, this.b_height, this.movementSpeed, this.attackStat);
            }
        } else if (kee.equals("right")) {
            if (this.posn.x + this.width / 2 >= b_width) {
                return this;
            } else {
                return new AttackWPlayer(new Posn(this.posn.x + movementSpeed, this.posn.y),
                        this.b_width, this.b_height, this.movementSpeed, this.attackStat);
            }
        } else if (kee.equals("up")) {
            if (this.posn.y - this.height / 2 <= 0) {
                return this;
            } else {
                return new AttackWPlayer(new Posn(this.posn.x, this.posn.y - movementSpeed),
                        this.b_width, this.b_height, this.movementSpeed, this.attackStat);
            }
        } else if (kee.equals("down")) {
            if (this.posn.y + this.height / 2 >= b_height) {
                return this;
            }
            return new AttackWPlayer(new Posn(this.posn.x, this.posn.y + movementSpeed),
                    this.b_width, this.b_height, this.movementSpeed, this.attackStat);
        } else {
            return this;
        }
    }

    public AttackWPlayer levelUp() {
        Random rand = new Random();
        int min = 1;
        int max = 3;
        int randUp = rand.nextInt((max - min) + 1) + min;
        return new AttackWPlayer(this.posn, this.b_width, this.b_height,
                this.movementSpeed + randUp, this.attackStat + randUp);
    }

    //hit checkers, for use in 2nd mode/world
    // Helper: Check left and right
    public boolean hitByMobX(AttackWMob mob) {
        return (mob.posn.x + mob.width / 2 >= this.posn.x - this.width / 2)
                && (mob.posn.x - mob.width / 2 <= this.posn.x + this.width / 2);
    }

    // Helper: check top and bot; Check for both X && Y in world class to confirm touch
    public boolean hitByMobY(AttackWMob mob) {
        return (mob.posn.y + mob.height / 2 >= this.posn.y - this.height / 2)
                && (mob.posn.y - mob.height / 2 <= this.posn.y + this.height / 2);
    }

//    public boolean hitAtAll(AttackWMob mob) {
//        return (this.hitByMobX(mob) && this.hitByMobY(mob));
//    }
    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }
}
