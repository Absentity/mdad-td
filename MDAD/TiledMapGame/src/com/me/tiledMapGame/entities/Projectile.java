/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.pathing.ObjectGrid;

/**
 * @author Bret
 *
 */
public class Projectile extends MobileEntity {
	
	private Vector2 projection; // TODO not needed. see directional code in update
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

	private ProjectileType projectile;
	private float distanceTravelled;
	private float maxDistance;
	private int damage;

	/**
	 * Use this constructor to create projectiles that don't aim for targets.
	 * For example, a frag bomb would create sub explosions in random directions
	 * coming from the original explosion.
	 * @param projectile Projectile type that defines this projectile
	 * @param projection  Initial velocity
	 */
	public Projectile(ProjectileType projectile, Vector2 projection, float maxDistance, int damage) {
		super(projectile.texture, 1000000, projectile.maxVelocity);
		this.projectile = projectile;
		this.projection = projection;
		this.maxDistance = maxDistance;
		this.damage = damage;
	}
	
	// TODO Change this to public static fireAt(Texture, Entity) ? to return new Projectile
	/**
	 * Fire a projectile at where we expect the target to be by the time the
	 * projectile could hit it. Lots of fancy math here.
	 * @param projectile Projectile type that defines this projectile
	 * @param target     Predict this entity's next location.
	 */
	public Projectile(ProjectileType projectile, Entity target) {
		super(projectile.texture, 1000000, 5);
		// Project
		this.target = target;
		maxDistance = 300f;
	}
	
	/**
	 * Update projectile traveling path
	 */
	public void update(float delta) {
		// Ignore path layer and follow direct projectile vector
		// Has the projectile gone too far??
		if (distanceTravelled >= maxDistance)
			dispose();
		
		// Move
		float dX = getX() + projection.x;
		float dY = getY() + projection.y;
		setPosition(dX, dY);
		distanceTravelled += projection.len();
		
		// Collision with enemy? This may be inflexible if we ever want enemies to shoot
		for (Enemy e : ObjectGrid.enemyList()) {
			if (this.getBoundingRectangle().overlaps(e.getBoundingRectangle())) {
				e.hurt(damage);
			}
		}
		
		// Animation
		rotate(-15); // TODO Investigate: possibly more costly than sprite animation frames
		
		// Tracking. Hold onto this in case of making homing projectiles
//		calculateAngle(target);
//		setPosition((float)(getX() + speed*Math.cos(angle)),(float)(getY() + speed*Math.sin(angle)));
		
//		distanceToTarget = (float)Math.hypot(getX()-target.getX(), getY()-target.getY()); // can be used later to determine if in range to deal damage
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

	@Override
	public void dispose() {
		// Do nothing but stop rendering. Add to projectile pool if we have time to optimize?
		// Hack currently to make it behave properly, but not animate properly
		damage = 0;
	}
}
