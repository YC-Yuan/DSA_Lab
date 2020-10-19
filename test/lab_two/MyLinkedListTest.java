package lab_two;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Use JDK 11
 */
class MyLinkedListTest {

    @Test
    void atException() {
        lab_two.MyLinkedList<Integer> mll = new lab_two.MyLinkedList<>();
        assertThrows(lab_two.MyLinkedListIndexException.class, () -> mll.at(1));
    }

    @Test
    void at() throws lab_two.MyLinkedListEmptyException {

        lab_two.MyLinkedList<Integer> mll = new lab_two.MyLinkedList<>();
        mll.addLast(3);
        mll.addLast(8);
        try {
            assertEquals(8, mll.at(1));
            assertEquals(3, mll.at(0));
        } catch (lab_two.MyLinkedListIndexException e) {
            e.printStackTrace();
        }

        mll.rmLast();
        try {
            assertEquals(3, mll.at(0));
        } catch (lab_two.MyLinkedListIndexException e) {
            e.printStackTrace();
        }
        assertThrows(lab_two.MyLinkedListIndexException.class, () -> mll.at(1));
        mll.rmLast();
        assertThrows(lab_two.MyLinkedListEmptyException.class, mll::rmLast);

    }

    @Test
    public void iterator() {
        lab_two.MyLinkedList<Integer> mll = new lab_two.MyLinkedList<>();
        mll.addLast(2);
        mll.addLast(7);
        mll.addLast(9);
        mll.addLast(3);
        lab_two.MyIterator<Integer> iterator = mll.iterator();
        assertEquals(2, iterator.first());
        assertEquals(3, iterator.last());
        assertEquals(7, iterator.next());
        assertEquals(9, iterator.next());
        assertEquals(3, iterator.next());
        assertNull(iterator.next());
    }

    @Test
    public void isEnd4() {
        lab_two.MyLinkedList<Double> mll = new lab_two.MyLinkedList<>();
        lab_two.MyIterator<Double> iter1 = mll.iterator();
        // zero element
        assertTrue(iter1.isEnd4First());
        assertTrue(iter1.isEnd4Last());
        // two elements
        mll.addLast(1.0);
        mll.addLast(2.0);
        lab_two.MyIterator<Double> iterator = mll.iterator();
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
    public void first() throws lab_two.MyLinkedListIndexException, lab_two.MyLinkedListEmptyException {
        lab_two.MyLinkedList<Integer> mll = new lab_two.MyLinkedList<>();
        mll.addFirst(1);
        mll.addFirst(3);
        assertEquals(1, mll.at(1));
        mll.rmFirst();
        assertThrows(lab_two.MyLinkedListIndexException.class, () -> mll.at(1));
        assertEquals(1, mll.at(0));
    }

}