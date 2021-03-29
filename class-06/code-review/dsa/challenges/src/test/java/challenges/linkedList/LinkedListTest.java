package challenges.linkedList;

import org.junit.Test;
import static org.junit.Assert.*;

public class LinkedListTest {

    @Test public void testLinkedList(){
        LinkedList linky = new LinkedList();
        assertNull("the head of a new LL shoudl be null", linky.head);
        linky.insert(5);
        assertEquals("the head node''s value should be 5", 5, linky.head.value);
        linky.insert(7);
        assertEquals("the head node''s value should be 7", 7, linky.head.value);
        assertEquals(
                "the head.next should hold a value of 5",
                5,
                linky.head.next.value
        );
        linky.insert(9);
        assertEquals("the head node''s value should be 9", 9, linky.head.value);
        assertEquals(
                "the head.next should hold a value of 7",
                7,
                linky.head.next.value
        );
        assertEquals(
                "the head.next should hold a value of 5",
                5,
                linky.head.next.value
        );
    }
}
