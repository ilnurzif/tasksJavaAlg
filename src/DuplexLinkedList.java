import java.util.NoSuchElementException;

public class DuplexLinkedList<T> extends LinkedList<T> {
    protected Node last;

    @Override
    public void insertFirst(T val) {
        if (this.isEmpty()) {
            super.insertFirst(val);
            last = first;
            return;
        }
        Node temp = first;
        super.insertFirst(val);
        temp.prev = first;
    }

    @Override
    public boolean remove(T val) {
        if (last.val.equals(val)) {
            last = last.prev;
            last.next = null;
            size--;
        }
        temp = first;
        if (super.remove(val)) {
            if (temp.next != null)
                temp.next.prev = temp;
            return true;
        }
        return false;
    }

    @Override
    public T removeFirst() {
        if (first.next == null)
            last = null;
        return super.removeFirst();
    }

    public T removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Node temp = last;

        if (last != null) {
            last.next = null;
            last = last.prev;
        }

        size--;
        if (size == 0)
            first = null;
        return (T) temp.val;
    }

    public void insertLast(T val) {
        if (this.isEmpty()) {
            insertFirst(val);
            return;
        }
        Node newNode = new Node<>(val, null);
        newNode.prev = last;
        last.next = newNode;
        last = newNode;
        size++;
    }

    @Override
    public void insert(int index, T val) {
        super.insert(index, val);
        if (index == 0) return;
        newNode.prev = temp;
        temp.next = newNode;
        if (newNode.next != null)
            newNode.next.prev = newNode;
        if (index == size - 1)
            last = newNode;
    }
}


