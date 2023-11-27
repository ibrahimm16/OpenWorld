package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.OpenWorldGame;

public class MenuScreen extends BaseScreen {

    final OrthographicCamera camera;
    final SpriteBatch batch;

    public MenuScreen(OpenWorldGame game) {
        super(game);

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void update() {
        if (input.leftClicked) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        game.font.draw(batch, "chillin", 400, 400);
        batch.end();

        update();
    }

    public void dispose() {
        batch.dispose();

        super.dispose();
    }
}
