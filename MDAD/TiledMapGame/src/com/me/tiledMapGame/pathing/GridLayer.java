package com.me.tiledMapGame.pathing;

/**
 * Holds a 2D array of Nodes, which represents squares on a Tiled Map.
 * <p>
 * *Note: (0,0) marks the bottom left.
 */
public class GridLayer{
    private Node[][] grid;
    
    /**
     * Initializes a 2 Dimensional Array of Nodes, known as "grid", for use by
     * PathFinder.
     * 
     * @param length
     * @param width
     */
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
    
    /**
     * Getter method for this GridLayer's 2 Dimensional array of Nodes.
     * 
     * @return returns the 2 Dimensional array of Nodes for this GridLayer.
     */
    public Node[][] getGrid(){
		return grid;
    }
    
    /**
     * Quickly access the node specified by x and y in grid.
     * 
     * @param x
     * @param y
     * @return the node specified by grid[y][x].
     */
    public Node getNodeInGrid(int x, int y){
    	return grid[y][x];
    }
}
