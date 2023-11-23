package com.ibrahim.game.util.display;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.ibrahim.game.OpenWorldGame;

public class MenuScreen implements Screen {

    final OpenWorldGame game;

    OrthographicCamera camera;

    public MenuScreen(OpenWorldGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 800, 480);
    }

    public void show() {

    }

    public void render(float delta) {

    }

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {
    }
}
