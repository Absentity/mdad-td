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
	
	private EnemyType enemy;
	
	Vector2 destination = new Vector2();
	
	int toMoveX = 0, toMoveY = 0;
	
	public Enemy(EnemyType enemy) {
		super(enemy.texture, enemy.health, enemy.maxVelocity);
		this.enemy = enemy;
		destination.set(10,10);
	}
	
	public void update(float delta) {
		super.update(delta);
		
		toMoveX = (int)vectorGrid[(int)getX()/32][(int)getY()/32].x;
		toMoveY = (int)vectorGrid[(int)getX()/32][(int)getY()/32].y;
		
		if(getX() - destination.x <= 5 && getY() - destination.y <= 5){ // within 5 pixels 
//			destination reached, begin attacking 
		}
		
//		toMoveX = (int) pathLayer.getNodeInGrid((int)getY()/32, (int)getX()/32).dir.x;
//		toMoveY = (int) pathLayer.getNodeInGrid((int)getY()/32, (int)getX()/32).dir.y;
		
		setPosition(getX() + toMoveX, getY() + toMoveY);
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
