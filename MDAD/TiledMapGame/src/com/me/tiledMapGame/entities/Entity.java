/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.tiledMapGame.pathing.Node;

/**
 * @author Bret
 *
 */
public abstract class Entity extends Sprite {

	protected int health;
	protected float stateTime;
	
	private Animation animation;
    private Texture sheet;
    private static TextureRegion[] frames;
    private static TextureRegion currentFrame;
	private int index = 0;
	
	public Entity(Texture texture, int health) {
		super(texture);
		this.health = health;
		
		TextureRegion[][] tempTexReg = TextureRegion.split(texture, texture.getWidth()/4, texture.getHeight()/3);
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

	public void update(float stateTime){
		currentFrame = animation.getKeyFrame(this.stateTime, true);
		this.stateTime += stateTime;
	}
	
	public Node getTile() {
		// TODO: Implement me! D:
		return null;
	}
	
	public void dispose() {
		// TODO: Subclasses such as Tower could create some explosion effects on dispose!
	}
	
	public float getStatetime(){
		return stateTime;
	}
	
	public TextureRegion getCurrentFrame(){
		return currentFrame;
	}
}
