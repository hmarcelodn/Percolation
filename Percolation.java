/****************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    java Percolation
 *  Dependencies: StdOut.java
 *
 * This class uses Quick-Union-Find Algorithm without path compression
 * and performs dynamic connectivity until the system percolates
 * 
 * Hugo Marcelo Del Negro.
 * 
 * Quick-Union Algotithm
 * Percolation analysis
 *
 ****************************************************************************/

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class Percolation {

    private int[] grid1D;
    private boolean[][] grid2D;
    private int virtualBottom; 
    private int virtualTop;
    private int valueN;
    
    // create N-by-N grid, with all sites blocked
    // (N^2 Worst-Case)
    public Percolation(int N){
        
        if(N <= 0) {
            throw new IllegalArgumentException("Wrong number of grid sites.");
        }
        
        grid1D = new int[(N * N) + 2];
        grid2D = new boolean[N][N];        
        valueN = N;        
        
        virtualTop = 0;
        virtualBottom = N * N + 1;
        
        grid1D[virtualTop] = virtualTop;
        grid1D[virtualBottom] = virtualBottom;        
        
        for(int i = 0; i < N; i ++){
            for(int j = 0; j < N; j ++){
                int site = xyTo1D(i + 1, j + 1);
                
                if(i == 0){
                    grid1D[site + 1] = 0;
                }
                else if(i == (N - 1)){
                    grid1D[site + 1] = virtualBottom;
                }
                else{
                    grid1D[site + 1] = site + 1;
                }
            }
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j){              
        validate(i ,j);         
        
        //Open a site
        grid2D[i - 1][j - 1] = true;
        
        // Map to 1-Dimensional Component
        int currentSite = xyTo1D(i, j);        
        
        StdOut.print("Current -> " + currentSite + "\n");
        
        // Get Left Adjacent Site
        if(!((j - 1) == 0)){
            StdOut.print("Connecting Left\n");
            tryAdjacentUnion(i, j - 1, currentSite);   
        }
        
        // Get Right Adjacent Site
        if(!((j + 1) > valueN)){
            StdOut.print("Connecting Right\n");
            tryAdjacentUnion(i, j + 1, currentSite);
        }
        
        // Get Below Adjacent Site
        if(!((i + 1) > valueN)){
            StdOut.print("Connecting Below\n");
            tryAdjacentUnion(i + 1, j, currentSite);
        }
        
        // Get Above Adjacent Site
        if(!((i - 1) == 0)){
            StdOut.print("Connecting Above\n");
            tryAdjacentUnion(i - 1, j, currentSite);
        }        
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j){
        validate(i ,j);
        
        return grid2D[i - 1][j - 1] == true;
    }     
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j){
        validate(i ,j);
        
        if(!isOpen(i, j)) return false;
        
        StdOut.print("Start is full!");
        
        StdOut.print("i -> " + i + " j -> " + j + "\n");
        
        int currentSite = xyTo1D(i, j) + 1;        
        int currentSiteRoot = find(currentSite);        
        
        StdOut.print("Site -> " + currentSite + " Root -> " + currentSiteRoot + "\n");
        //print();
              
       StdOut.print("End is full!");
       
        return currentSiteRoot == find(grid1D[virtualBottom]) && currentSiteRoot == find(grid1D[virtualTop]);
        
        
        //return currentSiteRoot == virtualBottom && currentSiteRoot == virtualTop;
    }     
    
    // does the system percolate?
    // Constant Calls to Find Method
    public boolean percolates(){        
        StdOut.print("Percolates?\n");
        
        StdOut.print("Virtual Bottom Index ->" + find(grid1D[virtualBottom]) + "\n");
        StdOut.print("Virtual Top Index -> " + find(grid1D[virtualTop]) + "\n");
        
        return find(grid1D[virtualBottom]) == find(grid1D[virtualTop]);
    }                 
    
    private void validate(int i, int j){        
        if(i < 1 || j > valueN) {
            throw new IndexOutOfBoundsException("Out of grid range. i=" + i + " j:" + j);
        }
    }
    
    // (Constant-Time)
    private int xyTo1D(int x, int y){
        return ((x - 1) * valueN) + (y - 1);
    }
    
    private void print(){
        System.out.println(Arrays.toString(grid1D));
    }  
    
    private void tryAdjacentUnion(int p, int q, int currentSite){
        if(isOpen(p, q)){
            int adjacentSite = xyTo1D(p, q);
            if(!connected(currentSite + 1, adjacentSite + 1)){
                union(currentSite + 1, adjacentSite + 1);
                
                if(connected(currentSite + 1, adjacentSite + 1)){
                    StdOut.print("\nConnected!!!" + (currentSite + 1) + " " + (adjacentSite + 1) + "\n");
                }
                else{
                    StdOut.print("\nNot Connected!!!");
                }
            }
            else{
                StdOut.print("\nAlready Connected!!!" + (currentSite + 1) + " " + (adjacentSite + 1) + "\n");
            }
        }
        
        //print();
    }
    
    // join 2 components
    private void union(int p, int q){
        int i = find(p);
        int j = find(q);
        
        grid1D[i] = j;
    }
    
    // find root
    private int find(int p){
        while(p != grid1D[p] ){
            p = grid1D[p];
        }
        
        return p;
    }
   
    // Verify if two components has the same root
    private boolean connected(int p, int q){
        return find(p) == find(q);
    }
    
    // test client (optional)
    public static void main(String[] args){
    }   
}