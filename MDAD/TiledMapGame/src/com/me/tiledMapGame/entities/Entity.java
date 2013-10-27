/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.pathing.Node;

/**
 * @author Bret
 *
 */
public abstract class Entity extends Sprite {

	protected int health;
	protected double statetime;
	
	public Entity(Sprite sprite) {
		super(sprite);
	}

	public Node getTile() {
		// TODO: Implement me! D:
		return null;
	}
	
	public void dispose() {
		// TODO: Subclasses such as Tower could create some explosion effects on dispose!
	}
}
