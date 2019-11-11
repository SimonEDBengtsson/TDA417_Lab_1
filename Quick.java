/**
 *  The {@code Quick} class provides static methods for sorting an
 *  array and selecting the ith smallest element in an array using quicksort.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/23quick">Section 2.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
import java.awt.*;
import java.util.*;

public class Quick {
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    private static int maxDepth=100;
    private static final int insertionSortSizeLimit=30;
    public static void sort(int[] a) {
        // To do: try randomising the array before sorting.
        if(isSorted(a)) return;
        maxDepth=(int)(Math.log(a.length)*5);
        sort(a, 0, a.length - 1,1);
        assert isSorted(a);
    }

    // quicksort the subarray from a[lo] to a[hi]
    public static void sort(int[] a, int lo, int hi, int depth) {
        // To do: try switching to insertion sort if a[lo..hi] is small.
        if (hi <= lo) return;
        if((hi-lo)<insertionSortSizeLimit){
            Insertion.sort(a,lo,hi);
            return;
        }
        if(depth>maxDepth){
            Heap.sort(a,lo,hi);
            return;
        }


        int j = partition(a, lo, hi);
        sort(a, lo, j-1,depth+1);
        sort(a, j+1, hi,depth+1);
        assert isSorted(a, lo, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(int[] a, int lo, int hi){
        // choose pivot as median of first, middle and last, swap it to end
        int pivot=median3(a,lo,hi,(lo+hi)/2);
        exch(a,pivot,hi);
        int v=a[hi];// pivot value
        // the main loop
        int i = lo;
        int j = hi-1;
        while (i < j) {
            while (a[i] <= v && i!=j){
                i++;
            }
            while (v <= a[j] && i!=j){
                j--;
            }
            exch(a, i, j);
        }
        if(v>a[j]) {
            exch(a, hi, j+1);
            return j+1;
        }
        exch(a,hi,j);
        return j;
    }

   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // return the index of the median element among a[i], a[j], and a[k]
    private static final int median3(int[] a, int i, int j, int k) {
        if (a[i] <= a[j]) {
            if (a[j] <= a[k]) return j;      /* a[i] <= a[j] <= a[k] */
            else if (a[i] <= a[k]) return k; /* a[i] <= a[k] <  a[j] */
            else return i;                   /* a[k] <  a[i] <= a[j] */
        } else {
            if (a[k] <= a[j]) return j;      /* a[k] <= a[j] <  a[i] */
            else if (a[k] <= a[i]) return k; /* a[j] <  a[k] <= a[i] */
            else return i;                   /* a[j] <  a[i] <  a[k] */
        }
    }

   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(int[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (a[i] < a[i-1]) return false;
        return true;
    }

    // This class should not be instantiated.
    private Quick() { }
    private static class Heap{
        public static void sort(int[] array, int minBound, int maxBound){
            heapify(array,minBound,maxBound);// structure array as a max heap
            for(int i=maxBound;i>minBound;i--){
                swap(array,minBound,i);// swap the largest element in the heap to the sorted region
                siftDown(array,minBound,minBound,i-1);// rebuild the heap
            }
        }
        private static void swap(int[] array, int element_1, int element_2){// swap element 1 and 2 in an array
            int mem=array[element_1];
            array[element_1]=array[element_2];
            array[element_2]=mem;
        }
        private static void siftDown(int[] array, int i, int minBound, int maxBound){// swaps an element down to maintain the heap
            int child_1_index=2*i-minBound+1;
            if (child_1_index>maxBound) {
                return;
            }
            int child_2_index=child_1_index+1;
            if (child_2_index>maxBound) {
                if (array[child_1_index]>array[i]) {
                    swap(array,i,child_1_index);
                }
            }
            else {
                int largerChildIndex=array[child_1_index]>array[child_2_index] ? child_1_index : child_2_index;
                if (array[largerChildIndex]>array[i]) {
                    swap(array,i,largerChildIndex);
                    siftDown(array,largerChildIndex,minBound,maxBound);
                }
            }
        }
        private static void heapify(int[] array, int minBound, int maxBound){
            for (int i = (maxBound+minBound)/2; i>=minBound; i--){
                siftDown(array,i,minBound,maxBound);
            }
        }
    }
}

/******************************************************************************
 *  Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
