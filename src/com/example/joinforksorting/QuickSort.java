package com.example.joinforksorting;
import java.util.concurrent.RecursiveAction;

public class QuickSort extends RecursiveAction{
  private int[] array;
  private int left, right;
  private int threshold;

  
  public QuickSort(int[] arr, int l, int r, int t) {
    this.array = arr;
    this.left = l;
    this.right = r;
    this.threshold = t;
  }

  @Override
  protected void compute() {
    if (right-left < threshold) {
      insertionSort(array, left, right);
    } else {
      int pivot = partition(array, left, right);
      QuickSort leftTask = new QuickSort(array, left, pivot - 1, threshold);
      QuickSort rightTask = new QuickSort(array, pivot + 1, right, threshold);
      invokeAll(leftTask, rightTask);
    }
  }

  private void swap(int[] array, int a, int b) {
    int tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
  }

  private int partition(int[] array, int start, int end) {
    int pivot = array[end];
    int i = start - 1;
    for (int j = start; j < end; j++) {
      if (array[j] <= pivot) {
	i++;
	swap(array, i, j);
      }
    }
    swap(array, i + 1, end);
    return i + 1;
  }

  private void insertionSort(int[] array, int start, int end) {
    for (int i = start + 1; i <= end; i++) {
      int key = array[i];
      int j = i - 1;
      while (j >= start && array[j] > key) {
	array[j + 1] = array[j];
	j--;
      }
      array[j + 1] = key;
    }
  }
}
