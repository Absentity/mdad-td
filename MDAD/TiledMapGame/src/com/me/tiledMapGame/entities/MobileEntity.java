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

	public static final float FRICTION = 0.7f;
	
	float maxVelocity;
	protected Vector2 velocity;
	protected GridLayer pathLayer;
	
	public MobileEntity(Texture texture, int health, float maxVelocity) {
		super(texture, health);
		this.maxVelocity = maxVelocity;
	}
	
	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		spriteBatch.begin();
		super.draw(spriteBatch);
		spriteBatch.end();
	}

	/**
	 * Update the position of the mobile entity, letting them curve
	 * around corners.
	 * @param delta time in milliseconds since last update
	 */
	public void update(float delta) {
		Node tile = this.getTile();
		// Update position based on velocity
		float dX = (1/delta) * tile.dir.x + velocity.x - FRICTION;
		float dY = (1/delta) * tile.dir.y + velocity.y - FRICTION;
		Vector2 dir = new Vector2(dX,dY).limit(maxVelocity);
//		this.setX()
	}
}
