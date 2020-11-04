package ru.kuznetsovka.myalgoritms.lesson4;

import java.util.Iterator;

public class TwoSideLinkedListImpl<E> extends SimpleLinkedListImpl<E> implements TwoSideLinkedList<E>, Iterable<E> {

    private Node<E> lastElement;


    public void insertFirst(E value) {
        super.insertFirst(value);
        if (size == 1) {
            lastElement = firstElement;
        }
    }

    @Override
    public E removeFirst() {
        E removedData = super.removeFirst();
        if (isEmpty()) {
            lastElement = null;
        }

        return removedData;
    }

    public boolean remove(E value) {
        Node<E> current = firstElement;
        Node<E> previous = null;
        while (current != null) {
            if (current.item.equals(value)) {
                break;
            }
            previous = current;
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        if (size() == 1) {
            return removeFirst() != null;
        }

        if (current == firstElement) {
            firstElement = firstElement.next;
        }
        else if (current == lastElement) {
            lastElement = previous;
            previous.next = null;
        }
        else {
            previous.next = current.next;
        }

        current.next = null;
        current.item = null;

        size--;
        return true;
    }

    @Override
    public void insertLast(E value) {
        Node<E> entry = new Node<>(value, null);
        if (isEmpty()) {
            firstElement = entry;
        }
        else {
            lastElement.next = entry;
        }

        lastElement = entry;
        size++;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            Node<E> current = firstElement;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public E next() {
                if (currentIndex==0) {
                    currentIndex++;
                    return current.item;
                }
                current = current.next;
                currentIndex++;
                return current.item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
