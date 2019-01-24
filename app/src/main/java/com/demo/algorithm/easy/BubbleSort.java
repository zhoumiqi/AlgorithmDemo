package com.demo.algorithm.easy;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int a[] = {3, 8, 89, 34, 21, 67, 100};
        bubbleSort(a, a.length);
        for (int i : a) {
            System.out.println("排序后的数字为：" + i);
        }
    }

    public static void bubbleSort(int a[], int n) {
        int i, j, temp;
        for (j = 0; j < n - 1; j++)
            for (i = 0; i < n - 1 - j; i++) {
                if (a[i] > a[i + 1]) {
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
    }
}
