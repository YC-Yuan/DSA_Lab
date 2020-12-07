package lab_seven;

/**
 * @author : wqruan
 * @version : 1.0.0
 * @date : 2020/11/15 15:58
 */
public class InsertSort {
    public static <T extends Comparable<T>> void sort(T[] table) {
        // Sort the table of data using Insert Sort
        for (int i=0;i<table.length-1;i++){
            swap(table,i,findMinIndex(table,i));
        }

    }

    private static <T extends Comparable<T>> int findMinIndex(T[] table,int first) {
        int index = first;
        for (int i = first; i < table.length - 1; i++) {//i为当前index，i+1为下一个
            if (table[index].compareTo(table[i + 1]) > 0) index = i + 1;
        }
        return index;
    }

    private static <T extends Comparable<T>> void swap(T[] table,int i,int j) {
        // Swaps table[i] with table[j]
        T temp = table[i];
        table[i] = table[j];
        table[j] = temp;
    }

    public static void main(String[] args) {
// Create a random table of 20 integers with values between 10 and 99
        Integer[] table = new Integer[20];
        for (int i = 0; i < 20; i++)
            table[i] = (int) (Math.random() * 90) + 10;

        // Output original table
        System.out.println("ORIGINAL TABLE:");
        for (Integer i : table)
            System.out.print(i + " ");
        System.out.println();

        // Sort the table
        sort(table);

        // Output sorted table
        System.out.println("SORTED TABLE:");
        for (Integer i : table)
            System.out.print(i + " ");
        System.out.println();
    }
}
