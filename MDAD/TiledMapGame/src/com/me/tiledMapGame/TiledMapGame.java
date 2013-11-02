package com.me.tiledMapGame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
<<<<<<< HEAD
<<<<<<< HEAD
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
=======
=======
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.Tower;
<<<<<<< HEAD
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8
=======
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8
import com.me.tiledMapGame.screens.GameScreen;
import com.me.tiledMapGame.screens.LevelSelectScreen;
import com.me.tiledMapGame.screens.MainMenuScreen;
import com.me.tiledMapGame.screens.Splash;

/**
 * TiledMapGame takes care of starting the game up, loading necessary
 * assets to start, managing menus, and changing screen states.
 */
public class TiledMapGame extends Game {
	
	public final static int screenWidth = 320;
	public final static int screenHeight = 320;
	
	boolean mainScreen = true;
	public static MainMenuScreen M;
	LevelSelectScreen L;
	GameScreen P;
<<<<<<< HEAD
<<<<<<< HEAD
	public static Music music;
	public static Splash S;
	public ClickListener listener = new ClickListener();
	
	@Override
	public void create() {
		S = new Splash(this);
		M = new MainMenuScreen();
		this.setScreen(S);
=======
	
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
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8
		
=======
	
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
		
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8
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
