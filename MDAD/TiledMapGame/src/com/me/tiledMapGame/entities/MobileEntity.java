/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Bret
 *
 */
public abstract class MobileEntity extends Entity {

	protected Vector2 maxVelocity;
	protected Vector2 curVelocity;
	protected float acceleration;
	
	protected void update() {
		// Update position based on velocity
		float x = this.getX();
		float y = this.getY();
//		Vector2 dir = getTile().dir;
//		this.setX()
	}
}
