public class MyLinearMap<Key, Value> {
    private int capacity = 97;
    private int size = 0;
    private Integer deletedEl = -1;

    private Key[] keys = (Key[]) new Object[capacity];
    private Value[] values = (Value[]) new Object[capacity];

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public final int hash(Key key) {
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
        isKeyNotNull(key);
        if (size == capacity - 1) {
            throw new RuntimeException("Места нет");
        }
        if (key.equals(deletedEl))
            throw new IllegalArgumentException("Недопустимое значение ключа");
        int j;
        for (j = hash(key); keys[j] != null; j = (j + 1) % capacity) {
            if (keys[j].equals(key)) {
                values[j] = value;
            }
        }
        keys[j] = key;
        values[j] = value;
        size++;
    }

    public boolean remove(Key key) {
        int numb = getValKey(key);
        if (numb != -1) {
            values[numb] = (Value) deletedEl;
            return true;
        }
        return false;
    }

    public Value get(Key key) {
        int numb = getValKey(key);
        return numb != -1 ? values[numb] : null;
    }

    private int getValKey(Key key) {
        isKeyNotNull(key);
        for (int i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (!values[i].equals(deletedEl))
                if (keys[i].equals(key)) {
                    return i;
                }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            sb.append(keys[i]).append(", ");
        }
        return sb.toString();
    }

}
