package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

public class UnitType implements EntityDefinition {
	
	final String name;
	final Texture texture;
	final ProjectileType projectileType;
	final float maxSpeed;
	final float sightRange;
	final float attackRate;
	final int attackStrength;
	final int health;
	final int price;
	final int id;
	
	public UnitType(final String name,
			final Texture texture,
			final int health,
			final int price,
			final float maxSpeed,
			final float sightRange,
			final ProjectileType projectileType,
			final float attackRate,
			final int attackStrength,
			final int unit) {
		this.name = name;
		this.texture = texture;
		this.health = health;
		this.price = price;
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
