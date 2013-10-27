/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.pathing.GridLayer;

/**
 * @author Bret
 *
 */
public abstract class MobileEntity extends Entity {

	protected Vector2 velocity;
	protected GridLayer pathLayer;
	
	public MobileEntity(Sprite sprite) {
		super(sprite);
	}
	
	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}

	protected void update(float delta) {
		// Update position based on velocity
		float x = this.getX();
		float y = this.getY();
//		Vector2 dir = getTile().dir;
//		this.setX()
	}
}
