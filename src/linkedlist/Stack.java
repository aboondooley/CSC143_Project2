package linkedlist;

public class Stack<T> {
    private LinkedList<T> list;

    public Stack() {
        this.list = new LinkedList<>();
    }

    public void push(T value) {
        // place on the top of the stack
        list.pushFront(value);
    }

    public T pop() {
        // take from the top of the stack
        return list.popFront();
    }

    public T peek() {
        // peek at the top of the stack
        return list.peekFront();
    }

    public int size() {
        return list.size();
    }
}
