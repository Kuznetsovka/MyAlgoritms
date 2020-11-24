package ru.kuznetsovka.myalgoritms.lesson7;

import java.util.*;

public class Graph {

    private final List<Vertex> vertexList;
    private final boolean[][] adjMat;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<> (maxVertexCount);
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    public void addEdge(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);

        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException ("Invalid label for vertex");
        }

        adjMat[startIndex][endIndex] = true;
        adjMat[endIndex][startIndex] = true;
    }

    public void addEdges(String startLabel, String secondLabel, String... others) {
        addEdge(startLabel, secondLabel);
        for (String other : others) {
            addEdge(startLabel, other);
        }
    }

    private int indexOf(String vertexLabel) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexLabel.equals(vertexList.get(i).getLabel())) {
                return  i;
            }
        }
        return -1;
    }


    public int getVertexSize() {
        return vertexList.size();
    }

    public void display() {
        for (int i = 0; i < getVertexSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getVertexSize(); j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    /**
     * англ. Depth-first search, DFS
     *
     * @param startLabel
     */
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException ("Invalid start label");
        }

        Stack<Vertex> stack = new Stack<> ();

        Vertex vertex = vertexList.get(startIndex);

        visitVertex(vertex, stack);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(vertex, stack);
            }
            else {
                stack.pop();
            }
        }

        resetVertexState();
    }

    /**
     * англ. breadth-first search, BFS
     *
     * @param startLabel
     */
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException ("Invalid start label");
        }

        Queue<Vertex> queue = new LinkedList<> ();

        Vertex vertex = vertexList.get(startIndex);

        visitVertex(vertex, queue);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
            }
            else {
                queue.remove();
            }
        }

        resetVertexState();
    }

    public Deque<Vertex> shortRoute(String startLabel, String finishLabel) {
        int startIndex = indexOf(startLabel);
        int finishIndex = indexOf(finishLabel);
        Vertex finishVertex = vertexList.get(finishIndex);
        Vertex vertex = vertexList.get(startIndex);
        if (startIndex == -1 || finishIndex== -1) {
            throw new IllegalArgumentException ("Invalid labels");
        }

        Queue<Vertex> queue = new LinkedList<> ();

        int countRoute = getCountNearUnvisitedVertex(vertex);
        List<Deque<Vertex>> routes = new ArrayList<> ();
        for (int i = 0; i < countRoute; i++) {
            routes.add(new LinkedList<> ());
        }
        for (Deque<Vertex> route : routes) {
            route.add(vertex);
        }
        visitVertex(vertex, queue);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(vertex, queue);
                Deque<Vertex> route = buildRoutes (finishVertex, vertex, routes);
                if (route != null) return route;
            } else {
                queue.remove();
            }
        }
        resetVertexState();
        return null;
    }

    private Deque<Vertex> buildRoutes(Vertex finishVertex, Vertex vertex, List<Deque<Vertex>> routes) {
        for (Deque<Vertex> route : routes) {
            Vertex vertexRoute = route.getLast ();
            if (vertexRoute.equals (getNearVisitedVertex(vertex))) {
                route.add (vertex);
                if (vertex.equals (finishVertex))
                    return route;
                break;
            }
        }
        return null;
    }

    private Vertex getNearVisitedVertex(Vertex peek) {
        int peekIndex = vertexList.indexOf(peek);
        for (int i = 0; i < getVertexSize(); i++) {
            if (adjMat[peekIndex][i] && vertexList.get(i).getVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex peek) {
        int peekIndex = vertexList.indexOf(peek);
        for (int i = 0; i < getVertexSize(); i++) {
            if (adjMat[peekIndex][i] && !vertexList.get(i).getVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private Integer getCountNearUnvisitedVertex(Vertex peek) {
        int peekIndex = vertexList.indexOf(peek);
        int count=0;
        for (int i = 0; i < getVertexSize(); i++) {
            if (adjMat[peekIndex][i] && !vertexList.get(i).getVisited()) {
                count++;
            }
        }
        return count;
    }

    private void visitVertex(Vertex vertex, Stack<Vertex> stack) {
        stack.push(vertex);
        vertex.setVisited(true);
    }
    private void visitVertex(Vertex vertex, Queue<Vertex> queue) {
        queue.add(vertex);
        vertex.setVisited(true);
    }
}
