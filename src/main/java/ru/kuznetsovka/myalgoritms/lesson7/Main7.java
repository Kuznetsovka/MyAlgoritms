package ru.kuznetsovka.myalgoritms.lesson7;

import java.util.Deque;

public class Main7 {

    public static void main(String[] args) {
        testGraph();
    }

    private static void testGraph() {
        Graph graph = new Graph(10);
        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Липецк");
        graph.addVertex("Тамбов");
        graph.addVertex("Орел");
        graph.addVertex("Саратов");
        graph.addVertex("Курск");
        graph.addVertex("Воронеж");
        graph.addEdges("Москва", "Тула", "Рязань","Калуга");
        graph.addEdges("Воронеж", "Липецк", "Саратов","Курск");
        graph.addEdge ("Тула","Липецк");
        graph.addEdge ("Рязань","Тамбов");
        graph.addEdge ("Калуга","Орел");
        graph.addEdge ("Тамбов","Саратов");
        graph.addEdge ("Орел","Курск");
        Deque<Vertex> route =  graph.shortRoute("Москва","Воронеж");
        System.out.println("Кратчайший маршрут!");
        int count=0;
        for (Vertex vertex : route) {
            System.out.print (vertex.getLabel ());
            count++;
            if (route.size ()!=count)
                System.out.print (" -> ");
        }
    }
}

