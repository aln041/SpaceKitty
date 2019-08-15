package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Ufo {
    private Texture top, bottom;
    private Vector2 top_pos, bot_pos;
    private Random rand;
    private static final int FLUCTUATION = 130;
    private static final int GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static final int UFO_WIDTH = 52;

    public Ufo(float x){
        top = new Texture("ufo_up.png");
        bottom = new Texture("ufo_down.png");
        rand = new Random();

        top_pos = new Vector2(x, rand.nextInt(FLUCTUATION)+GAP+LOWEST_OPENING);
        bot_pos = new Vector2(x, top_pos.y-GAP-top.getHeight());
    }

    public Texture getTop() {
        return top;
    }

    public Texture getBottom() {
        return bottom;
    }

    public Vector2 getTop_pos() {
        return top_pos;
    }

    public Vector2 getBot_pos() {
        return bot_pos;
    }

    public void reposition(float x){
        top_pos.set(x, rand.nextInt(FLUCTUATION)+GAP+LOWEST_OPENING);
        bot_pos.set(x, top_pos.y-GAP-top.getHeight());
    }
}
