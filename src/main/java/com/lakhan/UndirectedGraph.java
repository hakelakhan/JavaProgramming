package com.lakhan;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class UndirectedGraph implements Graph{
    int V;
    ArrayList<ArrayList<Integer>> adj;
    UndirectedGraph(int v) {
        this.V = v;
        initializeAdjacencyList(V);
    }

    UndirectedGraph(int v, ArrayList<ArrayList<Integer>> adj) {
       this.V = v;
       this.adj = adj;
    }

    private void initializeAdjacencyList(int maximumNodes) {
        this.adj = new ArrayList<>();
        for(int i = 0; i < maximumNodes; i++) {
            this.adj.add(new ArrayList<>());
        }
    }

    @Override
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    @Override
    public ArrayList<Integer> getConnectedVertices(int u) {
        return adj.get(u);
    }

    boolean isEmpty() {
        return V == 0;
    }
    public int isEularCircuitExist() {
        if(this.isEmpty())
            return 0;
        if(containsCycleInConnectedGraph())
            return 1;
        else
            return 0;
    }

    private boolean containsCycleInConnectedGraph() {
        return !isAcyclic();
    }

    boolean isAcyclic() {
        if(isEmpty())
            return false;
        GraphMetaData output = bfs(0);
        System.out.println(output);
        return output.acyclic;
    }
    public GraphMetaData bfs(int s) {
        GraphMetaData output = new GraphMetaData();
        output.acyclic = true;
        output.connected = true;
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(s);
        while(!q.isEmpty()) {
            int node = q.remove();
            visited.add(node);
            List<Integer> adjacentUnvisitedNodes = getConnectedVertices(node).stream().
                    filter(n -> !visited.contains(n)).collect(Collectors.toList());
                for(Integer unvisitedNode  : adjacentUnvisitedNodes) {
                    if (q.contains(unvisitedNode))
                        output.acyclic = false;
                    else
                        q.add(unvisitedNode);
                }
        }
        if(getNextUnvisitedNode(visited, V) != -1)
            output.connected = false;
        return output;
    }
    public void bfs(int s, Consumer<Integer> consumer) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.add(s);
        while(!q.isEmpty()) {
            int node = q.remove();
            visited.add(node);
            consumer.accept(node);
            getConnectedVertices(node).stream().
                    filter(n -> !visited.contains(n) && !q.contains(n))
                .forEach(unvisitedNode ->  {
                    q.add(unvisitedNode);
                });
        }
    }
    boolean isGraphConnected() {
        Set<Integer> visitedNodes = new HashSet<>();
        //dfs(0, visitedNodes, (n -> System.out.printf("%-5d", n)));
        dfs(0, visitedNodes, noOp -> {});
        return areAllNodesVisited(visitedNodes, V);
    }

    private boolean areAllNodesVisited(Set<Integer> visitedNodes, int maximumNodes) {
        return getNextUnvisitedNode(visitedNodes, maximumNodes) == -1;
    }
    int getNextUnvisitedNode(Set<Integer> visitedNodes, int maximumNodes) {

        for(int i = 0; i < maximumNodes; i++) {
            if(!visitedNodes.contains(i)) return i;
        }
        return -1;
    }

    void dfs(int s, Set<Integer> visitedNodes, Consumer<Integer> consumer) {
        if(!visitedNodes.contains(s)) {
            visitedNodes.add(s);
            consumer.accept(s);
            ArrayList<Integer> connectedVertices = getConnectedVertices(s);
            connectedVertices.forEach( node -> dfs(node, visitedNodes, consumer));
        }
    }
}
