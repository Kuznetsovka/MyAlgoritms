package ru.kuznetsovka.myalgoritms.lesson5;

public class Item implements Comparable<Item>{
    private int weight;
    private String name;
    private int cost;

    public int getCost() {
        return cost;
    }

    public Item(String name, int weight, int cost) {
        this.weight = weight;
        this.name = name;
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass () != obj.getClass ()) return false;
        Item item = (Item) obj;
        return weight== item.getWeight ();
    }

    @Override
    public String toString() {
        return "Item{" +
                "weight=" + weight +
                "cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Item o) {
        return weight > o.getWeight () ? 1 : weight == o.getWeight () ? 0 : -1;
    }
}
