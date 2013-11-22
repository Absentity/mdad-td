package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Unit extends MobileEntity {

	private UnitType unit;
	
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

}
