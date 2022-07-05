package com.lyn.codeLearing.Arrays.sort;


import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void quickSort(int[] num, int left, int right) {
        System.out.println("方法刚开始的数组:" + Arrays.toString(num)+" left="+left+" right"+right);
        if (num == null)
            return;
        //如果左边大于右边，则return，这里是递归的终点，需要写在前面。
        if (left >= right) {
            return;
        }
        int i = left;
        System.out.println("i:" + i);
        int j = right;
        System.out.println("j:" + j);
        int temp = num[i];
        System.out.println("temp:" + temp);
        //此处开始进入遍历循环
        while (i < j) {
            //从右往左循环
            while (i < j && num[j] >= temp) {//如果num[j]大于temp值，则pass，比较下一个
                j--;
            }
            System.out.println("num[j] >= temp while循环后的j:" + j);
            num[i] = num[j];
            System.out.println("num[i] = num[j]后:"+Arrays.toString(num));
            while (i < j && num[i] <= temp) {
                i++;
            }
            System.out.println("num[i] <= temp while循环后的i:" + i);
            num[j] = num[i];
            System.out.println("num[j] = num[i]后"+Arrays.toString(num));
            num[i] = temp; // 此处不可遗漏，将基准值插入到指定位置
            System.out.println("num[i] = temp后"+Arrays.toString(num));
            System.out.println("-------------------------------------------------\n\n");

        }
        quickSort(num, left, i - 1);
        quickSort(num, i + 1, right);
    }


    public static void main(String[] args) {
        int[] a = {3, 1, 5, 9, 11, 6, 99, 22, 55};
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));


    }
}


