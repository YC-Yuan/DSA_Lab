package lab_seven;

@SuppressWarnings("unchecked")

public class QuickSort {

    public static <T extends Comparable<T>> void sort(T[] table) {
        // Sort the table of data using Quick Sort from index 0 to index table.length-1
        // (i.e. sort the entire table)

        quicksort(table,0,table.length - 1);
    }

    private static <T extends Comparable<T>> void quicksort(T[] table,int first,int last) {

        // Sort the table using quicksort from index first to index last
        // Precondition: first <= last

        // Base case: If the given section of the table has 1 element,
        // then it is sorted already, so just return

        if (first < last) {

            // Partition the table so that the table contains all elements less than the pivot,
            // followed by the pivot, followed by all elements greater than the pivot.
            // Return the final position of the pivot after partioning.
            int pivotIndex = partition(table,first,last);

            // Sort the elements less than the pivot
            quicksort(table,first,pivotIndex - 1);

            // Sort the elements greater than the pivot
            quicksort(table,pivotIndex + 1,last);

        }
    }

    private static <T extends Comparable<T>> int partition(T[] table,int first,int last) {
        //取table[first]为快排基准
        T standard = table[last];
        //想要的效果为，range左边全是小于standard，而右边全是大的。range不仅作为分界线，也保证了"引用的数是大区的"，于是发现了新的小数后可以直接交换
        int range = first;
        for (int i = first; i < last; i++) {
            if (table[i].compareTo(standard) < 0) {
                //将刚刚比较的数和range代表的大数互换
                swap(table,i,range);
                //把小的数吃进范围，保证右边是大数
                range++;
            }
        }
        //操作完毕，将基准和range互换
        swap(table,last,range);

        return range;//所有数分居两侧，并返回standard的位置，作为递归的参数
        // COMPLETE THIS METHOD BASED ON THE COMMENTS MARKED WITH ***

        // Select the first element in the table as the pivot


        // Now the table consists of:
        // the pivot
        // followed by the first partition containing elements <= pivot (not necessarily sorted)
        // followed by the second partition containing elements > pivot (not necessarily sorted)
        // Swap the pivot with the final value in the first partition ***


        // Return the final position of the pivot  ***
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
