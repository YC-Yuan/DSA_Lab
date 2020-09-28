package Lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    @Test
    void top() throws Lab2.MyQueueEmptyException {
        Lab2.MyQueue<Integer> queue = new Lab2.MyQueue<>();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertThrows(Lab2.MyQueueEmptyException.class, queue::top);
        assertThrows(Lab2.MyQueueEmptyException.class, queue::dequeue);

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

        assertThrows(Lab2.MyQueueEmptyException.class, queue::top);
        assertThrows(Lab2.MyQueueEmptyException.class, queue::dequeue);
        assertTrue(queue.isEmpty());

    }

    @Test
    public void String() {
        Lab2.MyQueue<Integer> queue = new Lab2.MyQueue<>();
        String s = queue.toString();
        assertEquals("", s);
        queue.enqueue(1);
        queue.enqueue(4);
        queue.enqueue(9);
        s = queue.toString();
        assertEquals("1, 4, 9", s);
    }
}