package com.me.tiledMapGame;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.pathing.ObjectGrid;

public class levelSaver {
	
	private String checkSum;
	private FileWriter fw;
	
	public levelSaver(String saveFile){
		checkSum = "";
		try {
			fw = new FileWriter(new File(saveFile));
		} catch (Exception e) {
			fw = null;
		}
	}
	
	public boolean saveLevel(Level l){
		//Prepare Save File
		if(fw == null){
			return false;
		}
		
		//Begin saving processing
		checkSum += (char) ('A' + l.levelId - 1);		//Begin calculating checkSum
		try {
			fw.write(l.levelId + "\n");
			fw.write(l.getGold() + "\n");
			fw.write(l.getWave() + "\n");
			fw.write(ObjectGrid.towers.size()+ "\n");
			checkSum += ObjectGrid.towers.size() % 100;
			for(Tower t: ObjectGrid.towers){			//Save info for each tower
				fw.write(t.getX() + " ");
				fw.write(t.getY() + " ");
				fw.write(t.getTowerType() + "\n");
			}
			fw.write(checkSum);
			//TODO
			
			fw.close();
			
		} catch (IOException e) {
			return false;
		}
		
		return false;
	}

}