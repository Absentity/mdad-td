package com.me.tiledMapGame.entities;

import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.Level;
import com.me.tiledMapGame.pathing.Node;
import com.me.tiledMapGame.pathing.ObjectGrid;


public class Enemy extends MobileEntity {
	
	private EnemyType enemy;
	private float attackRate;
	public int destX;
	public int destY;
	public boolean flying;
	
	public Enemy(EnemyType enemy) {
		super(enemy.name, enemy.texture, enemy.health, enemy.price, enemy.maxVelocity);
		this.enemy = enemy;
		flying = false;
	}
	
	public Enemy(EnemyType enemy, boolean flying) {
		super(enemy.name, enemy.texture, enemy.health, enemy.price, enemy.maxVelocity);
		this.enemy = enemy;
		this.flying = flying;
	}
	
	public void update(float delta) {
		super.update(delta);
		
		attackRate += delta;
		// If enemy has reached destination, attack.
		if (attackRate >= enemy.attackRate) {
			for (Tower t : ObjectGrid.towerList()) {
				if (this.getBoundingRectangle().overlaps(t.getBoundingRectangle()) && (t.isPlaced() || t.towerType == 0)) {
					t.hurt(enemy.attackStrength);
					attackRate = 0;
				}
			}
			for (Unit u : ObjectGrid.unitList()) {
				if (this.getBoundingRectangle().overlaps(u.getBoundingRectangle())){
					u.hurt(enemy.attackStrength);
					attackRate = 0;
				}
			}
		}
		
		// Requires Enemy.java's getTile() method to work!
		float toMoveX;
		float toMoveY;
		if(this.flying){
			Vector2 dest = (new Vector2(destX*32, destY*32)).sub(new Vector2(getX(), getY())).limit(1);
			toMoveX = dest.x * maxSpeed;
			toMoveY = dest.y * maxSpeed;
		} else {
			toMoveX = getTile().dir.x * maxSpeed;
			toMoveY = getTile().dir.y * maxSpeed;
		}
		
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
