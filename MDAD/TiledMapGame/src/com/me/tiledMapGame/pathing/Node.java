package com.me.tiledMapGame.pathing;

import java.util.ArrayList;

public class Node {
	
	public Direction dir;
	public int dirValue;
    int dist_to_src; 
    int x, y;
    public boolean is_passable;
    boolean visited;
    ArrayList<Node> neighbors;

    public Node(){
        is_passable = true;
        visited = false;
        dir = Direction.NONE;
        neighbors = new ArrayList<>();
    }

}
