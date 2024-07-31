package com.thebooleanguy.dictionary.service;

import java.util.List;
import com.thebooleanguy.dictionary.model.Word;

public class QuickSort {

    // Public method to call QuickSort on a List of Word objects
    public static void quickSort(List<Word> list) {
        qs(list, 0, list.size() - 1);
    }

    // Recursive QuickSort method for a List of Word objects
    private static void qs(List<Word> list, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int pivotIdx = partition(list, lo, hi);

        // Sub-sort Left side
        qs(list, lo, pivotIdx - 1);
        // Sub-sort Right side
        qs(list, pivotIdx + 1, hi);
    }

    // Partition method to return the pivot index for a List of Word objects
    private static int partition(List<Word> list, int lo, int hi) {
        Word pivot = list.get(hi);
        int idx = lo - 1;

        for (int i = lo; i < hi; i++) {
            if (list.get(i).getFrequency() > pivot.getFrequency()) { // Note: Sorting in descending order
                idx++;
                swap(list, i, idx);
            }
        }

        idx++;
        swap(list, idx, hi);

        return idx;
    }

    // Swap method to swap elements in the List of Word objects
    private static void swap(List<Word> list, int i, int j) {
        Word temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

