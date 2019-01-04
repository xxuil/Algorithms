package main.java.assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

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
            uf.union(0,i);
        }

        for(int i = (size - n - 1); i < size - 1; i++){
            uf.union(size - 1, i);
        }
    }

    // open site if it is not open yet
    public void open(int row, int col){
        // 1. open the site
        int index = convert(row, col);
        if(open[index] == 0) {
            open[index] = 1;
            openCount += 1;
        }

        // 2. check its neighbors
        // They are, index +- 1 and index +- n
        // 3. connect open neighbors
        int temp;
        if(col != 1){
            temp = index - 1;
            if(temp > 0){
                if(isOpen(temp)){
                    uf.union(index, temp);
                }
            }
        }

        if(col != n){
            temp = index + 1;
            if(temp < size - 1){
                if(isOpen(temp)){
                    uf.union(index, temp);
                }
            }
        }

        temp = index - n;
        if(temp > 0){
            if(isOpen(temp)){
                uf.union(index, temp);
            }
        }

        temp = index + n;
        if(temp < size - 1){
            if(isOpen(temp)){
                uf.union(index, temp);
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
        boolean flag = false;
        if(open[index] == 1){
            int root = uf.find(index);
            if(root == uf.find(0)){
                flag = true;
            }
        }
        return flag;
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
        return ((row - 1) * n) + col;
    }

    // test client
    public static void main(String[] args){

    }
}
