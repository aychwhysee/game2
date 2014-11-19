package game2;

import java.awt.Color;
import java.util.Random;

import tester.*;

import javalib.funworld.*;
import javalib.colors.*;
import javalib.worldcanvas.*;
import javalib.worldimages.*;

public class ConsListMobs implements ListMobs {

    public Mob first;
    public ListMobs rest;

    public ConsListMobs(Mob first, ListMobs rest) {
        this.first = first;
        this.rest = rest;
    }

    public WorldImage drawListMobs() {
        return new OverlayImages(first.drawImage(), rest.drawListMobs());
    }

    public ListMobs move() {
        return new ConsListMobs(first.move(), rest.move());
    }

    public ListMobs react(Player player) {
        return new ConsListMobs(first.react(player), rest.react(player));
    }

    // wait a sec. logistics aside, how would this even work? we want to
    // remove a mob when it dies. So with this setup, would I need to be
    // calling remove every single tick?????????
    public ListMobs remove(Mob mob) {
        if (this.first == mob) {
            return this.rest; ///???? not sure yet.
            // ok but what if we're only removing the third elt? if we just
            // return rest, what'll happen to the first and second elts?
        } else {
            return new ConsListMobs(this.first, rest.remove(mob));
        }
    }
}
