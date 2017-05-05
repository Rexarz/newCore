package com.rexarz.dungeoncore.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rexarz.dungeoncore.core.GameCore;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 960;
        config.height = 640;
        config.vSyncEnabled = false;

        new LwjglApplication(new GameCore(), config);
    }
}
