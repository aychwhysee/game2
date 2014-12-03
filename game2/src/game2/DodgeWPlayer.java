package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class DodgeWPlayer {
    
//    public AttackWPlayer awp;
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
    public int money;
    
    public IColor color = new Blue();

    public DodgeWPlayer(AttackWPlayer awp) { // make sure 2nd world player is same?
        this.posn = awp.posn;
        this.b_width = awp.b_width;
        this.b_height = awp.b_height;
        this.movementSpeed = awp.movementSpeed;
        this.attackStat = awp.attackStat;
        this.money = awp.money;
    }
    
    
}
