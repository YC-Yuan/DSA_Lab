package Lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Use JDK 11
 */
class MyLinkedListTest {

    @Test
    void atException() {
        Lab2.MyLinkedList<Integer> mll = new Lab2.MyLinkedList<>();
        assertThrows(Lab2.MyLinkedListIndexException.class, () -> mll.at(1));
    }

    @Test
    void at() throws Lab2.MyLinkedListEmptyException {

        Lab2.MyLinkedList<Integer> mll = new Lab2.MyLinkedList<>();
        mll.addLast(3);
        mll.addLast(8);
        try {
            assertEquals(8, mll.at(1));
            assertEquals(3, mll.at(0));
        } catch (Lab2.MyLinkedListIndexException e) {
            e.printStackTrace();
        }

        mll.rmLast();
        try {
            assertEquals(3, mll.at(0));
        } catch (Lab2.MyLinkedListIndexException e) {
            e.printStackTrace();
        }
        assertThrows(Lab2.MyLinkedListIndexException.class, () -> mll.at(1));
        mll.rmLast();
        assertThrows(Lab2.MyLinkedListEmptyException.class, mll::rmLast);

    }

    @Test
    public void iterator() {
        Lab2.MyLinkedList<Integer> mll = new Lab2.MyLinkedList<>();
        mll.addLast(2);
        mll.addLast(7);
        mll.addLast(9);
        mll.addLast(3);
        Lab2.MyIterator<Integer> iterator = mll.iterator();
        assertEquals(2, iterator.first());
        assertEquals(3, iterator.last());
        assertEquals(7, iterator.next());
        assertEquals(9, iterator.next());
        assertEquals(3, iterator.next());
        assertNull(iterator.next());
    }

    @Test
    public void isEnd4() {
        Lab2.MyLinkedList<Double> mll = new Lab2.MyLinkedList<>();
        Lab2.MyIterator<Double> iter1 = mll.iterator();
        // zero element
        assertTrue(iter1.isEnd4First());
        assertTrue(iter1.isEnd4Last());
        // two elements
        mll.addLast(1.0);
        mll.addLast(2.0);
        Lab2.MyIterator<Double> iterator = mll.iterator();
        int cnt = 0;
        for (Double d = iterator.first(); !iterator.isEnd4First(); d = iterator.next()) {
            cnt += 1;
            assertEquals(cnt, d);
        }
        assertEquals(2, cnt);
        // do not have to set the pointer to the first element
        assertTrue(iterator.isEnd4First());
    }

    @Test
    public void first() throws Lab2.MyLinkedListIndexException, Lab2.MyLinkedListEmptyException {
        Lab2.MyLinkedList<Integer> mll = new Lab2.MyLinkedList<>();
        mll.addFirst(1);
        mll.addFirst(3);
        assertEquals(1, mll.at(1));
        mll.rmFirst();
        assertThrows(Lab2.MyLinkedListIndexException.class, () -> mll.at(1));
        assertEquals(1, mll.at(0));
    }

}