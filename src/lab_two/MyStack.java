package lab_two;

class MyStackEmptyException extends Exception {
    public MyStackEmptyException(String message) {
        super(message);
    }
}

public class MyStack<T> {

    private final MyLinkedList<T> stack;

    public MyStack() {
        this.stack = new MyLinkedList<>();
    }

    /**
     * todo
     * add a value to the top of the stack
     *
     * @param val value to be added into the stack
     */
    public void push(T val) {
        stack.addFirst(val);
    }

    /**
     * todo
     * get the value at the top of the stack, and remove it
     *
     * @return the last value added into the stack
     */
    public T pop() throws MyStackEmptyException {
        if (stack.getSize() == 0) throw new MyStackEmptyException("The stack is empty");
        try {
            return stack.rmFirst();
        } catch (MyLinkedListEmptyException e) {
            e.printStackTrace();
        }
        throw new MyStackEmptyException("The stack is empty");
    }

    /**
     * todo
     * get the value at the top of the stack, but not remove it
     *
     * @return the last value added into the stack
     */
    public T top() throws MyStackEmptyException {
        if (stack.getSize() == 0) throw new MyStackEmptyException("The stack is empty");
        return stack.iterator().first();
    }

    /**
     * todo
     *
     * @return whether the stack contains any elements or not
     */
    public boolean isEmpty() {
        return stack.getSize() == 0;
    }

    /**
     * todo
     * size of the stack
     *
     * @return size
     */
    public int size() {
        return stack.getSize();
    }

    /**
     * todo
     * Elements will be printed in order
     * May simply call T.toString() method
     *
     * @return all elements in the stack
     */
    @Override
    public String toString() {
        if (stack.getSize() == 0) return "";
        MyIterator<T> iterator = stack.iterator();
        if (stack.getSize() == 1) return iterator.first().toString();
        else {
            StringBuilder result = new StringBuilder();
            result.append(iterator.first().toString());
            System.out.println(iterator.first().toString());
            while (!iterator.isEnd4First()) {
//            for (int i=0;i<stack.getSize()-1;i++){
                T val = iterator.next();
                if (val != null) {
                    System.out.println(val.toString());
                    result.append(", ");
                    result.append(val.toString());
                }
            }
            return result.toString();
        }
    }
}
