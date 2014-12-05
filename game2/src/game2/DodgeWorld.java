package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class DodgeWorld extends World {

    public static final int b_width = 800;
    public static final int b_height = 800;

    public int timer;
    // once timer gets to 0, game will switch to attackworld mode

    public AttackWPlayer player; //need to initialize to be the same one from first mode..? how?
    public AttackWMob mob; //same here, though HP of mob probs doesnt matter.

    public boolean gameOver; // will be 'true' once player gets hit by mob.

    public DodgeWorld(AttackWPlayer player, AttackWMob mob, int timer,
            boolean gameOver) {
        this.player = player;
        this.mob = mob;
        this.timer = timer;
        this.gameOver = gameOver;
    }

    public World onTick() {
        AttackWPlayer new_player = this.player;
        if (new_player.hitByMobX(mob) && new_player.hitByMobY(mob)) {
            gameOver = true;
        }
        timer--;
        // mob.chase(new_player); // need to write this later
        return new DodgeWorld(new_player, mob.chase(new_player), timer, gameOver);
    }

    public World onKeyEvent(String kee) {
        if (kee.equals("left") || kee.equals("right")
                || kee.equals("up") || kee.equals("down")) {
            return new DodgeWorld(player.move(kee), mob, timer, gameOver);
        } else {
            return this;
        }
    }
}
