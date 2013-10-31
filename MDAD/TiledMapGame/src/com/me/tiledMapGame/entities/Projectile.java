/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Bret
 *
 */
public class Projectile extends MobileEntity {
	
	private Vector2 direction; // TODO not needed. see directional code in update
	                           // Bret: Unless the target is a point and not following a target, right?
	private Sprite target = new Sprite();
	private float speed = 1.5f; // TODO: adjust this to make animation look right
	private float distance = 0;
	private float delX = 0, delY = 0, angle = 0;

	public Projectile(Texture texture, /*Sprite target,*/ Vector2 direction) {
	/* public Projectile(Texture texture, /*Sprite target, int x, int y) {*/
		super(texture, 0, direction.len());
		this.direction = direction;
//		target.setX(200); // FOR TESTING
//		target.setY(200); // FOR TESTING
//		this.target = target;
//		this.direction = direction;
	}
	
	public void update(float delta) {
		// Ignore path layer and follow direct projectile vector
		rotate(-15); // TODO Investigate: possibly more costly than sprite animation frames
		
		delX = getX() - target.getX();
		delY = getY() - target.getY();
		angle = (float)(Math.atan2(delY, delX)*(180/Math.PI));
		
		setPosition((float)(getX() + speed*Math.cos(angle)),(float)(getY() + speed*Math.sin(angle)));

		distance = (float)Math.hypot(getX()-target.getX(), getY()-target.getY()); // can be used later to determine if in range to deal damage
		
	}
	
	public Sprite getTarget(){
		return target;
	}
	
	// each projectile will be given a target and will not switch so this isn't needed
//	public void setTarget(Sprite target){
//		this.target = target;
//	}
	
	public float getDistance(){
		return distance;
	}
	
	public void setDistance(float distance){
		this.distance = distance;
	}
}
