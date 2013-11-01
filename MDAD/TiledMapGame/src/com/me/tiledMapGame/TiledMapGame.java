package com.me.tiledMapGame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.me.tiledMapGame.screens.GameScreen;
import com.me.tiledMapGame.screens.LevelSelectScreen;
import com.me.tiledMapGame.screens.MainMenuScreen;
import com.me.tiledMapGame.screens.Splash;

public class TiledMapGame extends Game {
	
	public final static int screenWidth = 320;
	public final static int screenHeight = 320;
	
	boolean mainScreen = true;
	public static MainMenuScreen M;
	LevelSelectScreen L;
	GameScreen P;
	public static Music music;
	public static Splash S;
	public ClickListener listener = new ClickListener();
	
	@Override
	public void create() {
		S = new Splash(this);
		M = new MainMenuScreen();
		this.setScreen(S);
		
		
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
