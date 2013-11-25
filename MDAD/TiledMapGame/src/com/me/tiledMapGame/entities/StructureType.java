package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

public class StructureType implements EntityDefinition {
	
	final String name;
	final Texture texture;
	final int health;
	final int price;
	final float earnRate;
	final int earnAmount;
	final String resourceType;
	
	/**
	 * Structures have an earn cycle. Each cycle lasts for a set time and grants
	 * an amount of a given resource at the end.
	 * @param texture    TextureRegion file describing animation
	 * @param health     Structure Health
	 * @param earnRate   Seconds-long for a cycle
	 * @param earnAmount Integer value. Narrow minded?
	 */
	public StructureType(final String name,
			final Texture texture,
			final int health,
			final int price,
			final float earnRate,
			final int earnAmount,
			final String resourceType) {
		this.name = name;
		this.texture = texture;
		this.health = health;
		this.price = price;
		this.earnRate = earnRate;
		this.earnAmount = earnAmount;
		this.resourceType = resourceType;
	}
	
	public Structure createInstance() {
		return new Structure(this);
	}
}
