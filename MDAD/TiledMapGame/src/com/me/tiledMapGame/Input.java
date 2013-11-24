package com.me.tiledMapGame;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.me.tiledMapGame.entities.Tower;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.PathFinder;
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
		
		List<Tower> towers = ObjectGrid.towers;
		towers.get(towers.size()-1).setPosition(x, y); // FOR TESTING
		towers.get(towers.size()-1).setAlpha(.65f); // FOR TESTING
		towers.get(towers.size()-1).setMoved(true);
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		List<Tower> towers = ObjectGrid.towers;
		
		if(MainMenuScreen.done){ // FOR TESTING
			//make sure that the tower can be placed
			if(level.getNode(screenX/32, 15-(screenY)/32).is_buildable){
				if(ObjectGrid.towerList().size()-1 >= 0) {
					towers.get(towers.size()-1).setAlpha(1);
					towers.get(towers.size()-1).setPlaced(true);
					//mark the spot as unbuildable for now TODO
					level.getNode(screenX/32, 15-(screenY)/32).markTower();//getNode(screenX/32,16-(screenY/32)).markTower();
					System.out.println(screenX/32 + " " + (16-(screenY/32)));
					level.getObjectGrid().clearAllVisited();
					PathFinder.find_path(ObjectGrid.gridLayer(0).getGrid(), 10, 10);
				}
				//Reset enemy grids
				
//				for(Enemy e: level.enemies){
//					e.resetGrid(level.getGrid(0));
//				}
				
			}
			else{
				if(ObjectGrid.towerList().size()-1 >= 0) {
					towers.get(towers.size()-1).dispose();
					towers.remove(towers.size()-1);
					System.out.println("Can't build here!");//testing
				}
			}

//			GameScreen.chose = false; //temporary fix
//			GameScreen.openTowerMenu = false;
			
			Gdx.input.setInputProcessor(GameScreen.im);
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
		
		
		if(MainMenuScreen.done) {
			if(ObjectGrid.towerList().size()-1 >= 0) {
				ObjectGrid.towerList().get(ObjectGrid.towerList().size()-1).setPosition(x, y);
			}
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
