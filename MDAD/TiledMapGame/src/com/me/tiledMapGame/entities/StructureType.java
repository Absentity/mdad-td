package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

public class StructureType implements EntityDefinition {
	
	Texture texture;
	int health;
	float earnRate;
	int earnAmount;
	String resourceType;
	
	/**
	 * Structures have an earn cycle. Each cycle lasts for a set time and grants
	 * an amount of a given resource at the end.
	 * @param texture    TextureRegion file describing animation
	 * @param health     Structure Health
	 * @param earnRate   Seconds-long for a cycle
	 * @param earnAmount Integer value. Narrow minded?
	 */
	public StructureType(Texture texture, int health, float earnRate, int earnAmount) {
		this.texture = texture;
		this.health = health;
		this.earnRate = earnRate;
		this.earnAmount = earnAmount;
	}
	
	public Structure createInstance() {
		return new Structure(this);
	}
}
