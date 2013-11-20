package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * This class defines a tower's basics
 */
public class TowerType {
	
	private static final float DEFAULT_COOLDOWN = 0.25f;
	
	Texture texture;
	
	float sightRange;
	float fireRate;
	
	int health;
	int projectileDamage;
	int towerType;
	
	public TowerType(Texture texture, int health, float sightRange, int tower) {
		this.texture = texture;
		this.health = health;
		this.sightRange = sightRange;
		this.towerType = tower;
		this.fireRate = DEFAULT_COOLDOWN;
	}
}
