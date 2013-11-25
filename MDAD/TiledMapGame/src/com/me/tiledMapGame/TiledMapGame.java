package com.me.tiledMapGame;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ObjectMap;
import com.me.tiledMapGame.entities.AnimationEntity;
import com.me.tiledMapGame.entities.EnemyType;
import com.me.tiledMapGame.entities.ProjectileType;
import com.me.tiledMapGame.entities.StructureType;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.entities.TowerType;
import com.me.tiledMapGame.entities.UnitType;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.screens.GameScreen;
import com.me.tiledMapGame.screens.LevelSelectScreen;
import com.me.tiledMapGame.screens.LoseScreen;
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
	public static ObjectMap<String, AnimationEntity> animationLibrary;
	public static ObjectMap<String, EnemyType> enemyTypeLibrary;
	public static ObjectMap<String, TowerType> towerTypeLibrary;
	public static ObjectMap<String, StructureType> structureTypeLibrary;
	public static ObjectMap<String, ProjectileType> projectileTypeLibrary;
	public static ObjectMap<String, UnitType> unitTypeLibrary;
	public static ObjectMap<String, Texture> textureLibrary;
	public static ObjectMap<String, Music> musicLibrary;
	public static ObjectMap<String, Sound> soundLibrary;
	public LoseScreen lose;
	
	public ClickListener listener = new ClickListener();
	
	boolean mainScreen = true;
	
	GameScreen P;
	
	@Override
	public void create() {
		S = new Splash(this);
		
		// Start up the game
		loadTextures();
		loadAudio();
		loadEntities();

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
		
		// http://www.codeproject.com/KB/game/677417/Explosion2.png
		textureLibrary.put("Explosion", new Texture("img/Explosion2.png"));
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
		structureTypeLibrary = new ObjectMap<String, StructureType>();
		projectileTypeLibrary = new ObjectMap<String, ProjectileType>();
		unitTypeLibrary = new ObjectMap<String, UnitType>();
		animationLibrary = new ObjectMap<String, AnimationEntity>();
		
		// Animations
		animationLibrary.put("Explosion", new AnimationEntity(textureLibrary.get("Explosion")));
		
		// Projectiles
		projectileTypeLibrary.put("Crescent", new ProjectileType(new Texture("img/possibleCresent.png"), 1, 5));
		projectileTypeLibrary.put("Fireball", new ProjectileType(new Texture("img/fireball.png"), 1, 5));
		projectileTypeLibrary.put("Canon", new ProjectileType(new Texture("img/fireball.png"), 1, 3, 30f, 30));
		
		// Towers
		towerTypeLibrary.put("Crescent", new TowerType("Crescent", new Texture("img/cresentTower.png"), 100, 70, 70f,
				projectileTypeLibrary.get("Crescent"), 0.2f, 10, 1));
		towerTypeLibrary.put("Bomb", new TowerType("Bomb", new Texture("img/bombTower.png"), 100, 80, 70f,
				projectileTypeLibrary.get("Canon"), 1.2f, 60, 2));
		towerTypeLibrary.put("Amplify", new TowerType("Amplify", new Texture("img/amplifyTower.png"), 100, 60, 70f,
				projectileTypeLibrary.get("Crescent"), 0f, 0, 3));
		towerTypeLibrary.put("Fireball", new TowerType("Fireball", new Texture("img/fireballTower.png"), 100, 30, 70f,
				projectileTypeLibrary.get("Fireball"), 0.6f, 6, 4));
		towerTypeLibrary.put("Portal", new TowerType("Portal", new Texture("img/portalTower.png"), 100, 999999, 70f,
				projectileTypeLibrary.get("Crescent"), 0f, 0, 0));
		
		// Structures
//		structureTypeLibrary.put("Kingdom", new StructureType(new Texture("img/Kingdom.png"),
//				1000, 0f, 0, "Gold"));
		structureTypeLibrary.put("Farm", new StructureType("Farm", new Texture("img/sheetStructureFarm.png"),
				100, 50, 3f, 20, "Gold"));
		
		// Enemies
		enemyTypeLibrary.put("Skeleton", new EnemyType("Skeleton", new Texture("img/Skeleton.png"), 100, .5f, 1, 1f, 10, 10));
		enemyTypeLibrary.put("Wight", new EnemyType("Wight", new Texture("img/wight.png"), 100, 1f, 1, .6f, 10, 10));
		enemyTypeLibrary.put("Wyvern", new EnemyType("Wyvern", new Texture("img/Wyvern2.png"), 250, .3f, 15, 1f, 10, 10));
		
		// Units
		unitTypeLibrary.put("Mage", new UnitType("Mage", new Texture("img/mage.png"), 100, 10, .6f, 70f,
				projectileTypeLibrary.get("Fireball"), 0.6f, 12, 1));
		
	}

	@Override
	public void dispose() {
		
		super.dispose();
		//LevelSaver save = new LevelSaver("testSaveFile.txt");
		
		//save.saveLevel(GameScreen.);
	}

	/**
	 * This is called every time!!
	 */
	@Override
	public void render() {		
		super.render();
		if(M == null)
			M = new MainMenuScreen();
		//Set the screen to the level select screen
		if(M.levelSelectListener.isPressed()){
			M.dispose();
			L = new LevelSelectScreen();
			this.setScreen(L);
			musicLibrary.get("titleSong").stop();
			musicLibrary.get("worldOne").play();
			//P = new GameScreen(null);
			//this.setScreen(P);
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
	if(lose != null && lose.listener.isPressed()){
			lose.dispose();
			M = new MainMenuScreen();
			this.setScreen(M);
		}
		if(L != null && L.goBackListener.isPressed()){
			L.dispose();
			M = new MainMenuScreen();
			this.setScreen(M);
		}
		if(P != null && P.lost){
			System.out.print("You Have Lost");
			P.lost=false;
			//P.dispose();
			lose = new LoseScreen();
			this.setScreen(lose);
		//	M = new MainMenuScreen();
			//this.setScreen(M);
			
			P.dispose();
		}
		if(L != null && L.level1Listener.isPressed()){
			Level Levels;
			L.dispose();
			Levels= new Level("MDADMap1v1");
			P = new GameScreen(Levels);
			this.setScreen(P);
			
		}
		if(L != null && L.level2Listener.isPressed()){
			Level Levels;
			L.dispose();
			Levels= new Level("MDADMap1v2");
			P = new GameScreen(Levels);
			this.setScreen(P);
			
		}
		if(L != null && L.level3Listener.isPressed()){
			Level Levels;
			L.dispose();
			Levels= new Level("MDADMap1v3");
			P = new GameScreen(Levels);
			this.setScreen(P);
			
		}
		if(L != null && L.level4Listener.isPressed()){
			Level Levels;
			L.dispose();
			Levels= new Level("MDADMap1v4");
			P = new GameScreen(Levels);
			this.setScreen(P);
			
		}
		if(L != null && L.level5Listener.isPressed()){
			Level Levels;
			L.dispose();
			Levels= new Level("MDADMap1v5");
			P = new GameScreen(Levels);
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
