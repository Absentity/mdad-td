package com.me.tiledMapGame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.screens.Play;

public class Player extends Sprite implements InputProcessor {
	
	public static boolean kingdomSelected = false;
	
	private TiledMapTileLayer collisionLayer;
	
	// player velocity
	private Vector2 velocity = new Vector2();
	
	// camera velocity
	private Vector2 cVel = new Vector2();
	
	private float speed = 16*4;
	
	boolean in = false;
	boolean out = false;
	
	public Player(Sprite sprite, TiledMapTileLayer collisionLayer){
		super(sprite);
		this.collisionLayer = collisionLayer;
	}
	
	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	public void update(float delta){

		boolean collisionX = false;
		boolean collisionY = false;
		float oldX = getX(), tileWidth = collisionLayer.getTileWidth();
		float oldY = getY(), tileHeight = collisionLayer.getTileHeight();
		
		// move on x-axis
		if((getX() + velocity.x * delta) >= 0*collisionLayer.getTileWidth())
			setX(getX() + velocity.x * delta);
		
		if(velocity.x < 0){
			// top left
			collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
			
			// middle left
			if(!collisionX)
				collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight() / 2) / tileHeight)).getTile().getProperties().containsKey("blocked");
			
			// bottom left
			if(!collisionX)
				collisionX = collisionLayer.getCell((int)(getX() / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
		}
		else if(velocity.x > 0){
			// top right
			collisionX = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
			
			// middle right
			if(!collisionX)
				collisionX = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getWidth() / 2) / tileHeight)).getTile().getProperties().containsKey("blocked");
			
			// bottom right
			if(!collisionX)
				collisionX = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
		}
		
		// react to x collision
		if(collisionX) {
			setX(oldX);
			velocity.x = 0;
		}
		
		// move on y-axis
		setY(getY() + velocity.y * delta);
		
		if(velocity.y < 0) {
			// bottom left
			collisionY = collisionLayer.getCell((int)(getX() / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");

			// bottom middle
			if(!collisionY)
				collisionY = collisionLayer.getCell((int)((getX() + getWidth() / 2) / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
			
			// bottom right
			if(!collisionY)
				collisionY = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)(getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
		}
		else if(velocity.y > 0) {
			// top left
			collisionY = collisionLayer.getCell((int)(getX() / tileWidth), (int)((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
			
			// top middle
			if(!collisionY)
				collisionY = collisionLayer.getCell((int)((getX() + getWidth() / 2) / tileWidth), (int)((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
			
			// top right
			if(!collisionY)
				collisionY = collisionLayer.getCell((int)((getX() + getWidth()) / tileWidth), (int)((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
		}
		
		// react to y collision
		if(collisionY) {
			setY(oldY);
			velocity.y=0;
		}
		
		// sets position of camera
		Play.camera.position.x += cVel.x*delta;
		Play.camera.position.y += cVel.y*delta;
		
		// zoom continuously by holding "i" or "o" key
		if(in)
			Play.camera.zoom *= .995f;
		else if(out)
			Play.camera.zoom *= 1.005f;
		
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		switch(keycode) {
			case Keys.UP:
				velocity.y = speed;
				break;
			case Keys.DOWN:
				velocity.y = -speed;
				break;
			case Keys.LEFT:
				velocity.x = -speed;
				break;
			case Keys.RIGHT:
				velocity.x = speed;
				break;
			case Keys.W:
				cVel.y = 2*speed;
				break;
			case Keys.S:
				cVel.y = -2*speed;
				break;
			case Keys.D:
				cVel.x = 2*speed;
				break;
			case Keys.A:
				cVel.x = -2*speed;
				break;
			case Keys.I:
				in = true;
				break;
			case Keys.O:
				out = true;
				break;
			default:
				break;
				
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode) {
			case Keys.UP:
				velocity.y = 0;
				break;
			case Keys.DOWN:
				velocity.y = 0;
				break;
			case Keys.LEFT:
				velocity.x = 0;
				break;
			case Keys.RIGHT:
				velocity.x = 0;
				break;
			case Keys.W:
				cVel.y = 0;
				break;
			case Keys.S:
				cVel.y = 0;
				break;
			case Keys.D:
				cVel.x = 0;
				break;
			case Keys.A:
				cVel.x = 0;
				break;
			case Keys.I:
				in = false;
				break;
			case Keys.O:
				out = false;
				break;
			default:
				break;
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//		if(!kingdomSelected){
//			if(screenX >= (38*16) && screenX <= (40*16)
//					&& screenY >= (2*16) && screenY <= (4*16)) {
//						System.out.println("Kingdom Selected. Press 'x'.");
//						kingdomSelected = true;
//			}
//			else {
//				System.out.println("You must place the kingdom on the map first.");
//			}
//		}
			
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
}
