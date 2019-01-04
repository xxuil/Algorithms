package main.java.assignment;

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

    private Percolation p;
    private double count[];

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        if(n < 1 || trials < 1){
            throw new IllegalArgumentException();
        }
        this.p = new Percolation(n);
        this.count = new double[trials];
        int range = n;
        int T = trials;

        for(int i = 0; i < T; i++){
            while(true){
                int randomRow = StdRandom.uniform(range) + 1;
                int randomCol = StdRandom.uniform(range) + 1;
                p.open(randomRow, randomCol);
                if(p.percolates()){
                    count[i] = p.numberOfOpenSites() /(double) (n * n);
                    break;
                }
            }
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
        double mean = mean();
        double std = stddev();

        double lo = mean - (1.96d * std) / Math.sqrt((double) count.length);
        return lo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        double mean = mean();
        double std = stddev();

        double hi = mean + (1.96d * std) / Math.sqrt((double) count.length);
        return hi;
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
