package com.thebooleanguy.dictionary.service;

public class QuickSort {

    // Public method to call QuickSort
    public static void quickSort(int[] arr) {
        qs(arr, 0, arr.length - 1);
    }

    // Recursive QuickSort method
    private static void qs(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int pivotIdx = partition(arr, lo, hi);

        // Sub-sort Left side
        qs(arr, lo, pivotIdx - 1);
        // Sub-sort Right side
        qs(arr, pivotIdx + 1, hi);
    }

    // Partition method to return the pivot index
    private static int partition(int[] arr, int lo, int hi) {
        int pivot = arr[hi];
        int idx = lo - 1;

        for (int i = lo; i < hi; i++) {
            if (arr[i] <= pivot) {
                idx++;
                swap(arr, i, idx);
            }
        }

        idx++;
        swap(arr, idx, hi);

        return idx;
    }

    // Swap method to swap elements in the array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] arr = { 34, 7, 23, 32, 5, 62 };
        quickSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}

