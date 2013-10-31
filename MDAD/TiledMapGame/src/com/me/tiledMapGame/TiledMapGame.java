package com.me.tiledMapGame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.screens.GameScreen;
import com.me.tiledMapGame.screens.LevelSelectScreen;
import com.me.tiledMapGame.screens.MainMenuScreen;

/**
 * TiledMapGame takes care of starting the game up, loading necessary
 * assets to start, managing menus, and changing screen states.
 */
public class TiledMapGame extends Game {
	boolean mainScreen = true;
	MainMenuScreen M;
	LevelSelectScreen L;
	GameScreen P;
	
	public static ObjectMap<String, Enemy> enemyLibrary;
	public static ObjectMap<String, Tower> towerLibrary;
	public static ObjectMap<String, Texture> textureLibrary;
	public static ObjectMap<String, Music> musicLibrary;
	public static ObjectMap<String, Sound> soundLibrary;
	
	@Override
	public void create() {
		
		// Start up the game
		loadEntities();
		loadAudio();
		loadTextures();
		
		M = new MainMenuScreen();
		this.setScreen(M);
	}

	/**
	 * Load Textures into object hashmap following this convention:
	 * typeName -
	 * 	type = texture type
	 *  name = unique name to identify the texture
	 * 
	 * Types:
	 *  ui
	 *  enemy
	 *  tower
	 *  
	 */
	private void loadTextures() {
		textureLibrary = new ObjectMap<String, Texture>();
		
		// textureLibrary.put(key, value);
	}

	private void loadAudio() {
		musicLibrary = new ObjectMap<String, Music>();
		soundLibrary = new ObjectMap<String, Sound>();

		// music code - change directory to sounds/song name.mp3 to change song
		Music song = Gdx.audio.newMusic(Gdx.files.internal("sounds/Facade.mp3"));
		song.setLooping(true);
		song.setVolume(.5f);
		song.play();
		musicLibrary.put("facade", song);
	}

	private void loadEntities() {
		enemyLibrary = new ObjectMap<String, Enemy>();
		towerLibrary = new ObjectMap<String, Tower>();
		
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
		// TODO: Remove this in favor of an event?
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
