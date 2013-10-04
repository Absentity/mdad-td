package com.me.tiledMapGame.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite implements InputProcessor {
	
	public static boolean kingdomSelected = false;
	
	// player position
	private Vector2 velocity = new Vector2();
	
	private float speed = 16*3, gravity = 16*1.5f;
	
	public Player(Sprite sprite){
		super(sprite);
	}
	
	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	public void update(float delta){
		
//		velocity.y -= gravity*delta;
//		
//		if(velocity.y > speed)
//			velocity.y = speed;
//		else if(velocity.y < -speed)
//			velocity.y = -speed;
		
		// sets position
		setY(getY() + velocity.y * delta);
		setX(getX() + velocity.x * delta);
		
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
	}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(!kingdomSelected){
			if(screenX >= (38*16) && screenX <= (40*16)
					&& screenY >= (2*16) && screenY <= (4*16)) {
						System.out.println("Kingdom Selected. Press 'x'.");
						kingdomSelected = true;
			}
			else {
				System.out.println("You must place the kingdom on the map first.");
			}
		}
			
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
