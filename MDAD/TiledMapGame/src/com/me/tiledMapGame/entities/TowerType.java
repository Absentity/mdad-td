package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * This class defines a tower's basics
 */
public class TowerType {
	
	private static final float DEFAULT_COOLDOWN = 0.6f;
	private static final int DEFAULT_DAMAGE = 50;
	
	Texture texture;
	int towerType;
	
	ProjectileType projectileType;
	float sightRange, fireRate;
	int projectileDamage;	
	int health;
	
	@Deprecated
	public TowerType(Texture texture, int health, float sightRange, int tower) {
		this.texture = texture;
		this.health = health;
		this.sightRange = sightRange;
		this.towerType = tower;
		this.fireRate = DEFAULT_COOLDOWN;
		this.projectileDamage = DEFAULT_DAMAGE;
	}
	
	public TowerType(Texture texture, int health, float sightRange, ProjectileType projectileType,
			float fireRate, int projectileDamage, int tower) {
		this.texture = texture;
		this.health = health;
		this.sightRange = sightRange;
		this.projectileType = projectileType;
		this.towerType = tower;
		this.fireRate = fireRate;
		this.projectileDamage = projectileDamage;
	}
	
	public Tower createInstance() {
		return new Tower(this);
	}
}
