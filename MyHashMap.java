import java.util.LinkedList;

public class MyHashMap<Key, Value> {
    private int capacity;
    private int size = 0;

    private LinkedList<Node>[] st;

    public MyHashMap() {
        capacity = 7;
        st = new LinkedList[capacity];
        for (int i = 0; i < st.length; i++) {
            st[i] = new LinkedList<>();
        }
    }

    private class Node {
        private Value value;
        private Key key;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public boolean remove(Key key) {
        if (!contains(key))
            return false;
        int i = hash(key);
        for (Node node : st[i])
            if (key.equals(node.key))
                return st[i].remove(node);
        return false;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public final int hash(Key key) {
        isKeyNotNull(key);
        return (key.hashCode() & 0xfffffff) % capacity;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    private boolean isKeyNotNull(Key key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public void put(Key key, Value value) {
        int i = hash(key);
        for (Node node : st[i]
        ) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        st[i].addLast(new Node(key, value));
        size++;
    }


    public Value get(Key key) {
        int i = hash(key);
        for (Node node : st[i]
        ) {
            if (key.equals(node.key))
                return node.value;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            for (Node node : st[i]
            ) {
                sb.append(node.key).append(", ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}

