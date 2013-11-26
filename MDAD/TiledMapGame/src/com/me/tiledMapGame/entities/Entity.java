/**
 * 
 */
package com.me.tiledMapGame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.me.tiledMapGame.pathing.ObjectGrid;

/**
 * The entity class is solely responsible for very fundamental functionality
 * present in all entities. This may involve collision detection, animations,
 * rendering, getting the base tile the entity sits on, etc.
 */
public abstract class Entity extends Sprite {

	protected int health;
	protected float stateTime;
	
	protected TextureRegion currentFrame;
	protected Animation animation;
	protected Texture texture;
	
	final protected String name;
	final protected int price;

	private boolean selected;
	protected float alpha; // For drawing towers transparent before being placed. (.65 for transparent, 1 for opaque)
	private boolean moved = false;
	private boolean placed = false;

	public Entity(final String name, Texture texture, int health, final int price) {
		super(texture);
		this.name = name;
		this.texture = texture;
		this.health = health;
		this.price = price;
		
		if (texture.getHeight() == 96) {
			animation = createAnimation(texture, 4, 3, 12);
		} else if (texture.getHeight() == 160) {
			animation = createAnimation(texture, 4, 5, 20);
		} else if (texture.getHeight() == 240) {
			animation = createAnimation(texture, 9, 3, 27);
		} else if (texture.getHeight() == 48) {
			animation = createAnimation(texture, 4, 3, 12);
		} else if (texture.getHeight() == 128) {
			animation = createAnimation(texture, 2, 2, 4);
		} else {
//			animation = createAnimation(texture, numTilesWide, numTilesTall, numFramesTotal);
			TextureRegion[] frames = new TextureRegion[1];
			animation = new Animation(.05f, frames);
	        stateTime = 0f;
		}
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
	protected Animation createAnimation(Texture texture, int widthBounds, int heightBounds, int numFrames) {
		TextureRegion[][] tempTexReg = TextureRegion.split(texture, texture.getWidth()/widthBounds, texture.getHeight()/heightBounds);
		TextureRegion[] frames = new TextureRegion[numFrames];
		int index = 0;
		for (int i = 0; i < heightBounds; i++) {
            for (int j = 0; j < widthBounds; j++) {
            	frames[index++] = tempTexReg[i][j];
            }
		}
		Animation animation = new Animation(.1f, frames);
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
	
	/**
	 * EXTREMELY TEMPORARY IN THIS STATE. Will actually utilize an
	 * ObjectIntMap<String> or ObjectFloatMap<String> in the future
	 * Buffs update instead of an annoying if-sandwich.
	 * @param statName Name of a status you'd from which you'd like to get the value
	 * @return the value of statName
	 */
	public int getStat(String statName) {
		// TODO expand in Buffs update
		if ("Range".equals(statName)) {
			// HACKTACULAR
			if (this instanceof Tower)
				return (int) ((Tower) this).getRange();
			if (this instanceof Unit)
				return (int) ((Unit) this).getRange();
		} else if ("Price".equals(statName)) {
			return this.price;
		}
		return 0;
	}
	
	public String getName() {
		return name;
	}

	public float getMidpointX() {
//		return this.getX() + this.getBoundingRectangle().width/2;
//		return this.getX() + 1;
		return this.getX();
	}

	public float getMidpointY() {
//		return this.getY() + this.getBoundingRectangle().height/2;
//		return this.getY() + 1;
		return this.getY();
	}

	// Copied from Tower
	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public float getAlpha(){
		return alpha;
	}
	
	public void setAlpha(float alpha){
		this.alpha = alpha;
	}
	
	public boolean isPlaced(){
		return placed;
	}
	
	public void setPlaced(boolean placed){
		this.placed = placed;
	}
	
	public boolean getMoved(){
		return moved;
	}
	
	public void setMoved(boolean moved){
		this.moved = moved;
	}
}
