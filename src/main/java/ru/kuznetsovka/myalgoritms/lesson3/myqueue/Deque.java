package ru.kuznetsovka.myalgoritms.lesson3.myqueue;

public interface Deque<E> extends Queue<E>,Iterable<E> {

    boolean insertLeft(E value);

    E peekTail();

    E removeRight();
}
