package com.me.tiledMapGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.badlogic.gdx.graphics.Texture;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.entities.TowerType;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.PathFinder;

/**
 * Opens a text file containing save information from a previous game and restores that game.
 * 
 * @author Brandon
 *
 */
public class LevelLoader {
	private Level l;
	private String checkSum; //Used to make sure that the save file is not tampered with.
	private Scanner sc;
	private int n;
	
	public LevelLoader(){
		l = null;
	}
	
	/**
	 * Open text file named in 'loadFile' and create a level based on the information read.
	 * Checks if the 'checkSum' is valid, if not, the level will not be loaded.
	 * 
	 * @param loadFile Name of file to be loaded in form 'name.txt'.
	 * @return True if succesful; false if the level is not found or the 'checkSum' is not valid.
	 */
	public boolean loadLevel(String loadFile){
		try {
			sc = new Scanner(new File(loadFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Begin Reading File*/
		l.levelId = sc.nextInt();
		l.setGold(sc.nextInt());
		l.setWave(sc.nextInt());
		n = sc.nextInt();
		
		/*Set each tower (skip the 1st one -- the castle)*/
		for(int i = 0; i < n; ++i){
			int  x = sc.nextInt();
			int y = sc.nextInt();
			int t = sc.nextInt();
			
			switch(t){
    		case 1:
    			ObjectGrid.towers.add(new Tower(new TowerType(new Texture("img/cresentTower.png"), 100, 70f, 1)));
    			break;
    		case 2:
    			ObjectGrid.towers.add(new Tower(new TowerType(new Texture("img/bombTower.png"), 100, 70f, 2)));
    			break;
    		case 3:
    			ObjectGrid.towers.add(new Tower(new TowerType(new Texture("img/amplifyTower.png"), 100, 70f, 3)));
    			break;
    		case 4:
    			ObjectGrid.towers.add(new Tower(new TowerType(new Texture("img/fireballTower.png"), 100, 70f, 4)));
    		default:
    			break;
			}
			l.getNode(x/32, 15-(y)/32).markTower();//getNode(screenX/32,16-(screenY/32)).markTower();
			System.out.println(x/32 + " " + (16-(y/32)));
			l.getObjectGrid().clearAllVisited();
			PathFinder.find_path(ObjectGrid.gridLayer(0).getGrid(), 10, 10);
			
			List<Tower> towers = ObjectGrid.towers;
			towers.get(towers.size()-1).setPosition(x, TiledMapGame.screenHeight - y); // FOR TESTING
			towers.get(towers.size()-1).setAlpha(.65f); // FOR TESTING
			towers.get(towers.size()-1).setMoved(true);
			towers.get(towers.size()-1).setAlpha(1);
			towers.get(towers.size()-1).setPlaced(true);
		}
		
		/*Validate Checksum*/
		//TODO
	
		return false;
	}
	/**
	 * Retrieves the level created by 'loadLevel()'. Assumes 'loadLevel()' returned true and was previously ran.
	 * @return The level created by 'loadLevel()'.
	 */
	public Level getLevel(){
		return l;
	}

}
