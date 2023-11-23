package com.ibrahim.game.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.ibrahim.game.OpenWorldGame;

public class GameScreen extends BaseScreen {

    Texture playerTexture;
    Sprite spock;
    TiledMap map;
    float speed = 3f;


    TextureAtlas textureAtlas;

    public GameScreen(OpenWorldGame game) {
        super(game);

        playerTexture = new Texture("spock.png");
        spock = new Sprite(playerTexture);
        spock.setX(200);
        spock.setY(200);
    }

    public void update() {
        if (inputMap.pressed(Input.Keys.W)) spock.translateY(speed);
        if (inputMap.pressed(Input.Keys.A)) spock.translateX(-speed);
        if (inputMap.pressed(Input.Keys.S)) spock.translateY(-speed);
        if (inputMap.pressed(Input.Keys.D)) spock.translateX(speed);

        if (inputMap.leftClicked) spock.setPosition(inputMap.x, inputMap.y);
    }

    public void render(float delta) {
        super.render(delta);

        batch.begin();
        batch.draw(spock, spock.getX(), spock.getY());
        font.draw(batch, "X: " + spock.getX(), 10, 60);
        font.draw(batch, "Y: " + spock.getY(), 10, 30);
        batch.end();

        update();
    }

    public void hide() {
        
    }

    public void dispose() {
        playerTexture.dispose();
//        map.dispose();

        super.dispose();
    }
}
