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
	
	public Node getTile() {
		// TODO: Implement me! D:
		return null;
	}
}
