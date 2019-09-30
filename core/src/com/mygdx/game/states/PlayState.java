package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SpaceKitty;
import com.mygdx.game.sprites.Cat;
import com.mygdx.game.sprites.Ufo;

public class PlayState extends State {

    private Texture background;
    private Cat cat;
    private Sound meow;
    private float timePassed = 0;
    private Array<Ufo> ufos;

    private static final int UFO_SPACING = 125;
    private static final int UFO_COUNT = 6;
    private static final int CAMERA_OFFSET = 80;

    protected PlayState(GameStateManager gsm) {
        super(gsm); // contains camera, mouse, gsm
        cat = new Cat(0, 200);
        camera.setToOrtho(false, (float)SpaceKitty.WIDTH/2,(float)SpaceKitty.HEIGHT/2);
        background = new Texture("background.png");
        ufos = new Array<>();

        for(int i=1; i<=UFO_COUNT; i++){
            ufos.add(new Ufo(i * (UFO_SPACING + Ufo.UFO_WIDTH)));
        }
        meow = Gdx.audio.newSound(Gdx.files.internal("meow.mp3"));
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
        for(int i=0; i<ufos.size; i++){
            Ufo ufo = ufos.get(i);
            // if tube is off screen to left of screen
            if(camera.position.x - (camera.viewportWidth / 2)
                    > ufo.getTop_pos().x + ufo.getTop().getWidth()){
                ufo.reposition(ufo.getTop_pos().x + (Ufo.UFO_WIDTH + UFO_SPACING) * UFO_COUNT);
            }
            if(ufo.collides(cat.getBounds())){
                gsm.set(new PlayState(gsm));
                meow.play();
                break;
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

        //draw the kitty
        sb.draw(cat.getCat().getKeyFrame(timePassed, true),
                cat.getPosition().x, cat.getPosition().y);

        // draw the ufo's
        for(int i=0; i<ufos.size; i++){
            Ufo ufo = ufos.get(i);
            sb.draw(ufo.getTop(), ufo.getTop_pos().x, ufo.getTop_pos().y);
            sb.draw(ufo.getBottom(), ufo.getBot_pos().x, ufo.getBot_pos().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        cat.dispose();
        //for(Ufo ufo : ufos){
        for(int i=0; i<ufos.size; i++){
            Ufo ufo = ufos.get(i);
            ufo.dispose();
        }
        System.out.println("PlayState disposed");
    }

}
