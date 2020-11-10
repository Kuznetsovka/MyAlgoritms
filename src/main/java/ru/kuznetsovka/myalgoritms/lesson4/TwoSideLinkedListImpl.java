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
        return new LinkedListIterator<> (this);
    }


    private static class LinkedListIterator<E> implements ListIterator<E> {

        private final TwoSideLinkedListImpl<E> list;

        private Node<E> current;
        private Node<E> previous;

        public LinkedListIterator(TwoSideLinkedListImpl<E> list) {
            this.list = list;
            reset();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E nextValue = current.item;
            previous = current;
            current = current.next;
            return nextValue;
        }

        @Override
        public void remove() {
            if (previous == null){
                list.firstElement = current.next;
                reset();
            } else {
                previous.next = current.next;
                if ( !hasNext() ) {
                    reset();
                } else {
                    current = current.next;
                }
            }
        }

        @Override
        public void reset() {
            current = list.firstElement;
            previous = null;
        }

        @Override
        public void insertBefore(E value) {
            Node<E> newItem = new Node<>(value, null);
            if(previous == null) {
                newItem.next = list.firstElement;
                list.firstElement = newItem;
                reset();
            }
            else {
                newItem.next = previous.next;
                previous.next = newItem;
                current = newItem;
            }

        }

        @Override
        public void insertAfter(E value) {
            Node<E> newItem = new Node<>(value, null);
            if (list.isEmpty()){
                list.firstElement = newItem;
                current = newItem;
            } else {
                newItem.next = current.next;
                current.next = newItem;
                next();
            }
        }

    }
}
