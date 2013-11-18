package com.me.tiledMapGame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ObjectMap;
import com.me.tiledMapGame.entities.EnemyType;
import com.me.tiledMapGame.entities.TowerType;
import com.me.tiledMapGame.screens.GameScreen;
import com.me.tiledMapGame.screens.LevelSelectScreen;
import com.me.tiledMapGame.screens.MainMenuScreen;
import com.me.tiledMapGame.screens.Splash;

/**
 * TiledMapGame takes care of starting the game up, loading necessary
 * assets to start, managing menus, and changing screen states.
 */
public class TiledMapGame extends Game {
	
	public final static int screenWidth = 32*16;
	public final static int screenHeight = 32*16;
	
	boolean mainScreen = true;
	public static MainMenuScreen M;
	public static Splash S;
	public static LevelSelectScreen L;
	GameScreen P;
	
	public static ObjectMap<String, EnemyType> enemyTypeLibrary;
	public static ObjectMap<String, TowerType> towerTypeLibrary;
	public static ObjectMap<String, Texture> textureLibrary;
	public static ObjectMap<String, Music> musicLibrary;
	public static ObjectMap<String, Sound> soundLibrary;

	public ClickListener listener = new ClickListener();
	
	@Override
	public void create() {
		S = new Splash(this);
		
		// Start up the game
		loadEntities();
		loadAudio();
		loadTextures();

		M = new MainMenuScreen();
		this.setScreen(S);
		
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
	}

	private void loadAudio() {
		musicLibrary = new ObjectMap<String, Music>();
		soundLibrary = new ObjectMap<String, Sound>();

		// music code - change directory to sounds/song name.mp3 to change song
		Music song = Gdx.audio.newMusic(Gdx.files.internal("sounds/towersLoop.mp3"));
		song.setLooping(true);
		song.setVolume(.8f);
		song.play();
		musicLibrary.put("facade", song);
	}

	private void loadEntities() {
		enemyTypeLibrary = new ObjectMap<String, EnemyType>();
		towerTypeLibrary = new ObjectMap<String, TowerType>();
		
		enemyTypeLibrary.put("Skeleton", new EnemyType(new Texture("img/Skeleton.png"), 100, 1f));
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
		//Set the screen to the level select screen
		if(M.levelSelectListener.isPressed()){
			M.dispose();
			L = new LevelSelectScreen();
			this.setScreen(L);
		}
		
		//Set the screen to a level screen, TODO load level info
		if(M.levelLoadListener.isPressed()){
			M.dispose();
			P = new GameScreen(null);
			this.setScreen(P);
		}
		if(L != null && L.goBackListener.isPressed()){
			L.dispose();
			M = new MainMenuScreen();
			this.setScreen(M);
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
