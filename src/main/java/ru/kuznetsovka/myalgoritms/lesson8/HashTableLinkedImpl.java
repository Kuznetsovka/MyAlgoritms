package ru.kuznetsovka.myalgoritms.lesson8;

import java.util.*;

public class HashTableLinkedImpl<K, V> implements HashTable<K, V> {
    private final int maxSize;

    public HashTableLinkedImpl(int maxSize) {
        this.maxSize = maxSize;
        this.data = new LinkedList [maxSize];
    }


    static class Node<K, V> implements HashTable.Entry<K, V> {

        private final K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private final LinkedList<Node<K, V>> data[];
    private int size;

    @Override
    public boolean put(K key, V value) {
        int index = hash(key);

        if (size == maxSize) {
            return false;
        }

        while(data[index] != null) {
            for (Node<K, V> node : data[index]) {
                if (node.getKey ().equals (key)) {
                    data[index].add(new Node<> (key, value));
                    return true;
                }
            }
            index = index + getStep(key);
            index %= data.length;
        }
        data[index] = new  LinkedList<> ();
        data[index].add (new Node<> (key, value));
        size++;
        return true;
    }

    protected int getStep(K key) {
        return 1;
    }

    private int hash(K key) {
        return key.hashCode() % data.length;
    }

    //Не выходит
    @Override
    public V get(K key) {
        int index = indexOf(key);
        if (index==-1)
            return null;
        for (Node<K, V> datum : data[index]) {
            if (datum.getKey ().equals (key))
                return datum.getValue ();
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = indexOf(key);
        if (index == -1) {
            return null;
        }
        for (Node<K, V> datum : data[index]) {
            if (datum.getKey ().equals (key)) {
                Node<K, V> node = datum;
                data[index].remove (datum);
                size--;
                return node.getValue();
            }
        }

        return null;
    }

    private int indexOf(K key) {
        int index = hash(key);
        while (data[index].size ()!= 0) {
            for (Node<K, V> node : data[index]) {
                if (node.getKey ().equals (key)) {
                    return index;
                }
            }
            index = index + getStep(key);
            index %= data.length;
        }
        return -1;
    }

    @Override
    public V remove(K key, V value) {
        int index = indexOf(key);
        if (index == -1) {
            return null;
        }
        LinkedList<Node<K, V>> nodes = data[index];
        if (nodes.size ()>1)
            for (Node<K, V> node : nodes) {
                if (node.getValue().equals (value))
                    node = null;
                size--;
                return node.getValue();
            }
        data[index] = null;
        size--;
        return nodes.get(index).getValue ();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println("----------");
        for (int i = 0; i < data.length; i++) {
                System.out.printf("%d = [%s]%n", i, data[i]);
        }
        System.out.println("----------");
    }
}
