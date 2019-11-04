package linkedlist;

import javax.naming.OperationNotSupportedException;
import javax.swing.*;
import java.util.Iterator;

public class Queue<T> extends LinkedList<T> {

    public Queue(){
        super();
    }

    @Override
    public void add(T value) {
        // add to the back of the line
        this.pushBack(value);
    }

    @Override
    public T remove() {
        // take from the front of the line
        return  super.popFront();
    }

    public T peek() {
        // peek at the front of the line
        return super.peekFront();
    }

    // Override all of these to throw a Run time exception
    @Override
    public void add(int index, T value) {
        throw new RuntimeException();
    }

    @Override
    public T remove(int index) {
        throw new RuntimeException();
    }

    @Override
    public T peekFront() {
        throw new RuntimeException();
    }

    @Override
    public T peekBack() {
        throw new RuntimeException();
    }

    @Override
    public T popFront() {
        throw new RuntimeException();
    }

    @Override
    public T popBack(){
        throw new RuntimeException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new RuntimeException();
    }

    @Override
    public Iterator<T> reverseIterator(){
        throw new RuntimeException();
    }

    }
