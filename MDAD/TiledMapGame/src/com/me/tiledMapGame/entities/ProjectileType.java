package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

public class ProjectileType /*implements EntityDefinition*/ {
	
	final float maxSpeed;
	final Texture texture;
	final boolean explodes;
	final float blastRadius;
	final int blastDamage;
	
	public ProjectileType(final Texture texture, final float maxSpeed, final float blastRadius, final int blastDamage) {
		this.texture = texture;
		this.maxSpeed = maxSpeed;
		this.explodes = true;
		this.blastRadius = blastRadius;
		this.blastDamage = blastDamage;
	}
	
	public ProjectileType(final Texture texture, final float maxSpeed) {
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
