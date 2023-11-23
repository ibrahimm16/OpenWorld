package com.ibrahim.game.screens;

import com.badlogic.gdx.Gdx;
import com.ibrahim.game.OpenWorldGame;
import com.ibrahim.game.util.State;

public class MenuScreen extends BaseScreen {

    public MenuScreen(OpenWorldGame game) {
        super(game);
    }

    public void update() {
        if (Gdx.input.isTouched()) {
            game.setScreen(game.gameStates.get(State.GAME));
            dispose();
        }
    }

    public void render(float delta) {
        super.render(delta);

        game.batch.begin();
        game.font.draw(game.batch, "chillin", 100, 100);
        game.batch.end();

        update();
    }
}
