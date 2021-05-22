package com.lakhan;


public class GraphMetaData {
    boolean acyclic;
    boolean connected;

    @Override
    public String toString() {
        return "GraphMetaData{" +
                "acyclic=" + acyclic +
                ", connected=" + connected +
                '}';
    }
}
