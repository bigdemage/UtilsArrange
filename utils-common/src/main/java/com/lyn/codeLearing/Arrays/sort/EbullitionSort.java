package com.lyn.codeLearing.Arrays.sort;

/**
 * 冒泡排序
 */
public class EbullitionSort {

    public static void mpSort(int[] arrs) {

        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs.length-i - 1; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    swap(arrs, j);
                }
            }
        }
    }

    /**
     * 数组两个数互换
     * @param arr
     * @param index index与index+1互换
     */
    public static void swap(int[] arr, int index) {
        int i = arr[index];
        arr[index] = arr[index + 1];
        arr[index + 1] = i;
    }

    public static void main(String[] args) {
        int[] arrs = {2, 5, 4, 1, 0, 9, 8, 56, 24};

        mpSort(arrs);

        for (int i : arrs) {
            System.out.println(i);
        }
    }
}
