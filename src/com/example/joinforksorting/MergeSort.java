package com.example.joinforksorting;

import java.util.concurrent.RecursiveAction;

public class MergeSort extends RecursiveAction {
    private int[] array;
    private int left, right;
    private int threshold;

    public MergeSort(int[] array, int left, int right, int threshold) {
        this.array = array;
        this.left = left;
        this.right = right;
        this.threshold = threshold;
    }

    @Override
    protected void compute() {
        if (right - left < threshold) {
            insertionSort(array, left, right);
        } else {
            int mid = (left + right) / 2;
            MergeSort leftTask = new MergeSort(array, left, mid, threshold);
            MergeSort rightTask = new MergeSort(array, mid + 1, right, threshold);
            invokeAll(leftTask, rightTask);
            merge(array, left, mid, right);
        }
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

    private void merge(int[] array, int left, int mid, int right) {
        int[] leftArray = new int[mid - left + 1];
        int[] rightArray = new int[right - mid];

        System.arraycopy(array, left, leftArray, 0, leftArray.length);
        System.arraycopy(array, mid + 1, rightArray, 0, rightArray.length);

        int i = 0, j = 0, k = left;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < leftArray.length) {
            array[k++] = leftArray[i++];
        }

        while (j < rightArray.length) {
            array[k++] = rightArray[j++];
        }
    }
}

