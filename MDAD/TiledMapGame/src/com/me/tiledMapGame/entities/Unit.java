package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Unit extends MobileEntity {

	private UnitType unit;
	
	public Unit(UnitType unit) {
		super(unit.texture, unit.health, unit.maxVelocity);
		this.unit = unit;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
