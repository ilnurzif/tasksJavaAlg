public class StackList<T> {
    private LinkedList ll;

    public StackList() {
        ll = new LinkedList<T>();
    }

    public void push(T val) {
        ll.insertFirst(val);
    }

    public T pop() {
        return (T) ll.removeFirst();
    }

    public T peek() {return (T) ll.firstval();}

    public boolean isEmpty() {
        return ll.isEmpty();
    }
}
