package com.me.tiledMapGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.title = "TiledMapGame";
		cfg.useGL20 = true;
		cfg.width = 672;
		cfg.height = 672;
		
		new LwjglApplication(new TiledMapGame(), cfg);
	}
}
