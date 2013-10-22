package com.me.tiledMapGame;


import com.badlogic.gdx.Game;
import com.me.tiledMapGame.screens.MainMenuScreen;
import com.me.tiledMapGame.screens.Play;

public class TiledMapGame extends Game {
	boolean mainScreen = true;
	MainMenuScreen M;
	
	@Override
	public void create() {	
		M = new MainMenuScreen();
		setScreen(M);
		
		
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
		if(M.listener.isPressed()){
			this.getScreen().dispose();
			this.setScreen(new Play());
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
