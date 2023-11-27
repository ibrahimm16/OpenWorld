package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.game.screens.MenuScreen;

public class OpenWorldGame extends Game {

    public BitmapFont font;

    public void create() {
        font = new BitmapFont();
        font.setColor(Color.RED);

        setScreen(new MenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        font.dispose();

        super.dispose();
    }
}
