/*
 * Copyright 2013 by some group of people
 */
package com.me.tiledMapGame.pathing;

import java.util.ArrayList;
import java.util.List;

import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.Entity;
import com.me.tiledMapGame.entities.Projectile;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.entities.Unit;

/**
 *
 * @author brandon
 */
/**
 * Contains multiple {@link GridLayer}s and a list of all entities.
 */
public class ObjectGrid {
	
	public static List<GridLayer> gridLayers;
//  private static List<Entity> entities;
	public static ArrayList<Tower> towers = new ArrayList<Tower>();
	public static ArrayList<Unit> units = new ArrayList<Unit>();
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	// Entities in here will be erased
	public static ArrayList<Entity> disposeList = new ArrayList<Entity>();
	
    private int length;
    private int width;
    
    public ObjectGrid(int length, int width){
    	this.length = length;
    	this.width = width;
    	gridLayers = new ArrayList<GridLayer>();
    	gridLayers.add(new GridLayer(length, width));
    }
    
    /**
     * Creates a new initialized (but empty) GridLayer and adds it 
     * to the end of ArrayList<{@link GridLayer}> GridLayers.
     * 
     * @return void
     */
    public void addGridLayer(){
    	gridLayers.add(new GridLayer(length, width));
    }
    
    /**
     * Marks all nodes in the grid as unvisited for the layer indicated by li;
     * this MUST be called before running PathFinder.find_path() again.
     * 
     * @return void
     * @param li index for the layer to be cleared
     */
    public void clearVisited(int li){
    	for(int i = 0; i < length; ++i){
    		for(int j = 0; j < width; ++j){
    			gridLayers.get(li).getGrid()[i][j].visited = false;
    		}
    	}
    }
    
    /**
     * Calls {@link clearVisited(int li)} for all layers in {@link GridLayer}s.
     */
    public void clearAllVisited(){
    	for(int i = 0; i < gridLayers.size(); ++i){
    		this.clearVisited(i);
    	}
    }

    /**
     * Retrieve the list of all entities on the grid
     * @return entity list
     */
    public static List<Tower> towerList() {
    	// Set final before returning?
    	return towers;
    }
    
    /**
     * Retrieve the list of all entities on the grid
     * @return entity list
     */
    public static List<Unit> unitList() {
    	// Set final before returning?
    	return units;
    }
    
    /**
     * Retrieve the list of all entities on the grid
     * @return entity list
     */
    public static List<Enemy> enemyList() {
    	// Set final before returning?
    	return enemies;
    }
    
    /**
     * Retrieve the list of all entities on the grid
     * @return entity list
     */
    public static List<Projectile> projectileList() {
    	// Set final before returning?
    	return projectiles;
    }
    
    /**
     * Retrieve the list of all grid layers
     * @return grid layers list
     */
    public static GridLayer gridLayer(int layerIndex) {
    	return gridLayers.get(layerIndex);
    }

    /**
     * Convert screen coordinates into tile coordinate space. May change if
     * panning is offered.
     * @param x
     * @param y
     * @return 
     */
	public static int[] worldToTileCoordinates(float x, float y) {
		// TODO This is probably naively constructed.
		// Also, not very flexible :(
		int[] tile = new int[2];
		tile[0] = (int) x/32;
		tile[1] = (int) y/32;
		return tile;
	}
}
