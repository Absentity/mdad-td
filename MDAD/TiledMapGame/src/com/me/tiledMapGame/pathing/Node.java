package com.me.tiledMapGame.pathing;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Node {
	
	public static final Node SENTINEL = new Node();
	
	public Vector2 dir;
    public boolean is_passable;
    public boolean is_buildable;
    
    private String terrain;
    
    int dist_to_src; 
    int x, y;
    boolean visited;
    ArrayList<Node> neighbors;

    public Node(){
        is_passable = true;
        is_buildable = true;
        visited = false;
        dir = new Vector2();
        neighbors = new ArrayList<Node>();
        terrain = "none";
    }
    
    public void setTerrain(String t){
    	terrain = t;
    }
    
    public String getTerrain(){
    	return terrain;
    }
    
    public void markTower(){
    	this.is_buildable = false;
    	this.is_passable = false;
    }
    
    public void markObstacle(){
    	this.is_buildable = false;
    }
}