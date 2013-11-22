package com.me.tiledMapGame.entities;

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
	}

	@Override
	public void dispose() {
		super.dispose();
		// TODO Reward player with gold and XP
//		Game.gold += enemy.goldReward;
//		Game.xp += enemy.xpReward;
	}

}
