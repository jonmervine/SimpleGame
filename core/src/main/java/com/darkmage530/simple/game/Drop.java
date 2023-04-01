package com.darkmage530.simple.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Drop extends Game {

    public SpriteBatch batch;
    public BitmapFont font;
    private Array<Screen> disposables = new Array<>();

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // use libGDX's default Arial font
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render(); //important!
    }

    public void dispose() {
        System.out.println("Dispose of Game");
        batch.dispose();
        font.dispose();

        for (Screen disposalHook : disposables) {
            disposalHook.dispose();
        }
    }

    public void disposalHook(Screen screen) {
        disposables.add(screen);
    }
}
