package lab_three;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHeapTest {

    @Test
    void findMin() throws lab_three.HeapOverflowException, lab_three.HeapEmptyException {
        lab_three.MyHeap heap = new lab_three.MyHeap(3);
        assertThrows(lab_three.HeapEmptyException.class, heap::findMin);
        heap.insert(8);
        heap.insert(7);
        heap.insert(9);
        assertEquals(7, heap.findMin());
        assertThrows(lab_three.HeapOverflowException.class, () -> heap.insert(1));
    }

    @Test
    void deleteMin() throws lab_three.HeapOverflowException, lab_three.HeapEmptyException {
        lab_three.MyHeap heap = new lab_three.MyHeap(300);
        assertThrows(lab_three.HeapEmptyException.class, heap::deleteMin);
        heap.insert(9);
        heap.insert(2);
        heap.insert(8);
        heap.insert(5);
        heap.insert(10);
        heap.insert(4);
        heap.insert(4);
        heap.insert(4);
        assertEquals(2, heap.deleteMin());
        assertEquals(4, heap.deleteMin());
        assertEquals(4, heap.deleteMin());
        assertEquals(4, heap.deleteMin());
        assertEquals(5, heap.deleteMin());
        assertEquals(8, heap.deleteMin());
        assertEquals(9, heap.deleteMin());
        assertEquals(10, heap.deleteMin());
        assertThrows(lab_three.HeapEmptyException.class, heap::deleteMin);

    }

    @Test
    void isEmpty() throws lab_three.HeapOverflowException {
        lab_three.MyHeap heap = new lab_three.MyHeap(100);
        assertTrue(heap.isEmpty());
        heap.insert(1);
        assertFalse(heap.isEmpty());
    }

    @Test
    void makeEmpty() throws lab_three.HeapOverflowException {
        lab_three.MyHeap heap = new lab_three.MyHeap(100);
        heap.insert(1);
        assertFalse(heap.isEmpty());
        heap.makeEmpty();
        assertTrue(heap.isEmpty());
    }
}