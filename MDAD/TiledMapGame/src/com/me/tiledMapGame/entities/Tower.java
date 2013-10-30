/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * This is an instance of a Tower object. When the player places a tower
 * on the map, the game creates a new instance on that cell.
 */
public class Tower extends Entity {
	
	private TowerType tower;
	
	public Tower(TowerType tower) {
		super(tower.texture, tower.health);
		this.tower = tower;
	}

	public void createProjectiles() {
		
	}
}
