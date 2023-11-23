package com.ibrahim.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ibrahim.game.screens.GameScreen;
import com.ibrahim.game.screens.MenuScreen;
import com.ibrahim.game.util.InputMap;
import com.ibrahim.game.util.State;

import java.util.HashMap;

public class OpenWorldGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

    public InputMap inputMap;
    public HashMap<State, Screen> gameStates;

    @Override
    public void create() {
        inputMap = new InputMap();
        Gdx.input.setInputProcessor(inputMap);

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("DialogInput32.fnt"));
        font.setColor(Color.WHITE);

        gameStates = new HashMap<>();
        gameStates.put(State.MAIN_MENU, new MenuScreen(this));
        gameStates.put(State.GAME, new GameScreen(this));

        setScreen(gameStates.get(State.MAIN_MENU));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        gameStates.values().forEach(Screen::dispose);

        super.dispose();
    }
}
