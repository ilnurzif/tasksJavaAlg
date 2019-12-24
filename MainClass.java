package com.example.examplelib;


import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.stream.IntStream;

public class MainClass {
    public static void main(String[] args) {

        int storage_size = 5;
// Проверка работы стека
        //  MyStack <Integer> myStack=new MyStack<>(5,StorageFullStrategy.STORAGEESIZE_STRATEGY);
        MyStack<Integer> myStack = new MyStack<>(storage_size);
        for (int i = 1; i <= storage_size; i++)
            myStack.push(new Integer(i));
        while (!myStack.isEmpty()) {
            System.out.println(myStack.pop());
        }

// Проверка работы очереди
        MyQueue<Integer> myQueue = new MyQueue<Integer>(storage_size);
        for (int i = 1; i <= storage_size; i++) {
            myQueue.insert(i);
        }
        while (!myQueue.isEmpty()) {
            System.out.println(myQueue.remove());
        }

// Проверка работы приритетной очереди
        MyPriorityQueue<Integer> myPriorityQueue = new MyPriorityQueue<>(storage_size);
        myPriorityQueue.insert(7);
        myPriorityQueue.insert(4);
        myPriorityQueue.insert(2);
        myPriorityQueue.insert(3);
        myPriorityQueue.insert(9);

        while (!myPriorityQueue.isEmpty()) {
            System.out.println(myPriorityQueue.delete());
        }

// Проверка работы ReversArr переворачиваем строку
        String str = "123456";
        Character[] ChArray = IntStream.range(0, str.toCharArray().length)
                .mapToObj(i -> str.toCharArray()[i])
                .toArray(Character[]::new);

        ReverceArr<Character> charArr = new ReverceArr<Character>(ChArray);
        Object[] ch = charArr.reverceArr();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ch.length; i++) {
            sb.append(ch[i]);
        }
        System.out.println(sb);

// Провека работы DoubleDeque
        MyDoubleQueue<Integer> myDoubleQueue = new MyDoubleQueue<Integer>(storage_size);
        for (int i = 1; i <= storage_size; i++) {
            myDoubleQueue.insertRight(i);
        }
        //        режим очереди
        while (!myDoubleQueue.isEmpty()) {
            System.out.println(myDoubleQueue.removeLeft());
        }

        //       режим стека
        for (int i = 1; i <= storage_size; i++) {
            myDoubleQueue.insertRight(i);
        }

        while (!myDoubleQueue.isEmpty()) {
            System.out.println(myDoubleQueue.removeRight());
        }

    }
}

enum StorageFullStrategy {
    ERROR_STRATEGY,
    STORAGEESIZE_STRATEGY
}

/////////////////////////// class MyStack
class MyStack<T> {
    protected int size = 0;
    protected T[] arr;
    protected int currPos = -1;
    protected StorageFullStrategy storageFullStrategy = StorageFullStrategy.ERROR_STRATEGY;

    public MyStack(int size) {
        this.size = size;
        arr = (T[]) new Object[size];
    }

    public MyStack(int size, StorageFullStrategy storageFullStrategy) {
        this(size);
        this.storageFullStrategy = storageFullStrategy;
    }

    public T pop() {
        T temp = peek();
        arr[currPos--] = null;
        return temp;
    }

    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return arr[currPos];
    }

    public void push(T el) {
        if (size <= currPos + 1) {
            if (storageFullStrategy == storageFullStrategy.ERROR_STRATEGY)
                throw new StackOverflowError();
            else
                arr = Arrays.copyOf(arr, (size + (int) size * 2 / 3));
        }
        arr[++currPos] = el;
    }

    public boolean isEmpty() {
        return currPos < 0;
    }
}

/////////////////////////// class MyQueue
class MyQueue<T> {
    protected int maxSize;
    protected int size = 0;
    protected T[] arr;
    protected int begin_pos = 0;
    protected int end_pos = 0;
    protected StorageFullStrategy storageFullStrategy = StorageFullStrategy.ERROR_STRATEGY;

