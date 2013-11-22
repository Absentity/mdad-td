package com.me.tiledMapGame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ObjectMap;
import com.me.tiledMapGame.entities.EnemyType;
import com.me.tiledMapGame.entities.ProjectileType;
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
	
	public static MainMenuScreen M;
	public static Splash S;
	public static LevelSelectScreen L;
	public static ObjectMap<String, EnemyType> enemyTypeLibrary;
	public static ObjectMap<String, TowerType> towerTypeLibrary;
	public static ObjectMap<String, TowerType> structureTypeLibrary;
	public static ObjectMap<String, ProjectileType> projectileTypeLibrary;
	public static ObjectMap<String, Texture> textureLibrary;
	public static ObjectMap<String, Music> musicLibrary;
	public static ObjectMap<String, Sound> soundLibrary;
	
	public ClickListener listener = new ClickListener();
	
	boolean mainScreen = true;
	
	GameScreen P;
	
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

		Music titleSong = Gdx.audio.newMusic(Gdx.files.internal("sounds/towersLoop.mp3"));
		titleSong.setLooping(true);
		titleSong.setVolume(.8f);
		titleSong.play();
		musicLibrary.put("titleSong", titleSong);
		
		Music worldOne = Gdx.audio.newMusic(Gdx.files.internal("sounds/World 1.mp3"));
		worldOne.setLooping(true);
		worldOne.setVolume(.8f);
		musicLibrary.put("worldOne", worldOne);

	}

	private void loadEntities() {
		enemyTypeLibrary = new ObjectMap<String, EnemyType>();
		towerTypeLibrary = new ObjectMap<String, TowerType>();
		structureTypeLibrary = new ObjectMap<String, TowerType>();
		projectileTypeLibrary = new ObjectMap<String, ProjectileType>();
		
		// Projectiles
		projectileTypeLibrary.put("Crescent", new ProjectileType(new Texture("img/possibleCresent.png"), 5));
		projectileTypeLibrary.put("Fireball", new ProjectileType(new Texture("img/fireball.png"), 5));
		projectileTypeLibrary.put("Canon", new ProjectileType(new Texture("img/fireball.png"), 3));
		
		// Towers
		towerTypeLibrary.put("Crescent", new TowerType(new Texture("img/cresentTower.png"), 100, 70f,
				projectileTypeLibrary.get("Crescent"), 0.2f, 10, 1));
		towerTypeLibrary.put("Bomb", new TowerType(new Texture("img/bombTower.png"), 100, 70f,
				projectileTypeLibrary.get("Canon"), 1.2f, 60, 2));
		towerTypeLibrary.put("Amplify", new TowerType(new Texture("img/amplifyTower.png"), 100, 70f,
				projectileTypeLibrary.get("Crescent"), 0f, 0, 3));
		towerTypeLibrary.put("Fireball", new TowerType(new Texture("img/fireballTower.png"), 100, 70f,
				projectileTypeLibrary.get("Fireball"), 0.6f, 6, 4));
		towerTypeLibrary.put("Portal", new TowerType(new Texture("img/portalTower.png"), 100, 70f,
				projectileTypeLibrary.get("Crescent"), 0f, 0, 0));
		
		// Structures //TODO TEMPORARILY USING TOWERS
//		structureTypeLibrary.put("Kingdom", new TowerType(new Texture("img/Castle.png"), 1000, 70f,
//				projectileTypeLibrary.get("Crescent"), 0f, 0, 0));
//		structureTypeLibrary.put("Farm", new TowerType(new Texture("img/Castle.png"), 1000, 70f,
//				projectileTypeLibrary.get("Crescent"), 0f, 0, 0));
		
		// Enemies
		enemyTypeLibrary.put("Skeleton", new EnemyType(new Texture("img/Skeleton.png"), 100, .5f, 4, 1f, 10, 10));
		enemyTypeLibrary.put("Wight", new EnemyType(new Texture("img/wight.png"), 100, 1f, 4, .6f, 10, 10));
		enemyTypeLibrary.put("Wyvern", new EnemyType(new Texture("img/Wyvern2.png"), 5000, .3f, 15, 1f, 10, 10));
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
			//L = new LevelSelectScreen();
			//this.setScreen(L);
			P = new GameScreen(null);
			this.setScreen(P);
		}
		
		//Set the screen to a level screen, TODO load level info
		if(M.levelLoadListener.isPressed()){
			M.dispose();
			Level L;
			LevelLoader.loadLevel("testSaveFile.txt");
			L = LevelLoader.getLevel();
			P = new GameScreen(L);
			musicLibrary.get("titleSong").stop();
			musicLibrary.get("worldOne").play();
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
