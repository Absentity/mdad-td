package com.me.tiledMapGame.pathing;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class Node {
	
	public Vector2 dir;
    public boolean is_passable;
    int dist_to_src; 
    int x, y;
    boolean visited;
    ArrayList<Node> neighbors;
    private String terrain;

    public Node(){
        is_passable = true;
        visited = false;
        dir = new Vector2();
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