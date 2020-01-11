import java.util.NoSuchElementException;

public class MyListIterator<T> {
    private IteratorLinkedList ll;

    public MyListIterator() {
        ll = new IteratorLinkedList<T>();
    }

    private class IteratorLinkedList<T> extends DuplexLinkedList<T> {
        public Node currentEl = null;
        private int currentIndex = 0;

        private T next() {
            if (isEmpty() || currentEl.next == null) {
                throw new NoSuchElementException();
            }
            if (currentIndex++ == 0)
                currentEl = first;
            else
                currentEl = currentEl.next;
            return (T) currentEl.val;
        }

        private T previous() {
            if (isEmpty() || currentIndex == 0) {
                throw new NoSuchElementException();
            }
            Node temp = currentEl;
            currentEl = currentEl.prev;
            currentIndex--;
            return (T) temp.val;
        }

        public void add(T val) {
            if (!isEmpty()) {
                int index = index((T) currentEl.val);
                insert(index + 1, val);
            } else {
                insertFirst(val);
                currentEl = first;
            }
        }

        public boolean hasNext() {
            return currentEl.next != null;
        }

        public boolean hasPrevios() {
            return currentIndex != -0;
        }

        public int nextIndex() {
            return (currentIndex + 1 > size) ? size : currentIndex;
        }

        public int previousIndex() {
            return (currentIndex == 0) ? -1 : currentIndex - 1;
        }

        public void removeCurrent() {
            if (currentIndex == 0 || currentIndex > size || currentEl == null)
                throw new IllegalStateException();
            if (currentEl.equals(last)) {
                removeLast();
                currentIndex--;
                return;
            }
            if (currentEl.equals(first)) {
                removeFirst();
                return;
            }
            Node temp = currentEl.next;
            temp.prev = currentEl.prev;
            currentIndex = 0;
            currentEl = first;
        }

        public void set(T el) {
            currentEl.val = el;
        }
    }

    public void add(T el) {
        ll.add(el);
    }

    public boolean hasNext() {
        return ll.hasNext();
    }

    public boolean hasPrevious() {
        return ll.hasPrevios();
    }

    public T next() {
        return (T) ll.next();
    }

    public T previous() {
        return (T) ll.previous();
    }

    public int nextIndex() {
        return ll.nextIndex();
    }

    public int previousIndex() {
        return ll.previousIndex();
    }

    public void remove() {
        ll.removeCurrent();
    }

    public void set(T el) {
        ll.set(el);
    }
}
