package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.pathing.GridLayer;
import com.me.tiledMapGame.pathing.Node;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.PathFinder;
import com.me.tiledMapGame.pathing.Targeting;

public class Unit extends MobileEntity {

	private UnitType unit;
	private boolean selected;
	private GridLayer personalLayer;
	private int price;
	private Circle range;
	
	private float waitTime;
	
	public Unit(UnitType unit) {
		super(unit.texture, unit.health, unit.maxSpeed);
		this.unit = unit;
		this.setBounds(getX(), getY(), 16, 16);
		
		if (this.unit.getId() == 1) {
			price = 10;
		}
		
		range = new Circle(this.getMidpointX(), this.getMidpointY(), unit.sightRange);
		
		personalLayer = new GridLayer(TiledMapGame.screenWidth/32, TiledMapGame.screenHeight/32);
	}

	public void update(float delta) {
		super.update(delta);
		
		// Handle movement first
		float toMoveX = getTile().dir.x * maxVelocity;
		float toMoveY = getTile().dir.y * maxVelocity;
		
		setPosition(getX() + toMoveX, getY() + toMoveY);
		
		if (unit.attackRate != 0f) {
			waitTime += delta;
			if (waitTime >= unit.attackRate) {
				// The enemy detection method can be easily changed
				Enemy enemyInRange = Targeting.detectFirstEnemyFrom(this);
				
				// Fire!!
				if (enemyInRange != null) {
					createProjectiles(enemyInRange);
					waitTime = 0;
				}
			}
		}
	}

	/**
	 * TODO: Does this fire at enemies? Or is something else controlling
	 * when the projectiles come out?
	 * TODO "THIS IS REPEATED CODE AAAAAAAAAAAGH" -Bret
	 * @param target
	 */
	public void createProjectiles(Enemy target) {
		Projectile p = Projectile.fireAt(unit.projectileType, this, target);
		p.scale(-.5f);
		p.setPosition(getX(), getY());
		p.setDamage(unit.attackStrength);
		ObjectGrid.projectiles.add(p);
	}
	
	public Node getTile() {
		int[] tile = ObjectGrid.worldToTileCoordinates(getX(), getY());
		int x = tile[0];
		int y = tile[1];
		
		Node node;
		try {
			node = personalLayer.getNodeInGrid(x, y);
		} catch (IndexOutOfBoundsException e) {
			// Handle properly? Use logs? At least give more info.
//			e.printStackTrace();
			System.err.println("Off the map!");
			return Node.SENTINEL;
		}
		return node;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		
		AnimationEntity ae = TiledMapGame.animationLibrary.get("Explosion").createInstance(getX(), getY(), getWidth()*2.2f, getHeight()*2.2f);
		ae.setColor(Color.RED);
		ObjectGrid.animations.add(ae);
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void setDestination(int x, int y) {
		personalLayer = new GridLayer(TiledMapGame.screenWidth/32, TiledMapGame.screenHeight/32);
		PathFinder.find_path(personalLayer.getGrid(), (x/32), (y/32));
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public float getRange() {
		return range.radius;
	}
	
}
