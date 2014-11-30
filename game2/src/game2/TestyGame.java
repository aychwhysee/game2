package game2;

import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class TestyGame {

    /*
     Game ideas: idea 1: NEVEREST (arbitrary name)
    
     *** PERSISTENT PLAYER ATTRIBUTES
     - You gain levels based on how much time you spend in the game
     - As you gain levels, your "attack" (ATT) stat increases, making it easier
     to kill mobs. Enemy HP decreases by player ATT every time
     player hits enemy.
    
     *** INDEPENDENT MOBS
     - One massive map.
     - There are only a set number of mobs in the game, however, with each
     mob having a different amount of HP (color coded?). Your objective
     is to clear the game as quickly as possible. Of course, since you passively
     gain levels and ATT, the game will get easier to clear as more time passes.
     - You are allowed to just AFK and gain levels and come back later and
     clear the game, but the timer is always going.
     - So the point would be to see if you can clear the game quickly with
     as low of a player level as possible.
     - Mobs will just be roaming around by themselves doing whatever. If they
     get hit, they will move the opposite direction of you (or run away from you).
     
    
     - You can't die in this mode. You can't lose the game. Just for simplicity. Your validation
     of success depends on how fast you can clock the game.
    
     *** MODES
     - So we have the main mode, as described above
     - Second mode is the reverse! Player will die (game will end) if player gets
       hit by a mob. Player's job is to avoid/dodge all mobs for a set amount of time.
     */
    public static void main(String[] args) {
        System.out.println("hello world!");
        //....I need ideas!
        
        /*
        Things to test/check
        - Player movement speed works
        - Player attack stat deals right amount of damage to mob
        - Mobs move "randomly" (at least 3 ticks in one direction, then switch)
        - Mobs run away when hit by player
        
        
        */
    }

}
