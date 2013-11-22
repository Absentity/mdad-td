package com.me.tiledMapGame.entities;

import com.me.tiledMapGame.Level;
import com.me.tiledMapGame.pathing.Node;
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
	
	public Node getTile() {
		int[] tile = ObjectGrid.worldToTileCoordinates(getX(), getY());
		int x = tile[0];
		int y = tile[1];
		
		Node node;
		try {
			node = ObjectGrid.gridLayer(0).getNodeInGrid(x, y);
		} catch (IndexOutOfBoundsException e) {
			// Handle properly? Use logs? At least give more info.
//			e.printStackTrace();
			System.err.println("Off the map!");
			return Node.SENTINEL;
		}
		return node;
	}

	@Override
	public void dispose() {
		super.dispose();
		Level.gainResource("Gold", enemy.goldReward);
		Level.gainResource("XP", enemy.xpReward);
	}

}
