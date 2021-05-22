package com.lakhan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

public class DirectedGraph extends UndirectedGraph{
    DirectedGraph(int v) {
        super(v);
    }

    DirectedGraph(int v, ArrayList<ArrayList<Integer>> adj) {
        super(v, adj);
    }

    @Override
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    public void topSort() {
        if(!isAcyclic())
            return;
        Set<Integer> visitedNodes = new HashSet<>();
        Stack<Integer> topSort = new Stack<>();
        int unvisitedNode = 0;
        while(unvisitedNode != -1) {
//            dfs(unvisitedNode, visitedNodes, n -> System.out.printf("%-5d", n));
            dfs(unvisitedNode, visitedNodes, n -> {}, topSort);
            unvisitedNode = getNextUnvisitedNode(visitedNodes, V);
        }
        System.out.println(topSort.toString());
    }
    void dfs(int s, Set<Integer> visitedNodes, Consumer<Integer> consumer, Stack<Integer> topSort) {
        if(!visitedNodes.contains(s)) {
            visitedNodes.add(s);
            consumer.accept(s);
            ArrayList<Integer> connectedVertices = getConnectedVertices(s);
            connectedVertices.forEach( node -> dfs(node, visitedNodes, consumer, topSort));
            topSort.push(s);
        }
    }
}
