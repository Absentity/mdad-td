package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

public class Structure extends Entity {
	
	StructureType structure;

	public Structure(StructureType structure) {
		super(structure.texture, structure.health);
		this.structure = structure;
	}

}
