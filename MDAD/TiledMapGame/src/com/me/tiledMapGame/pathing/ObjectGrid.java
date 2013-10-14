package com.me.tiledMapGame.pathing;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author brandon
 */
/******************************************************************************
 * ObjectGrid
 * ----------
 * Description:
 * 0,0 marks bottom left
 *****************************************************************************/
public class ObjectGrid {
    public Node[][] grid;
    private int length;
    private int width;
    
    public ObjectGrid(int length, int width){
        grid = new Node[length][width];
        this.setLength(length);
        this.setWidth(width);
        
        //Set each node's neighbor(s) and coordinates
        for(int r = 0; r < length; ++r){
            for(int c = 0; c < width; ++c){
                grid[r][c] = new Node();
                grid[r][c].x = c;
                grid[r][c].y = r;
            }
        }
        for(int r = 0; r < length; ++r){
            for(int c = 0; c < width; ++c){
                if(r < length-1)// Has a neighbor above
                    grid[r][c].neighbors.add(grid[r+1][c]);
                if(r > 0)       // Has a neighbor below
                    grid[r][c].neighbors.add(grid[r-1][c]);
                if(c < length-1)// Has a neighbor to the right
                    grid[r][c].neighbors.add(grid[r][c+1]);
                if(c > 0)       // Has a neighbor to the left
                    grid[r][c].neighbors.add(grid[r][c-1]);
            }
        }
        
    }

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
    
}
//class Node{
//    public Direction dir;
//    int dist_to_src; 
//    int x, y;
//    boolean is_passable;
//    boolean visited;
//    ArrayList<Node> neighbors;
//
//    public Node(){
//        is_passable = true;
//        visited = false;
//        dir = Direction.NONE;
//        neighbors = new ArrayList<>();
//    }
//}

//enum Direction{
//    UP, DOWN, LEFT, RIGHT, NONE;
//}
