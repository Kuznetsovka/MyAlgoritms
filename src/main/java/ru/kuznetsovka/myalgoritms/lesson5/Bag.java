package ru.kuznetsovka.myalgoritms.lesson5;

import ru.kuznetsovka.myalgoritms.lesson3.myqueue.Deque;
import ru.kuznetsovka.myalgoritms.lesson3.myqueue.DequeImpl;

import java.util.ArrayList;
import java.util.Collections;


public class Bag {
    protected int capacity;
    //TODO Поменять на TwoSideLinkedList
    protected ArrayList<Integer> optimumWeight = new ArrayList<> ();
    protected ArrayList<Deque<Item>> optimumList =  new ArrayList<> ();

    public Bag(int capacity) {
        this.capacity = capacity;
    }

    public void pack(ArrayList<Item> list){
        Collections.sort(list);
        Collections.reverse (list);
        Deque<Item> dequeList = new DequeImpl<> (list.size ());
        for (Item item : list) {
            dequeList.insertRight (item);
        }
        getAnagrammSum(dequeList);

        for (int i = 0; i < optimumWeight.size (); i++) {
            if (optimumWeight.get(i)>capacity) {
                if (i==0)
                    System.out.println (optimumWeight.get(i));
                else
                    System.out.println (optimumWeight.get(i-1));
                return;
            }
        }

    }

    public void getAnagrammSum(Deque<Item> list){
        if (list.size ()==0) return;
        saveResult(list);
        change(list);
        list.removeLeft ();
        getAnagrammSum(list);
    }

    private void saveResult(Deque<Item>list) {
        int weight = 0;
        //TODO Сделать итератор для TwoSideLinkedList
        for (Item item : list) {
            weight += item.getWeight ();
        }
        optimumWeight.add(weight);
        optimumList.add(list);
    }

    public void change(Deque<Item> list){
        //TODO Сделать итератор для TwoSideLinkedList
        for (Item item : list) {
            Item temp = list.removeLeft ();
            saveResult(list);
            list.insertRight (temp);
        }
    }
}
