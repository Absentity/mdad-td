/**
 * 
 */
package com.me.tiledMapGame.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.me.tiledMapGame.pathing.ObjectGrid;

/**
 * This is an instance of a Tower object. When the player places a tower
 * on the map, the game creates a new instance on that cell.
 * 
 * NOTE: Tower sprites MUST have 12 frames in the sprite sheet. (currently)
 */
public class Tower extends Entity {

	public final int PORTAL = 0;
	public final int CRESENT = 1;
	public final int BOMB = 2;
	public final int AMPLIFIER = 3;
	public final int FIREBALL = 4;
	
	// DEFAULT
	public int towerType = 1;
	
	private boolean moved = false;
	
	private TowerType tower;
	private Circle range;
	private float cooldown;
	private boolean placed = false;
	private float alpha = .65f; // For drawing towers transparent before being placed. (.65 for transparent, 1 for opaque)
	
	public Tower(TowerType tower) {
		super(tower.texture, tower.health);
		this.tower = tower;
		towerType = tower.towerType;
		/* Since a tower is created before it's set in the ground,
		   we have to pretend it has no range at first and edit it
		   later. */
		range = new Circle(-1000, -1000, tower.sightRange);
		if(tower.towerType == 0){
			alpha = 1;
		}
		cooldown = tower.fireRate;
	}
	
	/**
	 * Fire at enemies every so often. We use a cooldown mechanism here.
	 * We want the tower to fire as soon as it's ready, not on some global
	 * beat.
	 */
	public void update(float delta) {
		super.update(delta);
		
		// Non-projectile towers such as Amplifier don't attack!
		if (tower.fireRate != 0f) {
			cooldown -= delta;
			if (cooldown <= 0) {
				// The enemy detection method can be easily changed
				Enemy enemyInRange = detectFirstEnemy();
				
				// Fire!!
				if (enemyInRange != null) {
					createProjectiles(enemyInRange);
					cooldown = tower.fireRate;
				}
			}
		}
	}

	/**
	 * TODO: Does this fire at enemies? Or is something else controlling
	 * when the projectiles come out?
	 * @param target
	 */
	public void createProjectiles(Enemy target) {
		Projectile p = Projectile.fireAt(new ProjectileType(new Texture("img/possibleCresent.png"), 5), this, target);
		p.scale(-.5f);
		p.setPosition(getX(), getY());
		p.setDamage(tower.projectileDamage);
		ObjectGrid.projectiles.add(p);
	}

	/**
	 * Seek out any enemies in the tower's range and return the first one it finds.
	 * @return
	 */
	private Enemy detectFirstEnemy() {
		for (Enemy e : ObjectGrid.enemyList()) {
			if (Intersector.overlaps(range, e.getBoundingRectangle())) {
				return e;
			}
		}
		return null;
	}
	
	@Deprecated
	public float getStateTime(){
		return super.getStatetime();
	}

	@Deprecated
	public TextureRegion getCurrentFrame(){
		return super.getCurrentFrame();
	}

	@Override
	public void dispose() {
		// TODO Create nifty explosion? ~LOWPRIORITY
	}
	
	public boolean isPlaced(){
		return placed;
	}
	
	public void setPlaced(boolean placed){
		this.placed = placed;
		if (placed) {
			range = new Circle(getX(), getY(), tower.sightRange);
		}
	}
	
	public float getAlpha(){
		return alpha;
	}
	
	public void setAlpha(float alpha){
		this.alpha = alpha;
	}
	
	public boolean getMoved(){
		return moved;
	}
	
	public void setMoved(boolean moved){
		this.moved = moved;
	}
	
	public int getTowerType(){
		return this.tower.towerType;
	}

	/**
	 * 
	 * @return
	 */
	public float getRange() {
		return range.radius;
	}
}
