package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BrickBreaker;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "BrickBreaker";
		config.width = Gdx.graphics.getWidth();
		config.height = Gdx.graphics.getHeight();
		new LwjglApplication(new BrickBreaker(), config);
	}
}
