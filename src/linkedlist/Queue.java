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
        this.pushBack(value);
    }

    @Override
    public T remove() {
        return this.popFront();
    }

    public T peek() {
        return this.peekFront();
    }

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
