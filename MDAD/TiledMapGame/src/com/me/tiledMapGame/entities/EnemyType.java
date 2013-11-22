package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * This class allows us to define types of enemies and their basics
 */
public class EnemyType implements EntityDefinition {
	
	private static final int DEFAULT_ATTACK_STRENGTH = 10;
	private static final int DEFAULT_GOLD_REWARD = 10;
	private static final int DEFAULT_XP_REWARD = 10;
	
	Texture texture;
	int health;
	int attackStrength;
	float attackRate;
	int goldReward, xpReward;
	float maxVelocity;
	
	public EnemyType(Texture texture, int health, float maxVelocity, int attackStrength, float attackRate, int goldReward, int xpReward) {
		this.texture = texture;
		this.health = health;
		this.maxVelocity = maxVelocity;
		this.attackStrength = attackStrength;
		this.attackRate = attackRate;
		this.goldReward = goldReward;
		this.xpReward = xpReward;
	}

	public Enemy createInstance() {
		return new Enemy(this);
	}
}
