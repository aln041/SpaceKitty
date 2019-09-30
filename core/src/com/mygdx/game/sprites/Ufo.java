package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Ufo {
    private Texture top, bottom;
    private Vector2 top_pos, bot_pos;
    private Random rand;
    private static final int FLUCTUATION = 130;
    private static final int GAP = 100;
    private static final int LOWEST_OPENING = 120;
    public static final int UFO_WIDTH = 50;
    private Rectangle boundsTop, boundsBot;

    public Ufo(float x){
        rand = new Random();
        top = new Texture("ufo_up.png");
        bottom = new Texture("ufo_down.png");

        top_pos = new Vector2(x, rand.nextInt(FLUCTUATION)+GAP+LOWEST_OPENING);
        bot_pos = new Vector2(x, top_pos.y-GAP-top.getHeight());

        boundsTop = new Rectangle(top_pos.x,top_pos.y,top.getWidth(),top.getHeight()-10);
        boundsBot = new Rectangle(bot_pos.x, bot_pos.y, bottom.getWidth(), bottom.getHeight()-10);
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
        boundsTop.setPosition(top_pos.x,top_pos.y);
        boundsBot.setPosition(bot_pos.x, bot_pos.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose(){
        top.dispose();
        bottom.dispose();
    }
}
