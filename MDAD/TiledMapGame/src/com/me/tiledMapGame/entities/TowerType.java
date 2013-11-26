package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * This class defines a tower's basics
 */
public class TowerType implements EntityDefinition {
	
	final String name;
	final Texture texture;
	final int towerType;
	
	final ProjectileType projectileType;
	final float sightRange;
	float fireRate;
	final int projectileDamage;	
	final int health;
	
	final int price;
	
	public TowerType(final String name,
			final Texture texture,
			final int health,
			final int price,
			final float sightRange,
			final ProjectileType projectileType,
			final float fireRate,
			final int projectileDamage,
			final int tower) {
		this.name = name;
		this.texture = texture;
		this.health = health;
		this.sightRange = sightRange;
		this.projectileType = projectileType;
		this.towerType = tower;
		this.fireRate = fireRate;
		this.projectileDamage = projectileDamage;
		this.price = price;
	}
	
	public Tower createInstance() {
		return new Tower(this);
	}
	
	public float getFireRate() {
		return fireRate;
	}
	
	public void setFireRate(float fireRate) {
		this.fireRate = fireRate;
	}
}
