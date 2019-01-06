package main.java.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /*
     * Solve the grid given the start and end points
     */
    public static String solve(int[] start, int[] end, int[][] grid){
        ArrayList<ArrayList<int[]>> rList = new ArrayList<>();
        ArrayList<int[]> route = new ArrayList<>();
        if(!recursiveSolve(start, end, grid, route))
            return "no way out";
        else{
            return "";
        }
    }

    public static boolean recursiveSolve(int[] a, int[] b, int[][] grid, List<int[]> route){
        // check if arrive
        if(check(a, b)){
            route.add(b);
            return true;
        }

        if(grid[a[0]][a[1]] == 2){
            return false;
        }
        // check if blocked
        if(grid[a[0]][a[1]] == 1){
            return false;
        }

        // if not block, keep moving
        // store current position
        grid[a[0]][a[1]] = 2;
        route.add(a);

        // move!
        boolean flag = false;
        int row = a[0] - 1;
        if(row >= 0){
            int[] a1 = {row, a[1]};
            if(recursiveSolve(a1,b, grid, route)){
                return true;
            }
        }

        row = a[0] + 1;
        if(row < grid.length){
            int[] a1 = {row, a[1]};
            if(recursiveSolve(a1,b, grid, route)){
                return true;
            }
        }

        int col = a[1] - 1;
        if(col >= 0){
            int[] a1 = {a[0], col};
            if(recursiveSolve(a1,b, grid, route)){
                return true;
            }
        }

        col = a[1] + 1;
        if(col < grid[0].length){
            int[] a1 = {a[0], col};
            if(recursiveSolve(a1,b, grid, route)){
                return true;
            }
        }

        route.remove(a);
        return false;
    }

    /*
     * Check if position a == position b
     */
    public static boolean check(int[] a, int[] b){
        if(a[0] == b[0]){
            if(a[1] == b[1])
                return true;
        }
        return false;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = 0;
        int col = 0;
        int bCount = 0;
        int[][] grid;
        while(sc.hasNext()){
            // read input
            String s = sc.nextLine();
            String[] ss = s.split(" ");

            // check length
            if(ss.length < 4)
                throw new IllegalArgumentException("Wrong input String");

            bCount = Integer.valueOf(ss[3]);
            if(bCount != ss.length - 4)
                throw new IllegalArgumentException("Input does not match");

            // read dimensions
            String[] temp = ss[0].split(",");
            row = Integer.valueOf(temp[0]) + 1;
            col = Integer.valueOf(temp[1]) + 1;

            if(bCount > (row * col))
                throw new IllegalArgumentException("Input does not match");

            // construct the grid
            grid = new int[row][];
            for(int i = 0; i < row; i++){
                grid[i] = new int[col];
            }

            // fill the blocks
            for(int i = 4; i < ss.length; i++){
                temp = ss[i].split(",");
                int r = Integer.valueOf(temp[0]);
                int c = Integer.valueOf(temp[1]);
                grid[r][c] = 1;
            }

            // start and end
            int[] start = new int[2];
            int[] end = new int[2];
            temp = ss[1].split(",");
            start[0] = Integer.valueOf(temp[0]);
            start[1] = Integer.valueOf(temp[1]);
            temp = ss[2].split(",");
            end[0] = Integer.valueOf(temp[0]);
            end[1] = Integer.valueOf(temp[1]);

            // start the recursive helper function
            System.out.println(solve(start, end, grid));
        }
    }
}