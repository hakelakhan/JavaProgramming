package com.lakhan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CycleInGraph {
    public static int isEularCircuitExist(UndirectedGraph g) {
        return g.isEularCircuitExist();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader
                (System.in));


        String[] input = br.readLine().split(" ");
        int V = Integer.parseInt(input[0]);
        int E = Integer.parseInt(input[1]);
        UndirectedGraph g = new UndirectedGraph(V);
        for(int i = 0 ; i < E; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]);
            int v = Integer.parseInt(edge[1]);
            g.addEdge(u, v);
        }
        isEularCircuitExist(g);
    }
}
