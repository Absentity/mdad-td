package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * This class defines a tower's basics
 */
public class TowerType {
	
	Texture texture;
	int health;
	float sightRange;
	float projectileCooldown;
	int projectileDamage;
	int towerType;
	
	public TowerType(Texture texture, int health, float sightRange, int tower) {
		this.texture = texture;
		this.health = health;
		this.sightRange = sightRange;
		this.towerType = tower;
	}
}
