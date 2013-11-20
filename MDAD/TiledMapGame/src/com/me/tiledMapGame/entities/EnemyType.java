package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * This class allows us to define types of enemies and their basics
 */
public class EnemyType {
	
	private static final int DEFAULT_ATTACK_STRENGTH = 10;
	private static final int DEFAULT_GOLD_REWARD = 10;
	private static final int DEFAULT_XP_REWARD = 10;
	
	Texture texture;
	int health;
	int attackStrength;
	int goldReward, xpReward;
	float maxVelocity;
	
	public EnemyType(Texture texture, int health, float maxVelocity/*, int goldReward, int xpReward*/) {
		this.texture = texture;
		this.health = health;
		this.maxVelocity = maxVelocity;
		this.attackStrength = DEFAULT_ATTACK_STRENGTH;
		this.goldReward = DEFAULT_GOLD_REWARD;
		this.xpReward = DEFAULT_XP_REWARD;
	}
}
