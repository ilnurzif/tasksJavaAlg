import java.util.Arrays;
import java.util.Random;

public class MainClass {
  private static final int arrsize=100000;
  private static int[] srcarr;

 public static void main(String[] args) {
  srcarr=new int[arrsize];
  Random random=new Random();
   for (int i = 0; i <arrsize; i++) {
       srcarr[i]=(int) (random.nextInt());
   }


     MyArray[] sortMethodArr={
               new BubbleSorting(srcarr, "пузырька"),
               new SortSelect(srcarr, "выбора"),
               new SortInsert(srcarr, "вставки")
          };

     for (MyArray sorting: sortMethodArr)
     {  sorting.doSort();
        sorting.printArr();
     }

  }
}

abstract class MyArray {
  protected int[] arr;
  protected String methodName;
  protected long start, time;
  protected int arrSize=0;
  public MyArray(int[] startarr, String methodName) {
    this.methodName=methodName;
    arr=startarr.clone();
    arrSize=arr.length-1;
  }
  public abstract int[] sort();
  public void printArr() {
      System.out.println("Массив отсортированный методом "
                          +methodName
                          +" Время выполнения: "+time + " ms");
  }

  public int SimpleFind(int findVal) {
      for (int i = 0; i < arr.length; i++) {
         if(arr[i]==findVal)
           return i;
      }
      return -1;
  }

  public void add(int el) {
   if (arr.length<=arrSize-2)
      arr = Arrays.copyOf(arr, (int) (arrSize+(1.5*arrSize)));
    arr[++arrSize]=el;
  }

  public void insert(int index, int el) {
  if (arr.length<=arrSize+1)
    arr = Arrays.copyOf(arr, (int) (arrSize+(1.5*arrSize)));
      for (int i = arrSize+1; i > index; i--) {
        arr[i]=arr[i-1];
      }
      arr[index]=el;
      arrSize++;
  }

  public int BinaryFind(int findVal) {
      int min=0;
      int max=arr.length-1;

      while(true)
      {
        int mid=(max+min)/2;
        if (findVal>arr[mid])
         {min=mid+1; }
        else if (findVal<arr[mid])
        { max=mid-1;
        }
        else
          return mid;

        if (min>max)
           return -1;
      }
  }

  public void del(int index) {
      for (int i = index; i < arr.length-1; i++)
        arr[i]=arr[i+1];
      int last=arr.length-1;
      arr=Arrays.copyOf(arr, arr.length -1);
      arrSize--;
  }

  public void doSort() {
    start = System.currentTimeMillis();
    sort();
    time = System.currentTimeMillis()-start;
  }

  public void changeEl(int ind1, int ind2) {
    int temp=this.arr[ind2];
    this.arr[ind2]=this.arr[ind1];
    this.arr[ind1]=temp;
  }
}

class BubbleSorting extends MyArray {
    public BubbleSorting(int[] startarr, String methodName) {
        super(startarr, methodName);
    }

    @Override
    public int[] sort() {
       for (int i=arrSize-1; i>0;i--) {
         for (int j=0;j<i;j++) {
           if (arr[j]>arr[j+1])
               changeEl(j,j+1);
         }
       }
       return arr;
    }
}

class SortSelect extends MyArray {
    public SortSelect(int[] startarr, String methodName) {
        super(startarr, methodName);
    }

    @Override
    public int[] sort() {
     int mark;
     for (int out = 0; out < arrSize; out++) {
       mark=out;
       for (int in = out+1; in < arrSize; in++) {
        if(arr[in]<arr[mark])
          mark=in;
       }
       changeEl(mark,out);
      }
     return arr;
    }
}

class SortInsert extends MyArray {
    public SortInsert(int[] startarr, String methodName) {
        super(startarr, methodName);
    }

    @Override
    public int[] sort() {
        for (int i = 1; i < arrSize; i++) {
         int temp=arr[i];
         int j=i;
           while(j>0&&temp<=arr[j-1]) {
              arr[j]=arr[j-1];
              j--;
            }
          arr[j]=temp;
        }
       return arr;
    }
}