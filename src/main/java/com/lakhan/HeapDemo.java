package com.lakhan;

public class HeapDemo {
    static void heapify2(int[] a, int index , int heapsize) {
        while(index < heapsize) {
            int l = (index << 1) + 1;
            int r = l + 1;
            int max = index;
            if (l < heapsize && a[l] > a[max])
                max = l;
            if (r < heapsize && a[r] > a[max])
                max = r;
            if (max != index) {
                swap(a, index, max);
                index = max;
            }
            else {
                break;
            }
        }
    }

    private static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    static void heapify(int[] a, int index , int heapsize) {
        System.out.println("Heapifying index"  + index);
        int leftIndex = (index << 1 )+ 1;
        int rightIndex = (index << 1) + 2;

        int maxIndex = index;
        if(leftIndex < heapsize && a[leftIndex] > a[maxIndex]) {
            maxIndex = leftIndex;
        }
        if(rightIndex < heapsize && a[rightIndex] > a[maxIndex])
            maxIndex = rightIndex;
        if(maxIndex != index) {
            int tmp = a[maxIndex];
            a[maxIndex] = a[index];
            a[index] = tmp;
            printArray(a);
            heapify(a, maxIndex, heapsize);
        }
    }

    public static void buildHeap(int[] arr) {
        int n = arr.length ;
        for(int i = n / 2 -1   ; i >= 0 ; i--)
            heapify(arr, i, n);
    }
    public static void main(String args[]) {
        int[] arr = {23, 35, 435, 63, 345, 98, 945, 450, 8521, 7842, 245};
        int n = arr.length;
        buildHeap(arr);
        System.out.println("Final Heap array");
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
