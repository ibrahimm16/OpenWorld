package com.ibrahim.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ibrahim.game.OpenWorldGame;
import com.ibrahim.game.util.InputMap;

public abstract class BaseScreen implements Screen {

    final protected OpenWorldGame game;

    final InputMap inputMap;

    final protected SpriteBatch batch;
    final protected BitmapFont font;
    final protected OrthographicCamera camera;

    public BaseScreen(OpenWorldGame game) {
        this.game = game;
        this.inputMap = game.inputMap;
        this.batch = game.batch;
        this.font = game.font;

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
    }

    public void update() {

    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }

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
