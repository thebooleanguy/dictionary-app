package com.thebooleanguy.dictionary.dataStructure.algorithms;

import com.thebooleanguy.dictionary.model.Word;

import java.util.List;

public class QuickSort {

    // Public method to call QuickSort with an option to skip the first element
    public static void quickSort(List<Word> list, boolean skipFirstElement) {
        int lo = skipFirstElement ? 1 : 0;
        int hi = list.size() - 1;

        qs(list, lo, hi);
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
