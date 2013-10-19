package com.me.tiledMapGame.pathing;

/******************************************************************************
 * (Inner Class) GridLayer
 * ------------------------
 * Description: Holds a 2D array of Nodes, which represents squares on a 
 * 	Tiled Map.
 * 
 * *Note: 0,0 marks bottom left.
 *****************************************************************************/
public class GridLayer{
    public Node[][] grid;
    
    public GridLayer(int length, int width){
        grid = new Node[length][width];
        
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
}
