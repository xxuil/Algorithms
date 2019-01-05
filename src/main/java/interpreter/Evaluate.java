package main.java.interpreter;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

public class Evaluate {
    public static void main(String[] args){
        Stack<String> ops = new Stack<>();
        Stack<Integer> vals = new Stack<>();

        while(!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(s.equals("("));
            else if(s.equals("+"));
        }
    }
}
