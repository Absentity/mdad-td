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


public class Enemy extends MobileEntity {
		
//	private Vector2 velocity = new Vector2();
//	private float speed = 16*2;
	
//	private int health = 100;
	private EnemyType enemy;
	
//	float tileWidth;
//	float tileHeight;
	
	// Destination
//	private float kingdomX;
//	private float kingdomY;
	Vector2 destination = new Vector2();
	
//	TiledMapTileLayer collisionLayer;
//	ObjectGrid objG = new ObjectGrid(10, 10);
<<<<<<< HEAD
<<<<<<< HEAD
	public Enemy(Sprite sprite) {
		super(sprite);
//		destination.set(x, y); // set to location of kingdom
=======
	public Enemy(EnemyType enemy) {
		super(enemy.texture, enemy.health, enemy.maxVelocity);
		this.enemy = enemy;
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8
=======
	public Enemy(EnemyType enemy) {
		super(enemy.texture, enemy.health, enemy.maxVelocity);
		this.enemy = enemy;
>>>>>>> 4b8a1b63a9b6cebc576f2533b4b8d2a9c902d0d8
	}
	
//	public Enemy(Sprite sprite, TiledMapTileLayer collisionLayer){
//		
//		super(sprite);
//		this.collisionLayer = collisionLayer;
//
//		tileWidth = collisionLayer.getTileWidth();
//		tileHeight = collisionLayer.getTileHeight();
//		
//		for(int x=0 ; x<collisionLayer.getWidth() ; x++){
//			for(int y=0; y<collisionLayer.getHeight() ; y++){
//				if(collisionLayer.getCell(x, y).getTile().getProperties().containsKey("kingdom")){
//					kingdomX = x*collisionLayer.getTileWidth();
//					kingdomY = y*collisionLayer.getTileHeight();
//				}
//			}
//		}
//		
//		kingdomX /= collisionLayer.getTileWidth();
//		kingdomY /= collisionLayer.getTileHeight();
//		
//		System.out.println(kingdomX + " " + kingdomY);
//		
//		for(int x = 0; x < objG.width; x++){
//			for(int y=0 ; y < objG.length ; y++){
//        		if(collisionLayer.getCell(x, y).getTile().getProperties().containsKey("blocked")){
//        			objG.gridLayers.get(0).getNodeInGrid(x, y).is_passable = false;
//        		}
////        		if(collisionLayer.getCell(i, j).getTile().getProperties().containsKey("kingdom")){
////        			objG.grid[i][j].is_passable = false;
////        		}
//        	}
//        }
//		
//		PathFinder.find_path(objG.gridLayers.get(0).getGrid(), (int)kingdomX, (int)kingdomY);
//		
//        for(int x = 0; x < objG.width; x++){
//        	for(int y=0 ; y < objG.length ; y++){
//        		System.out.print(x + "," + y + ": " + objG.gridLayers.get(0).getNodeInGrid(x, y).dir + " | ");
//        	}
//        	System.out.println();
//        }
//		
//	}
	
	public void update(float delta){
		super.update(delta);
//		
//		int currX = Math.round(getX()/tileWidth);
//		int currY = Math.round(getY()/tileHeight);
//		
////		System.out.println(currY + "," + currX + ": " + objG.GridLayers.get(0).grid[currY][currX].dir);
//		
		if(getX() - destination.x <= 5 && getY() - destination.y <= 5){ // within 5 pixels 
//			destination reached, begin attacking 
		}
			
//		if(currY == 7 && currX == 7){
//			//stop moving, coordinates for testing
//		}
//		else if(objG.gridLayers.get(0).getNodeInGrid(currX, currY).dir == Direction.RIGHT){
//			setX(getX() + 1);
//		}
//		else if(objG.gridLayers.get(0).getNodeInGrid(currX, currY).dir == Direction.LEFT){
//			setX(getX() - 1);
//		}
//		else if(objG.gridLayers.get(0).getNodeInGrid(currX, currY).dir == Direction.UP){
//			setY(getY() + 1);
//		}
//		else if(objG.gridLayers.get(0).getNodeInGrid(currX, currY).dir == Direction.DOWN){
//			setY(getY() - 1);
//		}
		
	}
	
}
