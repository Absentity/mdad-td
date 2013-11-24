package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

public class UnitType implements EntityDefinition {
	
	final Texture texture;
	final ProjectileType projectileType;
	final float maxSpeed;
	final float sightRange;
	final float attackRate;
	final int attackStrength;
	final int health;
	final int id;
	
	public UnitType(final Texture texture,
			final int health,
			final float maxSpeed,
			final float sightRange,
			final ProjectileType projectileType,
			final float attackRate,
			final int attackStrength,
			final int unit) {
		this.texture = texture;
		this.health = health;
		this.maxSpeed = maxSpeed;
		this.sightRange = sightRange;
		this.projectileType = projectileType;
		this.attackRate = attackRate;
		this.attackStrength = attackStrength;
		this.id = unit;
	}

	@Override
	public Unit createInstance() {
		return new Unit(this);
	}
	
	public int getId(){
		return this.id;
	}
	
}
