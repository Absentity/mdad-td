/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	
	private static TextureRegion currentFrame;
	private int index = 0;
	private Animation animation;

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
		}/* else {
		// TODO Make everything have some kind of animation...
		}*/
		stateTime = 0f;
	}

	/**
	 * Chain Entity updating in each draw.
	 */
//	public void draw(SpriteBatch spriteBatch) {
//		float delta = Gdx.graphics.getDeltaTime();
//		stateTime += delta;
//		
//		update(delta);
//		
//		currentFrame = animation.getKeyFrame(stateTime, true);
//		super.draw(spriteBatch);
//	}
	
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
		TextureRegion[] frames = new TextureRegion[numFrames];
		index = 0;
		for (int i = 0; i < heightBounds; i++) {
            for (int j = 0; j < widthBounds; j++) {
            	frames[index++] = tempTexReg[i][j];
            }
		}
		Animation animation = new Animation(.05f, frames);
		return animation;
	}

	public void update(float stateTime) {
		currentFrame = animation.getKeyFrame(this.stateTime, true);
		this.stateTime += stateTime;

		if (health <= 0) {
			dispose();
		}
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


	public int getHealth() {
		return health;
	}

	public String showHealth() {
		//TODO: Draw bar?
		return "HP: " + Integer.toString(health);
	}
	
	public void drawHealthBar(float healthRatio) {
		// TODO: Implement me ~LOWPRIORITY
	}
	
	/**
	 * Get the Node the entity stands on. Useful for determining movement
	 * direction from the tile for MobileEntities.
	 * @return
	 */
//	public Node getTile() {
//		int[] tile = ObjectGrid.worldToTileCoordinates(getX(), getY());
//		int x = tile[0];
//		int y = tile[1];
//		
//		Node node;
//		try {
//			node = ObjectGrid.gridLayer(0).getNodeInGrid(x, y);
//		} catch (IndexOutOfBoundsException e) {
//			// Handle properly? Use logs? At least give more info.
////			e.printStackTrace();
//			System.err.println("Off the map!");
//			return Node.SENTINEL;
//		}
//		return node;
//	}

	public void dispose() {
		ObjectGrid.disposeList.add(this);
		// TODO ~LOWPRIORITY asplodey effektz
	}
}
