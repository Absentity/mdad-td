/*
 * Copyright 2013 by some group of people
 */
package com.me.tiledMapGame.pathing;

import java.util.ArrayList;
import java.util.List;

import com.me.tiledMapGame.entities.Entity;

/**
 *
 * @author brandon
 */
/**
 * Contains multiple {@link GridLayer}s and a list of all entities.
 */
public class ObjectGrid {
    public static List<GridLayer> gridLayers;
    private int length;
    private int width;
    private List<Entity> entities;
    
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
    
    public List<Entity> entityList() {
    	return entities;
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
    
//    /**
//     * Creates a new Tower object at location (x,y) with the properties
//     * of the Tower described by Tower towerIndex[ti].
//     * <p>
//     * Marks element GridLayer.grid[y][x].is_passable as false for GridLayers[li];
//     * if "allLayers" is true, then "is_passable" will be marked for all layers
//     * in GridLayers.
//     * <p>
//     * GridLayers[0] is always marked (it is the main layer)
//     * 
//     * @return true for successfully built, and false for not
//     * @param ti identifies which tower should be built
//     * @param li identifies which layer should be marked
//     * @param x 
//     * @param y
//     * @param allLayers indicates that all layers in GridLayers should be marked
//     */
//    public boolean createTower(int ti,int li, int x, int y, boolean allLayers) {
//    	//check if the main grid (GridLayers[0] is empty)
//    	if(gridLayers.get(0).getGrid()[y][x].is_passable){
//	    	//Create the new Tower
//	    	//... TODO
//	    	
//	    	//Add it to the towerList
//	    	//.... TODO
//	    	
//	    	//Mark the appropriate Nodes with is_passable = false;
//	    	if (!allLayers) {
//	    		gridLayers.get(0).getGrid()[y][x].is_passable = false;
//	    		gridLayers.get(li).getGrid()[y][x].is_passable = false;
//	    	}
//	    	else {
//	    		for (GridLayer g: gridLayers) {
//	    			g.getGrid()[y][x].is_passable = false;
//	    		}
//	    	}
//	    	
//	    	return true;
//    	}
//    	return false;
//    }
//    
//    /**
//     * Create a new unit specified by ui (unit_index) at location (x,y); assumes player 
//     * resources have already been checked and will be augmented elsewhere.
//     * 
//     * @param ui indicates what unit should be created
//     * @param x
//     * @param y
//     */
//    public boolean createUnit(int ui, int x, int y){
//    	//Create the new Unit
//    	//... TODO
//    	
//    	//Add it to the unitList
//    	//... TODO
//    	
//    	return true;
//    }
//    
//    /**
//     * Create a new Enemy specified by ei (enemy_index) at location (x,y).
//     * 
//     * @param ei indicates what enemy should be created
//     */
//    public boolean spawnEnemy(int ei, int x, int y){
//    	//Create the new Enemy
//    	//... TODO
//    	
//    	//Add it to the enemyList
//    	//... TODO
//    	return false;
//    }
//    
//    /**
//     * Removes the tower located at (x,y) from the {@link ObjectGrid}'s entity list.
//     * 
//     */
//    public void removeTower(int li, int x, int y){
//    	//Find the Tower at (x,y) and remove it from towerList
//    	
//    	
//    	//Mark "is_passable" = true for (x,y) in GridLayers[li]
//    	//... TODO
//    }
}
