/**
 * 
 */
package com.me.tiledMapGame.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

<<<<<<< HEAD
/** NOTE: Tower sprites MUST have 12 frames in the sprite sheet.
 *
 * @author Bret
 *
=======
/**
 * This is an instance of a Tower object. When the player places a tower
 * on the map, the game creates a new instance on that cell.
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8
 */
public class Tower extends Entity {
	
	private Animation animation;
    private Texture sheet;
    private static TextureRegion[] frames;
    private static TextureRegion currentFrame;
	private int index = 0;
	private float stateTime;
	private TowerType tower;
	
<<<<<<< HEAD
	private boolean placed = false;
	private float alpha = .65f; // For drawing towers transparent before being placed. (.65 for transparent, 1 for opaque)
	
	private ArrayList<Projectile> magazine = new ArrayList<>(); 
	
	public Tower(Sprite sprite) {
		super(sprite);
		sheet = sprite.getTexture();
=======
	public Tower(TowerType tower) {
		super(tower.texture, tower.health);
		this.tower = tower;
//		super(sprite);
		sheet = tower.texture;
		// TODO Move Animation generating code into Entity
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8
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
		Projectile p = new Projectile(new Sprite(new Texture("img/fireball.png")), (int)getX(), (int)getY());
		magazine.add(p);
	}
	
	public void update(float stateTime){
		currentFrame = animation.getKeyFrame(this.stateTime, true);
		this.stateTime += stateTime;
	}
	
	public float getStateTime(){
		return stateTime;
	}
	
	public TextureRegion getCurrentFrame(){
		return currentFrame;
	}
	
	public boolean isPlaced(){
		return placed;
	}
	
	public void setPlaced(boolean placed){
		this.placed = placed;
	}
	
	public float getAlpha(){
		return alpha;
	}
	
	public void setAlpha(float alpha){
		this.alpha = alpha;
	}
	
	public ArrayList<Projectile> getProjectiles(){
		return magazine;
	}
}
