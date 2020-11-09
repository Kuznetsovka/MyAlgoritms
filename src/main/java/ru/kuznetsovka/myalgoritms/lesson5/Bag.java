package ru.kuznetsovka.myalgoritms.lesson5;

import ru.kuznetsovka.myalgoritms.lesson3.myqueue.Deque;
import ru.kuznetsovka.myalgoritms.lesson3.myqueue.DequeImpl;

import java.util.*;


public class Bag {
    protected int capacity;
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
        saveOneItemResult(dequeList);
        searchSum(dequeList);
        showResult ();
    }

    private void showResult() {
        int count=0;
        String tempList = null;
        int tempWeight=0;
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

    public void searchSum(Deque<Item> list){
        if (list.size ()==1) return;
        saveResult(list);
        change(list);
        list.removeRight ();
        searchSum(list);
    }

    private void saveOneItemResult(Deque<Item> list) {
        for (Item item : list) {
            optimum.put (item.getWeight (), String.valueOf (item));
        }
    }

    private void saveResult(Deque<Item>list) {
        int weight = 0;
        StringBuilder temp = new StringBuilder();
        for (Item item : list) {
            weight += item.getWeight ();
            temp.append (item);
            temp.append (",");
        }
        optimum.put (weight, String.valueOf (temp));
    }

    public void change(Deque<Item> list){
        for (Item item : list) {
            Item temp = list.removeLeft ();
            saveResult(list);
            list.insertRight (temp);
        }
    }
}
