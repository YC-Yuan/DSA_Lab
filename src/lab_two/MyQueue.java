package lab_two;

class MyQueueEmptyException extends Exception {
    public MyQueueEmptyException(String message) {
        super(message);
    }
}

public class MyQueue<T> {
    private final lab_two.MyLinkedList<T> queue;

    public MyQueue() {
        queue = new lab_two.MyLinkedList<>();
    }

    /**
     * todo
     * add a value to the end of the queue
     *
     * @param val a value
     */
    public void enqueue(T val) {
        queue.addLast(val);
    }

    /**
     * todo
     * get the value at the top (beginning) of the queue, and remove it
     *
     * @return the value
     */
    public T dequeue() throws MyQueueEmptyException {
        if(queue.getSize()==0) throw new MyQueueEmptyException("The queue is empty");
        try {
            return queue.rmFirst();
        } catch (MyLinkedListEmptyException e) {
            throw new MyQueueEmptyException("The queue is empty");
        }
    }

    /**
     * todo
     * get the value at the top (beginning) of the queue, but not remove it
     *
     * @return the value
     */
    public T top() throws MyQueueEmptyException {
        MyIterator<T> iterator = queue.iterator();
        if (queue.getSize() == 0) throw new MyQueueEmptyException("The queue is empty");
        else return iterator.first();
    }

    /**
     * todo
     *
     * @return size of the queue
     */
    public int size() {
        return queue.getSize();
    }

    /**
     * todo
     *
     * @return whether the queue is empty or not
     */
    public boolean isEmpty() {
        return queue.getSize() == 0;
    }

    /**
     * todo
     * Elements will be printed in order
     * May simply call T.toString() method
     *
     * @return the elements in the queue
     */
    @Override
    public String toString() {
        if (queue.getSize() == 0) return "";
        MyIterator<T> iterator = queue.iterator();
        if (queue.getSize() == 1) return iterator.first().toString();
        else {
            StringBuilder result = new StringBuilder();
            result.append(iterator.first().toString());
            while (!iterator.isEnd4First()) {
                T val = iterator.next();
                if (val != null) {
                    result.append(", ");
                    result.append(val.toString());
                }
            }
            return result.toString();
        }
    }
}
