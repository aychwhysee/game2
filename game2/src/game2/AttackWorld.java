package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class AttackWorld extends World {

    public static final int b_width = 1200;
    public static final int b_height = 1200;

    public int score; //will literally just be a tick counter
    public int timer; //will determine when to switch to Dodge mode

    public AttackWPlayer player;
    public AttackWMob mob;

    public boolean gameOver; // will be 'true' once mob hp is <= 0

    public AttackWorld() { //init
        super();
        this.player = new AttackWPlayer(new Posn(b_width / 2, 1100), b_width, b_height,
                5, 1);
        this.player.color = new Green();
        this.mob = new AttackWMob(b_width, b_height, 200);
        this.mob.color = new Red();
        this.score = 0;
        this.timer = 1000;
        this.gameOver = false;
    }

    public AttackWorld(AttackWPlayer player, AttackWMob mob, int score, int timer,
            boolean gameOver) {
        this.player = player;
        player.color = new Green();
        this.mob = mob;
        mob.color = new Red();
        this.score = score;
        this.timer = timer;
        this.gameOver = gameOver;
    }

    public World onTick() {
//        AttackWMob new_mob = this.mob;
        if (this.mob.health <= 0) {
            gameOver = true;
        }
        score++;
        if (score % 480 == 1) {
            player.levelUp();
        }
        timer--;
        if (timer <= 0) {
            return this.changeMode();
        } else {
            return new AttackWorld(player, mob.react(player), score, timer,
                    gameOver);
        }
    }

    public DodgeWorld changeMode() {
        return new DodgeWorld(this.player, this.mob, this.score, 300, false);
    }

    public World onKeyEvent(String kee) {
        if (kee.equals("left") || kee.equals("right")
                || kee.equals("up") || kee.equals("down")) {
            return new AttackWorld(player.move(kee), mob, score, timer, gameOver);
        } else {
            return this;
        }
    }

    public WorldEnd worldEnds() {
        if (gameOver) {
            return new WorldEnd(true, new OverlayImages(this.makeImage(),
                    new TextImage(new Posn(b_width / 2, b_height / 2),
                            ("You beat the monster! Your score is " + this.score
                            + "Aim lower next time!"),
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
                new Black());
    }

    public WorldImage scoreImage() {
        return new TextImage(
                new Posn(600, 25),
                ("Score: " + this.score),
                14,
                new Green());
    }

    public WorldImage mobHPImage() {
        return new TextImage(
                new Posn(100, 25),
                ("Mob HP: " + this.mob.health),
                14,
                new Green());
    }

    public WorldImage timerImage() {
        return new TextImage(
                new Posn(1100, 25),
                ("Timer: " + this.timer),
                14,
                new Green());
    }

    public WorldImage makeImage() {
        return new OverlayImages(board(), new OverlayImages(mob.drawImage(),
                new OverlayImages(player.drawImage(), new OverlayImages(scoreImage(),
                                new OverlayImages(mobHPImage(), timerImage())))));
    }

}
