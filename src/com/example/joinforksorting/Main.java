package com.example.joinforksorting;
import java.util.concurrent.ForkJoinPool;
import com.example.joinforksorting.MergeSort;

public class Main {
    public static void main(String[] args) {
      int[] arr = {328, 415, 625, 17, 56, 520, 918, 1040, 225, 316, 217};
      int[] arr2 = {328, 415, 625, 17, 56, 520, 918, 1040, 225, 316, 217};
      QuickSort sortedArray = new QuickSort(arr, 0, 10, 3);
      MergeSort sortedArrayMergeSort = new MergeSort(arr2, 0, 10, 3);

      ForkJoinPool pool = new ForkJoinPool();
      pool.invoke(sortedArray);

      ForkJoinPool pool2 = new ForkJoinPool();
      pool2.invoke(sortedArrayMergeSort);
      pool.close();
      pool2.close();

      for (int i : arr) {
	System.out.print(i + " ");
      }
      System.out.print("\n");
      for (int i : arr2) {
	System.out.print(i + " ");
      }
    }
}
