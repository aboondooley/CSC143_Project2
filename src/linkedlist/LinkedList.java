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

       for (T value : c) {
            if (frontNode == null && backNode == null) {
                frontNode = new ListNode<T>(value); // If empty, creates the first node
                backNode = frontNode; // Both frontNode and backNode are set to this node at first
            } else { // If the list is not empty, add the other elements and move backNode along!
                backNode.next = new ListNode<T>(value);
                ListNode<T> temp = backNode;
                backNode = backNode.next;
                backNode.prev = temp;
            }
            ++size;
        }

    }

    public T peekFront() {
        // Takes a look at the first node in the list
        if (frontNode==null){
            return null; // returns null if the list is empty
        }
        return this.frontNode.payload; // otherwise returns the value of the first node
    }

    public T peekBack() {
        // Takes a look at the back node in the list
        if (backNode==null){
            return null; // returns null if the list is empty
        }
        return this.backNode.payload; // otherwise returns the value of the last node in the list
    }

    public T popFront() {
        // Takes off and returns the first value in the list
        if (frontNode == null){ // If the list is empty
            throw new NoSuchElementException();
        }
        T result = frontNode.payload; // Save to return
        if (this.size==1){ // If this is the last element of the list
            frontNode = null; // Set both front and back nodes to null
            backNode = null; // Empties the list
        } else { // If the list size is not 1 or fewer
            ListNode<T> temp = frontNode; // Mark to return
            frontNode = frontNode.next; // Move frontNode forward one
            temp.next = null; // detach the previous front node
            frontNode.prev = null; // detach final tie from the previous front node
        }
        size--;
        return result;
    }

    public T popBack() {
        // Take off and return the value at the back of the list
        if (frontNode == null){ // If the list is empty
            throw new NoSuchElementException();
        }
        T result = backNode.payload;
        if (this.size==1){ // If this is the last element of the list
            frontNode = null;
            backNode = null;
        } else { // If the list is size 2 or greater
            ListNode<T> temp = backNode; // mark to return
            backNode = backNode.prev;
            temp.prev = null; // detach the first node from the list
            backNode.next = null; // detach from the previous backNode
        }
        size--;
        return result;
    }

    public void pushBack(T value) {
        // Adds value to the back of the list
        ListNode<T> newNode = new ListNode<T>(value);
       if (frontNode==null){ // If the list is empty
            backNode = newNode;
            frontNode = backNode;
        } else { // If the list if not empty
           newNode.prev = backNode; // Need to incorporate the new node as the backNode
           backNode.next = newNode;
           backNode= newNode;
       }
        size++;
    }

    public void pushFront(T value) {
        // Adds to the value to the front of the list
        ListNode<T> newNode = new ListNode<T>(value);
        if (frontNode==null){ // If the list is empty
            frontNode = newNode;
            backNode = frontNode;
        } else { // If the list is not empty
            newNode.next = frontNode; // Need to incorporate the new node as the frontNode
            frontNode.prev = newNode;
            frontNode = newNode;
        }
        size++;
    }

    public void add(T value) {
        // Adds exclusively to the front of the list
        this.pushFront(value);
    }

    public void add(int index, T value) {
        // This operation is O(n) because worst case we need to step through each element in the list
        if (index==0){ // If we are adding to the front
            pushFront(value); // we can just pop from the front
        } else if (index==this.size()){ // If we are adding to the back
            pushBack(value); // we can just pop from the back
        } else  if (index < 0 | index >= this.size) { // If the index is negative or past the last index in the list
            throw new IndexOutOfBoundsException();
        } else { // else, if adding to the middle of the list
            int counter = 1; //
            ListNode<T> currentNode = frontNode;
            ListNode<T> newNode = new ListNode<>(value);
            while (counter < index) {
                currentNode = currentNode.next;
                counter++;
            }
            // Once at the index, reconfigure the references to include the new node
            newNode.prev = currentNode;
            newNode.next = currentNode.next;
            newNode.prev.next = newNode;
            newNode.next.prev = newNode;
            size++;
        }
    }

    public T remove() {
        // removes from the front of the list and returns the value
        return popFront();

    }

    public T remove(int index) {
        // Removes the element at the given index (and returns the value)
        // This is also O(n) because the worst case scenario would be to iterate through each element
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
            // Once at the correct index, save the value to return and rearrange the references to exclude the removed node
            result = currentNode.payload;
            currentNode.prev.next = currentNode.next;
            currentNode.next.prev = currentNode.prev;
            size--;
        }
        return result;
    }

    private void remove(ListNode<T> node) {
        // Not needed.

    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public class LinkedListIterator implements Iterator<T> {
        // Begin at the frontNode
        private ListNode<T> current = frontNode;
        @Override
        public boolean hasNext() {
            // We will keep moving the frontNode along
            // So current is actually once step before us
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
            // Removes the last value that was returned by .next()
            if (size==0){ // If the LinkedList is empty
                throw new NoSuchElementException();
            }
            if (current == frontNode){ // If next has not been called yet
                throw new IllegalStateException();
            }
            if (size==1){ // If this is the last node, set the front and back nodes to zero
                backNode = null; frontNode = null;
            } else if (current == null) { // If removing the last element in the list
                current = backNode; backNode = backNode.prev;
                backNode.next = null; current.prev = null;
            } else if (current.prev == frontNode) { // If removing the first node
                current = current.prev; frontNode = frontNode.next;
                current.next = null; frontNode.prev = null;
                current = frontNode;
            } else { // if removing anywhere else in the list
                current = current.prev;
                ListNode<T> marker = current;
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current = current.next;
                marker.prev = null; marker.next = null;
            }
            size--;
        }
    }

    public Iterator<T> reverseIterator() {
        return new LinkedListReverseIterator();
    }

    public class LinkedListReverseIterator implements Iterator<T> {
        // Start at the backNode
        ListNode<T> current = backNode;
        @Override
        public boolean hasNext() {
            // Current will be null if
            return current!=null;
        }

        @Override
        public T next() {
            // If on the last node, there is no 'next' to return
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            T result = current.payload;
            // Always move current to the next node
            current = current.prev;
            return result;
        }

        @Override
        public void remove() {
            // removing during iteration
            // Always returns the last element that was returned with the 'next' method
            if (size==0){
                throw new NoSuchElementException();
            }
            if (current == backNode){
                throw new IllegalStateException();
            }
            if (size==1){
                backNode = null; frontNode = null;
            } else if (current == null) {
                current = frontNode;
                frontNode = frontNode.next;
                frontNode.prev = null; current.next = null;
            } else if (current.next == backNode){
                current = current.next; backNode = backNode.prev;
                current.prev = null; backNode.next = null;
                current = backNode;
            } else {
                current = current.next;
                ListNode<T> marker = current;
                current.prev.next = current.next;
                current.next.prev = current.prev;
                current = current.prev;
                marker.prev = null; marker.next = null;
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
