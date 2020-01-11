import java.util.NoSuchElementException;

public class LinkedList<T> {
    protected Node first;
    protected int size = 0;
    protected Node newNode;
    protected Node temp;

    protected class Node<T> {
        protected Node next;
        protected Node prev; // для двухстронний очереди
        protected T val;

        public Node(T val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(T val) {
        Node temp = first;
        first = new Node(val, temp);
        size++;
    }

    public T removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        Node temp = first;
        first = first.next;
        size--;
        return (T) temp.val;
    }

    public int indexOf(T val) {
        return index(val);
    }

    protected final int index(T val) {
        int ind = 0;
        Node temp = first;
        while (temp != null) {
            if (temp.val.equals(val)) {
                return ind;
            }
            temp = temp.next;
            ind++;
        }
        return -1;
    }

    public boolean constaint(T val) {
        return (index(val) != -1);
    }

    public boolean remove(T val) {
        if (isEmpty())
            return false;
        if (first.val.equals(val)) {
            removeFirst();
            return true;
        }
        temp = first;
        while (temp.next != null && !temp.next.val.equals(val)) {
            temp = temp.next;
        }
        if (temp.next == null) {
            return false;
        }
        temp.next = temp.next.next;
        size--;
        return true;
    }

    public void insert(int index, T val) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            insertFirst(val);
            return;
        }
        temp = first;
        int ind = 1;

        while (ind++ < index) {
            temp = temp.next;
        }
        newNode = new Node(val, temp.next);
        temp.next = newNode;
        size++;
    }

    public T firstval() {
        return (T) first.val;
    }
}
