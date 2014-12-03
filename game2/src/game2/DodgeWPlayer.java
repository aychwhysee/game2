package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class DodgeWPlayer {

    public AttackWPlayer awp; //?? initialize to be the current attackWplayer..? how
    public Posn posn;

    public int b_width;
    public int b_height;

    public int width = 15;
    public int height = 20;

//    public int movementSpeed = awp.movementSpeed;
//    public int attackStat = awp.attackStat;
//    public int money = awp.money;
    public int movementSpeed;
    public int attackStat;
//    public int money;

    public IColor color = new Blue();

    public DodgeWPlayer(AttackWPlayer awp) { // make sure 2nd world player is same?
        this.posn = awp.posn;
        this.b_width = awp.b_width;
        this.b_height = awp.b_height;
        this.movementSpeed = awp.movementSpeed;
        this.attackStat = awp.attackStat;
//        this.money = awp.money;
    }

    public DodgeWPlayer move(String kee) {
        if (kee.equals("left")) {
//            return new DodgeWPlayer((awp.move("left"))); // need to initialize awp tho
            return new DodgeWPlayer(
                    new AttackWPlayer(new Posn(this.posn.x - movementSpeed, this.posn.y),
                            this.b_width, this.b_height, this.movementSpeed, this.attackStat));
        } else if (kee.equals("right")) {
            return new DodgeWPlayer(
                    new AttackWPlayer(new Posn(this.posn.x + movementSpeed, this.posn.y),
                            this.b_width, this.b_height, this.movementSpeed, this.attackStat));
        } else if (kee.equals("up")) {
            return new DodgeWPlayer(
                    new AttackWPlayer(new Posn(this.posn.x, this.posn.y - movementSpeed),
                            this.b_width, this.b_height, this.movementSpeed, this.attackStat));
        } else if (kee.equals("down")) {
            return new DodgeWPlayer(
                    new AttackWPlayer(new Posn(this.posn.x, this.posn.y + movementSpeed),
                            this.b_width, this.b_height, this.movementSpeed, this.attackStat));
        } else {
            return this;
        }
    }

    // Not going to deal with leveling up in this world.
    
    // checking mob hits
    // - top border of mob needs to be > bot border of player && bottom border of
    //   mob needs to be < top border of player for checking left/right (extremes)
    
    
    public boolean hitOnLeft(DodgeWMob mob) {
        // check left
        return ((mob.posn.x + mob.width / 2 >= this.posn.x - this.width / 2)
                && (mob.posn.y - mob.height / 2 <= this.posn.y + this.height / 2)
                && (mob.posn.y + mob.height / 2 >= this.posn.y - this.height / 2));
    }
    
    public boolean hitOnRight(DodgeWMob mob) {
        // check right
        return ((mob.posn.x - mob.width / 2 <= this.posn.x + this.width / 2)
                && (mob.posn.y - mob.height / 2 <= this.posn.y + this.height / 2)
                && (mob.posn.y + mob.height / 2 >= this.posn.y - this.height / 2));
    }
    
    public WorldImage drawImage() {
        return new RectangleImage(this.posn, this.width, this.height, this.color);
    }
    
    
    
}
