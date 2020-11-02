package ru.kuznetsovka.myalgoritms.lesson3.myqueue;

public interface Queue<E> {

    boolean insertRight(E value);

    E removeLeft();

    E peekHead();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    boolean isFull();
}
