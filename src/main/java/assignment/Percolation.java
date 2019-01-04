package main.java.assignment;

import edu.princeton.cs.algs4.*;

/**
 * Percolation
 * Xiangxing Liu
 * 01/04/2019
 * Coursera Algorithms, Part I, Assignment 1
 */
public class Percolation {
    // variables
    private int n;
    private int size;
    private WeightedQuickUnionUF uf;
    private int open[];                 // 0 ==> close, 1 ==> open
    private int openCount;

    // create n x n grid, with all sites blocked
    public Percolation(int n){
        this.n = n;
        this.size = n * n + 2;
        this.uf = new WeightedQuickUnionUF(size);
        this.openCount = 0;
        this.open = new int[size];

        for(int i = 0; i < size; i++){
            open[i]= 0;
        }

        for(int i = 1; i < n + 1; i++){
            uf.union(i,0);
        }

        for(int i = (size - n - 1); i < size - 1; i++){
            uf.union(i, size - 1);
        }
    }

    // open site if it is not open yet
    public void open(int row, int col){
        // 1. open the site
        int index = convert(row, col);
        if(open[index] == 0)
            open[index] = 1;

        // 2. check its neighbors
        // They are, index +- 1 and index +- n
        // 3. connect open neighbors
        if(index - 1 > 0){
            if(isOpen(index - 1)){
                uf.union(index, index - 1);
            }
        }

        if(index + 1 < size - 1){
            if(isOpen(index + 1)){
                uf.union(index, index + 1);
            }
        }

        if(index - n > 0){
            if(isOpen(index - n)){
                uf.union(index, index - n);
            }
        }

        if(index + n > size - 1){
            if(isOpen(index + n)){
                uf.union(index, index + n);
            }
        }
    }

    // is site open?
    public boolean isOpen(int row, int col){
        int index = convert(row, col);
        return open[index] == 1;
    }

    private boolean isOpen(int index){
        return open[index] == 1;
    }

    // is site full?
    public boolean isFull(int row, int col){
        int index = convert(row, col);
        return open[index] == 0;
    }

    // number of open sites
    public int numberOfOpenSites(){
        return openCount;
    }

    // does the system percolate?
    public boolean percolates(){
        return uf.connected(0, size - 1);
    }

    // convert row and col into index
    private int convert(int row, int col){
        if((row < 1)||(row > n)){
            throw new IllegalArgumentException();
        }

        if((col < 1)||(col > n)){
            throw new IllegalArgumentException();
        }
        return -1;
    }

    // test client
    public static void main(String[] args){

    }
}
