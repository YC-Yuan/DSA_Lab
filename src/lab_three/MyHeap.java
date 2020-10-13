package lab_three;

class HeapOverflowException extends Exception {
    public HeapOverflowException(String message) {
        super(message);
    }
}

class HeapEmptyException extends Exception {
    public HeapEmptyException(String message) {
        super(message);
    }
}

/**
 * You may use {@link java.util.Queue}.
 */
public class MyHeap {
    private static final int MAX_CAPACITY = 10_000_001;
    private int capacity;
    private int curNum = 0;
    private int[] repo;


    /**
     * todo: init
     *
     * @param capacity capacity
     */
    public MyHeap(int capacity) {
        this.capacity = capacity;
        this.repo = new int[capacity];
    }

    /**
     * todo: capacity of the heap
     *
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * todo: insert an item into the heap
     *
     * @param x item
     * @throws HeapOverflowException too many items
     */
    public void insert(int x) throws HeapOverflowException {
        if (curNum == capacity) throw new HeapOverflowException("Overflow,fail to insert");
        else {
            int check = 0;
            int index = curNum++;
            repo[index] = x;
            while (index != 0 && check != capacity) {
                if (hasRChild(getParent(index))) check = exchange(getParent(index),true);
                else check = exchange(getParent(index),false);
                index = getParent(index);
            }

        }
    }

    /**
     * todo: find min value
     *
     * @return min value
     * @throws HeapEmptyException heap may be empty
     */
    public int findMin() throws HeapEmptyException {
        if (curNum == 0) throw new HeapEmptyException("Empty Heap,fail to find min.");
        else return repo[0];
    }

    /**
     * todo: find min value, and remove it
     *
     * @return deleted min value
     * @throws HeapEmptyException heap may be empty
     */
    public int deleteMin() throws HeapEmptyException {
        if (curNum == 0) throw new HeapEmptyException("Empty Heap,fail to delete.");
        else {
            int temp = repo[0];
            repo[0] = repo[--curNum];
            modify(0);
            return temp;
        }
    }

    /**
     * todo: isEmpty
     *
     * @return whether the heap is empty
     */
    public boolean isEmpty() {
        return curNum == 0;
    }

    /**
     * todo: remove all items in the heap
     */
    public void makeEmpty() {
        repo = new int[capacity];
        curNum = 0;
    }

    private void modify(int index) {
        while (hasChild(index) && index != capacity) {
            //System.out.println(index);
            if (hasRChild(index)) {//有两个孩子，跟小的换！
                index = exchange(index,true);
            }
            else {//只有一个孩子，比比！
                index = exchange(index,false);
            }
        }
    }

    //输入true为三者比较，跟最小的换；false为跟左比，后者更小则换
    private int exchange(int index,boolean method) {
        int indexL = (index + 1) * 2 - 1;
        int indexR = (index + 1) * 2;
        int newIndex = capacity;
        if (method) {
            if (repo[index] > repo[indexL]) {
                if (repo[indexL] > repo[indexR]) {//R最小
                    int temp = repo[index];
                    repo[index] = repo[indexR];
                    repo[indexR] = temp;
                    newIndex = indexR;
                }
                else {//L最小
                    int temp = repo[index];
                    repo[index] = repo[indexL];
                    repo[indexL] = temp;
                    newIndex = indexL;
                }
            }
            else if (repo[index] > repo[indexR]) {//R最小
                int temp = repo[index];
                repo[index] = repo[indexR];
                repo[indexR] = temp;
                newIndex = indexR;
            }
        }
        else {
            if (repo[index] > repo[indexL]) {//跟L换
                int temp = repo[index];
                repo[index] = repo[indexL];
                repo[indexL] = temp;
                newIndex = indexL;
            }
        }
        return newIndex;
    }

    private boolean hasChild(int index) {
        return (index + 1) * 2 <= curNum;
    }

    private boolean hasRChild(int index) {
        return (index + 1) * 2 < curNum;
    }

    private int getParent(int index) {
        return (index + 1) / 2 - 1;
    }

    public boolean isFull() {
        return curNum >= capacity;
    }

    public int cowNum() {
        return curNum;
    }
}
