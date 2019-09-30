package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceKitty;

public class MenuState extends State {

    private Texture background;
    private Texture startButton;

    public MenuState(GameStateManager gsm){
        super(gsm); // contains camera, mouse, gsm
        camera.setToOrtho(false, (float)SpaceKitty.WIDTH/2,(float)SpaceKitty.HEIGHT/2);
        this.background = new Texture("background.png");
        this.startButton = new Texture("startButton.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background,0,0, SpaceKitty.WIDTH,SpaceKitty.HEIGHT);
        //sb.draw(startButton,((SpaceKitty.WIDTH - startButton.getWidth())/2), SpaceKitty.HEIGHT/2);
        sb.draw(startButton,camera.position.x-(float)startButton.getWidth()/2,camera.position.y);
        sb.end();
    }

    @Override
    public void dispose(){
        background.dispose();
        startButton.dispose();
        System.out.println("MenuState disposed");
    }
}
