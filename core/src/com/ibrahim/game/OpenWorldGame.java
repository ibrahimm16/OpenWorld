package com.ibrahim.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.ScreenUtils;
import com.ibrahim.game.util.InputMap;
import com.ibrahim.game.util.State;

import java.util.HashMap;

public class OpenWorldGame extends Game {
    SpriteBatch batch;
    Texture img;
    Sprite spock;
    InputMap inputMap;
    BitmapFont font;
    TiledMap map;

    HashMap<State, Screen> gameStates;

    @Override
    public void create() {
        inputMap = new InputMap();
        Gdx.input.setInputProcessor(inputMap);

        batch = new SpriteBatch();
        img = new Texture("spock.png");
        spock = new Sprite(img);
        spock.setX(200);
        spock.setY(200);

        font = new BitmapFont(Gdx.files.internal("DialogInput32.fnt"));
        font.setColor(Color.BLUE);

        gameStates = new HashMap<>();
        gameStates.put(State.MAIN_MENU, )
    }

    void update() {
        if (inputMap.pressed(Input.Keys.W)) spock.translateY(3f);
        if (inputMap.pressed(Input.Keys.A)) spock.translateX(-3f);
        if (inputMap.pressed(Input.Keys.S)) spock.translateY(-3f);
        if (inputMap.pressed(Input.Keys.D)) spock.translateX(3f);

        if (inputMap.leftClicked)
            spock.setPosition(inputMap.x, inputMap.y);
    }

    @Override
    public void render() {
        update();

        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();


        batch.draw(spock, spock.getX(), spock.getY());

        font.draw(batch, "X: " + spock.getX(), 10, 60);
        font.draw(batch, "Y: " + spock.getY(), 10, 30);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        font.dispose();
//        map.dispose();
        gameStates.values().forEach(Screen::dispose);
    }
}
