package com.me.tiledMapGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.entities.TowerType;
import com.me.tiledMapGame.screens.GameScreen;
import com.me.tiledMapGame.screens.MainMenuScreen;

public class Input implements InputProcessor {
	
	private int x = 0, y = 0;
	private Level level;
	
	public Input(Level level){
		this.level = level;
	}
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if((int) (((int)(screenX/32)) * 32) > TiledMapGame.screenWidth) {
			x = TiledMapGame.screenWidth-32;
		} else if((int) (((int)(screenX/32)) * 32) < 0) {
			x = 0;
		} else {
			x = (int) (((int)(screenX/32)) * 32);
		}
		
		if((int)((TiledMapGame.screenHeight) - ((screenY/32) * 32) - 32)+16 > TiledMapGame.screenHeight) {
			y = TiledMapGame.screenHeight-32;
		} else if ((int)((TiledMapGame.screenHeight) - ((screenY/32) * 32) - 32) < 0) {
			y = 0;
		} else {
			y = (int)((TiledMapGame.screenHeight) - ((screenY/32) * 32) - 32);
		}

		MainMenuScreen.done = true; // FOR TESTING
		
		GameScreen.towers.get(GameScreen.towers.size()-1).setPosition(x, y); // FOR TESTING
		GameScreen.towers.get(GameScreen.towers.size()-1).setAlpha(.65f); // FOR TESTING
		GameScreen.towers.get(GameScreen.towers.size()-1).setMoved(true);
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		
		if(MainMenuScreen.done){ // FOR TESTING
			//make sure that the tower can be placed
			if(level.getNode(screenX, screenY).is_buildable){
				GameScreen.towers.get(GameScreen.towers.size()-1).setAlpha(1);
				GameScreen.towers.get(GameScreen.towers.size()-1).setPlaced(true);
				//mark the spot as unbuildable for now TODO
				level.getNode(screenX,screenY).is_buildable = false;
			}
			else{
				GameScreen.towers.get(GameScreen.towers.size()-1).dispose();
				GameScreen.towers.remove(GameScreen.towers.size()-1);
				System.out.println("Can't build here!");//testing
			}

//			GameScreen.chose = false; //temporary fix
//			GameScreen.openTowerMenu = false;
			
			Gdx.input.setInputProcessor(GameScreen.stage);
		}
		
		
		
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		if((int) (((int)(screenX/32)) * 32) > TiledMapGame.screenWidth) {
			x = TiledMapGame.screenWidth-32;
		} else if((int) (((int)(screenX/32)) * 32) < 0) {
			x = 0;
		} else {
			x = (int) (((int)(screenX/32)) * 32);
		}
		
		if((int)((TiledMapGame.screenHeight) - ((screenY/32) * 32) - 32)+16 > TiledMapGame.screenHeight) {
			y = TiledMapGame.screenHeight-32;
		} else if ((int)((TiledMapGame.screenHeight) - ((screenY/32) * 32) - 32) < 0) {
			y = 0;
		} else {
			y = (int)((TiledMapGame.screenHeight) - ((screenY/32) * 32) - 32);
		}
		
		
		if(MainMenuScreen.done){ // FOR TESTING
			GameScreen.towers.get(GameScreen.towers.size()-1).setPosition(x, y);
		}
		
		
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}
