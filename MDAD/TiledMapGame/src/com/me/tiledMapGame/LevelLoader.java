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
	private static Level l;
	private static String checkSum; //Used to make sure that the save file is not tampered with.
	private static Scanner sc;
	private static int n;
	
	private LevelLoader(){
		l = null;
	}
	
	/**
	 * Open text file named in 'loadFile' and create a level based on the information read.
	 * Checks if the 'checkSum' is valid, if not, the level will not be loaded.
	 * 
	 * @param loadFile Name of file to be loaded in form 'name.txt'.
	 * @return True if succesful; false if the level is not found or the 'checkSum' is not valid.
	 */
	public static boolean loadLevel(String loadFile){
		l = new Level("MDADMap1v1");
		try {
			sc = new Scanner(new File(loadFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*Begin Reading File*/
		l.levelId = sc.nextInt();
		l.setResource("Gold", sc.nextInt());
		l.setWave(sc.nextInt());
		n = sc.nextInt();
		
		/*Set each tower*/
		for(int i = 0; i < n; ++i){
			int x = (int) sc.nextFloat();
			int y = (int) sc.nextFloat();
			int t = sc.nextInt();
			
			switch(t){
			case 0:
    			ObjectGrid.towers.add(TiledMapGame.towerTypeLibrary.get("Portal").createInstance());
				break;
    		case 1:
    			ObjectGrid.towers.add(TiledMapGame.towerTypeLibrary.get("Crescent").createInstance());
    			break;
    		case 2:
    			ObjectGrid.towers.add(TiledMapGame.towerTypeLibrary.get("Bomb").createInstance());
    			break;
    		case 3:
    			ObjectGrid.towers.add(TiledMapGame.towerTypeLibrary.get("Amplify").createInstance());
    			break;
    		case 4:
    			ObjectGrid.towers.add(TiledMapGame.towerTypeLibrary.get("Fireball").createInstance());
    		default:
    			break;
			}
			l.getNode((int)x/32, ((int)y)/32).markTower();
			l.getObjectGrid().clearAllVisited();
			PathFinder.find_path(ObjectGrid.gridLayer(0).getGrid(), 10, 10);
			
			List<Tower> towers = ObjectGrid.towers;
			towers.get(towers.size()-1).setPosition(x, y); // FOR TESTING
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
	public static Level getLevel(){
		return l;
	}

}
