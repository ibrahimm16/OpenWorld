package com.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(1200, 900);
//        config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
        config.setForegroundFPS(60);
        config.setIdleFPS(30);
        config.useVsync(false);
        config.setTitle("OpenWorld");
        new Lwjgl3Application(new OpenWorldGame(), config);
    }
}
