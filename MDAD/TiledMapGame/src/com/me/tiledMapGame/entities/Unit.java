package com.me.tiledMapGame.entities;

import com.me.tiledMapGame.TiledMapGame;
import com.me.tiledMapGame.pathing.GridLayer;
import com.me.tiledMapGame.pathing.Node;
import com.me.tiledMapGame.pathing.ObjectGrid;
import com.me.tiledMapGame.pathing.PathFinder;

public class Unit extends MobileEntity {

	private UnitType unit;
	private boolean selected;
	private GridLayer personalLayer;
	private int price;
	
	
	public Unit(UnitType unit) {
		super(unit.texture, unit.health, unit.maxVelocity);
		this.unit = unit;
		this.setBounds(getX(), getY(), 16, 16);
		
		if(this.unit.getId() == 1){
			price = 10;
		}
		
		personalLayer = new GridLayer(TiledMapGame.screenWidth/32, TiledMapGame.screenHeight/32);
	}

	public void update(float stateTime){
		super.update(stateTime);
		
		float toMoveX = getTile().dir.x * maxVelocity;
		float toMoveY = getTile().dir.y * maxVelocity;
		
		setPosition(getX() + toMoveX, getY() + toMoveY);
	}
	
	public Node getTile() {
		int[] tile = ObjectGrid.worldToTileCoordinates(getX(), getY());
		int x = tile[0];
		int y = tile[1];
		
		Node node;
		try {
			node = personalLayer.getNodeInGrid(x, y);
		} catch (IndexOutOfBoundsException e) {
			// Handle properly? Use logs? At least give more info.
//			e.printStackTrace();
			System.err.println("Off the map!");
			return Node.SENTINEL;
		}
		return node;
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
	public boolean isSelected() {
		return this.selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public void setDestination(int x, int y) {
		personalLayer = new GridLayer(TiledMapGame.screenWidth/32, TiledMapGame.screenHeight/32);
		PathFinder.find_path(personalLayer.getGrid(), (x/32), (y/32));
	}
	
	public int getPrice() {
		return this.price;
	}
	
}
