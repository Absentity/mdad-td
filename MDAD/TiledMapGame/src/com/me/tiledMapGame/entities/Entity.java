/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.tiledMapGame.pathing.Node;

/**
 * @author Bret
 *
 */
public abstract class Entity extends Sprite {

	protected int health;
	protected double statetime;
	
	public Entity(Texture texture, int health) {
		super(texture);
		this.health = health;
	}

	public Node getTile() {
		// TODO: Implement me! D:
		return null;
	}
	
	public void dispose() {
		// TODO: Subclasses such as Tower could create some explosion effects on dispose!
	}
}
