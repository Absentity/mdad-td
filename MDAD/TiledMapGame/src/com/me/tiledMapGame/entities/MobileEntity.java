/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.pathing.GridLayer;
import com.me.tiledMapGame.pathing.Node;

/**
 * @author Bret
 *
 */
public abstract class MobileEntity extends Entity {

	float maxVelocity;
	protected Vector2 velocity;
	protected GridLayer pathLayer;
	
	public MobileEntity(Texture texture, int health, float maxVelocity) {
		super(texture, health);
		this.maxVelocity = maxVelocity;
	}
	
	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}

	/**
	 * Update the position of the mobile entity, letting them curve
	 * around corners.
	 * @param delta time in milliseconds since last update
	 */
	public void update(float delta) {
		Node tile = this.getTile();
		// Update position based on velocity
//		float dX = tile.dir;
//		float dY = this.getY();
//		Vector2 dir = getTile().dir;
//		this.setX()
	}
}
