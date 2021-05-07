package com.lakhan;
import java.util.*;

import static com.lakhan.MatrixSpiralTraversal.Direction.*;

public class MatrixSpiralTraversal {
    static Set<String> visitedPositions = new HashSet<>();
    public static void main(String[] args) {
        int matrix[][];
        int rows, cols;
        Scanner sc = new Scanner(System.in);
        rows = sc.nextInt();
        cols = sc.nextInt();
        matrix = new int[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println("Matrix is as follows");
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                System.out.printf("%-10d", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Spiral Traversal as follows : ");

        //printTraversalMethod1(0, 0, rows, cols, matrix, DOWN);
        printTraversalMethod2(matrix, rows, cols);
    }

    enum Direction {
        RIGHT, DOWN, LEFT, UP;
        Direction getNext() {
            if(this.ordinal() == Direction.values().length - 1)
                return Direction.values()[0];
            if(this.ordinal() < Direction.values().length - 1  )
                return Direction.values()[this.ordinal()  + 1];
            return null;
        }
    }
    static boolean isInsideBoundray(int i, int j, int rows, int cols) {
        return i >= 0 && i < rows &&  j >= 0 && j < cols;
    }

    private static  void printTraversalMethod2 (int matrix[][], int rows, int cols) {
        int i = 0;
        int j = 0;

        int hu = 0;
        int vr = 0;
        int vl = 0;
        int hd = 0;
        while (true) {
            i = hu;
            for(j = vr; j < cols - vr; j++) {
                System.out.printf("%-5d", matrix[i][j]);
            }
            j--;
            hu++;       //1
            if(traversalCompleted(hu, hd, vl, vr, rows, cols)) return;
            for(i = hu; i < rows - hd; i++) {
                System.out.printf("%-5d", matrix[i][j]);
            }
            i--;
            vr++;       //1
            if(traversalCompleted(hu, hd, vl, vr, rows, cols)) return;
            for(j = j - vr; j >= vl; j--) {
                System.out.printf("%-5d", matrix[i][j]);
            }
            j++;
            hd++; //1
            if(traversalCompleted(hu, hd, vl, vr, rows, cols)) return;
            for(i = i - hd; i >= hu; i--) {
                System.out.printf("%-5d", matrix[i][j]);
            }
            i++;
            vl++;    //1;
            if(traversalCompleted(hu, hd, vl, vr, rows, cols)) return;
        }

    }

    private static boolean traversalCompleted(int hu, int hd, int vl, int vr, int rows, int cols) {
        return hu + hd == rows || vl + vr == cols;
    }

    //Time Complexity O(rows * cols) Space Complexity O(rows * cols)
    private static void printTraversalMethod1(int i, int j, int rows, int cols, int[][] matrix, Direction currentDirection) {
        if(isInsideBoundray(i, j, rows, cols) && !isVisited(i, j)) {
                    System.out.printf("%-5d", matrix[i][j]);
                    visitedPositions.add(i + "-" + j);
                    if(currentDirection == RIGHT)
                        j++;
                    else if(currentDirection == LEFT)
                        j--;
                    else if(currentDirection == UP)
                        i--;
                    else if(currentDirection == DOWN)
                        i++;
        }
        else {
            if(currentDirection == RIGHT) {
                j--;
                i++;
            }
            else if(currentDirection == LEFT) {
                j++;
                i--;
            }
            else if(currentDirection == UP) {
                i++;
                j++;
            }
            else if(currentDirection == DOWN) {
                i--;
                j--;
            }
            currentDirection = currentDirection.getNext();
        }
        if(visitedPositions.size() != rows * cols)
            printTraversalMethod1(i, j, rows, cols, matrix, currentDirection);//return this;
    }


    private static boolean isVisited(int i, int j) {
        return visitedPositions.contains(i + "-"+j) ;
    }
}
