package ru.kuznetsovka.myalgoritms.lesson2;

public class TestArray {
    public static final int SIZE = 100000;
    static Array<Integer> data = new ArrayImpl<>(SIZE);
    public static void main(String[] args) {
        System.out.println ("Инициализация массива");
        InitData();
        Array<Integer> testArr1 = new ArrayImpl<>(SIZE);
        Array<Integer> testArr2 = new ArrayImpl<>(SIZE);
        Array<Integer> testArr3 = new ArrayImpl<>(SIZE);
        System.out.println ("Копирование массива");
        fillArrays(data,testArr1,testArr2,testArr3);
        //Можно было бы сделать без дублирования кода, но это приведет к лишним проверкам
        // и поставить тесты в неравые условия. Доли милисекунд, но те не менее.
        testBubble (testArr1);
        System.out.println ("****************");
        testSelect(testArr2);
        System.out.println ("****************");
        testInsert(testArr3);
        System.out.println ("****************");
    }

    private static void fillArrays(Array<Integer> data, Array<Integer> testArr1, Array<Integer> testArr2, Array<Integer> testArr3) {
        for (int i = 0; i < SIZE; i++) {
            testArr1.add(data.get (i));
            testArr2.add(data.get (i));
            testArr3.add(data.get (i));
        }
    }

    private static void testBubble(Array<Integer> testArr) {
        System.out.println ("Начало сортировки BUBBLE");
        long start = System.currentTimeMillis ();
        testArr.sortBubble ();
        long finish = System.currentTimeMillis ();
        long time = (finish-start)/1000;
        System.out.println ("Затрачено " + time + " секунд!");
    }

    private static void testSelect(Array<Integer> testArr) {
        System.out.println ("Начало сортировки SELECT");
        long start = System.currentTimeMillis ();
        testArr.sortSelect ();
        long finish = System.currentTimeMillis ();
        long time = (finish-start)/1000;
        System.out.println ("Затрачено " + time + "секунд!");
    }

    private static void testInsert(Array<Integer> testArr) {
        System.out.println ("Начало сортировки INSERT");
        long start = System.currentTimeMillis ();
        testArr.sortInsert ();
        long finish = System.currentTimeMillis ();
        long time = (finish-start)/1000;
        System.out.println ("Затрачено " + time + "секунд!");
    }

    private static void InitData(){
        for (int i = 0; i < SIZE; i++) {
            data.add(((int) (Math.random ()*SIZE)));
        }
    }
}
