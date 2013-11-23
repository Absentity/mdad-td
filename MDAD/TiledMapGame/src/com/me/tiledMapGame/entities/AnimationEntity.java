package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

/**
 * So far, this is only for an explosion, so it's not very flexible.
 * It's only purpose is to be an animated sprite.
 */
public class AnimationEntity extends Entity {

	public AnimationEntity(Texture texture) {
		// Setting health to 1 instead of 0 in case rendering would stop for sub-1 health
		super(texture, 1);
		animation = createAnimation(texture, 5, 4, 20);
	}
	
	public void update(float delta) {
		super.update(delta);
		
		if (animation.isAnimationFinished(stateTime)) {
			dispose();
		}
	}

	/**
	 * What do I need to scale?! I want to set the width and the height.
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */
	public AnimationEntity createInstance(final float x, final float y, final float w, final float h) {
		AnimationEntity a = new AnimationEntity(texture);
		
		// Reduce width to width of a texture region
		final float trW = getWidth()/5;
		final float trH = getHeight()/4;
		
		// offset bottom left pixel by half for the center
//		final float trX = x - trW/2;
//		final float trY = y - trH/2;
		final float trX = x - w/2;
		final float trY = y - h/2;
		
		// scale ratio. Take the desired width of
		final float scale = w/trW;
		a.setPosition(trX, trY);
		a.setBounds(trX, trY, trW, trH);
//		a.setBounds(trX, trY, w, h);
		a.setScale(scale);
		
		// Set a random rotation
		a.rotate(MathUtils.random(360));
		
		System.out.println("[AnimationEntity] Explosion X:" + x + " Y:" + y + " R:" + w);
		System.out.println("[AnimationEntity]  Offset-- X:" + trX + " Y:" + trW + " R:" + scale*w);
		return a;
	}

}
