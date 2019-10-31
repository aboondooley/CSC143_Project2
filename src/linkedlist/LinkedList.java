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
        // this.frontNode=null;
        // this.backNode=null;
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
        if (this.size==0){
            return null;
        }
        return this.frontNode.payload;


    }

    public T peekBack() {
        /* YOUR CODE HERE */
        if (this.size==0){
            return null;
        }
        return this.backNode.payload;
    }

    public T popFront() {
        //
        if (frontNode == null){
            throw new NoSuchElementException();
        }
        ListNode<T> temp = frontNode; // mark to return
        frontNode = frontNode.next;
        temp.next = null; // detach the first node from the list
        frontNode.prev = null; // detach from the
        --size;
        return temp.payload;
    }

    public T popBack() {
        //
        if (frontNode == null){
            throw new NoSuchElementException();
        }
        ListNode<T> temp = backNode; // mark to return
        backNode = backNode.prev;
        temp.prev = null; // detach the first node from the list
        backNode.next = null; // detach from the
        --size;
        return temp.payload;

    }

    public void pushBack(T value) {
        //

        size++;
    }

    public void pushFront(T value) {
        /* YOUR CODE HERE */
    }

    public void add(T value) {
        /* YOUR CODE HERE */
    }

    public void add(int index, T value) {
        /* YOUR CODE HERE */
    }

    public T remove() {
        /* YOUR CODE HERE */
        return null;
    }

    public T remove(int index) {
        /* YOUR CODE HERE */
        return null;
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
        @Override
        public boolean hasNext() {
            // We will keep moving the frontNode along so if frontNode is
           return frontNode != null;
        }

        @Override
        public T next() {
            // Move through the nodes, starting at the front
            if (frontNode == null){
                throw new NoSuchElementException();
            }
            //ListNode<T> temp = frontNode; // mark to return
            //temp.next = null; // detach the first node from the list
            frontNode = frontNode.next;
            //frontNode.prev = null; // detach from the
            //--size;
            return frontNode.prev.payload;
        }

        @Override
        public void remove() {
            //
            if (frontNode == null){
                throw new NoSuchElementException();
            }
            frontNode = frontNode.next;
            --size;
            frontNode.prev = null;
        }
    }

    public Iterator<T> reverseIterator() {
        /* YOUR CODE HERE */
        return new LinkedListReverseIterator();
    }

    public class LinkedListReverseIterator implements Iterator<T> {
        /* YOUR CODE HERE */

        @Override
        public boolean hasNext() {
            /* YOUR CODE HERE */
            return backNode!=null;
        }

        @Override
        public T next() {
            //
            if (frontNode == null){
                throw new NoSuchElementException();
            }
            //ListNode<T> temp = frontNode; // mark to return
            //temp.next = null; // detach the first node from the list
            backNode = backNode.prev;
            //frontNode.prev = null; // detach from the
            //--size;
            return backNode.next.payload;
        }

        @Override
        public void remove() {
            //
            if (backNode == null){
                throw new NoSuchElementException();
            }
            backNode = backNode.prev;
            --size;
            backNode.next = null;
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
