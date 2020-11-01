package ru.kuznetsovka.myalgoritms.lesson3;

import ru.kuznetsovka.myalgoritms.lesson3.myqueue.*;
import ru.kuznetsovka.myalgoritms.lesson3.mystack.Stack;
import ru.kuznetsovka.myalgoritms.lesson3.mystack.StackImpl;

public class Test {

    public static void main(String[] args) {
        testStack();//Немного переделал метод addToStack, сделал универсальный, Первый вариант решения 2-й задачи
        testQueue();
        testDeque(); //3-е задание //Наверное так, если правильно понял, что такое Deque.
        reverseData(); //Второй вариант решения 2-й задачи

    }

    private static void reverseData() {
        Deque<String> deque = new DequeImpl<> (5);
        System.out.println("Add value 1: " + deque.insertRight ( "A"));
        System.out.println("Add value 2: " + deque.insertRight ( "B"));
        System.out.println("Add value 3: " + deque.insertRight ( "C"));
        System.out.println("Add value 4: " + deque.insertRight ( "D"));
        System.out.println("Add value 5: " + deque.insertRight ( "F"));
        System.out.println("Add value 6: " + deque.insertRight ( "G"));
        while (!deque.isEmpty()){
            System.out.println(deque.removeRight ());
        }
    }

    private static void testDeque() {
        Deque<Integer> deque = new DequeImpl<> (5);
        System.out.println(deque.insertRight (1));
        System.out.println(deque.insertRight (2));
        System.out.println(deque.insertRight (3));
        System.out.println(deque.insertRight (4));
        System.out.println(deque.insertRight (5));
        System.out.println(deque.insertRight (4));
        System.out.println(deque.insertLeft (4));
        deque.removeLeft ();
        System.out.println(deque.insertLeft (4));

        System.out.println("Queue peek: " + deque.peekHead());
        System.out.println("Queue size: " + deque.size());
        System.out.println("Queue is full: " + deque.isFull());

        while (!deque.isEmpty()) {
            System.out.println(deque.removeRight ());
        }
    }

    private static void testStack() {
        Stack<String> stack = new StackImpl<> (5);
        System.out.println("Add value 1: " + addToStack(stack, "A"));
        System.out.println("Add value 2: " + addToStack(stack, "B"));
        System.out.println("Add value 3: " + addToStack(stack, "C"));
        System.out.println("Add value 4: " + addToStack(stack, "D"));
        System.out.println("Add value 5: " + addToStack(stack, "E"));
        System.out.println("Add value 6: " + addToStack(stack, "F"));
        System.out.println("Stack size: " + stack.size());
        System.out.println("Stack top: " + stack.peek());

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }



    private static  <E> boolean addToStack(Stack<E> stack, E value) {
        if (!stack.isFull()) {
            stack.push(value);
            return true;
        }
        return false;
    }

    private static void testQueue() {
        Queue<Integer> queue = new QueueImpl<> (5);
        System.out.println(queue.insertRight (3));
        System.out.println(queue.insertRight (5));
        System.out.println(queue.insertRight (1));
        System.out.println(queue.removeLeft ());
        System.out.println(queue.insertRight (2));
        System.out.println(queue.insertRight (6));
        System.out.println(queue.insertRight (4));

        System.out.println("Queue peek: " + queue.peekHead());
        System.out.println("Queue size: " + queue.size());
        System.out.println("Queue is full: " + queue.isFull());

        while (!queue.isEmpty()) {
            System.out.println(queue.removeLeft ());
        }
    }
}
