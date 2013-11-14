package com.me.tiledMapGame.entities;


import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.pathing.Node;


public class Enemy extends MobileEntity {
	
	private EnemyType enemy;
	
	public Enemy(EnemyType enemy) {
		super(enemy.texture, enemy.health, enemy.maxVelocity);
		this.enemy = enemy;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public int getHealth() {
		return health;
	}

	public String showHealth() {
		//TODO: Draw bar?
		return "HP: " + Integer.toString(health);
	}

}
