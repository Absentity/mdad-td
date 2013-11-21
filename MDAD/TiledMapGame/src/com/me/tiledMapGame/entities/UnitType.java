package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

public class UnitType implements EntityDefinition {
	
	Texture texture;
	float maxVelocity;
	int health;
	
	public UnitType(Texture texture, int health, float maxVelocity, int unit) {
		this.texture = texture;
		this.health = health;
		this.maxVelocity = maxVelocity;
	}

	@Override
	public Unit createInstance() {
		return new Unit(this);
	}
	
	
}