    public MyQueue(int maxSize) {
        arr = (T[]) new Object[maxSize];
        this.maxSize = maxSize;
    }

    public MyQueue(int size, StorageFullStrategy storageFullStrategy) {
        this(size);
        this.storageFullStrategy = storageFullStrategy;
    }

    public void insert(T el) {
        if (isFull()) {
            if (storageFullStrategy == storageFullStrategy.ERROR_STRATEGY)
                throw new StackOverflowError();
            else {
                end_pos = maxSize;
                maxSize = (size + (int) size * 2 / 3);
                arr = Arrays.copyOf(arr, maxSize);
            }
        }
        arr[end_pos] = el;
        end_pos = nextIndex(end_pos);
        size++;
    }

    protected boolean isFull() {
        return size == maxSize;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    protected int nextIndex(int pos) {
        return (pos + 1) % maxSize;
    }

    public T remove() {
        if (isEmpty())
            throw new EmptyStackException();
        T temp = arr[begin_pos];
        arr[begin_pos] = null;
        begin_pos = nextIndex(begin_pos);
        size--;
        return temp;
    }
}

/////////////// MyPriorityQueue
class MyPriorityQueue<T extends Comparable<T>> {
    private int size = 0;
    private T[] arr;
    private int currPos = -1;
    private StorageFullStrategy storageFullStrategy = StorageFullStrategy.ERROR_STRATEGY;

    public MyPriorityQueue(int size) {
        this.size = size;
        arr = (T[]) new Comparable[size];
    }

    public MyPriorityQueue(int size, StorageFullStrategy storageFullStrategy) {
        this(size);
        this.storageFullStrategy = storageFullStrategy;
    }

    public T delete() {
        T temp = peek();
        arr[currPos--] = null;
        return temp;
    }

    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return arr[currPos];
    }

    public void insert(T el) {
        if (size <= currPos + 1) {
            if (storageFullStrategy == storageFullStrategy.ERROR_STRATEGY)
                throw new StackOverflowError();
            else
                arr = Arrays.copyOf(arr, (size + (int) size * 2 / 3));
        }
        arr[++currPos] = el;
        int i = currPos;
        while (i > 0 && arr[i].compareTo(arr[i - 1]) > 0) {
            changeEl(i, i - 1);
            i--;
        }
    }

    public void changeEl(int ind1, int ind2) {
        T temp = arr[ind2];
        this.arr[ind2] = this.arr[ind1];
        this.arr[ind1] = temp;
    }

    public boolean isEmpty() {
        return currPos < 0;
    }
}

////////////////////////// ReverceClass
class ReverceArr<T> {
    private T[] arr;
    MyStack<T> myStack;

    public ReverceArr(T[] arr) {
        this.arr = arr;
        myStack = new MyStack<>(arr.length);
    }

    public T[] reverceArr() {
        for (int i = 0; i < arr.length; i++) {
            myStack.push(arr[i]);
        }
        T[] output_arr = (T[]) new Object[arr.length];
        int i = 0;
        while (!myStack.isEmpty()) {
            output_arr[i++] = myStack.pop();
        }
        return (T[]) output_arr;
    }
}

/////////////////// MyDoubleQueue
class MyDoubleQueue<T> extends MyQueue<T> {

    public MyDoubleQueue(int maxSize) {
        super(maxSize);
    }

    public void insertRight(T el) {
        insert(el);
    }

    public void insertLeft(T el) {
        if (isFull())
            throw new StackOverflowError();
        begin_pos = prevIndex(begin_pos);
        arr[begin_pos] = el;
        size++;
    }

    public T removeLeft() {
        return remove();
    }

    public T removeRight() {
        if (isEmpty())
            throw new EmptyStackException();
        end_pos = prevIndex(end_pos);
        T temp = arr[end_pos];
        arr[end_pos] = null;
        size--;
        return temp;
    }

    private int prevIndex(int pos) {
        if (isEmpty())
            throw new EmptyStackException();
        if (pos == 0)
            return maxSize - 1;
        return pos - 1;
    }
}


