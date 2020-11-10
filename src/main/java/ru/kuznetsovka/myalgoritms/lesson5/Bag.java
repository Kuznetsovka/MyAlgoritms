package ru.kuznetsovka.myalgoritms.lesson5;

import ru.kuznetsovka.myalgoritms.lesson4.SimpleLinkedListImpl;
import ru.kuznetsovka.myalgoritms.lesson4.TwoSideLinkedListImpl;

import java.util.*;
import java.util.stream.Collectors;


public class Bag {
    protected int capacity;
    protected Map<Integer,ArrayList<Item>>  optimum = new HashMap<> ();

    public Bag(int capacity) {
        this.capacity = capacity;
    }

    public void pack(ArrayList<Item> list){
        TwoSideLinkedListImpl<Item> linkedList = new TwoSideLinkedListImpl<> ();
        for (Item item : list) {
            linkedList.insertLast (item);
        }
        saveOneItemResult(linkedList);
        saveResult(linkedList);
        searchSum(linkedList);
        optimum = optimum.entrySet().stream()
                .sorted(Map.Entry.<Integer,ArrayList<Item>>comparingByKey().reversed()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        showResult ();
    }

    private void showResult() {
        for(Map.Entry<Integer, ArrayList<Item>> entry : optimum.entrySet()) {
            Integer weight = entry.getValue().stream().mapToInt(Item::getWeight).sum();
            if (weight<=capacity) {
                System.out.println ("Оптимальный список: " + entry.getValue() + " оптимальный вес: " + weight + " стоимость: " + entry.getKey ());
                return;
            }
        }
        System.out.println ("Ни один элемент не помещается в рюкзак");
    }

    public void searchSum(TwoSideLinkedListImpl<Item> list){
        if (list.size ()==1) return;
        for (int i = 0; i < list.size(); i++) {
            Item temp = list.removeFirst ();
            searchSum(list);
            saveResult (list);
            change(list);
            list.insertLast (temp);
        }
    }

    private void saveOneItemResult(TwoSideLinkedListImpl<Item> list) {
        for (Item item : list) {
            ArrayList arr = new ArrayList<Item>();
            arr.add(item);
            optimum.put (item.getCost (),arr);
        }
    }

    private void saveResult(TwoSideLinkedListImpl<Item>list) {
        int cost = 0;
        ArrayList arr = new ArrayList<Item> ();
        for (Item item : list) {
            cost += item.getCost ();
            arr.add (item);
        }
        optimum.put (cost, arr);
    }

    public void change(TwoSideLinkedListImpl<Item> list){
        for (int i = 0; i < list.size(); i++) {
            Item temp = list.removeFirst ();
            saveResult(list);
            list.insertLast(temp);
        }
    }
}
