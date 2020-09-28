package Lab2;
/*
 * May add properties, functions, classes if you need.
 * But mustn't change interfaces called in test cases.
 */

/**
 * To save an element in {@link MyLinkedList}
 *
 * @param <T>
 */
class MyNode<T> {
    MyNode<T> prev;
    MyNode<T> next;
    T val;

    public MyNode(T val) {
        this.val = val;
    }

    public MyNode(T val,MyNode<T> prev,MyNode<T> next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }

}

/**
 * Used to iterate {@link MyLinkedList}
 *
 * @param <T>
 */
class MyIterator<T> {
    /**
     * Currently parse element at {@code ptr}.
     */
    private MyNode<T> ptr;
    private final MyNode<T> tail;
    private final MyNode<T> head;

    MyIterator(MyNode<T> head,MyNode<T> tail) {
        ptr = head;
        this.tail = tail;
        this.head = head;
    }

    /**
     * get the first element,
     * to iterate from the first to the last
     *
     * @return the first value
     */
    public T first() {
        return this.head.val;
    }

    /**
     * get the last element,
     * to iterate from the last to the first
     *
     * @return the last value
     */
    public T last() {
        return this.tail.val;
    }

    /**
     * todo
     * iterate from first to last
     * whether to end or not
     *
     * @return if ends
     */
    public boolean isEnd4First() {
        //如果整个是空，那肯定true
        if (this.head == null) {System.out.println("head==null"); return true;}
        //如果当前是空，也是true
        System.out.println(ptr==null);
        return ptr == null;
    }

    /**
     * todo
     * iterate from last to first
     * whether to end or not
     *
     * @return if ends
     */
    public boolean isEnd4Last() {
        if (this.head == null) return true;
        return ptr == null;
    }

    /**
     * todo
     * need to parse {@link NullPointerException}, or throw it
     * from first to last
     *
     * @return next value
     */
    public T next() {
        if (ptr == null) {
            System.out.println("ptr==null!");
            return null;
        }
        ptr = ptr.next;
        if (ptr == null) {
            System.out.println("ptr.next==null!");
            return null;
        }
        return ptr.val;
    }

    /**
     * todo
     * need to parse {@link NullPointerException}, or throw it
     * from the last to the first
     *
     * @return prev value
     */
    public T prev() {
        if (ptr == null) {
            return null;
        }
        ptr = ptr.prev;
        if (ptr == null) {
            return null;
        }
        return ptr.val;
    }

}

/**
 * List may be empty when removing an element
 */
class MyLinkedListEmptyException extends Exception {
    public MyLinkedListEmptyException(String message) {
        super(message);
    }
}

/**
 * for {@link MyLinkedList#at(int)}
 */
class MyLinkedListIndexException extends Exception {
    public MyLinkedListIndexException(String message) {
        super(message);
    }
}

/**
 * {@link MyLinkedList} is an implementation of {@link java.util.Deque}.
 *
 * @param <T> T can be user-defined object.
 */
public class MyLinkedList<T> {
    private MyNode<T> head;
    private MyNode<T> tail;
    /**
     * suppose that there are at most 2^31 -1 elements.
     */
    private int size = 0;

    public int getSize() {
        return this.size;
    }

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * find value at index (start from zero)
     *
     * @param index index
     * @return value at index
     * @throws MyLinkedListIndexException out of index
     */
    public T at(int index) throws MyLinkedListIndexException {
        int i = 0;
        if (index < 0 || index > size - 1) {
            throw new MyLinkedListIndexException("Index < 0 or index is too large");
        }
        T v = null;
        for (MyNode<T> n = this.head; n != null; n = n.next,i++) {
            if (i == index) {
                v = n.val;
                // may be better to remove break clause
                break;
            }
        }
        return v;
    }

    /**
     * insert value to the end of the list
     *
     * @param item value
     */
    public void addLast(T item) {
        if (size == 0) {
            MyNode<T> node = new MyNode<>(item,null,null);
            this.head = node;
            this.tail = node;
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }
        else {
            MyNode<T> t = this.tail;
            this.tail = new MyNode<>(item,t,null);
            t.next = this.tail;
        }
        size++;
    }

    /**
     * remove the last value, and return it
     *
     * @return last value
     * @throws MyLinkedListEmptyException nothing to remove
     */
    public T rmLast() throws MyLinkedListEmptyException {
        if (size == 0) {
            throw new MyLinkedListEmptyException("Linked List has no elements");
        }
        T v = this.tail.val;
        if (size == 1) {
            this.head = null;
            this.tail = null;
        }
        else {
            this.tail = this.tail.prev;
            this.tail.next = null;
        }
        size--;
        return v;
    }

    /**
     * todo
     * insert value to the start of the list
     * i.e., addFirst(1) to List 3 - 7 - 2 and get 1 - 3 - 7 - 2
     *
     * @param item value
     */
    public void addFirst(T item) {
        if (size == 0) {
            MyNode<T> node = new MyNode<>(item,null,null);
            this.head = node;
            this.tail = node;
            this.tail.prev = this.head;
        }
        else {
            MyNode<T> t = this.head;
            this.head = new MyNode<>(item,t,null);
            this.head.next = t;
        }
        size++;
    }

    /**
     * todo
     * remove value at the start of the list and return it
     *
     * @return removed value
     * @throws MyLinkedListEmptyException nothing to remove
     */
    public T rmFirst() throws MyLinkedListEmptyException {
        if (size == 0) {
            throw new MyLinkedListEmptyException("Linked List has no elements");
        }
        T v = this.head.val;
        if (size == 1) {
            this.head = null;
            this.tail = null;
        }
        else {
            this.head = this.head.next;
            this.head.prev = null;
        }
        size--;
        return v;
    }

    /**
     * @return iterator of the list.
     */
    public MyIterator<T> iterator() {
        return new MyIterator<>(this.head,this.tail);
    }
}
