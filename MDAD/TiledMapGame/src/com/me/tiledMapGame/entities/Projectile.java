/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Bret
 *
 */
public class Projectile extends MobileEntity {
	
	private Vector2 direction;

	public Projectile(Texture texture, Vector2 direction) {
		super(texture, 0, direction.len());
		this.direction = direction;
		// TODO Auto-generated constructor stub
	}
	
	public void update(float delta) {
		// Ignore path layer and follow direct projectile vector
	}
}
