package com.me.tiledMapGame.entities;


public class Enemy extends MobileEntity {
	
	private EnemyType enemy;
	
	public Enemy(EnemyType enemy) {
		super(enemy.texture, enemy.health, enemy.maxVelocity);
		this.enemy = enemy;
	}

	@Override
	public void dispose() {
		super.dispose();
		// TODO Reward player with gold and XP
//		Game.gold += enemy.goldReward;
//		Game.xp += enemy.xpReward;
	}

}
