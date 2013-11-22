package com.me.tiledMapGame.entities;

import com.me.tiledMapGame.Level;
import com.me.tiledMapGame.pathing.ObjectGrid;


public class Enemy extends MobileEntity {
	
	private EnemyType enemy;
	private float attackRate;
	
	public Enemy(EnemyType enemy) {
		super(enemy.texture, enemy.health, enemy.maxVelocity);
		this.enemy = enemy;
	}
	
	public void update(float delta) {
		super.update(delta);
		
		attackRate += delta;
		// If enemy has reached destination, attack.
		if (attackRate >= enemy.attackRate) {
			for (Tower t : ObjectGrid.towerList()) {
				if (this.getBoundingRectangle().overlaps(t.getBoundingRectangle())) {
					t.hurt(enemy.attackStrength);
					attackRate = 0;
				}
			}
		}
		
		// Requires Entity.java's getTile() method to work!
		float toMoveX = getTile().dir.x * maxVelocity;
		float toMoveY = getTile().dir.y * maxVelocity;
		
		setPosition(getX() + toMoveX, getY() + toMoveY);
	}

	@Override
	public void dispose() {
		super.dispose();
		Level.gainResource("Gold", enemy.goldReward);
		Level.gainResource("XP", enemy.xpReward);
	}

}
