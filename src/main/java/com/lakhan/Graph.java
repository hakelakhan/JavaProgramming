package com.lakhan;

import java.util.ArrayList;

public interface Graph {
    void addEdge(int u, int v);
    ArrayList<Integer> getConnectedVertices(int u);
}
