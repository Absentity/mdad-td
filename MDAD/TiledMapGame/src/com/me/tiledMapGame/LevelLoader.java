package com.me.tiledMapGame;
/**
 * Opens a text file containing save information from a previous game and restores that game.
 * 
 * @author Brandon
 *
 */
public class LevelLoader {
	private Level l;
	private String checkSum; //Used to make sure that the save file is not tampered with.
	
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
		char first;
		char fourth;
		int digits1;
		int digits2;
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
