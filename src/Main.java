import javafx.scene.chart.ScatterChart;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Проверка работы LinkedList и DuplexLinkedList
        ArrayList<LinkedList> arList = new ArrayList<>();
        arList.add(new LinkedList<String>());
        arList.add(new DuplexLinkedList<String>());

        for (LinkedList ll : arList) {

            ll.insertFirst("First");
            ll.insertFirst("Second");
            ll.insertFirst("Three");
            ll.insert(0, "Temp");
            ll.insert(4, "Temp2");
            ll.remove("Second");

            if (ll instanceof DuplexLinkedList) {
                ((DuplexLinkedList) ll).insertLast("Last");
                ((DuplexLinkedList) ll).removeLast();
            }

            while (!ll.isEmpty()) {
                if (ll instanceof DuplexLinkedList) {
                    System.out.println((String) ((DuplexLinkedList) ll).removeLast());
                } else
                    System.out.println((String) ll.removeFirst());
            }
            System.out.println("----------------------");
        }
        //Поверка стека на базе LinkedList
        StackList<String> stackList = new StackList();
        stackList.push("First");
        stackList.push("Second");
        while (!stackList.isEmpty()) {
            System.out.println(stackList.pop());
        }

        System.out.println("----------------------");

        // Проверка ListIterator
        MyListIterator<String> mi = new MyListIterator();
        mi.add("1el");
        mi.add("2el");
        mi.add("3el");

        while (mi.hasNext())
            System.out.println(mi.next());

        mi.previous();
        mi.set("changeEl");
        mi.next();

        mi.remove();

        while (mi.hasPrevious())
            System.out.println(mi.previous());

        System.out.println("Next index=" + mi.nextIndex());
        System.out.println("Prev index=" + mi.previousIndex());

    }
}

