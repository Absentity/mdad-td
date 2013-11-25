package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * This class allows us to define types of enemies and their basics
 */
public class EnemyType implements EntityDefinition {
	
	final String name;
	final Texture texture;
	final int health;
	final int price;
	final int attackStrength;
	final float attackRate;
	final int goldReward;
	final int xpReward;
	final float maxVelocity;
	
	public EnemyType(final String name,
			final Texture texture,
			final int health,
			final float maxVelocity,
			final int attackStrength,
			final float attackRate,
			final int goldReward,
			final int xpReward) {
		this.name = name;
		this.texture = texture;
		this.health = health;
		this.price = 0;
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
