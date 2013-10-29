/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Bret
 *
 */
public class Tower extends Entity {
	
	private Animation animation;
    private Texture sheet;
    public static TextureRegion[] frames;
    private static TextureRegion currentFrame;
	private int index = 0;
	private float stateTime;
	
	public Tower(Sprite sprite) {
		super(sprite);
		sheet = sprite.getTexture();
		TextureRegion[][] tempTexReg = TextureRegion.split(sheet, sheet.getWidth()/4, sheet.getHeight()/3);
		frames = new TextureRegion[12];
		index = 0;
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                    frames[index++] = tempTexReg[i][j];
            }
		}
		animation = new Animation(.05f, frames);
        stateTime = 0f;
	}

	public void createProjectiles() {
		// TODO: create the projectile
		// TODO: add to the projectile arraylist
	}
	
	public void update(float stateTime){
		currentFrame = animation.getKeyFrame(this.stateTime, true);
		this.stateTime += stateTime;
	}
	
	public Animation getAnimation(){
		return animation;
	}
	
	public float getStateTime(){
		return stateTime;
	}
	
	public TextureRegion getCurrentFrame(){
		return currentFrame;
	}
	
}
