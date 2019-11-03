package linkedlist;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {
    private class ListNode<T> {
        private T payload;
        private ListNode<T> next;
        private ListNode<T> prev;

        public ListNode(T v) {
            this.payload = v;
        }
    }

    protected ListNode<T> frontNode;
    protected ListNode<T> backNode;
    protected int size;

    public LinkedList() {
        this.frontNode=null;
        this.backNode=null;
        this.size=0;
    }

    public LinkedList(Iterable<? extends T> c) {
        /* YOUR CODE HERE */

       for (T value : c) {
            if (frontNode == null && backNode == null) {
                frontNode = new ListNode<T>(value);
                backNode = frontNode;
            } else {
                backNode.next = new ListNode<T>(value);
                ListNode<T> temp = backNode;
                backNode = backNode.next;
                backNode.prev = temp;
            }
            ++size;
        }

    }

    public T peekFront() {
        /* YOUR CODE HERE */
        if (frontNode==null){
            return null;
        }
        return this.frontNode.payload;


    }

    public T peekBack() {
        /* YOUR CODE HERE */
        if (backNode==null){
            return null;
        }
        return this.backNode.payload;
    }

    public T popFront() {
        //
        if (frontNode == null){
            throw new NoSuchElementException();
        }
        T result = frontNode.payload;
        if (this.size==1){
            frontNode = null;
            backNode = null;
        } else {
            ListNode<T> temp = frontNode; // mark to return
            frontNode = frontNode.next;
            temp.next = null;
            frontNode.prev = null;
        }
        size--;
        return result;
    }

    public T popBack() {
        //
        if (frontNode == null){
            throw new NoSuchElementException();
        }
        T result = backNode.payload;
        if (this.size==1){
            frontNode = null;
            backNode = null;
        } else {
            ListNode<T> temp = backNode; // mark to return
            backNode = backNode.prev;
            temp.prev = null; // detach the first node from the list
            backNode.next = null; // detach from the
        }
        size--;
        return result;
    }

    public void pushBack(T value) {
        //
        ListNode<T> newNode = new ListNode<T>(value);
       if (frontNode==null){
            backNode = newNode;
            frontNode = backNode;
            size++;
        } else {
           newNode.prev = backNode;
           backNode.next = newNode;
           backNode= newNode;
           size++;
       }

    }

    public void pushFront(T value) {
        //
        ListNode<T> newNode = new ListNode<T>(value);
        if (frontNode==null){
            frontNode = newNode;
            backNode = frontNode;
            size++;
        } else {
            newNode.next = frontNode;
            frontNode.prev = newNode;
            frontNode = newNode;
            size++;
        }

    }

    public void add(T value) {
        //
        this.pushFront(value);
    }

    public void add(int index, T value) {
        //
        if (index==0){
            pushFront(value);
        } else if (index==this.size()){
            pushBack(value);
        } else  if (index < 0 | index >= this.size) {
            throw new IndexOutOfBoundsException();
        } else {
            int counter = 1;
            ListNode<T> currentNode = frontNode;
            ListNode<T> newNode = new ListNode<>(value);
            while (counter < index) {
                currentNode = currentNode.next;
                counter++;
            }
            newNode.prev = currentNode;
            newNode.next = currentNode.next;
            newNode.prev.next = newNode;
            newNode.next.prev = newNode;
            size++;
        }
    }

    public T remove() {
        //
        T result = popFront();
        return result;
    }

    public T remove(int index) {
        //
        T result;
        if (index == 0){
            result = popFront();
        } else if (index == this.size-1){
            result = popBack();
        } else if (index < 0 | index >= this.size){
            throw new IndexOutOfBoundsException();
        } else {
            int counter = 1;
            ListNode<T> currentNode = frontNode;
            while (counter <= index) {
                currentNode = currentNode.next;
                counter++;
            }
            result = currentNode.payload;
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            size--;
        }
        return result;
    }

    private void remove(ListNode<T> node) {
        /* YOUR CODE HERE */
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        /* YOUR CODE HERE */
        return new LinkedListIterator();
    }

    public class LinkedListIterator implements Iterator<T> {
        /* YOUR CODE HERE */
        private ListNode<T> current = frontNode;
        @Override
        public boolean hasNext() {
            // We will keep moving the frontNode along so if frontNode is
           return current != null;
        }

        @Override
        public T next() {
            // Move through the nodes, starting at the front
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            T result = current.payload;
            current = current.next;
            return result;
        }

        @Override
        public void remove() {
            //
            if (size==0){
                throw new NoSuchElementException();
            }
            if (current == frontNode){
                throw new IllegalStateException();
            }
            if (size==1){
                //current = null;
                backNode = null;
                frontNode = null;
            } else if (current == null) {
                current = backNode;
                backNode = backNode.prev;
                backNode.next = null;
                current.prev = null;
            } else if (current.prev == frontNode) {
                current = current.prev;
                frontNode = frontNode.next;
                current.next = null;
                frontNode.prev = null;
                current = frontNode;
            } else {
                current = current.prev;
                ListNode<T> marker = current;
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current = current.next;
                marker.prev = null;
                marker.next = null;
            }
            size--;
        }
    }

    public Iterator<T> reverseIterator() {
        /* YOUR CODE HERE */
        return new LinkedListReverseIterator();
    }

    public class LinkedListReverseIterator implements Iterator<T> {
        /* YOUR CODE HERE */
        ListNode<T> current = backNode;
        @Override
        public boolean hasNext() {
            /* YOUR CODE HERE */
            return current!=null;
        }

        @Override
        public T next() {
            //
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            T result = current.payload;
            current = current.prev;
            return result;
        }

        @Override
        public void remove() {
            //
            if (size==0){
                throw new NoSuchElementException();
            }
            if (current == backNode){
                throw new IllegalStateException();
            }
            if (size==1){
                //current = null;
                backNode = null;
                frontNode = null;
            } else if (current == null) {
                current = frontNode;
                frontNode = frontNode.next;
                frontNode.prev = null;
                current.next = null;
            } else if (current.next == backNode){
                current = current.next;
                backNode = backNode.prev;
                current.prev = null;
                backNode.next = null;
                current = backNode;
            } else {
                current = current.next;
                ListNode<T> marker = current;
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current = current.prev;
                marker.prev = null;
                marker.next = null;
            }
            size--;
        }
    }


    // toString requires Iterator to be partially implemented to function
    // as it uses the for-each loop construct
    @Override
    public String toString() {
        String result = "[";

        for (T value : this) {
            result += String.format("%s, ", value.toString());
        }

        if (result.length() > 1) {
            result = result.substring(0, result.length() - 2);
        }

        result += "]";
        return result;
    }
}
