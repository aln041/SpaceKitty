package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.mygdx.game.SpaceKitty;

public class PlayState extends State {

    private TextureAtlas kittyAtlas;
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> animation;
    private float timePassed = 0;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        kittyAtlas = new TextureAtlas(Gdx.files.internal("kitty.atlas"));
        animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f,kittyAtlas.getRegions());
        camera.setToOrtho(false, SpaceKitty.WIDTH/2,SpaceKitty.HEIGHT/2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        timePassed += Gdx.graphics.getDeltaTime();
        sb.draw(animation.getKeyFrame(timePassed, true), 100, 100);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
