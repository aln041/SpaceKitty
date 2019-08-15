package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SpaceKitty;
import com.mygdx.game.sprites.Cat;
import com.mygdx.game.sprites.Ufo;

public class PlayState extends State {

    private Texture background;
    //private TextureAtlas kittyAtlas;
    //private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> animation;
    private Cat cat;
    private float timePassed = 0;
    private Ufo ufo;

    private static final int UFO_SPACING = 125;
    private static final int UFO_COUNT = 6;
    private int CAMERA_OFFSET = 80;

    private Array<Ufo> ufos;

    protected PlayState(GameStateManager gsm) {
        super(gsm);
        //kittyAtlas = new TextureAtlas(Gdx.files.internal("kitty.atlas"));
        //animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(1/10f,kittyAtlas.getRegions());
        cat = new Cat(50, 10);
        camera.setToOrtho(false, SpaceKitty.WIDTH/2,SpaceKitty.HEIGHT/2);
        background = new Texture("background.png");
        //ufo = new Ufo(100);

        ufos = new Array<>();

        for(int i=1; i<=UFO_COUNT; i++){
            ufos.add(new Ufo(i * (UFO_SPACING + Ufo.UFO_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            cat.pullKitty();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        cat.update(dt);
        // update camera position based on where kitty is
        camera.position.x = cat.getPosition().x + CAMERA_OFFSET;

        // reposition the ufos when off screen
        for(Ufo ufo : ufos){
            // if tube is off screen to left of screen
            if(camera.position.x - (camera.viewportWidth / 2)
                    > ufo.getTop_pos().x + ufo.getTop().getWidth()){
                ufo.reposition(ufo.getTop_pos().x + (Ufo.UFO_WIDTH + UFO_SPACING) * UFO_COUNT);
            }
        }

        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        // draw the background
        sb.draw(background,camera.position.x-(camera.viewportWidth/2),0);
        timePassed += Gdx.graphics.getDeltaTime();
        //sb.draw(animation.getKeyFrame(timePassed, true), 50, 50);

        //draw the kitty
        sb.draw(cat.getCat().getKeyFrame(timePassed, true),
                cat.getPosition().x, cat.getPosition().y);


        // draw the ufo's
        for(Ufo ufo : ufos) {
            sb.draw(ufo.getTop(), ufo.getTop_pos().x, ufo.getTop_pos().y);
            sb.draw(ufo.getBottom(), ufo.getBot_pos().x, ufo.getBot_pos().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
