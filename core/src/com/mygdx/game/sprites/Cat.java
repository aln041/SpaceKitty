package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Cat {
    private static final int GRAVITY = 15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> cat;

    public Cat(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        TextureAtlas kittyAtlas = new TextureAtlas(Gdx.files.internal("kitty.atlas"));
        cat = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f, kittyAtlas.getRegions());
    }

    public void update(float dt){
        if(position.y >= 350) velocity.add(0,GRAVITY,0);
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        if(position.y >= 350) position.y = 350;
        velocity.scl(1/dt);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Animation<TextureRegion> getCat() {
        return cat;
    }

    public void pullKitty(){
        velocity.y = -250;
    }
}
