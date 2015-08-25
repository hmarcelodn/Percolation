/****************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats
 *  Dependencies: StdOut.java
 *
 * Author: Hugo Marcelo Del Negro
 *
 ****************************************************************************/

import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
   
   // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T){        
        if( N <= 0 || T <= 0){
            throw new IllegalArgumentException(); 
        }
    }     
   
    // sample mean of percolation threshold
    public double mean() {
        return 0;
    } 
    
    // sample standard deviation of percolation threshold
    public double stddev(){
        return 0;
    }       
    
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0;
    }         
    
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0;
    }

    // test client (described below)
    public static void main(String[] args){
        if (args.length == 2) {
            int N = Integer.parseInt(args[0]);
            int T = Integer.parseInt(args[1]);
            
            PercolationStats percolationStats = new PercolationStats(N, T);
            
            StdOut.print("mean                    = " + percolationStats.mean() + "\n");
            StdOut.print("stddev                  = " + percolationStats.stddev() + "\n");
            StdOut.print("95% confidence interval = " + percolationStats.confidenceLo() + " ," + percolationStats.confidenceHi() + "\n");
        }
    }    
}