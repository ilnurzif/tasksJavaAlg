package com.company;

import java.util.ArrayList;

import static java.lang.Math.pow;

public class Main {

    public static void main(String[] args) {
     // Проверка рек-ого метода возвдения числа в степень
      System.out.println("result = "+star(2,4) );
      System.out.println("-----------------------" );

   // проверка решения задачи о рюкзаке
        ArrayList<Thing> things = new ArrayList<Thing>();
        things.add(new Thing("Нож", 1, 400));
        things.add(new Thing("Палатка", 5, 23000));
        things.add(new Thing("Аптечка", 4, 1200));
        things.add(new Thing("Котелок", 2, 4300));
        things.add(new Thing("Топор", 1, 600));

        Backpack bp = new Backpack(10);
        bp.checkCombinations(things);
        ArrayList<Thing> bestThings = (ArrayList<Thing>) bp.getBestCombination();

        for (Thing thing : bestThings) {
            System.out.println(thing.name+" "+thing.price+" "+thing.weigth);
        }
    }

    // возведение числа в степень с помощью рекурсии
    private static int star(int numb, int st) {
        if (st==0) {
            return 1;
        }
        return star(numb, st-1)*numb;
    }
}
