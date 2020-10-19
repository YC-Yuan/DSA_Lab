package lab_four;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void empty() {
        BinarySearchTree bst = new BinarySearchTree();
        assertEquals(0, bst.size());
        assertThrows(EmptyBSTException.class, () -> bst.remove(0));
        assertThrows(EmptyBSTException.class, bst::removeMax);
        assertThrows(EmptyBSTException.class, bst::removeMin);
        assertThrows(EmptyBSTException.class, bst::min);
        assertThrows(EmptyBSTException.class, bst::max);
    }

    @Test
    void insert() throws EmptyBSTException, DuplicateKeyException {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(1);
        bst.insert(4);
        assertEquals(2, bst.size());
        assertEquals(1, bst.min());
        assertThrows(DuplicateKeyException.class, () -> bst.insert(1));
        assertEquals(4, bst.max());
    }

    @Test
    void remove() throws DuplicateKeyException, EmptyBSTException, NoSuchKeyException {
        BinarySearchTree bst = new BinarySearchTree(new int[]{7, 5, 10, 1, 8, 9, 12, 6, 11, 4, -5});
        assertEquals(12, bst.max());
        assertEquals(-5, bst.min());
        assertEquals(11, bst.size());
        assertEquals(12, bst.removeMax());
        assertEquals(10, bst.size());
        assertEquals(-5, bst.removeMin());
        assertEquals(9, bst.size());
        assertFalse(bst.hasKey(3));
        assertThrows(NoSuchKeyException.class, () -> bst.remove(3));
        assertTrue(bst.hasKey(8));
        bst.remove(8);
        Integer[] inorder = bst.inorder(true).toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{1, 4, 5, 6, 7, 9, 10, 11}, inorder);
        bst.remove(7);
        inorder = bst.inorder(true).toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{1, 4, 5, 6, 9, 10, 11}, inorder);
        Integer[] level = bst.levelOrder().toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{9, 5, 10, 1, 6, 11, 4}, level);
    }

    @Test
    void order() throws DuplicateKeyException {
        int[] arr = new int[]{2, 1, 3, 4, 14, 8, 9, 5, 12};
        BinarySearchTree bst = new BinarySearchTree();
        for (int i : arr) bst.insert(i);
        Integer[] actualInorder = bst.inorder(true).toArray(new Integer[bst.size()]);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 8, 9, 12, 14}, actualInorder);
        bst.insert(7);
        Integer[] actualPreorder = bst.preorder(true).toArray(new Integer[bst.size()]);
        assertArrayEquals(new Integer[]{2, 1, 3, 4, 14, 8, 5, 7, 9, 12}, actualPreorder);
        Integer[] actualPostorder = bst.postorder(true).toArray(new Integer[bst.size()]);
        Integer[] actualLevelOrder = bst.levelOrder().toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{1, 7, 5, 12, 9, 8, 14, 4, 3, 2}, actualPostorder);
        assertArrayEquals(new Integer[]{2, 1, 3, 4, 14, 8, 5, 9, 7, 12}, actualLevelOrder);
    }

    @Test
    void orderNonRecur() throws DuplicateKeyException {
        BinarySearchTree bst = new BinarySearchTree(new int[]{2, 1, 3, 4, 14, 8, 9, 5, 12, 7});
        Integer[] actualPreorder = bst.preorder(false).toArray(new Integer[0]);
        assertArrayEquals(new Integer[]{2, 1, 3, 4, 14, 8, 5, 7, 9, 12}, actualPreorder);
        Integer[] actualPostorder = bst.postorder(false).toArray(new Integer[bst.size()]);
        assertArrayEquals(new Integer[]{1, 7, 5, 12, 9, 8, 14, 4, 3, 2}, actualPostorder);
        Integer[] actualInorder = bst.inorder(false).toArray(new Integer[bst.size()]);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 7, 8, 9, 12, 14}, actualInorder);
    }

    @Test
    void depth() throws DuplicateKeyException, EmptyBSTException, NoSuchKeyException {
        BinarySearchTree bst = new BinarySearchTree();
        assertEquals(0, bst.depth());
        bst.insert(5);
        assertEquals(1, bst.depth());
        bst.insert(9);
        assertEquals(2, bst.depth());
        bst.insert(2);
        assertEquals(2, bst.depth());
        bst.insert(1);
        assertEquals(3, bst.depth());
        bst.insert(-1);
        assertEquals(4, bst.depth());
        bst.remove(2);
        assertEquals(3, bst.depth());
    }

    @Test
    void kthSmallest() throws DuplicateKeyException, EmptyBSTException, NoSuchKeyException, CloneNotSupportedException {
        BinarySearchTree bst = new BinarySearchTree(new Integer[]{7, 5, 10, 1, 8, 9, 12, 6, 11, 4, -5});
        assertEquals(-5, bst.kthSmallest(1));
        assertEquals(1, bst.kthSmallest(2));
        assertEquals(4, bst.kthSmallest(3));
        assertEquals(5, bst.kthSmallest(4));
        assertEquals(6, bst.kthSmallest(5));
        assertEquals(7, bst.kthSmallest(6));
        assertEquals(8, bst.kthSmallest(7));
        assertEquals(9, bst.kthSmallest(8));
        assertEquals(10, bst.kthSmallest(9));
        bst.remove(7);
        assertEquals(8, bst.kthSmallest(6));
    }

    @Test
    void closestValue() throws DuplicateKeyException, EmptyBSTException, NoSuchKeyException {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(7);
        assertEquals(7, bst.closestSmall(7.5));
        bst.insert(8);
        assertEquals(7, bst.closestSmall(7.5));
        bst.insert(6);
        assertEquals(7, bst.closestSmall(7.5));
        bst.remove(7);
        assertEquals(8, bst.closestSmall(7.5));

        BinarySearchTree newBst = new BinarySearchTree();
        newBst.insert(7);
        newBst.insert(9);
        assertEquals(7, newBst.closestSmall(8));

    }

    @Test
    void huge() throws DuplicateKeyException, EmptyBSTException, NoSuchKeyException, CloneNotSupportedException {
        int total = 1_000_000;
        Integer[] items = new Integer[total];
        Integer[] exceptedInorder = new Integer[total];
        for (int i = 0; i < total; i++) {
            items[i] = i + 1;
            exceptedInorder[i] = i + 1;
        }
        int half = total / 2;
        Random rand = new Random();
        for (int i = 0; i <= half; i++) {
            int rand_i = i;
            while (rand_i == i)
                rand_i = rand.nextInt(total);
            int tmp = items[rand_i];
            items[rand_i] = items[i];
            items[i] = tmp;
        }
        BinarySearchTree bst = new BinarySearchTree(items);

        assertEquals(1, bst.min());
        assertEquals(total, bst.max());
        assertTrue(bst.hasKey(1000));
        assertEquals(1000, bst.kthSmallest(1000));
        assertEquals(10, bst.kthSmallest(10));

        assertEquals(999, bst.closestSmall(999.5));
        assertEquals(1000, bst.closestSmall(1000.5));
        assertEquals(1001, bst.closestSmall(1000.6));
        Integer[] inorder = bst.inorder(true).toArray(new Integer[0]);
        assertArrayEquals(exceptedInorder, inorder);

        bst.remove(1000);
        assertEquals(999_999, bst.size());
        assertEquals(1001, bst.kthSmallest(1000));
        List<Integer> l = new ArrayList<>(Arrays.asList(exceptedInorder));
        l.remove(Integer.valueOf(1000));
        assertEquals(1001, bst.closestSmall(1000.5));
        inorder = bst.inorder(false).toArray(new Integer[0]);
        exceptedInorder = l.toArray(new Integer[0]);
        assertArrayEquals(exceptedInorder, inorder);
    }
}