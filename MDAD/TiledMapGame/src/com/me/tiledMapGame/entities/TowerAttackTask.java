package com.me.tiledMapGame.entities;

import com.badlogic.gdx.utils.Timer;

public class TowerAttackTask extends Timer.Task{
	private String towerName = "";

	public TowerAttackTask(String name){
		towerName = name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Tower " + towerName + " is attacking!");
		
	}

}
