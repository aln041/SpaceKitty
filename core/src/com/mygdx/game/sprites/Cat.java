package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Cat {

    private static final int GRAVITY = 15;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private TextureAtlas kittyAtlas;
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> cat;
    private Rectangle bounds;
    private Sound effect;

    public Cat(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        kittyAtlas = new TextureAtlas(Gdx.files.internal("kitty.atlas"));
        cat = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f, kittyAtlas.getRegions());
        bounds = new Rectangle(x,y,
                //kittyAtlas.findRegion("kitty.atlas").getTexture().getWidth(),
                //kittyAtlas.findRegion("kitty.atlas").getTexture().getHeight());
                kittyAtlas.getRegions().peek().getRegionWidth()-15,
                kittyAtlas.getRegions().peek().getRegionHeight()-10);
        effect = Gdx.audio.newSound(Gdx.files.internal("sfx.ogg"));
    }

    public void update(float dt){
        if(position.y >= 350) velocity.add(0,GRAVITY,0);
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt,velocity.y,0);
        if(position.y >= 350) position.y = 350;
        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Animation<TextureRegion> getCat() {
        return cat;
    }

    public void pullKitty(){
        velocity.y = -250;
        effect.play(0.3f);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        kittyAtlas.dispose();
    }
}
