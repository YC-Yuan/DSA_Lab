package lab_two;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    @Test
    public void top() throws lab_two.MyStackEmptyException {
        lab_two.MyStack<Integer> stack = new lab_two.MyStack<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertThrows(lab_two.MyStackEmptyException.class, stack::top);
        assertThrows(lab_two.MyStackEmptyException.class, stack::pop);

        stack.push(1);
        stack.push(3);
        assertFalse(stack.isEmpty());

        assertEquals(3, stack.top());

        assertEquals(2, stack.size());

        assertEquals(3, stack.pop());

        assertEquals(1, stack.size());

        assertEquals(1, stack.top());


    }

    @Test
    public void String() {
        lab_two.MyStack<Integer> stack = new lab_two.MyStack<>();
        String s = stack.toString();
        assertEquals("", s);
        stack.push(4);
        stack.push(8);
        stack.push(0);
        s = stack.toString();
        assertEquals("0, 8, 4", s);
    }

}