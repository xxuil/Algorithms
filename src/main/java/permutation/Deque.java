package main.java.permutation;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int n;
    private Node first;
    private Node last;

    private class Node{
        Item obj;
        Node next;
        Node prev;
    }


    public Deque(){
        n = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    public void addFirst(Item item){
        if(item == null)
            throw new IllegalArgumentException();
        Node temp = new Node();
        temp.obj = item;
        temp.next = first;
        temp.prev = null;

        if(n == 0){
            first = temp;
            last = temp;
        } else {
            first.prev = temp;
            first = temp;
        }
        n += 1;
    }

    public void addLast(Item item){
        if(item == null)
            throw new IllegalArgumentException();
        Node temp = new Node();
        temp.obj = item;
        temp.prev = last;
        temp.next = null;

        if(n == 0){
            first = temp;
            last = temp;
        } else {
            last.next = temp;
            last = temp;
        }
        n += 1;
    }

    public Item removeFirst(){
//        Node temp = first;
//        Item ret = temp.obj;
//        temp.next.prev = null;
//        first = temp.prev;
//        temp = null;
        if(n > 0){
            Item ret = first.obj;
            first = first.next;
            if(n == 1){
                last = null;
            } else {
                first.prev = null;
            }
            n -= 1;
            return ret;
        } else {
            throw new NoSuchElementException();
        }
    }

    public Item removeLast(){
//        Node temp = last;
//        Item ret = temp.obj;
//        temp.prev.next = null;
//        last = temp.prev;
//        temp = null;
        if(n > 0){
            Item ret = last.obj;
            last = last.prev;

            if(n == 1){
                first = null;
            } else {
                last.next = null;
            }
            n -= 1;
            return ret;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if(!hasNext())
                throw new NoSuchElementException();
            Item ret = current.obj;
            current = current.next;
            return ret;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String toString() {
        String s = "[";
        Node temNode = first;
        for(int i = 0; i < n; i++){
            String tempStr = temNode.obj.toString();
            temNode = temNode.next;
            s = s + " " + tempStr;
        }
        s = s + " ]";
        return s;
    }

    public static void main(String[] args){
        int limit = 5;
        Deque<Integer> d = new Deque<>();

        for(int i = 0; i < limit; i++){
            d.addFirst(i);
        }

        for(Integer i : d){
            System.out.println(i);
        }

        for(int i = 0; i < limit; i++){
            if(d.removeLast() != i)
                System.out.println("ERROR: element not match");
        }

        if(!d.isEmpty())
            System.out.println("ERROR: should be empty");

        for(int i = 0; i < limit; i++){
            d.addLast(i);
        }

        for(Integer i : d){
            System.out.println(i);
        }

        for(int i = 0; i < limit; i++){
            if(d.removeFirst() != i)
                System.out.println("ERROR: element not match");
        }

        if(!d.isEmpty())
            System.out.println("ERROR: should be empty");

        // end of testing
    }
}
