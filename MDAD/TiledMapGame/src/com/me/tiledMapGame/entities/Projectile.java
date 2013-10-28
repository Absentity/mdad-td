/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Bret
 *
 */
public class Projectile extends MobileEntity {
	
	private Vector2 direction;

	public Projectile(Sprite sprite, Vector2 direction) {
		super(sprite);
		this.direction = direction;
		// TODO Auto-generated constructor stub
	}
	
	public void update(float delta) {
		// Ignore path layer and follow direct projectile vector
	}
}
