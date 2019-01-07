package main.java.datastructure;

import java.util.Collection;
import java.util.Iterator;

public class LinkedList<E> {
    private class Node {
        E obj;
        Node prev;
        Node next;
    }

    public LinkedList(){

    }

    public static void main(String[] args){
        LinkedList<Integer> list = new LinkedList<>();
    }
}
