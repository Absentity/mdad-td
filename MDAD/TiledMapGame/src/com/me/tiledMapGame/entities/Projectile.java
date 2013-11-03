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
								// Reggie: We would just set target.x to the point's x and target.y to the point's y, see the comment block below

	/*
	 * For Multidirectional attacks, each projectile fired can be given a target
	 * that is at the edge of the max range if whatever direction. For example,
	 * if projectiles should be fired in four directions, let the X's represent 
	 * each projectile's target. Imagine a circle drawn through the X's to represent
	 * the tower T's max radius.
	 * 
	 *  _______________________
	 *  |          X2          |
	 *  |                      |
	 *  |     X3   T    X1     |
	 *  |                      |
	 *  |__________X4__________|
	 *  
	 * The projectile going towards X1 will be given a target directly to the left,
	 * the one going towards X2 can be given a target directly upward, etc. Damage 
	 * can be determined when the projectile comes within so many pixels of any enemy
	 * in range. To make sure the enemies aren't too fast for the tower to hit them,
	 * the tower should begin fired as soon as an enemy is within range.
	 * 
	 */
	
	private Sprite target = new Sprite();
	private float speed = 1.5f; // TODO: adjust for balance
	private float distanceToTarget = 9000;
	private float delX = 0, delY = 0, angle = 0;

	public Projectile(Texture texture, /*Sprite target,*/ Vector2 direction) {
		super(texture, 0, direction.len());
//		this.direction = direction;
	}
	
	public Projectile(Sprite sprite, Sprite target, Vector2 direction) {
		super(sprite.getTexture(), 1000000, direction.len());
	}
	
	// TEST CONSTRUCTOR - REMOVE LATER
	public Projectile(Sprite sprite, int x, int y) {
		super(sprite.getTexture(), 1000000, (float)Math.hypot(sprite.getX()-x, sprite.getY()-y));
		target.setPosition(x, y);

	/* public Projectile(Texture texture, /*Sprite target, int x, int y) {*/
//		super(texture, 0, direction.len());
//		this.direction = direction;
//		target.setX(200); // FOR TESTING
//		target.setY(200); // FOR TESTING
//		this.target = target;
//		this.direction = direction;
	}
	
	public void update(float delta) {
		// Ignore path layer and follow direct projectile vector
		rotate(-15); // TODO Investigate: possibly more costly than sprite animation frames
		
		calculateAngle(target);
		setPosition((float)(getX() + speed*Math.cos(angle)),(float)(getY() + speed*Math.sin(angle)));
		
		distanceToTarget = (float)Math.hypot(getX()-target.getX(), getY()-target.getY()); // can be used later to determine if in range to deal damage
	}
	
	public Sprite getTarget(){
		return target;
	}
		
	/**
	 * The distance to the target, in pixels
	 * @return Distance to the target
	 */
	public float getDistance(){
		return distanceToTarget;
	}
	
	/**
	 * Finds the difference in x and y distance from the
	 * attacker to its target then calculates theta of
	 * (r,theta) in polar coordinates.
	 * @param target The attacker's target
	 */
	public void calculateAngle(Sprite target){
		delX = getX() - target.getX();
		delY = getY() - target.getY();
		this.angle = (float)(Math.atan2(delY, delX)*(180/Math.PI));
	}
}
