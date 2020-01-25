import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("------Проверка работы методов класса MyHashMap------------------");
        MyHashMap<Integer, String> myHashMap=new MyHashMap<>();
        myHashMap.put(1,"one");
        myHashMap.put(2,"two");
        myHashMap.put(3,"three");
        myHashMap.put(4,"four");
        myHashMap.put(5,"five");
        myHashMap.put(7,"seven");
        myHashMap.put(14,"14numb");

        myHashMap.put(4,"edit4");
        System.out.println(myHashMap.get(4));

        myHashMap.remove(4);
        System.out.println(myHashMap.get(4));
        System.out.println(myHashMap.contains(7));

        System.out.println(myHashMap);

//////////////////////////////////////////////////////
        System.out.println("------Проверка работы методов класса MyLinearMap------------------");
        MyLinearMap<Integer, String> map=new MyLinearMap<>();

        map.put(1,"one");
        map.put(2,"two");
        map.put(3,"three");
        map.put(4,"four");
        map.put(5,"five");
        map.put(7,"seven");
        map.put(14,"14numb");

        System.out.println(map.get(4));

        myHashMap.remove(4);
        System.out.println(map.get(4));
        System.out.println(map.contains(7));

        System.out.println(map);
    }
}
