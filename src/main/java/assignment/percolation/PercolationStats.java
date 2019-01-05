package main.java.assignment.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
/**
 * PercolationStats
 * Xiangxing Liu
 * 01/04/2019
 * Coursera Algorithms, Part I, Assignment 1
 */
public class PercolationStats {

    private int T;
    private double count[];

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n < 1 || trials < 1){
            throw new IllegalArgumentException();
        }

        this.count = new double[trials];
        int range = n;
        this.T = trials;

        for(int i = 0; i < T; i++){
            Percolation p = new Percolation(n);
            while(!p.percolates()){
                int randomRow = StdRandom.uniform(range) + 1;
                int randomCol = StdRandom.uniform(range) + 1;
                p.open(randomRow, randomCol);
            }
            count[i] = p.numberOfOpenSites() /(double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(count);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(count);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - (1.96d * stddev()) / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + (1.96d * stddev()) / Math.sqrt(T);
    }

    // test client (described below)
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats ps = new PercolationStats(n, T);
        StdOut.println("mean =" + ps.mean());
        StdOut.println("std =" + ps.stddev());
        StdOut.println("lo =" + ps.confidenceLo());
        StdOut.println("hi =" + ps.confidenceHi());
    }
}
