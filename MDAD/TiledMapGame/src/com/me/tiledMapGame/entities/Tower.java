/**
 * 
 */
package com.me.tiledMapGame.entities;

import java.util.ArrayList;
import java.util.List;

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

	@Deprecated
	public final int CRESENT = 1;
	@Deprecated
	public final int BOMB = 2;
	@Deprecated
	public final int AMPLIFIER = 3;
	@Deprecated
	public final int FIREBALL = 4;
	
	// DEFAULT
	@Deprecated
	public int towerType = 1;
	
	private boolean moved = false;
	
	private TowerType tower;
	private Circle range;
	private float cooldown;
	private boolean placed = false;
	private float alpha = .65f; // For drawing towers transparent before being placed. (.65 for transparent, 1 for opaque)
	
	private List<Projectile> magazine = new ArrayList<Projectile>(); 
	
	public Tower(TowerType tower) {
		super(tower.texture, tower.health);
		this.tower = tower;
		range = new Circle(getX(), getY(), tower.sightRange);
		towerType = 1;
	}
	
	/**
	 * Fire at enemies every so often. We use a cooldown mechanism here.
	 * We want the tower to fire as soon as it's ready, not on some global
	 * beat.
	 */
	public void update(float delta) {
		super.update(delta);
		
		cooldown -= delta;
		if (cooldown <= 0) {
			// The enemy detection method can be easily changed
			Enemy enemyInRange = detectFirstEnemy();
			
			// Fire!!
			if (enemyInRange != null) {
				createProjectiles(enemyInRange);
				cooldown = tower.projectileCooldown;
			}
		}
	}

	/**
	 * TODO: Does this fire at enemies? Or is something else controlling
	 * when the projectiles come out?
	 * @param target
	 */
	public void createProjectiles(Enemy target) {
		Projectile p = new Projectile(new ProjectileType(new Texture("img/possibleCresent.png"), 5), target);
		p.scale(-.5f);
		p.setPosition(getX(), getY());
		magazine.add(p);
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
	}
	
	public float getAlpha(){
		return alpha;
	}
	
	public void setAlpha(float alpha){
		this.alpha = alpha;
	}
	
	public List<Projectile> getProjectiles(){
		return magazine;
	}
	
	public boolean getMoved(){
		return moved;
	}
	
	public void setMoved(boolean moved){
		this.moved = moved;
	}
	
	public float getRange() {
		return tower.sightRange;
	}
	
	public void setRange(float sightRange) {
		this.tower.sightRange = sightRange;
	}
}
