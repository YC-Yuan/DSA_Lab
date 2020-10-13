package lab_three;

import java.text.DecimalFormat;
import java.util.List;

public class EventSimulator {
    private int k;
    private List<Cow> cows;

    /**
     * todo: init
     *
     * @param k k cows
     * @param arrivalLine arrival time, eating time of cows
     */
    public EventSimulator(int k,List<Cow> arrivalLine) {
        this.k = k;
        this.cows = arrivalLine;
    }

    /**
     * todo: simulate the process
     *
     * @return string of time, arrival, eating and leave
     * @throws HeapOverflowException too many items
     * @throws HeapEmptyException no item
     */
    public String simulate() throws lab_three.HeapOverflowException, lab_three.HeapEmptyException {
        long start=System.currentTimeMillis();
        StringBuilder result = new StringBuilder();
        MyHeap heap = new MyHeap(k);
        System.out.println("草场大小：" + k);
        System.out.println("牛数量：" + cows.size());
        int curTime = 0;
        int emptyTime = 0;
        int cowNum=0;
        for (Cow cow : this.cows
        ) {
            cowNum++;
            if (cowNum%10000==0) System.out.println(cowNum+"Time:"+(System.currentTimeMillis()-start));
            while (heap.isFull()) {//时间流逝，直到有空位
//                if(cowNum<10000)System.out.println("min:"+heap.findMin());
                curTime=heap.findMin();
                while (!heap.isEmpty() && curTime >= heap.findMin()) {//时间过了，空出空位；
                    emptyTime = heap.deleteMin();
                }
            }
            heap.insert(Math.max(cow.getArrival(),emptyTime) + cow.getEating());//牛入，记载离开时间
            result.append("Time:").append(String.format("%9d",emptyTime));
            result.append(", arrival:").append(String.format("%9d",cow.getArrival()));
            result.append(", stay:").append(String.format("%9d",cow.getEating()));
            result.append(", leave:").append(String.format("%9d",+Math.max(emptyTime,cow.getArrival()) + cow.getEating()));
            result.append("\n");
        }
        return result.toString();
    }

}
