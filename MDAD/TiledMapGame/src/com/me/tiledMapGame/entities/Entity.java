/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.me.tiledMapGame.pathing.Node;
import com.me.tiledMapGame.pathing.ObjectGrid;

/**
 * The entity class is solely responsible for very fundamental functionality
 * present in all entities. This may involve collision detection, animations,
 * rendering, getting the base tile the entity sits on, etc.
 */
public abstract class Entity extends Sprite {

	protected int health;
	protected float stateTime;
	
	private Animation animation;
    private static TextureRegion currentFrame;
	private int index = 0;
	
	public Entity(Texture texture, int health) {
		super(texture);
		this.health = health;
		
		if (texture.getHeight() == 96) {
			animation = createAnimation(texture, 4, 3, 12);
		} else if (texture.getHeight() == 160) {
			animation = createAnimation(texture, 4, 5, 20);
		} else if (texture.getHeight() == 240) {
			animation = createAnimation(texture, 9, 3, 27);
		} else if (texture.getHeight() == 48) {
			animation = createAnimation(texture, 4, 3, 12);
		} else if (texture.getHeight() == 16) {
//			animation = createAnimation(texture, numTilesWide, numTilesTall, numFramesTotal);
			TextureRegion[] frames = new TextureRegion[1];
			animation = new Animation(.05f, frames);
	        stateTime = 0f;
		}
		stateTime = 0f;
	}
	
	/**
	 * Create an animation object from a texture and some given constraints.
	 * @param texture      The texture to cut up
	 * @param widthBounds  Number of tiles wide to cut the texture
	 * @param heightBounds Number of tiles high to cut the texture
	 * @param numFrames    Number of frames total to generate
	 * @return
	 */
	private Animation createAnimation(Texture texture, int widthBounds, int heightBounds, int numFrames) {
		TextureRegion[][] tempTexReg = TextureRegion.split(texture, texture.getWidth()/widthBounds, texture.getHeight()/heightBounds);
		TextureRegion[] frames = new TextureRegion[12];
		index = 0;
		for (int i = 0; i < heightBounds; i++) {
            for (int j = 0; j < widthBounds; j++) {
            	frames[index++] = tempTexReg[i][j];
            }
		}
		return new Animation(.05f, frames);
	}

	public void update(float stateTime) {
		currentFrame = animation.getKeyFrame(this.stateTime, true);
		this.stateTime += stateTime;
	}
	
	public float getStatetime() {
		return stateTime;
	}
	
	public TextureRegion getCurrentFrame() {
		return currentFrame;
	}
	
	/**
	 * Decrease the health of this enemy by the damageAmount
	 * @param damageAmount
	 */
	public void hurt(int damageAmount) {
		health -= damageAmount;
	}
	
	public void drawHealthBar(float healthRatio) {
		// TODO: Implement me! D:
	}
	
	public Node getTile() {
		// TODO: Implement me! D:
		// I need to have a way to 
		int[] tile = ObjectGrid.worldToTileCoordinates(getX(), getY());
		int x = tile[0];
		int y = tile[1];
		Node node = ObjectGrid.gridLayers().get(0).getNodeInGrid(x, y);
		return null;
	}

	// TODO: Subclasses such as Tower could create some explosion effects on dispose!
	public abstract void dispose();
}
