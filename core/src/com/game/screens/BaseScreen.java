package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.game.OpenWorldGame;
import com.game.util.InputMap;

public abstract class BaseScreen implements Screen {

    final protected OpenWorldGame game;
    final protected BitmapFont font;
    final protected InputMap input;

    public BaseScreen(OpenWorldGame game) {
        this.game = game;
        this.font = game.font;

        this.input = new InputMap();
        Gdx.input.setInputProcessor(input);
    }

    public abstract void update();

    public void dispose() {
    }

    public void show() {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void resize(int width, int height) {
    }
}
