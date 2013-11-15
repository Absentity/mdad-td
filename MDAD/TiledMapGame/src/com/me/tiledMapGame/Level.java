package com.me.tiledMapGame;

import java.util.ArrayList;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.pathing.Node;
import com.me.tiledMapGame.pathing.ObjectGrid;

/**
 * A level contains many aspects require to initiate or resume
 * the gameplay screen (GameScreen.java). This class facilitates
 * reading back level save data or loading level configuration
 * from a file.
 */
public class Level {

	private TiledMap map;
	private ObjectGrid objectGrid;
	private int gold; // Example resource
	
	/**
	 * 
	 * @param name Name of a map, not a path
	 */
	public Level(String name) {
		map = new TmxMapLoader().load("maps/" + name + ".tmx");
		// TODO load enemy wave data file
		// TODO from map, generate blocked grid, not manually
		
		objectGrid = new ObjectGrid(16,16);
		
		/*Mark all unbuildable spots here*/
		this.getNode(10, 2).markObstacle();
		this.getNode(10, 3).markObstacle();
		this.getNode(10, 4).markObstacle();
		this.getNode(11, 3).markObstacle();
		this.getNode(13, 7).markObstacle();

		this.getNode(9, 16-7).markTower();
		
		// TODO start off with a set amount of money
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
	public void removeEnemy(int position){
		ObjectGrid.enemies.remove(position);
	}

	@Deprecated
	public void runPathing(int li){
		//TODO why was PathFinder changed?
	}
}
