package com.me.tiledMapGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.title = "MDAD";
		cfg.useGL20 = true;
		cfg.width = TiledMapGame.screenWidth;
		cfg.height = TiledMapGame.screenHeight;
		
		cfg.resizable = false; // Disables JFrame resizing
		
		new LwjglApplication(new TiledMapGame(), cfg);
	}
}
