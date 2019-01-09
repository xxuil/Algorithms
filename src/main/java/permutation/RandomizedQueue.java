package main.java.permutation;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue <E> implements Iterable<E> {
    private int n;
    private E[] objs;

    public RandomizedQueue(){
        n = 0;
        objs = (E[]) new Object[1];
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    private void resize(boolean flag){
        int max;
        if(flag){
            max = objs.length * 2;
        } else {
            max = objs.length / 2;
        }

        E[] temp = (E[]) new Object[max];
        for(int i = 0; i < n; i ++){
            temp[i] = objs[i];
        }
        objs = temp;
    }

    public void enqueue(E item){
        if(item == null)
            throw new IllegalArgumentException();
        if(n == objs.length)
            resize(true);
        objs[n] = item;
        n += 1;
    }

    // return a random one
    public E dequeue(){
        E ret = null;
        if(n == 0)
            throw new NoSuchElementException();
        int i = StdRandom.uniform(n);
        ret = objs[i];
        if(n == 1){
            objs[i] = null;
        } else {
            objs[i] = objs[n - 1];
            objs[n - 1] = null;
        }
        n -= 1;

        if(n > 0 && n == objs.length / 4)
            resize(false);

        return ret;
    }

    public E sample(){
        if(n == 0)
            throw new NoSuchElementException();
        return objs[StdRandom.uniform(n)];
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
        private int[] index;
        private int count;

        public QueueIterator(){
            index = new int[n];
            for(int i = 0; i < index.length; i++){
                index[i] = i;
            }

            count = index.length;
            for(int i = (count - 1); i > 0; i--){
                int rand = StdRandom.uniform(i + 1);
                int a = index[rand];
                index[rand] = index[i];
                index[i] = a;
            }
        }

        @Override
        public boolean hasNext() {
            return count != 0;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            count -= 1;
            return objs[index[count]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public String toString() {
        String s = "[";
        for(int i = 0; i < objs.length; i++){
            if(objs[i] != null){
                String tempStr = objs[i].toString();
                s = s + " " + tempStr;
            }
        }
        s = s + " ]";
        return s;
    }

    public static void main(String[] args){
        int limit = 10;
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        for(int i = 0; i < limit; i++){
            rq.enqueue(i);
        }

        System.out.println(rq);

        for(Integer i: rq){
            System.out.println(i);
        }
    }
}
