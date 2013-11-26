package com.me.tiledMapGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ObjectIntMap;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.Entity;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.pathing.Node;
import com.me.tiledMapGame.pathing.ObjectGrid;

/**
 * A level contains many aspects require to initiate or resume
 * the gameplay screen (GameScreen.java). This class facilitates
 * reading back level save data or loading level configuration
 * from a file.
 */
public class Level {

	public int levelId = 1;			// For levelSaver
	public int castleX = 10;
	public int castleY = 10;
	public Entity lastBuilt;
	private TiledMap map;
	private ObjectGrid objectGrid;
	private int wave = 1;				// Indicates what wave of enemies is attacking
	private int totalWaves, enemPerWave;
	private float timeBetweenWaves, timeBetweenSpawns;
	private boolean won;
	int enemyTypes[];
	public int leveln;
	private static ObjectIntMap<String> resources;
	
	Vector2 spawnPoint = new Vector2();
	
	/**
	 * 
	 * @param name Name of a map, not a path
	 */
	public Level(String name) {
		map = new TmxMapLoader().load("maps/" + name + ".tmx");
		
		objectGrid = new ObjectGrid(16,16);
		TiledMapTileLayer groundLayer = (TiledMapTileLayer)map.getLayers().get(0);
		
		for(int i=0 ; i<16 ; i++) {
			for(int j=0 ; j<16 ; j++) {
				
				if(groundLayer.getCell(j, i).getTile().getProperties().containsKey("blocked")) {
					objectGrid.gridLayer(0).getNodeInGrid(j, i).is_passable = false;
					objectGrid.gridLayer(0).getNodeInGrid(j, i).is_buildable = false;
				} else if(groundLayer.getCell(j, i).getTile().getProperties().containsKey("solid")) {
					objectGrid.gridLayer(0).getNodeInGrid(j, i).is_buildable = false;
				} else if(groundLayer.getCell(j, i).getTile().getProperties().containsKey("spawn")) {
					spawnPoint.x = j*32;
					spawnPoint.y = i*32;
				}
				
			}
		}

//		System.out.println("spawn x = " + spawnPoint.x);
//		System.out.println("spawn y = " + spawnPoint.y);
		
		// TODO load enemy wave data file
		
		// TODO start off with a set amount of money
		
		try {
			Scanner sc = new Scanner(new File("blankLevel.txt"));
			
			totalWaves = sc.nextInt();
			enemyTypes = new int[totalWaves];
			
			for (int i=0 ; i<totalWaves ; i++) {
				enemyTypes[i] = sc.nextInt();
			}
			
			enemPerWave = sc.nextInt();
			
			timeBetweenWaves = sc.nextFloat();
			timeBetweenSpawns = sc.nextFloat();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		resources = new ObjectIntMap<String>();
		setResource("Gold", 9999);
		
		
		// Reggie: all y's that were 450 were changed to 400 to keep tower of life on the screen
		
		 if(name.contains("2")){
				ObjectGrid.towerList().add(new Tower(TiledMapGame.towerTypeLibrary.get("Portal"))); //TODO Change to Kingdom
				castleX = ((int)(475/32));
				castleY = ((int)(400/32));
				ObjectGrid.towerList().get(0).setPosition(castleX*32, castleY*32);
				leveln=2;
		}
		else if(name.contains("3")){
				ObjectGrid.towerList().add(new Tower(TiledMapGame.towerTypeLibrary.get("Portal"))); //TODO Change to Kingdom
				castleX = ((int)(450/32));
				castleY = ((int)(150/32));
				ObjectGrid.towerList().get(0).setPosition(castleX*32, castleY*32);
				leveln=3;
		}
		else if(name.contains("4")){
			ObjectGrid.towerList().add(new Tower(TiledMapGame.towerTypeLibrary.get("Portal"))); //TODO Change to Kingdom
			castleX = ((int)(2));
			castleY = ((int)(400/32));
			ObjectGrid.towerList().get(0).setPosition(castleX*32, castleY*32);
			leveln=4;
		}
		else if(name.contains("5")){
			ObjectGrid.towerList().add(new Tower(TiledMapGame.towerTypeLibrary.get("Portal"))); //TODO Change to Kingdom
			castleX = ((int)(475/32));
			castleY = ((int)(400/32));
			ObjectGrid.towerList().get(0).setPosition(castleX*32, castleY*32);
			leveln=5;
		}

		else if(name.contains("1")){
				ObjectGrid.towerList().add(new Tower(TiledMapGame.towerTypeLibrary.get("Portal"))); //TODO Change to Kingdom
				castleX = ((int)(475/32));
				castleY = ((int)(400/32));
				ObjectGrid.towerList().get(0).setPosition(castleX*32, castleY*32);
				leveln=1;
		}
	
	}
	
	/**
	 * Retrieve an enemy from the game's library and add it onto the grid.
	 * @param enemyName
	 */
	public void generateEnemy(String enemyName) {
		if (TiledMapGame.enemyTypeLibrary.containsKey(enemyName)) {
			Enemy e;
			if(enemyName.equals("Wyvern")){
				e = new Enemy(TiledMapGame.enemyTypeLibrary.get(enemyName), true);
				//e = TiledMapGame.enemyTypeLibrary.get(enemyName).createInstance();
			} else {
				e = TiledMapGame.enemyTypeLibrary.get(enemyName).createInstance();
			}
			e.setPosition(spawnPoint.x, spawnPoint.y);
			ObjectGrid.enemies.add(e);
			e.destX = this.castleX;
			e.destY = this.castleY;
		} else {
			System.err.println("[Level] no enemy named " + enemyName + " found in library.");
		}
	}
	
	/**
	 * Unserialize a saved level
	 * @return
	 */
	public static Level load() {
		// TODO load save data
		// find map name, new Level(name), add towers back on top
		// set the proper amount of gold
		return null;
	}
	
	/**
	 * Serialize the towers and their placement on the map
	 */
	public void save() {
		// TODO
	}
	
	/**
	 * Draw the map
	 */
	public void render() {
		
	}
	
	public TiledMap getMap(){
		return map;
	}

	@Deprecated
	public ObjectGrid getObjectGrid(){
		return objectGrid;
	}
	
	@Deprecated
	public Node[][] getGrid(int li){
		return objectGrid.gridLayers.get(0).getGrid();
	}
	
	/**
	 * TODO description
	 * 
	 * @param x x location in pixels
	 * @param y y location in pixels
	 * @param m multiplier for coordinates
	 * @return a node from the first GridLayer in this.objectGrid
	 */
	@Deprecated
	public Node getNode(int x, int y){
		return objectGrid.gridLayers.get(0).getNodeInGrid(x, y);
	}
	
	/**
	 * Removes the enemy from the enemies ArrayList. To be called upon enemy death.
	 * @param position The place in the ArrayList.
	 */
	@Deprecated
	public void removeEnemy(int position){
		ObjectGrid.enemies.remove(position);
	}

	public static int getResource(String resourceName){
		return resources.get(resourceName, 0);
	}
	
	public void setResource(String resourceName, int amount){
		resources.put(resourceName, amount);
	}
	
	public static void gainResource(String resourceName, int amount){
		resources.getAndIncrement(resourceName, 0, amount);
	}
	
	public int getWave(){
		return this.wave;
	}
	
	public int getTotalWaves(){
		return this.totalWaves;
	}
	
	public int getEnemiesPerWave(){
		return this.enemPerWave;
	}
	
	public float getTimeBetWaves(){
		return this.timeBetweenWaves;
	}
	
	public float getTimeBetSpawns(){
		return this.timeBetweenSpawns;
	}
	
	public int getEnemyTypes(int pos){
		return this.enemyTypes[pos];
	}
	
	public void setWave(int wave){
		this.wave = wave;
	}
	
	public void setWon(){
		won = true;
	}

public int normX(final int screenX) {
	if ((int) (((int)(screenX/32))) > TiledMapGame.screenWidth) {
		return TiledMapGame.screenWidth-32;
	} else if((int) (((int)(screenX/32))*32) < 0) {
		return 0;
	} else {
		return (int) (((int)(screenX/32)) * 32);
	}
}public int normY(final int screenY) {
	if ((int)((TiledMapGame.screenHeight) - ((screenY/32) * 32) - 32)+16 > TiledMapGame.screenHeight) {
		return TiledMapGame.screenHeight-32;
	} else if ((int)((TiledMapGame.screenHeight) - ((screenY/32) * 32) - 32) < 0) {
		return 0;
	} else {
		return (int)((TiledMapGame.screenHeight) - ((screenY/32) * 32) - 32);
	}
}

}