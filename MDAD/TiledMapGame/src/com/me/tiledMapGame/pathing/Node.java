package com.me.tiledMapGame.pathing;

import java.util.ArrayList;

public class Node {
	
	public Direction dir;
	public int dirValue;
    public boolean is_passable;
    int dist_to_src; 
    int x, y;
    boolean visited;
    ArrayList<Node> neighbors;
    private String terrain;

    public Node(){
        is_passable = true;
        visited = false;
        dir = Direction.NONE;
        neighbors = new ArrayList<>();
        terrain = "none";
    }
    
    public void setTerrain(String t){
    	terrain = t;
    }
    
    public String getTerrain(){
    	return terrain;
    }

}