package lab_two;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    @Test
    void top() throws lab_two.MyQueueEmptyException {
        lab_two.MyQueue<Integer> queue = new lab_two.MyQueue<>();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertThrows(lab_two.MyQueueEmptyException.class, queue::top);
        assertThrows(lab_two.MyQueueEmptyException.class, queue::dequeue);

        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(1);
        assertFalse(queue.isEmpty());

        assertEquals(3, queue.size());

        assertEquals(2, queue.top());
        assertEquals(2, queue.dequeue());

        assertEquals(4, queue.top());
        assertEquals(4, queue.dequeue());

        assertEquals(1, queue.top());
        assertEquals(1, queue.dequeue());

        assertThrows(lab_two.MyQueueEmptyException.class, queue::top);
        assertThrows(lab_two.MyQueueEmptyException.class, queue::dequeue);
        assertTrue(queue.isEmpty());

    }

    @Test
    public void String() {
        lab_two.MyQueue<Integer> queue = new lab_two.MyQueue<>();
        String s = queue.toString();
        assertEquals("", s);
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(9);
        s = queue.toString();
        assertEquals("1, 4, 9", s);
    }
}