package com.me.tiledMapGame.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.me.tiledMapGame.pathing.Direction;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.PathFinder;


public class Enemy extends Sprite {
	
	public static final int UP = -1, DOWN = 1, RIGHT = 1, LEFT = -1, NEUTRAL = 0;
	
//	private Vector2 velocity = new Vector2();
//	private float speed = 16*2;
	
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

		
		for(int j=0 ; j < objG.getLength() ; j++){
        	for(int i = 0; i < objG.getWidth(); ++i){
        		if(collisionLayer.getCell(j, i).getTile().getProperties().containsKey("blocked")){
        			objG.grid[i][j].is_passable = false;
        		}
//        		if(collisionLayer.getCell(i, j).getTile().getProperties().containsKey("kingdom")){
//        			objG.grid[i][j].is_passable = false;
//        		}
        	}
        }
		
		PathFinder.find_path(objG, (int)kingdomX, (int)kingdomY);
		
		for(int j=0 ; j < objG.getLength() ; j++){
        	for(int i = 0; i < objG.getWidth(); ++i){
        		System.out.println(j + "," + i + ": " + objG.grid[j][i].dir);
        	}
        }
		
	}
	
	public void draw(SpriteBatch spriteBatch){
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}
	
	public void update(float delta){
		
		int currX = (int)(getX()/tileWidth);
		int currY = (int)(getY()/tileHeight);
		
		System.out.println(currY + "," + currX + ": " + objG.grid[currY][currX].dir);
		
		if(objG.grid[currY][currX].dir == Direction.RIGHT){
			setX(currX + tileWidth);
//			System.out.println("going " + objG.grid[currY][currX].dir);
		}
		if(objG.grid[currY][currX].dir == Direction.LEFT){
			setX(currX - tileWidth);
//			System.out.println("going " + objG.grid[currY][currX].dir);
		}
		if(objG.grid[currY][currX].dir == Direction.UP){
			setY(currY - tileHeight);
//			System.out.println("going " + objG.grid[currY][currX].dir);
		}
		if(objG.grid[currY][currX].dir == Direction.DOWN){
			setY(currY + tileHeight);
//			System.out.println("going " + objG.grid[currY][currX].dir);
		}
		
	}
	
	
}
