package com.me.tiledMapGame.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.pathing.Direction;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.PathFinder;
import com.me.tiledMapGame.pathing.GridLayer;


public class Enemy extends Sprite {
		
//	private Vector2 velocity = new Vector2();
//	private float speed = 16*2;
	
	private int health = 100;
	
	float tileWidth;
	float tileHeight;
	
	// Destination
	private float kingdomX;
	private float kingdomY;
	
	TiledMapTileLayer collisionLayer;
	ObjectGrid objG = new ObjectGrid(10, 10);

	public Enemy(Sprite sprite, TiledMapTileLayer collisionLayer){
		
		super(sprite);
		this.collisionLayer = collisionLayer;

		tileWidth = collisionLayer.getTileWidth();
		tileHeight = collisionLayer.getTileHeight();
		
		for(int x=0 ; x<collisionLayer.getWidth() ; x++){
			for(int y=0; y<collisionLayer.getHeight() ; y++){
				if(collisionLayer.getCell(x, y).getTile().getProperties().containsKey("kingdom")){
					kingdomX = x*collisionLayer.getTileWidth();
					kingdomY = y*collisionLayer.getTileHeight();
				}
			}
		}
		
		kingdomX /= collisionLayer.getTileWidth();
		kingdomY /= collisionLayer.getTileHeight();
		
		System.out.println(kingdomX + " " + kingdomY);
		
		for(int x = 0; x < objG.width; x++){
			for(int y=0 ; y < objG.length ; y++){
        		if(collisionLayer.getCell(x, y).getTile().getProperties().containsKey("blocked")){
        			objG.GridLayers.get(0).grid[x][y].is_passable = false;
        		}
//        		if(collisionLayer.getCell(i, j).getTile().getProperties().containsKey("kingdom")){
//        			objG.grid[i][j].is_passable = false;
//        		}
        	}
        }
		
		PathFinder.find_path(objG.GridLayers.get(0).grid, (int)kingdomX, (int)kingdomY);
		
        for(int x = 0; x < objG.width; x++){
        	for(int y=0 ; y < objG.length ; y++){
        		System.out.print(x + "," + y + ": " + objG.GridLayers.get(0).grid[x][y].dir + " | ");
        	}
        	System.out.println();
        }
		
	}
	
	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
//		try {
//			Thread.sleep(300);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		super.draw(spriteBatch);
	}
	
	public void update(float delta){
		
		int currX = Math.round(getX()/tileWidth);
		int currY = Math.round(getY()/tileHeight);
		
//		System.out.println(currY + "," + currX + ": " + objG.GridLayers.get(0).grid[currY][currX].dir);
		
		if(currY == 7 && currX == 7){
			//stop moving, coordinates for testing
		}
		else if(objG.GridLayers.get(0).grid[currY][currX].dir == Direction.RIGHT){
			setX(getX() + tileWidth);
		}
		else if(objG.GridLayers.get(0).grid[currY][currX].dir == Direction.LEFT){
			setX(getX() - tileWidth);
		}
		else if(objG.GridLayers.get(0).grid[currY][currX].dir == Direction.UP){
			setY(getY() + tileHeight);
		}
		else if(objG.GridLayers.get(0).grid[currY][currX].dir == Direction.DOWN){
			setY(getY() - tileHeight);
		}
		
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
}
