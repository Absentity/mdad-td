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
 * @author Bret
 *
 */
public abstract class MobileEntity extends Entity {

	protected Vector2 velocity;
	
	public static final float FRICTION = 0.7f;
	
	float maxVelocity;
	
	public MobileEntity(Texture texture, int health, float maxVelocity){
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
		super.update(delta);
		
//		Node tile = this.getTile();
		// Update position based on velocity
//		float dX = (1/delta) * tile.dir.x + velocity.x - FRICTION;
//		float dY = (1/delta) * tile.dir.y + velocity.y - FRICTION;
//		Vector2 dir = new Vector2(dX,dY).limit(maxVelocity);
//		this.setX()
		
		// Requires Entity.java's getTile() method to work!
		float toMoveX = getTile().dir.x * maxVelocity;
		float toMoveY = getTile().dir.y * maxVelocity;

		//TODO: if enemy has reached destination, attack.
		
		setPosition(getX() + toMoveX, getY() + toMoveY);
	}
}
