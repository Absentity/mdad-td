/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
		
		if(texture.getHeight() == 96){
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
		} else if(texture.getHeight() == 160){
			TextureRegion[][] tempTexReg = TextureRegion.split(texture, texture.getWidth()/4, texture.getHeight()/5);
			frames = new TextureRegion[20];
			index = 0;
			for (int i = 0; i < 5; i++) {
	            for (int j = 0; j < 4; j++) {
	                    frames[index++] = tempTexReg[i][j];
	            }
			}
			animation = new Animation(.05f, frames);
	        stateTime = 0f;
		} else if(texture.getHeight() == 240){
			TextureRegion[][] tempTexReg = TextureRegion.split(texture, texture.getWidth()/9, texture.getHeight()/3);
			frames = new TextureRegion[27];
			index = 0;
			for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 9; j++) {
	                    frames[index++] = tempTexReg[i][j];
	            }
			}
			animation = new Animation(.05f, frames);
	        stateTime = 0f;
		}
		
	}

	public void update(float stateTime) {
		currentFrame = animation.getKeyFrame(this.stateTime, true);
		this.stateTime += stateTime;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public Node getTile() {
		// TODO: Implement me! D:
		return null;
	}

	// TODO: Subclasses such as Tower could create some explosion effects on dispose!
	public abstract void dispose();
	
	public float getStatetime() {
		return stateTime;
	}
	
	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}
	
	public String showHealth(){
		return "HP: " + Integer.toString(health);
	}
}
