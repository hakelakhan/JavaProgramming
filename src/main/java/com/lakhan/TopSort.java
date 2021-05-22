package com.lakhan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TopSort {
    public static void main(String[] args) throws IOException {
        {
            BufferedReader br = new BufferedReader(new InputStreamReader
                    (System.in));

            String[] input = br.readLine().split(" ");
            int V = Integer.parseInt(input[0]);
            int E = Integer.parseInt(input[1]);
            DirectedGraph g = new DirectedGraph(V);
            for(int i = 0 ; i < E; i++) {
                String[] edge = br.readLine().split(" ");
                int u = Integer.parseInt(edge[0]);
                int v = Integer.parseInt(edge[1]);
                g.addEdge(u, v);
            }
            g.topSort();
        }
    }
}
/*
Test input
9 11
0 1
0 5
2 1
5 2
2 3
5 6
4 6
4 3
8 5
7 8
7 6
 */
/*
Output
[1, 3, 2, 6, 5, 0, 4, 8, 7]

 */