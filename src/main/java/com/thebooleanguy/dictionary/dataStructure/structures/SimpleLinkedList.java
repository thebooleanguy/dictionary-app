package com.thebooleanguy.dictionary.dataStructure.structures;

/**
 * A custom implementation of a singly linked list.
 */
public class SimpleLinkedList<T> {
    private Node<T> head; // The first node in the list
    private Node<T> tail; // The last node in the list
    private int size; // The number of elements in the list

    /**
     * Node class representing an element in the linked list.
     */
    public static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    /**
     * Adds an element to the front of the list.
     *
     * @param data The data to add.
     */
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * Removes the first occurrence of the given data.
     *
     * @param data The data to remove.
     */
    public void remove(T data) {
        if (head == null) return;

        if (head.data.equals(data)) {
            head = head.next;
            if (head == null) tail = null;
            size--;
            return;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

        if (current.next != null) {
            if (current.next == tail) {
                tail = current;
            }
            current.next = current.next.next;
            size--;
        }
    }

    /**
     * Removes the last element from the list.
     *
     * @return The data of the removed element.
     */
    public T removeLast() {
        if (head == null) return null;

        if (head == tail) {
            T data = head.data;
            head = tail = null;
            size--;
            return data;
        }

        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }

        T data = tail.data;
        tail = current;
        tail.next = null;
        size--;
        return data;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the head node of the list.
     *
     * @return The head node.
     */
    public Node<T> getHead() {
        return head;
    }
}
