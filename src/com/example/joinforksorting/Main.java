package com.example.joinforksorting;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
      int[] arr = {328, 415, 625, 17, 56, 520, 918, 1040, 225, 316, 217};
      QuickSort sortedArray = new QuickSort(arr, 0, 10, 3);
      ForkJoinPool pool = new ForkJoinPool();
      pool.invoke(sortedArray);
      pool.close();
      for (int i : arr) {
	System.out.print(i + " ");
      }
    }
}
