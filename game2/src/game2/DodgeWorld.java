package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class DodgeWorld extends World {

    public static final int b_width = 1000;
    public static final int b_height = 1000;

    public int score;
    public int timer;
    // once timer gets to 0, game will switch to attackworld mode

    public AttackWPlayer player; //need to initialize to be the same one from first mode..? how?
    public AttackWMob mob; //same here, though HP of mob probs doesnt matter.

    public boolean gameOver; // will be 'true' once player gets hit by mob.

    public DodgeWorld() {
        super();
        this.player = new AttackWPlayer(new Posn(b_width / 2, 900), b_width, b_height,
                15, 1);
        this.player.color = new Blue();
        this.mob = new AttackWMob(b_width, b_height, 200);
        this.mob.color = new Yellow();
        this.timer = 300; // fix later
        this.gameOver = false;
        this.score = 0;
    }

    public DodgeWorld(AttackWPlayer player, AttackWMob mob, int score, int timer,
            boolean gameOver) {
        this.player = player;
        player.color = new Blue(); //? need to make sure this is correct
        this.mob = mob;
        mob.color = new Yellow(); //? same here
        this.timer = timer;
        this.gameOver = gameOver;
        this.score = score;
    }

    public World onTick() {
        AttackWPlayer new_player = this.player;
        if (new_player.hitByMobX(mob) && new_player.hitByMobY(mob)) {
            gameOver = true;
        }
        timer--;
        // mob.chase(new_player); // need to write this later
        if (timer <= 0) { // change world here?
            return this.changeMode();// maybe not here. hmmmmmmmmmmmmmmmmmmm
        } else {
            return new DodgeWorld(new_player, mob.chase(new_player), score, timer, gameOver);
        }
    }

    public AttackWorld changeMode() {
        return new AttackWorld(this.player, this.mob, this.score, 1000, false); //fix
    }

    public World onKeyEvent(String kee) {
        if (kee.equals("left") || kee.equals("right")
                || kee.equals("up") || kee.equals("down")) {
            return new DodgeWorld(player.move(kee), mob, score, timer, gameOver);
        } else {
            return this;
        }
    }

    public WorldEnd worldEnds() {
        if (gameOver) {
            return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(b_width / 2, b_height / 2),
                            ("Game over! You got hit. You don't deserve a score."),
                            14,
                            new White())));
        } else {
            return new WorldEnd(false, this.makeImage());
        }
    }

    public WorldImage board() {
        return new RectangleImage(
                new Posn(b_width / 2, b_height / 2),
                b_width,
                b_height,
                new Red());
    }

    public WorldImage scoreImage() {
        return new TextImage(
                new Posn(500, 25),
                ("Timer: " + this.timer),
                14,
                new White());
    }

    public WorldImage makeImage() {
        return new OverlayImages(board(), new OverlayImages(mob.drawImage(),
                new OverlayImages(player.drawImage(), scoreImage())));
    }
}
