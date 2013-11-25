package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

public class ProjectileType /*implements EntityDefinition*/ {
	
	final String name;
	final Texture texture;
	final int price;
	final int health;
	final float maxSpeed;
	final boolean explodes;
	final float blastRadius;
	final int blastDamage;
	
	public ProjectileType(final Texture texture,
			final int health,
			final float maxSpeed,
			final float blastRadius,
			final int blastDamage) {
		this.name = "";
		this.price = 0;
		this.health = health;
		this.texture = texture;
		this.maxSpeed = maxSpeed;
		this.explodes = true;
		this.blastRadius = blastRadius;
		this.blastDamage = blastDamage;
	}
	
	public ProjectileType(final Texture texture, final int health, final float maxSpeed) {
		this.name = "";
		this.price = 0;
		this.health = health;
		this.texture = texture;
		this.maxSpeed = maxSpeed;
		this.explodes = false;
		this.blastRadius = 0;
		this.blastDamage = 0;
	}
//
//	@Override
//	public Projectile createInstance() {
//		return new Projectile(this);
//	}
}
