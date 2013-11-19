package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * This class allows us to define types of enemies and their basics
 */
public class EnemyType {
	
	private static final int BASE_ATTACK_STRENGTH = 10;
	
	Texture texture;
	int health;
	int attackStrength;
	float maxVelocity;
	
	public EnemyType(Texture texture, int health, float maxVelocity) {
		this.texture = texture;
		this.health = health;
		this.maxVelocity = maxVelocity;
		this.attackStrength = BASE_ATTACK_STRENGTH;
	}
}
