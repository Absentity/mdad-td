package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.PathFinder;

public class Unit extends MobileEntity {

	private UnitType unit;
	private boolean selected;
	
	public Unit(UnitType unit) {
		super(unit.texture, unit.health, unit.maxVelocity);
		this.unit = unit;
	}

	public void update(float stateTime){
		super.update(stateTime);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
