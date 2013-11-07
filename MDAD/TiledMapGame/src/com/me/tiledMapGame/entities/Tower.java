/**
 * 
 */
package com.me.tiledMapGame.entities;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * This is an instance of a Tower object. When the player places a tower
 * on the map, the game creates a new instance on that cell.
 * 
 * NOTE: Tower sprites MUST have 12 frames in the sprite sheet. (currently)
 */
public class Tower extends Entity {
	
	public final int CRESENT = 1;
	public final int BOMB = 2;
	public final int AMPLIFIER = 3;
	public final int FIREBALL = 4;
	
	// DEFAULT
	public int towerType = 1;
	
	private boolean moved = false;
	
	private Animation animation;
    private Texture sheet;
    private static TextureRegion[] frames;
    private static TextureRegion currentFrame;
	private int index = 0;
	private float stateTime;
	private TowerType tower;
	
	private boolean placed = false;
	private float alpha = .65f; // For drawing towers transparent before being placed. (.65 for transparent, 1 for opaque)
	
	private ArrayList<Projectile> magazine = new ArrayList<>(); 
	
//	public Tower(Sprite sprite) {
//		super(sprite);
//		sheet = sprite.getTexture();
//		TextureRegion[][] tempTexReg = TextureRegion.split(sheet, sheet.getWidth()/4, sheet.getHeight()/3);
//		frames = new TextureRegion[12];
//		index = 0;
//		for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 4; j++) {
//                    frames[index++] = tempTexReg[i][j];
//            }
//		}
//		animation = new Animation(.05f, frames);
//        stateTime = 0f;	
//	}
	
	public Tower(TowerType tower) {
		super(tower.texture, tower.health);
		this.tower = tower;
		sheet = tower.texture;
		towerType = 1;
	}
	
//	public Tower(TowerType tower) {
//		super(tower.texture, tower.health);
//		this.tower = tower;
////		super(sprite);
//		sheet = tower.texture;
//		// TODO Move Animation generating code into Entity
//		TextureRegion[][] tempTexReg = TextureRegion.split(sheet, sheet.getWidth()/4, sheet.getHeight()/3);
//		frames = new TextureRegion[12];
//		index = 0;
//		for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 4; j++) {
//                    frames[index++] = tempTexReg[i][j];
//            }
//		}
//		animation = new Animation(.05f, frames);
//        stateTime = 0f;
//	}

	public void createProjectiles(Enemy target) {
		// TODO: create the projectile
		Projectile p = new Projectile(new ProjectileType(new Texture("img/possibleCresent.png"), 5), target);
		p.scale(-.5f);
		p.setPosition(getX(), getY());
		magazine.add(p);
	}
	
	public void update(float stateTime){
		super.update(stateTime);
//		currentFrame = animation.getKeyFrame(this.stateTime, true);
//		this.stateTime += stateTime;
	}
	
	public float getStateTime(){
		return super.getStatetime();
	}
	
	public TextureRegion getCurrentFrame(){
		return super.getCurrentFrame();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
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
	
	public boolean getMoved(){
		return moved;
	}
	
	public void setMoved(boolean moved){
		this.moved = moved;
	}
}
