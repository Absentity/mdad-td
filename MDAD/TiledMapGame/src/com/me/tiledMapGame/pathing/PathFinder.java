/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.tiledMapGame.pathing;

import java.util.LinkedList;

import com.badlogic.gdx.math.Vector2;


/**
 *
 * @author Brandon Jones
 */
public class PathFinder {
/*
 * find_path()      Return: Void
 * -----------------------------------
 * Description:
 * Marks every Node on the grid with the direction that should be moved to
 *  get to the destination Node the quickest from the current Node;
 *  also marks each Node with the distance (the number of Nodes that must be 
 *  passed through) to the DESTINATION
 * This is essentially just a BFS ran from the DESTINATION to all reachable
 *  Nodes
 * -----------------------------------
 * Preconditions:
 * Any spot on the grid that should not be passed through MUST have its
 *  variable "is_passable == false"
 * -----------------------------------
 * Postconditions:
 * Every Node that is part of a path to the specified destination from ANY 
 *  source will be marked with a direction
 * If a Node is not part of any path to the specified destination, it will be
 *  marked "dir = Direction.NONE"
 * .
 */ 
    public static Vector2[][] find_path(Node[][] aGrid, int destX, int destY){
        LinkedList<Node> queue = new LinkedList<Node>();
        aGrid[destY][destX].visited = true;
        aGrid[destY][destX].dist_to_src = 0;
        queue.add(aGrid[destY][destX]);
        int count = 0;
        int in_queue = 1;               //For keeping track of distance from
                                        //the destination...DFD
        
        Vector2[][] vectorGrid = new Vector2[aGrid.length][aGrid.length];
        
        for(int i=0 ; i<aGrid.length ; i++){
        	for(int j=0 ; j<aGrid.length ; j++) {
        		vectorGrid[i][j] = new Vector2();
        	}
        }
        
        while(!queue.isEmpty()){
            for(int i = 0; i < in_queue; ++i){
                //The element(s) already in the queue are already marked, except destination
                Node cur_Node = queue.poll();
                
                //Mark and enqueue all of cur_Node's neighbors (if not already visited)
                // TODO: change from Direction enum to unit vector direction
                for(Node n: cur_Node.neighbors){
                    if(n.is_passable && !n.visited){
                        if(n.x > cur_Node.x) {
//                            n.dir.set(-1, 0);
                        	vectorGrid[n.x][n.y].set(-1,0);
                        }
                        if(n.x < cur_Node.x) {
//                            n.dir.set(1,0);
                        	vectorGrid[n.x][n.y].set(1,0);
                        }
                        if(n.y > cur_Node.y) {
//                            n.dir.set(0,-1);
                        	vectorGrid[n.x][n.y].set(0,-1);
                        }
                        if(n.y < cur_Node.y) {
//                            n.dir.set(0, 1);
                        	vectorGrid[n.x][n.y].set(0,1);
                        }

                        n.visited = true;  		 //Mark the node as visited
                        n.dist_to_src = count;	 //Mark dist_to_src
                        queue.add(n);
                    }
                }
            }
            in_queue = queue.size();       		 //For DFD
            count++;
        }
		
        return vectorGrid;
    }
    
}
