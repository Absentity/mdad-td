package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;

public class ProjectileType /*implements EntityDefinition*/ {
	
	float maxVelocity;
	Texture texture;
	
	public ProjectileType(Texture texture, float maxVelocity) {
		this.texture = texture;
		this.maxVelocity = maxVelocity;
	}
//
//	@Override
//	public Projectile createInstance() {
//		return new Projectile(this);
//	}
}
