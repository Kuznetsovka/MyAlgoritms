package ru.kuznetsovka.myalgoritms.lesson5;

import java.util.ArrayList;

public class Main {
    private static ArrayList<Item> list;
    public static void main(String[] args) {
        System.out.println (myPower(2,2));
        System.out.println (myPower(3,3));
        System.out.println (myPower(2,10));
        Bag bag = new Bag(100);
        initItem();
        bag.pack(list);
    }

    private static void initItem() {
        Item item1 = new Item("1",15,10);
        Item item2 = new Item("2",101,5);
        Item item3 = new Item("3",30,20);
        Item item4 = new Item("4",50,4);
        Item item5 = new Item("5",5,3);
        Item item6 = new Item("6",15,1);
        Item item7 = new Item("7",30,3);
        list = new ArrayList<> ();
        list.add (item1);
        list.add (item2);
        list.add (item3);
        list.add (item4);
        list.add (item5);
        list.add (item6);
        list.add (item7);
    }

    private static double myPower(double number, int pow) {
        if (number == 0)
            if (pow > 0)
                return 0;
            else
                throw new IllegalArgumentException("Invalid pow " + pow + " for number = 0");
        if (pow==1) {
            return number;
        } else if (pow > 0) {
            return number*myPower(number, pow-1);
        } else {
            return 1 / (number*myPower(number, -pow-1));
        }
    }
}
