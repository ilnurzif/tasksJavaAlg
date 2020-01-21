import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClass {
    private static final int treeCount = 20;
    private static final int maxLevel = 6;
    private static final int minRandNumb = -100;
    private static final int maxRandNumb = 100;
    static MyTreeMap myTreeMap;

    public static void main(String[] args) {
        // проверка класса myTreeMap
        myTreeMap = new MyTreeMap<Integer, Integer>();

        myTreeMap.put(34, 10);
        myTreeMap.put(23, 20);
        myTreeMap.put(48, 30);
        myTreeMap.put(21, 25);
        myTreeMap.put(55, 26);
        myTreeMap.put(57, 444);    // если расскоментировать 2 строки дерево становится несбалансировано
        myTreeMap.put(65, 526);

        myTreeMap.delete(34);

//        myTreeMap.deleteMin();

        System.out.println("get(9) = " + myTreeMap.get(65));
        System.out.println("containts(9) = " + myTreeMap.containts(9));
        System.out.println("containts(21) = " + myTreeMap.containts(21));
        System.out.println("treeHeigh = " + myTreeMap.treeHeigh());
        System.out.println("isBalanced() = " + myTreeMap.isBalanced());

        System.out.println(myTreeMap.toString());

        int balancedCount = 0;
        // строим 20 деревьев глубиной 6 уровней
        MyTreeMap[] myTreeMapArr = new MyTreeMap[treeCount];
        for (int i = 0; i < treeCount; i++) {
            myTreeMapArr[i] = new MyTreeMap<Integer, Integer>();
            while (myTreeMapArr[i].treeHeigh() < maxLevel)
                myTreeMapArr[i].put(gen_random(minRandNumb, maxRandNumb),
                        gen_random(minRandNumb, maxRandNumb));
            if (myTreeMapArr[i].isBalanced()) balancedCount++;
        }
        System.out.println("Cбалансированных деревьев = " + (balancedCount * 100 / treeCount) + " %");
    }

    public static int gen_random(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
