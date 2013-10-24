/*
 * Copyright 2013 by some group of people
 */
package com.me.tiledMapGame.pathing;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Timer;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.TowerAttackTask;

/**
 *
 * @author brandon
 */
/******************************************************************************
 * ObjectGrid
 * ----------
 * Description:
 * Holds multiple GridLayers (see below); 
 * 	Holds all game objects (Units, Enemies and Towers), and is used to interact
 * 	with those objects.
 *****************************************************************************/
public class ObjectGrid {
    public int length;
    public int width;
    public ArrayList<GridLayer> GridLayers;
    public ArrayList<Enemy> EnemyList;
    public ArrayList<Unit> UnitList;
    public ArrayList<Tower> TowerList;
    public Timer ActionTimer;
    public Timer.Task task;
    
    public ObjectGrid(int length, int width){
    	this.length = length;
    	this.width = width;
    	GridLayers = new ArrayList<GridLayer>();
    	GridLayers.add(new GridLayer(length, width));
    	ActionTimer = new Timer();
    	task = new TowerAttackTask("Abba"); // TODO Testing
    }
    
    /*
     * update()				Return: void
     * ---------------------------------
     * Description: Call clearVisited for each GridLayer in GridLayers; call
     * 	"update()" for all Towers, Units, and Enemies.
     */
    public void update(){
    	for(int i = 0; i < GridLayers.size(); ++i){
    		this.clearVisited(i);
    	}
    	//Call other update() functions
    	//... TODO
    	ActionTimer.scheduleTask(task, .5f, 3.0f);
    }
    
    /******************************************************************************
     * addGridLayer()		Return: void
     * ---------------------------------
     * Description: Creates a new initialized (but empty) GridLayer and adds it
     * 	to the end of ArrayList GridLayers.
     ******************************************************************************/
    public void addGridLayer(){
    	GridLayers.add(new GridLayer(length, width));
    }
    
    /****************************************************************************
     * clearVisited()	Return: Void
     * -----------------------------
     * Description:
     * Marks all nodes in the grid as unvisited for the layer indicated by li;
     * 	this MUST be called before running PathFinder.find_path() again.
     ****************************************************************************/
    public void clearVisited(int li){
    	for(int i = 0; i < length; ++i){
    		for(int j = 0; j < width; ++j){
    			GridLayers.get(li).grid[i][j].visited = false;
    		}
    	}
    }
    
    /*****************************************************************************
     * createTower()		Return: true if succesful, false if tower can't be placed
     * ---------------------------------
     * Description: creates a new Tower object at location (x,y) with the properties
     * 	of the Tower described by Tower towerIndex[ti].
     * Marks element GridLayer.grid[y][x].is_passable as false for GridLayers[li];
     * 	if "allLayers" is true, then "is_passable" will be marked for all layers
     * 	in GridLayers.
     * GridLayers[0] is always marked (it is the main layer)
     * @return true for successfully built, and false for not
     *****************************************************************************/
    public boolean createTower(int ti,int li, int x, int y, boolean allLayers){
    	//check if the main grid (GridLayers[0] is empty)
    	if(GridLayers.get(0).grid[y][x].is_passable){
	    	//Create the new Tower
	    	//... TODO
	    	
	    	//Add it to the towerList
	    	//.... TODO
	    	
	    	//Mark the appropriate Nodes with is_passable = false;
	    	if(!allLayers){
	    		GridLayers.get(0).grid[y][x].is_passable = false;
	    		GridLayers.get(li).grid[y][x].is_passable = false;
	    	}
	    	else{
	    		for(GridLayer g: GridLayers){
	    			g.grid[y][x].is_passable = false;
	    		}
	    	}
	    	
	    	//Begin the Tower's attack timer
	    	ActionTimer.scheduleTask(task, .5f, 3f);
	    	
	    	return true;
    	}
    	return false;
    }
    
    /****************************************************************************
     * createUnit()		Return: True if Unit Created, False if Unit isn't
     * -----------------------------
     * Description:
     * Create a new unit specified by ui (unit_index) at location (x,y); assumes player 
     * 	resources have already been checked and will be augmented elsewhere.
     ****************************************************************************/
    public boolean createUnit(int ui, int x, int y){
    	//Create the new Unit
    	//... TODO
    	
    	//Add it to the unitList
    	//... TODO
    	
    	return true;
    }
    
    /****************************************************************************
     * spawnEnemy()		Return: True if Enemy is created, false if it isn't
     * -----------------------------
     * Description:
     * Create a new Enemy specified by ei (enemy_index) at location (x,y).
     ****************************************************************************/
    public boolean spawnEnemy(int ei, int x, int y){
    	//Create the new Enemy
    	//... TODO
    	
    	//Add it to the enemyList
    	//... TODO
    	return false;
    }
    
    /****************************************************************************
     * removeTower()		Return: void
     * ---------------------------------
     * 
     ****************************************************************************/
    public void removeTower(int li, int x, int y){
    	//Find the Tower at (x,y) and remove it from towerList
    	
    	
    	//Mark "is_passable" = true for (x,y) in GridLayers[li]
    	//... TODO
    }
    
    /*****************************************************************************
     * remove
     *****************************************************************************/
}


class Tower{		//Place holder class
	
}
class Unit{			//Place holder class
	
}
