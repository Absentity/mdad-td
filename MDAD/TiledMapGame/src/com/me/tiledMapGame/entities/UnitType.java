package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

public class UnitType {
	
	Texture texture;
	int health;
	float maxVelocity;
	
	public UnitType(Texture texture, int health, float maxVelocity, int unit) {
		this.texture = texture;
		this.health = health;
		this.maxVelocity = maxVelocity;
	}
}
