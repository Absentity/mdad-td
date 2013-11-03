/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.pathing.GridLayer;

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

	public void update(float delta) {
		// Update position based on velocity
		float x = this.getX();
		float y = this.getY();
//		Vector2 dir = getTile().dir;
//		this.setX()
	}
}
