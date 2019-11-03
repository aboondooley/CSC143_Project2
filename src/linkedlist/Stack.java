package linkedlist;

public class Stack<T> {
    private LinkedList<T> list;
    private int size;

    public Stack() {
        this.list = new LinkedList<>();
        this.size = 0;
    }

    public void push(T value) {
        //
        list.pushFront(value);
        size++;
    }

    public T pop() {
        T result = list.popFront();
        size--;
        return result;
    }

    public T peek() {
        T result = list.peekFront();
        return result;
    }

    public int size() {
        return this.size;
    }
}
