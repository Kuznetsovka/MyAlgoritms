package ru.kuznetsovka.myalgoritms.lesson3.myqueue;

import java.util.ArrayDeque;

public class DequeImpl<E> extends QueueImpl<E> implements Deque<E> {
    private int maxSize;

    public DequeImpl(int maxSize) {
        super (maxSize);
    }

    @Override // O(1)
    public E removeRight() {
        if (isEmpty()) {
            return  null;
        }
        if (tail == DEFAULT_TAIL)
            tail = data.length - 1;
        size--;
        return data[tail--];
    }

    @Override
    public boolean insertLeft(E value) {
        if (isFull()) {
            return false;
        }
        if (head == DEFAULT_HEAD)
            head = data.length;
        data[--head] = value;
        size++;
        return true;
    }

    @Override
    public E peekTail() {
        return data[tail];
    }
}
