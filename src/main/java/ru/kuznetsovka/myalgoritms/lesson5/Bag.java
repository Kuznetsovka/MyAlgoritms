package ru.kuznetsovka.myalgoritms.lesson5;

import ru.kuznetsovka.myalgoritms.lesson3.myqueue.Deque;
import ru.kuznetsovka.myalgoritms.lesson3.myqueue.DequeImpl;

import java.util.*;


public class Bag {
    protected int capacity;
    //TODO Поменять на TwoSideLinkedList
    protected Map<Integer,String>  optimum = new TreeMap<> ();

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
        int count=0;
        String tempList = null;
        Integer tempWeight=0;
        for(Map.Entry<Integer,String> entry : optimum.entrySet()) {
            Integer weight = entry.getKey();
            if (weight>capacity) {
                if (count==0)
                    System.out.println ("Ни один элемент не помещается в рюкзак");
                else
                    System.out.println ("Оптимальный список: " + tempList + " оптимальный вес: " + tempWeight);
                return;
            }
            count++;
            tempList = entry.getValue();
            tempWeight = entry.getKey ();
        }

    }

    public void getAnagrammSum(Deque<Item> list){
        if (list.size ()==0) return;
        saveResult(list);
        change(list);
        list.removeRight ();
        getAnagrammSum(list);
    }

    private void saveResult(Deque<Item>list) {
        int weight = 0;
        String temp = "";
        //TODO Сделать итератор для TwoSideLinkedList
        for (Item item : list) {
            weight += item.getWeight ();
            temp+=item + ",";
        }
        optimum.put (weight,temp);
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
