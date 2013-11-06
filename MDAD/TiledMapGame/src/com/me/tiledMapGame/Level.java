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
	
	ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	/**
	 * 
	 * @param name Name of a map, not a path
	 */
	public Level(String name) {
		map = new TmxMapLoader().load("maps/" + name + ".tmx");
		objectGrid = new ObjectGrid(16,16);
		objectGrid.gridLayers.get(0).getNodeInGrid(0, 0).is_buildable = false;//TESTING TODO
		// TODO: load enemy wave data file
		// from map, generate blocked grid
		// start off with a set amount of money
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
	
	public ObjectGrid getObjectGrid(){
		return objectGrid;
	}
	
	/**
	 * TODO description
	 * 
	 * @param x x location in pixels
	 * @param y y location in pixels
	 * @return a node from the first GridLayer in this.objectGrid
	 */
	public Node getNode(int x, int y){
		return objectGrid.gridLayers.get(0).getNodeInGrid(x/32, y/32);
	}
	
	/**
	 * Removes the enemy from the enemies ArrayList. To be called upon enemy death.
	 * @param position The place in the ArrayList.
	 */
	public void removeEnemy(int position){
		enemies.remove(position);
	}
}
