package com.ibrahim.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1200, 900);
        config.setIdleFPS(30);
        config.setForegroundFPS(144);
        config.setTitle("OpenWorld");
        new Lwjgl3Application(new OpenWorldGame(), config);
    }
}
