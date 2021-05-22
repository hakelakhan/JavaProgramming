package com.lakhan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ReverseGraph {
    //Function to find number of strongly connected components in the graph.
    static ArrayList<ArrayList<Integer>> reverseEdges(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<ArrayList<Integer>> reversed = new ArrayList<>();

        for(int i = 0; i < adj.size(); i++)
            reversed.add(new ArrayList<>());
        //[[2, 3], [0], [1], [4], []]
        //[  [1]  [2] [0] [0], [3]]
        for(int i = 0; i < adj.size(); i++) {
            ArrayList<Integer> integers = adj.get(i);
            for (Integer edge: integers) {
                    reversed.get(edge).add(i);
            }
        }
/*
        for(int i = 0; i < adj.size(); i++) {
            ArrayList<Integer> edgeFromI = adj.get(i);     //2, 3
            for(int e : edgeFromI) {
                if(i == 2) {
                    System.out.println("i = " + i);
                    System.out.println("e = " + e);
                    System.out.println(reversed.get(e));
                    reversed.get(e).add(i);
                    System.out.println(reversed.get(e));
                }
            }
        }
        System.out.println(reversed);*/
        return reversed;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader
                (System.in));

        ArrayList<ArrayList<Integer>> adj ;

        String[] input = br.readLine().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        adj = new ArrayList<>();
        for(int i = 0; i < V; i++)
            adj.add(new ArrayList<>());

        for(int i = 0 ; i < E; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            if(adj.get(u) == null )
                adj.set(u, new ArrayList<>());
            adj.get(u).add(v);
        }
        System.out.println(adj);
        int scc = kosaraju(V, adj);
        System.out.println("Found Strongly connected components " + scc);
    }

    static ArrayList<Integer> getConnectedNodes(ArrayList<ArrayList<Integer>> adj, int u) {
        return adj.get(u);
    }
    private static int kosaraju(int V, ArrayList<ArrayList<Integer>> adj) {
        Set<Integer> visitedNodes = new HashSet<>();
        if( V < 0)
            return 0;
        int unvisitedNode = getUnvisitedNode(visitedNodes, V);
        Stack<Integer> traversalStack = new Stack<>();
        while(unvisitedNode != -1) {
            DFSUtils(adj, unvisitedNode, visitedNodes, traversalStack);
            unvisitedNode = getUnvisitedNode(visitedNodes, V);
        }
        ArrayList<ArrayList<Integer>> reversed = reverseEdges(adj);

        visitedNodes.clear();
        int scc = 0;
        unvisitedNode = getUnvisitedNode(visitedNodes, traversalStack);
        while(unvisitedNode != -1) {
            System.out.println("------------------------------------------------------------");
            DFSUtilsReverse(reversed, unvisitedNode, visitedNodes);
            System.out.println("\n------------------------------------------------------------");
            unvisitedNode = getUnvisitedNode(visitedNodes, traversalStack);
            scc++;
        }
        return scc;
    }

    private static int getUnvisitedNode(Set<Integer> visitedNodes, Stack<Integer> traversalStack) {
        while(!traversalStack.isEmpty()) {
            int s = traversalStack.pop();
            if(!visitedNodes.contains(s)) return s;
        }
        return -1;
    }

    private static void DFSUtilsReverse(ArrayList<ArrayList<Integer>> reversed, int s, Set<Integer> visitedNodes) {
        if(!visitedNodes.contains(s)) {
            System.out.printf("%-5d", s);
            visitedNodes.add(s);
            ArrayList<Integer> connectedNodes = getConnectedNodes(reversed, s);
            connectedNodes.stream().filter(node -> !visitedNodes.contains(node)).forEach(
                    node -> {
                        DFSUtilsReverse(reversed, node, visitedNodes);
                    }
            );
        }
    }

    private static void DFSUtils(ArrayList<ArrayList<Integer>> adj, int s, Set<Integer> visitedNodes, Stack<Integer> traversalStack) {
        if(!visitedNodes.contains(s)) {
            visitedNodes.add(s);

            ArrayList<Integer> connectedNodes = getConnectedNodes(adj, s);
            connectedNodes.stream().filter(node -> !visitedNodes.contains(node)).forEach(
                    node -> {
                        DFSUtils(adj, node, visitedNodes, traversalStack);
                    }
            );
        }
        traversalStack.push(s);
    }

    private static int getUnvisitedNode(Set<Integer> visitedNodes, int maximumNodes) {
        for(int i = 0; i < maximumNodes; i++) {
            if(visitedNodes.contains(i)) continue;
            return i;
        }
        return -1;
    }
}

/*
Sample Input
5 5
1 0
0 2
2 1
0 3
3 4
Output
3
 */