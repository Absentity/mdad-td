/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.pathing.GridLayer;
import com.me.tiledMapGame.pathing.Node;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.PathFinder;

/**
 * Mobile Entities add autonomous movement based on their current velocity.
 * Extend Mobile Entities when you want your Entity to move on its own logic.
 * This requires updating its current velocity on update() calls. (Planned)
 */
public abstract class MobileEntity extends Entity {
	
	public static final float FRICTION = 0.7f;

	protected Vector2 velocity;	
	float maxSpeed;
	
	public MobileEntity(final String name, final Texture texture, int health, final int price, final float maxVelocity){
		super(name, texture, health, price);
		this.maxSpeed = maxVelocity;
	}
	
	// TODO Should this method be in Entity?
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
		super.update(delta);
		
//		Node tile = this.getTile();
		// Update position based on velocity
//		float dX = (1/delta) * tile.dir.x + velocity.x - FRICTION;
//		float dY = (1/delta) * tile.dir.y + velocity.y - FRICTION;
//		Vector2 dir = new Vector2(dX,dY).limit(maxVelocity);
//		this.setX()
		
//		// Requires Entity.java's getTile() method to work!
//		float toMoveX = getTile().dir.x * maxVelocity;
//		float toMoveY = getTile().dir.y * maxVelocity;
//		
//		setPosition(getX() + toMoveX, getY() + toMoveY);
	}
	
//	protected abstract void updateVelocity();
	
	public void dispose() {
		super.dispose();
	}
}
