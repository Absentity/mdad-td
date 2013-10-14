package com.me.tiledMapGame.pathing;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedList;

/**
 *
 * @author brandon
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Test ObjectGrid;
        ObjectGrid objG = new ObjectGrid(10, 10);
        objG.grid[0][8].is_passable = false;
        objG.grid[0][1].is_passable = false;
        objG.grid[1][1].is_passable = false;
        objG.grid[2][2].is_passable = false;
        objG.grid[3][3].is_passable = false;
        PathFinder.find_path(objG, 9, 0);
        //print out the bottom row's directions
        for(int i = 0; i < objG.getWidth(); ++i){
        	for(int j=0 ; j < objG.getLength() ; j++){
        		System.out.println(i + "," + j + ": " + objG.grid[i][j].dir);
        	}
        }

    }

    public static point BFS(int[][] x, point p) {
        //initiate queue for the BFS and put the starting point in it
        LinkedList<point> queue = new LinkedList<point>();
        queue.add(p);

        //begin the breadth first search
        while (!queue.isEmpty()) {
            //remove the first item from the queue
            point p2 = queue.poll();

            //search in all directions
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    if (Math.abs(i) == Math.abs(j))//only need to check four directions
                    {
                        continue;
                    }
                    if (p2.y + i < 1000 && p2.x + j < 1000 && p2.y + i >= 0 && p2.x + j >= 0) {//check if we're in bounds
                        if (x[p2.y + i][p2.x + j] == -1) {//check if the spot has been visited before
                            continue;
                        } else if (x[p2.y + i][p2.x + j] == 2) {//check if we found what we are looking for
                            return new point(p2.y + i, p2.x + j, p2);
                        } else {//we have neither found what we were looking for or checked this spot yet
                            point pNew = new point(p2.y + i, p2.x + j, p2);
                            queue.add(pNew);
                            x[pNew.y][pNew.x] = -1; // mark the point as searched
                        }
                    }
                }
            }




        }
        return null;
    }
}

class point {

    public int x, y;
    public point parent;

    public point(int r, int c, point par) {
        x = c;
        y = r;
        parent = par;
    }

    public String toString() {
        return String.format(y + " " + x);
    }
}
