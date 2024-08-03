package com.thebooleanguy.dictionary.dataStructure.structures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLinkedListTests {

    private SimpleLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SimpleLinkedList<>();
    }

    @Test
    void testAddFirst() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        assertEquals(3, list.size());
        assertEquals(3, list.getHead().data);
        assertEquals(2, list.getHead().next.data);
        assertEquals(1, list.getHead().next.next.data);
    }

    @Test
    void testRemove() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        list.remove(2);
        assertEquals(2, list.size());
        assertEquals(3, list.getHead().data);
        assertEquals(1, list.getHead().next.data);

        list.remove(3);
        assertEquals(1, list.size());
        assertEquals(1, list.getHead().data);

        list.remove(1);
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test
    void testRemoveLast() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        assertEquals(1, list.removeLast());
        assertEquals(2, list.size());
        assertEquals(2, list.removeLast());
        assertEquals(1, list.size());
        assertEquals(3, list.removeLast());
        assertEquals(0, list.size());
        assertNull(list.getHead());

        assertNull(list.removeLast());
    }

    @Test
    void testSize() {
        assertEquals(0, list.size());

        list.addFirst(1);
        assertEquals(1, list.size());

        list.addFirst(2);
        assertEquals(2, list.size());

        list.remove(1);
        assertEquals(1, list.size());

        list.remove(2);
        assertEquals(0, list.size());
    }
}
