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

    public int width = 15;
    public int height = 20;

    // Eh, maybe too many player attributes to handle..?
    public int movementSpeed = 5; //initial movementSpeed
    public int attackStat = 1; //initial att
    public int money = 0; //initial money

    public IColor color = new Green();

    public AttackWPlayer(Posn posn, int b_width, int b_height,
            int movementSpeed, int attackStat, int money) {
        this.posn = posn;
        this.b_width = b_width;
        this.b_height = b_height;
        this.movementSpeed = movementSpeed;
        this.attackStat = attackStat;
        this.money = money;
    }

    public AttackWPlayer move(String kee) {
        if (kee.equals("left")) {
            return new AttackWPlayer(new Posn(this.posn.x - movementSpeed, this.posn.y),
                    this.b_width, this.b_height, this.movementSpeed, this.attackStat,
                    this.money);
        } else if (kee.equals("right")) {
            return new AttackWPlayer(new Posn(this.posn.x + movementSpeed, this.posn.y),
                    this.b_width, this.b_height, this.movementSpeed, this.attackStat,
                    this.money);
        } else if (kee.equals("up")) {
            return new AttackWPlayer(new Posn(this.posn.x, this.posn.y - movementSpeed),
                    this.b_width, this.b_height, this.movementSpeed, this.attackStat,
                    this.money);
        } else if (kee.equals("down")) {
            return new AttackWPlayer(new Posn(this.posn.x, this.posn.y + movementSpeed),
                    this.b_width, this.b_height, this.movementSpeed, this.attackStat,
                    this.money);
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
                this.movementSpeed + randUp, this.attackStat + randUp, this.money);
    }

    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }
}
