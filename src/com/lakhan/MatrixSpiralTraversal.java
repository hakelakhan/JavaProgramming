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

        printTraversal(0, 0, rows, cols, matrix, RIGHT);
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

    private static void printTraversal(int i, int j, int rows, int cols, int[][] matrix, Direction currentDirection) {
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
            printTraversal(i, j, rows, cols, matrix, currentDirection);//return this;
    }


    private static boolean isVisited(int i, int j) {
        return visitedPositions.contains(i + "-"+j) ;
    }
}
