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

    public ListMobs remove() {
        if (first.isDeadHuh()) {
            return new ConsListMobs(
                    new Mob(new Posn(0, 0), 0, 0, 0),
                    rest); ///???? not sure yet.
        } else {
            return rest.remove();
        }
    }
}
