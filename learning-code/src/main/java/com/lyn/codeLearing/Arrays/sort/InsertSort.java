package com.lyn.codeLearing.Arrays.sort;

import java.util.Arrays;

/**
 * 直接插入排序
 *
 *
 */
public class InsertSort {

    /**
     * 直接插入排序
     * [3 5 1 4 2]->[3 5 5 4 2]->[3 3 5 4 2]->[1 3 5 4 2]->[1 3 5 5 2]->[1 3 4 5 2]->[1 3 4 5 5]->[1 3 4 4 5]->[1 3 3 4 5]->[1 2 3 4 5]
     * @param arrs
     */
    public static void sort(int[] arrs){
        for(int i=1;i<arrs.length;i++) {
            int key =arrs[i];
            int j=i-1;
            while(j>=0&&arrs[j]>key){
                arrs[j+1]=arrs[j];
                j--;
            }
            arrs[j+1]=key;
        }
    }

    /**
     * 希尔排序
     * 对直接插入排序进行优化，先分出几个小块进行排序，最后来一次直接插入排序
     * {3, 1, 5, 9, 11, 6, 99, 22, 55} 先分出{11 6 99 22 55}小块，往前散开
     * @param arrs
     */
    public static void heerSort(int[] arrs){
        //从中间分块
        for(int gap =arrs.length/2;gap>0;gap/=2){
            for(int i=gap;i<arrs.length;i++){
                int key=arrs[i];
                int j=i-gap;
                while(j>=0&&arrs[j]>key){
                    arrs[j+gap]=arrs[j];
                    j-=gap;
                }
                arrs[j+gap]=key;
            }
        }


    }

    public static void main(String[] args) {
        int[] a ={3, 1, 5, 9, 11, 6, 99, 22, 55};
        heerSort(a);
        System.out.println(Arrays.toString(a));
    }


}
