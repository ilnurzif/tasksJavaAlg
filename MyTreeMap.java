public class MyTreeMap<Key extends Comparable<Key>, Value> {
    private final String notNullErrMsg = "Key не должен быть равен нулю";
    private Node root;
    private int treeHeight = 0;

    private class Node {
        Key key;
        Value value;
        Node leftchild;
        Node rightchild;
        int size;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            size = 1;
        }
    }

    private Value inner_get(Node node, Key key) {
        isKeyNullExcCheck(key);
        if (node == null)
            return null;
        Node tempNode = node;

        int cmd = key.compareTo(node.key);
        if (cmd < 0)
            return inner_get(tempNode.leftchild, key);
        if (cmd > 0)
            return inner_get(tempNode.rightchild, key);
        if (cmd == 0)
            return tempNode.value;
        return null;
    }

    public final Value get(Key key) {
        return inner_get(root, key);
    }

    private int inner_size(Node node) {
        return node == null ? 0 : node.size;
    }

    public final int size() {
        return inner_size(root);
    }

    public Node inner_delete(Node node, Key key) {
        if (node == null)
            return null;
        int cmd = key.compareTo(node.key);
        if (cmd < 0)
            node.leftchild = inner_delete(node.leftchild, key);
        else if (cmd > 0)
            node.rightchild = inner_delete(node.rightchild, key);
        else {
            if (node.leftchild == null)
                return node.rightchild;
            if (node.rightchild == null)
                return node.leftchild;
            Node temp = node;
            node = inner_min(temp.rightchild);
            node.rightchild = inner_deleteMin(temp.rightchild);
            node.leftchild = temp.leftchild;
            node.size = inner_size(node.leftchild) + inner_size(node.rightchild) + 1;
        }
        return node;
    }

    public void delete(Key key) {
        root = inner_delete(root, key);
    }

    private Node inner_deleteMin(Node node) {
        if (node.leftchild == null)
            return node.rightchild;
        node.leftchild = inner_deleteMin(node.leftchild);
        node.size = inner_size(node.rightchild) + inner_size(node.leftchild) + 1;
        return node;
    }

    public void deleteMin() {
        inner_deleteMin(root);
    }

    private void isKeyNullExcCheck(Key key) {
        if (key == null)
            throw new IllegalArgumentException(notNullErrMsg);
    }

    public final void put(Key key, Value value) {
        if (value == null) return;
        root = inner_put(root, key, value);
    }

    private Node inner_put(Node node, Key key, Value value) {
        if (node == null)
            return new Node(key, value);
        int cmd = key.compareTo(node.key);
        if (cmd == 0) node.value = value;
        if (cmd < 0) node.leftchild = inner_put(node.leftchild, key, value);
        if (cmd > 0) node.rightchild = inner_put(node.rightchild, key, value);
        node.size = inner_size(node.leftchild) + inner_size(node.rightchild) + 1;
        return node;
    }

    private Node inner_min(Node node) {
        if (node.leftchild == null)
            return node;
        return inner_min(node.leftchild);
    }

    public Key minKey() {
        return inner_min(root).key;
    }

    public boolean containts(Key key) {
        Value val = inner_get(root, key);
        return val != null ? true : false;
    }

    @Override
    public String toString() {
        return inner_toString(root);
    }

    private String inner_toString(Node node) {
        if (node == null)
            return "";
        return inner_toString(node.leftchild) + "["
                + node.key + "=" + node.value + "]" +
                inner_toString(node.rightchild);
    }

    // Высота дерева
    private int inner_treeHeigh(Node node) {
        return node != null ? Math.max(inner_treeHeigh(node.rightchild), inner_treeHeigh(node.leftchild)) + 1 : 0;
    }

    public final int treeHeigh() {
        return inner_treeHeigh(root);
    }

    // Порверка сбалансированности
    private int inner_checkBalance(Node h) {
        if (h == null) {
            return 1;
        }
        int left = inner_checkBalance(h.leftchild);
        int right = inner_checkBalance(h.rightchild);
        if ((left == 0 || right == 0) || (Math.abs(left - right) > 1)) return 0;
        else return (left + right + 1) / 2 + 1;
    }

    public final boolean isBalanced() {
        return inner_checkBalance(root) > 0 ? true : false;
    }
}
