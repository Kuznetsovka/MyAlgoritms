package ru.kuznetsovka.myalgoritms.lesson3.myqueue;

public interface Deque<E> extends Queue<E> {

    boolean insertLeft(E value);

    E peekTail();

    E removeRight();
}
