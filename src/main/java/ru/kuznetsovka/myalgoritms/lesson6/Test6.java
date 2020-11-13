package ru.kuznetsovka.myalgoritms.lesson6;

import java.util.ArrayList;
import java.util.List;

public class Test6 {
    /*Создать и запустить программу для построения двоичного дерева. В цикле построить двадцать деревьев с глубиной в 4 уровней.
    Данные, которыми необходимо заполнить узлы деревьев, представляются в виде чисел типа int. Число, которое попадает в узел,
    должно генерироваться случайным образом в диапазоне от -100 до 100.*/
    private static List<TreeImpl<Integer>> list = new ArrayList<> ();
    private static final int COUNT_TREES = 20;
    private static final int MAX_LEVEL = 4;
    public static void main(String[] args) {
        initTrees();
        int count=0;
        for (TreeImpl<Integer> tree : list)
            if (tree.isBalanced(tree.getRoot())) {
                System.out.println ("Tree is balanced");
            } else {
                System.out.println ("Tree is not balanced");
                count++;
            }
        System.out.println ("Процент несбалансированных деревьев: " + (count*100/COUNT_TREES) + "%");

    }

    private static void initTrees() {
        int count = (int) (Math.pow (2,MAX_LEVEL)-1);
        for (int i = 0; i < COUNT_TREES; i++) {
            list.add(new TreeImpl<>(MAX_LEVEL));
            for (int j = 0; j < count; j++) {
                list.get(i).add((int) (Math.random ()*200-100));
            }
            list.get(i).display();
        }
    }

}
