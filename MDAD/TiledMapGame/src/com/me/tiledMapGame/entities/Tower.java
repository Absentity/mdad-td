/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.Targeting;

/**
 * This is an instance of a Tower object. When the player places a tower
 * on the map, the game creates a new instance on that cell.
 * 
 * NOTE: Tower sprites MUST have 12 frames in the sprite sheet. (currently)
 */
public class Tower extends Entity {

	public final int PORTAL = 0;
	public final int CRESCENT = 1;
	public final int BOMB = 2;
	public final int AMPLIFIER = 3;
	public final int FIREBALL = 4;
	
	// DEFAULT
	public int towerType = 1;
	
	private boolean moved = false;
	
	private TowerType tower;
	private float cooldown;
	private boolean placed = false;
	private float alpha = .65f; // For drawing towers transparent before being placed. (.65 for transparent, 1 for opaque)
	private boolean selected = false;
	
	private int price;
	private Circle range;
	
	public Tower(TowerType tower) {
		super(tower.texture, tower.health);
		this.tower = tower;
		towerType = tower.towerType;
		
		switch(towerType){
			case CRESCENT:
				price = 70;
				break;
			case BOMB:
				price = 80;
				break;
			case AMPLIFIER:
				price = 60;
				break;
			case FIREBALL:
				price = 30;
				break;
			default:
				break;
		}
		
		/* Since a tower is created before it's set in the ground,
		   we have to pretend it has no range at first and edit it
		   later. */
		range = new Circle(-1000, -1000, tower.sightRange);
		if(tower.towerType == 0){
			alpha = 1;
		}
		cooldown = tower.fireRate;
		this.setBounds(getBoundingRectangle().x, getBoundingRectangle().y, 32, 32);
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
				Enemy enemyInRange = Targeting.detectFirstEnemyFrom(this);
				
				// Fire!!
				if (enemyInRange != null) {
					createProjectiles(enemyInRange);
					cooldown = tower.fireRate;
				}
			}
		}
	}
	
	public void dispose() {
		super.dispose();
		AnimationEntity ae = TiledMapGame.animationLibrary.get("Explosion").createInstance(getX(), getY(), getWidth()*2.2f, getHeight()*2.2f);
		ae.setColor(Color.CYAN);
		ObjectGrid.animations.add(ae);
	}

	/**
	 * TODO: Does this fire at enemies? Or is something else controlling
	 * when the projectiles come out?
	 * @param target
	 */
	public void createProjectiles(Enemy target) {
		Projectile p = Projectile.fireAt(tower.projectileType, this, target);
		p.scale(-.5f);
		p.setPosition(getX(), getY());
		p.setDamage(tower.projectileDamage);
		ObjectGrid.projectiles.add(p);
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

	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public int getPrice() {
		return this.price;
	}
}
