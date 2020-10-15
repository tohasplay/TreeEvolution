package com.adea.evogame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.adea.evogame.EvoMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Evo";
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		new LwjglApplication(new EvoMain(), config);
	}
}
