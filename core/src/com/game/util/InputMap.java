package com.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.HashMap;

public class InputMap extends HashMap<Integer, Boolean> implements InputProcessor {

    public Integer x;
    public Integer y;
    public Boolean leftClicked;

    public InputMap() {
        x = 0;
        y = 0;
        leftClicked = false;
    }

    public boolean pressed(String key) {
        int keyCode = Input.Keys.valueOf(key.toUpperCase());

        return getOrDefault(keyCode, false);
    }

    public boolean keyDown(int keycode) {
        put(keycode, true);

        return true;
    }

    public boolean keyUp(int keycode) {
        put(keycode, false);

        return true;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            x = screenX;
            y = Gdx.graphics.getHeight() - screenY;
            leftClicked = true;
        }

        return true;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            leftClicked = false;
        }

        return true;
    }

    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        x = screenX;
        y = Gdx.graphics.getHeight() - screenY;

        return true;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }


    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
