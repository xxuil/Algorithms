package main.java.assignment;

import main.java.edu.princeton.cs.algs4.*;

/**
 * Percolation
 * Xiangxing Liu
 * 01/04/2019
 * Coursera Algorithms, Part I, Assignment 1
 */
public class Percolation {
    // variables
    private int size;
    private WeightedQuickUnionUF uf;
    private int open;

    // create n x n grid, with all sites blocked
    public Percolation(int n){
        this.size = n * n;
        this.uf = new WeightedQuickUnionUF(size);
        this.open = 0;
    }

    // open site if it is not open yet
    public void open(int row, int col){

    }

    // is site open?
    public boolean isOpen(int row, int col){
        return false;
    }

    // is site full?
    public boolean isFull(int row, int col){
        return false;
    }

    // number of open sites
    public int numberOfOpenSites(){
        return open;
    }

    // does the system percolate?
    public boolean percolates(){
        return false;
    }

    // convert row and col into index
    private int convert(int row, int col){
        return -1;
    }

    // test client
    public static void main(String[] args){

    }
}
