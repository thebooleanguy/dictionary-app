package com.thebooleanguy.dictionary.dataStructure.algorithms;

import com.thebooleanguy.dictionary.model.Word;
import java.util.List;

public class QuickSort {

    /**
     * Public method to sort a list of Word objects using QuickSort.
     *
     * @param list The list of Word objects to be sorted.
     * @param skipFirstElement If true, the first element is not considered in the sorting.
     */
    public static void quickSort(List<Word> list, boolean skipFirstElement) {
        int lo = skipFirstElement ? 1 : 0; // Determine the starting index based on the skipFirstElement flag
        int hi = list.size() - 1; // Last index of the list

        // Perform QuickSort on the specified range
        qs(list, lo, hi);
    }

    /**
     * Recursive QuickSort method.
     *
     * @param list The list of Word objects to be sorted.
     * @param lo The starting index of the sublist to be sorted.
     * @param hi The ending index of the sublist to be sorted.
     */
    private static void qs(List<Word> list, int lo, int hi) {
        if (lo >= hi) {
            return; // Base case: If the sublist has one or no elements, it's already sorted
        }

        int pivotIdx = partition(list, lo, hi); // Partition the list and get the pivot index

        // Recursively sort the left and right sublists
        qs(list, lo, pivotIdx - 1);
        qs(list, pivotIdx + 1, hi);
    }

    /**
     * Partition method to arrange elements around a pivot.
     *
     * @param list The list of Word objects to be partitioned.
     * @param lo The starting index of the sublist to be partitioned.
     * @param hi The ending index of the sublist to be partitioned.
     * @return The index of the pivot after partitioning.
     */
    private static int partition(List<Word> list, int lo, int hi) {
        Word pivot = list.get(hi); // Choose the last element as the pivot
        int idx = lo - 1; // Index of the smaller element

        // Iterate through the list and rearrange elements based on the pivot
        for (int i = lo; i < hi; i++) {
            // Change the comparison here to sort in descending order based on frequency
            if (list.get(i).getFrequency() > pivot.getFrequency()) {
                idx++;
                swap(list, i, idx); // Swap elements to ensure elements greater than pivot are on the left
            }
        }

        idx++;
        swap(list, idx, hi); // Move the pivot element to its correct position

        return idx; // Return the index of the pivot
    }

    /**
     * Swap method to exchange elements at two indices in the list.
     *
     * @param list The list of Word objects.
     * @param i The index of the first element to be swapped.
     * @param j The index of the second element to be swapped.
     */
    private static void swap(List<Word> list, int i, int j) {
        Word temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
