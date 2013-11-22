package com.me.tiledMapGame.entities;

import com.me.tiledMapGame.Level;

public class Structure extends Entity {
	
	private StructureType structure;
	private float waitTime;

	public Structure(StructureType structure) {
		super(structure.texture, structure.health);
		this.structure = structure;
	}
	
	public void update(float delta) {
		super.update(delta);
		waitTime += delta;
		if (waitTime >= structure.earnRate) {
			Level.gainResource(structure.resourceType, structure.earnAmount);
			waitTime -= structure.earnRate;
		}
	}

}
