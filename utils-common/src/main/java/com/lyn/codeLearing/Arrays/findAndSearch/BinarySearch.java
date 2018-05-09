package com.lyn.codeLearing.Arrays.findAndSearch;

import com.lyn.codeLearing.Arrays.sort.InsertSort;

/**
 * 二分查找
 * 先排序，每次取中间，一直到无元素为止
 */
public class BinarySearch {

    /**
     * 返回找到的下标
     * @param arrs
     * @param find
     * @return
     */
    public static int binarySerrch(int[] arrs,int find){
        int low =0;
        int high=arrs.length-1;
        int middle;

        while(low<high){
            middle=(high-low)/2;
            if(arrs[middle]==find){
                return middle;
            }
            if(arrs[middle]>find){
                high=middle;
            }
            if(arrs[middle]<find){
                low=middle+1;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        int find=50;

        int[] a={11, 1, 5, 9, 3, 6, 99, 22, 55};
        //先排序
        InsertSort.sort(a);

        System.out.println(binarySerrch(a,find));
        //[1, 3, 5, 6, 9, 11, 22, 55, 99]


    }

}
