package com.me.tiledMapGame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.me.tiledMapGame.screens.GameScreen;
import com.me.tiledMapGame.screens.LevelSelectScreen;
import com.me.tiledMapGame.screens.MainMenuScreen;

public class TiledMapGame extends Game {
	boolean mainScreen = true;
	MainMenuScreen M;
	LevelSelectScreen L;
	GameScreen P;
	public static Music music;
	
	@Override
	public void create() {	
		M = new MainMenuScreen();
		this.setScreen(M);
		
		
		// music code - change directory to sounds/song name.mp3 to change song
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Facade.mp3"));
		music.setLooping(true);
		music.setVolume(.5f);
		music.play();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	/**
	 * This is called every time!!
	 */
	@Override
	public void render() {		
		super.render();
		// TODO: Remove this in favor of an event.
		if(M.listener.isPressed()){
			this.getScreen().dispose();
//			this.setScreen(new Play());
			P = new GameScreen(null);
			this.setScreen(P);
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
