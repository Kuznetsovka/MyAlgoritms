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
            return null;
        }
        if (isFull()) {
            tail=size-1;
        }
        E removedValue = data[tail--];
        size--;
        return removedValue;
    }

    @Override
    public boolean insertLeft(E value) {
        if (isFull()) {
            return false;
        }
        if (head==0)
            head=DEFAULT_HEAD+1;
        data[--head] = value;
        size++;
        return true;
    }

    @Override
    public E peekTail() {
        return data[tail];
    }
}
